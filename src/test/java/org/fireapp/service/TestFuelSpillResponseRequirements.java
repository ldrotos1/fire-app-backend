package org.fireapp.service;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.fireapp.model.incident.FuelSpillIncident;
import org.fireapp.model.incident.IncidentRequirements;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Parameterized unit test for the fuel spill incident response
 * requirements method of the response requirements service
 * 
 * @author Louis Drotos
 *
 */
@RunWith( Parameterized.class )
public class TestFuelSpillResponseRequirements {

	public static ResponseRequirementsService service;
	
	public String spillSize;
	public Boolean hasFuelIgnited;
	
	public int engineCount;
	public int hazmatCount;
	public int foamCount;
	public int aerialCount;
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
        	{ "small", false, 1, 1, 0, 0, 0, 0, 0, 2 }, 
        	{ "small", true, 3, 1, 2, 0, 0, 1, 0, 7 },
        	{ "large", false, 6, 1, 2, 1, 2, 2, 1, 15 },
        	{ "large", true, 6, 1, 2, 1, 2, 2, 1, 15 }
	      }
		);
    }
	
	public TestFuelSpillResponseRequirements( String spillSize, Boolean hasFuelIgnited, int engineCount, int hazmatCount, 
			int foamCount, int aerialCount, int medicCount, int battalionChiefCount, int emsSupervisorCount, int totalCount ) {
		
      this.spillSize = spillSize;
      this.hasFuelIgnited = hasFuelIgnited;
      this.engineCount = engineCount;
      this.hazmatCount = hazmatCount;
      this.foamCount = foamCount;
      this.aerialCount = aerialCount;
      this.medicCount = medicCount;
      this.battalionChiefCount = battalionChiefCount;
      this.emsSupervisorCount = emsSupervisorCount;
      this.totalCount = totalCount;
	}

	/**
	 * Tests the incident requirements service for a fuel spill incident
	 * @throws Exception 
	 */
	@Test
	public void testGetFuelSpillResponseReq() throws Exception {
		
		FuelSpillIncident incident;
		IncidentRequirements req;
		
		incident = new FuelSpillIncident();
		incident.setLatitude( 26.5 );
		incident.setLongitude( 33.4 );
		incident.setSpillSize( this.spillSize );
		incident.setIgnited( this.hasFuelIgnited );
		
		req = service.getIncidentRespReq( incident );
		
		assertEquals( this.engineCount, req.getEngineCount() );
		assertEquals( this.hazmatCount, req.getHazmatCount() );
		assertEquals( this.foamCount, req.getFoamCount() );
		assertEquals( this.aerialCount, req.getAerialCount() );
		assertEquals( this.medicCount, req.getMedicCount() );
		assertEquals( this.battalionChiefCount, req.getBattalionChiefCount() );
		assertEquals( this.emsSupervisorCount, req.getEmsSupervisorCount() );
		assertEquals( this.totalCount, req.getTotalApparatusCount() );
	}
}
