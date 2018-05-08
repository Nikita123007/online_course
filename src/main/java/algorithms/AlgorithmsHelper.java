package algorithms;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import constants.algorithms.Algorithm;

public class AlgorithmsHelper {
    private static String algorithmNameDefault = Algorithm.MD5;

    public static byte[] GetHash(String value){
        try {
            byte[] b = value.getBytes();
            return MessageDigest.getInstance(algorithmNameDefault).digest(b);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static byte[] GetHash(String value, String algorithmName){
        try {
            byte[] b = value.getBytes();
            return MessageDigest.getInstance(algorithmName).digest(b);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
