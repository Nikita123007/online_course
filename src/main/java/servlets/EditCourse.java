package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import constants.Pages;
import constants.Roles;
import dao.DAOFactory;
import hibernate.CourseEntity;
import hibernate.SubscriptionEntity;
import hibernate.TestEntity;
import hibernate.UserEntity;
import request.AuthHelper;
import response.ResponseData;
import constants.Roles.*;

@WebServlet("/EditCourse")
public class EditCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserEntity user = AuthHelper.GetAuthUser(request);
        Writer wr = response.getWriter();
        String courseName = request.getParameter("courseName");
        String courseDescription = request.getParameter("courseDescription");
        String idCourse = request.getParameter("idCourse");
        CourseEntity course = DAOFactory.getInstance().getCourseDAO().getCourse(Integer.parseInt(idCourse));

        if (course == null || user == null || (user.getRole() != Role.Admin && course.getAuthor() != user.getIdUser())){
            ResponseData responseData = new ResponseData("", Pages.Page.Login, null);
            wr.write(responseData.ToJson());
            wr.close();
            return;
        }

        if (ErrorsCourseName(courseName) != null || ErrorsCourseDescription(courseDescription) != null) {
            ResponseData responseData = new ResponseData("Invalid data. ", Pages.Page.Courses, new ArrayList<String>());
            String errors = ErrorsCourseName(courseName);
            if (errors != null) {
                responseData.setError(responseData.getError() + errors + " ");
                responseData.getNameErrors().add("login");
            }
            errors = ErrorsCourseDescription(courseDescription);
            if (errors != null) {
                responseData.setError(responseData.getError() + errors + " ");
                responseData.getNameErrors().add("email");
            }
            wr.write(responseData.ToJson());
            wr.close();
            return;
        }

        course.setName(courseName);
        course.setDescription(courseDescription);
        DAOFactory.getInstance().getCourseDAO().mergeCourse(course);

        ResponseData responseData = new ResponseData("", Pages.Page.Courses, null);
        wr.write(responseData.ToJson());
        wr.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserEntity user = AuthHelper.GetAuthUser(request);
        String id = request.getParameter("id");

        if (user != null && id != null && !id.equals("")){
            CourseEntity course = DAOFactory.getInstance().getCourseDAO().getCourse(Integer.parseInt(id));
            if (course != null && (user.getRole() == Roles.Role.Admin || course.getAuthor() == user.getIdUser())) {
                request.setAttribute("course", course);
                request.setAttribute("userName", user.getName());
                request.getRequestDispatcher("EditCourse.jsp").forward(request, response);
                return;
            }
        }

        response.sendRedirect("Login");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Writer wr = response.getWriter();
        UserEntity user = AuthHelper.GetAuthUser(request);
        String id = request.getParameter("id");

        if (user != null && id != null && !id.equals("")){
            CourseEntity course = DAOFactory.getInstance().getCourseDAO().getCourse(Integer.parseInt(id));
            if (course != null && (user.getRole() == Roles.Role.Admin || course.getAuthor() == user.getIdUser())) {
                DAOFactory.getInstance().getCourseDAO().removeCourse(course);
                ResponseData responseData = new ResponseData("", null, null);
                wr.write(responseData.ToJson());
                wr.close();
                return;
            }
        }

        ResponseData responseData = new ResponseData("Error.", null, null);
        wr.write(responseData.ToJson());
        wr.close();
    }

    private String ErrorsCourseName(String courseName) {
        if (courseName.length() == 0){
            return "Input course name.";
        }
        return null;
    }

    private String ErrorsCourseDescription(String courseDescription) {
        if (courseDescription.length() == 0){
            return "Input course description.";
        }
        return null;
    }
}
