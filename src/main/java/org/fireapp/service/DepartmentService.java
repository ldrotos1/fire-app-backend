package org.fireapp.service;

import java.util.List;

import org.fireapp.dao.BasicDepartmentDao;
import org.fireapp.model.BasicDepartment;
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
	private BasicDepartmentDao basicDepartmentDao;
	
	public DepartmentService() {
		// Empty body
	}
	
	/**
	 * Returns a list of simplified fire department objects representing
	 * all departments
	 * 
	 * @return The list of all departments
	 */
	public List<BasicDepartment> getAllDeparments() {
		
		return basicDepartmentDao.findAllDepartments();
	}
}
