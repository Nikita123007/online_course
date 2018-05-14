package servlets;

import common.ActionType;
import dao.DAOFactory;
import dao.TestDAO;
import hibernate.TestEntity;
import servlets.Utils.ServletHelper;
import servlets.core.AbstractViewServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TestRun")
public class TestRun extends AbstractViewServlet<TestEntity, TestDAO> {

    @Override
    protected TestDAO getDao(){
        return DAOFactory.getInstance().getTestDAO();
    }

    @Override
    protected String getJspName(){
        return "TestRun.jsp";
    }

    @Override
    protected boolean isCollection(){
        return false;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
