package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import response.ResponseData;

@WebServlet("/EditCourse")
public class EditCource extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Writer wr = response.getWriter();
        String courceName = request.getParameter("courceName");
        String courceDescription = request.getParameter("courceDescription");
        ResponseData responseData = new ResponseData("Cource name: " + courceName + "</br>Cource description: " + courceDescription, null, new ArrayList<String>());
        responseData.getNameErrors().add("courseName");
        wr.write(responseData.ToJson());
        wr.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TestLection> lections = new ArrayList<TestLection>();
        lections.add(new TestLection("Lection1", "Description1", 2));
        lections.add(new TestLection("Lection2", "Description2", 3));
        lections.add(new TestLection("Lection3", "Description3", 4));
        List<TestTest> tests = new ArrayList<TestTest>();
        tests.add(new TestTest("Test1", "Access", 1));
        tests.add(new TestTest("Test2", "Completed", 2));
        tests.add(new TestTest("Test3", "Access", 3));
        TestCource cource = new TestCource(lections, tests, "NameCource1", "DescriptionCource1");

        request.setAttribute("cource", cource);
        request.getRequestDispatcher("EditCourse.jsp").forward(request, response);
    }

    public class TestCource{
        private List<TestLection> lections;
        private List<TestTest> tests;
        private String name;
        private String description;

        public TestCource(List<TestLection> lections, List<TestTest> tests, String name, String description){
            this.lections = lections;
            this.tests = tests;
            this.name = name;
            this.description = description;
        }

        public List<TestLection> getLections() {
            return lections;
        }

        public void setLections(List<TestLection> lections) {
            this.lections = lections;
        }

        public List<TestTest> getTests() {
            return tests;
        }

        public void setTests(List<TestTest> test) {
            this.tests = test;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public class TestLection {
        private String name;
        private String description;
        private Integer hours;

        public TestLection(String name, String description, Integer hours){
            this.name = name;
            this.description = description;
            this.hours = hours;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getHours(){
            return hours;
        }

        public void setHours(Integer hours){ this.hours = hours; }
    }

    public class TestTest {
        private String name;
        private String status;
        private Integer mark;

        public TestTest(String name, String status, Integer mark){
            this.name = name;
            this.status = status;
            this.mark = mark;
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

        public Integer getMark() {
            return mark;
        }

        public void setMark(Integer mark) {
            this.mark = mark;
        }
    }
}
