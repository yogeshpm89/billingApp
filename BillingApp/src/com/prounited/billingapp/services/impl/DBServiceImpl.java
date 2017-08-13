package com.prounited.billingapp.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.prounited.billingapp.helpers.Criterion;
import com.prounited.billingapp.helpers.DBSessionManager;
import com.prounited.billingapp.services.DBService;

@Service("dbService")
public class DBServiceImpl extends DBSessionManager implements DBService {
	
	@Override
	public Session openSession() {
		return getSession();
	}
	
	@Override
	public void closeSession(Session session) {
		super.closeSession(session);
	}
	@Override
	public <T> Object saveOrUpdate(Session session, Object entity, Class<T> entityClass)
			throws Exception {
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(entity);
			transaction.commit();
		} catch (HibernateException exception) {
			transaction.rollback();
			throw new Exception(exception.getMessage());
		}
		return entity;
	}
	
	
	public List<Object> bulkSaveOrUpdate(Session session, List<Object> entities)
			throws Exception {
		List<Object> resultList = new ArrayList<Object>();
		Transaction transaction = session.beginTransaction();
		try {
			for (Object object: entities) {
				session.saveOrUpdate(object.getClass().getName(), object);
				resultList.add(object);
			}
			transaction.commit();
		} catch (HibernateException exception) {
			transaction.rollback();
			resultList.clear();
			throw new Exception(exception.getMessage());
		}
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getAll(Session session, Class<T> entityClass,
			Map<String, String> sortMap, Map<String, Object> filterMap,
			int page, int limit) throws Exception {

		List<T> list = new ArrayList<T>();
		Criteria criteria = session.createCriteria(entityClass);

		if (filterMap != null && filterMap.size() > 0) {
			Set<Map.Entry<String, Object>> filterMapEntries = filterMap
					.entrySet();
			for (Map.Entry<String, Object> entry : filterMapEntries) {
				String valueType =  entry.getValue().getClass().getName();
				if (Long.class.getName().equalsIgnoreCase(valueType)) {
					criteria.add(Restrictions.eq(entry.getKey(),entry.getValue()));
				} else {
					criteria.add(Restrictions.like(entry.getKey(),"%" + entry.getValue() + "%"));
				}
			}
		}

		if (sortMap != null && sortMap.size() > 0) {
			String direction = sortMap.get("direction");
			String property = sortMap.get("property");

			if ("ASC".equalsIgnoreCase(direction)) {
				criteria.addOrder(Order.asc(property));
			} else {
				criteria.addOrder(Order.desc(property));
			}
		}
		
		if (limit > 0) {
			criteria.setFirstResult(((page-1)*limit)-1);
			criteria.setMaxResults(limit);
		}
		try {
			list = criteria.list();
		} catch (HibernateException exception) {
			throw new Exception(exception.getMessage());
		}

		return list;
	}

	@Override
	public <T> List<T> getAll(Session session, Class<T> entityClass,
			Map<String, String> sortMap, List<Criterion> criterions, int page,
			int limit) throws Exception {

		List<T> list = new ArrayList<T>();
		Criteria criteria = session.createCriteria(entityClass);

		if (criterions != null && !criterions.isEmpty()) {
			for (Criterion criterion : criterions) {
				String key = criterion.getKey();
				Object value = criterion.getValue();
				switch (criterion.getType()) {
				case "string":
					criteria.add(Restrictions.like(key, "%" + value + "%"));
					break;
				case "long":
					criteria.add(Restrictions.eq(key, value));
				default:
					break;
				}
			}
		}

		if (sortMap != null && sortMap.size() > 0) {
			String direction = sortMap.get("direction");
			String property = sortMap.get("property");

			if ("ASC".equalsIgnoreCase(direction)) {
				criteria.addOrder(Order.asc(property));
			} else {
				criteria.addOrder(Order.desc(property));
			}
		}
		criteria.setFirstResult(page);
		criteria.setMaxResults(limit);
		try {
			list = criteria.list();
		} catch (HibernateException exception) {
			throw new Exception(exception.getMessage());
		}
		return list;
	}

	@Override
	public <T> int getAllCount(Session session, Class<T> entityClass,
			Map<String, Object> filterMap) throws Exception {
		int count = 0;
		Criteria criteria = session.createCriteria(entityClass);

		if (filterMap != null && filterMap.size() > 0) {
			Set<Map.Entry<String, Object>> filterMapEntries = filterMap.entrySet();
			for (Map.Entry<String, Object> entry : filterMapEntries) {
				String valueType =  entry.getValue().getClass().getName();
				if (Long.class.getName().equalsIgnoreCase(valueType)) {
					criteria.add(Restrictions.eq(entry.getKey(),entry.getValue()));
				} else {
					criteria.add(Restrictions.like(entry.getKey(),"%" + entry.getValue() + "%"));
				}
			}
		}
		criteria.setProjection(Projections.rowCount());
		try {
			count = ((Number) criteria.uniqueResult()).intValue();
		} catch (HibernateException exception) {
			throw new Exception(exception.getMessage());
		}
		return count;
	}

	@Override
	public <T> String delete(Session session, Class<T> entityClass, Map<String, String> deleteMap)
			throws Exception {
		return null;
	}

	@Override
	public <T> String executeUpdateQuery(Session session, String hqlQuery, List<Criterion> criterions)
			throws Exception {
		String result = "-1";
		Query query = session.createQuery(hqlQuery);
		Transaction transaction = session.beginTransaction();

		for (Criterion criterion : criterions) {
			String key = criterion.getKey();
			Object value = criterion.getValue();
			switch (criterion.getType()) {
			case "string":
				query.setString(key, (String) value);
				break;
			case "long": 
				BigDecimal bigDecimal = new BigDecimal((Long)value);
				query.setBigDecimal(key, bigDecimal);
			default:
				break;
			}
		}

		try {
			int count = query.executeUpdate();
			transaction.commit();
			result = "1";
		} catch (HibernateException exception) {
			result = "-1";
			transaction.rollback();
			throw new Exception(exception.getMessage());
		}
		return result;
	}

	@Override
	public <T> Object executeQuery(Session session, String hqlQuery,
			List<Criterion> criterions) throws Exception {
		Query query = session.createQuery(hqlQuery);
		
		if (criterions != null && !criterions.isEmpty()) {
			for (Criterion criterion : criterions) {
				String key = criterion.getKey();
				Object value = criterion.getValue();
				switch (criterion.getType()) {
				case "string":
					query.setString(key, (String) value);
					break;
				case "long": 
					BigDecimal bigDecimal = new BigDecimal((Long)value);
					query.setBigDecimal(key, bigDecimal);
				default:
					break;
				}
			}
		}
		
		@SuppressWarnings("unchecked")
		List<Object> objects = query.list();
		return objects;
	}
}
