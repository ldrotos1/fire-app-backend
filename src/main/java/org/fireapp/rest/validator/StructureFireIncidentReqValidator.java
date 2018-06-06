package org.fireapp.rest.validator;

import org.fireapp.model.incident.StructureFireIncident;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * This class is used to validate request objects for the 
 * structure fire response simulator REST service
 * 
 * @author Louis Drotos
 *
 */
@Component
public class StructureFireIncidentReqValidator extends BaseIncidentReqValidator implements Validator {

	@Override
	public boolean supports( Class<?> val ) {
		
		return StructureFireIncident.class.equals( val );
	}

	@Override
	public void validate( Object val, Errors err ) {
		
		StructureFireIncident incident = ( StructureFireIncident ) val;
		
		Integer alarmNumber = incident.getAlarmNumber();
		Boolean hydrantAccess = incident.getHydrantAccess();
		
		// Checks for invalid nulls
		this.checkForInvalidNull( alarmNumber, "alarmNumber", err );
		this.checkForInvalidNull( hydrantAccess, "hydrantAccess", err );
		
		// Validates location
		this.validateCoordinates( val, err );
		
		// Validates the alarm number
		if ( alarmNumber != null ) {
			
			if ( alarmNumber < 1 ) {
				
				err.rejectValue( "alarmNumber", "Below minimum value" );
			}
			else if ( alarmNumber > 5 ) {
				
				err.rejectValue( "alarmNumber", "Above maximum value" );
			}
		}
	}
}
