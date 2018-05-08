package servlets;

import constants.Roles;
import dao.DAOFactory;
import hibernate.LectionEntity;
import hibernate.TestEntity;
import hibernate.UserEntity;
import request.AuthHelper;
import response.ResponseData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/TestEdit")
public class TestEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*List<Question> questions = new ArrayList<Question>();
        List<Answer> answers1 = new ArrayList<Answer>();
        answers1.add(new Answer("Answer1", false));
        answers1.add(new Answer("Answer2", false));
        answers1.add(new Answer("Answer3", false));
        List<Answer> answers2 = new ArrayList<Answer>();
        answers2.add(new Answer("Answer4", false));
        answers2.add(new Answer("Answer5", false));
        answers2.add(new Answer("Answer6", false));
        questions.add(new Question("Qustion1", answers1));
        questions.add(new Question("Qustion2", answers2));
        questions.add(new Question("Qustion2", answers2));
        questions.add(new Question("Qustion2", answers2));
        questions.add(new Question("Qustion2", answers2));
        questions.add(new Question("Qustion2", answers2));
        questions.add(new Question("Qustion2", answers2));
        TestTest test = new TestTest("NameTest1", "Access", -1, questions);

        request.setAttribute("test", test);
        request.getRequestDispatcher("TestEdit.jsp").forward(request, response);*/
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Writer wr = response.getWriter();
        UserEntity user = AuthHelper.GetAuthUser(request);
        String id = request.getParameter("id");

        if (user != null && id != null && !id.equals("")){
            TestEntity test = DAOFactory.getInstance().getTestDAO().getTest(Integer.parseInt(id));
            if (test != null && (user.getRole() == Roles.Role.Admin || test.getCourseByCourse().getAuthor() == user.getIdUser())) {
                DAOFactory.getInstance().getTestDAO().removeTest(test);
                ResponseData responseData = new ResponseData("", null, null);
                wr.write(responseData.ToJson());
                wr.close();
                return;
            }
        }

        ResponseData responseData = new ResponseData("Error.", null, null);
        wr.write(responseData.ToJson());
        wr.close();
    }
}
