package servlets;

import com.google.gson.JsonObject;
import common.ActionType;
import constants.Pages;
import dao.DAOFactory;
import dao.QuestionDAO;
import hibernate.*;
import response.ResponseData;
import servlets.Utils.ServletHelper;
import servlets.core.AbstractEditServlet;
import javax.servlet.annotation.WebServlet;

@WebServlet("/QuestionEdit")
public class QuestionEdit extends AbstractEditServlet<QuestionEntity, QuestionDAO> {

    protected QuestionDAO getDao(){
        return DAOFactory.getInstance().getQuestionDAO();
    }

    @Override
    protected QuestionEntity createEntity(ServletHelper<QuestionEntity> helper){
        QuestionEntity result = new QuestionEntity();
        result.setTest(DAOFactory.getInstance().getTestDAO().get(helper.getParentId()));
        return result;
    }

    @Override
    protected void parseEntity(ServletHelper<QuestionEntity> helper, QuestionEntity entity, JsonObject json){
        entity.setQuestion(json.get("name").getAsString());
    }

    @Override
    protected String getNextUrl(ServletHelper<QuestionEntity> helper, QuestionEntity entity){
        return Pages.Page.TestEdit + "?id=" + entity.getTest().getId();
    }

    @Override
    protected String getErrorString(ServletHelper<QuestionEntity> helper, QuestionEntity entity){
        if (entity.getQuestion().length() == 0)
            return  "Input test name.\n";
        else
            return "";
    }

    @Override
    protected void setUpdateAttributes(ServletHelper<QuestionEntity> helper){
        helper.getRequest().setAttribute("add", false);
    }

    @Override
    protected void setAddAttributes(ServletHelper<QuestionEntity> helper){
        helper.getRequest().setAttribute("add", true);
        helper.getRequest().setAttribute("parentId", helper.getRequest().getParameter("parentId"));
    }

    @Override
    protected String getJspName(){
        return "QuestionEdit.jsp";
    }

    @Override
    protected void BeforeAction(ServletHelper<QuestionEntity> helper, QuestionEntity entity, ResponseData responseData){
        if (helper.getAction() == ActionType.Create) {
            AnswerEntity answer = new AnswerEntity();
            answer.setText("New answer");
            answer.setIsCorrect((byte) 0);
            answer.setQuestion(entity);
            entity.getAnswers().add(answer);
        }

        /*if (helper.getAction() == ActionType.Delete){
            if (entity.getTest().getQuestions().size() == 1){
                responseData.setError("This is the last question.");
            }
        }*/
    }
}
