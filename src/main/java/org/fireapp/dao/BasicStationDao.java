package org.fireapp.dao;

import org.springframework.stereotype.Component;

import java.util.List;

import org.fireapp.model.station.BasicStation;

/**
 * An instance of this class is used to access station objects
 * from the database
 * 
 * @author Louis Drotos
 *
 */
@Component
public class BasicStationDao extends BaseDao<BasicStation> {

	/**
	 * Gets a list collection of all stations
	 */
	public List<BasicStation> findAllStations() {

		return this.query( "FROM BasicStation" );
	}
}
