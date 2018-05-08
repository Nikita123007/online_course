package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/TestStatus")
public class TestStatus extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TestTest test = new TestTest("NameTest1", "Completed", 10, null);

        //request.setAttribute("test", test);
        request.getRequestDispatcher("TestStatus.jsp").forward(request, response);
    }/*

    public class TestTest {
        private String name;
        private String status;
        private Integer mark;
        private List<Cource.Question> questions;

        public TestTest(String name, String status, Integer mark, List<Cource.Question> questions){
            this.name = name;
            this.status = status;
            this.mark = mark;
            this.questions = questions;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<Cource.Question> getQuestions() {
            return questions;
        }

        public void setQuestions(List<Cource.Question> questions) {
            this.questions = questions;
        }

        public Integer getMark() {
            return mark;
        }

        public void setMark(Integer mark) {
            this.mark = mark;
        }
    }

    public class Answer{
        private String description;
        private Boolean isTrue;

        public Answer(String description, Boolean isTrue){
            this.description = description;
            this.isTrue = isTrue;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Boolean getIsTrue() {
            return isTrue;
        }

        public void setIsTrue(Boolean isTrue) {
            this.isTrue = isTrue;
        }
    }

    public class Question{
        private String name;
        private List<Cource.Answer> answers;

        public Question(String name, List<Cource.Answer> answers){
            this.name = name;
            this.answers = answers;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Cource.Answer> getAnswers() {
            return answers;
        }

        public void setAnswers(List<Cource.Answer> answers) {
            this.answers = answers;
        }
    }*/
}
