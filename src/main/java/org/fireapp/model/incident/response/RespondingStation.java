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

@Entity
@Table( name = "station" )
public class RespondingStation {

	@Id
	@Column( name = "station_id" )
	private Integer stationId;
	
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinColumn( name = "station_id" )
	private List<RespondingApparatus> apparatus;
	
	@Transient
	private Integer travelTime;
	
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

	public List<RespondingApparatus> getApparatus() {
		return apparatus;
	}

	public void setApparatus(List<RespondingApparatus> apparatus) {
		this.apparatus = apparatus;
	}

	public Integer getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(Integer travelTime) {
		this.travelTime = travelTime;
	}

	public Double getTravelDistance() {
		return travelDistance;
	}

	public void setTravelDistance(Double travelDistance) {
		this.travelDistance = travelDistance;
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
