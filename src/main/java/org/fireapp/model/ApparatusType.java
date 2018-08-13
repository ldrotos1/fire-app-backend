package org.fireapp.model;

import java.math.BigInteger;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * An entity class that provides a representation of 
 * a apparatus type
 * 
 * @author Louis Drotos
 *
 */
@JsonInclude( JsonInclude.Include.NON_NULL )
@Entity
@Table( name = "apparatus_type" )
public class ApparatusType {

	@Id
	@Column( name = "apparatus_type_id" )
	private Integer apparatusTypeId;
	
	@Column( name = "name" )
	private String typeName;
	
	@Column( name = "category" )
	private String category;
	
	@Column( name = "image" )
	private String image;
	
	@Column( name = "description" )
	private String description;

	@Transient
	private Map<Integer,BigInteger> departMap;
	
	@Transient
	private Map<Integer,BigInteger> stationMap;
	
	public Integer getApparatusTypeId() {
		return apparatusTypeId;
	}

	public void setApparatusTypeId(Integer apparatusTypeId) {
		this.apparatusTypeId = apparatusTypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<Integer, BigInteger> getDepartMap() {
		return departMap;
	}

	public void setDepartMap(Map<Integer, BigInteger> departMap) {
		this.departMap = departMap;
	}

	public Map<Integer, BigInteger> getStationMap() {
		return stationMap;
	}

	public void setStationMap(Map<Integer, BigInteger> stationMap) {
		this.stationMap = stationMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apparatusTypeId == null) ? 0 : apparatusTypeId.hashCode());
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
		ApparatusType other = (ApparatusType) obj;
		if (apparatusTypeId == null) {
			if (other.apparatusTypeId != null)
				return false;
		} else if (!apparatusTypeId.equals(other.apparatusTypeId))
			return false;
		return true;
	}
}
