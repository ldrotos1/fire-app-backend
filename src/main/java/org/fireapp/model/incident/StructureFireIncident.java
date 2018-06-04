package org.fireapp.model.incident;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Represents a structure fire incident 
 * 
 * @author Louis Drotos
 *
 */
@Entity
public class StructureFireIncident extends Incident {

	@Min( value = 1, message = "Minimum alarm number is 1" )
	@Max( value = 5, message = "Maximum alarm number is 5" )
	@NotNull( message = "Must provide an alarm number" )
	private Integer alarmNumber;
	
	@NotNull( message = "Must provide a value for hydrant access" )
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
