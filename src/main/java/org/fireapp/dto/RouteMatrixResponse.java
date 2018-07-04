package org.fireapp.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A response object representing the server response from 
 * the route matrix service 
 * 
 * @author Louis Drotos
 *
 */
@JsonIgnoreProperties( ignoreUnknown = true )
public class RouteMatrixResponse {

	private List<Double> distance;
	private List<Long> time;
	
	public RouteMatrixResponse() {
		// Empty body
	}

	public List<Double> getDistance() {
		return distance;
	}

	public void setDistance(List<Double> distance) {
		this.distance = distance;
	}

	public List<Long> getTime() {
		return time;
	}

	public void setTime(List<Long> time) {
		this.time = time;
	}
}
