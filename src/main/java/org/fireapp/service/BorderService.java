package org.fireapp.service;

import org.fireapp.dao.BorderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;

/**
 * A service class for accessing borders and performing spatail
 * querying operations against borders 
 * 
 * @author Louis Drotos
 *
 */
@Service
public class BorderService {

	@Autowired
	private BorderDao borderDao;
	
	/**
	 * Returns a boolean indicating if the specified geographic 
	 * location is within the application's area of interest 
	 * 
	 * @param lat The point location's latitude
	 * @param lon The point location's longitude
	 * @return True if location is within AOI, otherwise false 
	 */
	public Boolean isLocationWithAoi( Double lat, Double lon ) {
		
		// Creates the coordinate
		GeometryFactory geomFact = new GeometryFactory( new PrecisionModel(), 4326 );
		Point point = geomFact.createPoint( new Coordinate( lon, lat ) );
		
		// Performs the spatial query 
		return borderDao.pointWithBorder( point );
	}
}
