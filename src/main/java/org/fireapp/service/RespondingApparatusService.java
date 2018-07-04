package org.fireapp.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.fireapp.dao.RespondingStationDao;
import org.fireapp.model.incident.FuelSpillIncident;
import org.fireapp.model.incident.IncidentRequirements;
import org.fireapp.model.incident.MassCasualtyIncident;
import org.fireapp.model.incident.MedicalEmergencyIncident;
import org.fireapp.model.incident.StructureFireIncident;
import org.fireapp.model.incident.VehicleAccidentIncident;
import org.fireapp.model.incident.WaterRescueIncident;
import org.fireapp.model.incident.response.RespondingApparatus;
import org.fireapp.model.incident.response.RespondingStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vividsolutions.jts.geom.Point;

/**
 * This service provides methods for determining the apparatus
 * that should respond to various types of emergency incidents
 * 
 * @author Louis Drotos
 *
 */
@Service
public class RespondingApparatusService {

	private static final int BATTALION_CHIEF_ID = 7;
	private static final int EMS_SUPER_ID = 12;
	private static final int ENGINE = 1;
	private static final int MEDIC = 6;
	private static final int BOAT_ID = 18;
	private static final int RESCUE_ID = 5;
	private static final int HAZMAT_ID = 9;
	private static final int FOAM_ID = 10;
	private static final int FOAM_ENGINE_ID = 22;
	private static final int TRUCK_ID = 2;
	private static final int TOWER_ID = 3;
	private static final int TANKER_ID = 8;
	private static final int MOBILE_COMMAND_ID = 13;
	private static final int MASS_CAS_SUPPORT_ID = 17;
	private static final int MED_BUS_ID = 23;
	private static final String AERIAL_CATEGORY = "Aerial Support";
	
	@Autowired
	private RespondingStationDao respondingStationDao;
	
	@Autowired
	private RoutingService routingService;
	
	/**
	 * Gets a list of apparatus that should respond to the specified 
	 * emergency incident 
	 * 
	 * @param incident The medical emergency incident
	 * @param respReq The response requirements
	 * @param location The incident location
	 * @return The list of responding apparatus
	 * @throws IOException 
	 */
	public List<RespondingApparatus> getRespondingApparatus( 
			MedicalEmergencyIncident incident, IncidentRequirements respReq, Point location ) throws IOException {
		
		Set<RespondingStation> stations;
		List<RespondingApparatus> apparatus;
		
		// Gets the stations that may send apparatus to the incident 
		stations = new HashSet<RespondingStation>();
		stations.addAll( respondingStationDao.getNearestStations( location, 5 ) );
		
		// Computes travel times from stations
		this.routingService.computeTravelTimesAndDist( location, stations );
		
		return null;
	}
	
