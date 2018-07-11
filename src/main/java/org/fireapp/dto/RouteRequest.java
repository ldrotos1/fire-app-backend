package org.fireapp.dto;

import java.util.List;

/**
 * A request object representing the client request for 
 * the route service
 * 
 * @author Louis Drotos
 *
 */
public class RouteRequest {

	private Integer stationId;
	
	private List<Location> locations;
	
	private Options options = new Options();
	
	public RouteRequest() {
		// Empty body
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}
}
