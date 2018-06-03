package org.fireapp.model.incident;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Represents a vehicle accident incident 
 * 
 * @author Louis Drotos
 *
 */
public class VehicleAccidentIncident extends Incident {

	@Min( value = 0, message = "Minimum number of injuries is 0" )
	@Max( value = 50, message = "Maximum number of injuries is 50" )
	@NotNull( message = "Must provide the number of injuries" )
	private Integer injuries;
	
	@Min( value = 1, message = "Minimum number of 1 vehicle in accident" )
	@Max( value = 1, message = "Maximum number of 5 vehicle in accident" )
	@NotNull( message = "Must provide the number of vehicles in accident" )
	private Integer vehicles;
	
	@NotNull( message = "Hazmat must not be null" )
	private Boolean hazmat;
	
	@NotNull( message = "Entrapment must not be null" )
	private Boolean entrapment;

	public Integer getInjuries() {
		return injuries;
	}

	public void setInjuries(Integer injuries) {
		this.injuries = injuries;
	}

	public Integer getVehicles() {
		return vehicles;
	}

	public void setVehicles(Integer vehicles) {
		this.vehicles = vehicles;
	}

	public Boolean getHazmat() {
		return hazmat;
	}

	public void setHazmat(Boolean hazmat) {
		this.hazmat = hazmat;
	}

	public Boolean getEntrapment() {
		return entrapment;
	}

	public void setEntrapment(Boolean entrapment) {
		this.entrapment = entrapment;
	}
}
