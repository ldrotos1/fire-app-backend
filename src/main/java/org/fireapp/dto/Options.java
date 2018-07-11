package org.fireapp.dto;

/**
 * This class is used to specify options for the MapQuest
 * Route service
 * 
 * @author Louis Drotos
 *
 */
public class Options {

	private String routeType = "fastest";
	private String narrativeType = "none";
	private String shapeFormat = "raw";
	private Boolean fullShape = true;
	private Boolean doReverseGeocode = false;
	private int generalize = 0;
	
	public Options() {
		// Empty body 
	}

	public String getRouteType() {
		return routeType;
	}

	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}

	public String getNarrativeType() {
		return narrativeType;
	}

	public void setNarrativeType(String narrativeType) {
		this.narrativeType = narrativeType;
	}

	public String getShapeFormat() {
		return shapeFormat;
	}

	public void setShapeFormat(String shapeFormat) {
		this.shapeFormat = shapeFormat;
	}

	public Boolean getFullShape() {
		return fullShape;
	}

	public void setFullShape(Boolean fullShape) {
		this.fullShape = fullShape;
	}

	public Boolean getDoReverseGeocode() {
		return doReverseGeocode;
	}

	public void setDoReverseGeocode(Boolean doReverseGeocode) {
		this.doReverseGeocode = doReverseGeocode;
	}

	public int getGeneralize() {
		return generalize;
	}

	public void setGeneralize(int generalize) {
		this.generalize = generalize;
	}
}
