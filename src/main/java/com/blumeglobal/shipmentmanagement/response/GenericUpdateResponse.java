package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
import java.util.List;

public class GenericUpdateResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer numberOfRecordsUpdated;
	private String recordType;
	private Long runtimeSeconds;
	private List<String> notUpdatedRecords;
	private List<String> updatedRecords;
	
	
	public GenericUpdateResponse() {
	}


	public Integer getNumberOfRecordsUpdated() {
		return numberOfRecordsUpdated;
	}


	public void setNumberOfRecordsUpdated(Integer numberOfRecordsUpdated) {
		this.numberOfRecordsUpdated = numberOfRecordsUpdated;
	}


	public List<String> getUpdatedRecords() {
		return updatedRecords;
	}


	public void setUpdatedRecords(List<String> updatedRecords) {
		this.updatedRecords = updatedRecords;
	}


	public String getRecordType() {
		return recordType;
	}


	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}


	public Long getRuntimeSeconds() {
		return runtimeSeconds;
	}


	public void setRuntimeSeconds(Long runtimeSeconds) {
		this.runtimeSeconds = runtimeSeconds;
	}


	public List<String> getNotUpdatedRecords() {
		return notUpdatedRecords;
	}


	public void setNotUpdatedRecords(List<String> notUpdatedRecords) {
		this.notUpdatedRecords = notUpdatedRecords;
	}


}
