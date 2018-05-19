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

@RestController
@RequestMapping("/station")
public class StationController {

	@Autowired
	StationService stationService;
	
	@RequestMapping( value = "/all", method = RequestMethod.GET, produces = "application/json" )
    public List<StationLiteLocation> getAllStations() {
        
		return stationService.getAllStations();
    }
	
	@RequestMapping( value = "/byid", method = RequestMethod.GET, produces = "application/json" )
	public Station getStation( @RequestParam( "id" ) Integer id ) {
		
		return stationService.getStation( id );
	}
}
