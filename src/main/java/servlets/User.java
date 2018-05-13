package servlets;

import dao.DAOFactory;
import dao.UserDAO;
import hibernate.UserEntity;
import servlets.core.AbstractViewServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet("/User")
public class User extends AbstractViewServlet<UserEntity, UserDAO> {

    protected UserDAO getDao(){
        return DAOFactory.getInstance().getUserDAO();
    }

    @Override
    protected String getJspName(){
        return "User.jsp";
    }
}
