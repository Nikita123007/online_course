package servlets.core;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import common.ActionType;
import dao.AbstractEntityDAO;
import hibernate.AbstractEntity;
import response.ResponseData;
import servlets.Utils.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public abstract class AbstractEditServlet<EntityType extends AbstractEntity, DAOType extends AbstractEntityDAO<EntityType>> extends AbstractServlet<EntityType, DAOType> {
    @Override
    protected final void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doEdit(request, response, ActionType.Create);
    }

    @Override
    protected final void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doEdit(request, response, ActionType.Update);
    }

    @Override
    protected final void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doEdit(request, response, ActionType.Delete);
    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response, ActionType action) throws IOException{
        Writer wr = response.getWriter();

        ServletHelper<EntityType> helper = new ServletHelper<>();
        helper.setAction(action);
        helper.setRequest(request);
        helper.setResponse(response);
        helper.setDao(getDao());

        if(helper.getErrorCode(request, getDao(), action) != null){
            wr.write(new ResponseData("Error.", null, null).toJson());
            wr.close();
            return;
        }

        EntityType entity;
        if(action == ActionType.Create){
            entity = createEntity(helper);
        }
        else{
            entity = helper.getEntity();
        }

        ResponseData responseData;
        if(action == ActionType.Delete) {
            responseData = new ResponseData("", null, null);
            getDao().remove(entity);
        }
        else{
            request.setCharacterEncoding("UTF-8");
            JsonObject json = new Gson().fromJson(request.getReader(), JsonElement.class).getAsJsonObject();
            parseEntity(entity, json);

            responseData = getResponseData(entity);
            if(!responseData.isError()){
                if(action == ActionType.Create){
                    getDao().add(entity);
                }
                else{
                    getDao().merge(entity);
                }
            }
        }

        wr.write(responseData.toJson());
        wr.close();
    }

    protected final void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper<EntityType> helper = new ServletHelper<>();

        helper.setAction(ActionType.Read);
        helper.setRequest(request);
        helper.setResponse(response);
        helper.setDao(getDao());

        String addStr = request.getParameter("add");
        ActionType action = addStr != null && addStr.equals("true") ? ActionType.Create : ActionType.Update;
        if(helper.checkAndSetError())
            return;

        if (action == ActionType.Update) {
            request.setAttribute("entity", helper.getEntity());
            setUpdateAttributes(helper);
        }else{
            request.setAttribute("entity", createEntity(helper));
            setAddAttributes(helper);
        }
        request.getRequestDispatcher(getJspName()).forward(request, response);
    }

    protected abstract EntityType createEntity(ServletHelper<EntityType> helper);
    protected abstract ResponseData getResponseData(EntityType entity);
    protected abstract void parseEntity(EntityType entity, JsonObject json);
    protected void setUpdateAttributes(ServletHelper<EntityType> helper){
    }
    protected void setAddAttributes(ServletHelper<EntityType> helper){
        setUpdateAttributes(helper);
    }
}
