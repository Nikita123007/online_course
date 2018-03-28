package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

import response.ResponseData;
import constants.Pages.Page;

@WebServlet("/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Writer wr = response.getWriter();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String loginAndPasswordErrors = CheckLoginErrors(login) + CheckPasswordErrors(password);
        if (loginAndPasswordErrors.replace(" ", "").length() == 0){
            ResponseData responseData = new ResponseData("", Page.Cources, null);
            wr.write(responseData.ToJson());
        }else {
            ResponseData responseData = new ResponseData(loginAndPasswordErrors, null, new String[]{"login","password"});
            wr.write(responseData.ToJson());
        }
        wr.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }

    private String CheckLoginErrors(String login){
        //check correct and exist login
        if (login.equals("Nekit")){
            return  "Fuck you, it's mine login.";
        }
        return "";
    }

    private String CheckPasswordErrors(String password){
        //check correct password
        return "";
    }
}
