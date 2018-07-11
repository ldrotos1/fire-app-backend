package org.fireapp.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.fireapp.AppPropUtils;
import org.fireapp.dto.Coordinate;
import org.fireapp.dto.Location;
import org.fireapp.dto.RouteMatrixRequest;
import org.fireapp.dto.RouteMatrixResponse;
import org.fireapp.model.incident.response.RespondingApparatus;
import org.fireapp.model.incident.response.RespondingStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.collections4.ListUtils;

import com.vividsolutions.jts.geom.Point;

/**
 * This service is used to make route matrix requests to the 
 * MapQuest Directions API
 * 
 * @author Louis Drotos
 *
 */
@Service
public class RouteMatrixService {

	@Autowired
	private AppPropUtils appPropUtils;
	
	public RouteMatrixService() {
		// Empty body
	}
	
	/**
	 * Computes the travel time and distances between an emergency
	 * incident and each of a set of stations. Returns a list of all
	 * the stations ordered by travel time in ascending order 
	 * 
	 * @param incident The incident location
	 * @param stations The set of stations
	 * @return The ordered list of stations
	 * @throws IOException 
	 */
	public List<RespondingStation> computeTravelTimesAndDist( Point incident, Set<RespondingStation> stations ) throws IOException {
	
		List<RespondingStation> stationList;
		List<RouteMatrixRequest> requestList;
		List<RouteMatrixResponse> responseList;
		int stationIndex = 0;
		
		// Converts the station set into a list
		stationList = new ArrayList<RespondingStation>( stations.size() );
		stationList.addAll( stations );
		
		// Gets the route matrix request objects
		requestList = this.getRouteMatrixRequests( incident, stationList );
		
		// Executes the MapQuest service calls
		responseList = this.executeRouteMatrixCalls( requestList );
		
		// Adds the travel time and distances to the station and apparatus objects
		for ( RouteMatrixResponse resp : responseList ) {
		
			for ( int x = 1; x < resp.getTime().size(); x++ ) {
				
				// Adds the travel time and distance values to the apparatus
				for ( RespondingApparatus apparatus : stationList.get( stationIndex ).getApparatus() ) {
					
					apparatus.setTravelDistance( resp.getDistance().get(x) );
					apparatus.setTravelTime( resp.getTime().get(x) );
				}
				
				// Adds the travel time and distance values to the station
				stationList.get( stationIndex ).setTravelDistance( resp.getDistance().get(x) );
				stationList.get( stationIndex ).setTravelTime( resp.getTime().get(x) );
				
				stationIndex++;
			} 
		}

		// Sorts the stations by travel time in ascending order
		Collections.sort( stationList );

		return stationList;
	}
	
	/**
	 * Generates a list of route matrix request object where each 
	 * request object has a maximum of 49 station locations 
	 * 
	 * @param incident The incident location
	 * @param stations The station set 
	 * @return The route matrix request list
	 */
	private List<RouteMatrixRequest> getRouteMatrixRequests( Point incident, List<RespondingStation> stations ) {
		
		List<List<RespondingStation>> splitList;
		List<RouteMatrixRequest> reqList;
		RouteMatrixRequest req;
		List<Location> locationList;
		Coordinate coord;
		
		splitList = ListUtils.partition( stations, 49 );
		reqList = new ArrayList<RouteMatrixRequest>();
		
		for ( List<RespondingStation> list : splitList ) {
			
			locationList = new ArrayList<Location>();
			
			// Add the incident location as the first location list item
			coord =  new Coordinate( incident.getY(), incident.getX() );
			locationList.add( new Location( coord )  );
			
			// Add each of the station locations to the location list
			for( RespondingStation station : list ) {
				
				coord =  new Coordinate( station.getLat(), station.getLon() );
				locationList.add( new Location( coord )  );
			}
			
			// Creates the request object
			req = new RouteMatrixRequest();
			req.setLocations( locationList );
			reqList.add( req );
		}
		
		return reqList;
	}
	
	/**
	 * Executes a set of route matrix requests 
	 * 
	 * @param requests The requests
	 * @return A list of route matrix responses
	 * @throws IOException
	 */
	private List<RouteMatrixResponse> executeRouteMatrixCalls( List<RouteMatrixRequest> requests ) throws IOException {
		
		RestTemplate restTemplate = new RestTemplate();
		List<RouteMatrixResponse> list = new ArrayList<RouteMatrixResponse> ();

		for( RouteMatrixRequest req : requests ) {
			
			// Creates and executes the request
		    RouteMatrixResponse response = restTemplate.postForObject( appPropUtils.getRouteMatrixUrl(), req, RouteMatrixResponse.class );
			list.add( response );
		}
		
		return list;
	}
}
