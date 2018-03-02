package Servlets;
import Models.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Users")
public class ExampleServletForUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException
    {
        List<User> listUsers = new ArrayList<User>();
        User user1 = new User();
        user1.setFirstName("Nekit");
        user1.setSecondName("Zabeyda");
        user1.setAge(20);
        listUsers.add(user1);
        User user2 = new User();
        user2.setFirstName("Dima");
        user2.setSecondName("Shulitsciy");
        user2.setAge(19);
        listUsers.add(user2);

        req.setAttribute("users", listUsers);
        req.getRequestDispatcher("Users.jsp").forward(req, resp);
    }
}
