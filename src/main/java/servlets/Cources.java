package servlets;

import dao.DAOFactory;
import hibernate.CourseEntity;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.query.Query;

import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet("/Cources")
public class Cources extends HttpServlet {

    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Collection<CourseEntity> courses = DAOFactory.getInstance().getCourseDAO().getAll();

        request.setAttribute("cources", courses);
        request.setAttribute("courcesSelf", courses);
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
