package servlets;

import com.google.gson.JsonObject;
import common.ActionType;
import constants.Pages;
import dao.DAOFactory;
import dao.TestAnswerDAO;
import dao.TestQuestionDAO;
import hibernate.TestAnswerEntity;
import hibernate.TestQuestionEntity;
import response.ResponseData;
import servlets.Utils.ServletHelper;
import servlets.core.AbstractEditServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet("/AnswerEdit")
public class AnswerEdit extends AbstractEditServlet<TestAnswerEntity, TestAnswerDAO> {

    protected TestAnswerDAO getDao(){
        return DAOFactory.getInstance().getTestAnswerDAO();
    }

    @Override
    protected TestAnswerEntity createEntity(ServletHelper<TestAnswerEntity> helper){
        TestAnswerEntity result = new TestAnswerEntity();
        result.setTestQuestion(helper.getParentId());
        return result;
    }

    @Override
    protected void parseEntity(ServletHelper<TestAnswerEntity> helper, TestAnswerEntity entity, JsonObject json){
        entity.setText(json.get("name").getAsString());
        entity.setIsCorrect(json.get("correct").getAsString().equals("true") ? (byte) 1 : (byte)0);
    }

    @Override
    protected ResponseData getResponseData(ServletHelper<TestAnswerEntity> helper, TestAnswerEntity entity){
        if (helper.getAction() == ActionType.Delete){
            return new ResponseData("", Pages.Page.QuestionEdit + "?id=" + entity.getTestQuestion());
        }

        String errorString = "";

        if (entity.getText().length() == 0){
            errorString = errorString + "Input test name.\n";
        }

        if(errorString.isEmpty()){
            return new ResponseData("", Pages.Page.QuestionEdit + "?id=" + entity.getTestQuestion());
        }
        else{
            return new ResponseData("Invalid data.\n" + errorString, "");
        }
    }

    @Override
    protected String getJspName(){
        return "AnswerEdit.jsp";
    }
}
