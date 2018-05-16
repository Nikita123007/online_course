package servlets;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import common.ActionType;
import constants.Pages;
import dao.DAOFactory;
import dao.TestDAO;
import hibernate.*;
import response.ResponseData;
import servlets.Utils.ServletHelper;
import servlets.core.AbstractEditServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/TestEdit")
public class TestEdit extends AbstractEditServlet<TestEntity, TestDAO> {

    protected TestDAO getDao(){
        return DAOFactory.getInstance().getTestDAO();
    }

    @Override
    protected TestEntity createEntity(ServletHelper<TestEntity> helper){
        TestEntity result = new TestEntity();
        result.setCourse(helper.getParentId());
        return result;
    }

    @Override
    protected void parseEntity(ServletHelper<TestEntity> helper, TestEntity entity, JsonObject json){
        entity.setName(json.get("name").getAsString());
    }

    @Override
    protected String getNextUrl(ServletHelper<TestEntity> helper, TestEntity entity){
        return Pages.Page.EditCourse + "?id=" + entity.getCourse();
    }

    @Override
    protected String getErrorString(ServletHelper<TestEntity> helper, TestEntity entity){
        String errorString = "";

        if (entity.getName().length() == 0){
            errorString = errorString + "Input test name.\n";
        }

        /*if (entity.getTestQuestionsByIdTest().size() == 0){
            errorString = errorString + "Add test question.\n";
        }

        for (TestQuestionEntity question : entity.getTestQuestionsByIdTest()) {
            if(question.getQuestion().isEmpty()){
                errorString = errorString + "Input question.\n";
            }

            if(question.getTestAnswersByIdTestQuestion().isEmpty()){
                errorString = errorString + "Add answers.\n";
                break;
            }

            for(TestAnswerEntity answer : question.getTestAnswersByIdTestQuestion()){
                if(answer.getText().isEmpty()){
                    errorString = errorString + "Input answer.\n";
                    break;
                }
            }
        }*/
        return errorString;
    }

    @Override
    protected String getJspName(){
        return "TestEdit.jsp";
    }
}
