package com.stl.ngms.orrisaone.util;

import com.stl.ngms.orrisaone.util.TpiAesUtil;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TpiAesUtil {

    private static final String CIPHER_NAME = "AES/CBC/PKCS5PADDING";
    private static final int CIPHER_KEY_LEN = 16; //128 bits
    private static final String iv = "fedcba9876543210"; //Same as in JAVA
    private static String key;

    private TpiAesUtil(String accessKey){
        key=accessKey;
    }

    public static TpiAesUtil getInstance(String accessKey) {
        TpiAesUtil tpiAesUtil = new TpiAesUtil(accessKey);
        return tpiAesUtil;
    }

    /**
     * Encrypt data using AES Cipher (CBC) with 128 bit key
     *
     *
     * @param data - data to encrypt
     * @return encryptedData data in base64 encoding with iv attached at end after a :
     */


    public String encrypt(String data) {
        try {
            if (key.length() < TpiAesUtil.CIPHER_KEY_LEN) {
                int numPad = TpiAesUtil.CIPHER_KEY_LEN - key.length();

                for(int i = 0; i < numPad; i++){
                    key += "0"; //0 pad to len 16 bytes
                }

            } else if (key.length() > TpiAesUtil.CIPHER_KEY_LEN) {
                key = key.substring(0, CIPHER_KEY_LEN); //truncate to 16 bytes
            }


            IvParameterSpec initVector = new IvParameterSpec(iv.getBytes(StandardCharsets.ISO_8859_1));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.ISO_8859_1), "AES");

            Cipher cipher = Cipher.getInstance(TpiAesUtil.CIPHER_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, initVector);

            byte[] encryptedData = cipher.doFinal((data.getBytes()));

            String base64_EncryptedData = Base64.getEncoder().encodeToString(encryptedData);
            String base64_IV = Base64.getEncoder().encodeToString(iv.getBytes(StandardCharsets.ISO_8859_1));

            return base64_EncryptedData + ":" + base64_IV;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * Decrypt data using AES Cipher (CBC) with 128 bit key
     *
     * @param data - encrypted data with iv at the end separate by :
     * @return decrypted data string
     */

    public String decrypt(String data) {
        try {
            if (key.length() < TpiAesUtil.CIPHER_KEY_LEN) {
                int numPad = TpiAesUtil.CIPHER_KEY_LEN - key.length();

                for(int i = 0; i < numPad; i++){
                    key += "0"; //0 pad to len 16 bytes
                }

            } else if (key.length() > TpiAesUtil.CIPHER_KEY_LEN) {
                key = key.substring(0, CIPHER_KEY_LEN); //truncate to 16 bytes
            }

            String[] parts = data.split(":");

            IvParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(parts[1]));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.ISO_8859_1), "AES");

            Cipher cipher = Cipher.getInstance(TpiAesUtil.CIPHER_NAME);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] decodedEncryptedData = Base64.getDecoder().decode(parts[0]);

            byte[] original = cipher.doFinal(decodedEncryptedData);

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}