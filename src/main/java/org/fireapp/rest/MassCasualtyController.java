package org.fireapp.rest;

import org.fireapp.model.incident.IncidentResponse;
import org.fireapp.model.incident.MassCasualtyIncident;
import org.fireapp.rest.validator.MassCasIncidentReqValidator;
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
 * responses to mass casualty incidents
 * 
 * @author Louis Drotos
 *
 */
@RestController
@RequestMapping( "/masscasualty" )
public class MassCasualtyController {

	@Autowired
	private IncidentRespSimService incidentRespSimService;
	
	@Autowired
	private MassCasIncidentReqValidator incidentValidator;
	
	@InitBinder
	protected void initBinderMassCas( WebDataBinder binder ) {
		binder.addValidators( incidentValidator );
	}
	
	/**
	 * Simulates the fire department response to a mass casualty incident
	 * 
	 * @param incident The incident 
	 * @param result The list of validation errors
	 * @return The incident response
	 */
	@RequestMapping( value = "/incident", method = RequestMethod.POST, produces = "application/json" )
	public ResponseEntity<Object> simulateMassCasualtyResponse( 
			@Validated @RequestBody MassCasualtyIncident incident, BindingResult result ) {
		
		// Checks for request errors error
		if ( result.hasErrors() ) {
			
			return new ResponseEntity<Object>( result.getFieldErrors(), HttpStatus.BAD_REQUEST );
		}	
			
		// Simulates the response
		IncidentResponse resp = incidentRespSimService.simulateMassCasualtyResponse( incident );
		return new ResponseEntity<Object>( resp, HttpStatus.OK ); 
	}
}
