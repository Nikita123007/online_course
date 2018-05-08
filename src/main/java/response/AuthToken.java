package response;

import java.security.SecureRandom;

public class AuthToken {
    private final static int tokenLenght = 20;

    public static String GetToken(){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[tokenLenght];
        random.nextBytes(bytes);
        return bytes.toString();
    }
}
