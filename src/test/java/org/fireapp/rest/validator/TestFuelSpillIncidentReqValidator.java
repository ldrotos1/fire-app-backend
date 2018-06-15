package org.fireapp.rest.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.fireapp.model.incident.FuelSpillIncident;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

/**
 * Unit tests for the fuel spill incident simulator request validator
 * 
 * @author Louis Drotos
 *
 */
public class TestFuelSpillIncidentReqValidator {

	private static FuelSpillIncidentReqValidator validator;
	
	private static Errors errors;
	
	@BeforeClass
	public static void setup() {
		
		validator = new FuelSpillIncidentReqValidator();
	}
	
	/**
	 * Testing coordinates
	 */
	@Test
	public void testCoordinates() {
		
		// Sets up incident
		FuelSpillIncident incident = new FuelSpillIncident();
		incident.setSpillSize( "large" );
		incident.setIgnited( false );
		
	    // Check valid coordinates
	    incident.setLatitude( 25.3 );
	    incident.setLongitude( 54.6 );
	    errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertFalse( errors.hasErrors() );
	    
	    // Check invalid coordinates
	    incident.setLatitude( null );
	    incident.setLongitude( null );
	    Errors errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertEquals( 2, errors.getErrorCount() );
	}
	
	/**
	 * Testing the spill size parameter 
	 */
	@Test
	public void testSpillSize() {
		
		// Sets up incident
		FuelSpillIncident incident = new FuelSpillIncident();
		incident.setLatitude( 43.2 );
		incident.setLongitude( 15.6 );
		incident.setIgnited( false );
		
		// Check valid 'small' spill size
		incident.setSpillSize( "small" );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertFalse( errors.hasErrors() );
	    
	    // Check valid 'large' spill size
	    incident.setSpillSize( "large" );		
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertFalse( errors.hasErrors() );
	    
	    // Check invalid spill size value
	    incident.setSpillSize( "huge" );		
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertEquals( 1, errors.getErrorCount() );
	    
	    // Check invalid spill size null value
	    incident.setSpillSize( null );		
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertEquals( 1, errors.getErrorCount() );
	}
	
	/**
	 * Testing the ignition parameter
	 */
	@Test
	public void testIgnition() {
		
		// Sets up incident
		FuelSpillIncident incident = new FuelSpillIncident();
		incident.setLatitude( 43.2 );
		incident.setLongitude( 15.6 );
		incident.setSpillSize( "small" );
		
		// Check valid false value
		incident.setIgnited( false );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertFalse( errors.hasErrors() );
	    
	    // Check valid true value
	    incident.setIgnited( true );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertFalse( errors.hasErrors() );
	    
	    // Check invalid null value
	    incident.setIgnited( null );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertEquals( 1, errors.getErrorCount() );
	}
}
