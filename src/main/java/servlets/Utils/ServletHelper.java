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

    private Integer parentId = null;
    private UserEntity user;
    private T entity;

    public UserEntity getUser(){
        return user;
    }

    public T getEntity(){
        return entity;
    }

    public Integer getParentId(){
        return parentId;
    }

    public boolean checkAndSetEntityError(HttpServletRequest request, HttpServletResponse response, AbstractEntityDAO<T> dao, ActionType action) throws IOException {
        Integer errorCode = getEntityErrorCode(request, dao, action);
        if(errorCode != null){
            response.sendError(errorCode);
            return true;
        }

        return false;
    }

    public Integer getEntityErrorCode(HttpServletRequest request, AbstractEntityDAO<T> dao, ActionType action){
        user = AuthHelper.GetAuthUser(CookieHelper.GetCookieValue(request, CookieAuthToken));

        if (user == null)
            return HttpServletResponse.SC_UNAUTHORIZED;

        if(action != ActionType.Create){
            String id = request.getParameter("id");
            if(id == null || id.equals(""))
                return HttpServletResponse.SC_NOT_FOUND;

            entity = dao.get(Integer.parseInt(id));
            if (entity == null)
                return HttpServletResponse.SC_NOT_FOUND;

            if(!entity.checkRights(user, action))
                return HttpServletResponse.SC_FORBIDDEN;
        }
        else{
            String parentIdStr = request.getParameter("parentId");
            if(parentIdStr != null && !parentIdStr.equals(""))
                parentId = Integer.parseInt(parentIdStr);
            if(parentId != null && !dao.getParent(parentId).checkRights(user, action))
                return HttpServletResponse.SC_FORBIDDEN;
        }

        return null;
    }

    public boolean checkAndSetCollectionError(HttpServletRequest request, HttpServletResponse response) throws IOException{
        user = AuthHelper.GetAuthUser(CookieHelper.GetCookieValue(request, CookieAuthToken));

        if (user == null){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return true;
        }

        return false;
    }
}
