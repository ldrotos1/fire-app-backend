package org.fireapp.rest.validator;

import java.util.regex.Pattern;

import org.fireapp.model.incident.FuelSpillIncident;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * This class is used to validate request objects for the 
 * fuel spill response simulator REST service
 * 
 * @author Louis Drotos
 *
 */
@Component
public class FuelSpillIncidentReqValidator extends BaseIncidentReqValidator implements Validator {

	@Override
	public boolean supports( Class<?> val ) {
		
		return FuelSpillIncident.class.equals( val );
	}

	@Override
	public void validate( Object val, Errors err ) {
		
		FuelSpillIncident incident = ( FuelSpillIncident ) val;
		
		String spillSize = incident.getSpillSize();
		Boolean ignited = incident.getIgnited();
		
		// Checks for invalid nulls
		this.checkForInvalidNull( spillSize, "spillSize", err );
		this.checkForInvalidNull( ignited, "ignited", err );
		
		// Validates spill size
		if ( spillSize != null ) {
			
			Pattern pattern = Pattern.compile( "small|large", Pattern.CASE_INSENSITIVE );
			
			if ( !( pattern.matcher( spillSize ).matches() ) ) {
				
		         err.rejectValue( "spillSize", "Must be 'small' or 'large'" );
		      }
		}
		
		// Validates location
		this.validateCoordinates( val, err );
	}
}
