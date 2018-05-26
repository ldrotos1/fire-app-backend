package org.fireapp.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.fireapp.dao.DepartmentDao;
import org.fireapp.dao.DepartmentLiteDao;
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
	private StationService stationService;
	
	@Autowired
	private ApparatusService apparatusService;
	
	@Autowired
	private DepartmentLiteDao departmentLiteDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
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
		for( StationLiteUnitCount station : dept.getStations() ) {
			
			station.setUnitCount( stationService.getApparatusCount( station.getStationId() ) );
		}
		
		// Gets and sets the apparatus category count map 
		dept.setUnitTypeMap( apparatusService.getCategoryCountMap( id ) );
		
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
		
		return departmentDao.getDeptApparatusTypeMap( apparatusTypeId );
	}
}
