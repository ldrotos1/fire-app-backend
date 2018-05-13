package org.fireapp.service;

import java.util.List;

import org.fireapp.dao.ApparatusDao;
import org.fireapp.dao.BasicStationDao;
import org.fireapp.dao.FullStationDao;
import org.fireapp.model.BasicStation;
import org.fireapp.model.FullStation;
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
	private BasicStationDao basicStationDao;
	
	@Autowired
	private FullStationDao fullStationDao;
	
	@Autowired
	private ApparatusDao apparatusDao;
	
	public StationService() {
		// Empty body
	}
	
	/**
	 * Returns a list of all fire stations
	 * 
	 * @return The list of fire stations
	 */
	public List<BasicStation> getAllStations() {
		
		return basicStationDao.findAllStations();
	}
	
	/**
	 * Returns the specified station record
	 * 
	 * @param id The station ID
	 * @return The station record
	 */
	public FullStation getStation( Integer id ) {
		
		FullStation station = fullStationDao.getStation( id );
		station.setApparatus( apparatusDao.getApparatusAssignedToStation( station.getDesignator() ) );
		
		return station;
	}
}
