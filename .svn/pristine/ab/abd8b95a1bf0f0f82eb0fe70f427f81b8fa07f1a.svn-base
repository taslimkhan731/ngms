package com.stl.ngms.utils;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AESEncryptionUtil {
    private static final String ALGORITHM = "AES";
    private static final String KEY = "techl@b123456789";

    public static String encrypt(String plaintext) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
    /*public static void main(String args[]) throws Exception {
    	System.out.println("user name  "+encrypt("postgres"));
    	System.out.println("password  "+encrypt("postgres"));
    	System.out.println("user name "+decrypt("QZSiyuPz2M2Q0SU+uaZN9w=="));
    	System.out.println("password "+decrypt("QZSiyuPz2M2Q0SU+uaZN9w=="));
    }*/
}
