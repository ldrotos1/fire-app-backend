package org.fireapp.service;

import org.fireapp.model.incident.FuelSpillIncident;
import org.fireapp.model.incident.IncidentResponse;
import org.fireapp.model.incident.MassCasualtyIncident;
import org.fireapp.model.incident.MedicalEmergencyIncident;
import org.fireapp.model.incident.StructureFireIncident;
import org.fireapp.model.incident.VehicleAccidentIncident;
import org.fireapp.model.incident.WaterRescueIncident;
import org.springframework.stereotype.Service;

/**
 * This service is used to simulate responses by emergency
 * services to various types of emergency incidents
 * 
 * @author Louis Drotos
 *
 */
@Service
public class IncidentRespSimService {

	/**
	 * Simulates the emergency services response to a structure fire
	 * 
	 * @param incident The parameters of the structure fire
	 * @return The incident response
	 */
	public IncidentResponse simulateStructureFireResponse( StructureFireIncident incident ) {
		
		return new IncidentResponse();
	}
	
	/**
	 * Simulates the emergency services response to an medical emergency
	 * 
	 * @param incident The parameters of the medical emergency
	 * @return The incident response
	 */
	public IncidentResponse simulateMedicalEmergencyResponse( MedicalEmergencyIncident incident ) {
			
			return new IncidentResponse();
		}
	
	/**
	 * Simulates the emergency services response to an mass casualty
	 * incident
	 * 
	 * @param incident The parameters of the mass casualty incident
	 * @return The incident response
	 */
	public IncidentResponse simulateMassCasualtyResponse( MassCasualtyIncident incident ) {
		
		return new IncidentResponse();
	}
	
	/**
	 * Simulates the emergency response to a water rescue incident
	 * 
	 * @param incident The parameters of the water rescue emergency
	 * @return The incident response
	 */
	public IncidentResponse simulateWaterRescueResponse( WaterRescueIncident incident ) {
		
		return new IncidentResponse();
	}
	
	/**
	 * Simulates the emergency response to a vehicle accident
	 * 
	 * @param incident The parameters of the vehicle accident
	 * @return The incident response
	 */
	public IncidentResponse simulateVehicleAccidentResponse( VehicleAccidentIncident incident ) {
		
		return new IncidentResponse();
	}
	
	/**
	 * Simulates the emergency response to a flammable fuel spill
	 * 
	 * @param incident The parameters of the fuel spill
	 * @return The incident response
	 */
	public IncidentResponse simulateFuelSpillResponse( FuelSpillIncident incident ) {
		
		return new IncidentResponse();
	}
}
