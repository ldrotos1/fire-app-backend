package org.fireapp.dto;

import java.util.List;

import org.fireapp.model.incident.response.Location;

/**
 * A request object representing the client request for 
 * the route service
 * 
 * @author Louis Drotos
 *
 */
public class RouteRequest {

	private List<Location> locations;
	
	public RouteRequest() {
		// Empty body
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
}
