package org.fireapp.model.incident.response;

import java.util.List;

import org.fireapp.dto.Coordinate;

/**
 * Represents a route from a fire station to an emergency
 * incident that responding apparatus will traverse
 * 
 * @author Louis Drotos
 *
 */
public class ResponseRoute {

	private Integer stationId;
	private List<Integer> apparatusIds;
	private List<Coordinate> waypoints;
	
	public ResponseRoute() {
		// Empty body
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public List<Integer> getApparatusIds() {
		return apparatusIds;
	}

	public void setApparatusIds(List<Integer> apparatusIds) {
		this.apparatusIds = apparatusIds;
	}

	public List<Coordinate> getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(List<Coordinate> waypoints) {
		this.waypoints = waypoints;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stationId == null) ? 0 : stationId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponseRoute other = (ResponseRoute) obj;
		if (stationId == null) {
			if (other.stationId != null)
				return false;
		} else if (!stationId.equals(other.stationId))
			return false;
		return true;
	}
}
