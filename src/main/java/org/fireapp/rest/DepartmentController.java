package org.fireapp.rest;

import java.util.List;

import org.fireapp.model.Department;
import org.fireapp.model.DepartmentLite;
import org.fireapp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/department" )
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping( value = "/all", method = RequestMethod.GET, produces = "application/json" )
	public List<DepartmentLite> getAllDepartments() {
		
		return departmentService.getAllDeparments();
	}
	
	@RequestMapping( value = "/byid", method = RequestMethod.GET, produces = "application/json" )
	public Department getDepartmentInfo( @RequestParam( "id" ) Integer id ) {
		
		return departmentService.getDepartmentInfo( id );
	}
}
