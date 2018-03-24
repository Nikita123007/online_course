package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/Diploma")
public class Diploma extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TestDiploma diploma = new TestDiploma("UserName1", "CourceName1", 10, "01.01.2018");

        request.setAttribute("diploma", diploma);
        request.getRequestDispatcher("Diploma.jsp").forward(request, response);
    }

    public class TestDiploma{
        private String userName;
        private String courceName;
        private Integer mark;
        private String date;

        public TestDiploma(String userName, String courceName, Integer mark, String date){
            this.userName = userName;
            this.courceName = courceName;
            this.mark = mark;
            this.date = date;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCourceName() {
            return courceName;
        }

        public void setCourceName(String courceName) {
            this.courceName = courceName;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getMark() {
            return mark;
        }

        public void setMark(Integer mark) {
            this.mark = mark;
        }
    }
}
