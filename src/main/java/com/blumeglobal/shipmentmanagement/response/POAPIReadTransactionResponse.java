package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
import java.util.List;


public class POAPIReadTransactionResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	private String status;
	private String[] predefinedSatus;
	private List<POTransactionsAPIResponse> successfullData;
	private String message;

	public POAPIReadTransactionResponse() {

	}
	public POAPIReadTransactionResponse(List<POTransactionsAPIResponse> successfullData,String[] predefinedSatus,String message, int draftRecordCount, int pendingDataCount) {
		super();
		this.successfullData = successfullData;
		this.predefinedSatus = predefinedSatus;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<POTransactionsAPIResponse> getSuccessfullData() {
		return successfullData;
	}
	public void setSuccessfullData(List<POTransactionsAPIResponse> successfullData) {
		this.successfullData = successfullData;
	}
	
	public String[] getPredefinedSatus() {
		return predefinedSatus;
	}
	public void setPredefinedSatus(String[] predefinedSatus) {
		this.predefinedSatus = predefinedSatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
