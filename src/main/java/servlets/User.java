package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/User")
public class User extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException
    {
        TestUser user = new TestUser("Nikita", "Zabeyda", 20);

        request.setAttribute("user", user);
        request.getRequestDispatcher("User.jsp").forward(request, response);
    }

    public class TestUser {
        private String firstName;
        private String secondName;
        private Integer age;

        public TestUser(String firstName, String secondName, Integer age){
            this.firstName = firstName;
            this.secondName = secondName;
            this.age = age;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getSecondName() {
            return secondName;
        }

        public void setSecondName(String secondName) {
            this.secondName = secondName;
        }

        public Integer getAge(){
            return age;
        }

        public void setAge(Integer age){
            this.age = age;
        }
    }
}
