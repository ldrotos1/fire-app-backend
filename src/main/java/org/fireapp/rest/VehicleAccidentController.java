package org.fireapp.rest;

import org.fireapp.model.incident.IncidentResponse;
import org.fireapp.model.incident.VehicleAccidentIncident;
import org.fireapp.rest.validator.VehicleAccidentIncidentReqValidator;
import org.fireapp.service.IncidentRespSimService;
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
	private IncidentRespSimService incidentRespSimService;
	
	@Autowired
	private VehicleAccidentIncidentReqValidator incidentValidator;
	
	@InitBinder
	protected void initBinderVehAcc( WebDataBinder binder ) {
		binder.addValidators( incidentValidator );
	}
	
	@RequestMapping( value = "/incident", method = RequestMethod.POST, produces = "application/json" )
	public ResponseEntity<Object> simulateVehicleAccidentResponse( 
			@Validated @RequestBody VehicleAccidentIncident incident, BindingResult result ) {
		
		// Checks for request errors error
		if ( result.hasErrors() ) {
			
			return new ResponseEntity<Object>( result.getFieldErrors(), HttpStatus.BAD_REQUEST );
		}	
		
		// Simulates the response
		IncidentResponse resp = incidentRespSimService.simulateVehicleAccidentResponse( incident );
		return new ResponseEntity<Object>( resp, HttpStatus.OK ); 
	}
}
