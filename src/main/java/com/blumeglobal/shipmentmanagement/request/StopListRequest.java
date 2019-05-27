package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;

public class StopListRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String stopType;
	
	private String stopName;
	
	private String stopNumber;
	
	private FacilityRequest facility;

	
	public StopListRequest() {
		
	}


	public String getStopType() {
		return stopType;
	}


	public void setStopType(String stopType) {
		this.stopType = stopType;
	}


	public String getStopName() {
		return stopName;
	}


	public void setStopName(String stopName) {
		this.stopName = stopName;
	}


	public String getStopNumber() {
		return stopNumber;
	}


	public void setStopNumber(String stopNumber) {
		this.stopNumber = stopNumber;
	}


	public FacilityRequest getFacility() {
		return facility;
	}


	public void setFacility(FacilityRequest facility) {
		this.facility = facility;
	}

}
