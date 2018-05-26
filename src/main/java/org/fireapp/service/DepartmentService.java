package org.fireapp.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.fireapp.dao.DepartmentDao;
import org.fireapp.dao.DepartmentLiteDao;
import org.fireapp.model.Department;
import org.fireapp.model.DepartmentLite;
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
		
		return departmentDao.getDepartment( id );
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
