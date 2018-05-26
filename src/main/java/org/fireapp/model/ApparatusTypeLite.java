package org.fireapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * An entity class that provides a simplified representation of 
 * a apparatus type
 * 
 * @author Louis Drotos
 *
 */
@Entity
@Table( name = "apparatus_type" )
public class ApparatusTypeLite {

	@Id
	@Column( name = "apparatus_type_id" )
	private Integer apparatusTypeId;
	
	@Column( name = "name" )
	private String name;
	
	public ApparatusTypeLite() {
		// Empty body
	}

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
		ApparatusTypeLite other = (ApparatusTypeLite) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
