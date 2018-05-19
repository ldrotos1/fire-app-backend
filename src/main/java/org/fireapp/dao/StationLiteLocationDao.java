package org.fireapp.dao;

import org.fireapp.model.StationLiteLocation;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * An instance of this class is used to access station objects
 * from the database
 * 
 * @author Louis Drotos
 *
 */
@Repository( "stationLiteLocationDao" )
public class StationLiteLocationDao extends BaseDao<StationLiteLocation> {

	/**
	 * Gets a list collection of all stations
	 */
	public List<StationLiteLocation> findAllStations() {

		return this.query( "FROM StationLiteLocation" );
	}
}
