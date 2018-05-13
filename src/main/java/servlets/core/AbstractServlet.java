package servlets.core;

import dao.AbstractEntityDAO;
import hibernate.AbstractEntity;

import javax.servlet.http.HttpServlet;

public abstract class AbstractServlet<EntityType extends AbstractEntity, DAOType extends AbstractEntityDAO<EntityType>> extends HttpServlet {
    protected abstract DAOType getDao();
    protected abstract String getJspName();
}
