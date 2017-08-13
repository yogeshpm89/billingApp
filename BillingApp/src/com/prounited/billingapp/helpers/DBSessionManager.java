package com.prounited.billingapp.helpers;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DBSessionManager {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
		} catch(HibernateException exception) {
			if (session == null) {
				session = sessionFactory.openSession();
				System.out.println("===========> opening session " +session.hashCode());
			}
		}
		return session;
	}

	protected void closeSession(Session session) {
		if (session != null && session.isOpen()) {
			System.out.println("===========> clossing session " +session.hashCode());
			session.close();
		}
	}

	public void persist(Object entity) {
		getSession().persist(entity);
	}

	public void delete(Object entity) {
		getSession().delete(entity);
	}
}
