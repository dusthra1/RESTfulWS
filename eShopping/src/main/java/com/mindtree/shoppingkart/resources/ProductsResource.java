package com.mindtree.shoppingkart.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindtree.shoppingkart.exception.DataNotFoundException;
import com.mindtree.shoppingkart.model.Product;
import com.mindtree.shoppingkart.service.EKartService;

@Component
@Path("/products")
public class ProductsResource {
	
	private static final Logger log = Logger.getLogger(ProductsResource.class);
	
	@Autowired
	EKartService ekartService;
	
	/*  
	 * Buyer Module 
	 */
	@GET
	@Produces(value={MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Product> getAllProducts(@QueryParam("startRange") double startPrice, @QueryParam("endRange") double endPrice,
										@QueryParam("category") long category){
		log.info("Called GET Method: get All Product");
		List<Product> prodList = null;
		if(startPrice >0 && endPrice > 0){
			prodList = ekartService.getProductsByRange(startPrice, endPrice);
			
		}else if(category >0){
			prodList = ekartService.getProductsByCategory(category);
		}else{
			prodList = ekartService.getAllProducts();
		}
		if(prodList.size()==0){
			throw new DataNotFoundException("Product(s) not found.");
		}
		return prodList;
	}
	
	@GET
	@Path("/{productId}")
	@Produces(value={MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Product getProduct(@PathParam("productId") String id){
		Product product =   (Product) ekartService.find(Product.class, Long.parseLong(id));
		if(product ==null){
			throw new DataNotFoundException("Product with Id: "+id+" not found");
		}
		return product;
	}	
	
	/*  
	 * Seller Module 
	 */
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_XML)
	public String addProduct(Product product){
		log.info("Called POST Method: Add Product");
		ekartService.saveProduct(product);
		return "Product added Successfully. Product ID:"+product.getProductId()+"";
	}
	
	@PUT
	@Path("/{productId}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_XML)
	public String updateProduct(@PathParam("productId") long productId,Product product){
		product.setProductId(productId);
		ekartService.saveProduct(product);
		return "Product updated Successfully.";
	}
	
	@DELETE
	@Path("/{productId}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_XML)
	public String removeProduct(@PathParam("productId") long productId){
		Product product =new Product();
		product.setProductId(productId);
		ekartService.deleteProduct(product);
		return "Product deleted Successfully.";
	}

}
