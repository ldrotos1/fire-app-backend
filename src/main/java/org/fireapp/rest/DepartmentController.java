package org.fireapp.rest;

import java.util.List;

import org.fireapp.model.BasicDepartment;
import org.fireapp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/department" )
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping( value = "/all", method = RequestMethod.GET, produces = "application/json" )
	public List<BasicDepartment> getAllDepartments() {
		
		return departmentService.getAllDeparments();
	}
}
