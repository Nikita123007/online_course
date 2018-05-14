package servlets;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
    protected void parseEntity(TestEntity entity, JsonObject json){
        entity.setName(json.get("name").getAsString());
        JsonArray questions = json.get("questions").getAsJsonArray();
        for(JsonElement questionElement : questions){
            TestQuestionEntity questionEntity = new TestQuestionEntity();
            JsonObject questionObject = questionElement.getAsJsonObject();

            questionEntity.setQuestion(questionObject.get("question").getAsString());

            for(JsonElement answerElement : questionObject.getAsJsonArray()){
                TestAnswerEntity answerEntity = new TestAnswerEntity();
                JsonObject answerObject = answerElement.getAsJsonObject();

                questionEntity.setQuestion(answerObject.get("text").getAsString());

                questionEntity.getTestAnswersByIdTestQuestion().add(answerEntity);
            }

            entity.getTestQuestionsByIdTest().add(questionEntity);
        }
    }

    @Override
    protected ResponseData getResponseData(TestEntity entity){
        String errorString = "";
        List<String> nameErrors = new ArrayList<>();

        if (entity.getName().length() == 0){
            errorString = errorString + "Input test name.\n";
            nameErrors.add("name");
        }

        if (entity.getTestQuestionsByIdTest().size() == 0){
            errorString = errorString + "Add test question.\n";
            nameErrors.add("question");
        }

        for (TestQuestionEntity question : entity.getTestQuestionsByIdTest()) {
            if(question.getQuestion().isEmpty()){
                errorString = errorString + "Input question.\n";
                nameErrors.add("question name");
                break;
            }

            if(question.getTestAnswersByIdTestQuestion().isEmpty()){
                errorString = errorString + "Add answers.\n";
                nameErrors.add("question answers");
                break;
            }

            for(TestAnswerEntity answer : question.getTestAnswersByIdTestQuestion()){
                errorString = errorString + "Input answer.\n";
                nameErrors.add("answer");
                break;
            }
        }

        if(nameErrors.isEmpty()){
            return new ResponseData("", Pages.Page.Courses, null);
        }
        else{
            return new ResponseData("Invalid data.\n" + errorString, Pages.Page.Courses, nameErrors);
        }
    }

    @Override
    protected void setUpdateAttributes(ServletHelper<TestEntity> helper){
        helper.getRequest().setAttribute("userName", helper.getUser().getName());
    }

    @Override
    protected String getJspName(){
        return "TestEdit.jsp";
    }
}
