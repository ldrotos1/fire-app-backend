package org.fireapp.rest.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.fireapp.model.incident.VehicleAccidentIncident;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

/**
 * Unit tests for the vehicle accident incident simulator request validator
 * 
 * @author Louis Drotos
 *
 */
public class TestVehAccidentIncidentReqValidator {

	private static VehicleAccidentIncidentReqValidator validator;
	
	private static Errors errors;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		validator = new VehicleAccidentIncidentReqValidator();
	}
	
	/**
	 * Testing coordinates
	 */
	@Test
	public void testCoordinates() {
		
		// Sets up incident
		VehicleAccidentIncident incident = new VehicleAccidentIncident();
		incident.setInjuries( 3 );
		incident.setVehicles( 2 );
		incident.setHazmat( false );
		incident.setEntrapment( true );
		
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
	 * Test the number of injuries parameter
	 */
	@Test
	public void testInjuryCount() {
		
		// Sets up incident
		VehicleAccidentIncident incident = new VehicleAccidentIncident();
		incident.setLatitude( 25.3 );
	    incident.setLongitude( 54.6 );
		incident.setVehicles( 1 );
		incident.setHazmat( false );
		incident.setEntrapment( true );
		
		// Test valid injury count edge cases
		incident.setInjuries( 0 );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertFalse( errors.hasErrors() );
		
		incident.setInjuries( 4 );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertFalse( errors.hasErrors() );
	    
	    // Test invalid high count
		incident.setInjuries( 5 );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertEquals( 1, errors.getErrorCount() );
	    
	    // Test invalid low count
		incident.setInjuries( -1 );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertEquals( 1, errors.getErrorCount() );
	    
	    // Test invalid null value
		incident.setInjuries( null );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertEquals( 1, errors.getErrorCount() );
	}
	
	/**
	 * Test the number of vehicles parameter
	 */
	@Test
	public void testVehicleCount() {
		
		// Sets up incident
		VehicleAccidentIncident incident = new VehicleAccidentIncident();
		incident.setLatitude( 25.3 );
	    incident.setLongitude( 54.6 );
		incident.setInjuries( 1 );
		incident.setHazmat( false );
		incident.setEntrapment( true );
		
		// Test valid vehicle count edge cases
		incident.setVehicles( 1 );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertFalse( errors.hasErrors() );
		
		incident.setVehicles( 3 );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertFalse( errors.hasErrors() );
	    
	    // Test invalid high count
		incident.setVehicles( 4 );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertEquals( 1, errors.getErrorCount() );
	    
	    // Test invalid low count
		incident.setVehicles( 0 );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertEquals( 1, errors.getErrorCount() );
	    
	    // Test invalid null value
		incident.setVehicles( null );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertEquals( 1, errors.getErrorCount() );
	}
	
	/**
	 * Test the hazmat paramter
	 */
	@Test
	public void testHazmat() {
		
		// Sets up incident
		VehicleAccidentIncident incident = new VehicleAccidentIncident();
		incident.setLatitude( 25.3 );
	    incident.setLongitude( 54.6 );
		incident.setInjuries( 3 );
		incident.setVehicles( 3 );
		incident.setEntrapment( false );
		
		// Test valid true value
		incident.setHazmat( true );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertFalse( errors.hasErrors() );
		
		// Test valid false value
		incident.setHazmat( false );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertFalse( errors.hasErrors() );
	    
		// Test invalid null value
		incident.setHazmat( null );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertEquals( 1, errors.getErrorCount() );
	}
	
	/**
	 * Test the entrapment parameter
	 */
	@Test
	public void testEntrapment() {
		
		// Sets up incident
		VehicleAccidentIncident incident = new VehicleAccidentIncident();
		incident.setLatitude( 25.3 );
	    incident.setLongitude( 54.6 );
		incident.setInjuries( 3 );
		incident.setVehicles( 2 );
		incident.setHazmat( true );
		
		// Test valid true value
		incident.setEntrapment( true );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertFalse( errors.hasErrors() );
		
		// Test valid false value
		incident.setEntrapment( false );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertFalse( errors.hasErrors() );
	    
		// Test invalid null value
		incident.setEntrapment( null );
		errors = new BeanPropertyBindingResult( incident, "incident" );
	    validator.validate( incident, errors );
	    assertEquals( 1, errors.getErrorCount() );
	}
}
