package Servlets;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Cources")
public class Cources extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TestModel> listUsers = new ArrayList<TestModel>();
        listUsers.add(new TestModel("Name1", "Zabeyda", 20));
        listUsers.add(new TestModel("Имя2", "Автор2", 22));

        request.setAttribute("cources", listUsers);
        request.getRequestDispatcher("Cources.jsp").forward(request, response);
    }

    public class TestModel{
        private String name;
        private String author;
        private Integer price;

        public TestModel(String name, String author, Integer price){
            this.name = name;
            this.author = author;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public Integer getPrice(){
            return price;
        }

        public void setPrice(Integer price){
            this.price = price;
        }
    }
}
