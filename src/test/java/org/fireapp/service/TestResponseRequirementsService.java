package org.fireapp.service;

import static org.junit.Assert.assertEquals;

import org.fireapp.model.incident.IncidentRequirements;
import org.fireapp.model.incident.MedicalEmergencyIncident;
import org.fireapp.model.incident.WaterRescueIncident;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * Unit tests for the response requirements service
 * 
 * @author Louis Drotos
 *
 */
public class TestResponseRequirementsService {
	
	public static ResponseRequirementsService service;
	
	@BeforeClass
	public static void setup() {
		
		service = new ResponseRequirementsService();
	}
	
	/**
	 * Tests the incident requirements service for a medical emergency incident 
	 */
	@Test
	public void testGetMedicalEmergencyReponseReq() {

		MedicalEmergencyIncident incident;
		IncidentRequirements req;
		
		incident = new MedicalEmergencyIncident();
		incident.setLatitude( 26.5 );
		incident.setLongitude( 33.4 );
		
		req = service.getIncidentRespReq( incident );
		
		assertEquals ( 1, req.getEngineCount() );
		assertEquals( 1, req.getMedicCount() );
		assertEquals( 2, req.getTotalApparatusCount() );
	}
	
	/**
	 * Tests the incident requirements service for a water rescue incident
	 */
	@Test
	public void testGetWaterRescueResponseReq() {
		
		WaterRescueIncident incident;
		IncidentRequirements req;
		
		incident = new WaterRescueIncident();
		incident.setLatitude( 26.5 );
		incident.setLongitude( 33.4 );
		
		req = service.getIncidentRespReq( incident );
		
		assertEquals( 2, req.getBoatCount() );
		assertEquals( 1, req.getRescueCount() );
		assertEquals( 1, req.getTruckOrTowerCount() );
		assertEquals( 1, req.getBattalionChiefCount() );
		assertEquals( 1, req.getMedicCount() );
		assertEquals( 6, req.getTotalApparatusCount() );
	}
}