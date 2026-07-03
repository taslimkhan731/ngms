package com.stl.ngms.config;

import java.io.IOException;
import java.security.Key;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotAuthorizedException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

//import com.stl.rcs.config.JsonToUrlEncodedAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * Filter to check the token availability & validity in requests
 * 
 * @author Ranavir Dash
 * @since 31/01/2019
 */
public class JWTTokenNeededFilter implements Filter   {

	private static final Logger logger = Logger.getLogger(JWTTokenNeededFilter.class);

	@Autowired
    private KeyGenerator keyGenerator;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//dummy
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		if(keyGenerator == null) {
			logger.info("Injecting Autoware component KeyGenerator... ");
			ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            keyGenerator = webApplicationContext.getBean(KeyGenerator.class);
		}
		
		// Get the HTTP Authorization header from the request
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        logger.info("#### authorizationHeader : " + authorizationHeader);

        // Check if the HTTP Authorization header is present and formatted correctly
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            logger.error("#### invalid authorizationHeader : " + authorizationHeader);
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();
        boolean authStatus = false;
        try {

            // Validate the token
            Key key = keyGenerator.generateKey();
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            request.setAttribute("claims", claims);
            logger.info("#### valid token : " + token);
            authStatus = true;
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error("#### invalid token : " + token);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
		
        if(authStatus) {
        	response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
            response.addHeader("Access-Control-Expose-Headers", "xsrf-token,Authorization");
        	filterChain.doFilter(request, response);
        }
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}