	/**
	 * Gets a list of apparatus that should respond to the specified 
	 * emergency incident 
	 * 
	 * @param incident The structure fire incident
	 * @param respReq The response requirements
	 * @param location The incident location
	 * @return The list of responding apparatus
	 * @throws IOException 
	 */
	public List<RespondingApparatus> getRespondingApparatus( 
			StructureFireIncident incident, IncidentRequirements respReq, Point location ) throws IOException { 
		
		Set<RespondingStation> stations;
		List<RespondingApparatus> apparatus;
		int alarmNum = incident.getAlarmNumber();
		
		// Gets the stations that may send apparatus to the incident 
		stations = new HashSet<RespondingStation>();
		stations.addAll( respondingStationDao.getNearestStations( location, 10 * alarmNum ) );
		stations.addAll( respondingStationDao.getNearestStationsWithApparatusCategory( location, 6 * alarmNum, AERIAL_CATEGORY ) );
		stations.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 6 * alarmNum, BATTALION_CHIEF_ID ) );
		stations.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3 * alarmNum, EMS_SUPER_ID ) );
		stations.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3 * alarmNum, RESCUE_ID ) );
		
		if ( !incident.getHydrantAccess() ) {
			
			// No hydrant access - get stations with tankers
			stations.addAll( respondingStationDao.getAllStationsWithApparatusType( TANKER_ID ) );
		}
		
		// Computes travel times from stations
		this.routingService.computeTravelTimesAndDist( location, stations );
		
		return null;
	}
	
	/**
	 * Gets a list of apparatus that should respond to the specified 
	 * emergency incident 
	 * 
	 * @param incident The vehicle accident incident
	 * @param respReq The response requirements
	 * @param location The incident location
	 * @return The list of responding apparatus
	 * @throws IOException 
	 */
	public List<RespondingApparatus> getRespondingApparatus( 
			VehicleAccidentIncident incident, IncidentRequirements respReq, Point location ) throws IOException { 
		
		Set<RespondingStation> stations;
		List<RespondingApparatus> apparatus;
		int vehicleCount = incident.getVehicles();
		int injuryCount = incident.getInjuries();
		boolean entrapment = incident.getEntrapment();
		boolean hazmat = incident.getEntrapment();
		
		// Gets the stations that may send apparatus to the incident 
		stations = new HashSet<RespondingStation>();
		stations.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3 * vehicleCount, ENGINE ) );
		stations.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3 * injuryCount, MEDIC ) );
		
		if ( injuryCount > 2 || vehicleCount > 2 || entrapment || hazmat ) {
			
			stations.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3 * injuryCount, BATTALION_CHIEF_ID ) );
		}
		
		if ( injuryCount > 2 || entrapment ) {
			
			stations.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3 * injuryCount, EMS_SUPER_ID ) );
		}
		
		if ( entrapment ) {
			
			stations.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 6 * injuryCount, RESCUE_ID ) );
		}
		
		if ( hazmat ) {
			
			stations.addAll( respondingStationDao.getAllStationsWithApparatusType( HAZMAT_ID ) );
		}
		
		// Computes travel times from stations
		this.routingService.computeTravelTimesAndDist( location, stations );
		
		return null;
	}
	
	/**
	 * Gets a list of apparatus that should respond to the specified 
	 * emergency incident 
	 * 
	 * @param incident The mass casualty incident
	 * @param respReq The response requirements
	 * @param location The incident location
	 * @return The list of responding apparatus
	 * @throws IOException 
	 */
	public List<RespondingApparatus> getRespondingApparatus( 
			MassCasualtyIncident incident, IncidentRequirements respReq, Point location ) throws IOException { 
		
		Set<RespondingStation> stations;
		List<RespondingApparatus> apparatus;
		int massCasAlarmNum = incident.getMassCasAlarmNum();
		
		// Gets the stations that may send apparatus to the incident 
		stations = new HashSet<RespondingStation>();
		
		if ( massCasAlarmNum == 0 ) {
			
			stations.addAll( respondingStationDao.getNearestStations( location, 10 ) );
			stations.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3, BATTALION_CHIEF_ID ) );
			stations.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 6, EMS_SUPER_ID ) );
		}
		else {
			
			stations.addAll( respondingStationDao.getNearestStations( location, 16 * massCasAlarmNum ) );
			stations.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3 * massCasAlarmNum, BATTALION_CHIEF_ID ) );
			stations.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 6 * massCasAlarmNum, EMS_SUPER_ID ) );
			stations.addAll( respondingStationDao.getAllStationsWithApparatusType( MOBILE_COMMAND_ID ) );
			stations.addAll( respondingStationDao.getAllStationsWithApparatusType( MASS_CAS_SUPPORT_ID ) );
			stations.addAll( respondingStationDao.getAllStationsWithApparatusType( MED_BUS_ID ) );
		}

		// Computes travel times from stations
		this.routingService.computeTravelTimesAndDist( location, stations );
		
		return null;
	}
	
	/**
	 * Gets a list of apparatus that should respond to the specified 
	 * emergency incident 
	 * 
	 * @param incident The water rescue incident
	 * @param respReq The response requirements
	 * @param location The incident location
	 * @return The list of responding apparatus
	 * @throws IOException 
	 */
	public List<RespondingApparatus> getRespondingApparatus( 
			WaterRescueIncident incident, IncidentRequirements respReq, Point location ) throws IOException { 
		
		Set<RespondingStation> stations;
		List<RespondingApparatus> apparatus;
		
		// Gets the stations that may send apparatus to the incident 
		stations = new HashSet<RespondingStation>();
		stations.addAll( respondingStationDao.getNearestStations( location, 5 ) );
		stations.addAll( respondingStationDao.getAllStationsWithApparatusType( BOAT_ID ) );
		stations.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3, BATTALION_CHIEF_ID ) );
		stations.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3, RESCUE_ID ) );
		
		List<Integer> aerialIds = new ArrayList<Integer>( 2 );
		aerialIds.add( TRUCK_ID );
		aerialIds.add( TOWER_ID );
		stations.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3, aerialIds ) );

		// Computes travel times from stations
		this.routingService.computeTravelTimesAndDist( location, stations );
		
		return null;
	}
	
	/**
	 * Gets a list of apparatus that should respond to the specified 
	 * emergency incident 
	 * 
	 * @param incident The fuel spill incident
	 * @param respReq The response requirements
	 * @param location The incident location
	 * @return The list of responding apparatus
	 * @throws IOException 
	 */
	public List<RespondingApparatus> getRespondingApparatus( 
			FuelSpillIncident incident, IncidentRequirements respReq, Point location ) throws IOException { 
		
		Set<RespondingStation> stations;
		List<RespondingApparatus> apparatus;
		
		// Gets the stations that may send apparatus to the incident 
		stations = new HashSet<RespondingStation>();
		stations.addAll( respondingStationDao.getAllStationsWithApparatusType( HAZMAT_ID ) );
		
		if ( incident.getSpillSize().equals( "small" ) && !incident.getIgnited() ) {
			
			// Get stations for a small spill that has not ignited
			stations.addAll( respondingStationDao.getNearestStations( location, 5 ) );
		}
		else if ( incident.getSpillSize().equals( "small" ) && incident.getIgnited() ) {
			
			// Get stations for a small spill that has ignited
			stations.addAll( respondingStationDao.getNearestStations( location, 5 ) );
			stations.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3, BATTALION_CHIEF_ID ) );
			
			List<Integer> foamIds = new ArrayList<Integer>( 2 );
			foamIds.add( FOAM_ID );
			foamIds.add( FOAM_ENGINE_ID );
			stations.addAll( respondingStationDao.getAllStationsWithApparatusType( foamIds ) );
		}
		else {
			
			// Get stations for a large spill
			stations.addAll( respondingStationDao.getNearestStations( location, 10 ) );
			stations.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3, BATTALION_CHIEF_ID ) );
			stations.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3, EMS_SUPER_ID ) );
			
			List<Integer> foamIds = new ArrayList<Integer>( 2 );
			foamIds.add( FOAM_ID );
			foamIds.add( FOAM_ENGINE_ID );
			stations.addAll( respondingStationDao.getAllStationsWithApparatusType( foamIds ) );
		}
		
		// Computes travel times from stations
		this.routingService.computeTravelTimesAndDist( location, stations );
		
		return null;
	}
}
