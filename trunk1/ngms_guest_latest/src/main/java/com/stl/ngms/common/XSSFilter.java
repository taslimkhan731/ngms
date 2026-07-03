package com.stl.ngms.common;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;
/**
 * 
 * @author R. Dash
 * @since  18-09-2017
 *
 */
public class XSSFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
    	System.out.println("XSSFilter on...........................................................");
        chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);
    }

}
