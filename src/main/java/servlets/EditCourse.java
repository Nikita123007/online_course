package servlets;

import com.google.gson.JsonObject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import common.ActionType;
import constants.Pages;
import dao.CourseDAO;
import dao.DAOFactory;
import hibernate.CourseEntity;
import response.ResponseData;
import servlets.Utils.ServletHelper;
import servlets.core.AbstractEditServlet;


@WebServlet("/EditCourse")
public class EditCourse extends AbstractEditServlet<CourseEntity, CourseDAO> {

    protected CourseDAO getDao(){
        return DAOFactory.getInstance().getCourseDAO();
    }

    @Override
    protected CourseEntity createEntity(ServletHelper<CourseEntity> helper){
        CourseEntity result = new CourseEntity();
        return result;
    }

    @Override
    protected void parseEntity(ServletHelper<CourseEntity> helper, CourseEntity entity, JsonObject json){
        entity.setName(json.get("name").getAsString());
        entity.setDescription(json.get("description").getAsString());
        entity.setPrice(json.get("price").getAsBigDecimal());
        entity.setLevel(json.get("level").getAsString());

        if(helper.getAction() == ActionType.Create)
            entity.setAuthor(helper.getUser().getIdUser());

        if(entity.getCategory() == 0)
            entity.setCategory(1);
    }

    @Override
    protected String getNextUrl(ServletHelper<CourseEntity> helper, CourseEntity entity){
        return Pages.Page.Courses;
    }

    @Override
    protected String getErrorString(ServletHelper<CourseEntity> helper, CourseEntity entity){
        String errorString = "";
        if (entity.getName().length() == 0){
            errorString = errorString + "Input course name.\n";
        }
        if (entity.getDescription().length() == 0){
            errorString = errorString + "Input course description.\n";
        }
        return errorString;
    }

    @Override
    protected String getJspName(){
        return "EditCourse.jsp";
    }
}
