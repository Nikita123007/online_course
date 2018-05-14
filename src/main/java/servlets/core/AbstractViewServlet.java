package servlets.core;

import common.ActionType;
import constants.Constants;
import dao.AbstractEntityDAO;
import hibernate.AbstractEntity;
import servlets.Utils.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
                int offset = 0;
                String offsetStr = request.getParameter("offset");
                if(offsetStr != null && !offsetStr.isEmpty())
                    offset = Integer.parseInt(offsetStr);

                Collection<EntityType> entities;
                if(helper.getParentId() != null){
                    request.setAttribute("parentId", helper.getParentId());
                    entities = getDao().getAllByParent(getDao().getParent(helper.getParentId()));
                }
                else{
                    entities = getDao().getAll();
                }
                request.setAttribute("entities", new ArrayList<>(entities).subList(offset, Math.min(offset + Constants.Constant.EntitiesPerPage, entities.size())));

                //Show prev and next buttons
                request.setAttribute("prev", (offset != 0) ? (offset - Constants.Constant.EntitiesPerPage) : null);
                request.setAttribute("next", (offset + Constants.Constant.EntitiesPerPage < entities.size()) ? (offset + Constants.Constant.EntitiesPerPage) : null);
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
