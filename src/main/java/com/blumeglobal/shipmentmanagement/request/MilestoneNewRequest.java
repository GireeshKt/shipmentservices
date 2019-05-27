package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;
import java.math.BigDecimal;


import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * The ShipmentEventRequest is used to handle the request of Shipment Event
 * Operations
 *
 */

public class MilestoneNewRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long milestoneId;
	
	@Size(max = 20, message = "Event Code must be maximum of 20 characters")
	private String eventCode;
	
	@Size(max = 50, message = "Event Name must be maximum of 50 characters")
	private String eventName;
	
	@Size(max = 20, message = "Mode of Transport must be maximum of 20 characters")
	private String modeOfTransport;
	
	@Size(max = 50, message = "Milestone Name must be maximum of 50 characters")
	private String milestoneName;
	
	@Size(max = 50, message = "Organization ID must be maximum of 50 characters")
	private String organizationId;
	
	private String withLeadTime;
	
	private Long orderSeq;
	
	private String display;
	
	@Size(max = 20, message = "Milestone Code must be maximum of 20 characters")
	private String milestoneCode;
	
	@Size(max = 20, message = "Tied To Location must be maximum of 20 characters")
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

	public String getModeOfTransport() {
		return modeOfTransport;
	}

	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
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

	public String getWithLeadTime() {
		return withLeadTime;
	}

	public void setWithLeadTime(String withLeadTime) {
		this.withLeadTime = withLeadTime;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public Long getOrderSeq() {
		return orderSeq;
	}

	public void setOrderSeq(Long orderSeq) {
		this.orderSeq = orderSeq;
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
	
	
}