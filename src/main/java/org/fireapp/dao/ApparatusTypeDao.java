package org.fireapp.dao;

import java.util.List;

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
	 * Gets a list of all apparatus types
	 * 
	 * @return The list of apparatus types
	 */
	public List<ApparatusType> getApparatusTypes() {
		
		return this.query( "FROM ApparatusType" );
	}
}
