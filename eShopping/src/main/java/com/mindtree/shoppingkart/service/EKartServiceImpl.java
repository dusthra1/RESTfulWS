package com.mindtree.shoppingkart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.shoppingkart.constants.NamedQueryConstants;
import com.mindtree.shoppingkart.dao.EKartDAO;
import com.mindtree.shoppingkart.model.Persistable;
import com.mindtree.shoppingkart.model.Product;

@Service
public class EKartServiceImpl implements EKartService {
	
	private static final Logger log = Logger.getLogger(EKartServiceImpl.class);
	
	@Autowired
	EKartDAO ekartDAO;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public String test() {
		
		/*ekarkDAO.saveEntity(new Category("Electronic Appliances"));
		ekarkDAO.saveEntity(new Category("Books"));
		ekarkDAO.saveEntity(new Category("Home, Kitchen"));
		ekarkDAO.saveEntity(new Category("Sports & Fitness"));
		ekarkDAO.saveEntity(new Category("Mobile & Computers"));*/
		
		/*Product p1= new Product();
		p1.setProductName("PenDrive 1GB");
		p1.setPrice(250.00);
		Category c = (Category) ekarkDAO.getEntity(Category.class, Long.parseLong("1"));
		p1.setCategory(c);
		p1.setStock(1000);
		p1.setRemarks("USB 3.0");
		ekarkDAO.saveEntity(p1);*/
	
		return "Spring-Jersey Integration";
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Persistable find(Class entityobj, Long key) {		
		Persistable obj = null;
		try {
			obj = ekartDAO.getEntity(entityobj, key);
			
		} catch (Exception ex) {
			log.error("Exception occured while finding entity " + ex.getMessage());
		}	
		return obj;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Product> getAllProducts() {
		List<Persistable> tempList = null;
		List<Product> prodList = new ArrayList<>();
		try{
			tempList =  ekartDAO.findRecords(NamedQueryConstants.ALL_PRODUCTS, null);
			for(Persistable obj:tempList){
				Product prod = (Product)obj;
				prodList.add(prod);
			}
		}catch (Exception ex) {
			log.error("Exception occured while finding products " + ex.getMessage());
		}	
		return prodList;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Product saveProduct(Product product) {
		ekartDAO.saveEntity(product);
		return product;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Product deleteProduct(Product product) {
		ekartDAO.deleteEntity(product);
		return product;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Product> getProductsByRange(double startPrice, double endPrice) {
		List<Product> prodList = new ArrayList<>();
		Map<String,Object> params = new HashMap<>();
		params.put("startRange", startPrice);
		params.put("endRange", endPrice);
		List<Persistable> tempList = ekartDAO.findRecords(NamedQueryConstants.ALL_PRODUCTS_BY_RANGE, params);
		for(Persistable obj:tempList){
			Product prod = (Product)obj;
			prodList.add(prod);
		}
		return prodList;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Product> getProductsByCategory(long category) {
		List<Product> prodList = new ArrayList<>();
		Map<String,Object> params = new HashMap<>();
		params.put("category", category);
		List<Persistable> tempList = ekartDAO.findRecords(NamedQueryConstants.ALL_PRODUCTS_BY_CATEGORY, params);
		for(Persistable obj:tempList){
			Product prod = (Product)obj;
			prodList.add(prod);
		}
		return prodList;
	}


	
}
