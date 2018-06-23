package org.fireapp.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.fireapp.model.incident.IncidentRequirements;
import org.fireapp.model.incident.VehicleAccidentIncident;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Parameterized unit test for the vehicle accident incident response
 * requirements method of the response requirements service
 * 
 * @author Louis Drotos
 *
 */
@RunWith( Parameterized.class )
public class TestVehicleAccidentResponseRequirements {

	public static ResponseRequirementsService service;
	public int numOfInjuries;
	public int numOfVehicles;
	public boolean isEntrapment;
	public boolean isHazmat;
	
	public int engineCount;
	public int medicCount;
	public int rescueCount;
	public int battalionChiefCount;
	public int emsSupervisorCount;
	public int hazmatCount;
	public int totalCount;
	
	@BeforeClass
	public static void setup() {
		
		service = new ResponseRequirementsService();
	}
	
	@SuppressWarnings("rawtypes")
	@Parameterized.Parameters
	public static Collection dataFuelSpill() {
		
		return Arrays.asList(new Object[][] {
        	{ 0, 1, false, false, 1, 0, 0, 0, 0, 0, 1 },
        	{ 0, 2, false, false, 2, 0, 0, 0, 0, 0, 2 },
        	{ 1, 3, false, false, 3, 1, 0, 1, 0, 0, 5 },
        	{ 2, 2, false, false, 2, 2, 0, 0, 0, 0, 4 },
        	{ 3, 2, false, false, 2, 3, 0, 1, 1, 0, 7 },
        	{ 4, 3, false, false, 3, 4, 0, 1, 1, 0, 9 },
        	{ 4, 3, true, true, 3, 4, 2, 1, 1, 1, 12 },
	      }
		);
    }
	
	public TestVehicleAccidentResponseRequirements( int numOfInjuries, int numOfVehicles, boolean isEntrapment, 
			boolean isHazmat, int engineCount, int medicCount, int rescueCount, int battalionChiefCount, 
			int emsSupervisorCount, int hazmatCount, int totalCount ) {
		
		this.numOfInjuries = numOfInjuries;
		this.numOfVehicles = numOfVehicles;
		this.isEntrapment = isEntrapment;
		this.isHazmat = isHazmat;
		
		this.engineCount = engineCount;
		this.medicCount = medicCount;
		this.rescueCount = rescueCount;
		this.battalionChiefCount = battalionChiefCount;
		this.emsSupervisorCount = emsSupervisorCount;
		this.hazmatCount = hazmatCount;
		this.totalCount = totalCount;
	}
	
	/**
	 * Tests the incident requirements service for a vehicle accident
	 */
	@Test
	public void testGetVehicleAccidentResponseReq() {
		
		VehicleAccidentIncident incident;
		IncidentRequirements req;
		
		incident = new VehicleAccidentIncident();
		incident.setLatitude( 26.5 );
		incident.setLongitude( 33.4 );
		incident.setInjuries( this.numOfInjuries );
		incident.setVehicles( this.numOfVehicles );
		incident.setEntrapment( this.isHazmat );
		incident.setHazmat( this.isHazmat );
		
		req = service.getIncidentRespReq( incident );
		
		assertEquals( this.engineCount, req.getEngineCount() );
		assertEquals( this.medicCount, req.getMedicCount() );
		assertEquals( this.rescueCount, req.getRescueCount() );
		assertEquals( this.battalionChiefCount, req.getBattalionChiefCount() );
		assertEquals( this.emsSupervisorCount, req.getEmsSupervisorCount() );
		assertEquals( this.hazmatCount, req.getHazmatCount() );
		assertEquals( this.totalCount, req.getTotalApparatusCount() );
	}
}
