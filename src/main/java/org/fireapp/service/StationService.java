package org.fireapp.service;

import java.util.List;

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
		
		Station station = stationDao.getStation( id );

		return station;
	}
}
