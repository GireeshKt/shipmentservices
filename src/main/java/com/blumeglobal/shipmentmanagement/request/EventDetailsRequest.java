package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;

public class EventDetailsRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String eventType;
	private String eventAction;
	private String reportedDate;
	private String comments;
    private EquipmentRequest equipment;
	
    public EventDetailsRequest() {
		
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventAction() {
		return eventAction;
	}

	public void setEventAction(String eventAction) {
		this.eventAction = eventAction;
	}

	public String getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public EquipmentRequest getEquipment() {
		return equipment;
	}

	public void setEquipment(EquipmentRequest equipment) {
		this.equipment = equipment;
	}
}
