package com.mindtree.eshopping.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Category {
	
	public Category(){
		
	}
	
	public Category(long id){
		this.id = id;
	}
	
	private long id;
	private String category;
	
	public long getId() {
		return id;
	}
	public String getCategory() {
		return category;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Category= "+category+"";
	}
	
	

}
