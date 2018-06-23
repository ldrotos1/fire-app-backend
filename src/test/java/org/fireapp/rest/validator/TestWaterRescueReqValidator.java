package org.fireapp.rest.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.fireapp.model.incident.WaterRescueIncident;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

/**
 * Unit tests for the water rescue incident simulator request validator
 * 
 * @author Louis Drotos
 *
 */
public class TestWaterRescueReqValidator {

	private static WaterRescueIncidentReqValidator validator;

	private static Errors errors;
	
	@BeforeClass
	public static void setup() {
		
		validator = new WaterRescueIncidentReqValidator();
	}
	
	/**
	 * Testing coordinates
	 */
	@Test
	public void testCoordinates() {
		
	    WaterRescueIncident incident = new WaterRescueIncident();
	    
	    // Check valid coordinates
	    incident.setLatitude( 25.3 );
	    incident.setLongitude( 54.6 );
	    errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertFalse( errors.hasErrors() );
	    
	    // Check invalid coordinates
	    incident.setLatitude( null );
	    incident.setLongitude( null );
	    errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertEquals( 2, errors.getErrorCount() );
	}
}
