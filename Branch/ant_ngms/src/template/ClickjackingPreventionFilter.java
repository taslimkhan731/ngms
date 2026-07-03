package template;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class ClickjackingPreventionFilter implements Filter {

	//	private String mode = "DENY";
	private String mode = "SAMEORIGIN";
	private String mode1 = "nosniff";

	// Add X-FRAME-OPTIONS response header to tell any other browsers who   not to display this //content in a frame.
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse)response;
		res.setHeader("Cache-control", "no-cache, no-store,max-age=0, must-revalidate");
		res.addHeader("X-XSS-Protection", "1; mode=block" ); 
		res.addHeader("X-FRAME-OPTIONS", mode );   
		res.addHeader("Access-Control-Allow-Credentials", "true");
		res.addHeader("Access-Control-Allow-Methods", "GET,POST");
		res.addIntHeader("Access-Control-Max-Age", 1800);
//		res.addHeader("Content-Type", "text/html; charset=UTF-8");
		res.addIntHeader("Expires", 0);
		res.addHeader("Pragma", "no-cache");
		res.addHeader("X-Application-Context", "application");
//		res.addHeader("X-Content-Type-Options", mode1);
		chain.doFilter(request, response);
	}
	public void destroy() {
	}

	public void init(FilterConfig filterConfig) {
		String configMode = filterConfig.getInitParameter("mode");
		if ( configMode != null ) {
			mode = configMode;
		}
	}

}
