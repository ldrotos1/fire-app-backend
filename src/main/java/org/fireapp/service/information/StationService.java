package org.fireapp.service.information;

import java.util.List;

import org.fireapp.dao.BasicStationDao;
import org.fireapp.model.station.BasicStation;
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
	private BasicStationDao stationDao;
	
	public StationService() {
		// Empty body
	}
	
	/**
	 * Returns a list of all fire stations
	 * 
	 * @return The list of fire stations
	 * @throws Exception 
	 */
	public List<BasicStation> getAllStations() {
		
		return stationDao.findAllStations();
	}
}
