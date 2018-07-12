package org.fireapp.model.incident.response;

import java.util.List;

import org.fireapp.model.incident.Incident;

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
	private Incident incident;
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

	public Incident getIncident() {
		return incident;
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
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
