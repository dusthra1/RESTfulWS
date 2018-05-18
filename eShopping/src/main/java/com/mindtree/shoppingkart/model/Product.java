package com.mindtree.shoppingkart.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Proxy;

import com.mindtree.shoppingkart.constants.NamedQueryConstants;
import com.mindtree.shoppingkart.constants.QueryConstants;

@Entity
@Table(name="PRODUCT")
@NamedQueries(value = { @NamedQuery(name = NamedQueryConstants.ALL_PRODUCTS, query = QueryConstants.ALL_PRODUCTS),
						@NamedQuery(name = NamedQueryConstants.ALL_PRODUCTS_BY_RANGE, query = QueryConstants.ALL_PRODUCTS_BY_RANGE),
						@NamedQuery(name = NamedQueryConstants.ALL_PRODUCTS_BY_CATEGORY, query = QueryConstants.ALL_PRODUCTS_BY_CATEGORY)
})
@Proxy(lazy = false)
@XmlRootElement
public class Product implements Persistable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="product_id")
	private long productId;
	@Column(name="")
	private String productName;
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinColumn(name="category_id")
	private Category category;
	@Column(name="price")
	private double price;
	@Column(name="stock")
	private long stock;
	@Column(name="remarks")
	private String remarks;
	
	public long getProductId() {
		return productId;
	}
	public String getProductName() {
		return productName;
	}
	public Category getCategory() {
		return category;
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
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setCategory(Category category) {
		this.category = category;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + (int) (productId ^ (productId >>> 32));
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
		Product other = (Product) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (productId != other.productId)
			return false;
		return true;
	}
	
	
}
