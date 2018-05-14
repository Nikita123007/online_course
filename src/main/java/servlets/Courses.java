package servlets;

import constants.Pages;
import constants.Roles.*;
import dao.CourseDAO;
import dao.DAOFactory;
import hibernate.CourseEntity;
import hibernate.SubscriptionEntity;
import hibernate.UserEntity;
import request.AuthHelper;
import request.CookieHelper;
import response.ResponseData;
import servlets.Utils.ServletHelper;
import servlets.core.AbstractServlet;
import servlets.core.AbstractViewServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import static constants.Constants.Constant.CookieAuthToken;

@WebServlet("/Courses")
public class Courses extends AbstractViewServlet<CourseEntity, CourseDAO> {

    @Override
    protected CourseDAO getDao(){
        return DAOFactory.getInstance().getCourseDAO();
    }

    @Override
    protected String getJspName(){
        return "Courses.jsp";
    }

    @Override
    protected boolean isCollection(){
        return true;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserEntity user = AuthHelper.GetAuthUser(CookieHelper.GetCookieValue(request, CookieAuthToken));
        Writer wr = response.getWriter();
        String idCourse = request.getParameter("idCourse");

        if (user == null || idCourse == null || idCourse.equals("")){
            ResponseData responseData = new ResponseData("", Pages.Page.Courses, null);
            wr.write(responseData.toJson());
            wr.close();
            return;
        }

        CourseEntity course = DAOFactory.getInstance().getCourseDAO().get(Integer.parseInt(idCourse));
        if (course == null || course.isSubscribed(user.getIdUser())) {
            ResponseData responseData = new ResponseData("", Pages.Page.Courses, null);
            wr.write(responseData.toJson());
            wr.close();
            return;
        }

        SubscriptionEntity subscription = new SubscriptionEntity();
        subscription.setCourseByCourse(DAOFactory.getInstance().getCourseDAO().get(Integer.parseInt(idCourse)));
        subscription.setUserByUser(user);
        subscription.setDate(new Timestamp(new Date().getTime()));
        user.getSubscriptionsByIdUser().add(subscription);
        DAOFactory.getInstance().getSubscriptionDAO().add(subscription);

        ResponseData responseData = new ResponseData("", Pages.Page.Courses, null);
        wr.write(responseData.toJson());
        wr.close();
    }

    @Override
    protected void processGet(ServletHelper<CourseEntity> helper) throws ServletException, IOException {
        UserEntity user = helper.getUser();
        List<CourseEntity> courses;
        List<CourseEntity> subscribeCourses;
        Collection<CourseEntity> coursesSelf;

        if (user.admin()){
            courses = new ArrayList<>();
            subscribeCourses = new ArrayList<>();
            coursesSelf = DAOFactory.getInstance().getCourseDAO().getAll();
        }
        else{
            coursesSelf = user.getCoursesByIdUser();

            subscribeCourses = user.getSubscriptionsByIdUser().stream()
                    .map(s ->  s.getCourseByCourse()).collect(Collectors.toList());

            courses = DAOFactory.getInstance().getCourseDAO().getAll().stream()
                    .filter(c -> !coursesSelf.contains(c) && !subscribeCourses.contains(c)).collect(Collectors.toList());
        }
        helper.getRequest().setAttribute("courses", courses);
        helper.getRequest().setAttribute("coursesSelf", coursesSelf);
        helper.getRequest().setAttribute("subscribeCourses", subscribeCourses);
    }
}
