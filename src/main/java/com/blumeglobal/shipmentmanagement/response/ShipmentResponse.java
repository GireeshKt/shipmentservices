package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;

public class ShipmentResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String status;
	private String message;
	
	public ShipmentResponse() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
