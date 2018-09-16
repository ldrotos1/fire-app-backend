package org.fireapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.fireapp.dto.Coordinate;
import org.fireapp.dto.Location;
import org.fireapp.dto.RouteRequest;
import org.fireapp.dto.RouteResponse;
import org.fireapp.model.incident.response.RespondingApparatus;
import org.fireapp.model.incident.response.RespondingStation;
import org.fireapp.model.incident.response.ResponseRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vividsolutions.jts.geom.Point;

/**
 * This service class is used for getting travel route shapes
 * between incident locations and fire stations  
 * 
 * @author Louis Drotos
 *
 */
@Service
public class RoutingService {

	@Autowired
	private RouteShapeService routeShapeService;
	
	/**
	 * Returns a list of travel route shapes between an incident location 
	 * and the fire stations that the responding apparatuses are traveling from.
	 * 
	 * @param incidentLocation The incident location
	 * @param apparatuses The list of responding apparatus
	 * @return The travel route shapes
	 * @throws InterruptedException 
	 * @throws ExecutionException 
	 */
	public List<ResponseRoute> getResponseTravelRoutes( 
			Point incidentLocation, List<RespondingApparatus> apparatuses ) throws InterruptedException, ExecutionException {
		
		List<RespondingStation> stations = new ArrayList<RespondingStation>();
		List<RouteRequest> routeReq = new ArrayList<RouteRequest>();
		List<CompletableFuture<RouteResponse>> reqThreads = new ArrayList<CompletableFuture<RouteResponse>>();
		List<ResponseRoute> routes = new ArrayList<ResponseRoute>();
		
		// Creates the list of responding stations 
		for ( RespondingApparatus apparatus : apparatuses ) {
			
			RespondingStation station = new RespondingStation();
			station.setStationId( apparatus.getStation().getStationId() );
			station.setApparatus( new ArrayList<RespondingApparatus>() );
			
			if ( stations.contains( station ) ) {
			
				stations.get( stations.indexOf( station ) ).getApparatus().add( apparatus );
			}
			else {
				
				station.setLat( apparatus.getStation().getLat() );
				station.setLon( apparatus.getStation().getLon() );
				station.getApparatus().add( apparatus );
				stations.add( station );
			}
		}
		
		// Populates the route request object collection
		for ( RespondingStation station : stations ) {
			
			// Creates a route request object for this station
			RouteRequest req = new RouteRequest();
			List<Location> locations = new ArrayList<Location>();
			locations.add( new Location ( new Coordinate( station.getLat(), station.getLon() ) ) );
			locations.add( new Location ( new Coordinate( incidentLocation.getY(), incidentLocation.getX() ) ) );
			req.setLocations( locations );
			req.setStationId( station.getStationId() );
			
			routeReq.add( req );
		}
		
		// Submit each route request to the MapQuest Route service
		for ( RouteRequest request : routeReq ) {
			
			reqThreads.add( routeShapeService.getRoute( request ) );
		}
		
		// Waits for all the route request to complete
        CompletableFuture.allOf( reqThreads.toArray( new CompletableFuture[ reqThreads.size() ] ) ).join();
        
        // Builds the response routes
        for ( CompletableFuture<RouteResponse> response : reqThreads ) {
        	
        	ResponseRoute route = new ResponseRoute();
        	RouteResponse respRoute = response.get();
        	
        	// Gets the station corresponding to the route
        	RespondingStation station = this.getStationForRoute( respRoute, stations );
        	
        	// Adds the station and apparatus IDs to the route
        	route.setStationId( station.getStationId() );
        	route.setApparatusIds( new ArrayList<Integer>() );
        	
        	for ( RespondingApparatus apparatus : station.getApparatus() ) {
        		
        		route.getApparatusIds().add( apparatus.getApparatusId() );
        	}
        	
        	// Adds the route shape to the route
        	List<Double> routePoints = respRoute.getRoute().getShape().getShapePoints(); 
        	route.setWaypoints( new ArrayList<Coordinate>() );
        	
        	// Adds the station location to the beginning of the route
        	route.getWaypoints().add( new Coordinate( station.getLat(), station.getLon() ) );
        	
        	for ( int x = 0; x < routePoints.size(); x += 2 ) {
        		
        		route.getWaypoints().add( new Coordinate( routePoints.get( x ), routePoints.get( x + 1 ) ) );
        	}
        	
        	// Adds the incident location to the end of the route
        	route.getWaypoints().add( new Coordinate( incidentLocation.getY(), incidentLocation.getX() ) );
        	
        	routes.add( route );
        }
        
		return routes;
	}
	
	/**
	 * Gets the station that corresponds to the specified route from
	 * a provided list of stations
	 * 
	 * @param route The route
	 * @param stations The list of stations
	 * @return The station
	 */
	private RespondingStation getStationForRoute( RouteResponse route, List<RespondingStation> stations ) {
		
		RespondingStation targetStation = null;

		for ( RespondingStation station : stations ) {
			
			if ( station.getStationId().equals( route.getStationId() ) ) {
				
				targetStation = station;
				break;
			}
		}
		
		return targetStation;
	}
}
