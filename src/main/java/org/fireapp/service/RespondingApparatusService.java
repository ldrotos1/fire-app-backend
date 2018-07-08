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

	private static final int ENGINE_ID = 1;
	private static final int MEDIC_ID = 6;
	private static final int TRUCK_ID = 2;
	private static final int TOWER_ID = 3;
	private static final int TILLER_ID = 4;
	private static final int BATTALION_CHIEF_ID = 7;
	private static final int EMS_SUPER_ID = 12;
	private static final int RESCUE_ID = 5;
	private static final int HAZMAT_ID = 9;
	private static final int FOAM_ID = 10;
	private static final int FOAM_ENGINE_ID = 22;
	private static final int TANKER_ID = 8;
	private static final int BOAT_ID = 18;
	private static final int MOBILE_COMMAND_ID = 13;
	private static final int MASS_CAS_SUPPORT_ID = 17;
	private static final int MED_BUS_ID = 23;
	private static final String AERIAL_CATEGORY = "Aerial Support";
	
	@Autowired
	private RespondingStationDao respondingStationDao;
	
	@Autowired
	private MapQuestDirectionsService mapQuestService;
	
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
		
		Set<RespondingStation> stationSet;
		List<RespondingStation> stationList;
		List<RespondingApparatus> apparatus;
		
		// Gets the stations that may send apparatus to the incident 
		stationSet = new HashSet<RespondingStation>();
		stationSet.addAll( respondingStationDao.getNearestStations( location, 5 ) );
		
		// Computes travel times from stations
		stationList = this.mapQuestService.computeTravelTimesAndDist( location, stationSet );
		
		// Gets the responding apparatus
		apparatus = getRespondingApparatus( respReq, stationList );
		
		return apparatus;
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
		
		Set<RespondingStation> stationSet;
		List<RespondingStation> stationList;
		List<RespondingApparatus> apparatus;
		int alarmNum = incident.getAlarmNumber();
		
		// Gets the stations that may send apparatus to the incident 
		stationSet = new HashSet<RespondingStation>();
		stationSet.addAll( respondingStationDao.getNearestStations( location, 10 * alarmNum ) );
		stationSet.addAll( respondingStationDao.getNearestStationsWithApparatusCategory( location, 6 * alarmNum, AERIAL_CATEGORY ) );
		stationSet.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 6 * alarmNum, BATTALION_CHIEF_ID ) );
		stationSet.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3 * alarmNum, EMS_SUPER_ID ) );
		stationSet.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3 * alarmNum, RESCUE_ID ) );
		
		if ( !incident.getHydrantAccess() ) {
			
			// No hydrant access - get stations with tankers
			stationSet.addAll( respondingStationDao.getAllStationsWithApparatusType( TANKER_ID ) );
		}
		
		// Computes travel times from stations
		stationList = this.mapQuestService.computeTravelTimesAndDist( location, stationSet );
		
		// Gets the responding apparatus
		apparatus = getRespondingApparatus( respReq, stationList );
		
		return apparatus;
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
		
		Set<RespondingStation> stationSet;
		List<RespondingStation> stationList;
		List<RespondingApparatus> apparatus;
		int vehicleCount = incident.getVehicles();
		int injuryCount = incident.getInjuries();
		boolean entrapment = incident.getEntrapment();
		boolean hazmat = incident.getEntrapment();
		
		// Gets the stations that may send apparatus to the incident 
		stationSet = new HashSet<RespondingStation>();
		stationSet.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3 * vehicleCount, ENGINE_ID ) );
		stationSet.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3 * injuryCount, MEDIC_ID ) );
		
		if ( injuryCount > 2 || vehicleCount > 2 || entrapment || hazmat ) {
			
			stationSet.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3 * injuryCount, BATTALION_CHIEF_ID ) );
		}
		
		if ( injuryCount > 2 || entrapment ) {
			
			stationSet.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3 * injuryCount, EMS_SUPER_ID ) );
		}
		
		if ( entrapment ) {
			
			stationSet.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 6 * injuryCount, RESCUE_ID ) );
		}
		
		if ( hazmat ) {
			
			stationSet.addAll( respondingStationDao.getAllStationsWithApparatusType( HAZMAT_ID ) );
		}
		
		// Computes travel times from stations
		stationList = this.mapQuestService.computeTravelTimesAndDist( location, stationSet );
		
		// Gets the responding apparatus
		apparatus = getRespondingApparatus( respReq, stationList );
		
		return apparatus;
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
		
		Set<RespondingStation> stationSet;
		List<RespondingStation> stationList;
		List<RespondingApparatus> apparatus;
		int massCasAlarmNum = incident.getMassCasAlarmNum();
		
		// Gets the stations that may send apparatus to the incident 
		stationSet = new HashSet<RespondingStation>();
		
		if ( massCasAlarmNum == 0 ) {
			
			stationSet.addAll( respondingStationDao.getNearestStations( location, 10 ) );
			stationSet.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3, BATTALION_CHIEF_ID ) );
			stationSet.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 6, EMS_SUPER_ID ) );
		}
		else {
			
			stationSet.addAll( respondingStationDao.getNearestStations( location, 16 * massCasAlarmNum ) );
			stationSet.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3 * massCasAlarmNum, BATTALION_CHIEF_ID ) );
			stationSet.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 6 * massCasAlarmNum, EMS_SUPER_ID ) );
			stationSet.addAll( respondingStationDao.getAllStationsWithApparatusType( MOBILE_COMMAND_ID ) );
			stationSet.addAll( respondingStationDao.getAllStationsWithApparatusType( MASS_CAS_SUPPORT_ID ) );
			stationSet.addAll( respondingStationDao.getAllStationsWithApparatusType( MED_BUS_ID ) );
		}

		// Computes travel times from stations
		stationList = this.mapQuestService.computeTravelTimesAndDist( location, stationSet );
		
		// Gets the responding apparatus
		apparatus = getRespondingApparatus( respReq, stationList );
		
		return apparatus;
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
		
		Set<RespondingStation> stationSet;
		List<RespondingStation> stationList;
		List<RespondingApparatus> apparatus;
		
		// Gets the stations that may send apparatus to the incident 
		stationSet = new HashSet<RespondingStation>();
		stationSet.addAll( respondingStationDao.getNearestStations( location, 5 ) );
		stationSet.addAll( respondingStationDao.getAllStationsWithApparatusType( BOAT_ID ) );
		stationSet.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3, BATTALION_CHIEF_ID ) );
		stationSet.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3, RESCUE_ID ) );
		
		List<Integer> aerialIds = new ArrayList<Integer>( 2 );
		aerialIds.add( TRUCK_ID );
		aerialIds.add( TOWER_ID );
		stationSet.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3, aerialIds ) );

		// Computes travel times from stations
		stationList = this.mapQuestService.computeTravelTimesAndDist( location, stationSet );
		
		// Gets the responding apparatus
		apparatus = getRespondingApparatus( respReq, stationList );
		
		return apparatus;
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
		
		Set<RespondingStation> stationSet;
		List<RespondingStation> stationList;
		List<RespondingApparatus> apparatus;
		
		// Gets the stations that may send apparatus to the incident 
		stationSet = new HashSet<RespondingStation>();
		stationSet.addAll( respondingStationDao.getAllStationsWithApparatusType( HAZMAT_ID ) );
		
		if ( incident.getSpillSize().equals( "small" ) && !incident.getIgnited() ) {
			
			// Get stations for a small spill that has not ignited
			stationSet.addAll( respondingStationDao.getNearestStations( location, 5 ) );
		}
		else if ( incident.getSpillSize().equals( "small" ) && incident.getIgnited() ) {
			
			// Get stations for a small spill that has ignited
			stationSet.addAll( respondingStationDao.getNearestStations( location, 5 ) );
			stationSet.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3, BATTALION_CHIEF_ID ) );
			
			List<Integer> foamIds = new ArrayList<Integer>( 2 );
			foamIds.add( FOAM_ID );
			foamIds.add( FOAM_ENGINE_ID );
			stationSet.addAll( respondingStationDao.getAllStationsWithApparatusType( foamIds ) );
		}
		else {
			
			// Get stations for a large spill
			stationSet.addAll( respondingStationDao.getNearestStations( location, 10 ) );
			stationSet.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3, BATTALION_CHIEF_ID ) );
			stationSet.addAll( respondingStationDao.getNearestStationsWithApparatusType( location, 3, EMS_SUPER_ID ) );
			
			List<Integer> foamIds = new ArrayList<Integer>( 2 );
			foamIds.add( FOAM_ID );
			foamIds.add( FOAM_ENGINE_ID );
			stationSet.addAll( respondingStationDao.getAllStationsWithApparatusType( foamIds ) );
		}
		
		// Computes travel times from stations
		stationList = this.mapQuestService.computeTravelTimesAndDist( location, stationSet );
		
		// Gets the responding apparatus
		apparatus = getRespondingApparatus( respReq, stationList );
		
		return apparatus;
	}
	
	/**
	 * Returns a list of apparatus that meet the specified response
	 * requirements and have the shortest travel time. Assumes the
	 * station list is sorted by travel time in ascending order 
	 * 
	 * @param respReq The incident requirements
	 * @param stations The ordered station list
	 * @return The apparatus list
	 */
	private List<RespondingApparatus> getRespondingApparatus( IncidentRequirements respReq, 
			List<RespondingStation> stations ) {
		
		List<RespondingApparatus> list = new ArrayList<RespondingApparatus>();
		
		// Iterates through each station
		for ( RespondingStation station : stations ) {
			
			// Check to see if response requirements have been met
			if ( respReq.getTotalApparatusCount() == 0 ) { 
				
				break; 
			}
			
			// Iterates through each apparatus assigned to the current station
			for ( RespondingApparatus apparatus : station.getApparatus() ) {
				
				// Check to see if response requirements have been met
				if ( respReq.getTotalApparatusCount() == 0 ) {
					
					break; 
				}
				
				// Adds the apparatus to the response if it is needed
				this.addApparatusToResponse( respReq, apparatus, list );
			}
		}
		
		return list;
	}
	
	/**
	 * Determines if a apparatus should be included with a list of apparatus
	 * that will respond to an emergency incident. The apparatus will be added
	 * to the list if the apparatus type is needed per the incident response 
	 * requirements. If the apparatus is added to the list then the response
	 * requirements will be modified to reflect the inclusion of the apparatus
	 * in the list   
	 * 
	 * @param respReq The incident response requirements
	 * @param apparatus The apparatus
	 * @param list The list of responding apparatus
	 */
	private void addApparatusToResponse( IncidentRequirements respReq, 
			RespondingApparatus apparatus, List<RespondingApparatus> list ) {
		
		int apparatusTypeId = apparatus.getApparatusType().getApparatusTypeId();
		int apparatusTypeNeededCount;
		
		if ( apparatusTypeId == ENGINE_ID ) {
			
			apparatusTypeNeededCount = respReq.getEngineCount();
			if ( apparatusTypeNeededCount > 0 ) {
				
				list.add( apparatus );
				respReq.setEngineCount( apparatusTypeNeededCount - 1 );
			}
		}
		else if ( apparatusTypeId == MEDIC_ID ) {
			
			apparatusTypeNeededCount = respReq.getMedicCount();
			if ( apparatusTypeNeededCount > 0 ) {
				
				list.add( apparatus );
				respReq.setMedicCount( apparatusTypeNeededCount - 1 );
			}
		}
		else if ( apparatusTypeId == TRUCK_ID || apparatusTypeId == TOWER_ID ) {
			
			apparatusTypeNeededCount = respReq.getAerialCount();
			if ( apparatusTypeNeededCount > 0 ) {
				
				list.add( apparatus );
				respReq.setAerialCount( apparatusTypeNeededCount - 1 );
			}
			else {
				
				apparatusTypeNeededCount = respReq.getTruckOrTowerCount();
				if ( apparatusTypeNeededCount > 0 ) {
					
					list.add( apparatus );
					respReq.setTruckOrTowerCount( apparatusTypeNeededCount - 1 );
				}
			}
			
		}
		else if ( apparatusTypeId == TILLER_ID ) {
			
			apparatusTypeNeededCount = respReq.getAerialCount();
			if ( apparatusTypeNeededCount > 0 ) {
				
				list.add( apparatus );
				respReq.setAerialCount( apparatusTypeNeededCount - 1 );
			}
			
		}
		else if ( apparatusTypeId == BATTALION_CHIEF_ID ) {
			
			apparatusTypeNeededCount = respReq.getBattalionChiefCount();
			if ( apparatusTypeNeededCount > 0 ) {
				
				list.add( apparatus );
				respReq.setBattalionChiefCount( apparatusTypeNeededCount - 1 );
			}
		}
		else if ( apparatusTypeId == EMS_SUPER_ID ) {
			
			apparatusTypeNeededCount = respReq.getEmsSupervisorCount();
			if ( apparatusTypeNeededCount > 0 ) {
				
				list.add( apparatus );
				respReq.setEmsSupervisorCount( apparatusTypeNeededCount - 1 );
			}
		}
		else if ( apparatusTypeId == RESCUE_ID ) {
			
			apparatusTypeNeededCount = respReq.getRescueCount();
			if ( apparatusTypeNeededCount > 0 ) {
				
				list.add( apparatus );
				respReq.setRescueCount( apparatusTypeNeededCount - 1 );
			}
		}
		else if ( apparatusTypeId == HAZMAT_ID ) {
			
			apparatusTypeNeededCount = respReq.getHazmatCount();
			if ( apparatusTypeNeededCount > 0 ) {
				
				list.add( apparatus );
				respReq.setHazmatCount( apparatusTypeNeededCount - 1 );
			}
		}
		else if ( apparatusTypeId == FOAM_ID || apparatusTypeId == FOAM_ENGINE_ID ) {
			
			apparatusTypeNeededCount = respReq.getFoamCount();
			if ( apparatusTypeNeededCount > 0 ) {
				
				list.add( apparatus );
				respReq.setFoamCount( apparatusTypeNeededCount - 1 );
			}
		}
		else if ( apparatusTypeId == TANKER_ID ) {
			
			apparatusTypeNeededCount = respReq.getTankerCount();
			if ( apparatusTypeNeededCount > 0 ) {
				
				list.add( apparatus );
				respReq.setTankerCount( apparatusTypeNeededCount - 1 );
			}
		}
		else if ( apparatusTypeId == BOAT_ID ) {
			
			apparatusTypeNeededCount = respReq.getBoatCount();
			if ( apparatusTypeNeededCount > 0 ) {
				
				list.add( apparatus );
				respReq.setBoatCount( apparatusTypeNeededCount - 1 );
			}
		}
		else if ( apparatusTypeId == MOBILE_COMMAND_ID ) {
			
			apparatusTypeNeededCount = respReq.getMobileCommandCount();
			if ( apparatusTypeNeededCount > 0 ) {
				
				list.add( apparatus );
				respReq.setMobileCommandCount( apparatusTypeNeededCount - 1 );
			}
		}
		else if ( apparatusTypeId == MASS_CAS_SUPPORT_ID || apparatusTypeId == MED_BUS_ID ) {
			
			apparatusTypeNeededCount = respReq.getMassCasSupCount();
			if ( apparatusTypeNeededCount > 0 ) {
				
				list.add( apparatus );
				respReq.setMassCasSupCount( apparatusTypeNeededCount - 1 );
			}
		}
	}
}







