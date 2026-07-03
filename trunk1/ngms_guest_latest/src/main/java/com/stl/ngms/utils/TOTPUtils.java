package com.stl.ngms.utils;

import java.lang.reflect.UndeclaredThrowableException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class TOTPUtils {
	
	public TOTPUtils() {
		super();
		System.out.println("OTP Service initialized..");
	}
	private static final int SECRET_SIZE = 10;

	private static final int PASS_CODE_LENGTH = 6;

	private static final int INTERVAL = 1;//min 30 sec interval for each unique otp generation for a particular secret
	
	@Value("${otp.window.length}")
	private int window ;//= 60;//validates within this window(in sec)

	private static final String CRYPTO = "HmacSHA1";

	private static final Random rand = new Random();
	
	public String generateSecret() {

		// Allocating the buffer
		byte[] buffer = new byte[SECRET_SIZE];

		// Filling the buffer with random numbers.
		rand.nextBytes(buffer);

		// Getting the key and converting it to Base32
		Base32 codec = new Base32();
		byte[] secretKey = Arrays.copyOf(buffer, SECRET_SIZE);
		byte[] encodedKey = codec.encode(secretKey);
		return new String(encodedKey);
	}

	public boolean checkCode(String secret, long code)
			throws NoSuchAlgorithmException, InvalidKeyException {
		Base32 codec = new Base32();
		byte[] decodedKey = codec.decode(secret);
		
		// Window is used to check codes generated in the near past.
		// You can use this value to tune how far you're willing to go.
		int window = this.window;
		long currentInterval = getCurrentInterval();
		for (int i = -window; i <= window; ++i) {
			long hash = TotpGenerator.generateTOTP(decodedKey, currentInterval + i,
					PASS_CODE_LENGTH, CRYPTO);
			
			if (hash == code) {
				return true;
			}
		}

		// The validation code is invalid.
		return false;
	}

	private static long getCurrentInterval() {
		long currentTimeSeconds = System.currentTimeMillis() / 1000;
		return currentTimeSeconds / INTERVAL;
	}
	public String getOtp(String secret){
		if(null == secret || secret.isEmpty())
			return null;
		else
			return String.valueOf(TotpGenerator.generateTOTP(secret));
	}
	private static class TotpGenerator {
		private static final int[] DIGITS_POWER 
		 // 0 1 2 3 4 5 6 7 8 
		 = { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000 }; 
//		private static final int PASS_CODE_LENGTH = 6;
//		private static final int INTERVAL = 1;
//		private static final String CRYPTO = "HmacSHA1";
		 private TotpGenerator() { 
		 } 
		 
		 /**
		  * This method uses the JCE to provide the crypto algorithm. HMAC computes a 
		  * Hashed Message Authentication Code with the crypto hash algorithm as a 
		  * parameter. 
		  *  
		  * @param crypto 
		  *            : the crypto algorithm (HmacSHA1, HmacSHA256, HmacSHA512) 
		  * @param keyBytes 
		  *            : the bytes to use for the HMAC key 
		  * @param text 
		  *            : the message or text to be authenticated 
		  */ 
		 
		 private static byte[] hmacSha(String crypto, byte[] keyBytes, byte[] text) { 
		  try { 
		   Mac hmac; 
		   hmac = Mac.getInstance(crypto); 
		   SecretKeySpec macKey = new SecretKeySpec(keyBytes, "RAW"); 
		   hmac.init(macKey); 
		   return hmac.doFinal(text); 
		  } catch (GeneralSecurityException gse) { 
		   throw new UndeclaredThrowableException(gse); 
		  } 
		 } 
		 
		 /**
		  * This method generates a TOTP value for the given set of parameters. 
		  *  
		  * @param key 
		  *            : the shared secret 
		  * @param time 
		  *            : a value that reflects a time 
		  * @param digits 
		  *            : number of digits to return 
		  * @param crypto 
		  *            : the crypto function to use 
		  *  
		  * @return: digits 
		  */ 
		 
		 public static int generateTOTP(byte[] key, long time, int digits, String crypto) { 
//		  System.out.println("time:"+time);
		  byte[] msg = ByteBuffer.allocate(8).putLong(time).array(); 
		  byte[] hash = hmacSha(crypto, key, msg); 
		 
		  // put selected bytes into result int 
		  int offset = hash[hash.length - 1] & 0xf; 
		 
		  int binary = ((hash[offset] & 0x7f) << 24) | ((hash[offset + 1] & 0xff) << 16) | ((hash[offset + 2] & 0xff) << 8) | (hash[offset + 3] & 0xff); 
		 
		  int otp = binary % DIGITS_POWER[digits]; 
		 
		  return otp; 
		 } 
		 public static int generateTOTP(String secret) {
			return generateTOTP(new Base32().decode(secret), System.currentTimeMillis()/1000/INTERVAL, PASS_CODE_LENGTH, CRYPTO);
		}
	}

}
