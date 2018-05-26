package org.fireapp.dao;

import java.util.List;

import org.fireapp.model.ApparatusTypeLite;
import org.springframework.stereotype.Repository;

/**
 * An instance of this class is used to access simplified apparatus 
 * type objects from the database
 * 
 * @author Louis Drotos
 *
 */
@Repository( "apparatusTypeLiteDao" )
public class ApparatusTypeLiteDao extends BaseDao<ApparatusTypeLite> {

	/**
	 * Gets a list of all apparatus types
	 * 
	 * @return The list of apparatus types
	 */
	public List<ApparatusTypeLite> getApparatusTypes() {
		
		return this.query( "FROM ApparatusTypeLite" );
	}
}
