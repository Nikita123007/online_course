package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import common.ActionType;
import constants.Pages;
import dao.DAOFactory;
import dao.TestDAO;
import hibernate.AnswerEntity;
import hibernate.CompletedTestEntity;
import hibernate.QuestionEntity;
import hibernate.TestEntity;
import response.ResponseData;
import servlets.Utils.ServletHelper;
import servlets.core.AbstractViewServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/TestRun")
public class TestRun extends AbstractViewServlet<TestEntity, TestDAO> {

    @Override
    protected TestDAO getDao(){
        return DAOFactory.getInstance().getTestDAO();
    }

    @Override
    protected String getJspName(){
        return "TestRun.jsp";
    }

    @Override
    protected boolean isCollection(){
        return false;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter wr = response.getWriter();

        ServletHelper<TestEntity> helper = new ServletHelper<>();
        helper.setAction(ActionType.Read);
        helper.setRequest(request);
        helper.setResponse(response);
        helper.setDao(getDao());
        helper.setCollection(isCollection());

        if(!helper.checkAndSetError()){
            JsonObject json = new Gson().fromJson(request.getReader(), JsonElement.class).getAsJsonObject();
            List<String> answersClient = getArrayString(json.get("answersCorrect").getAsJsonArray());
            int correct_answers = 0;

            for (QuestionEntity question: helper.getEntity().getQuestions()) {
                for (AnswerEntity answer: question.getAnswers()) {
                    if (answer.getIsCorrect() == 1){
                        if (answersClient.indexOf(String.valueOf(answer.getId())) < 0){
                            correct_answers--;
                            break;
                        }
                    }else {
                        if (answersClient.indexOf(String.valueOf(answer.getId())) >= 0){
                            correct_answers--;
                            break;
                        }
                    }
                }
                correct_answers++;
            }

            CompletedTestEntity entity = new CompletedTestEntity();
            entity.setCorrectAnswers(correct_answers);
            entity.setDate(new Timestamp((new Date()).getTime() - 1000000));
            entity.setUser(helper.getUser());
            entity.setTest(helper.getEntity());
            DAOFactory.getInstance().getCompletedTestDAO().addCompletedTest(entity);

            request.setAttribute("user", helper.getUser());
            ResponseData responseData = new ResponseData("", Pages.Page.Tests + "?parentId=" + helper.getEntity().getCourse());
            wr.write(responseData.toJson());
            wr.close();
        }
    }

    private List<String> getArrayString(JsonArray answersJson){
        List<String> answers = new ArrayList<>();

        for (JsonElement element: answersJson) {
            answers.add(element.getAsString());
        }

        return answers;
    }
}
