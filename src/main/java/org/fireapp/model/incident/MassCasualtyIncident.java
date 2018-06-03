package org.fireapp.model.incident;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Represents a mass casualty incident 
 * 
 * @author Louis Drotos
 *
 */
public class MassCasualtyIncident extends Incident {

	@Min( value = 5, message = "Minimum number of casualties is 5" )
	@Max( value = 50, message = "Maximum number of casualties is 50" )
	@NotNull( message = "Must provide the number of casualties" )
	private Integer casualties;
	
	public MassCasualtyIncident() {
		// Empty body
	}

	public Integer getCasualties() {
		return casualties;
	}

	public void setCasualties(Integer casualties) {
		this.casualties = casualties;
	}
}
