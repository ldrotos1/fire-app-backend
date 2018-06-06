package org.fireapp.rest.validator;

import org.fireapp.model.incident.Incident;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * This class is a base incident response simulator request validator 
 * that is use to validate fields that are common to all the incident 
 * response simulator REST services
 * 
 * @author Louis Drotos
 *
 */
@Component
public class BaseIncidentReqValidator {

	/**
	 * Validates the latitude and longitude of a incident response simulator 
	 * request object. If coordinates are not valid error messages are added 
	 * to the error collection
	 * 
	 * @param val The incident response simulator object
	 * @param err The errors collection
	 */
	protected void validateCoordinates( Object val, Errors err ) {
		
		Incident incident = ( Incident ) val;
		
		// Checks for null values
		this.checkForInvalidNull( incident.getLatitude(), "latitude", err );
		this.checkForInvalidNull( incident.getLongitude(), "longitude", err );	
	}
	
	/**
	 * Determines if the specified field contains a null value. If so
	 * then a invalid null error message is added to the errors collection 
	 *  
	 * @param value The field value
	 * @param fieldName The field value name
	 * @param err The errors collection
	 */
	protected void checkForInvalidNull( Object value, String fieldName, Errors err ) {
		
		if ( value == null ) {
			
			err.rejectValue( fieldName, "Invalid null value" );
		}
	}
}
