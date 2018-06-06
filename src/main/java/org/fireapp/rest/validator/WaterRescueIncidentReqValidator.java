package org.fireapp.rest.validator;

import org.fireapp.model.incident.WaterRescueIncident;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * This class is used to validate request objects for the 
 * water rescue response simulator REST service
 * 
 * @author Louis Drotos
 *
 */
@Component
public class WaterRescueIncidentReqValidator extends BaseIncidentReqValidator implements Validator {

	@Override
	public boolean supports( Class<?> val ) {
		
		return WaterRescueIncident.class.equals( val );
	}

	@Override
	public void validate( Object val, Errors err ) {
		
		// Validates location
		this.validateCoordinates( val, err );
	}
}
