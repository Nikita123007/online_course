package servlets;

import Documents.DocumentService;
import Documents.CourseDocumentService;
import hibernate.UserEntity;
import org.apache.commons.collections4.map.HashedMap;
import request.AuthHelper;
import request.CookieHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static constants.Constants.Constant.CookieAuthToken;

@WebServlet("/Document")
public class DocumentServlet extends HttpServlet{

    private Map<String, DocumentService> documentServiceMap = new HashedMap<>();

    public DocumentServlet(){
        documentServiceMap.put("Course", new CourseDocumentService());
    }

    public Integer getErrorCode(HttpServletRequest request){
        UserEntity user = AuthHelper.GetAuthUser(CookieHelper.GetCookieValue(request, CookieAuthToken));

        if (user == null || !user.admin())
            return HttpServletResponse.SC_FORBIDDEN;

        String nameStr = request.getParameter("name");
        if (nameStr == null || nameStr.isEmpty())
            return HttpServletResponse.SC_BAD_REQUEST;

        if(!documentServiceMap.containsKey(nameStr))
            return HttpServletResponse.SC_NOT_FOUND;

        String type = request.getParameter("type");
        if(type == null || (!type.equals("pdf") && !type.equals("csv") && !type.equals("excel"))){
            return HttpServletResponse.SC_BAD_REQUEST;
        }

        String id = request.getParameter("id");
        if(id == null){
            return HttpServletResponse.SC_BAD_REQUEST;
        }

        return null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer errorCode = getErrorCode(request);
        if(errorCode != null){
            response.sendError(errorCode);
            return;
        }

        DocumentService service = documentServiceMap.get(request.getParameter("name"));
        String type = request.getParameter("type");
        int id = Integer.parseInt(request.getParameter("id"));
        if(type.equals("pdf")){
            response.addHeader("Content-Type", "application/pdf");
            response.addHeader("Content-Disposition", "inline; filename=" + service.getTitle() + ".pdf");
            response.getOutputStream().write(service.generatePdf(id).toByteArray());
            return;
        }
        if(type.equals("csv")){
            response.addHeader("Content-Type", "application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "inline; filename=" + service.getTitle() + ".csv");
            response.getOutputStream().write(service.generateCSV(id).toByteArray());
            return;
        }
        if(type.equals("excel")){
            response.addHeader("Content-Type", "application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "inline; filename=" + service.getTitle() + ".xlsx");
            response.getOutputStream().write(service.generateExcel(id).toByteArray());
            //return;
        }
    }
}
