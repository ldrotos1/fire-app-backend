package org.fireapp.dao;

import java.util.List;

import org.fireapp.model.Border;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.vividsolutions.jts.geom.Point;

/**
 * An instance of this class is used to access border objects
 * from the database
 * 
 * @author Louis Drotos
 *
 */
@Repository( "borderDao" )
public class BorderDao extends BaseDao<Border> {

	/**
	 * Returns a boolean indicating if the specified geographic 
	 * location is within the application's area of interest
	 * 
	 * @param point The location
	 * @return True if location is within AOI, otherwise false
	 */
	public Boolean pointWithBorder( Point point ) {
		
		Session session = this.hibernateUtils.getSession();
		Transaction tx = null;
		
		try {
			
			// Creates the spatial query string
			String queryStr = "SELECT b FROM Border b WHERE CONTAINS( b.border, :location ) = TRUE";
			
			// Executes the query
			tx = session.beginTransaction();
			List<Border> border = ( List<Border> ) session.createQuery( queryStr, Border.class )
								  	.setParameter( "location", point ).list();
			tx.commit();
			
			if( border.size() > 0 ) {
				
				return true;
			}
			
			return false;
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