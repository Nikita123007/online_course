package servlets;

import constants.Roles.*;
import dao.DAOFactory;
import hibernate.CourseEntity;
import hibernate.SubscriptionEntity;
import hibernate.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.query.Query;
import request.AuthHelper;

import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet("/Courses")
public class Courses extends HttpServlet {

    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserEntity user = AuthHelper.GetAuthUser(request);
        List<CourseEntity> coursesSelf = new ArrayList<CourseEntity>();
        List<CourseEntity> courses = new ArrayList<CourseEntity>();
        List<CourseEntity> subscribeCourses = new ArrayList<CourseEntity>();

        if (user != null){
            if (user.getRole() == Role.User) {
                coursesSelf = new ArrayList<CourseEntity>(DAOFactory.getInstance().getCourseDAO().getAllByUser(user.getIdUser()));
                Collection<SubscriptionEntity> subscriptions = DAOFactory.getInstance().getSubscriptionDAO().getAllSubscriptionByUser(user.getIdUser());
                for (SubscriptionEntity subscription : subscriptions){
                    subscribeCourses.add(DAOFactory.getInstance().getCourseDAO().getCourse(subscription.getCourse()));
                }
                Collection<CourseEntity> tempAllCourses = DAOFactory.getInstance().getCourseDAO().getAll();
                for (CourseEntity course : tempAllCourses){
                    if (!coursesSelf.contains(course) && !subscribeCourses.contains(course)){
                        courses.add(course);
                    }
                }
            }else if (user.getRole() == Role.Admin){
                coursesSelf = new ArrayList<CourseEntity>(DAOFactory.getInstance().getCourseDAO().getAll());
            }
            request.setAttribute("courses", courses);
            request.setAttribute("coursesSelf", coursesSelf);
            request.setAttribute("subscribeCourses", subscribeCourses);
            request.setAttribute("userName", user.getName());
            request.getRequestDispatcher("Courses.jsp").forward(request, response);
            return;
        }else{
            response.sendRedirect("Login");
            return;
        }
    }
}
