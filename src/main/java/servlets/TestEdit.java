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
        result.setCourse((CourseEntity) getDao().getParent(helper.getParentId()));
        return result;
    }

    @Override
    protected void parseEntity(ServletHelper<TestEntity> helper, TestEntity entity, JsonObject json){
        entity.setName(json.get("name").getAsString());
    }

    @Override
    protected String getNextUrl(ServletHelper<TestEntity> helper, TestEntity entity){
        return Pages.Page.EditCourse + "?id=" + entity.getCourse().getId();
    }

    @Override
    protected String getErrorString(ServletHelper<TestEntity> helper, TestEntity entity){
        String errorString = "";

        if (entity.getName().length() == 0){
            errorString = errorString + "Input test name.\n";
        }

        return errorString;
    }

    @Override
    protected String getJspName(){
        return "TestEdit.jsp";
    }

    @Override
    protected void BeforeAction(ServletHelper<TestEntity> helper, TestEntity entity, ResponseData responseData){
        if (helper.getAction() == ActionType.Create) {
            AnswerEntity answer = new AnswerEntity();
            answer.setText("New answer");
            answer.setIsCorrect((byte) 0);
            QuestionEntity question = new QuestionEntity();
            question.setTest(entity);
            question.setQuestion("New question");
            answer.setQuestion(question);
            question.getAnswers().add(answer);
            entity.getQuestions().add(question);
        }
    }
}
