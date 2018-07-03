package org.fireapp.dao;

import java.util.List;

import org.fireapp.model.incident.response.RespondingStation;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import com.vividsolutions.jts.geom.Point;

@Repository( "respondingStationDao" )
public class RespondingStationDao extends BaseDao<RespondingStation> {

	/**
	 * Returns the nearest stations in euclidean distance to the specified
	 * location
	 * 
	 * @param point The location
	 * @param stationCount The number of stations to return
	 * @return The list of the stations
	 */
	public List<RespondingStation> getNearestStations( Point point, Integer stationCount ) {
		
		StringBuilder sql;
		Session session;
		NativeQuery<RespondingStation> query;
		List<RespondingStation> result;
		
		sql = new StringBuilder();
		sql.append( "SELECT * " );
		sql.append( "FROM station s " );
		sql.append( "ORDER BY ST_Distance( s.location, :point ) " );
		sql.append( "LIMIT :count" );
		
		session = this.hibernateUtils.getSession();
		query = session.createNativeQuery( sql.toString(), RespondingStation.class );
		query.setParameter( "point", point );
		query.setParameter( "count", stationCount );
		result = query.getResultList();
		
		return result;
	}
	
	/**
	 * Returns all stations that have at least one apparatus of one of the 
	 * specified types assigned to it
	 * 
	 * @param apparatusTypeIds The apparatus type ID
	 * @return The list of the stations
	 */
	public List<RespondingStation> getAllStationsWithApparatusType( List<Integer> apparatusTypeIds ) {
		
		StringBuilder sql;
		Session session;
		NativeQuery<RespondingStation> query;
		List<RespondingStation> result;
		
		sql = new StringBuilder();
		sql.append( "SELECT * " );
		sql.append( "FROM station s " );
		sql.append( "JOIN apparatus app " );
		sql.append( "ON app.station_id = s.station_id " );
		sql.append( "WHERE app.is_reserve = false AND ( ");
		
		// Adds the unit type IDs to the WHERE clause
		for ( int x = 0; x < apparatusTypeIds.size(); x++ ) {
			
			sql.append( "app.apparatus_type_id = " );
			sql.append( apparatusTypeIds.get( x ) );
			
			if ( x < apparatusTypeIds.size() - 1 ) {
				
				sql.append( " OR " );
			}
			else {
				sql.append( " ) " );
			}
		}

		session = this.hibernateUtils.getSession();
		query = session.createNativeQuery( sql.toString(), RespondingStation.class );
		result = query.getResultList();
		
		return result;
	}
	
	/**
	 * Returns all stations that have at least one apparatus of the specified
	 * type assigned to it
	 * 
	 * @param apparatusTypeId The apparatus type ID
	 * @return The list of the stations
	 */
	public List<RespondingStation> getAllStationsWithApparatusType( Integer apparatusTypeId ) {
		
		StringBuilder sql;
		Session session;
		NativeQuery<RespondingStation> query;
		List<RespondingStation> result;
		
		sql = new StringBuilder();
		sql.append( "SELECT * " );
		sql.append( "FROM station s " );
		sql.append( "JOIN apparatus app " );
		sql.append( "ON app.station_id = s.station_id " );
		sql.append( "WHERE app.apparatus_type_id = :type AND app.is_reserve = false " );

		session = this.hibernateUtils.getSession();
		query = session.createNativeQuery( sql.toString(), RespondingStation.class );
		query.setParameter( "type", apparatusTypeId );
		result = query.getResultList();
		
		return result;
	}
	
