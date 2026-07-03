package com.stl.ngms.common;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 
 * @author R. Dash
 * @since  14-09-2017
 * 
 */
public class CustomUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {
	private static final Logger logger = Logger.getLogger(CustomUsernamePasswordAuthenticationFilter.class);
	@Value("${message.captcha.invalid}")
	private String msgInvalidCaptcha;
	public static final String CAPTCHA_KEY = "STL_CAPTCHA";
	@Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
    throws IOException, ServletException {
		System.out.println("Entry,,,,,,,,,,11111111");
        super.successfulAuthentication(request, response, chain, authResult);
    }
     
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
    throws IOException, ServletException {
    	System.out.println("Entry,,,,,,,,,,22222222222");
        super.unsuccessfulAuthentication(request, response, failed);
    }
     
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//    	String remoteAddress = request.getRemoteAddr();
    	System.out.println("Entry,,,,,,,,,,");
    	HttpSession session = request.getSession(false);
    	
    	if(null == session || (null == session.getAttribute(CAPTCHA_KEY))){
    		throw new AuthenticationServiceException(msgInvalidCaptcha);//Invalid username or password
    	}
    	String reqCaptchaVal = request.getParameter("captcha");
    	String reqType = request.getParameter("reqType");
    	String sessCaptchaVal = (String) session.getAttribute(CAPTCHA_KEY);
    	session.removeAttribute(CAPTCHA_KEY);
    	if(reqType.equals("register")) {
	    	if(!StringUtils.equals(reqCaptchaVal, sessCaptchaVal)){
	    		throw new AuthenticationServiceException(msgInvalidCaptcha);
	    	}
    	}
      //Get response of recaptcha and Verify with VerifyRecaptcha (with site key and secret key)
//    	boolean isCaptchaValid = false;
    /*	String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
  		logger.debug("captcha :"+gRecaptchaResponse);
  		try {
			isCaptchaValid = captchaService.verify(gRecaptchaResponse);
			if(!isCaptchaValid){
	  			throw new AuthenticationServiceException(msgInvalidCaptcha);
	  		}
		} catch (IOException e) {
			e.printStackTrace();
			throw new AuthenticationServiceException(msgInvalidCaptcha);
		}*/
    	System.out.println("Exit CustomUsernamePasswordAuthenticationFilter111111111111");
        return super.attemptAuthentication(request, response);
    }
    @Override
    public String obtainUsername(HttpServletRequest request) {
    	HttpSession session = request.getSession(false);
    	
    	if(null == session || (null == session.getAttribute("saltKey"))){
    		throw new AuthenticationServiceException("Invalid Request.");//Invalid username or password
    	}
    	/*
    	 * Fetch the salt from Pre-authenticated session and use this salt(instead of salt in request) for
    	 * hashing the already hashed password saved in database & make it as salted hash
    	 * then compare with user provided salted hash
    	 */
    	JSONObject jObjUserName = new JSONObject();//put username with salt
    	try {
			jObjUserName.put("j_username", request.getParameter("j_username"));
			jObjUserName.put("saltKey", session.getAttribute("saltKey"));
			jObjUserName.put("reqType", request.getParameter("reqType"));
			System.out.println(request.getParameter("j_username"));
			System.out.println(session.getAttribute("saltKey"));
			System.out.println(request.getParameter("reqType"));
			session.removeAttribute("saltKey");
    	} catch (JSONException e) {
			e.printStackTrace();
			throw new AuthenticationServiceException("Invalid Request.");
		}
    	System.out.println("Exit CustomUsernamePasswordAuthenticationFilter2222222222222");
        return jObjUserName.toString();
    }
}
