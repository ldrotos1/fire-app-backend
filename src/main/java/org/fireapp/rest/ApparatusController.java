package org.fireapp.rest;

import java.util.List;

import org.fireapp.model.ApparatusType;
import org.fireapp.model.ApparatusTypeLite;
import org.fireapp.service.ApparatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Provides REST service for accessing information about 
 * fire department apparatus
 * 
 * @author Louis Drotos
 *
 */
@RestController
@RequestMapping( "/apparatus" )
public class ApparatusController {

	@Autowired
	private ApparatusService apparatusService;
	
	/**
	 * Returns a list of all apparatus types in a simplified format
	 * that includes the ID and type name
	 * 
	 * @return The list of apparatus types
	 */
	@RequestMapping( value = "/types", method = RequestMethod.GET, produces = "application/json" )
	public List<ApparatusTypeLite> getApparatusTypes() {
		
		return apparatusService.getApparatusTypes();
	}
	
	/**
	 * Returns a object representing the specified apparatus type
	 * 
	 * @param id The apparatus type ID
	 * @return The apparatus object
	 */
	@RequestMapping( value = "/type", method = RequestMethod.GET, produces = "application/json" )
	public ApparatusType getApparatusType( @RequestParam( "id" ) Integer id ) {
		
		return apparatusService.getApparatusType( id );
	}
}
