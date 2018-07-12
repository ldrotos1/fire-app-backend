package org.fireapp.service;

import java.io.IOException;
import java.util.List;

import org.fireapp.model.incident.FuelSpillIncident;
import org.fireapp.model.incident.IncidentRequirements;
import org.fireapp.model.incident.MassCasualtyIncident;
import org.fireapp.model.incident.MedicalEmergencyIncident;
import org.fireapp.model.incident.StructureFireIncident;
import org.fireapp.model.incident.VehicleAccidentIncident;
import org.fireapp.model.incident.WaterRescueIncident;
import org.fireapp.model.incident.response.IncidentResponse;
import org.fireapp.model.incident.response.RespondingApparatus;
import org.fireapp.model.incident.response.ResponseRoute;
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
	private RespondingApparatusService respondingApparatusService;
	
	@Autowired
	private RoutingService routingService;
	
	/**
	 * Simulates the emergency services response to a structure fire
	 * 
	 * @param incident The parameters of the structure fire
	 * @return The incident response
	 * @throws IOException 
	 */
	public IncidentResponse simulateStructureFireResponse( StructureFireIncident incident ) throws Exception {
		
		IncidentRequirements respReq;
		IncidentResponse incidentResp;
		List<RespondingApparatus> apparatus;
		List<ResponseRoute> routes;
		GeometryFactory geomFactory;
		Point incidentLocation;
		
		// Creates the incident location coordinate
		geomFactory = new GeometryFactory( new PrecisionModel(), 4326 );
		incidentLocation = geomFactory.createPoint( new Coordinate( incident.getLongitude(), incident.getLatitude() ) );
		
		// Determines the number and type of apparatus that should respond
		respReq = respReqService.getIncidentRespReq( incident );
		
		// Determines the apparatus that should respond to the incident and gets their travel routes
		apparatus = this.respondingApparatusService.getRespondingApparatus( incident, respReq, incidentLocation );
		routes = this.routingService.getResponseTravelRoutes( incidentLocation, apparatus );
		
		// Creates the incident response package
		incidentResp = new IncidentResponse();
		incidentResp.setIncidentTitle( this.getIncidentTitle( incident ) );
		incidentResp.setIncident( incident );
		incidentResp.setRespondingApparatus( apparatus );
		incidentResp.setReponseRoutes( routes );
		
		return incidentResp;
	}
	
	/**
	 * Simulates the emergency services response to an medical emergency
	 * 
	 * @param incident The parameters of the medical emergency
	 * @return The incident response
	 * @throws IOException 
	 */
	public IncidentResponse simulateMedicalEmergencyResponse( MedicalEmergencyIncident incident ) throws Exception {
			
		IncidentRequirements respReq;
		IncidentResponse incidentResp;
		List<RespondingApparatus> apparatus;
		List<ResponseRoute> routes;
		GeometryFactory geomFactory;
		Point incidentLocation;
		
		// Creates the incident location coordinate
		geomFactory = new GeometryFactory( new PrecisionModel(), 4326 );
		incidentLocation = geomFactory.createPoint( new Coordinate( incident.getLongitude(), incident.getLatitude() ) );
		
		// Determines the number and type of apparatus that should respond
		respReq = respReqService.getIncidentRespReq( incident );
		
		// Determines the apparatus that should respond to the incident and gets their travel routes
		apparatus = this.respondingApparatusService.getRespondingApparatus( incident, respReq, incidentLocation );
		routes = this.routingService.getResponseTravelRoutes( incidentLocation, apparatus );
				
		// Creates the incident response package
		incidentResp = new IncidentResponse();
		incidentResp.setIncidentTitle( "Emergency Medical Incident" );
		incidentResp.setIncident( incident );
		incidentResp.setRespondingApparatus( apparatus );
		incidentResp.setReponseRoutes( routes );
		
		return incidentResp;
	}
	
	/**
	 * Simulates the emergency services response to an mass casualty
	 * incident
	 * 
	 * @param incident The parameters of the mass casualty incident
	 * @return The incident response
	 * @throws IOException 
	 */
	public IncidentResponse simulateMassCasualtyResponse( MassCasualtyIncident incident ) throws Exception {
		
		IncidentRequirements respReq;
		IncidentResponse incidentResp;
		List<RespondingApparatus> apparatus;
		List<ResponseRoute> routes;
		GeometryFactory geomFactory;
		Point incidentLocation;
		
		// Creates the incident location coordinate
		geomFactory = new GeometryFactory( new PrecisionModel(), 4326 );
		incidentLocation = geomFactory.createPoint( new Coordinate( incident.getLongitude(), incident.getLatitude() ) );
		
		// Determines the number and type of apparatus that should respond
		respReq = respReqService.getIncidentRespReq( incident );
		
		// Determines the apparatus that should respond to the incident and gets their travel routes
		apparatus = this.respondingApparatusService.getRespondingApparatus( incident, respReq, incidentLocation );
		routes = this.routingService.getResponseTravelRoutes( incidentLocation, apparatus );
				
		// Creates the incident response package
		incidentResp = new IncidentResponse();
		incidentResp.setIncidentTitle( "Mass Casualty Incident" );
		incidentResp.setIncident( incident );
		incidentResp.setRespondingApparatus( apparatus );
		incidentResp.setReponseRoutes( routes );
		
		return incidentResp;
	}
	
	/**
	 * Simulates the emergency response to a water rescue incident
	 * 
	 * @param incident The parameters of the water rescue emergency
	 * @return The incident response
	 * @throws IOException 
	 */
	public IncidentResponse simulateWaterRescueResponse( WaterRescueIncident incident ) throws Exception {
		
		IncidentRequirements respReq;
		IncidentResponse incidentResp;
		List<RespondingApparatus> apparatus;
		List<ResponseRoute> routes;
		GeometryFactory geomFactory;
		Point incidentLocation;
		
		// Creates the incident location coordinate
		geomFactory = new GeometryFactory( new PrecisionModel(), 4326 );
		incidentLocation = geomFactory.createPoint( new Coordinate( incident.getLongitude(), incident.getLatitude() ) );
		
		// Determines the number and type of apparatus that should respond
		respReq = respReqService.getIncidentRespReq( incident );
		
		// Determines the apparatus that should respond to the incident and gets their travel routes
		apparatus = this.respondingApparatusService.getRespondingApparatus( incident, respReq, incidentLocation );
		routes = this.routingService.getResponseTravelRoutes( incidentLocation, apparatus );
				
		// Creates the incident response package
		incidentResp = new IncidentResponse();
		incidentResp.setIncidentTitle( "Water Rescue Emergency" );
		incidentResp.setIncident( incident );
		incidentResp.setRespondingApparatus( apparatus );
		incidentResp.setReponseRoutes( routes );
		
		return incidentResp;
	}
	
	/**
	 * Simulates the emergency response to a vehicle accident
	 * 
	 * @param incident The parameters of the vehicle accident
	 * @return The incident response
	 * @throws IOException 
	 */
	public IncidentResponse simulateVehicleAccidentResponse( VehicleAccidentIncident incident ) throws Exception {
		
		IncidentRequirements respReq;
		IncidentResponse incidentResp;
		List<RespondingApparatus> apparatus;
		List<ResponseRoute> routes;
		GeometryFactory geomFactory;
		Point incidentLocation;
		
		// Creates the incident location coordinate
		geomFactory = new GeometryFactory( new PrecisionModel(), 4326 );
		incidentLocation = geomFactory.createPoint( new Coordinate( incident.getLongitude(), incident.getLatitude() ) );
		
		// Determines the number and type of apparatus that should respond
		respReq = respReqService.getIncidentRespReq( incident );
		
		// Determines the apparatus that should respond to the incident and gets their travel routes
		apparatus = this.respondingApparatusService.getRespondingApparatus( incident, respReq, incidentLocation );
		routes = this.routingService.getResponseTravelRoutes( incidentLocation, apparatus );
				
		// Creates the incident response package
		incidentResp = new IncidentResponse();
		incidentResp.setIncidentTitle( "Vehicle Accident" );
		incidentResp.setIncident( incident );
		incidentResp.setRespondingApparatus( apparatus );
		incidentResp.setReponseRoutes( routes );
		
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
		List<ResponseRoute> routes;
		GeometryFactory geomFactory;
		Point incidentLocation;
		
		// Creates the incident location coordinate
		geomFactory = new GeometryFactory( new PrecisionModel(), 4326 );
		incidentLocation = geomFactory.createPoint( new Coordinate( incident.getLongitude(), incident.getLatitude() ) );
		
		// Determines the number and type of apparatus that should respond
		respReq = respReqService.getIncidentRespReq( incident );
		
		// Determines the apparatus that should respond to the incident and gets their travel routes
		apparatus = this.respondingApparatusService.getRespondingApparatus( incident, respReq, incidentLocation );
		routes = this.routingService.getResponseTravelRoutes( incidentLocation, apparatus );
				
		// Creates the incident response package
		incidentResp = new IncidentResponse();
		incidentResp.setIncidentTitle( this.getIncidentTitle( incident ) );
		incidentResp.setIncident( incident );
		incidentResp.setRespondingApparatus( apparatus );
		incidentResp.setReponseRoutes( routes );
		
		return incidentResp;
	}
	
	/**
	 * Generates a title for the specified fuel spill incident
	 * 
	 * @param incident The incident object
	 * @return The incident title
	 */
	private String getIncidentTitle( FuelSpillIncident incident ) {
		
		String title;
		
		if ( incident.getSpillSize().equals( "small" ) ) {
			
			if ( incident.getIgnited() ) {
				
				title = "Small Buring Fuel Spill Incident";
			}
			else {
				
				title = "Small Fuel Spill Incident";
			}
		}
		else {
			
			if ( incident.getIgnited() ) {
				
				title = "Large Buring Fuel Spill Incident";
			}
			else {
				
				title = "Large Fuel Spill Incident";
			}
		}
		
		return title;
	}
	
	/**
	 * Generates a title for the specified structure fire incident
	 * 
	 * @param incident The incident object
	 * @return The incident title
	 */
	private String getIncidentTitle( StructureFireIncident incident ) {
		
		String title;
		
		switch( incident.getAlarmNumber() ) {
		
			case 1:
				title = "One Alarm Structure Fire";
				break;
			case 2:
				title = "Two Alarm Structure Fire";
				break;
			case 3:
				title = "Three Alarm Structure Fire";
				break;
			case 4:
				title = "Four Alarm Structure Fire";
				break;
			case 5:
				title = "Five Alarm Structure Fire";
				break;
			default:
				title = "Structure Fire";
		}
		
		return title;
	}
}
