package com.blumeglobal.shipmentmanagement.response;

import java.util.List;

public class StopResponse {
	
	private static final long serialVersionUID = 1L;
	
	private List<Long> ids;
	private String status;
	private String message;
	
	public StopResponse() {

	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
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