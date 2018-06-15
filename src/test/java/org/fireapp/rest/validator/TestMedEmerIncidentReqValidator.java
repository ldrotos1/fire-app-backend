package org.fireapp.rest.validator;

import static org.junit.Assert.*;

import org.fireapp.model.incident.MedicalEmergencyIncident;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

/**
 * Unit tests for the medical emergency incident simulator request validator
 * 
 * @author Louis Drotos
 *
 */
public class TestMedEmerIncidentReqValidator {

	private static MedEmergencyIncidentReqValidator validator;
	
	private static Errors errors;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		validator = new MedEmergencyIncidentReqValidator();
	}

	/**
	 * Testing coordinates
	 */
	@Test
	public void testCoordinates() {

		MedicalEmergencyIncident incident = new MedicalEmergencyIncident();
		
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
