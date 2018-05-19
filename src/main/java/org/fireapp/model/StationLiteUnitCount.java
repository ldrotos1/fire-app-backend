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
	private Integer number;
	
	@Column( name = "station_designator" )
	private Integer designator;
	
	@Column( name = "department_id" )
	private Integer departmentId;
	
	@Column( name = "name" )
	private String name;
	
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

	public Long getUnitCount() {
		return unitCount;
	}

	public void setUnitCount(Long unitCount) {
		this.unitCount = unitCount;
	}
	
}
