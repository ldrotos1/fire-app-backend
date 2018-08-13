package org.fireapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class DepartmentLite {

	@Id
	@Column( name = "department_id" )
	private Integer departmentId;
	
	@Column( name = "name" )
	private String departmentName;
	
	@Column( name = "abbreviation", table = "department" )
	private String deptAbbreviation;
	
	public DepartmentLite() {
		// Empty body
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDeptAbbreviation() {
		return deptAbbreviation;
	}

	public void setDeptAbbreviation(String deptAbbreviation) {
		this.deptAbbreviation = deptAbbreviation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departmentId == null) ? 0 : departmentId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DepartmentLite other = (DepartmentLite) obj;
		if (departmentId == null) {
			if (other.departmentId != null)
				return false;
		} else if (!departmentId.equals(other.departmentId))
			return false;
		return true;
	}

}
