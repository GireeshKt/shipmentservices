package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;

public class FacilityRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String address1;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String postalCode;

	public FacilityRequest() {
		
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	 
}
