package org.fireapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A response object representing the server response from 
 * the MapQuest route service
 * 
 * @author Louis Drotos
 *
 */
@JsonIgnoreProperties( ignoreUnknown = true )
public class RouteResponse {

	private Route route;
	
	private Integer stationId;
	
	public RouteResponse() {
		// Empty body
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
}
