package org.fireapp.dao;

import java.util.List;

import org.fireapp.model.DepartmentLite;
import org.springframework.stereotype.Repository;

/**
 * An instance of this class is used to access simplified
 * fire department objects from the database
 * 
 * @author Louis Drotos
 *
 */
@Repository( "departmentLiteDao" )
public class DepartmentLiteDao extends BaseDao<DepartmentLite> {

	/**
	 * Gets a list collection of all departments
	 */
	public List<DepartmentLite> findAllDepartments() {

		return this.query( "FROM DepartmentLite" );
	}
}
