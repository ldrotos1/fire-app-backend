package org.fireapp.dao;

import org.fireapp.model.BasicStation;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * An instance of this class is used to access station objects
 * from the database
 * 
 * @author Louis Drotos
 *
 */
@Repository( "basicStationDao" )
public class BasicStationDao extends BaseDao<BasicStation> {

	/**
	 * Gets a list collection of all stations
	 */
	public List<BasicStation> findAllStations() {

		return this.query( "FROM BasicStation" );
	}
}
