package servlets;

import dao.DAOFactory;
import dao.exception.DAOException;
import domain.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Cources")
public class Cources extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TestModel> listCources = new ArrayList<TestModel>();
        listCources.add(new TestModel(3, "Name1", "Zabeyda", 20, "decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription "));
        listCources.add(new TestModel(4, "Имя2", "Автор2", 22, "decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription "));

        List<TestModel> listSelfCources = new ArrayList<TestModel>();
        listSelfCources.add(new TestModel(1, "Name2", "Zabeyda123", 202, "decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription "));
        listSelfCources.add(new TestModel(2, "Имя3", "Автор233", 222, "decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription decription "));
        
        request.setAttribute("cources", listCources);
        request.setAttribute("courcesSelf", listSelfCources);
        request.getRequestDispatcher("Cources.jsp").forward(request, response);
    }

    public class TestModel{
        private Integer id;
        private String name;
        private String author;
        private Integer price;
        private String description;

        public TestModel(Integer id, String name, String author, Integer price, String description){
            this.id = id;
            this.name = name;
            this.author = author;
            this.price = price;
            this.description = description;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
}
