package servlets;

import constants.Pages;
import hibernate.UserEntity;
import request.AuthHelper;
import request.CookieHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.Constants.Constant.CookieAuthToken;

@WebServlet("/Cube")
public class Cube extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserEntity user = AuthHelper.GetAuthUser(CookieHelper.GetCookieValue(request, CookieAuthToken));
        request.setAttribute("user", user);

        request.getRequestDispatcher("Cube.jsp").forward(request, response);
    }
}
