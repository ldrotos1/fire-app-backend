package org.fireapp.model;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * An instance of this class represents a fire department
 * 
 * @author Louis Drotos
 *
 */
@Entity
@Table( name = "department" )
public class Department {

	@Id
	@Column( name = "department_id" )
	private Integer departmentId;
	
	@Column( name = "name" )
	private String name;
	
	@Column( name = "chief" )
	private String chief;
	
	@Column( name = "address" )
	private String address;
	
	@Column( name = "city" )
	private String city;
	
	@Column( name = "zip" )
	private String zip;
	
	@Column( name = "phone" )
	private String phone;
	
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinColumn( name = "department_id" )
	private List<StationLiteUnitCount> stations;

	@Transient
	private Map<String,BigInteger> unitTypeMap;
	
	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChief() {
		return chief;
	}

	public void setChief(String chief) {
		this.chief = chief;
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

	public List<StationLiteUnitCount> getStations() {
		return stations;
	}

	public void setStations(List<StationLiteUnitCount> stations) {
		this.stations = stations;
	}

	public Map<String, BigInteger> getUnitTypeMap() {
		return unitTypeMap;
	}

	public void setUnitTypeMap(Map<String, BigInteger> unitTypeMap) {
		this.unitTypeMap = unitTypeMap;
	}
}
