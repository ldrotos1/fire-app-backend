package org.fireapp.model.incident;

/**
 * Represents a structure fire incident 
 * 
 * @author Louis Drotos
 *
 */
public class StructureFireIncident extends Incident {

	private Integer alarmNumber;
	private Boolean hydrantAccess;
	
	public StructureFireIncident() {
		// Empty body
	}

	public Integer getAlarmNumber() {
		return alarmNumber;
	}

	public void setAlarmNumber(Integer alarmNumber) {
		this.alarmNumber = alarmNumber;
	}

	public Boolean getHydrantAccess() {
		return hydrantAccess;
	}

	public void setHydrantAccess(Boolean hydrantAccess) {
		this.hydrantAccess = hydrantAccess;
	}
}
