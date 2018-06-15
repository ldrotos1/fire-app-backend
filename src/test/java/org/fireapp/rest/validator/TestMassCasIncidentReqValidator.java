package org.fireapp.rest.validator;

import static org.junit.Assert.*;

import org.fireapp.model.incident.MassCasualtyIncident;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

/**
 * Unit tests for the mass casualty incident simulator request validator
 * 
 * @author Louis Drotos
 *
 */
public class TestMassCasIncidentReqValidator {

	private static MassCasIncidentReqValidator validator;
	
	private static Errors errors;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		validator = new MassCasIncidentReqValidator();
	}

	/**
	 * Testing coordinates
	 */
	@Test
	public void testCoordinates() {
		
		// Sets up incident
		MassCasualtyIncident incident = new MassCasualtyIncident();
		incident.setCasualties( 51 );
		
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
	
	/**
	 * Testing coordinates
	 */
	@Test
	public void testCasualtyCount() {
		
		// Sets up incident
		MassCasualtyIncident incident = new MassCasualtyIncident();
		incident.setLatitude( 25.3 );
	    incident.setLongitude( 54.6 );
		
	    // Check valid casualty count edge cases
	    incident.setCasualties( 5 );
	    errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertFalse( errors.hasErrors() );
	    
	    incident.setCasualties( 100 );
	    errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertFalse( errors.hasErrors() );
	    
	    // Check invalid high casualty count
	    incident.setCasualties( 101 );
	    errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertEquals( 1, errors.getErrorCount() );
	    
	    // Check invalid low casualty count
	    incident.setCasualties( 4 );
	    errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertEquals( 1, errors.getErrorCount() );
	    
	    // Check invalid null casualty count
	    incident.setCasualties( null );
	    errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertEquals( 1, errors.getErrorCount() );
	}
}
