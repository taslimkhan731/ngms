package com.stl.ngms.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PerformanceLogger extends HandlerInterceptorAdapter{
	static long startTime ;
	static long endTime ;
	private static final Logger logger = Logger.getLogger(PerformanceLogger.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		startTime = System.currentTimeMillis();
		logger.warn("Request intercepted --->"	+ request.getRequestURI());
		//String servPath = request.getServletPath() ;
		
		/*String reqUri = request.getRequestURI() ;
		System.out.println("Request URI :"+reqUri);
		String servPath = request.getServletPath() ;
		System.out.println("Request Servlet Path :"+servPath);
		String authType = request.getAuthType();
		System.out.println("Request AuthPath :"+authType);
		String reqSessid = request.getRequestedSessionId();
		System.out.println("Request Session Id :"+reqSessid);
		String reqGetMethod = request.getMethod();
		System.out.println("Request Method :"+reqGetMethod);
		String contextPath = request.getContextPath();
		System.out.println("Context path :"+contextPath);
		String locAddr = request.getLocalAddr();
		System.out.println("LocalAddress : "+locAddr);
		String locName = request.getLocalName();
		System.out.println("Local Name :"+locName);
		String queryString = request.getQueryString();
		System.out.println("Query String :"+queryString);
		StringBuffer reqUrl = request.getRequestURL();
		System.out.println("ReqUrl as String Buffer :"+reqUrl);
		Enumeration<String> headerEnum = request.getHeaderNames();
		while(headerEnum.hasMoreElements())
			System.out.println("Header :"+headerEnum.nextElement());*/
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView){
		//System.out.println("inside postHandle() of PerformanceLogger Class");
		endTime = System.currentTimeMillis();
		logger.warn("Request servered ---> Total time: " + (endTime - startTime) + " ms");
	}
	
	public void logPerformance(String applicationUrl,String time){
		
	}
	

}
