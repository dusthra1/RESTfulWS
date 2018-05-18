package com.mindtree.eshopping.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.mindtree.eshopping.constant.ApplicationConstants;
import com.sun.jersey.api.client.filter.LoggingFilter;


public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateProduct() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String productId = request.getParameter("productId");	
		
		Client client = ClientBuilder.newClient( new ClientConfig().register(LoggingFilter.class) );
		StringBuilder results = new StringBuilder();  
		
		Response response1 = null;
		
		if(!productId.isEmpty()){
			response1 = client.target(ApplicationConstants.REQ_URI).path("/products").path(productId).request(MediaType.APPLICATION_XML).get();
						
			if (response1.getStatus() == 200) {
				results.append(response1.readEntity(String.class));
			}else{
				results.append("Product(s) not found.");
			}			
		}
		
		request.setAttribute("results", results);
		request.setAttribute("productId", productId);
		RequestDispatcher view = request.getRequestDispatcher("updateProduct.jsp");
		view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String productxml = request.getParameter("productxml");	
		String productId = request.getParameter("productId");	
		
		if(!productxml.isEmpty()){
			
			Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
			WebTarget webTarget = client.target(ApplicationConstants.REQ_URI).path("/products").path(productId);
			
			Invocation.Builder invocationBuilder =  webTarget.request(MediaType.TEXT_PLAIN); //Sets Accept type in request
			//Response response2 = invocationBuilder.put(Entity.entity(productxml, MediaType.APPLICATION_XML)); // sets ContentType in request.
			Response response2 = invocationBuilder.put(Entity.xml(productxml)); // sets ContentType in request.
						
			String results = null;  
			
			if (response2.getStatus() == 200) {
				results = response2.readEntity(String.class);
			}else{
				results = "--ERROR Occured-- Please try again.";
			}
			request.setAttribute("message", results);
			RequestDispatcher view = request.getRequestDispatcher("updateProduct.jsp");
			view.forward(request, response);
			
		}
	}

}
