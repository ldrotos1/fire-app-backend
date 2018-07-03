package org.fireapp.model.incident;

/**
 * Represents a mass casualty incident 
 * 
 * @author Louis Drotos
 *
 */
public class MassCasualtyIncident extends Incident {

	private Integer casualties;
	private Integer massCasAlarmNum;
	
	public MassCasualtyIncident() {
		// Empty body
	}

	public Integer getCasualties() {
		return casualties;
	}

	public void setCasualties(Integer casualties) {
		this.casualties = casualties;
	}

	public Integer getMassCasAlarmNum() {
		return massCasAlarmNum;
	}

	public void setMassCasAlarmNum(Integer massCasAlarmNum) {
		this.massCasAlarmNum = massCasAlarmNum;
	}
}
