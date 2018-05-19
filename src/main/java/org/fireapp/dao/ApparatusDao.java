package org.fireapp.dao;

import java.util.List;

import org.fireapp.model.Apparatus;
import org.springframework.stereotype.Repository;

/**
 * An instance of this class is used to access apparatus objects
 * from the database
 * 
 * @author Louis Drotos
 *
 */
@Repository( "apparatusDao" )
public class ApparatusDao extends BaseDao<Apparatus>  {

	/**
	 * Gets a list of apparatus assigned to the specified station
	 * 
	 * @param designator The station designator
	 * @return The list of assigned apparatus
	 */
	public List<Apparatus> getApparatusAssignedToStation( Integer designator ) {

		return this.query( "FROM Apparatus a WHERE a.stationDesignator = " + designator );
	}
	
	/**
	 * Returns the number of apparatus assigned to the specified
	 * fire station
	 * 
	 * @param stationId The fire station ID
	 * @return The number of assigned apparatus
	 */
	public Long getApparatusCount( Integer stationId ) {
		
		return this.count( "SELECT COUNT(*) FROM Apparatus a WHERE a.stationId = " + stationId );
	}
}
