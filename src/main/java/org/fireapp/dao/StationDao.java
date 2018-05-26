package org.fireapp.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * Returns a map collection of all station IDs and the number 
	 * of apparatus of the specified type assigned to each station
	 * 
	 * @param apparatusTypeId The apparatus type ID
	 * @return The map collection
	 */
	public Map<Integer,BigInteger> getStationApparatusTypeMap( Integer apparatusTypeId ) {
		
		// Creates the SQL
		StringBuilder sql = new StringBuilder();
		sql.append( "SELECT stat.station_id, count(*) " );
		sql.append( "FROM station stat " );
		sql.append( "JOIN apparatus app " );
		sql.append( "ON stat.station_id = app.station_id " );
		sql.append( "JOIN apparatus_type apptype " );
		sql.append( "ON app.apparatus_type_id = apptype.apparatus_type_id " );
		sql.append( "WHERE apptype.apparatus_type_id = " );
		sql.append( apparatusTypeId );
		sql.append( " GROUP BY stat.station_id" );
		
		// Gets the query results and builds the map
		Map<Integer, BigInteger> map = new HashMap<Integer, BigInteger>();
		List<Object[]> results = this.nativeQuery( sql.toString() );
		
		for ( Object[] row: results ) {
			
			map.put( (Integer)row[0], (BigInteger)row[1] );
		}
		
		return map;
	}
}
