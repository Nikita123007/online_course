package request;

import constants.Pages;
import dao.DAOFactory;
import hibernate.UserEntity;
import response.ResponseData;

import javax.servlet.http.HttpServletRequest;

import static constants.Constants.Constant.CookieAuthToken;

public class AuthHelper {
    public static UserEntity GetAuthUser(String authToken){
        if (authToken != null && !authToken.equals("")) {
            return DAOFactory.getInstance().getUserDAO().findUserByAuthToken(authToken);
        }
        return null;
    }
}
