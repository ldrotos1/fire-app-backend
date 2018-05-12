package org.fireapp.service.information;

import java.util.List;

import org.fireapp.dao.ApparatusDao;
import org.fireapp.model.apparatus.Apparatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class provides methods for accessing information
 * about fire apparatus
 * 
 * @author Louis Drotos
 *
 */
@Service
public class ApparatusService {

	@Autowired
	private ApparatusDao apparatusDao;
	
	/**
	 * Returns a list of all fire apparatus assigned to
	 * the specified station
	 * 
	 * @param stationDesignator The station designator
	 * @return The list of apparatus
	 */
	public List<Apparatus> getApparatusAssignedToStation( Integer stationDesignator ) {
		
		return apparatusDao.getApparatusAssignedToStation( stationDesignator );
	}
}
