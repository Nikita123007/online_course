package servlets;

import com.google.gson.JsonObject;
import common.ActionType;
import constants.Pages;
import dao.DAOFactory;
import dao.LectionDAO;
import hibernate.CourseEntity;
import hibernate.LectionEntity;
import response.ResponseData;
import servlets.Utils.ServletHelper;
import servlets.core.AbstractEditServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/LectionEdit")
public class LectionEdit extends AbstractEditServlet<LectionEntity, LectionDAO> {

    protected LectionDAO getDao(){
        return DAOFactory.getInstance().getLectionDAO();
    }

    @Override
    protected LectionEntity createEntity(ServletHelper<LectionEntity> helper){
        LectionEntity result = new LectionEntity();
        result.setCourse((CourseEntity) getDao().getParent(helper.getParentId()));
        return result;
    }

    @Override
    protected void parseEntity(ServletHelper<LectionEntity> helper, LectionEntity entity, JsonObject json){
        entity.setName(json.get("name").getAsString());
        entity.setText(json.get("text").getAsString());
    }

    @Override
    protected String getNextUrl(ServletHelper<LectionEntity> helper, LectionEntity entity){
        return Pages.Page.EditCourse + "?id=" + entity.getCourse().getId();
    }

    @Override
    protected String getErrorString(ServletHelper<LectionEntity> helper, LectionEntity entity){
        String errorString = "";

        if (entity.getName().length() == 0){
            errorString = errorString + "Input lection name.\n";
        }

        if (entity.getText().length() == 0){
            errorString = errorString + "Input lection text.\n";
        }
        return errorString;
    }

    @Override
    protected void setUpdateAttributes(ServletHelper<LectionEntity> helper){
        helper.getRequest().setAttribute("add", false);
    }

    @Override
    protected void setAddAttributes(ServletHelper<LectionEntity> helper){
        helper.getRequest().setAttribute("add", true);
        helper.getRequest().setAttribute("parentId", helper.getRequest().getParameter("parentId"));
    }

    @Override
    protected String getJspName(){
        return "LectionEdit.jsp";
    }
}
