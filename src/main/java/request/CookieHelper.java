package request;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.*;

public class CookieHelper {
    public static String GetCookieValue(HttpServletRequest request, String cookieName){
        List<Cookie> cookies = Arrays.asList(request.getCookies());

        for (Cookie cookie: cookies) {
            if (cookie.getName().equals(cookieName)){
                return cookie.getValue();
            }
        }

        return null;
    }
}