package org.fireapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * An entity class that provides a simplified representation 
 * of a fire station that includes it's geographic location 
 * 
 * @author Louis Drotos
 *
 */
@Entity
@Table( name = "station" )
public class StationLite {

	@Id
	@Column( name = "station_id" )
	private Integer stationId;
	
	@Column( name = "number" )
	private Integer stationNumber;
	
	@Column( name = "station_designator" )
	private Integer stationDesignator;
	
	@Column( name = "name" )
	private String stationName;
	
	@Column(name = "lat")
	private Double lat;
	
	@Column(name = "lon")
	private Double lon;
	
	@JsonUnwrapped
	@ManyToOne( cascade = CascadeType.ALL )
	@JoinColumn( name = "department_id", foreignKey = 
		@ForeignKey( name = "fk_dept_id" ) )
	private DepartmentLite department;
	
	public StationLite() {
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

	public Integer getStationDesignator() {
		return stationDesignator;
	}

	public void setStationDesignator(Integer stationDesignator) {
		this.stationDesignator = stationDesignator;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
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
		StationLite other = (StationLite) obj;
		if (stationId == null) {
			if (other.stationId != null)
				return false;
		} else if (!stationId.equals(other.stationId))
			return false;
		return true;
	}
}
