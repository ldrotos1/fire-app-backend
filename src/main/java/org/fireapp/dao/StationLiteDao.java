package org.fireapp.dao;

import org.fireapp.model.StationLite;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * An instance of this class is used to access station objects
 * from the database
 * 
 * @author Louis Drotos
 *
 */
@Repository( "stationLiteDao" )
public class StationLiteDao extends BaseDao<StationLite> {

	/**
	 * Gets a list collection of all stations
	 */
	public List<StationLite> findAllStations() {

		return this.query( "FROM StationLite" );
	}
}
