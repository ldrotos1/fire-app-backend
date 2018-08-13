package org.fireapp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
  * An entity class that provides a complete representation 
 * of a fire station 
 * 
 * @author Louis Drotos
 *
 */
@Entity
@Table( name = "station" )
public class Station {

	@Id
	@Column( name = "station_id" )
	private Integer stationId;
	
	@Column( name = "number" )
	private Integer stationNumber;
	
	@Column( name = "station_designator" )
	private Integer designator;
	
	@Column( name = "name" )
	private String name;
	
	@Column( name = "department_id" )
	private Integer departmentId;
	
	@Column( name = "address" )
	private String address;
	
	@Column( name = "city" )
	private String city;
	
	@Column( name = "state" )
	private String state;
	
	@Column( name = "zip" )
	private String zip;
	
	@Column( name = "phone" )
	private String phone;
	
	@Column( name = "is_volunteer" )
	private Boolean isVolunteer;
	
	@Column(name = "lat")
	private Double lat;
	
	@Column(name = "lon")
	private Double lon;
	
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinColumn( name = "station_id" )
	private List<Apparatus> apparatus;
	
	public Station() {
		super();
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public Integer getStationNumber() {
		return stationNumber;
	}

	public void setStationNumber(Integer stationNumber) {
		this.stationNumber = stationNumber;
	}

	public Integer getDesignator() {
		return designator;
	}

	public void setDesignator(Integer designator) {
		this.designator = designator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getIsVolunteer() {
		return isVolunteer;
	}

	public void setIsVolunteer(Boolean isVolunteer) {
		this.isVolunteer = isVolunteer;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public List<Apparatus> getApparatus() {
		return apparatus;
	}

	public void setApparatus(List<Apparatus> apparatus) {
		this.apparatus = apparatus;
	}

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((designator == null) ? 0 : designator.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		if (designator == null) {
			if (other.designator != null)
				return false;
		} else if (!designator.equals(other.designator))
			return false;
		return true;
	}
}
