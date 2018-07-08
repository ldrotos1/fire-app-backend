package org.fireapp.dto;

import java.util.List;

import org.fireapp.model.incident.response.Location;

/**
 * A request object representing the client request for 
 * the route matrix service
 * 
 * @author Louis Drotos
 *
 */
public class RouteMatrixRequest {

	private List<Location> locations;
	
	public RouteMatrixRequest() {
		// Empty body 
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
}
