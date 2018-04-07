package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

import dao.DAOFactory;
import hibernate.UserEntity;
import response.ResponseData;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserEntity user = new UserEntity();
        user.setName(login);
        user.setPasswordHash(password);
        user.setEmail(email);
        DAOFactory.getInstance().getUserDAO().addUser(user);

        ResponseData responseData = new ResponseData("Success", null, new String[]{});
        Writer wr = response.getWriter();
        wr.write(responseData.ToJson());
        wr.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Registration.jsp").forward(request, response);
    }
}
