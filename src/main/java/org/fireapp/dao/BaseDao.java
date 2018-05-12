package org.fireapp.dao;

import java.util.List;

import org.fireapp.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * A base DAO class that all other DAO classes should extends. 
 * Includes low level methods for accessing database.
 * 
 * @author Louis Drotos
 *
 */
@Repository( "baseDao" )
public abstract class BaseDao<T> {

	@Autowired
	protected HibernateUtils hibernateUtils;
	
	@SuppressWarnings("unchecked")
	protected List<T> query( String query ) {
		
		Session session = this.hibernateUtils.getSession();
		Transaction tx = null;
		List<T> list;
		
		try {
			
			tx = session.beginTransaction();
			list = ( List<T> ) session.createQuery( query ).list();
			tx.commit();
			
			return list;
		}
		catch( Exception e ) {
			
			if ( tx != null ) {
				
				tx.rollback();
			}
			
			throw e;
		}
		finally {
			
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	protected T get( String query ) {
		
		Session session = this.hibernateUtils.getSession();
		Transaction tx = null;
		T item;
		
		try {
			
			tx = session.beginTransaction();
			item = ( T ) session.createQuery( query ).getSingleResult();
			tx.commit();
			
			return item;
		}
		catch( Exception e ) {
			
			if ( tx != null ) {
				
				tx.rollback();
			}
			
			throw e;
		}
		finally {
			
			session.close();
		}
	}
}
