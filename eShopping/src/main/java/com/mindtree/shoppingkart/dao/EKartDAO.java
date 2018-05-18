package com.mindtree.shoppingkart.dao;

import java.util.List;
import java.util.Map;

import com.mindtree.shoppingkart.model.Persistable;


public interface EKartDAO {
	
public Persistable saveEntity(Persistable obj);
	
	public void deleteEntity(Persistable obj);
	
	public Persistable getEntity(Class obj, String key);
	
	public Persistable getEntity(Class obj, Long key);
	
	Persistable findRecord(String queryName, Map<String, Object> params);
	
	public List<Persistable> findRecords(String queryName, Map<String, Object> params);
	
	public void updateRecords(String queryName, Map<String, Object> params);

}
