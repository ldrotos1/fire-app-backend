package org.fireapp.service;

import org.fireapp.model.incident.FuelSpillIncident;
import org.fireapp.model.incident.IncidentRequirements;
import org.fireapp.model.incident.MassCasualtyIncident;
import org.fireapp.model.incident.MedicalEmergencyIncident;
import org.fireapp.model.incident.StructureFireIncident;
import org.fireapp.model.incident.VehicleAccidentIncident;
import org.fireapp.model.incident.WaterRescueIncident;
import org.springframework.stereotype.Service;

/**
 * This service provides methods that are used to return objects that 
 * define incident response requirements. The requirements are defined 
 * as the number and type of emergency apparatus that should respond based
 * on the characteristics of the incident.
 * 
 * @author Louis Drotos
 *
 */
@Service
public class ResponseRequirementsService {

	/**
	 * Determines the response requirements for the specified structure
	 * fire incident
	 * 
	 * @param incident The incident
	 * @return The response requirements
	 */
	public IncidentRequirements getIncidentRespReq( StructureFireIncident incident ) {
		
		IncidentRequirements reqs = new IncidentRequirements();
		int alarmNum = incident.getAlarmNumber();
		
		reqs.setAerialCount( 2 * alarmNum );
		reqs.setMedicCount( 1 * alarmNum );
		reqs.setEmsSupervisorCount( 1 * alarmNum );
		reqs.setRescueCount( 1 * alarmNum );
		
		if ( incident.getHydrantAccess() ) {
			
			reqs.setEngineCount( 4 * alarmNum );
			reqs.setBattalionChiefCount( 2 * alarmNum );
		}
		else {
			
			reqs.setEngineCount( ( 4 * alarmNum ) + 1 );
			reqs.setBattalionChiefCount( ( 2 * alarmNum ) + 1 );
			reqs.setTankerCount( ( 1 * alarmNum ) + 2 );
		}
		
		return reqs;
	}
	
	/**
	 * Determines the response requirements for the specified medical
	 * emergency incident
	 * 
	 * @param incident The incident
	 * @return The response requirements
	 */
	public IncidentRequirements getIncidentRespReq( MedicalEmergencyIncident incident ) {
		
		IncidentRequirements reqs = new IncidentRequirements();
		reqs.setEngineCount( 1 );
		reqs.setMedicCount( 1 );
		
		return reqs;
	}
	
	/**
	 * Determines the response requirements for the specified vehicle
	 * accident incident
	 * 
	 * @param incident The incident
	 * @return The response requirements
	 */
	public IncidentRequirements getIncidentRespReq( VehicleAccidentIncident incident ) {
		
		IncidentRequirements reqs = new IncidentRequirements();
		
		int injuryCount = incident.getInjuries();
		int vehicleCount = incident.getVehicles();
		
		reqs.setMedicCount( injuryCount );
		reqs.setEngineCount( vehicleCount );
		
		if ( vehicleCount > 2 ) {
			
			reqs.setBattalionChiefCount( 1 );
		}
		
		if ( injuryCount > 2 ) {
			
			reqs.setBattalionChiefCount( 1 );
			reqs.setEmsSupervisorCount( 1 );
		}
		
		// Checks for entrapment
		if ( incident.getEntrapment() ) {
			 
			reqs.setRescueCount( 2 );
			reqs.setEmsSupervisorCount( 1 );
			reqs.setBattalionChiefCount( 1 );
		}
		
		// Checks for hazardous material
		if ( incident.getHazmat() ) {
			
			reqs.setHazmatCount( 1 );
			reqs.setBattalionChiefCount( 1 );
		}
		
		return reqs;
	}
	
	/**
	 * Determines the response requirements for the specified mass
	 * casualty incident
	 * 
	 * @param incident The incident
	 * @return The response requirements
	 */
	public IncidentRequirements getIncidentRespReq( MassCasualtyIncident incident ) {
		
		IncidentRequirements reqs = new IncidentRequirements();
		int casualties = incident.getCasualties();
		
		if ( casualties <= 10 ) {
			
			reqs.setMedicCount( 5 );
			reqs.setBattalionChiefCount( 1 );
			reqs.setEmsSupervisorCount( 2 );
			reqs.setEngineCount( 2 );
		}
		else {
			
			int massCasAlarm;
			
			// Determines the mass casualty alarm level
			if ( casualties % 25 == 0 ) {
				
				massCasAlarm = casualties / 25;
			}
			else {
				
				massCasAlarm = ( casualties / 25 ) + 1;
			}
			
			reqs.setMobileCommandCount( 1 );
			reqs.setMedicCount( massCasAlarm * 10 );
			reqs.setEngineCount( massCasAlarm * 10 );
			reqs.setEmsSupervisorCount( massCasAlarm * 3 );
			reqs.setBattalionChiefCount( massCasAlarm * 1 );
			reqs.setMassCasSupCount( massCasAlarm * 2 );
		}
		
		return reqs;
	}
	
	/**
	 * Determines the response requirements for the specified fuel spill
	 * incident
	 * 
	 * @param incident The incident
	 * @return The response requirements
	 * @throws Exception 
	 */
	public IncidentRequirements getIncidentRespReq( FuelSpillIncident incident ) throws Exception {
		
		IncidentRequirements reqs = new IncidentRequirements();
		String spillSize = incident.getSpillSize();
		
		if ( spillSize.equals( "small" ) ) {
			
			// Sets requirements for a small spill
			reqs.setHazmatCount( 1 );
			reqs.setEngineCount( 1 );
			
			if ( incident.getIgnited() ) {
				
				// Sets requirements for a ignited small spill
				reqs.setEngineCount( 3 );
				reqs.setFoamCount( 2 );
				reqs.setBattalionChiefCount( 1 );
			}
		}
		else if ( spillSize.equals( "large" ) ) {
			
			// Sets requirements for a large spill
			reqs.setEngineCount( 6 );
			reqs.setFoamCount( 2 );
			reqs.setAerialCount( 1 );
			reqs.setMedicCount( 2 );
			reqs.setBattalionChiefCount( 2 );
			reqs.setEmsSupervisorCount( 1 );
			reqs.setHazmatCount( 1 );
		}
		else {
			
			throw new Exception( "Invalid spill size paramter - " + spillSize );
		}
		
		return reqs;
	}
	
	/**
	 * Determines the response requirements for the specified water
	 * rescue incident
	 * 
	 * @param incident The incident
	 * @return The response requirements
	 */
	public IncidentRequirements getIncidentRespReq( WaterRescueIncident incident ) {
		
		IncidentRequirements reqs = new IncidentRequirements();
		reqs.setBoatCount( 2 );
		reqs.setRescueCount( 1 );
		reqs.setTruckOrTowerCount( 1 );
		reqs.setBattalionChiefCount( 1 );
		reqs.setMedicCount( 1 );
		
		return reqs;
	}
}
