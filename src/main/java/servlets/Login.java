package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

import dao.DAOFactory;
import hibernate.UserEntity;
import response.ResponseData;
import constants.Pages.Page;

@WebServlet("/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Writer wr = response.getWriter();
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UserEntity user = DAOFactory.getInstance().getUserDAO().findUser(login);
        if (user != null && user.getPasswordHash().equals(password)){
            HttpSession session=request.getSession();
            session.setAttribute("UserId", user.getIdUser());

            ResponseData responseData = new ResponseData("", Page.Cources, null);
            wr.write(responseData.ToJson());
        }else {
            ResponseData responseData = new ResponseData("Неправильный логин или пароль", null, new String[]{"login","password"});
            wr.write(responseData.ToJson());
        }
        wr.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }
}
