package servlets.core;

import common.ActionType;
import constants.Constants;
import dao.AbstractEntityDAO;
import hibernate.AbstractEntity;
import servlets.Utils.ServletHelper;
import servlets.Utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

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
                Collection<EntityType> entities;
                if(helper.getParentId() != null){
                    request.setAttribute("parentId", helper.getParentId());
                    entities = getDao().getAllByParent(getDao().getParent(helper.getParentId()));
                }
                else{
                    entities = getDao().getAll();
                }
                Integer count = Utils.getInteger(request.getParameter("count"));
                if(count == null || count < Constants.Constant.EntitiesPerPage)
                    count = Constants.Constant.EntitiesPerPage;

                Integer offset = Utils.getInteger(request.getParameter("offset"));
                if(offset == null || offset < 0)
                    offset = 0;
                offset = (offset / count) * count;

                request.setAttribute("entities", new ArrayList<>(entities).subList(offset, Math.min(offset + count, entities.size())));

                //Show prev and next buttons
                request.setAttribute("prev", (offset != 0) ? (offset - count) : null);
                request.setAttribute("next", (offset + count < entities.size()) ? (offset + count) : null);
                request.setAttribute("offset", offset);
                request.setAttribute("count", count);
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
