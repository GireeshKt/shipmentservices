package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;

public class LaneLocationRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private LocationRequest origin;
	
	private LocationRequest destination;
	

	public LaneLocationRequest() {
		
	}

	public LocationRequest getOrigin() {
		return origin;
	}

	public void setOrigin(LocationRequest origin) {
		this.origin = origin;
	}

	public LocationRequest getDestination() {
		return destination;
	}

	public void setDestination(LocationRequest destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return "{origin:" + origin + ", destination:" + destination + "}";
	}
	
}
