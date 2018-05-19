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
	protected List<T> query( String queryStr ) {
		
		Session session = this.hibernateUtils.getSession();
		Transaction tx = null;
		List<T> list;
		
		try {
			
			tx = session.beginTransaction();
			list = ( List<T> ) session.createQuery( queryStr ).list();
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
	protected T get( String queryStr ) {
		
		Session session = this.hibernateUtils.getSession();
		Transaction tx = null;
		T item;
		
		try {
			
			tx = session.beginTransaction();
			item = ( T ) session.createQuery( queryStr ).getSingleResult();
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
	
	protected Long count( String queryStr ) {

		Session session = this.hibernateUtils.getSession();
		Transaction tx = null;
		Long count;
		
		try {
			
			tx = session.beginTransaction();
			count = (Long) session.createQuery( queryStr ).getSingleResult();
			tx.commit();
			
			return count;
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
	protected List<Object[]> nativeQuery( String queryStr ) {
		
		Session session = this.hibernateUtils.getSession();
		Transaction tx = null;
		List<Object[]> results;
		
		try {
			
			tx = session.beginTransaction();
			results = session.createNativeQuery( queryStr ).getResultList();
			tx.commit();
			
			return results;
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
