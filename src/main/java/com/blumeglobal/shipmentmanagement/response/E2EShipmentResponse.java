package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;

public class E2EShipmentResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	private ActiveShipmentResponse activeShipment;
	
	private Long shipmentId;

	private String bookingNumber;
	
	private String freightDescription;

	private String shipmentReferenceNumber;
	
	private String consignee;

	private String status;
	
	private String origin;

	private String originAddress1;
	
	private String originAddress2;
	
	private String originCity;
	
	private String originState;
	
	private String originZipCode;
	
	private String originCountry;
	
	private String destination;
	
	private String destinationAddress1;
	
	private String destinationAddress2;
	
	private String destinationCity;
	
	private String destinationState;
	
	private String destinationZipCode;
	
	private String destinationCountry;
	
	public E2EShipmentResponse() {

	}

	public Long getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(Long shipmentId) {
		this.shipmentId = shipmentId;
	}

	public String getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public String getFreightDescription() {
		return freightDescription;
	}

	public void setFreightDescription(String freightDescription) {
		this.freightDescription = freightDescription;
	}

	public String getShipmentReferenceNumber() {
		return shipmentReferenceNumber;
	}

	public void setShipmentReferenceNumber(String shipmentReferenceNumber) {
		this.shipmentReferenceNumber = shipmentReferenceNumber;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getOriginAddress1() {
		return originAddress1;
	}

	public void setOriginAddress1(String originAddress1) {
		this.originAddress1 = originAddress1;
	}

	public String getOriginAddress2() {
		return originAddress2;
	}

	public void setOriginAddress2(String originAddress2) {
		this.originAddress2 = originAddress2;
	}

	public String getOriginCity() {
		return originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	public String getOriginState() {
		return originState;
	}

	public void setOriginState(String originState) {
		this.originState = originState;
	}

	public String getOriginZipCode() {
		return originZipCode;
	}

	public void setOriginZipCode(String originZipCode) {
		this.originZipCode = originZipCode;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDestinationAddress1() {
		return destinationAddress1;
	}

	public void setDestinationAddress1(String destinationAddress1) {
		this.destinationAddress1 = destinationAddress1;
	}

	public String getDestinationAddress2() {
		return destinationAddress2;
	}

	public void setDestinationAddress2(String destinationAddress2) {
		this.destinationAddress2 = destinationAddress2;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public String getDestinationState() {
		return destinationState;
	}

	public void setDestinationState(String destinationState) {
		this.destinationState = destinationState;
	}

	public String getDestinationZipCode() {
		return destinationZipCode;
	}

	public void setDestinationZipCode(String destinationZipCode) {
		this.destinationZipCode = destinationZipCode;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public ActiveShipmentResponse getActiveShipment() {
		return activeShipment;
	}

	public void setActiveShipment(ActiveShipmentResponse activeShipment) {
		this.activeShipment = activeShipment;
	}

}
