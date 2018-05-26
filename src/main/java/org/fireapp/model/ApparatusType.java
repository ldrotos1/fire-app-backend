package org.fireapp.model;

import java.math.BigInteger;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * An entity class that provides a representation of 
 * a apparatus type
 * 
 * @author Louis Drotos
 *
 */
@Entity
@Table( name = "apparatus_type" )
public class ApparatusType {

	@Id
	@Column( name = "apparatus_type_id" )
	private Integer apparatusTypeId;
	
	@Column( name = "name" )
	private String name;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
