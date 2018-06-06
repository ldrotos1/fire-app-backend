package org.fireapp.rest.validator;

import org.fireapp.model.incident.MedicalEmergencyIncident;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * This class is used to validate request objects for the 
 * medical emergency response simulator REST service
 * 
 * @author Louis Drotos
 *
 */
@Component
public class MedEmergencyIncidentReqValidator extends BaseIncidentReqValidator implements Validator {

	@Override
	public boolean supports( Class<?> val ) {
		
		return MedicalEmergencyIncident.class.equals( val );
	}

	@Override
	public void validate( Object val, Errors err ) {
		
		// Validates location
		this.validateCoordinates( val, err );
	}
}
