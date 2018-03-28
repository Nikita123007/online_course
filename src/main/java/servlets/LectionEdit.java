package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LectionEdit")
public class LectionEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TestLection lection = new TestLection("NameLection", "This lection is very cool", "lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection lection ", 10);

        request.setAttribute("lection", lection);
        request.getRequestDispatcher("LectionEdit.jsp").forward(request, response);
    }

    public class TestLection {
        private String name;
        private String description;
        private String text;
        private Integer hours;

        public TestLection(String name, String description, String text, Integer hours){
            this.name = name;
            this.description = description;
            this.text = text;
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

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}