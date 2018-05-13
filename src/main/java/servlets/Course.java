package servlets;

import dao.CourseDAO;
import dao.DAOFactory;
import hibernate.*;
import servlets.Utils.ServletHelper;
import servlets.core.AbstractViewServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    protected void processGet(HttpServletRequest request, HttpServletResponse response, ServletHelper<CourseEntity> helper) throws ServletException, IOException {
        List<TestEntityViewModel> tests = new ArrayList<>();
        for (TestEntity test : helper.getEntity().getTestsByIdCourse()) {
            CompletedTestEntity completedTest = DAOFactory.getInstance().getCompletedTestDAO().getCompletedTest(helper.getUser().getIdUser(), test.getIdTest());
            TestEntityViewModel testViewModel = new TestEntityViewModel(test.getIdTest(), test.getCourse(), test.getName(), completedTest == null ? (byte) 0 : (byte) 1);
            tests.add(testViewModel);
        }

        request.setAttribute("tests", tests);
        request.setAttribute("userName", helper.getUser().getName());
        request.getRequestDispatcher("Course.jsp").forward(request, response);
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
