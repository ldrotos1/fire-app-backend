package org.fireapp.dao;

import org.fireapp.model.ApparatusType;
import org.springframework.stereotype.Repository;

/**
 * An instance of this class is used to access apparatus type 
 * objects from the database
 * 
 * @author Louis Drotos
 *
 */
@Repository( "apparatusTypeDao" )
public class ApparatusTypeDao extends BaseDao<ApparatusType> {
	
	/**
	 * Returns the apparatus type information object for the
	 * specified apparatus type
	 * 
	 * @param id The apparatus type ID
	 * @return The apparatus type object
	 */
	public ApparatusType getApparatusType( Integer id ) {

		return this.get( "FROM ApparatusType a WHERE a.apparatusTypeId = " + id );
		
	}
}
