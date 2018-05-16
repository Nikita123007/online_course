package servlets;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import common.ActionType;
import constants.Pages;
import dao.DAOFactory;
import dao.TestDAO;
import dao.TestQuestionDAO;
import hibernate.*;
import response.ResponseData;
import servlets.Utils.ServletHelper;
import servlets.core.AbstractEditServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/QuestionEdit")
public class QuestionEdit extends AbstractEditServlet<TestQuestionEntity, TestQuestionDAO> {

    protected TestQuestionDAO getDao(){
        return DAOFactory.getInstance().getTestQuestionDAO();
    }

    @Override
    protected TestQuestionEntity createEntity(ServletHelper<TestQuestionEntity> helper){
        TestQuestionEntity result = new TestQuestionEntity();
        result.setTest(helper.getParentId());
        return result;
    }

    @Override
    protected void parseEntity(ServletHelper<TestQuestionEntity> helper, TestQuestionEntity entity, JsonObject json){
        entity.setQuestion(json.get("name").getAsString());
    }

    @Override
    protected String getNextUrl(ServletHelper<TestQuestionEntity> helper, TestQuestionEntity entity){
        return Pages.Page.TestEdit + "?id=" + entity.getTest();
    }

    @Override
    protected String getErrorString(ServletHelper<TestQuestionEntity> helper, TestQuestionEntity entity){
        if (entity.getQuestion().length() == 0)
            return  "Input test name.\n";
        else
            return "";
    }

    @Override
    protected String getJspName(){
        return "QuestionEdit.jsp";
    }
}
