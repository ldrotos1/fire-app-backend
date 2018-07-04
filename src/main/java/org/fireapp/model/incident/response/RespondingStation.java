package org.fireapp.model.incident.response;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

@Entity
@Table( name = "station" )
public class RespondingStation implements Comparable<RespondingStation> {

	@Id
	@Column( name = "station_id" )
	private Integer stationId;
	
	@Column(name = "lat")
	private Double lat;
	
	@Column(name = "lon")
	private Double lon;
	
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinColumn( name = "station_id" )
	@Where( clause = "apparatust1_.include_in_simulator = 1 and is_reserve = false" )
	private List<RespondingApparatus> apparatus;
	
	@Transient
	private Long travelTime;
	
	@Transient
	private Double travelDistance;
	
	public RespondingStation() {
		// Empty body
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
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

	public List<RespondingApparatus> getApparatus() {
		return apparatus;
	}

	public void setApparatus(List<RespondingApparatus> apparatus) {
		this.apparatus = apparatus;
	}

	public Long getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(Long travelTime) {
		this.travelTime = travelTime;
	}

	public Double getTravelDistance() {
		return travelDistance;
	}

	public void setTravelDistance(Double travelDistance) {
		this.travelDistance = travelDistance;
	}

	@Override
	public int compareTo( RespondingStation otherStation ) {
		
		if ( this.travelTime < otherStation.travelTime ) {
			
			return -1;
		}
		else if ( this.travelTime > otherStation.travelTime ) {
			
			return 1;
		}
		
		return 0;
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
		RespondingStation other = (RespondingStation) obj;
		if (stationId == null) {
			if (other.stationId != null)
				return false;
		} else if (!stationId.equals(other.stationId))
			return false;
		return true;
	}
}
