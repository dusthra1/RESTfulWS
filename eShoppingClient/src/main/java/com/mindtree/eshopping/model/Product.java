package com.mindtree.eshopping.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {
	
	private long productId;
	private Category category;
	private String productName;
	private double price;
	private long stock;
	private String remarks;
	
	public long getProductId() {
		return productId;
	}
	public Category getCategory() {
		return category;
	}
	public String getProductName() {
		return productName;
	}
	public double getPrice() {
		return price;
	}
	public long getStock() {
		return stock;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setStock(long stock) {
		this.stock = stock;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ",\n["+category.toString()+"], \nproductName=" + productName
				+ ", \nprice=" + price + ", \nstock=" + stock + ", \nremarks=" + remarks + "]\n\n";
	}
	
	
	

}
