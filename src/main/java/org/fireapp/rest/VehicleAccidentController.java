package org.fireapp.rest;

import java.util.HashMap;
import java.util.Map;

import org.fireapp.model.incident.VehicleAccidentIncident;
import org.fireapp.model.incident.response.IncidentResponse;
import org.fireapp.rest.validator.VehicleAccidentIncidentReqValidator;
import org.fireapp.service.BorderService;
import org.fireapp.service.IncidentSimulatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Provides a REST service for simulating fire department 
 * responses to vehicle accident incidents
 * 
 * @author Louis Drotos
 *
 */
@RestController
@RequestMapping( "/vehicleaccident" )
public class VehicleAccidentController {

	@Autowired
	private IncidentSimulatorService incidentRespSimService;
	
	@Autowired
	private VehicleAccidentIncidentReqValidator incidentValidator;
	
	@Autowired
	private BorderService borderService;
	
	@InitBinder
	protected void initBinderVehAcc( WebDataBinder binder ) {
		binder.addValidators( incidentValidator );
	}
	
	/**
	 * Simulates the fire department response to a vehicle accident
	 * 
	 * @param incident The incident 
	 * @param result The list of validation errors
	 * @return The incident response
	 */
	@RequestMapping( value = "/incident", method = RequestMethod.POST, produces = "application/json" )
	public ResponseEntity<Object> simulateVehicleAccidentResponse( 
			@Validated @RequestBody VehicleAccidentIncident incident, BindingResult result ) {
		
		// Checks for request errors error
		if ( result.hasErrors() ) {
			
			return new ResponseEntity<Object>( result.getFieldErrors(), HttpStatus.BAD_REQUEST );
		}
		
		// Ensure the location is within the area of interest
		Boolean locationValid = this.borderService.isLocationWithAoi( incident.getLatitude(), incident.getLongitude() );
		
		if ( !locationValid ) {
			
			Map<String,String> resp = new HashMap<String,String>();
			resp.put( "message", "Location is outside of simulator area." );
			return new ResponseEntity<Object>( resp, HttpStatus.BAD_REQUEST ); 
		}
		
		// Simulates the response
		IncidentResponse resp = incidentRespSimService.simulateVehicleAccidentResponse( incident );
		return new ResponseEntity<Object>( resp, HttpStatus.OK ); 
	}
}
