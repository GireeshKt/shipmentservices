package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
import java.util.List;


public class ExcelPurchaseOrderUploadResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<ExcelPurchaseOrderErrorRowsResponse> conflictedData;
	private List<POAPIResponse> successfullData;
	private String message;
	private String error;

	public ExcelPurchaseOrderUploadResponse() {

	}
	
	public ExcelPurchaseOrderUploadResponse(List<ExcelPurchaseOrderErrorRowsResponse> conflictedData,
			List<POAPIResponse> successfullData,String message, String error) {
		this.conflictedData = conflictedData;
		this.successfullData = successfullData;
		this.message = message;
		this.error = error;
	}

	public List<ExcelPurchaseOrderErrorRowsResponse> getConflictedData() {
		return conflictedData;
	}

	public void setConflictedData(List<ExcelPurchaseOrderErrorRowsResponse> conflictedData) {
		this.conflictedData = conflictedData;
	}

	public List<POAPIResponse> getSuccessfullData() {
		return successfullData;
	}

	public void setSuccessfullData(List<POAPIResponse> successfullData) {
		this.successfullData = successfullData;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}