package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/TestEdit")
public class TestEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Question> questions = new ArrayList<Question>();
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
        request.getRequestDispatcher("TestEdit.jsp").forward(request, response);
    }

    public class TestTest {
        private String name;
        private String status;
        private Integer mark;
        private List<Question> questions;

        public TestTest(String name, String status, Integer mark, List<Question> questions){
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

        public List<Question> getQuestions() {
            return questions;
        }

        public void setQuestions(List<Question> questions) {
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
        private List<Answer> answers;

        public Question(String name, List<Answer> answers){
            this.name = name;
            this.answers = answers;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Answer> getAnswers() {
            return answers;
        }

        public void setAnswers(List<Answer> answers) {
            this.answers = answers;
        }
    }
}
