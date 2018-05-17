package servlets.Utils;

public class Utils {
    public static Integer getInteger(String intStr) {
        try{
            return Integer.parseInt(intStr);
        }
        catch (Exception e){
            return null;
        }
    }
}
