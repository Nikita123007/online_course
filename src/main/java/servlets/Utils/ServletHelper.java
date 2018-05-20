package servlets.Utils;

import common.ActionType;
import dao.AbstractEntityDAO;
import hibernate.AbstractEntity;
import hibernate.UserEntity;
import request.AuthHelper;
import request.CookieHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static constants.Constants.Constant.CookieAuthToken;

public class ServletHelper<T extends AbstractEntity> {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private AbstractEntityDAO<T> dao;
    private ActionType action;
    private boolean isCollection = false;

    private Integer parentId = null;
    private UserEntity user;
    private T entity;

    public boolean checkAndSetError() throws IOException {
        Integer errorCode = getErrorCode(request, dao, action);
        if(errorCode != null){
            response.sendError(errorCode);
            return true;
        }

        return false;
    }

    public Integer getErrorCode(HttpServletRequest request, AbstractEntityDAO<T> dao, ActionType action){
        user = AuthHelper.GetAuthUser(CookieHelper.GetCookieValue(request, CookieAuthToken));

        if (user == null)
            return HttpServletResponse.SC_UNAUTHORIZED;

        if(action != ActionType.Create && !isCollection){
            Integer id = Utils.getInteger(request.getParameter("id"));
            if(id == null)
                return HttpServletResponse.SC_BAD_REQUEST;

            entity = dao.get(id);
            if (entity == null)
                return HttpServletResponse.SC_NOT_FOUND;

            if(!entity.checkRights(user, action))
                return HttpServletResponse.SC_FORBIDDEN;
        }
        else{
            parentId = Utils.getInteger(request.getParameter("parentId"));
            if(parentId != null && !dao.getParent(parentId).checkRights(user, action))
                return HttpServletResponse.SC_FORBIDDEN;
        }

        return null;
    }

    public UserEntity getUser(){
        return user;
    }

    public T getEntity(){
        return entity;
    }

    public Integer getParentId(){
        return parentId;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public AbstractEntityDAO<T> getDao() {
        return dao;
    }

    public void setDao(AbstractEntityDAO<T> dao) {
        this.dao = dao;
    }

    public ActionType getAction() {
        return action;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public void setCollection(boolean collection) {
        isCollection = collection;
    }
}
