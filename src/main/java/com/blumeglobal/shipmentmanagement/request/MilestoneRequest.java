package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;
import javax.validation.constraints.Size;

/**
 * The ShipmentEventRequest is used to handle the request of Shipment Event
 * Operations
 *
 */

public class MilestoneRequest implements Serializable {
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
	
	private boolean withLeadTime;
	
	private Long orderSeq;
	
	private boolean display;
	
	@Size(max = 20, message = "Milestone Code must be maximum of 20 characters")
	private String milestoneCode;
	
	@Size(max = 20, message = "Tied To Location must be maximum of 20 characters")
	private String tiedToLocation;
	
	private Long leadTime;	
	
	@Size(max = 100, message = "Location Name must be maximum of 100 characters")
	private String locationName;
	
	@Size(max = 20, message = "Location Type must be maximum of 20 characters")
	private String locationType;
	
	@Size(max = 200, message = "Address1 must be maximum of 200 characters")
	private String address1;
	
	@Size(max = 200, message = "Address1 must be maximum of 200 characters")
	private String address2;
	
	@Size(max = 50, message = "City must be maximum of 50 characters")
	private String city;
	
	@Size(max = 50, message = "State must be maximum of 50 characters")
	private String state;
	
	@Size(max = 20, message = "Zipcode must be maximum of 20 characters")
	private String zipcode;
	
	@Size(max = 50, message = "Country must be maximum of 50 characters")
	private String country;
	
	@Size(max = 20, message = "Unlocode must be maximum of 20 characters")
    private String unlocode;

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

	public boolean isWithLeadTime() {
		return withLeadTime;
	}

	public void setWithLeadTime(boolean withLeadTime) {
		this.withLeadTime = withLeadTime;
	}

	public Long getOrderSeq() {
		return orderSeq;
	}

	public void setOrderSeq(Long orderSeq) {
		this.orderSeq = orderSeq;
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
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

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUnlocode() {
		return unlocode;
	}

	public void setUnlocode(String unlocode) {
		this.unlocode = unlocode;
	}

}