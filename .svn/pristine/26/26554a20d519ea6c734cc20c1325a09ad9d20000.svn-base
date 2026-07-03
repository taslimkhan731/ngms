package com.stl.ngms.jwt;

import java.io.IOException;
import java.security.Key;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotAuthorizedException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.stl.ngms.common.AuthorizationInterceptor;
import com.stl.ngms.config.KeyGenerator;
import com.stl.ngms.setting.model.ResourceVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

public class MobileFilter extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger.getLogger(AuthorizationInterceptor.class);
	@Autowired
	KeyGenerator keyGenerator;

	@Override
	@CrossOrigin
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request.getServletPath().split("/")[1].equals("mobile")) {
			Map<String, String> map = new HashMap<String, String>();

			Enumeration headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String key = (String) headerNames.nextElement();
				String value = request.getHeader(key);
				map.put(key, value);
			}

			System.out.println(map.toString());
			System.out.println("cors filter origin class");
			response.setHeader("Access-Control-Allow-Origin", "*"); // Origin http://localhost:4200
//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//        response.setHeader("Access-Control-Allow-Origin", "http://192.168.0.70:3001");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, OPTIONS");// "GET, POST, PUT, DELETE"
			response.setHeader("Access-Control-Max-Age", "1800");
			// response.setHeader("Access-Control-Allow-Headers", "authorization,
			// content-type,captchaKey, xsrf-token, rgid, caseno,Set-Cookie,Origin");
			response.setHeader("Access-Control-Allow-Headers",
					"proxyId,X-Requested-With,captchaKey,content-type, Authorization,caseno,Set-Cookie,Origin");
			response.addHeader("Access-Control-Expose-Headers",
					"xsrf-token,captchaKey,Authorization,proxyId,caseno,Set-Cookie,Origin");
			if ("OPTIONS".equals(request.getMethod())) {
				response.setStatus(HttpServletResponse.SC_OK);
			} else if ("TRAC".equals(request.getMethod())) {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			} else {
				// filterChain.doFilter(request, response);
				return false;
			}
			return true;
		} else {
			return true;
		}
	}// end preHandle

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
	}
}
