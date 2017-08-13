package com.prounited.billingapp.services;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.prounited.billingapp.helpers.Criterion;


@Service("dbService")
public interface DBService {
	public void closeSession(Session session);
	public Session openSession();
	public <T> Object  saveOrUpdate(Session session, Object entity, Class<T> entityClass) throws Exception;
	public List<Object> bulkSaveOrUpdate(Session session, List<Object> entities) throws Exception;
	public <T> List<T> getAll(Session session, Class<T> entityClass, Map<String, String> sortMap,
			Map<String, Object> filterMap, int page, int limit) throws Exception;
	
	public <T> List<T> getAll(Session session, Class<T> entityClass, Map<String, String> sortMap,
			List<Criterion> criterions, int page, int limit) throws Exception;
	
	public <T> int getAllCount(Session session, Class<T> entityClass, Map<String, Object> filterMap) throws Exception; 
	
	public <T> String delete(Session session, Class<T> entityClass, Map<String, String> deleteMap) throws Exception; 
	
	public <T> String executeUpdateQuery(Session session, String hqlQuery, List<Criterion> criterions) throws Exception;
	public <T> Object executeQuery(Session session, String hqlQuery, List<Criterion> criterions) throws Exception; 

}
