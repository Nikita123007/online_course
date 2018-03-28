package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

import response.ResponseData;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Writer wr = response.getWriter();
        String secondName = request.getParameter("secondName");
        String name = request.getParameter("name");
        String extensionName = request.getParameter("extensionName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("genderRadios");
        String password = request.getParameter("password");
        String license = request.getParameter("license");
        /*wr.write(
                "Second name: " + secondName +
                "</br>Name: " + name +
                "</br>ExtensionName: " + extensionName +
                "</br>Email: " + email +
                "</br>Phone: " + phone +
                "</br>Gender: " + gender +
                "</br>Password: " + password +
                "</br>License: " + license
        );*/
        ResponseData responseData = new ResponseData("Second name: " + secondName +
                "</br>Name: " + name +
                "</br>ExtensionName: " + extensionName +
                "</br>Email: " + email +
                "</br>Phone: " + phone +
                "</br>Gender: " + gender +
                "</br>Password: " + password +
                "</br>License: " + license, null, new String[]{});
        wr.write(responseData.ToJson());
        wr.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Registration.jsp").forward(request, response);
    }
}
