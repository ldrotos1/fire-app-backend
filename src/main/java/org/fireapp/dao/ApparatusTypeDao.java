package org.fireapp.dao;

import org.fireapp.model.ApparatusType;
import org.fireapp.service.DepartmentService;
import org.fireapp.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * An instance of this class is used to access apparatus type 
 * objects from the database
 * 
 * @author Louis Drotos
 *
 */
@Repository( "apparatusTypeDao" )
public class ApparatusTypeDao extends BaseDao<ApparatusType> {
	
	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	StationService stationService;
	
	/**
	 * Returns the apparatus type information object for the
	 * specified apparatus type
	 * 
	 * @param id The apparatus type ID
	 * @return The apparatus type object
	 */
	public ApparatusType getApparatusType( Integer id ) {
		
		// Gets the apparatus type object
		ApparatusType appType = this.get( "FROM ApparatusType a WHERE a.apparatusTypeId = " + id );
		
		// Gets and sets the map collections
		appType.setStationMap( stationService.getStationApparatusTypeMap( id ) );
		appType.setDepartMap( departmentService.getDeptApparatusTypeMap( id ) );
		
		return appType;
	}
}
