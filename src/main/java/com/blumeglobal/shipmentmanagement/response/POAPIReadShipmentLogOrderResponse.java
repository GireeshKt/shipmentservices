package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;


public class POAPIReadShipmentLogOrderResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	private String status;
	private ShipmentLogOrderDetailsResponse successfullData;
	private String message;

	public POAPIReadShipmentLogOrderResponse() {

	}
	public POAPIReadShipmentLogOrderResponse(ShipmentLogOrderDetailsResponse successfullData,String message, int draftRecordCount, int pendingDataCount) {
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
	public ShipmentLogOrderDetailsResponse getSuccessfullData() {
		return successfullData;
	}
	public void setSuccessfullData(ShipmentLogOrderDetailsResponse successfullData) {
		this.successfullData = successfullData;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
