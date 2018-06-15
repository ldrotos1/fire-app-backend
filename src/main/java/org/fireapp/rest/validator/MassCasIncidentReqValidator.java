package org.fireapp.rest.validator;

import org.fireapp.model.incident.MassCasualtyIncident;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * This class is used to validate request objects for the 
 * mass casualty response simulator REST service
 * 
 * @author Louis Drotos
 *
 */
@Component
public class MassCasIncidentReqValidator extends BaseIncidentReqValidator implements Validator {

	@Override
	public boolean supports( Class<?> val ) {
		
		return MassCasualtyIncident.class.equals( val );
	}
	
	@Override
	public void validate( Object val, Errors err ) {
		
		MassCasualtyIncident incident = ( MassCasualtyIncident ) val;
		
		Integer casualties = incident.getCasualties();
		
		// Checks for invalid nulls
		this.checkForInvalidNull( casualties, "casualties", err );
		
		// Validates location
		this.validateCoordinates( val, err );
		
		// Validates casualties
		if ( casualties != null ) {
			
			if ( casualties < 5 ) {
				
				err.rejectValue( "casualties", "Below minimum value" );
			}
			else if ( casualties > 100 ) {
				
				err.rejectValue( "casualties", "Above maximum value" );
			}
		}
	}
}
