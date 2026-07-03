package com.stl.ngms.common;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

public class SessionCookieFilter extends GenericFilterBean {
    private final List<String> PATHS_TO_IGNORE_SETTING_SAMESITE = Arrays.asList("resources", "/plugins", "/assets", "/customCSS", "/customJS");
    private final String SESSION_COOKIE_NAME = "JSESSIONID";

    public SessionCookieFilter() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        String requestUrl = req.getRequestURL().toString();
        Stream var10000 = this.PATHS_TO_IGNORE_SETTING_SAMESITE.stream();
        requestUrl.getClass();
        boolean isResourceRequest = StringUtils.isNotBlank(PATHS_TO_IGNORE_SETTING_SAMESITE.stream().filter(requestUrl::contains).findFirst().orElse(null));
        if (!isResourceRequest) {
            Cookie[] cookies = ((HttpServletRequest)request).getCookies();
            if (cookies != null && cookies.length > 0) {
                List<Cookie> cookieList = Arrays.asList(cookies);
                Cookie sessionCookie = (Cookie)cookieList.stream().filter((cookie) -> {
                    return "JSESSIONID".equals(cookie.getName());
                }).findFirst().orElse(null);
                if (sessionCookie != null) {
                    String ROOT_CONTEXT = "/";
                    String contextPath = request.getServletContext() != null && StringUtils.isNotBlank(request.getServletContext().getContextPath()) ? request.getServletContext().getContextPath() : ROOT_CONTEXT;
                    String SESSION_PATH_ATTRIBUTE = ";Path=";
                    String SAME_SITE_ATTRIBUTE_VALUES = ";HttpOnly;Secure;SameSite=None";
                    resp.setHeader("Set-Cookie", sessionCookie.getName() + "=" + sessionCookie.getValue() + SESSION_PATH_ATTRIBUTE + contextPath + SAME_SITE_ATTRIBUTE_VALUES);
                }
            }
        }

        chain.doFilter(request, response);
    }
}


//PG Not working :- As this file contains some changes.so below code is not working in production(After successful payment Session was expired)
//package com.stl.ngms.common;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.http.HttpHeaders;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
//public class SessionCookieFilter extends GenericFilterBean {
//    private final List<String> PATHS_TO_IGNORE_SETTING_SAMESITE = Arrays.asList("resources","/plugins","/assets","/customCSS","/customJS");
//    private final String SESSION_COOKIE_NAME = "JSESSIONID";
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
//        String requestUrl = req.getRequestURL().toString();
//        boolean isResourceRequest = StringUtils.isNotBlank(PATHS_TO_IGNORE_SETTING_SAMESITE.stream().filter(requestUrl::contains).findFirst().orElse(null));
//        if (!isResourceRequest) {
//            Cookie[] cookies = ((HttpServletRequest) request).getCookies();
//            if (cookies != null && cookies.length > 0) {
//                List<Cookie> cookieList = Arrays.asList(cookies);
//                Cookie sessionCookie = cookieList.stream().filter(cookie -> SESSION_COOKIE_NAME.equals(cookie.getName())).findFirst().orElse(null);
//                if (sessionCookie != null) {
//                    String ROOT_CONTEXT = "/";
//                    String contextPath = request.getServletContext() != null && StringUtils.isNotBlank(request.getServletContext().getContextPath()) ? request.getServletContext().getContextPath() : ROOT_CONTEXT;
//                    String SESSION_PATH_ATTRIBUTE = ";Path=";
//                    String SAME_SITE_ATTRIBUTE_VALUES = ";HttpOnly;Secure;SameSite=Lax";
//                    resp.setHeader(HttpHeaders.SET_COOKIE, sessionCookie.getName() + "=" + sessionCookie.getValue() + SESSION_PATH_ATTRIBUTE + contextPath + SAME_SITE_ATTRIBUTE_VALUES);
//                }
//            }
//        }
//        chain.doFilter(request, response);
//    }
//}
