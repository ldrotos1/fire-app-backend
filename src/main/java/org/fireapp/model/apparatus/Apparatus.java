package org.fireapp.model.apparatus;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.ForeignKey;

/**
 * An entity class that provides a representation of a fire
 * service apparatus vehicle
 * 
 * @author Louis Drotos
 *
 */
@Entity
@Table( name = "apparatus" )
public class Apparatus {

	@Id
	@GeneratedValue
	@Column( name = "apparatus_id" )
	private Integer apparatusId;
	
	@Column( name = "unit_designator" )
	private String unitDesignator;
	
	@Column( name = "station_designator" )
	private Integer stationDesignator;
	
	@Column( name = "is_reserve" )
	private Boolean isReserve;
	
	@ManyToOne( cascade = CascadeType.ALL )
	@JoinColumn( name = "apparatus_type_id", foreignKey = 
		@ForeignKey( name = "apparatus_apparatus_type_id_fkey" ) )
	private ApparatusType apparatusType;
	
	public Apparatus() {
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

	public Integer getStation() {
		return stationDesignator;
	}

	public void setStation(Integer stationDesignator) {
		this.stationDesignator = stationDesignator;
	}

	public Boolean getIsReserve() {
		return isReserve;
	}

	public void setIsReserve(Boolean isReserve) {
		this.isReserve = isReserve;
	}

	public Integer getStationDesignator() {
		return stationDesignator;
	}

	public void setStationDesignator(Integer stationDesignator) {
		this.stationDesignator = stationDesignator;
	}

	public ApparatusType getApparatusType() {
		return apparatusType;
	}

	public void setApparatusType(ApparatusType apparatusType) {
		this.apparatusType = apparatusType;
	}
}
