package servlets;

import constants.Roles;
import dao.DAOFactory;
import hibernate.*;
import request.AuthHelper;
import request.CookieHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static constants.Constants.Constant.CookieAuthToken;

@WebServlet("/Course")
public class Course extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserEntity user = AuthHelper.GetAuthUser(CookieHelper.GetCookieValue(request, CookieAuthToken));
        String id = request.getParameter("id");

        //TODO return 403, 404
        if (user == null) {
            response.sendRedirect("Login");
            return;
        }

        if(user.getRole() == Roles.Role.Admin || id == null || id.equals("")){
            response.sendRedirect("Courses");
            return;
        }

        CourseEntity course = DAOFactory.getInstance().getCourseDAO().getCourse(Integer.parseInt(id));
        if (course == null) {
            response.sendRedirect("Courses");
            return;
        }

        if (! course.isSubscribed(user.getIdUser())) {
            response.sendRedirect("Courses");
            return;
        }

        List<TestEntityViewModel> tests = new ArrayList<>();
        for (TestEntity test : course.getTestsByIdCourse()) {
            CompletedTestEntity completedTest = DAOFactory.getInstance().getCompletedTestDAO().getCompletedTest(user.getIdUser(), test.getIdTest());
            TestEntityViewModel testViewModel = new TestEntityViewModel(test.getIdTest(), test.getCourse(), test.getName(), completedTest == null ? (byte) 0 : (byte) 1);
            tests.add(testViewModel);
        }
        request.setAttribute("course", course);
        request.setAttribute("tests", tests);
        request.setAttribute("userName", user.getName());
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
