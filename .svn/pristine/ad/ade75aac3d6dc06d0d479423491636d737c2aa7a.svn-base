package com.stl.ngms.common;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.stl.ngms.setting.model.ResourceVO;
/**
 * 
 * @author SK Patra
 * @since  15-05-2018
 *
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter{
	private static final Logger logger = Logger.getLogger(AuthorizationInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("Entry ---> AuthorizationInterceptor");
		List<ResourceVO> availableResources = null ;
		List<ResourceVO> allowedResources = null ;
		boolean allowedflag = false ;
		boolean availableflag = false ;
		logger.warn("INTERCEPT URI: " + request.getRequestURI());  
		String servPath = request.getServletPath() ;//login 
		System.out.println("Request Method@@##:  " + request.getMethod());
		if (request.getMethod().equals("OPTIONS")||request.getMethod().equals("TRACE") ||request.getMethod().equals("TRACK")||request.getMethod().equals("PUT")||request.getMethod().equals("DELETE"))
        {
        	System.out.println("Methods Not allow");
        	((HttpServletResponse) response).sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        	return false;
        }
		if(request.getMethod().equalsIgnoreCase("POST") || request.getMethod().equalsIgnoreCase("GET")){
			if(servPath.endsWith(".do") || servPath.endsWith(".html") ){
				String resource= servPath.substring(1,servPath.indexOf('.'));
				logger.info("resource--->"+resource);
				logger.info(request.getContextPath());
				availableResources =(List<ResourceVO>) request.getSession().getAttribute("availableResources");
				allowedResources =(List<ResourceVO>) request.getSession().getAttribute("allowedResources");
				ResourceVO resourceObj = new ResourceVO(resource);
				System.out.println("resourceObj"+resourceObj);
				System.out.println("availableResources"+availableResources);
				//System.out.println("availableResources.contains(resourceObj)"+availableResources.contains(resourceObj));
				if(availableResources!=null && availableResources.contains(resourceObj)){//Check presence of resource in All Available Resources
					String resourceDescription = "";
					Cookie cookie[]=request.getCookies();
					Cookie cook=cookie[0];
					cook.setMaxAge(0); // Don't set to -1 or it will become a session cookie!
					response.addCookie(cook);
					System.out.println("=======================" +request.getSession().getAttribute("currentSessionId").toString());
					String jSessionId=cook.getName().equalsIgnoreCase("JSESSIONID")?cook.getValue():"NA";
					System.out.println("jSessionId===================="+jSessionId);
					
					if(!request.getSession().getAttribute("currentSessionId").toString().equalsIgnoreCase(jSessionId)) {
						logger.info("Trying to access unauthorized resource...Redirecting11111...");
						response.sendRedirect(request.getContextPath() + "/"+"403.html");
						return false;//valid available resource but user not authorized 
					}
//					for(ResourceVO res : allowedResources) {
//						if(res.equals(resourceObj)) {
//							resourceDescription = res.getDescription();
//						} else {
//							resourceDescription = "Method call";
//						}
//					}
					
					if(allowedResources!=null && !allowedResources.contains(resourceObj)){
						logger.info("Trying to access unauthorized resource...Redirecting...");
						response.sendRedirect(request.getContextPath() + "/"+"403.html");
						return false;//valid available resource but user not authorized 
					}//End Checking any allowedResources
				}//End checking any availableResources
				boolean requestController=resource.contains("guest/")||resource.contains("admin/");
				if(requestController) {
				if(null == request.getSession(false) || null == request.getSession(false).getAttribute("userId")){     
			        response.sendRedirect(request.getContextPath() + "/"+"login.html?sessExp=true"); 
			        return false;
			    }
				}
			}//End if .do resource check
		}else{
			logger.info("Trying to access unauthorized resource...Redirecting...");
			response.sendRedirect(request.getContextPath() + "/"+"403.html");
			return false;//valid available resource but user not authorized 
		}
		logger.info("Exit ---> AuthorizationInterceptor");
		return true;
	}//end preHandle
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView){
	}
	
	

}
