package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * The ShipmentEventResponse is used to handle the response of Shipment Event
 * Operation
 *
 */
public class ShipmentEventResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long shipmentEventId;
	private String publisherCode;
	private Long publisherId;
	private String publisherParentCode;
	private Long publisherParentId;
	private Long originatorId;
	private String originatorCode;
	private String originatorName;
	private Long originatorParentId;
	private String originatorParentCode;
	private String eventCode;
	private String eventName;
	private String houseBill;
	private String masterBill;
	private String workOrderNumber;
	private String onHand;
	private String unitId;
	private BigDecimal longitude;
	private BigDecimal latitude;
	private String location;
	private Date eventTime;
	private String reportedBy;
	private String reportSource;
	private String notes;
	private String createdBy;
	
	private Date createDate;
	private Long shipmentReference;
	private String shipmentLegMode;
	private String proNumberBol;
	private String shipmentNumber;

	private String unitState;
	private String state;
	private String country;
	private String originSPLC;
	private String destinationSPLC;
	private String destinationCity;
	private String destinationState;
	private String associatedUnitId;
	private String carrierName;
	private String carrierCode;
	private String receiverCode;
	private String receiverName;
	private String consigneeName;
	private Timestamp sentTime;
	private String sealNumber;
	private String unitTypeCode;
	private String voyageNumber;

	private String terminalCode;
	private Long unitSize;

	private String bookingOffice;
	private String vessel;
	private String terminalFunction;
	private String reasonCode;
	private String reasonName;

	private Long resolvedEventId;
	private String wayBillNumber;
	private String containerBookingNumber;
	private String customerReference;
	private String resolvedEventStatus;
	private String city;
	private String associatedAssetSize;

	private String resolvedEventSource;
	private Long resolvedEventOriginalId;
	
	//kafka service
	private String message;

	public ShipmentEventResponse() {

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getShipmentEventId() {
		return shipmentEventId;
	}

	public void setShipmentEventId(Long shipmentEventId) {
		this.shipmentEventId = shipmentEventId;
	}

	public String getPublisherCode() {
		return publisherCode;
	}

	public void setPublisherCode(String publisherCode) {
		this.publisherCode = publisherCode;
	}

	public Long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublisherParentCode() {
		return publisherParentCode;
	}

	public void setPublisherParentCode(String publisherParentCode) {
		this.publisherParentCode = publisherParentCode;
	}

	public Long getPublisherParentId() {
		return publisherParentId;
	}

	public void setPublisherParentId(Long publisherParentId) {
		this.publisherParentId = publisherParentId;
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

	public Long getOriginatorParentId() {
		return originatorParentId;
	}

	public void setOriginatorParentId(Long originatorParentId) {
		this.originatorParentId = originatorParentId;
	}

	public String getOriginatorParentCode() {
		return originatorParentCode;
	}

	public void setOriginatorParentCode(String originatorParentCode) {
		this.originatorParentCode = originatorParentCode;
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

	public String getOnHand() {
		return onHand;
	}

	public void setOnHand(String onHand) {
		this.onHand = onHand;
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

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getShipmentReference() {
		return shipmentReference;
	}

	public void setShipmentReference(Long shipmentReference) {
		this.shipmentReference = shipmentReference;
	}

	public String getShipmentLegMode() {
		return shipmentLegMode;
	}

	public void setShipmentLegMode(String shipmentLegMode) {
		this.shipmentLegMode = shipmentLegMode;
	}

	public String getProNumberBol() {
		return proNumberBol;
	}

	public void setProNumberBol(String proNumberBol) {
		this.proNumberBol = proNumberBol;
	}

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
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

	public Timestamp getSentTime() {
		return sentTime;
	}

	public void setSentTime(Timestamp sentTime) {
		this.sentTime = sentTime;
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

	public String getWayBillNumber() {
		return wayBillNumber;
	}

	public void setWayBillNumber(String wayBillNumber) {
		this.wayBillNumber = wayBillNumber;
	}

	public String getContainerBookingNumber() {
		return containerBookingNumber;
	}

	public void setContainerBookingNumber(String containerBookingNumber) {
		this.containerBookingNumber = containerBookingNumber;
	}

	public String getCustomerReference() {
		return customerReference;
	}

	public void setCustomerReference(String customerReference) {
		this.customerReference = customerReference;
	}

	public String getResolvedEventStatus() {
		return resolvedEventStatus;
	}

	public void setResolvedEventStatus(String resolvedEventStatus) {
		this.resolvedEventStatus = resolvedEventStatus;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

}
