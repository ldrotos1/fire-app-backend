package org.fireapp.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fireapp.model.Department;
import org.fireapp.model.StationLiteUnitCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository( "departmentDao" )
public class DepartmentDao extends BaseDao<Department> {
	
	@Autowired
	private ApparatusDao apparatusDao;
	
	/**
	 * Gets the specified fire department record
	 * 
	 * @param id The ID of the fire department record
	 * @return The department record
	 */
	public Department getDepartment( Integer id ) {

		// Gets the department object
		Department dept = this.get( "FROM Department d WHERE d.departmentId = " + id );
		
		// Gets the apparatus count for each station
		for( StationLiteUnitCount station : dept.getStations() ) {
			
			station.setUnitCount( apparatusDao.getApparatusCount( station.getStationId() ) );
		}
		
		// Creates the apparatus category map SQL
		StringBuilder sql = new StringBuilder();
		sql.append( "SELECT category, count(*) " );
		sql.append( "FROM station stat " );
		sql.append( "JOIN apparatus app " );
		sql.append( "ON stat.station_id = app.station_id " );
		sql.append( "JOIN apparatus_type apptype " );
		sql.append( "ON app.apparatus_type_id = apptype.apparatus_type_id " );
		sql.append( "WHERE stat.department_id = " );
		sql.append( dept.getDepartmentId() );
		sql.append( " GROUP BY category" );
		
		// Gets the query results and builds the map
		Map<String, BigInteger> map = new HashMap<String, BigInteger>();
		List<Object[]> results = apparatusDao.nativeQuery( sql.toString() );
		
		for ( Object[] row: results ) {
			
			map.put( (String)row[0], (BigInteger)row[1] );
		}
		
		// Sets the map on the department object and returns
		dept.setUnitTypeMap( map );
		return dept;
	}
	
	/**
	 * Returns a map collection of all department IDs and the number 
	 * of apparatus of the specified type belonging to each department
	 * 
	 * @param apparatusTypeId The apparatus type ID
	 * @return The map collection
	 */
	public Map<Integer,BigInteger> getDeptApparatusTypeMap( Integer apparatusTypeId ) {
		
		// Creates the SQL
		StringBuilder sql = new StringBuilder();
		sql.append( "SELECT dept.department_id, count(*) " );
		sql.append( "FROM department dept " );
		sql.append( "JOIN station stat " );
		sql.append( "ON dept.department_id = stat.department_id " );
		sql.append( "JOIN apparatus app " );
		sql.append( "ON stat.station_id = app.station_id " );
		sql.append( "JOIN apparatus_type apptype " );
		sql.append( "ON app.apparatus_type_id = apptype.apparatus_type_id " );
		sql.append( "WHERE apptype.apparatus_type_id = " );
		sql.append( apparatusTypeId );
		sql.append( " GROUP BY dept.department_id" );
		
		// Gets the query results and builds the map
		Map<Integer, BigInteger> map = new HashMap<Integer, BigInteger>();
		List<Object[]> results = this.nativeQuery( sql.toString() );
		
		for ( Object[] row: results ) {
			
			map.put( (Integer)row[0], (BigInteger)row[1] );
		}
		
		return map;
	}
}
