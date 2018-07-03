package org.fireapp.service;

import java.util.List;

import org.fireapp.dao.RespondingStationDao;
import org.fireapp.model.incident.FuelSpillIncident;
import org.fireapp.model.incident.IncidentRequirements;
import org.fireapp.model.incident.MassCasualtyIncident;
import org.fireapp.model.incident.MedicalEmergencyIncident;
import org.fireapp.model.incident.StructureFireIncident;
import org.fireapp.model.incident.VehicleAccidentIncident;
import org.fireapp.model.incident.WaterRescueIncident;
import org.fireapp.model.incident.response.IncidentResponse;
import org.fireapp.model.incident.response.RespondingApparatus;
import org.fireapp.model.incident.response.RespondingStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;

/**
 * This service is used to simulate responses by emergency
 * services to various types of emergency incidents
 * 
 * @author Louis Drotos
 *
 */
@Service
public class IncidentSimulatorService {

	@Autowired
	private ResponseRequirementsService respReqService;
	
	@Autowired
	RespondingApparatusService respondingApparatusService;
	
	/**
	 * Simulates the emergency services response to a structure fire
	 * 
	 * @param incident The parameters of the structure fire
	 * @return The incident response
	 */
	public IncidentResponse simulateStructureFireResponse( StructureFireIncident incident ) {
		
		IncidentRequirements respReq;
		IncidentResponse incidentResp;
		List<RespondingApparatus> apparatus;
		GeometryFactory geomFactory;
		Point incidentLocation;
		
		// Creates the incident location coordinate
		geomFactory = new GeometryFactory( new PrecisionModel(), 4326 );
		incidentLocation = geomFactory.createPoint( new Coordinate( incident.getLongitude(), incident.getLatitude() ) );
		
		// Determines the number and type of apparatus that should respond
		respReq = respReqService.getIncidentRespReq( incident );
		
		// Determines the apparatus that should respond to the incident
		apparatus = this.respondingApparatusService.getRespondingApparatus( incident, respReq, incidentLocation );
		
		// Creates the incident response package
		incidentResp = new IncidentResponse();
		incidentResp.setRespondingApparatus( apparatus );
		
		return incidentResp;
	}
	
	/**
	 * Simulates the emergency services response to an medical emergency
	 * 
	 * @param incident The parameters of the medical emergency
	 * @return The incident response
	 */
	public IncidentResponse simulateMedicalEmergencyResponse( MedicalEmergencyIncident incident ) {
			
		IncidentRequirements respReq;
		IncidentResponse incidentResp;
		List<RespondingApparatus> apparatus;
		GeometryFactory geomFactory;
		Point incidentLocation;
		
		// Creates the incident location coordinate
		geomFactory = new GeometryFactory( new PrecisionModel(), 4326 );
		incidentLocation = geomFactory.createPoint( new Coordinate( incident.getLongitude(), incident.getLatitude() ) );
		
		// Determines the number and type of apparatus that should respond
		respReq = respReqService.getIncidentRespReq( incident );
		
		// Determines the apparatus that should respond to the incident
		apparatus = this.respondingApparatusService.getRespondingApparatus( incident, respReq, incidentLocation );
		
		// Creates the incident response package
		incidentResp = new IncidentResponse();
		incidentResp.setRespondingApparatus( apparatus );
		
		return incidentResp;
	}
	
	/**
	 * Simulates the emergency services response to an mass casualty
	 * incident
	 * 
	 * @param incident The parameters of the mass casualty incident
	 * @return The incident response
	 */
	public IncidentResponse simulateMassCasualtyResponse( MassCasualtyIncident incident ) {
		
		IncidentRequirements respReq;
		IncidentResponse incidentResp;
		List<RespondingApparatus> apparatus;
		GeometryFactory geomFactory;
		Point incidentLocation;
		
		// Creates the incident location coordinate
		geomFactory = new GeometryFactory( new PrecisionModel(), 4326 );
		incidentLocation = geomFactory.createPoint( new Coordinate( incident.getLongitude(), incident.getLatitude() ) );
		
		// Determines the number and type of apparatus that should respond
		respReq = respReqService.getIncidentRespReq( incident );
		
		// Determines the apparatus that should respond to the incident
		apparatus = this.respondingApparatusService.getRespondingApparatus( incident, respReq, incidentLocation );
		
		// Creates the incident response package
		incidentResp = new IncidentResponse();
		incidentResp.setRespondingApparatus( apparatus );
		
		return incidentResp;
	}
	
