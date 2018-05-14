package servlets;

import com.google.gson.JsonObject;
import constants.Pages;
import dao.DAOFactory;
import dao.LectionDAO;
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
        result.setCourse(helper.getParentId());
        return result;
    }

    @Override
    protected void parseEntity(LectionEntity entity, JsonObject json){
        entity.setName(json.get("name").getAsString());
        entity.setText(json.get("text").getAsString());
    }

    @Override
    protected ResponseData getResponseData(LectionEntity entity){
        String errorString = "";
        List<String> nameErrors = new ArrayList<>();

        if (entity.getName().length() == 0){
            errorString = errorString + "Input lection name.\n";
            nameErrors.add("name");
        }

        if (entity.getText().length() == 0){
            errorString = errorString + "Input lection text.\n";
            nameErrors.add("text");
        }
        if(nameErrors.isEmpty()){
            return new ResponseData("", Pages.Page.Courses, null);
        }
        else{
            return new ResponseData("Invalid data.\n" + errorString, Pages.Page.Courses, nameErrors);
        }
    }

    @Override
    protected void setUpdateAttributes(ServletHelper<LectionEntity> helper){
        helper.getRequest().setAttribute("userName", helper.getUser().getName());
        helper.getRequest().setAttribute("add", false);
    }

    @Override
    protected void setAddAttributes(ServletHelper<LectionEntity> helper){
        helper.getRequest().setAttribute("userName", helper.getUser().getName());
        helper.getRequest().setAttribute("add", true);
        helper.getRequest().setAttribute("parentId", helper.getRequest().getParameter("parentId"));
    }

    @Override
    protected String getJspName(){
        return "LectionEdit.jsp";
    }
}
