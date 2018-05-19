package org.fireapp.dao;

import org.fireapp.model.Station;
import org.springframework.stereotype.Repository;

@Repository( "stationDao" )
public class StationDao extends BaseDao<Station> {

	/**
	 * Gets the specified station record
	 * 
	 * @param id The ID of the station record
	 * @return The station record
	 */
	public Station getStation( Integer id ) {

		return this.get( "FROM Station s WHERE s.id = " + id );
	}
}
