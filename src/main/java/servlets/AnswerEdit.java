package servlets;

import com.google.gson.JsonObject;
import constants.Pages;
import dao.DAOFactory;
import dao.AnswerDAO;
import hibernate.AnswerEntity;
import hibernate.QuestionEntity;
import servlets.Utils.ServletHelper;
import servlets.core.AbstractEditServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet("/AnswerEdit")
public class AnswerEdit extends AbstractEditServlet<AnswerEntity, AnswerDAO> {

    protected AnswerDAO getDao(){
        return DAOFactory.getInstance().getAnswerDAO();
    }

    @Override
    protected AnswerEntity createEntity(ServletHelper<AnswerEntity> helper){
        AnswerEntity result = new AnswerEntity();
        result.setQuestion((QuestionEntity) getDao().getParent(helper.getParentId()));
        return result;
    }

    @Override
    protected void parseEntity(ServletHelper<AnswerEntity> helper, AnswerEntity entity, JsonObject json){
        entity.setText(json.get("name").getAsString());
        entity.setIsCorrect(json.get("correct").getAsString().equals("true") ? (byte) 1 : (byte)0);
    }

    @Override
    protected String getNextUrl(ServletHelper<AnswerEntity> helper, AnswerEntity entity){
        return Pages.Page.QuestionEdit + "?id=" + entity.getQuestion().getId();
    }

    @Override
    protected String getErrorString(ServletHelper<AnswerEntity> helper, AnswerEntity entity){
        if (entity.getText().length() == 0)
            return  "Input test name.\n";
        else
            return "";
    }

    @Override
    protected void setUpdateAttributes(ServletHelper<AnswerEntity> helper){
        helper.getRequest().setAttribute("add", false);
    }

    @Override
    protected void setAddAttributes(ServletHelper<AnswerEntity> helper){
        helper.getRequest().setAttribute("add", true);
        helper.getRequest().setAttribute("parentId", helper.getRequest().getParameter("parentId"));
    }

    @Override
    protected String getJspName(){
        return "AnswerEdit.jsp";
    }
}
