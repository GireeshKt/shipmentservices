package com.blumeglobal.shipmentmanagement.response;

public class MilestoneGetResponse {
	private String status;
	private String message;
	private MilestoneResponse successfullData;
	
	public MilestoneGetResponse() {
		
	}
	public MilestoneGetResponse(MilestoneResponse successfullData,String message, String status) {
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
	public MilestoneResponse getSuccessfullData() {
		return successfullData;
	}
	public void setSuccessfullData(MilestoneResponse successfullData) {
		this.successfullData = successfullData;
	}
}
