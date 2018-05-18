package com.mindtree.shoppingkart.constants;

public final class QueryConstants {

	public static final String ALL_PRODUCTS = "from Product";
	
	public static final String ALL_PRODUCTS_BY_RANGE = "from Product where price between :startRange and :endRange";
	
	public static final String ALL_PRODUCTS_BY_CATEGORY = "from Product where category.id= :category";
	
}
