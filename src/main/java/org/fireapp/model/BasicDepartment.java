package org.fireapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * An entity class that provides a simplified representation 
 * of a fire department
 * 
 * @author Louis Drotos
 *
 */
@Entity
@Table( name = "department" )
public class BasicDepartment {

	@Id
	@GeneratedValue
	@Column( name = "department_id" )
	private Integer departmentId;
	
	@Column( name = "name" )
	private String name;
	
	public BasicDepartment() {
		// Empty body
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
