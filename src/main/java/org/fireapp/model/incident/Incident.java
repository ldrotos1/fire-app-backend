package org.fireapp.model.incident;

/**
 * Base class that represents an generic emergency incident
 * 
 * @author Louis Drotos
 *
 */
public class Incident {

	protected Double latitude;
	protected Double longitude;
	
	public Incident() {
		//Empty body
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}
