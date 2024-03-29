package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;

import com.blumeglobal.shipmentmanagement.request.LocationRequest;


public class LocationMasterResponse implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private String zip;
	
	private String country;
	
	private String location;
	
	private String locationType;
	
	private String typeDescription;

	public LocationMasterResponse() {
		
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	
	@Override
	public String toString() {
		return "{ address: " + address + ", city: " + city + ", state: " + state + ", zip: " + zip
				+ ", country: " + country + "}";
	}

}
