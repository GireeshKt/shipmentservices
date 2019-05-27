package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Size;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blumeglobal.shipmentmanagement.service.impl.BookingServiceImpl;

public class BookingFrontRequest implements Serializable{
	private static final Logger logger = LogManager.getLogger(BookingServiceImpl.class);
	
	
	private static final long serialVersionUID = 1L;
	
//	private Long bookingId;
	
	private Date bookingDate;
	
	@Size(max = 50, message = "shipmentReferenceNum must be maximum of 50 characters")
	private String shipmentReferenceNum;
	
	@Size(max = 100, message = "shipper must be maximum of 100 characters")
	private String shipper;
	
	@Size(max = 100, message = "consignee must be maximum of 100 characters")
	private String consignee;
	
	@Size(max = 200, message = "origin_locationId must be maximum of 200 characters")
	private String origin_locationId;
	
	@Size(max = 200, message = "destination_locationId must be maximum of 200 characters")
	private String destination_locationId;
	
	@Size(max = 20, message = "moveType must be maximum of 20 characters")
	private String moveType;
	
	@Size(max = 200, message = "commodity must be maximum of 200 characters")
	private String commodity;
	
	@Size(max = 50, message = "loadType must be maximum of 50 characters")
	private String loadType;
	
	@Size(max = 100, message = "equipmentDimensions must be maximum of 100 characters")
	private String equipmentDimensions;
	
	private Long numOfContainers;
	
	private Date requestedPickupDate;
	
	private Date requestedDeliveryDate;
	
	private String incoTerm;
	
	@Size(max = 1, message = "hazmat must be maximum of 4 characters of Y/N yes/no true/false value")
	private String hazmat;
	
	private Double weight;
	
	private Double volume;
	
	@Size(max = 50, message = "carrierName must be maximum of 50 characters")
	private String carrierName;
	
	@Size(max = 1, message = "isTempControlled must be maximum of 4 characters of Y/N yes/no true/false value")
	private String emptyConatinerNeeded;
	
	@Size(max = 200, message = "emptyContPickup_locId must be maximum of 200 characters")
	private String emptyContPickup_locId;
	
	@Size(max = 200, message = "emptyContDropoff_locId must be maximum of 200 characters")
	private String emptyContDropoff_locId;
	
	@Size(max = 600, message = "notes must be maximum of 600 characters")
	private String notes;
	
	private String destinationAddress;
	
	private String destinationCity;
	
	private String destinationCountry;
	
	private String destinationFacility;
	
	private String destinationLocation;
	
	private String destinationState;
	
	private String destinationZip;
	
	private String destinationZone;
	
	
	private String originAddress;
	
	private String originCity;
	
	private String originCountry;
	
	private String originFacility;
	
	private String originLocation;
	
	private String originState;
	
	private String originZip;
	
	private String originZone;
	
	private String createdBy;
	
	
	public BookingFrontRequest() {
		
	}


//	public Long getBookingId() {
//		return bookingId;
//	}
//
//
//	public void setBookingId(Long bookingId) {
//		this.bookingId = bookingId;
//	}


