package servlets;

import constants.Pages;
import dao.CourseDAO;
import dao.DAOFactory;
import hibernate.CourseEntity;
import hibernate.SubscriptionEntity;
import hibernate.UserEntity;
import request.AuthHelper;
import request.CookieHelper;
import response.ResponseData;
import servlets.Utils.Utils;
import servlets.core.AbstractViewServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.sql.Timestamp;

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
        Integer idCourse = Utils.getInteger(request.getParameter("idCourse"));

        if (user == null || idCourse == null){
            ResponseData responseData = new ResponseData("", Pages.Page.Courses);
            wr.write(responseData.toJson());
            wr.close();
            return;
        }

        CourseEntity course = DAOFactory.getInstance().getCourseDAO().get(idCourse);
        if (course == null || course.isSubscribed(user.getIdUser())) {
            ResponseData responseData = new ResponseData("", Pages.Page.Courses);
            wr.write(responseData.toJson());
            wr.close();
            return;
        }

        SubscriptionEntity subscription = new SubscriptionEntity();
        subscription.setCourseByCourse(DAOFactory.getInstance().getCourseDAO().get(idCourse));
        subscription.setUserByUser(user);
        subscription.setDate(new Timestamp(new Date().getTime()));
        user.getSubscriptionsByIdUser().add(subscription);
        DAOFactory.getInstance().getSubscriptionDAO().add(subscription);

        ResponseData responseData = new ResponseData("", Pages.Page.Courses);
        wr.write(responseData.toJson());
        wr.close();
    }
}
