package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;

public class LocationObjectRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String location;

	public LocationObjectRequest() {
		
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
