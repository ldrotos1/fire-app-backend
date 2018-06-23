package org.fireapp.model.incident;

/**
 * An instance of this class represents the incident response requirements to
 * an emergency incident. Requirements are express in terms of number and type 
 * of emergency apparatus that should respond to an emergency incident 
 * 
 * @author Louis Drotos
 *
 */
public class IncidentRequirements {
	
	private int engineCount;
	private int truckOrTowerCount;
	private int aerialCount;
	private int rescueCount;
	private int tankerCount;
	private int foamCount;
	private int medicCount;
	private int battalionChiefCount;
	private int emsSupervisorCount;
	private int mobileCommandCount;
	private int massCasSupCount;
	private int boatCount;
	private int hazmatCount;

	public IncidentRequirements() {
		// Empty body
	}
	
	public int getEngineCount() {
		return engineCount;
	}

	public void setEngineCount(int engineCount) {
		this.engineCount = engineCount;
	}
	
	public int getTruckOrTowerCount() {
		return truckOrTowerCount;
	}

	public void setTruckOrTowerCount(int truckOrTowerCount) {
		this.truckOrTowerCount = truckOrTowerCount;
	}

	public int getAerialCount() {
		return aerialCount;
	}

	public void setAerialCount(int aerialCount) {
		this.aerialCount = aerialCount;
	}

	public int getRescueCount() {
		return rescueCount;
	}

	public void setRescueCount(int rescueCount) {
		this.rescueCount = rescueCount;
	}

	public int getTankerCount() {
		return tankerCount;
	}

	public void setTankerCount(int tankerCount) {
		this.tankerCount = tankerCount;
	}

	public int getFoamCount() {
		return foamCount;
	}

	public void setFoamCount(int foamCount) {
		this.foamCount = foamCount;
	}

	public int getMedicCount() {
		return medicCount;
	}

	public void setMedicCount(int medicCount) {
		this.medicCount = medicCount;
	}

	public int getBattalionChiefCount() {
		return battalionChiefCount;
	}

	public void setBattalionChiefCount(int battalionChiefCount) {
		this.battalionChiefCount = battalionChiefCount;
	}

	public int getEmsSupervisorCount() {
		return emsSupervisorCount;
	}

	public void setEmsSupervisorCount(int emsSupervisorCount) {
		this.emsSupervisorCount = emsSupervisorCount;
	}

	public int getMobileCommandCount() {
		return mobileCommandCount;
	}

	public void setMobileCommandCount(int mobileCommandCount) {
		this.mobileCommandCount = mobileCommandCount;
	}

	public int getMassCasSupCount() {
		return massCasSupCount;
	}

	public void setMassCasSupCount(int massCasSupCount) {
		this.massCasSupCount = massCasSupCount;
	}

	public int getBoatCount() {
		return boatCount;
	}

	public void setBoatCount(int boatCount) {
		this.boatCount = boatCount;
	}

	public int getHazmatCount() {
		return hazmatCount;
	}

	public void setHazmatCount(int hazmatCount) {
		this.hazmatCount = hazmatCount;
	}

	/**
	 * Returns the total number of required apparatus of all types
	 * 
	 * @return The total number
	 */
	public int getTotalApparatusCount() {
		
		return this.engineCount + this.truckOrTowerCount +  this.aerialCount + 
				this.rescueCount + this.tankerCount + this.foamCount + this.medicCount + 
				this.battalionChiefCount + this.emsSupervisorCount + this.mobileCommandCount + 
				this.massCasSupCount + this.boatCount + this.hazmatCount;
	}
}
