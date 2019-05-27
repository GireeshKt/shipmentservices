package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class LocationMasterRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Size(max = 50, message = "Location Type must be a maximum of 50 characters")
	private String locationType;
	
	@Size(max = 500, message = "Location must be a maximum of 100 characters")
	private String location;
	
	@Size(max = 200, message = "Type Description must be a maximum of 200 characters")
	private String typeDescription;
	
	public LocationMasterRequest() {
		super();
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	
	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

}
