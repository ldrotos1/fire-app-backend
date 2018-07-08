package org.fireapp.model.incident.response;

import org.fireapp.dto.Coordinate;

/**
 * Represents a geographic location that can be submitted
 * to the MapQuest Directions API
 * 
 * @author Louis Drotos
 *
 */
public class Location {

	private Coordinate latLng;
	
	public Location() {
		// Empty body 
	}

	public Location( Coordinate latLng ) {
		this.latLng = latLng;
	}
	
	public Coordinate getLatLng() {
		return latLng;
	}

	public void setLatLng(Coordinate latLng) {
		this.latLng = latLng;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((latLng == null) ? 0 : latLng.hashCode());
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
		Location other = (Location) obj;
		if (latLng == null) {
			if (other.latLng != null)
				return false;
		} else if (!latLng.equals(other.latLng))
			return false;
		return true;
	}
}
