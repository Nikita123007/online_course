package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import algorithms.AlgorithmsHelper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dao.DAOFactory;
import hibernate.UserEntity;
import response.AuthToken;
import response.ResponseData;
import constants.Pages.Page;
import static constants.Constants.Constant.*;
import request.*;

@WebServlet("/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Writer wr = response.getWriter();
        JsonObject json = new Gson().fromJson(request.getReader(), JsonElement.class).getAsJsonObject();
        String login = json.get("login").getAsString();
        String password = json.get("password").getAsString();
        if (login == null || login.equals("")){
            ResponseData responseData = new ResponseData("Enter login.", null);
            wr.write(responseData.toJson());
            wr.close();
            return;
        }
        if (password == null || password.equals("")){
            ResponseData responseData = new ResponseData("Enter password.", null);
            wr.write(responseData.toJson());
            wr.close();
            return;
        }
        UserEntity user = DAOFactory.getInstance().getUserDAO().findUserByLogin(login);
        String passwordHashInString = DatatypeConverter.printHexBinary(AlgorithmsHelper.GetHash(password));
        if (user != null && user.getPasswordHash().toUpperCase().equals(passwordHashInString.toUpperCase())) {
            if (user.getAuthToken() == null || user.getAuthToken().equals("")) {
                user.setAuthToken(AuthToken.GetToken());
                DAOFactory.getInstance().getUserDAO().merge(user);
            }
            response.addCookie(new Cookie(CookieAuthToken, user.getAuthToken()));
            ResponseData responseData = new ResponseData("", Page.Courses);
            wr.write(responseData.toJson());
        } else {
            ResponseData responseData = new ResponseData("Incorrect login or password.", null);
            wr.write(responseData.toJson());
        }
        wr.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logout = request.getParameter("logout");
        UserEntity user = AuthHelper.GetAuthUser(CookieHelper.GetCookieValue(request, CookieAuthToken));

        if (logout != null && logout.equals("1")){
            response.addCookie(new Cookie(CookieAuthToken, ""));
            if (user != null) {
                user.setAuthToken("");
                DAOFactory.getInstance().getUserDAO().merge(user);
            }
        }else {
            if (AuthHelper.GetAuthUser(CookieHelper.GetCookieValue(request, CookieAuthToken)) != null) {
                response.sendRedirect("Courses");
                return;
            }
        }
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }
}
