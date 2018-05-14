package servlets;

import dao.DAOFactory;
import dao.LectionDAO;
import hibernate.CourseEntity;
import hibernate.LectionEntity;
import servlets.Utils.ServletHelper;
import servlets.core.AbstractViewServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet("/Lections")
public class Lections extends AbstractViewServlet<LectionEntity, LectionDAO> {

    @Override
    protected LectionDAO getDao(){
        return DAOFactory.getInstance().getLectionDAO();
    }

    @Override
    protected String getJspName(){
        return "Lections.jsp";
    }

    @Override
    protected boolean isCollection(){
        return true;
    }
}
