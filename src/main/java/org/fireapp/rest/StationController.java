package org.fireapp.rest;

import java.util.List;

import org.fireapp.model.StationLiteLocation;
import org.fireapp.model.Station;
import org.fireapp.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Provides REST services for accessing information about 
 * fire stations
 * 
 * @author Louis Drotos
 *
 */
@RestController
@RequestMapping("/station")
public class StationController {

	@Autowired
	StationService stationService;
	
	/**
	 * Returns a list of all fire stations in a simplified format
	 * that includes the station ID, name, number, and designator
	 * 
	 * @return The station list
	 */
	@RequestMapping( value = "/all", method = RequestMethod.GET, produces = "application/json" )
    public List<StationLiteLocation> getAllStations() {
        
		return stationService.getAllStations();
    }
	
	/**
	 * Returns a object representing the specified fire station
	 * 
	 * @param id The station ID
	 * @return The station object
	 */
	@RequestMapping( value = "/byid", method = RequestMethod.GET, produces = "application/json" )
	public Station getStation( @RequestParam( "id" ) Integer id ) {
		
		return stationService.getStation( id );
	}
}
