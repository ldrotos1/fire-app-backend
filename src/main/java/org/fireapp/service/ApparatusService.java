package org.fireapp.service;

import java.util.List;

import org.fireapp.dao.ApparatusDao;
import org.fireapp.dao.ApparatusTypeDao;
import org.fireapp.dao.ApparatusTypeLiteDao;
import org.fireapp.dao.DepartmentDao;
import org.fireapp.dao.StationDao;
import org.fireapp.model.Apparatus;
import org.fireapp.model.ApparatusType;
import org.fireapp.model.ApparatusTypeLite;
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
	
	@Autowired
	private ApparatusTypeLiteDao apparatusTypeLiteDao;
	
	@Autowired
	private StationDao stationDao;
		
	@Autowired
	private DepartmentDao departmentDao;
	
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
	 * Returns the specified apparatus type 
	 * 
	 * @param id The apparatus type ID
	 * @return The apparatus type object
	 */
	public ApparatusType getApparatusType( Integer id ) {
		
		// Gets the apparatus type object
		ApparatusType appType = apparatusTypeDao.getApparatusType( id );
		
		// Gets and sets the map collections
		appType.setStationMap( stationDao.getStationApparatusTypeMap( id ) );
		appType.setDepartMap( departmentDao.getDeptApparatusTypeMap( id ) );
		
		return appType;
	}
	
	/**
	 * Returns a list of all fire apparatus types
	 * 
	 * @return The list of apparatus types
	 */
	public List<ApparatusTypeLite> getApparatusTypes() {
		
		return apparatusTypeLiteDao.getApparatusTypes();
	}
}
