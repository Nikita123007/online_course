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
        helper.setAction(ActionType.Read);
        helper.setRequest(request);
        helper.setResponse(response);
        helper.setDao(getDao());
        helper.setCollection(isCollection());

        if(!helper.checkAndSetError()){
            if(helper.getEntity() != null)
                request.setAttribute("entity", helper.getEntity());

            if(isCollection()){
                if(helper.getParentId() != null){
                    request.setAttribute("entities", getDao().getAllByParent(getDao().getParent(helper.getParentId())));
                }
                else{
                    request.setAttribute("entities", getDao().getAll());
                }
            }

            request.setAttribute("user", helper.getUser());
            processGet(helper);
            request.getRequestDispatcher(getJspName()).forward(request, response);
        }
    }

    protected void processGet(ServletHelper<EntityType> helper) throws ServletException, IOException{
    }
    protected abstract boolean isCollection();
}
