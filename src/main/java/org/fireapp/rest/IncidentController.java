package org.fireapp.rest;

import org.fireapp.model.incident.FuelSpillIncident;
import org.fireapp.model.incident.IncidentResponse;
import org.fireapp.model.incident.MassCasualtyIncident;
import org.fireapp.model.incident.MedicalEmergencyIncident;
import org.fireapp.model.incident.StructureFireIncident;
import org.fireapp.model.incident.VehicleAccidentIncident;
import org.fireapp.model.incident.WaterRescueIncident;
import org.fireapp.service.IncidentRespSimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/incident" )
public class IncidentController {

	@Autowired
	private IncidentRespSimService incidentRespSimService;
	
	@RequestMapping( value = "/fire", method = RequestMethod.POST, produces = "application/json" )
	public IncidentResponse simulateStructureFireResponse( @RequestBody StructureFireIncident incident ) {
		
		return incidentRespSimService.simulateStructureFireResponse( incident );
	}
	
	@RequestMapping( value = "/ems", method = RequestMethod.POST, produces = "application/json" )
	public IncidentResponse simulateMedicalEmergencyResponse( @RequestBody MedicalEmergencyIncident incident ) {
		
		return incidentRespSimService.simulateMedicalEmergencyResponse( incident );
	}
	
	@RequestMapping( value = "/masscas", method = RequestMethod.POST, produces = "application/json" )
	public IncidentResponse simulateMassCasualtyResponse( @RequestBody MassCasualtyIncident incident ) {
		
		return incidentRespSimService.simulateMassCasualtyResponse( incident );
	}
	
	@RequestMapping( value = "/waterrescue", method = RequestMethod.POST, produces = "application/json" )
	public IncidentResponse simulateWaterRescueResponse( @RequestBody WaterRescueIncident incident ) {
		
		return incidentRespSimService.simulateWaterRescueResponse( incident );
	}
	
	@RequestMapping( value = "/vehicleaccident", method = RequestMethod.POST, produces = "application/json" )
	public IncidentResponse simulateVehicleAccidentResponse( @RequestBody VehicleAccidentIncident incident ) {
		
		return incidentRespSimService.simulateVehicleAccidentResponse( incident );
	}
	
	@RequestMapping( value = "/fuelspill", method = RequestMethod.POST, produces = "application/json" )
	public IncidentResponse simulateFuelSpillResponse( @RequestBody FuelSpillIncident incident ) {
		
		return incidentRespSimService.simulateFuelSpillResponse( incident );
	}
}


