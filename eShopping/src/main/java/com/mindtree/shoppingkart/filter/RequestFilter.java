package com.mindtree.shoppingkart.filter;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mindtree.shoppingkart.exception.UnauthorizedRequestException;

/**
 * Servlet Filter implementation class RequestFilter
 */
public class RequestFilter implements Filter {
	
	private static final Logger log = Logger.getLogger(RequestFilter.class);
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		log.info("Requested URL: "+req.getRequestURL().toString()+ " RequestType: "+req.getMethod()+ " ContentType: "+req.getContentType());
		
		String authString = req.getHeader("Authorization");
		
		if(authString != null && isUserAuthenticated(authString)){
			chain.doFilter(req, res);
		}else{
			log.error("--ALERT-- ERROR: Unauthorized Request");
			throw new UnauthorizedRequestException("HTTP Error 401 - Unauthorized: Access is denied due to no/invalid credentials");
		}
		
		
	}
	
	private boolean isUserAuthenticated(String authString){
        
		boolean flag = false;
        String decodedAuth = "";
        // Header is in the format "Basic 5tyc0uiDat4"
        // We need to extract data before decoding it back to original string
        String[] authParts = authString.split("\\s+");
        String authInfo = authParts[1];
        // Decode the data back to original string
        byte[] bytes = null;
        bytes = Base64.getDecoder().decode(authInfo);
        decodedAuth = new String(bytes);
       
         if("admin:admin".equals(decodedAuth)){
        	 flag = true;        	 
         }
        return flag;
    }

	
	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	public RequestFilter() {
	}

		
	public void destroy() {
	}

}
