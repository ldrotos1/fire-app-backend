package org.fireapp.service;

import java.util.concurrent.CompletableFuture;

import org.fireapp.AppPropUtils;
import org.fireapp.dto.RouteRequest;
import org.fireapp.dto.RouteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * This service is used to make route requests to the 
 * MapQuest Directions API
 * 
 * @author Louis Drotos
 *
 */
@Service
public class RouteShapeService {

	@Autowired
	private AppPropUtils appPropUtils;
	
	private final RestTemplate restTemplate;
	
	public RouteShapeService() {

		this.restTemplate = new RestTemplate();
	}
	
	/**
	 * Executes a asynchronous request to the Map Quest Route service
	 * 
	 * @param request The route request
	 * @return The route shape response
	 * @throws InterruptedException
	 */
	@Async
	public CompletableFuture<RouteResponse> getRoute( RouteRequest request ) throws InterruptedException { 

		RouteResponse response = this.restTemplate.postForObject( appPropUtils.getRouteUrl(), request, RouteResponse.class );
		response.setStationId( request.getStationId() );
		return CompletableFuture.completedFuture( response );
	}
}
