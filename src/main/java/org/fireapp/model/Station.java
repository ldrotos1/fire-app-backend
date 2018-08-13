package org.fireapp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

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
	private Integer stationDesignator;
	
	@Column( name = "name" )
	private String stationName;
	
	@JsonUnwrapped
	@ManyToOne( cascade = CascadeType.ALL )
	@JoinColumn( name = "department_id", foreignKey = 
		@ForeignKey( name = "fkey_dept_id" ) )
	private DepartmentLite department;
	
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

	public Integer getStationDesignator() {
		return stationDesignator;
	}

	public void setStationDesignator(Integer stationDesignator) {
		this.stationDesignator = stationDesignator;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	
	public DepartmentLite getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentLite department) {
		this.department = department;
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
		result = prime * result + ((stationDesignator == null) ? 0 : stationDesignator.hashCode());
		result = prime * result + ((stationId == null) ? 0 : stationId.hashCode());
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
		if (stationDesignator == null) {
			if (other.stationDesignator != null)
				return false;
		} else if (!stationDesignator.equals(other.stationDesignator))
			return false;
		if (stationId == null) {
			if (other.stationId != null)
				return false;
		} else if (!stationId.equals(other.stationId))
			return false;
		return true;
	}
}
