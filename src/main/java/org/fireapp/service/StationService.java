package org.fireapp.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.fireapp.dao.StationLiteLocationDao;
import org.fireapp.dao.StationDao;
import org.fireapp.model.StationLiteLocation;
import org.fireapp.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class provides methods for accessing information
 * about fire stations
 * 
 * @author Louis Drotos
 *
 */
@Service
public class StationService {

	@Autowired
	private StationDao stationDao;

	@Autowired
	private StationLiteLocationDao stationLiteLocationDao;
	
	public StationService() {
		// Empty body
	}
	
	/**
	 * Returns a list of all fire stations
	 * 
	 * @return The list of fire stations
	 */
	public List<StationLiteLocation> getAllStations() {
		
		return stationLiteLocationDao.findAllStations();
	}
	
	/**
	 * Returns the specified station record
	 * 
	 * @param id The station ID
	 * @return The station record
	 */
	public Station getStation( Integer id ) {
		
		return stationDao.getStation( id );
	}
	
	/**
	 * Returns the number of apparatus assigned to the
	 * specified station
	 * 
	 * @param id The station ID
	 * @return The number of assigned apparatus
	 */
	public Long getApparatusCount( Integer id ) {
		
		return stationDao.getApparatusCount( id );
	}
	
	/**
	 * Returns a map collection of all station IDs and the number 
	 * of apparatus of the specified type assigned to each station
	 * 
	 * @param apparatusTypeId The apparatus type ID
	 * @return The map collection
	 */
	public Map<Integer,BigInteger> getStationApparatusTypeMap( Integer apparatusTypeId ) {
		
		return stationDao.getStationApparatusTypeMap( apparatusTypeId );
	}
}
