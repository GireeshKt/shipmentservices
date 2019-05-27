package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
import java.util.List;

import com.blumeglobal.shipmentmanagement.request.E2EShipmentAPIRequest;


/**
 * The ExcelUploadEventResponse is used to handle the response of Upload Excel Shipment Events Operation
 *
 */
public class ExcelUploadResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ExcelErrorRowsResponse> conflictedData;
	private List<E2EShipmentAPIRequest> successfullData;
	private List<E2EShipmentAPIResponse>uploadedData;
	private String message;
	private int count;
	private String error;

	public ExcelUploadResponse() {

	}

	public ExcelUploadResponse(List<ExcelErrorRowsResponse> conflictedData,
			List<E2EShipmentAPIRequest> successfullData,List<E2EShipmentAPIResponse> uploadedData, String message, int count,String error) {
		this.conflictedData = conflictedData;
		this.successfullData = successfullData;
		this.uploadedData = uploadedData;
		this.message = message;
		this.count = count;
		this.error=error;
	}

	public List<ExcelErrorRowsResponse> getConflictedData() {
		return conflictedData;
	}

	public void setConflictedData(List<ExcelErrorRowsResponse> conflictedData) {
		this.conflictedData = conflictedData;
	}

	

	public List<E2EShipmentAPIRequest> getSuccessfullData() {
		return successfullData;
	}

	public void setSuccessfullData(List<E2EShipmentAPIRequest> successfullData) {
		this.successfullData = successfullData;
	}

	public List<E2EShipmentAPIResponse> getUploadedData() {
		return uploadedData;
	}

	public void setUploadedData(List<E2EShipmentAPIResponse> uploadedData) {
		this.uploadedData = uploadedData;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
