package servlets;

import dao.DAOFactory;
import dao.TestDAO;
import hibernate.CourseEntity;
import hibernate.TestEntity;
import servlets.Utils.ServletHelper;
import servlets.core.AbstractViewServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet("/Tests")
public class Tests extends AbstractViewServlet<TestEntity, TestDAO> {

    @Override
    protected TestDAO getDao(){
        return DAOFactory.getInstance().getTestDAO();
    }

    @Override
    protected String getJspName(){
        return "Tests.jsp";
    }

    @Override
    protected boolean isCollection(){
        return true;
    }
}