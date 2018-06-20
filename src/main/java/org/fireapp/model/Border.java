package org.fireapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.Polygon;

/**
 * A model class representing a border polygon 
 * 
 * @author Louis Drotos
 *
 */
@Entity
@Table( name = "area_of_interest_boundary" )
public class Border {

	@Id
	@Column( name = "id" )
	private Integer id;
	
	@Column( name = "boundary" )
	private Polygon border;
	
	public Border() {
		// Empty body 
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Polygon getBorder() {
		return border;
	}

	public void setBorder(Polygon border) {
		this.border = border;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((border == null) ? 0 : border.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Border other = (Border) obj;
		if (border == null) {
			if (other.border != null)
				return false;
		} else if (!border.equals(other.border))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
