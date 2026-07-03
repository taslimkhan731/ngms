package com.stl.ngms.common;

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
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.ws.rs.NotAuthorizedException;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stl.ngms.config.KeyGenerator;
import com.stl.ngms.jwt.JwtUtil;
import com.stl.ngms.setting.model.ResourceVO;
import com.stl.ngms.setting.model.UserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

/**
 * 
 * @author SK Patra
 * @since 15-05-2018
 *
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = Logger.getLogger(AuthorizationInterceptor.class);
	@Autowired
	KeyGenerator keyGenerator;
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Override
	@CrossOrigin
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("Entry ---> AuthorizationInterceptor");
		List<ResourceVO> availableResources = null;
		List<ResourceVO> allowedResources = null;
		boolean allowedflag = false;
		boolean availableflag = false;
		logger.warn("INTERCEPT URI: " + request.getRequestURI());
		String servPath = request.getServletPath();// login
		logger.info("Request Path  " + servPath);
		System.out.println("split path" + servPath.split("[\\s@&.?$+-]+")[0]);
		final List<String> allowedMethods = Arrays.asList(new String[] { "/mobileLogin.do", "/mobileRegistration.do",
				"/createUserForMobile.do", "/mobileresetPasswordRequest.do", "/validateForgotPasswordOtp.do",
				"/resendOTP.do", "/resendOTPForGuest.do", "/ws", "/getResults.do" });
		logger.info("Request Method@@##:  " + request.getMethod());
		if (!servPath.split("/")[1].equals("mobile")) {
			if (!allowedMethods.contains(servPath)) {
				logger.info("Request Method@@##:  inside false");
				if (request.getMethod().equals("OPTIONS") || request.getMethod().equals("TRACE")
						|| request.getMethod().equals("TRACK") || request.getMethod().equals("PUT")
						|| request.getMethod().equals("DELETE")) {
					logger.info("Methods Not allow");
					((HttpServletResponse) response).sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					return false;
				}
				if (request.getMethod().equalsIgnoreCase("POST") || request.getMethod().equalsIgnoreCase("GET")) {
					if (servPath.endsWith(".do") || servPath.endsWith(".html")) {

						String resource = servPath.substring(1, servPath.indexOf('.'));
						logger.info("resource--->" + resource);
						logger.info(request.getContextPath());
						availableResources = (List<ResourceVO>) request.getSession().getAttribute("availableResources");
						allowedResources = (List<ResourceVO>) request.getSession().getAttribute("allowedResources");
						ResourceVO resourceObj = new ResourceVO(resource);
						if (availableResources != null && availableResources.contains(resourceObj)) {// Check presence
																										// of resource
																										// in All
																										// Available
																										// Resources
							String resourceDescription = "";
							Cookie cookie[] = request.getCookies();
							Cookie cook = cookie[0];
							cook.setMaxAge(0); // Don't set to -1 or it will become a session cookie!
							response.addCookie(cook);
							logger.info("======================="
									+ request.getSession().getAttribute("currentSessionId").toString());
							String jSessionId = cook.getName().equalsIgnoreCase("JSESSIONID") ? cook.getValue() : "NA";
							logger.info("jSessionId====================" + jSessionId);
							if (!request.getSession().getAttribute("currentSessionId").toString()
									.equalsIgnoreCase(jSessionId)) {
								logger.info("Trying to access unauthorized resource...Redirecting11111...");
								response.sendRedirect(request.getContextPath() + "/" + "403.html");
								return false;// valid available resource but user not authorized
							}
							for (ResourceVO res : allowedResources) {
								if (res.equals(resourceObj)) {
									resourceDescription = res.getDescription();
								} else {
									resourceDescription = "Method call";
								}
							}
							if (allowedResources != null && !allowedResources.contains(resourceObj)) {
								logger.info("Trying to access unauthorized resource...Redirecting...");
								response.sendRedirect(request.getContextPath() + "/" + "403.html");
								return false;// valid available resource but user not authorized
							} // End Checking any allowedResources
						} // End checking any availableResources
						/*
						 * if (resource.startsWith("imagedownload")) { try {
						 * request.getSession().getAttribute("currentSessionId").toString(); }catch
						 * (Exception e) { response.sendRedirect(request.getContextPath() + "/" +
						 * "login.html?sessExp=true"); return false; } }
						 */
						boolean requestController = resource.contains("guest/") || resource.contains("admin/")
								|| resource.contains("setting/") || resource.contains("mdm/");
						if (requestController) {
							if (null == request.getSession(false)
									|| null == request.getSession(false).getAttribute("userId")) {
								response.sendRedirect(request.getContextPath() + "/" + "login.html?sessExp=true");
								return false;
							}
						}
					} // End if .do resource check
				} else {
					logger.info("Trying to access unauthorized resource...Redirecting...");
					response.sendRedirect(request.getContextPath() + "/" + "403.html");
					return false;// valid available resource but user not authorized
				}
				logger.info("Exit ---> AuthorizationInterceptor");
				return true;
			} else {
				logger.info("Request Method@@##:  inside true");
				// response.setHeader("Access-Control-Allow-Origin", "*"); //Origin
				// http://localhost:4200
//		        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//		        response.setHeader("Access-Control-Allow-Origin", "http://192.168.0.70:3001");
				// response.setHeader("Access-Control-Allow-Credentials", "true");
				// response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT,
				// DELETE");//"GET, POST, PUT, DELETE"
				// response.setHeader("Access-Control-Max-Age", "1800");
				// response.setHeader("Access-Control-Allow-Headers", "authorization,
				// content-type,captchaKey, xsrf-token, rgid, caseno,Set-Cookie,Origin");
				// response.setHeader("Access-Control-Allow-Headers",
				// "proxyId,X-Requested-With,captchaKey,content-type,
				// Authorization,caseno,Set-Cookie,Origin");
				// response.addHeader("Access-Control-Expose-Headers",
				// "xsrf-token,captchaKey,Authorization,proxyId,caseno,Set-Cookie,Origin");
				FilterChain filterChain = new FilterChain() {

					@Override
					public void doFilter(ServletRequest request, ServletResponse response)
							throws IOException, ServletException {
						// TODO Auto-generated method stub

					}
				};
				filterChain.doFilter(request, response);
				return true;
			}
		} else {
			System.out.println("inside this");
			// response.setHeader("Access-Control-Allow-Origin", "*");
			if ("OPTIONS".equals(request.getMethod())) {
				response.setStatus(200);
				System.out.println("inside this");
				return true;
			}
			Boolean status = true;
			final String authorization1 = request.getHeader("Authorization");
			final String tokenKey = request.getHeader("TokenKey");
			final String authorization = (authorization1 != null) ? authorization1 : tokenKey;

			String token = null;
			String userName = null;
			UserModel userMaster = null;
			if (null != authorization) {
				if (authorization.startsWith("Bearer ")) {
					token = authorization.substring("Bearer ".length());
					try {
						Map<String, Object> objectMap = (Map<String, Object>) jwtUtil.getAllClaimsFromToken(token)
								.get("user");
						ObjectMapper objectMapper = new ObjectMapper();
						userMaster = objectMapper.convertValue(objectMap, UserModel.class);
						// userName = jwtUtil.getUsernameFromToken(token);
						userName = userMaster.getUserId();
					} catch (IllegalArgumentException | ExpiredJwtException e) {
						response.setStatus(401);
						logger.error(e.getMessage());
						status = false;
						return false;
					}
				} else {
					logger.error("Jwt Token does not start with Bearer");
				}
			}
			System.out.println("userName" + userName);
			System.out.println("userType" + userMaster.getUserType());
			String userType = userMaster.getUserType();
			request.setAttribute("userType", userType);
			if (null != userName && null == SecurityContextHolder.getContext().getAuthentication()) {
				try {
					UserDetails userDetails = customUserDetailsService.loadUserDetailsByUserNameAndType(userName,
							userMaster.getUserType());
					System.out.println("userDetails" + userDetails.getUsername());
					if (jwtUtil.validateToken(token, userDetails)) {
						System.out.println("token validated");

						UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						logger.info("authenticated user " + userName + ", setting security context");
						try {
							SecurityContextHolder.getContext().setAuthentication(authentication);
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println("here is exception");
							e.printStackTrace();
						}
						// response.setHeader("Access-Control-Allow-Origin", "http://localhost:8100");
						// // Origin
						// http://localhost:4200
//			        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//			        response.setHeader("Access-Control-Allow-Origin", "http://192.168.0.70:3001");
//						response.setHeader("Access-Control-Allow-Origin", "*");
						response.setHeader("Access-Control-Allow-Credentials", "true");
						response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");// "GET, POST, PUT,
																										// DELETE"
						response.setHeader("Access-Control-Max-Age", "1800");
						response.setHeader("Access-Control-Allow-Headers",
								"proxyId,X-Requested-With,captchaKey,content-type, Authorization,caseno,Set-Cookie,Origin,TokenKey");
						response.addHeader("Access-Control-Expose-Headers",
								"xsrf-token,captchaKey,Authorization,proxyId,caseno,Set-Cookie,Origin");
						FilterChain filterChain = new FilterChain() {

							@Override
							public void doFilter(ServletRequest request, ServletResponse response)
									throws IOException, ServletException {
								// TODO Auto-generated method stub

							}
						};
						filterChain.doFilter(request, response);

					}
				} catch (Exception e) {
					// response.setStatus(401);
					// status = false;
					e.printStackTrace();

				}
			}
//			logger.info("Request Method@@##:  inside bottom true");
//			Boolean status=true;
//			if (keyGenerator == null) {
//				logger.info("Injecting Autoware component KeyGenerator... ");
//				ServletContext servletContext = request.getServletContext();
//				WebApplicationContext webApplicationContext = WebApplicationContextUtils
//						.getWebApplicationContext(servletContext);
//				keyGenerator = webApplicationContext.getBean(KeyGenerator.class);
//			}
//
//			// Get the HTTP Authorization header from the request
//			String authorizationHeader = request.getHeader("Authorization");
//			 Map<String, String> map = new HashMap<String, String>();
//
//		        Enumeration headerNames = request.getHeaderNames();
//		        while (headerNames.hasMoreElements()) {
//		            String key = (String) headerNames.nextElement();
//		            String value = request.getHeader(key);
//		            map.put(key, value);
//		        }
//
//		        System.out.println(map.toString());
//			logger.info("#### authorizationHeader : " + authorizationHeader);
//
//			// Check if the HTTP Authorization header is present and formatted correctly
//			if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
//				logger.error("#### invalid authorizationHeader : " + authorizationHeader);
//				throw new NotAuthorizedException("Authorization header must be provided");
//			}
//
//			// Extract the token from the HTTP Authorization header
//			String token = authorizationHeader.substring("Bearer".length()).trim();
//			boolean authStatus = false;
//			try {
//				Claims claims = null;
//				// Validate the token
//				Key key = keyGenerator.generateKey();
//				try {
//					claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
//					authStatus = true;
//				} catch (ExpiredJwtException e) {
//					e.printStackTrace();
//					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//					status=false;
//				}
//				request.setAttribute("claims", claims);
//				logger.info("#### valid token : " + token);
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				logger.error("#### invalid token : " + token);
//				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//				status=false;
//			}

//			if (authStatus) {
//
//				
//				
//			}
			return status;
		}

	}// end preHandle

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
	}

}
