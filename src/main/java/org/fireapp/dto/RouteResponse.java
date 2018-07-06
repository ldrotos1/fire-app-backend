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

	private Shape shape;
	
	public RouteResponse() {
		// Empty body
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}
}
