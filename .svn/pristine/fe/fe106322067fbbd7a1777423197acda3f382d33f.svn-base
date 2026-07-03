package com.stl.ngms.common;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.log4j.Logger;
/**
 * 
 * @author R. Dash
 * @since  18-09-2017
 *
 */
public class XSSRequestWrapper extends HttpServletRequestWrapper {
	private static Logger logger = Logger.getLogger(XSSRequestWrapper.class);
    private static Pattern[] patterns = new Pattern[]{
        // Script fragments
        Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),
        // src='...'
        Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        // lonely script tags
        Pattern.compile("</script>", Pattern.CASE_INSENSITIVE),
        Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        // eval(...)
        Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        // expression(...)
        Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        // javascript:...
        Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE),
        // vbscript:...
        Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE),
        // onload(...)=...
        Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL)
    };

    public XSSRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);

        if (values == null) {
            return null;
        }

        int count = values.length;
        //logger.debug("Pramaters count--->"+count);
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = stripXSS(values[i]);
        }

        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);

        return stripXSS(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return stripXSS(value);
    }

    private String stripXSS(String value) {
        if (value != null) {
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            // avoid encoded attacks.
        	
        	//System.out.println("b4 ESAPI--- value = "+value);
            //value = ESAPI.encoder().canonicalize(value);
            //System.out.println("Aftr ESAPI--- value = "+value);
        	
        	//logger.info("B4 stripXSS of value-->"+value);
            // Avoid null characters
            value = value.replaceAll("\0", "");

            // Remove all sections that match a pattern
            for (Pattern scriptPattern : patterns){
                value = scriptPattern.matcher(value).replaceAll("");
            }
            //logger.info("Aftr stripXSS of value-->"+value);
        }
        return value;
    }
}