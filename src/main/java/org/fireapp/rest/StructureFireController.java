package org.fireapp.rest;

import org.fireapp.model.incident.IncidentResponse;
import org.fireapp.model.incident.StructureFireIncident;
import org.fireapp.rest.validator.StructureFireIncidentReqValidator;
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
 * responses to structure fires
 * 
 * @author Louis Drotos
 *
 */
@RestController
@RequestMapping( "/fire" )
public class StructureFireController {

	@Autowired
	private IncidentRespSimService incidentRespSimService;
	
	@Autowired
	private StructureFireIncidentReqValidator incidentValidator;
	
	@InitBinder
	protected void initBinderFire( WebDataBinder binder ) {
		binder.addValidators( incidentValidator );
	}
	
	@RequestMapping( value = "/incident", method = RequestMethod.POST, produces = "application/json" )
	public ResponseEntity<Object> simulateStructureFireResponse( 
			@Validated @RequestBody StructureFireIncident incidentFire, BindingResult result ) {
		
		// Checks for request errors error
		if ( result.hasErrors() ) {
			
			return new ResponseEntity<Object>( result.getFieldErrors(), HttpStatus.BAD_REQUEST );
		}
		
		// Simulates the response
		IncidentResponse resp = incidentRespSimService.simulateStructureFireResponse( incidentFire );
		return new ResponseEntity<Object>( resp, HttpStatus.OK );
	}
}
