package com.blumeglobal.shipmentmanagement.response;


public class RoutingGetResponse {
	private String status;
	private String message;
	private RoutingResponse successfullData;
	
	public RoutingGetResponse() {
		
	}
	public RoutingGetResponse(RoutingResponse successfullData,String message, String status) {
		super();
		this.status = status;
		this.successfullData = successfullData;
		this.message = message;
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
	public RoutingResponse getSuccessfullData() {
		return successfullData;
	}
	public void setSuccessfullData(RoutingResponse successfullData) {
		this.successfullData = successfullData;
	}
	
}
