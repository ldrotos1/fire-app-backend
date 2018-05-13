package org.fireapp.service;

import java.util.List;

import org.fireapp.dao.ApparatusDao;
import org.fireapp.dao.ApparatusTypeDao;
import org.fireapp.model.Apparatus;
import org.fireapp.model.ApparatusType;
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
	
	@Autowired
	private ApparatusTypeDao apparatusTypeDao;
	
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
	
	/**
	 * Returns a list of all fire apparatus types
	 * 
	 * @return The list of apparatus types
	 */
	public List<ApparatusType> getApparatusTypes() {
		
		return apparatusTypeDao.getApparatusTypes();
	}
}
