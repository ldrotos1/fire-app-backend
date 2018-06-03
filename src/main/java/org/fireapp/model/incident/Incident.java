package org.fireapp.model.incident;

import javax.validation.constraints.NotNull;


/**
 * Base class that represents an generic emergency incident
 * 
 * @author Louis Drotos
 *
 */
public class Incident {

	@NotNull( message = "Must provide a latitude" )
	protected Double latitude;
	
	@NotNull( message = "Must provide a longitude" )
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
