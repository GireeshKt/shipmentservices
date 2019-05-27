package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;

/**
 * ShipmentException entity.
 */

public class ShipmentExceptionRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Size(max = 22, message = "Shipment Id: Shipment Id must be maximum of 22 characters")
	private Long shipmentId;
	
	@Size(max = 22, message = "E2E Shipment Id: E2E Shipment Id must be maximum of 22 characters")
	private Long e2eShipmentId;
	
	@Size(max = 20, message = "Mode of Transport: Shipment Exception Id must be maximum of 20 characters")
	private String modeOfTransport;
	
	@Size(max = 50, message = "Exception Type: Exception Type must be maximum of 50 characters")
	private String exceptionType;
	
	@Size(max = 7, message = "Occurance Date: Occurance Date must be maximum of 7 characters")
	private Date occuranceDate;
	
	@Size(max = 600, message = "Exception Notes: Exception Notes must be maximum of 600 characters")
	private String exceptionNotes;
	
	@Size(max  = 200, message = "Exception Location: Exception Location must be a maximum of 200 characters")
	private String exceptionLocation;

	@Size(max  = 50, message = "Exception category: Exception category must be a maximum of 50 characters")
	private String category;

	private Long shipmentMilestoneId;
	
	@Size(max = 200, message = "LocationId must be maximum of 200 characters")
	private String locationId;
	
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
	
	public ShipmentExceptionRequest() {
		super();
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

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public Date getOccuranceDate() {
		return occuranceDate;
	}

	public void setOccuranceDate(Date occuranceDate) {
		this.occuranceDate = occuranceDate;
	}

	public String getExceptionNotes() {
		return exceptionNotes;
	}

	public void setExceptionNotes(String exceptionNotes) {
		this.exceptionNotes = exceptionNotes;
	}
	
	public String getExceptionLocation() {
		return exceptionLocation;
	}

	public void setExceptionLocation(String exceptionLocation) {
		this.exceptionLocation = exceptionLocation;
	}

	public Long getShipmentMilestoneId() {
		return shipmentMilestoneId;
	}

	public void setShipmentMilestoneId(Long shipmentMilestoneId) {
		this.shipmentMilestoneId = shipmentMilestoneId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
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