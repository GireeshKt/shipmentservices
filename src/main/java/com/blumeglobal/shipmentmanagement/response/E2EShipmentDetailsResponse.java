package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class E2EShipmentDetailsResponse implements Serializable{

	private static final long serialVersionUID = -6336148932030783301L;
	private Long e2eshipmentId;
	private String freightDescription; 
	private String consignee;
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
	private String shipmentReferenceNumber;
	private String billOfLadingNumber;
	private String bookingNumber;
	private String poNumber;
	private String status;
	private String asnNumber;
	private List<ShipmentLegDetailsResponse> legs;
	private List<LocationResponse> milestoneLocations;
	private Map<String, List<ShipmentMilestoneResponse>> shipmentMilestoneMap;
	private List<ShipmentEventResponse> events;
	private List<ShipmentExceptionResponse> exceptions;
	
	public List<LocationResponse> getMilestoneLocations() {
		return milestoneLocations;
	}
	public void setMilestoneLocations(List<LocationResponse> milestoneLocations) {
		this.milestoneLocations = milestoneLocations;
	}
	
	public Map<String, List<ShipmentMilestoneResponse>> getShipmentMilestoneMap() {
		return shipmentMilestoneMap;
	}
	public void setShipmentMilestoneMap(Map<String, List<ShipmentMilestoneResponse>> shipmentMilestoneMap) {
		this.shipmentMilestoneMap = shipmentMilestoneMap;
	}
	public String getFreightDescription() {
		return freightDescription;
	}
	public void setFreightDescription(String freightDescription) {
		this.freightDescription = freightDescription;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public List<ShipmentLegDetailsResponse> getLegs() {
		return legs;
	}
	public void setLegs(List<ShipmentLegDetailsResponse> legs) {
		this.legs = legs;
	}
	public Long getE2eshipmentId() {
		return e2eshipmentId;
	}
	public void setE2eshipmentId(Long e2eshipmentId) {
		this.e2eshipmentId = e2eshipmentId;
	}
	public String getShipmentReferenceNumber() {
		return shipmentReferenceNumber;
	}
	public void setShipmentReferenceNumber(String shipmentReferenceNumber) {
		this.shipmentReferenceNumber = shipmentReferenceNumber;
	}
	public String getBillOfLadingNumber() {
		return billOfLadingNumber;
	}
	public void setBillOfLadingNumber(String billOfLadingNumber) {
		this.billOfLadingNumber = billOfLadingNumber;
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
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
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

	
	public String getPoNumber() {
		return poNumber;
	}
	public List<ShipmentEventResponse> getEvents() {
		return events;
	}
	public void setEvents(List<ShipmentEventResponse> events) {
		this.events = events;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public List<ShipmentExceptionResponse> getExceptions() {
		return exceptions;
	}
	public void setExceptions(List<ShipmentExceptionResponse> exceptions) {
		this.exceptions = exceptions;
	}
	public String getBookingNumber() {
		return bookingNumber;
	}
	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}
	public String getAsnNumber() {
		return asnNumber;
	}
	public void setAsnNumber(String asnNumber) {
		this.asnNumber = asnNumber;
	}
	
}
