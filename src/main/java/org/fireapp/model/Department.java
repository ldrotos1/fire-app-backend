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
	private String departmentName;
	
	@Column( name = "chief" )
	private String chief;
	
	@Column( name = "personnel" )
	private Integer personnel;
	
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

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getChief() {
		return chief;
	}

	public void setChief(String chief) {
		this.chief = chief;
	}

	public Integer getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Integer personnel) {
		this.personnel = personnel;
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

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departmentName == null) ? 0 : departmentName.hashCode());
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
		Department other = (Department) obj;
		if (departmentName == null) {
			if (other.departmentName != null)
				return false;
		} else if (!departmentName.equals(other.departmentName))
			return false;
		return true;
	}
}
