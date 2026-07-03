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
		logger.info"Request URI :"+reqUri);
		String servPath = request.getServletPath() ;
		logger.info"Request Servlet Path :"+servPath);
		String authType = request.getAuthType();
		logger.info"Request AuthPath :"+authType);
		String reqSessid = request.getRequestedSessionId();
		logger.info"Request Session Id :"+reqSessid);
		String reqGetMethod = request.getMethod();
		logger.info"Request Method :"+reqGetMethod);
		String contextPath = request.getContextPath();
		logger.info"Context path :"+contextPath);
		String locAddr = request.getLocalAddr();
		logger.info"LocalAddress : "+locAddr);
		String locName = request.getLocalName();
		logger.info"Local Name :"+locName);
		String queryString = request.getQueryString();
		logger.info"Query String :"+queryString);
		StringBuffer reqUrl = request.getRequestURL();
		logger.info"ReqUrl as String Buffer :"+reqUrl);
		Enumeration<String> headerEnum = request.getHeaderNames();
		while(headerEnum.hasMoreElements())
			logger.info"Header :"+headerEnum.nextElement());*/
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView){
		//logger.info"inside postHandle() of PerformanceLogger Class");
		endTime = System.currentTimeMillis();
		logger.warn("Request servered ---> Total time: " + (endTime - startTime) + " ms");
	}
	
	public void logPerformance(String applicationUrl,String time){
		
	}
	

}
