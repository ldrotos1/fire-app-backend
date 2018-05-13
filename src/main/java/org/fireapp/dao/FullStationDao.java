package org.fireapp.dao;

import org.fireapp.model.FullStation;
import org.springframework.stereotype.Repository;

@Repository( "fullStationDao" )
public class FullStationDao extends BaseDao<FullStation> {

	/**
	 * Gets the specified station record
	 * 
	 * @param id The ID of the station record
	 * @return The station record
	 */
	public FullStation getStation( Integer id ) {

		return this.get( "FROM FullStation s WHERE s.id = " + id );
	}
}
