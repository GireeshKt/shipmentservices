package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
import java.util.Date;

public class MilestoneResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long milestoneId;
	
	private String eventCode;
	
	private String eventName;
	
	private String milestoneName;
	
	private String organizationId;

	public String getModeOfTransport() {
		return modeOfTransport;
	}

	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}

	public String getWithLeadTime() {
		return withLeadTime;
	}

	public void setWithLeadTime(String withLeadTime) {
		this.withLeadTime = withLeadTime;
	}

	public Long getOrderSeq() {
		return orderSeq;
	}

	public void setOrderSeq(Long orderSeq) {
		this.orderSeq = orderSeq;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getMilestoneCode() {
		return milestoneCode;
	}

	public void setMilestoneCode(String milestoneCode) {
		this.milestoneCode = milestoneCode;
	}

	public String getTiedToLocation() {
		return tiedToLocation;
	}

	public void setTiedToLocation(String tiedToLocation) {
		this.tiedToLocation = tiedToLocation;
	}

	private String modeOfTransport;

	private String withLeadTime;

	private Long orderSeq;

	private String display;

	private String milestoneCode;

	private String tiedToLocation;

	private Long leadTime;

	public Long getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(Long leadTime) {
		this.leadTime = leadTime;
	}

	public Long getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(Long milestoneId) {
		this.milestoneId = milestoneId;
	}

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getMilestoneName() {
		return milestoneName;
	}

	public void setMilestoneName(String milestoneName) {
		this.milestoneName = milestoneName;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	
	
}