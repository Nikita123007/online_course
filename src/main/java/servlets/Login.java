package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import algorithms.AlgorithmsHelper;
import dao.DAOFactory;
import hibernate.UserEntity;
import response.AuthToken;
import response.ResponseData;
import constants.Pages.Page;
import static constants.Constants.Constant.*;
import request.*;

@WebServlet("/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Writer wr = response.getWriter();
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UserEntity user = DAOFactory.getInstance().getUserDAO().findUser(login);
        String passwordHashInString = DatatypeConverter.printHexBinary(AlgorithmsHelper.GetHash(password));
        if (user != null && user.getPasswordHash().equals(passwordHashInString)) {
            if (user.getAuthToken() == null || user.getAuthToken().equals("")) {
                user.setAuthToken(AuthToken.GetToken());
                DAOFactory.getInstance().getUserDAO().mergeUser(user);
            }
            response.addCookie(new Cookie(CookieAuthToken, user.getAuthToken()));
            ResponseData responseData = new ResponseData("", Page.Cources, null);
            wr.write(responseData.ToJson());
        } else {
            ResponseData responseData = new ResponseData("Incorrect login or password.", null, new ArrayList<String>());
            responseData.getNameErrors().add("login");
            responseData.getNameErrors().add("password");
            wr.write(responseData.ToJson());
        }
        wr.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logout = request.getParameter("logout");
        UserEntity user = AuthHelper.GetAuthUser(request);

        if (logout != null && logout.equals("1")){
            response.addCookie(new Cookie(CookieAuthToken, ""));
            if (user != null) {
                user.setAuthToken("");
                DAOFactory.getInstance().getUserDAO().mergeUser(user);
            }
        }else {
            if (AuthHelper.GetAuthUser(request) != null) {
                response.sendRedirect("Courses");
                return;
            }
        }
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }
}
