package org.fireapp.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.fireapp.model.incident.IncidentRequirements;
import org.fireapp.model.incident.MassCasualtyIncident;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Parameterized unit test for the mass casualty incident response
 * requirements method of the response requirements service
 * 
 * @author Louis Drotos
 *
 */
@RunWith( Parameterized.class )
public class TestMassCasualtyResponseRequirements {

	public static ResponseRequirementsService service;
	public int numOfInjuries;
	
	public int engineCount;
	public int medicCount;
	public int battalionChiefCount;
	public int emsSupervisorCount;
	public int massCasSupportCount;
	public int mobileCommandCount;
	public int totalCount;
	
	@BeforeClass
	public static void setup() {
		
		service = new ResponseRequirementsService();
	}
	
	@SuppressWarnings("rawtypes")
	@Parameterized.Parameters
	public static Collection dataFuelSpill() {
		
		return Arrays.asList(new Object[][] {
        	{ 5, 2, 5, 1, 2, 0, 0, 10 },
        	{ 10, 2, 5, 1, 2, 0, 0, 10 },
        	{ 11, 10, 10, 1, 3, 2, 1, 27 },
        	{ 25, 10, 10, 1, 3, 2, 1, 27 },
        	{ 26, 20, 20, 2, 6, 4, 1, 53 },
        	{ 50, 20, 20, 2, 6, 4, 1, 53 },
        	{ 51, 30, 30, 3, 9, 6, 1, 79 },
        	{ 75, 30, 30, 3, 9, 6, 1, 79 },
        	{ 76, 40, 40, 4, 12, 8, 1, 105 },
        	{ 100, 40, 40, 4, 12, 8, 1, 105 }
	      }
		);
    }
	
	public TestMassCasualtyResponseRequirements( int numOfInjuries, int engineCount, 
			int medicCount, int battalionChiefCount, int emsSupervisorCount, 
			int massCasSupportCount, int mobileCommandCount, int totalCount ) {
		
		this.numOfInjuries = numOfInjuries;
		this.engineCount = engineCount;
		this.medicCount = medicCount;
		this.battalionChiefCount = battalionChiefCount;
		this.emsSupervisorCount = emsSupervisorCount;
		this.massCasSupportCount = massCasSupportCount;
		this.mobileCommandCount = mobileCommandCount;
		this.totalCount = totalCount;
	}

	/**
	 * Tests the incident requirements service for a mass casualty incident
	 */
	@Test
	public void testGetMassCasResponseReq() {
		
		MassCasualtyIncident incident;
		IncidentRequirements req;
		
		incident = new MassCasualtyIncident();
		incident.setLatitude( 26.5 );
		incident.setLongitude( 33.4 );
		incident.setCasualties( this.numOfInjuries );
		
		req = service.getIncidentRespReq( incident );
		
		assertEquals( this.engineCount, req.getEngineCount() );
		assertEquals( this.medicCount, req.getMedicCount() );
		assertEquals( this.battalionChiefCount, req.getBattalionChiefCount() );
		assertEquals( this.emsSupervisorCount, req.getEmsSupervisorCount() );
		assertEquals( this.massCasSupportCount, req.getMassCasSupCount() );
		assertEquals( this.mobileCommandCount, req.getMobileCommandCount() );
		assertEquals( this.totalCount, req.getTotalApparatusCount() );
	}
}
