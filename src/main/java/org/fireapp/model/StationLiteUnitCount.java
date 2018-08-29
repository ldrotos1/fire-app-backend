package org.fireapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * An entity class that provides a simplified representation 
 * of a fire station that includes the number of apparatus 
 * assigned to it. 
 * 
 * @author Louis Drotos
 *
 */
@Entity
@Table( name = "station" )
public class StationLiteUnitCount {

	@Id
	@Column( name = "station_id" )
	private Integer stationId;
	
	@Column( name = "number" )
	private Integer stationNumber;
	
	@Column( name = "station_designator" )
	private Integer stationDesignator;
	
	@Column( name = "department_id" )
	private Integer departmentId;
	
	@Column( name = "name" )
	private String stationName;
	
	@Transient
	private Long unitCount;
	
	public StationLiteUnitCount() {
		//Empty body
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

	public Integer getStationDesignator() {
		return stationDesignator;
	}

	public void setStationDesignator(Integer stationDesignator) {
		this.stationDesignator = stationDesignator;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public Long getUnitCount() {
		return unitCount;
	}

	public void setUnitCount(Long unitCount) {
		this.unitCount = unitCount;
	}

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stationDesignator == null) ? 0 : stationDesignator.hashCode());
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
		StationLiteUnitCount other = (StationLiteUnitCount) obj;
		if (stationDesignator == null) {
			if (other.stationDesignator != null)
				return false;
		} else if (!stationDesignator.equals(other.stationDesignator))
			return false;
		return true;
	}
}
