package servlets;

import dao.DAOFactory;
import dao.DiplomaDAO;
import hibernate.DiplomaEntity;
import servlets.core.AbstractViewServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet("/Diploma")
public class Diploma extends AbstractViewServlet<DiplomaEntity, DiplomaDAO> {

    @Override
    protected DiplomaDAO getDao(){
        return DAOFactory.getInstance().getDiplomaDAO();
    }

    @Override
    protected String getJspName(){
        return "Diploma.jsp";
    }
}
