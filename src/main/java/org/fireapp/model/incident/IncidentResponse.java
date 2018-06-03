package org.fireapp.model.incident;

import java.util.List;

/**
 * Represents an emergency incident response. Includes basic
 * information about the incident, the apparatus responding to
 * the incident, and the travel routes the apparatus will take
 * to the incident  
 * 
 * @author Louis Drotos
 *
 */
public class IncidentResponse {

	private String incidentTitle;
	private Integer numOfStations;
	private Integer numOfUnits;
	private Integer firstArrivalTime;
	private Integer lastArrivalTime;
	private List<ResponseRoute> reponseRoutes;
	private List<RespondingApparatus> respondingApparatus;
	
	public IncidentResponse() {
		//Empty body
	}

	public String getIncidentTitle() {
		return incidentTitle;
	}

	public void setIncidentTitle(String incidentTitle) {
		this.incidentTitle = incidentTitle;
	}

	public Integer getNumOfStations() {
		return numOfStations;
	}

	public void setNumOfStations(Integer numOfStations) {
		this.numOfStations = numOfStations;
	}

	public Integer getNumOfUnits() {
		return numOfUnits;
	}

	public void setNumOfUnits(Integer numOfUnits) {
		this.numOfUnits = numOfUnits;
	}

	public Integer getFirstArrivalTime() {
		return firstArrivalTime;
	}

	public void setFirstArrivalTime(Integer firstArrivalTime) {
		this.firstArrivalTime = firstArrivalTime;
	}

	public Integer getLastArrivalTime() {
		return lastArrivalTime;
	}

	public void setLastArrivalTime(Integer lastArrivalTime) {
		this.lastArrivalTime = lastArrivalTime;
	}

	public List<ResponseRoute> getReponseRoutes() {
		return reponseRoutes;
	}

	public void setReponseRoutes(List<ResponseRoute> reponseRoutes) {
		this.reponseRoutes = reponseRoutes;
	}

	public List<RespondingApparatus> getRespondingApparatus() {
		return respondingApparatus;
	}

	public void setRespondingApparatus(List<RespondingApparatus> respondingApparatus) {
		this.respondingApparatus = respondingApparatus;
	}
}
