package servlets;

import common.ActionType;
import dao.DAOFactory;
import hibernate.TestEntity;
import servlets.Utils.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TestRun")
public class TestRun extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper<TestEntity> checker = new ServletHelper<>();
        if(checker.checkAndSetEntityError(request, response, DAOFactory.getInstance().getTestDAO(), ActionType.Read))
            return;

        request.setAttribute("test", checker.getEntity());
        request.getRequestDispatcher("TestRun.jsp").forward(request, response);
    }
}
