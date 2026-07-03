package com.stl.ngms.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import java.io.IOException;

public class DomainFilter implements Filter {

	private static final String ALLOWED_HOST = "203.193.144.93:9090"; 
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String host = httpRequest.getHeader("Host");
        System.out.println(host);
        if (host != null && !host.equals(ALLOWED_HOST)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}
