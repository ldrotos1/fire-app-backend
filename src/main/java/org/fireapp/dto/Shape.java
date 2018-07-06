package org.fireapp.dto;

import java.util.List;

/**
 * Represents a route shape from a MapQuest Route 
 * request response
 * 
 * @author Louis Drotos
 *
 */
public class Shape {

	private List<Double> shapePoints;
	
	public Shape() {
		// Empty body
	}

	public List<Double> getShapePoints() {
		return shapePoints;
	}

	public void setShapePoints(List<Double> shapePoints) {
		this.shapePoints = shapePoints;
	}
}
