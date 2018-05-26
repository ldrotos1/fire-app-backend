package org.fireapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * An entity class that provides a simplified representation 
 * of a fire station that includes it's geographic location 
 * 
 * @author Louis Drotos
 *
 */
@Entity
@Table( name = "station" )
public class StationLiteLocation {

	@Id
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
	
	public StationLiteLocation() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((designator == null) ? 0 : designator.hashCode());
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
		StationLiteLocation other = (StationLiteLocation) obj;
		if (designator == null) {
			if (other.designator != null)
				return false;
		} else if (!designator.equals(other.designator))
			return false;
		return true;
	}
}
