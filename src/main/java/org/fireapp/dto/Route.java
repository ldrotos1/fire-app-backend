package org.fireapp.dto;

import java.util.List;

/**
 * Represents a route from a MapQuest Route request response
 * 
 * @author Louis Drotos
 *
 */
public class Route {

	private Shape shape;
	
	private List<Location> locations;
	
	public Route() {
		// Empty body
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
}
