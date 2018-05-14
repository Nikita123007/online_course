package servlets;

import com.google.gson.JsonObject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
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
    protected void parseEntity(CourseEntity entity, JsonObject json){
        entity.setName(json.get("name").getAsString());
        entity.setDescription(json.get("description").getAsString());
    }

    @Override
    protected ResponseData getResponseData(CourseEntity entity){
        String errorString = "";
        List<String> nameErrors = new ArrayList<>();

        if (entity.getName().length() == 0){
            errorString = errorString + "Input course name.\n";
            nameErrors.add("name");
        }

        if (entity.getDescription().length() == 0){
            errorString = errorString + "Input course description.\n";
            nameErrors.add("description");
        }
        if(nameErrors.isEmpty()){
            return new ResponseData("", Pages.Page.Courses, null);
        }
        else{
            return new ResponseData("Invalid data.\n" + errorString, Pages.Page.Courses, nameErrors);
        }
    }

    @Override
    protected void setUpdateAttributes(ServletHelper<CourseEntity> helper){
        helper.getRequest().setAttribute("userName", helper.getUser().getName());
    }

    @Override
    protected String getJspName(){
        return "EditCourse.jsp";
    }
}
