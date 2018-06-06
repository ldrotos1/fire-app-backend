package org.fireapp.model.incident;

/**
 * Represents a vehicle accident incident 
 * 
 * @author Louis Drotos
 *
 */
public class VehicleAccidentIncident extends Incident {

	private Integer injuries;
	private Integer vehicles;
	private Boolean hazmat;
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
