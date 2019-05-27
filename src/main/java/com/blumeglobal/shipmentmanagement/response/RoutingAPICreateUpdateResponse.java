package com.blumeglobal.shipmentmanagement.response;

public class RoutingAPICreateUpdateResponse {
	private String status;
	private String message;
	
	public RoutingAPICreateUpdateResponse() {
		
	}
    public RoutingAPICreateUpdateResponse(String status,String message) {
	      this.status=status;
	      this.message= message;
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
