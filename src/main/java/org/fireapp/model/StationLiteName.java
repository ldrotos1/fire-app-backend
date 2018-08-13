package org.fireapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class StationLiteName {

	@Id
	@Column( name = "station_id" )
	private Integer stationId;
	
	@Column( name = "number" )
	private Integer stationNumber;
	
	@Column( name = "station_designator" )
	private Integer designator;
	
	@Column( name = "name" )
	private String name;
	
	@ManyToOne( cascade = CascadeType.ALL )
	@JoinColumn( name = "department_id", foreignKey = 
		@ForeignKey( name = "fk_dept_id" ) )
	private DepartmentLite department;
	
	public StationLiteName() {
		// Empty body
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public Integer getStationNumber() {
		return stationNumber;
	}

	public void setStationNumber(Integer stationNumber) {
		this.stationNumber = stationNumber;
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

	public DepartmentLite getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentLite department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (stationId == null) {
			if (other.stationId != null)
				return false;
		} else if (!stationId.equals(other.stationId))
			return false;
		return true;
	}
}
