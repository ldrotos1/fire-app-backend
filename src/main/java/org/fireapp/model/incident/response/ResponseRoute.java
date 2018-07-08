package org.fireapp.model.incident.response;

import java.util.List;

import org.fireapp.dto.Coordinate;
import org.fireapp.dto.Location;

/**
 * Represents a route from a fire station to an emergency
 * incident that responding apparatus will traverse
 * 
 * @author Louis Drotos
 *
 */
public class ResponseRoute {

	private String stationId;
	private List<String> apparatusIds;
	private List<Coordinate> waypoints;
	
	public ResponseRoute() {
		// Empty body
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public List<String> getApparatusIds() {
		return apparatusIds;
	}

	public void setApparatusIds(List<String> apparatusIds) {
		this.apparatusIds = apparatusIds;
	}

	public List<Coordinate> getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(List<Coordinate> waypoints) {
		this.waypoints = waypoints;
	}
}
