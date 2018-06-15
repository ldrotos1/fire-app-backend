package org.fireapp.rest.validator;

import static org.junit.Assert.*;

import org.fireapp.model.incident.StructureFireIncident;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

/**
 * Unit tests for the structure fire incident simulator request validator
 * 
 * @author Louis Drotos
 *
 */
public class TestStructureFireIncidentReqValidator {

	private static StructureFireIncidentReqValidator validator;
	
	private static Errors errors;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		validator = new StructureFireIncidentReqValidator();
	}

	/**
	 * Testing coordinates
	 */
	@Test
	public void testCoordinates() {
		
		// Sets up incident
		StructureFireIncident incident = new StructureFireIncident();
		incident.setAlarmNumber( 2 );
		incident.setHydrantAccess( true );
		
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
	 * Testing the alarm number parameter
	 */
	@Test
	public void testAlarmNumber() {
		
		// Sets up incident
		StructureFireIncident incident = new StructureFireIncident();
		incident.setLatitude( 25.3 );
	    incident.setLongitude( 54.6 );
		incident.setHydrantAccess( false );
		
		// Check valid alarm number edge cases
		incident.setAlarmNumber( 1 );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertFalse( errors.hasErrors() );
	    
	    incident.setAlarmNumber( 5 );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertFalse( errors.hasErrors() );
	    
	    // Check invalid high alarm number
	    incident.setAlarmNumber( 6 );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertEquals( 1, errors.getErrorCount() );
	    
	    // Check invalid low alarm number
	    incident.setAlarmNumber( 0 );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertEquals( 1, errors.getErrorCount() );
	    
	    // Check invalid null alarm number
	    incident.setAlarmNumber( null );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertEquals( 1, errors.getErrorCount() );
	}
	
	/**
	 * Testing the hydrant access parameter 
	 */
	@Test
	public void testHydrantAccess() {
		
		// Sets up incident
		StructureFireIncident incident = new StructureFireIncident();
		incident.setLatitude( 25.3 );
	    incident.setLongitude( 54.6 );
	    incident.setAlarmNumber( 3 );
	    
	    // Check valid hydrant access
	    incident.setHydrantAccess( true );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertFalse( errors.hasErrors() );
	    
	    incident.setHydrantAccess( false );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertFalse( errors.hasErrors() );
	    
	    // Check invalid null hydrant access
	    incident.setHydrantAccess( null );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertEquals( 1, errors.getErrorCount() );
	}
}