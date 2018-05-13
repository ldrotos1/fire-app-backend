package org.fireapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@GeneratedValue
	@Column( name = "apparatus_type_id" )
	private Integer apparatusTypeId;
	
	@Column( name = "name" )
	private String name;
	
	@Column( name = "image" )
	private String image;
	
	@Column( name = "description" )
	private String description;

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
}