	/**
	 * Returns the nearest stations in euclidean distance to the specified
	 * location that have at least one apparatus of the specified
	 * type assigned to it
	 * 
	 * @param point The location
	 * @param stationCount The number of stations to return
	 * @param apparatusTypeId The apparatus type ID
	 * @return The list of the stations
	 */
	public List<RespondingStation> getNearestStationsWithApparatusType( Point point, Integer stationCount, Integer apparatusTypeId ) {
		
		StringBuilder sql;
		Session session;
		NativeQuery<RespondingStation> query;
		List<RespondingStation> result;
		
		sql = new StringBuilder();
		sql.append( "SELECT * " );
		sql.append( "FROM station s " );
		sql.append( "JOIN apparatus app " );
		sql.append( "ON app.station_id = s.station_id " );
		sql.append( "WHERE app.apparatus_type_id = :type AND app.is_reserve = false " );
		sql.append( "ORDER BY ST_Distance( s.location, :point ) " );
		sql.append( "LIMIT :count " );
		
		session = this.hibernateUtils.getSession();
		query = session.createNativeQuery( sql.toString(), RespondingStation.class );
		query.setParameter( "type", apparatusTypeId );
		query.setParameter( "point", point );
		query.setParameter( "count", stationCount );
		result = query.getResultList();
		
		return result;
	}
	
	/**
	 * Returns the nearest stations in euclidean distance to the specified
	 * location that have at least one apparatus of one of the specified
	 * types assigned to it
	 * 
	 * @param point The location
	 * @param stationCount The number of stations to return
	 * @param apparatusTypeIds The apparatus type IDs
	 * @return The list of the stations
	 */
	public List<RespondingStation> getNearestStationsWithApparatusType( Point point, Integer stationCount, List<Integer> apparatusTypeIds ) {
		
		StringBuilder sql;
		Session session;
		NativeQuery<RespondingStation> query;
		List<RespondingStation> result;
		
		sql = new StringBuilder();
		sql.append( "SELECT * " );
		sql.append( "FROM station s " );
		sql.append( "JOIN apparatus app " );
		sql.append( "ON app.station_id = s.station_id " );
		sql.append( "WHERE app.is_reserve = false AND ( ");
		
		// Adds the unit type IDs to the WHERE clause
		for ( int x = 0; x < apparatusTypeIds.size(); x++ ) {
			
			sql.append( "app.apparatus_type_id = " );
			sql.append( apparatusTypeIds.get( x ) );
			
			if ( x < apparatusTypeIds.size() - 1 ) {
				
				sql.append( " OR " );
			}
			else {
				sql.append( " ) " );
			}
		}
		
		sql.append( " ORDER BY ST_Distance( s.location, :point ) " );
		sql.append( "LIMIT :count " );
		
		session = this.hibernateUtils.getSession();
		query = session.createNativeQuery( sql.toString(), RespondingStation.class );
		query.setParameter( "point", point );
		query.setParameter( "count", stationCount );
		result = query.getResultList();
		
		return result;
	}
	
	/**
	 * Returns the nearest stations in euclidean distance to the specified
	 * location that have at least one apparatus of the specified category
	 * assigned to it
	 * 
	 * @param point The location
	 * @param stationCount The number of stations to return
	 * @param apparatusCategory The apparatus type IDs
	 * @return The list of the stations
	 */
	public List<RespondingStation> getNearestStationsWithApparatusCategory( Point point, Integer stationCount, String apparatusCategory ) {
		
		StringBuilder sql;
		Session session;
		NativeQuery<RespondingStation> query;
		List<RespondingStation> result;
		
		sql = new StringBuilder();
		sql.append( "SELECT * " );
		sql.append( "FROM station s " );
		sql.append( "JOIN apparatus app " );
		sql.append( "ON app.station_id = s.station_id " );
		sql.append( "JOIN apparatus_type appType " );
		sql.append( "ON app.apparatus_type_id = appType.apparatus_type_id " );
		sql.append( "WHERE appType.category = :category AND app.is_reserve = false " );
		sql.append( "ORDER BY ST_Distance( s.location, :point ) " );
		sql.append( "LIMIT :count " );
		
		session = this.hibernateUtils.getSession();
		query = session.createNativeQuery( sql.toString(), RespondingStation.class );
		query.setParameter( "category", apparatusCategory );
		query.setParameter( "point", point );
		query.setParameter( "count", stationCount );
		result = query.getResultList();
		
		return result;
	}
}
