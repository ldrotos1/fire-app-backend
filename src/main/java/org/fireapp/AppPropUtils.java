package org.fireapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.stereotype.Component;

/**
 * This class provides access to the application's property values
 * 
 * @author Louis Drotos
 *
 */
@Component
public class AppPropUtils {

	private static String ROUTE_MATRIX_URL = null;
	private static String ROUTE_URL = null;
	
	private static String PROPERTY_FILE = "/app.properties";
	
	public AppPropUtils() {
		// Empty body
	}
	
	/**
	 * Returns the URL for the MapQuest route matrix service with
	 * the API key included as an parameter
	 * 
	 * @return The URL
	 */
	public String getRouteMatrixUrl() {
		
		if ( ROUTE_MATRIX_URL == null ) {
			
			loadPropertyValues();
		}
		
		return ROUTE_MATRIX_URL;
	}
	
	/**
	 * Returns the URL for the MapQuest route service with the
	 * API key included as an parameter 
	 * 
	 * @return The URL
	 */
	public String getRouteUrl() {
		
		if ( ROUTE_URL == null ) {
			
			loadPropertyValues();
		}
		
		return ROUTE_URL;
	}
	
	/**
	 * Loads the property files from the property file
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private void loadPropertyValues() {
		
		Properties appProps;
		String routeMatrixUrl;
		String routeUrl;
		String apiKey;
		
		try {
			
			// Loads the property file
			appProps = new Properties();
			appProps.load( new BufferedReader(new InputStreamReader(getClass().getResourceAsStream( PROPERTY_FILE ))));
			
			// Gets the property values
			routeMatrixUrl = appProps.getProperty( "route_matrix_url" );
			routeUrl = appProps.getProperty( "route_url" );
			apiKey = appProps.getProperty( "map_quest_api_key" );
			
			// Sets the URL values
			ROUTE_MATRIX_URL = routeMatrixUrl + "?key=" + apiKey;
			ROUTE_URL = routeUrl + "?key=" + apiKey;
		} 
		catch ( Exception e ) {
			
			e.printStackTrace();
		} 
	}
}
