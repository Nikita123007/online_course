package servlets;

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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        request.setAttribute("lection", DAOFactory.getInstance().getLectionDAO().getLection(id));
        request.getRequestDispatcher("LectionEdit.jsp").forward(request, response);
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
}