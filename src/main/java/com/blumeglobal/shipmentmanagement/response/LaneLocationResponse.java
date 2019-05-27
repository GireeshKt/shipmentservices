package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;

public class LaneLocationResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String origin_locationId;
	private String destination_locationId;	
	private String status;
	private String message;
	
	public LaneLocationResponse() {
		
	}

	public LaneLocationResponse(String status,String message) {
	      this.status=status;
	      this.message= message;
		}

	public String getOrigin_locationId() {
		return origin_locationId;
	}


	public void setOrigin_locationId(String origin_locationId) {
		this.origin_locationId = origin_locationId;
	}


	public String getDestination_locationId() {
		return destination_locationId;
	}


	public void setDestination_locationId(String destination_locationId) {
		this.destination_locationId = destination_locationId;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
