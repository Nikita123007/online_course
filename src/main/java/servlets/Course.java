package servlets;

import dao.CourseDAO;
import dao.DAOFactory;
import hibernate.*;
import servlets.Utils.ServletHelper;
import servlets.core.AbstractViewServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Course")
public class Course extends AbstractViewServlet<CourseEntity, CourseDAO> {

    @Override
    protected CourseDAO getDao(){
        return DAOFactory.getInstance().getCourseDAO();
    }

    @Override
    protected String getJspName(){
        return "Course.jsp";
    }

    @Override
    protected boolean isCollection(){
        return false;
    }

    @Override
    protected void processGet(ServletHelper<CourseEntity> helper) throws ServletException, IOException {
        List<TestEntityViewModel> tests = new ArrayList<>();
        for (TestEntity test : helper.getEntity().getTests()) {
            CompletedTestEntity completedTest = DAOFactory.getInstance().getCompletedTestDAO().getCompletedTest(helper.getUser().getId(), test.getId());
            TestEntityViewModel testViewModel = new TestEntityViewModel(test.getId(), test.getCourse().getId(), test.getName(), completedTest == null ? (byte) 0 : (byte) 1);
            tests.add(testViewModel);
        }

        helper.getRequest().setAttribute("tests", tests);
        helper.getRequest().setAttribute("courseCompleted", false);
    }

    public class TestEntityViewModel {
        private int idTest;
        private int course;
        private String name;
        private byte completed;

        public TestEntityViewModel(int idTest, int course, String name, byte completed){
            this.idTest = idTest;
            this.course = course;
            this.name = name;
            this.completed = completed;
        }

        public int getIdTest() {
            return idTest;
        }

        public void setIdTest(int idTest) {
            this.idTest = idTest;
        }

        public int getCourse() {
            return course;
        }

        public void setCourse(int course) {
            this.course = course;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public byte getCompleted() {
            return completed;
        }

        public void setCompleted(byte completed) {
            this.completed = completed;
        }
    }
}
