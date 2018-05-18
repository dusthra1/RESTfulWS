package com.mindtree.eshopping.servlet;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.mindtree.eshopping.constant.ApplicationConstants;
import com.mindtree.eshopping.model.Product;
import com.sun.jersey.api.client.filter.LoggingFilter;


@WebServlet("/viewProducts")
public class ViewProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

    public ViewProducts() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String allProducts = request.getParameter("allProducts");
		String productId = request.getParameter("productId");		
		String categoryId = request.getParameter("category");
		String startRange = request.getParameter("startRange");
		String endRange = request.getParameter("endRange");
		
	     String authString = ApplicationConstants.USERNAME + ":" + ApplicationConstants.USERKEY;
	     String authStringEnc = "Basic " +Base64.getEncoder().encodeToString(authString.getBytes());
		
		List<Product> productList =null;
		
		Client client = ClientBuilder.newClient( new ClientConfig().register(LoggingFilter.class));
		StringBuilder results = new StringBuilder();  
		
		Response response1 = null;
		
		if(!productId.isEmpty()){
			response1 = client.target(ApplicationConstants.REQ_URI).path("/products").path(productId).request(MediaType.APPLICATION_JSON).header("Authorization",authStringEnc).get();
						
			if (response1.getStatus() == 200) {
				Product product = response1.readEntity(Product.class);	
				results.append(product.toString());
			}else{
				results.append("Product(s) not found.");
			}			
		}else if(!categoryId.isEmpty()){
			response1 = client.target(ApplicationConstants.REQ_URI).path("/products").queryParam("category", categoryId).request(MediaType.APPLICATION_JSON).header("Authorization",authStringEnc).get();			
			if (response1.getStatus() == 200) {
				productList = response1.readEntity(new GenericType<List<Product>>() {});			
				for(Product p: productList){
					results.append(p.toString());
				}
			}else{
				results.append("Product(s) not found.");
			}	
			
		}else if(!startRange.isEmpty() && !endRange.isEmpty()){
			response1 = client.target(ApplicationConstants.REQ_URI).path("/products").queryParam("startRange", startRange).queryParam("endRange", endRange).request(MediaType.APPLICATION_JSON).header("Authorization",authStringEnc).get();
			if (response1.getStatus() == 200) {
				productList = response1.readEntity(new GenericType<List<Product>>() {});			
				for(Product p: productList){
					results.append(p.toString());
				}
			}else{
				results.append("Product(s) not found.");
			}	
		}else if("on".equalsIgnoreCase(allProducts)){
			response1 = client.target(ApplicationConstants.REQ_URI).path("/products").request(MediaType.APPLICATION_JSON).header("Authorization",authStringEnc).get();
			if (response1.getStatus() == 200) {
				productList = response1.readEntity(new GenericType<List<Product>>() {});			
				for(Product p: productList){
					results.append(p.toString());
				}
			}else{
				results.append("Product(s) not found.");
			}	
		}else{
			results.append("Please select any one of above columns.");
		}
		
		request.setAttribute("results", results);
		RequestDispatcher view = request.getRequestDispatcher("viewProducts.jsp");
		view.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
