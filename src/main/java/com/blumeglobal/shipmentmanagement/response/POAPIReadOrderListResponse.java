package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;


public class POAPIReadOrderListResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	private String status;
	private POAPIResponse successfullData;
	private String message;

	public POAPIReadOrderListResponse() {

	}
	public POAPIReadOrderListResponse(POAPIResponse successfullData,String message, int draftRecordCount, int pendingDataCount) {
		super();
		this.successfullData = successfullData;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public POAPIResponse getSuccessfullData() {
		return successfullData;
	}
	public void setSuccessfullData(POAPIResponse successfullData) {
		this.successfullData = successfullData;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
