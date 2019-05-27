package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;

public class StopRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String stopType;
	
	private String stopName;
	
	private Long stopNumber;
	
	private ShipmentFacilityRequest facility;

	
	public StopRequest() {
		
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


	public Long getStopNumber() {
		return stopNumber;
	}


	public void setStopNumber(Long stopNumber) {
		this.stopNumber = stopNumber;
	}


	public ShipmentFacilityRequest getFacility() {
		return facility;
	}


	public void setFacility(ShipmentFacilityRequest facility) {
		this.facility = facility;
	}


}
