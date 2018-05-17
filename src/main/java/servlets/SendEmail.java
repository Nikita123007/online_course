package servlets;

import Email.EmailEntity;
import Email.EmailSenderService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import constants.Pages;
import dao.DAOFactory;
import hibernate.UserEntity;
import request.AuthHelper;
import request.CookieHelper;
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

import static constants.Constants.Constant.CookieAuthToken;

@WebServlet("/SendEmail")
public class SendEmail extends HttpServlet {

    private Integer getErrorCode(HttpServletRequest request){
        UserEntity user = AuthHelper.GetAuthUser(CookieHelper.GetCookieValue(request, CookieAuthToken));

        if (user == null)
            return HttpServletResponse.SC_UNAUTHORIZED;

        String id = request.getParameter("id");
        if(id == null)
            return HttpServletResponse.SC_BAD_REQUEST;

        return null;
    }

    List<UserEntity> getUsers(String str){
        List<UserEntity> result = new ArrayList<>();
        String[] idStrings = str.split("_");
        for(String idStr : idStrings){
            result.add(DAOFactory.getInstance().getUserDAO().get(Integer.parseInt(idStr)));
        }
        return result;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Writer wr = response.getWriter();

        Integer errorCode = getErrorCode(request);
        if(errorCode != null){
            response.sendError(errorCode);
            return;
        }

        EmailEntity entity = new EmailEntity();

        entity.setRecipients(getUsers(request.getParameter("id")));

        request.setCharacterEncoding("UTF-8");
        JsonObject json = new Gson().fromJson(request.getReader(), JsonElement.class).getAsJsonObject();
        entity.setBody(json.get("text").getAsString());
        entity.setSubject(json.get("title").getAsString());

        EmailSenderService service = new EmailSenderService();
        service.sendEmail(entity);

        ResponseData responseData = new ResponseData("", Pages.Page.Users);
        wr.write(responseData.toJson());
        wr.close();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer errorCode = getErrorCode(request);
        if(errorCode != null){
            response.sendError(errorCode);
            return;
        }

        request.setAttribute("user", AuthHelper.GetAuthUser(CookieHelper.GetCookieValue(request, CookieAuthToken)));
        request.setAttribute("users", getUsers(request.getParameter("id")));
        request.setAttribute("idString", request.getParameter("id"));
        request.getRequestDispatcher("SendEmail.jsp").forward(request, response);
    }

}
