package com.mindtree.shoppingkart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="CATEGORY")
@Proxy(lazy = false)
@XmlRootElement
public class Category implements Persistable {
	
	private static final long serialVersionUID = 1L;
	
	public Category() {
	}
	
	public Category(String category) {
		super();
		this.category = category;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="category_id")
	private long id;
	@Column(name="category_name")
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
