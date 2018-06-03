package org.fireapp.model.incident;

import java.util.List;

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
	private List<Location> waypoints;
	
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

	public List<Location> getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(List<Location> waypoints) {
		this.waypoints = waypoints;
	}
}
