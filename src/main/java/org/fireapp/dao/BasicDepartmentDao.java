package org.fireapp.dao;

import java.util.List;

import org.fireapp.model.BasicDepartment;
import org.springframework.stereotype.Repository;

/**
 * An instance of this class is used to access simplified
 * fire department objects from the database
 * 
 * @author Louis Drotos
 *
 */
@Repository( "basicDepartmentDao" )
public class BasicDepartmentDao extends BaseDao<BasicDepartment> {

	/**
	 * Gets a list collection of all departments
	 */
	public List<BasicDepartment> findAllDepartments() {

		return this.query( "FROM BasicDepartment" );
	}
}
