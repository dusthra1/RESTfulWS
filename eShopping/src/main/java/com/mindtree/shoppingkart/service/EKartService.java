package com.mindtree.shoppingkart.service;

import java.util.List;

import com.mindtree.shoppingkart.model.Persistable;
import com.mindtree.shoppingkart.model.Product;

public interface EKartService {
	
	public String test();
	
	public Persistable find(Class entityobj, Long key);
	
	public List<Product> getAllProducts();
	
	public Product saveProduct(Product product);
	
	public Product deleteProduct(Product product);
	
	public List<Product> getProductsByRange(double startPrice, double endPrice);
	
	public List<Product> getProductsByCategory(long category);

}
