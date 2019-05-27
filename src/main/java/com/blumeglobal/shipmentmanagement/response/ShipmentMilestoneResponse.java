package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
import java.util.Date;


public class ShipmentMilestoneResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long shipmentMilestoneId;
		
	private Long shipmentId;
	
	private Long e2eShipmentId;
	
	private String modeOfTransport;
	
	private String milestoneName;
	
	private Date estimatedDate;
	
	private Date actualDate;
	
	private String locationId;
	
	private String locationDetails;
	
	private Double estimatedLeadTime;
	
	private Date actualLeadTime;
	
	private String milestoneAddress1;

	private String milestoneAddress2;

	private String milestoneCity;

	private String milestoneState;

	private String milestoneZipcode;

	private String milestoneCountry;

	private String exceptionCat;//exception category
	
	private Date plannedDate;
	
	private Long orderSeq;
	
	private String eventCode;
	
	private String eventName;
	
	private String locationName;
	
	private String locationType;

	private String address1;
	
	private String address2;
	
	private String city;
	
	private String state;
	
	private String zipcode;
	
	private String country;
	
	private String unlocode;
	       
		
	public ShipmentMilestoneResponse() {
		super();
	}

	public Long getShipmentMilestoneId() {
		return shipmentMilestoneId;
	}

	public void setShipmentMilestoneId(Long shipmentMilestoneId) {
		this.shipmentMilestoneId = shipmentMilestoneId;
	}

	public Long getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(Long shipmentId) {
		this.shipmentId = shipmentId;
	}

	public Long getE2eShipmentId() {
		return e2eShipmentId;
	}

	public void setE2eShipmentId(Long e2eShipmentId) {
		this.e2eShipmentId = e2eShipmentId;
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

	public Date getEstimatedDate() {
		return estimatedDate;
	}

	public void setEstimatedDate(Date estimatedDate) {
		this.estimatedDate = estimatedDate;
	}

	public Date getActualDate() {
		return actualDate;
	}

	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public Double getEstimatedLeadTime() {
		return estimatedLeadTime;
	}

	public void setEstimatedLeadTime(Double estimatedLeadTime) {
		this.estimatedLeadTime = estimatedLeadTime;
	}

	public Date getActualLeadTime() {
		return actualLeadTime;
	}

	public void setActualLeadTime(Date actualLeadTime) {
		this.actualLeadTime = actualLeadTime;
	}

	public String getMilestoneAddress1() {
		return milestoneAddress1;
	}

	public void setMilestoneAddress1(String milestoneAddress1) {
		this.milestoneAddress1 = milestoneAddress1;
	}

	public String getMilestoneAddress2() {
		return milestoneAddress2;
	}

	public void setMilestoneAddress2(String milestoneAddress2) {
		this.milestoneAddress2 = milestoneAddress2;
	}

	public String getMilestoneCity() {
		return milestoneCity;
	}

	public void setMilestoneCity(String milestoneCity) {
		this.milestoneCity = milestoneCity;
	}

	public String getMilestoneState() {
		return milestoneState;
	}

	public void setMilestoneState(String milestoneState) {
		this.milestoneState = milestoneState;
	}

	public String getMilestoneZipcode() {
		return milestoneZipcode;
	}

	public void setMilestoneZipcode(String milestoneZipcode) {
		this.milestoneZipcode = milestoneZipcode;
	}

	public String getMilestoneCountry() {
		return milestoneCountry;
	}

	public void setMilestoneCountry(String milestoneCountry) {
		this.milestoneCountry = milestoneCountry;
	}

	public String getExceptionCat() {
		return exceptionCat;
	}

	public void setExceptionCat(String exceptionCat) {
		this.exceptionCat = exceptionCat;
	}

	public Date getPlannedDate() {
		return plannedDate;
	}

	public void setPlannedDate(Date plannedDate) {
		this.plannedDate = plannedDate;
	}

    public Long getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(Long orderSeq) {
        this.orderSeq = orderSeq;
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

	public String getLocationDetails() {
		return locationDetails;
	}

	public void setLocationDetails(String locationDeatils) {
		this.locationDetails = locationDeatils;
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
