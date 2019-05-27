package com.blumeglobal.shipmentmanagement.exceptionlist.response;

public class ExceptionsResponse {
	private String description;
	
	private Long scheduleImpactedCount;

	public ExceptionsResponse() {
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getScheduleImpactedCount() {
		return scheduleImpactedCount;
	}

	public void setScheduleImpactedCount(Long scheduleImpactedCount) {
		this.scheduleImpactedCount = scheduleImpactedCount;
	}
}
