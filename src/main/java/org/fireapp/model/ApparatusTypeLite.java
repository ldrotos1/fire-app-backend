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
}
