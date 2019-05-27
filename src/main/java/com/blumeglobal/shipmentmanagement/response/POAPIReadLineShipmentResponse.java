package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;


public class POAPIReadLineShipmentResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	private String status;
	private POShipmentAPIResponse successfullData;
	private String message;

	public POAPIReadLineShipmentResponse() {

	}
	public POAPIReadLineShipmentResponse(POShipmentAPIResponse successfullData,String message, int draftRecordCount, int pendingDataCount) {
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
	public POShipmentAPIResponse getSuccessfullData() {
		return successfullData;
	}
	public void setSuccessfullData(POShipmentAPIResponse successfullData) {
		this.successfullData = successfullData;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
