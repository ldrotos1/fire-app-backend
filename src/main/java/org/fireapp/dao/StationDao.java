package org.fireapp.dao;

import java.util.ArrayList;
import java.util.List;

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
	
	/**
	 * Returns the number of apparatus assigned to the
	 * specified station
	 * 
	 * @param id The station ID
	 * @return The number of assigned apparatus
	 */
	public Long getApparatusCount( Integer id ) {
		
		return this.count( "SELECT COUNT(*) FROM Apparatus a WHERE a.stationId = " + id );
	}
	
	/**
	 * Returns the list of station IDs for stations that have an
	 * apparatus of the specified type assigned to it 
	 * 
	 * @param apparatusTypeId The apparatus type ID
	 * @return The station ID list
	 */
	public List<Integer> getStationListForApparatusType( Integer apparatusTypeId ) {
		
		// Creates the SQL
		StringBuilder sql = new StringBuilder();
		sql.append( "SELECT DISTINCT station_id " );
		sql.append( "FROM apparatus " );
		sql.append( "WHERE apparatus_type_id = " );
		sql.append( apparatusTypeId );
		
		// Gets the results and builds the list
		List<Integer> stationList = new ArrayList<Integer>();
		List<Object> results = this.nativeQuerySingleColumn( sql.toString() );
		
		for ( Object result: results ) {
			
			stationList.add( ( Integer )result );
		}
		
		return stationList;
	}
}
