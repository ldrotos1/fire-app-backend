package org.fireapp.model.station;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class BasicStation {

	@Id
	@GeneratedValue
	@Column( name = "station_id" )
	private Integer stationId;
	
	@Column( name = "number" )
	private Integer number;
	
	@Column( name = "station_designator" )
	private Integer designator;
	
	@Column( name = "name" )
	private String name;
	
	@Column(name = "lat")
	private Double lat;
	
	@Column(name = "lon")
	private Double lon;
	
	public BasicStation() {
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

	public void setDesignator(Integer designator) {
		this.designator = designator;
	}
	
	public Integer getDesignator() {
		return designator;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
