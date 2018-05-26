package org.fireapp.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	/**
	 * Returns a map collection of all apparatus categories and the
	 * number of apparatus of each category that belong to the 
	 * specified fire department 
	 * 
	 * @param deptId
	 * @return The map collection
	 */
	public Map<String,BigInteger> getCategoryCountMap( Integer deptId ) {
		
		// Creates the apparatus category map SQL
		StringBuilder sql = new StringBuilder();
		sql.append( "SELECT category, count(*) " );
		sql.append( "FROM station stat " );
		sql.append( "JOIN apparatus app " );
		sql.append( "ON stat.station_id = app.station_id " );
		sql.append( "JOIN apparatus_type apptype " );
		sql.append( "ON app.apparatus_type_id = apptype.apparatus_type_id " );
		sql.append( "WHERE stat.department_id = " );
		sql.append( deptId );
		sql.append( " GROUP BY category" );
		
		// Gets the query results and builds the map
		Map<String, BigInteger> map = new HashMap<String, BigInteger>();
		List<Object[]> results = this.nativeQuery( sql.toString() );
		
		for ( Object[] row: results ) {
			
			map.put( (String)row[0], (BigInteger)row[1] );
		}
				
		return map;
	}
}
