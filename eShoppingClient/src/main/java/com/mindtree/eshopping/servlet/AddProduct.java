package com.mindtree.eshopping.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
import com.mindtree.eshopping.model.Category;
import com.mindtree.eshopping.model.Product;
import com.sun.jersey.api.client.filter.LoggingFilter;

@WebServlet("/addProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
 
    public AddProduct() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Product p1 = new Product();
		p1.setProductName(request.getParameter("productName"));
		p1.setCategory(new Category(Long.parseLong(request.getParameter("category"))));
		p1.setPrice(Double.parseDouble(request.getParameter("price")));
		p1.setStock(Long.parseLong(request.getParameter("stock")));
		p1.setRemarks(request.getParameter("remarks"));
		
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		WebTarget webTarget = client.target(ApplicationConstants.REQ_URI).path("/products");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.TEXT_PLAIN); //Sets Accept type in request
		Response response2 = invocationBuilder.post(Entity.entity(p1, MediaType.APPLICATION_XML)); // sets ContentType in request.
		 
		
		String message = null;  
		
			if (response2.getStatus() == 200) {
				message = response2.readEntity(String.class);
			}else{
				message = "--ERROR Occured-- Please try again.";
			}
			request.setAttribute("message", message);
			RequestDispatcher view = request.getRequestDispatcher("addProduct.jsp");
			view.forward(request, response);
	}

}
