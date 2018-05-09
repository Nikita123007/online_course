package servlets;

import constants.Pages;
import constants.Roles;
import dao.DAOFactory;
import hibernate.CourseEntity;
import hibernate.LectionEntity;
import hibernate.SubscriptionEntity;
import hibernate.UserEntity;
import request.AuthHelper;
import response.ResponseData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInput;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet("/LectionEdit")
public class LectionEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserEntity user = AuthHelper.GetAuthUser(request);
        Writer wr = response.getWriter();
        String lectionName = request.getParameter("lectionName");
        String lectionText = request.getParameter("lectionText");
        String idCourse = request.getParameter("idCourse");

        if (ErrorsLectionName(lectionName) != null || ErrorsLectionText(lectionText) != null) {
            ResponseData responseData = new ResponseData("Invalid data. ", Pages.Page.Courses, new ArrayList<String>());
            String errors = ErrorsLectionName(lectionName);
            if (errors != null) {
                responseData.setError(responseData.getError() + errors + " ");
                responseData.getNameErrors().add("login");
            }
            errors = ErrorsLectionText(lectionText);
            if (errors != null) {
                responseData.setError(responseData.getError() + errors + " ");
                responseData.getNameErrors().add("email");
            }
            wr.write(responseData.ToJson());
            wr.close();
            return;
        }

        LectionEntity lection = new LectionEntity();
        lection.setName(lectionName);
        lection.setText(lectionText);
        lection.setCourse(Integer.parseInt(idCourse));
        DAOFactory.getInstance().getLectionDAO().addLection(lection);

        ResponseData responseData = new ResponseData("", Pages.Page.Courses, null);
        wr.write(responseData.ToJson());
        wr.close();
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserEntity user = AuthHelper.GetAuthUser(request);
        Writer wr = response.getWriter();
        String lectionName = request.getParameter("lectionName");
        String lectionText = request.getParameter("lectionText");
        String idLection = request.getParameter("idLection");
        LectionEntity lection = DAOFactory.getInstance().getLectionDAO().getLection(Integer.parseInt(idLection));

        if (lection == null || user == null || (user.getRole() != Roles.Role.Admin && lection.getCourseByCourse().getAuthor() != user.getIdUser())){
            ResponseData responseData = new ResponseData("", Pages.Page.Login, null);
            wr.write(responseData.ToJson());
            wr.close();
            return;
        }

        if (ErrorsLectionName(lectionName) != null || ErrorsLectionText(lectionText) != null) {
            ResponseData responseData = new ResponseData("Invalid data. ", Pages.Page.Courses, new ArrayList<String>());
            String errors = ErrorsLectionName(lectionName);
            if (errors != null) {
                responseData.setError(responseData.getError() + errors + " ");
                responseData.getNameErrors().add("login");
            }
            errors = ErrorsLectionText(lectionText);
            if (errors != null) {
                responseData.setError(responseData.getError() + errors + " ");
                responseData.getNameErrors().add("email");
            }
            wr.write(responseData.ToJson());
            wr.close();
            return;
        }

        lection.setName(lectionName);
        lection.setText(lectionText);
        DAOFactory.getInstance().getLectionDAO().mergeLection(lection);

        ResponseData responseData = new ResponseData("", Pages.Page.Courses, null);
        wr.write(responseData.ToJson());
        wr.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserEntity user = AuthHelper.GetAuthUser(request);
        String id = request.getParameter("id");
        String idCourse = request.getParameter("idCourse");

        if (user != null && id != null && !id.equals("")){
            if (Integer.parseInt(id) != -1) {
                LectionEntity lection = DAOFactory.getInstance().getLectionDAO().getLection(Integer.parseInt(id));
                if (lection != null && (user.getRole() == Roles.Role.Admin || lection.getCourseByCourse().getAuthor() == user.getIdUser())) {
                    request.setAttribute("lection", lection);
                    request.setAttribute("userName", user.getName());
                    request.getRequestDispatcher("LectionEdit.jsp").forward(request, response);
                    return;
                }
            }else{
                CourseEntity course = DAOFactory.getInstance().getCourseDAO().getCourse(Integer.parseInt(idCourse));
                if (course != null && (user.getRole() == Roles.Role.Admin || course.getAuthor() == user.getIdUser())) {
                    LectionEntity lection = new LectionEntity();
                    lection.setText("");
                    lection.setName("");
                    lection.setIdLection(-1);
                    lection.setCourse(course.getIdCourse());
                    request.setAttribute("lection", lection);
                    request.setAttribute("userName", user.getName());
                    request.getRequestDispatcher("LectionEdit.jsp").forward(request, response);
                    return;
                }
            }
        }

        response.sendRedirect("Login");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Writer wr = response.getWriter();
        UserEntity user = AuthHelper.GetAuthUser(request);
        String id = request.getParameter("id");

        if (user != null && id != null && !id.equals("")){
            LectionEntity lection = DAOFactory.getInstance().getLectionDAO().getLection(Integer.parseInt(id));
            if (lection != null && (user.getRole() == Roles.Role.Admin || lection.getCourseByCourse().getAuthor() == user.getIdUser())) {
                DAOFactory.getInstance().getLectionDAO().removeLection(lection);
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

    private String ErrorsLectionName(String lectionName) {
        if (lectionName.length() == 0){
            return "Input lection name.";
        }
        return null;
    }

    private String ErrorsLectionText(String lectionText) {
        if (lectionText.length() == 0){
            return "Input lection text.";
        }
        return null;
    }
}