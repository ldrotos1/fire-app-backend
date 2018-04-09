package org.fireapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtils {

	private static SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();
	
	public HibernateUtils() {
		
	}
	
	/**
	 * Creates and returns a new Hibernate session
	 */
	public Session getSession() {
		
		return HibernateUtils.sessionFactory.openSession();
	}
}
