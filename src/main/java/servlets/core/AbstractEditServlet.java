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
            wr.write(new ResponseData("Error.", null).toJson());
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
            responseData = new ResponseData("", getNextUrl(helper, entity));
        }
        else{
            request.setCharacterEncoding("UTF-8");
            JsonObject json = new Gson().fromJson(request.getReader(), JsonElement.class).getAsJsonObject();
            parseEntity(helper, entity, json);

            String errorString = getErrorString(helper, entity);
            if(errorString.isEmpty()){
                responseData = new ResponseData("", getNextUrl(helper, entity));
            }
            else{
                responseData = new ResponseData("Invalid data.\n" + errorString, "");
            }
        }

        if(!responseData.isError()){
            switch (action){
                case Create:
                    getDao().add(entity);
                    break;
                case Update:
                    getDao().merge(entity);
                    break;
                case Delete:
                    getDao().remove(entity);
                    break;
            }
        }

        wr.write(responseData.toJson());
        wr.close();
    }

    protected final void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String addStr = request.getParameter("add");
        ActionType action = addStr != null && addStr.equals("true") ? ActionType.Create : ActionType.Update;

        ServletHelper<EntityType> helper = new ServletHelper<>();
        helper.setAction(action);
        helper.setRequest(request);
        helper.setResponse(response);
        helper.setDao(getDao());
        if(helper.checkAndSetError())
            return;

        request.setAttribute("user", helper.getUser());
        request.setAttribute("add", action == ActionType.Create);
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
    protected abstract String getNextUrl(ServletHelper<EntityType> helper, EntityType entity);
    protected abstract String getErrorString(ServletHelper<EntityType> helper, EntityType entity);
    protected abstract void parseEntity(ServletHelper<EntityType> helper, EntityType entity, JsonObject json);
    protected void setUpdateAttributes(ServletHelper<EntityType> helper){
    }
    protected void setAddAttributes(ServletHelper<EntityType> helper){
        setUpdateAttributes(helper);
    }
}
