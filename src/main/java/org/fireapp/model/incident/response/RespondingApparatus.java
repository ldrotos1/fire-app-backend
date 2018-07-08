package org.fireapp.model.incident.response;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.fireapp.model.ApparatusTypeLite;
import org.fireapp.model.StationLiteName;
/**
 * Represents an fire department apparatus that is
 * responding to an emergency incident
 * 
 * @author Louis Drotos
 *
 */
@Entity
@Table( name = "apparatus" )
public class RespondingApparatus {

	@Id
	@Column( name = "apparatus_id" )
	private Integer apparatusId;
	
	@Column( name = "unit_designator", unique = true )
	private String unitDesignator;
	
	@ManyToOne( cascade = CascadeType.ALL )
	@JoinColumn( name = "station_id", foreignKey = 
		@ForeignKey( name = "fk_station" ) )
	private StationLiteName station;
	
	@ManyToOne( cascade = CascadeType.ALL )
	@JoinColumn( name = "apparatus_type_id", foreignKey = 
		@ForeignKey( name = "apparatus_apparatus_type_id_fkey" ) )
	private ApparatusTypeLite apparatusType;
	
	@Transient
	private Long travelTime;
	
	@Transient
	private Double travelDistance;
	
	public RespondingApparatus() {
		// Empty body
	}

	public Integer getApparatusId() {
		return apparatusId;
	}

	public void setApparatusId(Integer apparatusId) {
		this.apparatusId = apparatusId;
	}

	public String getUnitDesignator() {
		return unitDesignator;
	}

	public void setUnitDesignator(String unitDesignator) {
		this.unitDesignator = unitDesignator;
	}

	public StationLiteName getStation() {
		return station;
	}

	public void setStation(StationLiteName station) {
		this.station = station;
	}

	public ApparatusTypeLite getApparatusType() {
		return apparatusType;
	}

	public void setApparatusType(ApparatusTypeLite apparatusType) {
		this.apparatusType = apparatusType;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apparatusId == null) ? 0 : apparatusId.hashCode());
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
		RespondingApparatus other = (RespondingApparatus) obj;
		if (apparatusId == null) {
			if (other.apparatusId != null)
				return false;
		} else if (!apparatusId.equals(other.apparatusId))
			return false;
		return true;
	}
}
