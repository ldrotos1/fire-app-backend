package org.fireapp.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.fireapp.model.incident.IncidentRequirements;
import org.fireapp.model.incident.StructureFireIncident;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Parameterized unit test for the structure fire incident response
 * requirements method of the response requirements service
 * 
 * @author Louis Drotos
 *
 */
@RunWith( Parameterized.class )
public class TestStructureFireResponseRequirements {

	public static ResponseRequirementsService service;
	public int alarmNumber;
	public boolean hasHydrantAccess;
	
	public int engineCount;
	public int aerialCount;
	public int rescueCount;
	public int tankerCount;
	public int medicCount;
	public int battalionChiefCount;
	public int emsSupervisorCount;
	public int totalCount;
	
	@BeforeClass
	public static void setup() {
		
		service = new ResponseRequirementsService();
	}
	
	@SuppressWarnings("rawtypes")
	@Parameterized.Parameters
	public static Collection dataFuelSpill() {
		
		return Arrays.asList(new Object[][] {
        	{ 1, true, 4, 2, 1, 0, 1, 2, 1, 11 },
        	{ 1, false, 5, 2, 1, 3, 1, 3, 1, 16 },
        	{ 2, true, 8, 4, 2, 0, 2, 4, 2, 22 },
        	{ 2, false, 9, 4, 2, 4, 2, 5, 2, 28 },
        	{ 3, true, 12, 6, 3, 0, 3, 6, 3, 33 },
        	{ 3, false, 13, 6, 3, 5, 3, 7, 3, 40 },
        	{ 4, true, 16, 8, 4, 0, 4, 8, 4, 44 },
        	{ 4, false, 17, 8, 4, 6, 4, 9, 4, 52 },
        	{ 5, true, 20, 10, 5, 0, 5, 10, 5, 55 },
        	{ 5, false, 21, 10, 5, 7, 5, 11, 5, 64 }
	      }
		);
    }
	
	public TestStructureFireResponseRequirements( int numOfAlarms, boolean hasHydrantAccess, 
			int engineCount, int aerialCount, int rescueCount, int tankerCount, int medicCount, 
			int battalionChiefCount, int emsSupervisorCount, int totalCount ) {
		
		this.alarmNumber = numOfAlarms;
		this.hasHydrantAccess = hasHydrantAccess;
		
		this.engineCount = engineCount;
		this.aerialCount = aerialCount;
		this.rescueCount = rescueCount;
		this.tankerCount = tankerCount;
		this.medicCount = medicCount;
		this.battalionChiefCount = battalionChiefCount;
		this.emsSupervisorCount = emsSupervisorCount;
		this.totalCount = totalCount;
	}
	
	/**
	 * Tests the incident response requirements service for 
	 * a structure fire
	 */
	@Test
	public void testGetStructureFireResponseReq() {
		
		StructureFireIncident incident;
		IncidentRequirements req;
		
		incident = new StructureFireIncident();
		incident.setLatitude( 26.5 );
		incident.setLongitude( 33.4 );
		incident.setAlarmNumber( this.alarmNumber );
		incident.setHydrantAccess( this.hasHydrantAccess );
		
		req = service.getIncidentRespReq( incident );
		
		assertEquals( this.engineCount, req.getEngineCount() );
		assertEquals( this.aerialCount, req.getAerialCount() );
		assertEquals( this.rescueCount, req.getRescueCount() );
		assertEquals( this.tankerCount, req.getTankerCount() );
		assertEquals( this.medicCount, req.getMedicCount() );
		assertEquals( this.battalionChiefCount, req.getBattalionChiefCount() );
		assertEquals( this.emsSupervisorCount, req.getEmsSupervisorCount() );
		assertEquals( this.totalCount, req.getTotalApparatusCount() );
	}
}
