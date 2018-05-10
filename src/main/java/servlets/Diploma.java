package servlets;

import dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Diploma")
public class Diploma extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        request.setAttribute("diploma", DAOFactory.getInstance().getDiplomaDAO().getDiploma(id));
        request.getRequestDispatcher("Diploma.jsp").forward(request, response);
    }
}
