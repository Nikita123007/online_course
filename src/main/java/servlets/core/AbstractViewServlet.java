package servlets.core;

import common.ActionType;
import dao.AbstractEntityDAO;
import hibernate.AbstractEntity;
import servlets.Utils.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractViewServlet<EntityType extends AbstractEntity, DAOType extends AbstractEntityDAO<EntityType>> extends AbstractServlet<EntityType, DAOType> {
    @Override
    protected final void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper<EntityType> helper = new ServletHelper<>();
        if(!helper.checkAndSetEntityError(request, response, getDao(), ActionType.Read)){
            request.setAttribute("entity", helper.getEntity());
            processGet(request, response, helper);
            request.getRequestDispatcher(getJspName()).forward(request, response);
        }
    }

    protected void processGet(HttpServletRequest request, HttpServletResponse response, ServletHelper<EntityType> helper) throws ServletException, IOException{
    }
}
