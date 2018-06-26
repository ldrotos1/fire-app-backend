package org.fireapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

/**
* An entity class that provides a simplified representation
* of a fire station  
* 
* @author Louis Drotos
*
*/
@Entity
@Table( name = "station" )
@SecondaryTable( name = "department", foreignKey = 
	@ForeignKey( name = "fk_dept_id" ) )
public class StationLiteName {

	@Id
	@Column( name = "station_id" )
	private Integer stationId;
	
	@Column( name = "number" )
	private Integer number;
	
	@Column( name = "station_designator" )
	private Integer designator;
	
	@Column( name = "name" )
	private String name;
	
	@Column( name = "department_id" )
	private Integer deptId;
	
	@Column( name = "name", table = "department" )
	private String deptName;
	
	@Column( name = "abbreviation", table = "department" )
	private String deptAbbreviation;
	
	public StationLiteName() {
		// Empty body
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getDesignator() {
		return designator;
	}

	public void setDesignator(Integer designator) {
		this.designator = designator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
		result = prime * result + ((designator == null) ? 0 : designator.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((stationId == null) ? 0 : stationId.hashCode());
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
		StationLiteName other = (StationLiteName) obj;
		if (designator == null) {
			if (other.designator != null)
				return false;
		} else if (!designator.equals(other.designator))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (stationId == null) {
			if (other.stationId != null)
				return false;
		} else if (!stationId.equals(other.stationId))
			return false;
		return true;
	}
}
