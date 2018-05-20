package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;

import constants.Pages.Page;
import constants.Roles.*;
import dao.DAOFactory;
import hibernate.UserEntity;
import request.AuthHelper;
import request.CookieHelper;
import response.AuthToken;
import response.ResponseData;
import algorithms.AlgorithmsHelper;

import static constants.Constants.Constant.CookieAuthToken;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Writer wr = response.getWriter();
        final String login = request.getParameter("login");
        final String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (ErrorsLogin(login) != null || ErrorsEmail(email) != null || ErrorsPassword(password) != null) {
            ResponseData responseData = new ResponseData("Invalid data", Page.Courses);
            String errors = ErrorsLogin(login);
            if (errors != null) {
                responseData.setError(responseData.getError() + errors + " ");
            }
            errors = ErrorsEmail(email);
            if (errors != null) {
                responseData.setError(responseData.getError() + errors + " ");
            }
            errors = ErrorsLogin(login);
            if (errors != null) {
                responseData.setError(responseData.getError() + errors + " ");
            }
            wr.write(responseData.toJson());
            wr.close();
            return;
        }

        Collection<UserEntity> users = DAOFactory.getInstance().getUserDAO().getAll();
        UserEntity userByName = null;
        UserEntity userByEmail = null;
        for (UserEntity item : users) {
            if (item.getName().equals(login)) {
                userByName = item;
            }
            if (item.getEmail().equals(email)) {
                userByEmail = item;
            }
        }

        ResponseData responseData = new ResponseData("", Page.Courses);
        if (userByName != null || userByEmail != null) {
            if (userByName != null) {
                responseData.setError(responseData.getError() + "This login already exist." + " ");
            }
            if (userByEmail != null) {
                responseData.setError(responseData.getError() + "This email already exist." + " ");
            }
        } else {
            UserEntity user = new UserEntity();
            user.setName(login);
            user.setPasswordHash(DatatypeConverter.printHexBinary(AlgorithmsHelper.GetHash(password)));
            user.setEmail(email);
            user.setAuthToken(AuthToken.GetToken());
            response.addCookie(new Cookie(CookieAuthToken, user.getAuthToken()));
            user.setRole(DAOFactory.getInstance().getRoleDAO().get(Role.User));
            DAOFactory.getInstance().getUserDAO().add(user);
            responseData = new ResponseData("", Page.Courses);
        }

        wr.write(responseData.toJson());
        wr.close();
    }

    private String ErrorsLogin(String login) {
        if (login.length() == 0){
            return "Input login.";
        }
        return null;
    }

    private String ErrorsEmail(String email) {
        if (email.length() == 0){
            return "Input email.";
        }
        return null;
    }

    private String ErrorsPassword(String password) {
        if (password.length() == 0){
            return "Input password";
        }
        return null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (AuthHelper.GetAuthUser(CookieHelper.GetCookieValue(request, CookieAuthToken)) != null) {
            response.sendRedirect("Courses");
            return;
        }
        request.getRequestDispatcher("Registration.jsp").forward(request, response);
    }
}
