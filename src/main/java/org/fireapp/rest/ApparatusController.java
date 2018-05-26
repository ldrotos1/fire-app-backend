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

@RestController
@RequestMapping( "/apparatus" )
public class ApparatusController {

	@Autowired
	private ApparatusService apparatusService;
	
	@RequestMapping( value = "/types", method = RequestMethod.GET, produces = "application/json" )
	public List<ApparatusTypeLite> getApparatusTypes() {
		
		return apparatusService.getApparatusTypes();
	}
	
	@RequestMapping( value = "/type", method = RequestMethod.GET, produces = "application/json" )
	public ApparatusType getApparatusType( @RequestParam( "id" ) Integer id ) {
		
		return apparatusService.getApparatusType( id );
	}
}
