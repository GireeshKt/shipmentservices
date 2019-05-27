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

public class ShipmentEventRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	private Long originatorId;

	@Size(max = 20, message = "Originator Code: Originator Code must be maximum of 20 characters")
	private String originatorCode;

	@Size(max = 100, message = "Originator Name: OriginatorName must be maximum of 100 characters")
	private String originatorName;

	
	@Size(max = 20, message = "Event Code: Event Code must be maximum of 20 characters")
	private String eventCode;
	
	@Size(max = 50, message = "Event Name: Event Name must be maximum of 50 characters")
	private String eventName;

	@Size(max = 50, message = "HouseBill: HouseBill must be maximum of 50 characters")
	private String houseBill;

	@Size(max = 50, message = "MasterBill: MasterBill must be maximum of 50 characters")
	private String masterBill;

	@Size(max = 50, message = "WorkOrder Number: WorkOrder Number must be maximum of 50 characters")
	private String workOrderNumber;

	@Size(max = 50, message = "on Hand Number: on Hand Number must be maximum of 50 characters")
	private String onHandNumber;

	@Size(max = 50, message = "Unit Id: Unit Id must be maximum of 50 characters")
	private String unitId;

	private BigDecimal longitude;

	private BigDecimal latitude;

	@Size(max = 100, message = "Location: Location must be maximum of 100 characters")
	private String location;

	@DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss")
	private String eventTime;

	@Size(max = 100, message = "Reported By: Reported By must be maximum of 100 characters")
	private String reportedBy;

	@Size(max = 20, message = "Report Source: Report Source must be maximum of 20 characters")
	private String reportSource;

	@Size(max = 250, message = "Notes: Notes must be maximum of 250 characters")
	private String notes;

	@Size(max = 50, message = "Created By: Created By must be maximum of 50 characters")
	private String createdBy;
	
	@Size(max = 50, message = "Shipment Reference Number: Shipment Reference Number must be maximum of 50 characters")
	private String shipmentReferenceNumber;

	@Size(max = 20, message = "Unit State: Unit State must be maximum of 20 characters")
	private String unitState;

	@Size(max = 20, message = "State: State must be maximum of 20 characters")
	private String state;

	@Size(max = 20, message = "Country: Country must be maximum of 20 characters")
	private String country;

	@Size(max = 9, message = "Origin SPLC: Origin SPLC must be maximum of 9 characters")
	private String originSPLC;

	@Size(max = 9, message = "Destination SPLC: Destination SPLC must be maximum of 9 characters")
	private String destinationSPLC;

	@Size(max = 50, message = "Destination City: Destination City must be maximum of 50 characters")
	private String destinationCity;

	@Size(max = 10, message = "Destination State: Destination State must be maximum of 10 characters")
	private String destinationState;

	@Size(max = 15, message = "Associated UnitId: Associated UnitId must be maximum of 15 characters")
	private String associatedUnitId;

	@Size(max = 100, message = "Carrier Name: Carrier Name must be maximum of 100 characters")
	private String carrierName;

	@Size(max = 60, message = "Carrier Code: Carrier Code must be maximum of 60 characters")
	private String carrierCode;

	@Size(max = 60, message = "Receiver Code: Receiver Code must be maximum of 60 characters")
	private String receiverCode;

	@Size(max = 100, message = "Receiver Name: Receiver Name must be maximum of 100 characters")
	private String receiverName;

	@Size(max = 60, message = "Consignee Name: Consignee Name must be maximum of 60 characters")
	private String consigneeName;

	@Size(max = 25, message = "Seal Number: Seal Number must be maximum of 25 characters")
	private String sealNumber;

	@Size(max = 20, message = "Unit TypeCode: Unit TypeCode must be maximum of 20 characters")
	private String unitTypeCode;

	@Size(max = 50, message = "Voyage Number: Voyage Number must be maximum of 50 characters")
	private String voyageNumber;

	@Size(max = 50, message = "Terminal Code: Terminal Code must be maximum of 50 characters")
	private String terminalCode;

	@PositiveOrZero(message = "Unit Size should be a positive number")
	private Long unitSize;

	@Size(max = 50, message = "Booking Office: Booking Office must be maximum of 50 characters")
	private String bookingOffice;

	@Size(max = 50, message = "Vessel: Vessel must be maximum of 50 characters")
	private String vessel;

	@Size(max = 50, message = "Terminal Function: Terminal Function must be maximum of 50 characters")
	private String terminalFunction;

	@Size(max = 50, message = "City: City must be maximum of 50 characters")
	private String city;
	
	@Size(max = 50, message = "Reason Code: Reason Code must be maximum of 50 characters")
	private String reasonCode;

	@Size(max = 100, message = "Reason Name: Reason Name must be maximum of 100 characters")
	private String reasonName;

	private Long resolvedEventId;

	@Size(max = 50, message = "Rail Billing Number : Rail Billing Number  must be maximum of 50 characters")
	private String railBillingNumber;

	@Size(max = 100, message = "Container Booking Number: Container Booking Number  must be maximum of 100 characters")
	private String containerBookingNumber;

	@Size(max = 50, message = "Customer Order Reference: Customer Order Reference  must be maximum of 50 characters")
	private String customerOrderReferenceNumber;

	@Size(max = 15, message = "Resolved Event Status: Resolved Event Status  must be maximum of 15 characters")
	private String resolvedEventStatus;

	@Size(max = 50, message = "Associated Asset Size: Associated Asset Size must be maximum of 50 characters")
	private String associatedAssetSize;

	@Size(max = 15, message = "Resolved Event Source: Resolved Event Source  must be maximum of 15 characters")
	private String resolvedEventSource;
	
	@Size(max = 50, message = "Bill Of LadingNumber: Bill Of LadingNumber must be maximum of 50 characters")
	private String billOfLadingNumber;
	
	@Size(max = 50, message = "Import Reference Number: Import Reference Number must be maximum of 50 characters")
	private String importReferenceNumber;
	
	@Size(max = 50, message = "Purchase Order Reference Number: Purchase Order Reference Number must be maximum of 50 characters")
	private String purchaseOrderReferenceNumber;
	
	@Size(max = 50, message = "Booking Number: Booking Number must be maximum of 50 characters")
	private String bookingNumber;

	private Long resolvedEventOriginalId;
	
	@Size(max = 50, message = "Postal Code: Postal Code must be maximum of 50 characters")
	private String postalCode;
	
	@Size(max = 50, message = "Stop Type: Stop Type must be maximum of 50 characters")
	private String stopType;
	
	@Size(max = 50, message = "Signed By: Signed By must be maximum of 50 characters")
	private String signedBy;
	
	//codeType validation check for ocean events
	private String codeType;
	
	public ShipmentEventRequest() {

	}
	
	public String getCodeType() {
		return codeType;
	}


	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}


	public String getStopType() {
		return stopType;
	}

	public void setStopType(String stopType) {
		this.stopType = stopType;
	}

	public String getSignedBy() {
		return signedBy;
	}

	public void setSignedBy(String signedBy) {
		this.signedBy = signedBy;
	}




	public Long getOriginatorId() {
		return originatorId;
	}

	public void setOriginatorId(Long originatorId) {
		this.originatorId = originatorId;
	}

	public String getOriginatorCode() {
		return originatorCode;
	}

	public void setOriginatorCode(String originatorCode) {
		this.originatorCode = originatorCode;
	}

	public String getOriginatorName() {
		return originatorName;
	}

	public void setOriginatorName(String originatorName) {
		this.originatorName = originatorName;
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

	public String getHouseBill() {
		return houseBill;
	}

	public void setHouseBill(String houseBill) {
		this.houseBill = houseBill;
	}

	public String getMasterBill() {
		return masterBill;
	}

	public void setMasterBill(String masterBill) {
		this.masterBill = masterBill;
	}

	public String getWorkOrderNumber() {
		return workOrderNumber;
	}

	public void setWorkOrderNumber(String workOrderNumber) {
		this.workOrderNumber = workOrderNumber;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getReportedBy() {
		return reportedBy;
	}

	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}

	public String getReportSource() {
		return reportSource;
	}

	public void setReportSource(String reportSource) {
		this.reportSource = reportSource;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}	

	public String getShipmentReferenceNumber() {
		return shipmentReferenceNumber;
	}


	public void setShipmentReferenceNumber(String shipmentReferenceNumber) {
		this.shipmentReferenceNumber = shipmentReferenceNumber;
	}


	public String getUnitState() {
		return unitState;
	}

	public void setUnitState(String unitState) {
		this.unitState = unitState;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOriginSPLC() {
		return originSPLC;
	}

	public void setOriginSPLC(String originSPLC) {
		this.originSPLC = originSPLC;
	}

	public String getDestinationSPLC() {
		return destinationSPLC;
	}

	public void setDestinationSPLC(String destinationSPLC) {
		this.destinationSPLC = destinationSPLC;
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

	public String getAssociatedUnitId() {
		return associatedUnitId;
	}

	public void setAssociatedUnitId(String associatedUnitId) {
		this.associatedUnitId = associatedUnitId;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public String getReceiverCode() {
		return receiverCode;
	}

	public void setReceiverCode(String receiverCode) {
		this.receiverCode = receiverCode;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getSealNumber() {
		return sealNumber;
	}

	public void setSealNumber(String sealNumber) {
		this.sealNumber = sealNumber;
	}

	public String getUnitTypeCode() {
		return unitTypeCode;
	}

	public void setUnitTypeCode(String unitTypeCode) {
		this.unitTypeCode = unitTypeCode;
	}

	public String getVoyageNumber() {
		return voyageNumber;
	}

	public void setVoyageNumber(String voyageNumber) {
		this.voyageNumber = voyageNumber;
	}

	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public Long getUnitSize() {
		return unitSize;
	}

	public void setUnitSize(Long unitSize) {
		this.unitSize = unitSize;
	}

	public String getBookingOffice() {
		return bookingOffice;
	}

	public void setBookingOffice(String bookingOffice) {
		this.bookingOffice = bookingOffice;
	}

	public String getVessel() {
		return vessel;
	}

	public void setVessel(String vessel) {
		this.vessel = vessel;
	}

	public String getTerminalFunction() {
		return terminalFunction;
	}

	public void setTerminalFunction(String terminalFunction) {
		this.terminalFunction = terminalFunction;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getReasonName() {
		return reasonName;
	}

	public void setReasonName(String reasonName) {
		this.reasonName = reasonName;
	}

	public Long getResolvedEventId() {
		return resolvedEventId;
	}

	public void setResolvedEventId(Long resolvedEventId) {
		this.resolvedEventId = resolvedEventId;
	}

	public String getContainerBookingNumber() {
		return containerBookingNumber;
	}

	public void setContainerBookingNumber(String containerBookingNumber) {
		this.containerBookingNumber = containerBookingNumber;
	}

	
	public String getCustomerOrderReferenceNumber() {
		return customerOrderReferenceNumber;
	}

	public void setCustomerOrderReferenceNumber(String customerOrderReferenceNumber) {
		this.customerOrderReferenceNumber = customerOrderReferenceNumber;
	}

	public String getResolvedEventStatus() {
		return resolvedEventStatus;
	}

	public void setResolvedEventStatus(String resolvedEventStatus) {
		this.resolvedEventStatus = resolvedEventStatus;
	}

	public String getAssociatedAssetSize() {
		return associatedAssetSize;
	}

	public void setAssociatedAssetSize(String associatedAssetSize) {
		this.associatedAssetSize = associatedAssetSize;
	}

	

	public String getResolvedEventSource() {
		return resolvedEventSource;
	}

	public void setResolvedEventSource(String resolvedEventSource) {
		this.resolvedEventSource = resolvedEventSource;
	}

	public Long getResolvedEventOriginalId() {
		return resolvedEventOriginalId;
	}

	public void setResolvedEventOriginalId(Long resolvedEventOriginalId) {
		this.resolvedEventOriginalId = resolvedEventOriginalId;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getBillOfLadingNumber() {
		return billOfLadingNumber;
	}

	public void setBillOfLadingNumber(String billOfLadingNumber) {
		this.billOfLadingNumber = billOfLadingNumber;
	}

	public String getRailBillingNumber() {
		return railBillingNumber;
	}

	public void setRailBillingNumber(String railBillingNumber) {
		this.railBillingNumber = railBillingNumber;
	}

	public String getImportReferenceNumber() {
		return importReferenceNumber;
	}

	public void setImportReferenceNumber(String importReferenceNumber) {
		this.importReferenceNumber = importReferenceNumber;
	}

	public String getPurchaseOrderReferenceNumber() {
		return purchaseOrderReferenceNumber;
	}

	public void setPurchaseOrderReferenceNumber(String purchaseOrderReferenceNumber) {
		this.purchaseOrderReferenceNumber = purchaseOrderReferenceNumber;
	}

	public String getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}


	public String getOnHandNumber() {
		return onHandNumber;
	}


	public void setOnHandNumber(String onHandNumber) {
		this.onHandNumber = onHandNumber;
	}
	
}
