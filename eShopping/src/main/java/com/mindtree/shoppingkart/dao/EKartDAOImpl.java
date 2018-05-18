package com.mindtree.shoppingkart.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.shoppingkart.model.Persistable;

@Repository
public class EKartDAOImpl implements EKartDAO {
	
	private static final Logger log = Logger.getLogger(EKartDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	public Persistable saveEntity(Persistable obj) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.saveOrUpdate(obj);
			
		} catch (Exception ex) {	
			log.error("DAO Exception occurred: "+ex.getMessage());
		}
		return obj; 	
	}

	public void deleteEntity(Persistable obj) {

		Session session = sessionFactory.getCurrentSession();
		try {
			session.delete(obj);
			
		} catch (Exception ex) {	
			log.error("DAO Exception occurred: "+ex.getMessage());
		} 	
	}

	public Persistable getEntity(Class classObj, String key) {
		Persistable retrievedObj=null;
		Session session = sessionFactory.getCurrentSession();
		try {
			retrievedObj= (Persistable) session.load(classObj, key);
			
		} catch (Exception ex) {
			log.error("DAO Exception occurred: "+ex.getMessage());
		} 	
		return retrievedObj;
	}

	public Persistable getEntity(Class classObj, Long key) {
		Persistable retrievedObj=null;
		Session session = sessionFactory.getCurrentSession();
		try {
			retrievedObj= (Persistable) session.load(classObj, key);
			
		} catch (Exception ex) {	
			log.error("DAO Exception occurred: "+ex.getMessage());
		} 	
		return retrievedObj;
	}

	public Persistable findRecord(String queryName, Map<String, Object> params) {
		Session session = sessionFactory.getCurrentSession();
		Persistable persistable = null;
		try {
			
			Query query = session.createNamedQuery(queryName);
			if(params !=null){
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
			List<Persistable> resultList = query.getResultList();
			if (null != resultList && !resultList.isEmpty()) {
				persistable = resultList.get(0);
			}
		} catch (Exception ex) {
			log.error("DAO Exception occurred: "+ex.getMessage());
		}		
		return persistable;
	}

	public List<Persistable> findRecords(String queryName, Map<String, Object> params) {
		Session session = sessionFactory.getCurrentSession();
		List<Persistable> records = null;
		try{
			Query query = session.createNamedQuery(queryName);
			if(params !=null){
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
			records = query.getResultList();
		}catch (Exception ex) {	
			log.error("DAO Exception occurred: "+ex.getMessage());
		} 	
		return records;
	}

	public void updateRecords(String queryName, Map<String, Object> params) {
		Session session = sessionFactory.getCurrentSession();
		try{
			Query query = session.createNamedQuery(queryName);
			if(params !=null){
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
			query.executeUpdate();
		}catch (Exception ex) {	
			log.error("DAO Exception occurred: "+ex.getMessage());
		} 	
	}

	

}
