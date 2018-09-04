package org.fireapp.service;

import java.util.List;

import org.fireapp.dao.ApparatusDao;
import org.fireapp.dao.DepartmentDao;
import org.fireapp.dao.DepartmentLiteDao;
import org.fireapp.dao.StationDao;
import org.fireapp.model.Department;
import org.fireapp.model.DepartmentLite;
import org.fireapp.model.StationLiteUnitCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This service is used for accessing objects representing
 * fire departments
 * 
 * @author Louis Drotos
 *
 */
@Service
public class DepartmentService {

	@Autowired
	private DepartmentLiteDao departmentLiteDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private StationDao stationDao;
	
	@Autowired
	private ApparatusDao apparatusDao;
	
	public DepartmentService() {
		// Empty body
	}
	
	/**
	 * Returns a list of simplified fire department objects representing
	 * all departments
	 * 
	 * @return The list of all departments
	 */
	public List<DepartmentLite> getAllDeparments() {
		
		return departmentLiteDao.findAllDepartments();
	}
	
	/**
	 * Returns an object representing the specified fire department
	 * 
	 * @param id The department ID
	 * @return The department information
	 */
	public Department getDepartmentInfo( Integer id ) {
		
		// Gets the department object
		Department dept = departmentDao.getDepartment( id );
		
		// Gets the apparatus count for each station
		Long totalUnitCount = (long) 0;
		for( StationLiteUnitCount station : dept.getStations() ) {
			
			Long unitCount = stationDao.getApparatusCount( station.getStationId() );
			station.setUnitCount( unitCount );
			
			totalUnitCount = totalUnitCount + unitCount;
		}
		
		// Sets the total apparatus count for the apartment
		dept.setUnitCount( totalUnitCount );
		
		// Gets and sets the apparatus category count map 
		dept.setUnitTypeMap( apparatusDao.getCategoryCountMap( id ) );
		
		return dept;
	}
}
