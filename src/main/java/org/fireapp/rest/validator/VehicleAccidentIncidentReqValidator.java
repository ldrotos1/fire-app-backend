package org.fireapp.rest.validator;

import org.fireapp.model.incident.VehicleAccidentIncident;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * This class is used to validate request objects for the 
 * vehicle accident response simulator REST service
 * 
 * @author Louis Drotos
 *
 */
@Component
public class VehicleAccidentIncidentReqValidator extends BaseIncidentReqValidator implements Validator {

	@Override
	public boolean supports( Class<?> val ) {
		
		return VehicleAccidentIncident.class.equals( val );
	}
	
	@Override
	public void validate( Object val, Errors err ) {
		
		VehicleAccidentIncident incident = ( VehicleAccidentIncident ) val;
		
		Integer injuries = incident.getInjuries();
		Integer vehicles = incident.getVehicles();
		Boolean hazmat = incident.getHazmat();
		Boolean entrapment = incident.getEntrapment();
		
		// Checks for invalid nulls
		this.checkForInvalidNull( injuries, "injuries", err );
		this.checkForInvalidNull( vehicles, "vehicles", err );
		this.checkForInvalidNull( hazmat, "hazmat", err );
		this.checkForInvalidNull( entrapment, "entrapment", err );
		
		// Validates location
		this.validateCoordinates( val, err );
		
		// Validates the number injuries
		if ( injuries != null ) {
			
			if ( injuries < 0 ) {
				
				err.rejectValue( "injuries", "Below minimum value" );
			}
			else if ( injuries > 50 ) {
				
				err.rejectValue( "injuries", "Above maximum value" );
			}
		}
		
		// Validates the number of vehicles
		if ( vehicles != null ) {
			
			if ( vehicles < 0 ) {
				
				err.rejectValue( "vehicles", "Below minimum value" );
			}
			else if ( vehicles > 5 ) {
				
				err.rejectValue( "vehicles", "Above maximum value" );
			}
		}
	}
}
