package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
import java.util.List;


public class POAPIReadOrderLineListResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	private String status;
	private List<POListAPIResponse> successfullData;
	private String message;

	public POAPIReadOrderLineListResponse() {

	}
	public POAPIReadOrderLineListResponse(List<POListAPIResponse> successfullData,String message, int draftRecordCount, int pendingDataCount) {
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
	public List<POListAPIResponse> getSuccessfullData() {
		return successfullData;
	}
	public void setSuccessfullData(List<POListAPIResponse> successfullData) {
		this.successfullData = successfullData;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
