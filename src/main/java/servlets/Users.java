package servlets;

import dao.DAOFactory;
import dao.UserDAO;
import hibernate.UserEntity;
import servlets.core.AbstractViewServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet("/Users")
public class Users extends AbstractViewServlet<UserEntity, UserDAO> {

    @Override
    protected UserDAO getDao(){
        return DAOFactory.getInstance().getUserDAO();
    }

    @Override
    protected String getJspName(){
        return "Users.jsp";
    }

    @Override
    protected boolean isCollection(){
        return true;
    }
}