	/**
	 * Simulates the emergency response to a water rescue incident
	 * 
	 * @param incident The parameters of the water rescue emergency
	 * @return The incident response
	 */
	public IncidentResponse simulateWaterRescueResponse( WaterRescueIncident incident ) {
		
		IncidentRequirements respReq;
		IncidentResponse incidentResp;
		List<RespondingApparatus> apparatus;
		GeometryFactory geomFactory;
		Point incidentLocation;
		
		// Creates the incident location coordinate
		geomFactory = new GeometryFactory( new PrecisionModel(), 4326 );
		incidentLocation = geomFactory.createPoint( new Coordinate( incident.getLongitude(), incident.getLatitude() ) );
		
		// Determines the number and type of apparatus that should respond
		respReq = respReqService.getIncidentRespReq( incident );
		
		// Determines the apparatus that should respond to the incident
		apparatus = this.respondingApparatusService.getRespondingApparatus( incident, respReq, incidentLocation );
		
		// Creates the incident response package
		incidentResp = new IncidentResponse();
		incidentResp.setRespondingApparatus( apparatus );
		
		return incidentResp;
	}
	
	/**
	 * Simulates the emergency response to a vehicle accident
	 * 
	 * @param incident The parameters of the vehicle accident
	 * @return The incident response
	 */
	public IncidentResponse simulateVehicleAccidentResponse( VehicleAccidentIncident incident ) {
		
		IncidentRequirements respReq;
		IncidentResponse incidentResp;
		List<RespondingApparatus> apparatus;
		GeometryFactory geomFactory;
		Point incidentLocation;
		
		// Creates the incident location coordinate
		geomFactory = new GeometryFactory( new PrecisionModel(), 4326 );
		incidentLocation = geomFactory.createPoint( new Coordinate( incident.getLongitude(), incident.getLatitude() ) );
		
		// Determines the number and type of apparatus that should respond
		respReq = respReqService.getIncidentRespReq( incident );
		
		// Determines the apparatus that should respond to the incident
		apparatus = this.respondingApparatusService.getRespondingApparatus( incident, respReq, incidentLocation );
		
		// Creates the incident response package
		incidentResp = new IncidentResponse();
		incidentResp.setRespondingApparatus( apparatus );
		
		return incidentResp;
	}
	
	/**
	 * Simulates the emergency response to a flammable fuel spill
	 * 
	 * @param incident The parameters of the fuel spill
	 * @return The incident response
	 * @throws Exception 
	 */
	public IncidentResponse simulateFuelSpillResponse( FuelSpillIncident incident ) throws Exception {
		
		IncidentRequirements respReq;
		IncidentResponse incidentResp;
		List<RespondingApparatus> apparatus;
		GeometryFactory geomFactory;
		Point incidentLocation;
		
		// Creates the incident location coordinate
		geomFactory = new GeometryFactory( new PrecisionModel(), 4326 );
		incidentLocation = geomFactory.createPoint( new Coordinate( incident.getLongitude(), incident.getLatitude() ) );
		
		// Determines the number and type of apparatus that should respond
		respReq = respReqService.getIncidentRespReq( incident );
		
		// Determines the apparatus that should respond to the incident
		apparatus = this.respondingApparatusService.getRespondingApparatus( incident, respReq, incidentLocation );
		
		// Creates the incident response package
		incidentResp = new IncidentResponse();
		incidentResp.setRespondingApparatus( apparatus );
		
		return incidentResp;
	}
}