	public Date getBookingDate() {
		return bookingDate;
	}


	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}


	public String getShipmentReferenceNum() {
		return shipmentReferenceNum;
	}


	public void setShipmentReferenceNum(String shipmentReferenceNum) {
		this.shipmentReferenceNum = shipmentReferenceNum;
	}


	public String getShipper() {
		return shipper;
	}


	public void setShipper(String shipper) {
		this.shipper = shipper;
	}


	public String getConsignee() {
		return consignee;
	}


	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}


	public String getOrigin_locationId() {
		return origin_locationId;
	}


	public void setOrigin_locationId(String origin_locationId) {
		StringBuffer sb = new StringBuffer();
		sb.append(getOriginAddress().trim());
		sb.append("|");
		sb.append(getOriginCity().trim());
		sb.append("|");
		sb.append(getOriginState().trim());
		sb.append("|");
		sb.append(getOriginZip().trim());
		sb.append("|");
		sb.append(getOriginCountry().trim());
		
		String origin_location = sb.toString();
		
		this.origin_locationId = origin_location;
	}


	public String getDestination_locationId() {
		return destination_locationId;
	}


	public void setDestination_locationId(String destination_locationId) {
		StringBuffer sb = new StringBuffer();
		sb.append(getDestinationAddress().trim());
		sb.append("|");
		sb.append(getDestinationCity().trim());
		sb.append("|");
		sb.append(getDestinationState().trim());
		sb.append("|");
		sb.append(getDestinationZip().trim());
		sb.append("|");
		sb.append(getDestinationCountry().trim());
		
		String destination_location = sb.toString();
		
		
		this.destination_locationId = destination_location;
	}


	public String getMoveType() {
		return moveType;
	}


	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}


	public String getCommodity() {
		return commodity;
	}


	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}


	public String getLoadType() {
		return loadType;
	}


	public void setLoadType(String loadType) {
		this.loadType = loadType;
	}


	public String getEquipmentDimensions() {
		return equipmentDimensions;
	}


	public void setEquipmentDimensions(String equipmentDimensions) {
		this.equipmentDimensions = equipmentDimensions;
	}


	public Long getNumOfContainers() {
		return numOfContainers;
	}


	public void setNumOfContainers(Long numOfContainers) {
		this.numOfContainers = numOfContainers;
	}


	public Date getRequestedPickupDate() {
		return requestedPickupDate;
	}


	public void setRequestedPickupDate(Date requestedPickupDate) {
		this.requestedPickupDate = requestedPickupDate;
	}


	public Date getRequestedDeliveryDate() {
		return requestedDeliveryDate;
	}


	public void setRequestedDeliveryDate(Date requestedDeliveryDate) {
		this.requestedDeliveryDate = requestedDeliveryDate;
	}


	public String getIncoTerm() {
		return incoTerm;
	}


	public void setIncoTerm(String incoTerm) {
		this.incoTerm = incoTerm;
	}


	public String getHazmat() {
		return hazmat;
	}


	public void setHazmat(String hazmat) {
		this.hazmat = hazmat;
	}


	public Double getWeight() {
		return weight;
	}


	public void setWeight(Double weight) {
		this.weight = weight;
	}


	public Double getVolume() {
		return volume;
	}


	public void setVolume(Double volume) {
		this.volume = volume;
	}


	public String getCarrierName() {
		return carrierName;
	}


	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}


	public String getEmptyConatinerNeeded() {
		return emptyConatinerNeeded;
	}


	public void setEmptyConatinerNeeded(String emptyConatinerNeeded) {
		this.emptyConatinerNeeded = emptyConatinerNeeded;
	}


	public String getEmptyContPickup_locId() {
		return emptyContPickup_locId;
	}


	public void setEmptyContPickup_locId(String emptyContPickup_locId) {
		this.emptyContPickup_locId = emptyContPickup_locId;
	}


	public String getEmptyContDropoff_locId() {
		return emptyContDropoff_locId;
	}


	public void setEmptyContDropoff_locId(String emptyContDropoff_locId) {
		this.emptyContDropoff_locId = emptyContDropoff_locId;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public String getDestinationAddress() {
		return destinationAddress;
	}


	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}


	public String getDestinationCity() {
		return destinationCity;
	}


	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}


	public String getDestinationCountry() {
		return destinationCountry;
	}


	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}


	public String getDestinationFacility() {
		return destinationFacility;
	}


	public void setDestinationFacility(String destinationFacility) {
		this.destinationFacility = destinationFacility;
	}


	public String getDestinationLocation() {
		return destinationLocation;
	}


	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}


	public String getDestinationState() {
		return destinationState;
	}


	public void setDestinationState(String destinationState) {
		this.destinationState = destinationState;
	}

	public String getDestinationZip() {
		return destinationZip;
	}


	public void setDestinationZip(String destinationZip) {
		this.destinationZip = destinationZip;
	}


	public String getDestinationZone() {
		return destinationZone;
	}


	public void setDestinationZone(String destinationZone) {
		this.destinationZone = destinationZone;
	}


	public String getOriginAddress() {
		return originAddress;
	}


	public void setOriginAddress(String originAddress) {
		this.originAddress = originAddress;
	}

	
	public String getOriginCity() {
		return originCity;
	}


	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}


	public String getOriginCountry() {
		return originCountry;
	}


	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}


	public String getOriginFacility() {
		return originFacility;
	}


	public void setOriginFacility(String originFacility) {
		this.originFacility = originFacility;
	}


	public String getOriginLocation() {
		return originLocation;
	}


	public void setOriginLocation(String originLocation) {
		this.originLocation = originLocation;
	}

	public String getOriginState() {
		return originState;
	}


	public void setOriginState(String originState) {
		this.originState = originState;
	}


	public String getOriginZip() {
		return originZip;
	}


	public void setOriginZip(String originZip) {
		this.originZip = originZip;
	}


	public String getOriginZone() {
		return originZone;
	}


	public void setOriginZone(String originZone) {
		this.originZone = originZone;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
}
