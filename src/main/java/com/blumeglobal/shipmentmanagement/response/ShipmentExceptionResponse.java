package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
import java.util.Date;

public class ShipmentExceptionResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long shipmentExceptionId;
	private Long shipmentId;
	private Long e2eShipmentId;
	private String modeOfTransport;
	private String exceptionType;
	private Date occuranceDate;
	private String exceptionNotes;
	private String exceptionLocation;
	private Date expectedDate;
	private String priority;
	private String category;
	private String locationId;
	private String locationType;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	private String unlocode;
	
	public ShipmentExceptionResponse() {

	}

	public Long getShipmentExceptionId() {
		return shipmentExceptionId;
	}

	public void setShipmentExceptionId(Long shipmentExceptionId) {
		this.shipmentExceptionId = shipmentExceptionId;
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

	public Date getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
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
