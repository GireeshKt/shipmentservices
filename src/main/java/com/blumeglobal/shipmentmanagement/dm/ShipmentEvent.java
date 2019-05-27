package com.blumeglobal.shipmentmanagement.dm;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Shipmentevents entity.
 */

@Entity
@Table(name = "LE_SHIPMENTEVENTS")
public class ShipmentEvent implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_leshipmenteventid")
	@SequenceGenerator(name = "seq_leshipmenteventid", allocationSize = 1, sequenceName = "SEQ_LESHIPMENTEVENTID")
	@Column(name = "SHIPMENTEVENTID", precision = 22, scale = 0)
	private Long shipmentEventId;

	@Column(name = "PUBLISHERCODE", length = 100)
	private String publisherCode;
	
	@Column(name = "STOPTYPE", length = 50)
	private String stopType;
	
	@Column(name = "SIGNEDBY", length = 50)
	private String signedBy;

	@Column(name = "PUBLISHERID", precision = 22, scale = 0)
	private Long publisherId;

	@Column(name = "PUBLISHERPARENTCODE", length = 20)
	private String publisherParentCode;

	@Column(name = "PUBLISHERPARENTID", precision = 22, scale = 0)
	private Long publisherParentId;

	@Column(name = "ORIGINATORID", precision = 22, scale = 0)
	private Long originatorId;

	@Column(name = "ORIGINATORCODE", length = 20)
	private String originatorCode;

	@Column(name = "ORIGINATORNAME", length = 100)
	private String originatorName;

	@Column(name = "ORIGINATORPARENTID", precision = 22, scale = 0)
	private Long originatorParentId;

	@Column(name = "ORIGINATORPARENTCODE", length = 20)
	private String originatorParentCode;

	@Column(name = "EVENTCODE", length = 20)
	private String eventCode;

	@Column(name = "EVENTNAME", length = 50)
	private String eventName;

	@Column(name = "HOUSEBILL", length = 50)
	private String houseBill;

	@Column(name = "MASTERBILL", length = 50)
	private String masterBill;

	@Column(name = "WORKORDERNUMBER", length = 50)
	private String workOrderNumber;

	@Column(name = "ONHAND", length = 50)
	private String onHandNumber;

	@Column(name = "UNITID", length = 50)
	private String unitId;

	@Column(name = "LONGITUDE", precision = 22, scale = 0)
	private BigDecimal longitude;

	@Column(name = "LATITUDE", precision = 22, scale = 0)
	private BigDecimal latitude;

	@Column(name = "LOCATION", length = 100)
	private String location;

	@Column(name = "EVENTTIME")
	private Date eventTime;

	@Column(name = "REPORTEDBY", length = 100)
	private String reportedBy;

	@Column(name = "REPORTSOURCE", length = 20)
	private String reportSource;

	@Column(name = "NOTES", length = 250)
	private String notes;

	@Column(name = "CREATEDBY", length = 50)
	private String createdBy;

	@Column(name = "CREATEDATE")
	private Date createDate;

	@Column(name = "SHIPMENTREFERENCE")
	private Long shipmentReference;

	@Column(name = "SHIPMENTLEGREF")
	private Long shipmentLegRef;

	@Column(name = "SHIPMENTLEGMODE", length = 50)
	private String shipmentLegMode;

	@Column(name = "PRONUMBERBOL", length = 50)
	private String proNumberBol;

	@Column(name = "SHIPMENTNUMBER", length = 50)
	private String shipmentReferenceNumber;

	@Column(name = "UNITSTATE", length = 20)
	private String unitState;

	@Column(name = "STATE", length = 20)
	private String state;

	@Column(name = "COUNTRY", length = 20)
	private String country;

	@Column(name = "ORIGINSPLC", length = 9)
	private String originSPLC;

	@Column(name = "DESTINATIONSPLC", length = 9)
	private String destinationSPLC;

	@Column(name = "DESTINATIONCITY", length = 50)
	private String destinationCity;

	@Column(name = "DESTINATIONSTATE", length = 10)
	private String destinationState;

	@Column(name = "ASSOCIATEDUNITID", length = 15)
	private String associatedUnitId;

	@Column(name = "CARRIERNAME", length = 100)
	private String carrierName;

	@Column(name = "CARRIERCODE", length = 60)
	private String carrierCode;

	@Column(name = "RECEIVERCODE", length = 60)
	private String receiverCode;

	@Column(name = "RECEIVERNAME", length = 100)
	private String receiverName;

	@Column(name = "CONSIGNEENAME", length = 60)
	private String consigneeName;

	@Column(name = "SEALNUMBER", length = 25)
	private String sealNumber;

	@Column(name = "UNITTYPECODE", length = 20)
	private String unitTypeCode;

	@Column(name = "VOYAGENUMBER", length = 50)
	private String voyageNumber;

	@Column(name = "TERMINALCODE", length = 50)
	private String terminalCode;

	@Column(name = "UNITSIZE")
	private Long unitSize;

	@Column(name = "BOOKINGOFFICE", length = 50)
	private String bookingOffice;

	@Column(name = "VESSEL", length = 50)
	private String vessel;

	@Column(name = "TERMINALFUNCTION", length = 50)
	private String terminalFunction;

	@Column(name = "CITY", length = 50)
	private String city;

	@Column(name = "UPDATEDBY", length = 50)
	private String updatedBy;

	@Column(name = "UPDATEDDATE")
	private Date updatedDate;

	@Column(name = "REASONCODE", length = 50)
	private String reasonCode;

	@Column(name = "REASONNAME", length = 100)
	private String reasonName;

	@Column(name = "RESOLVEDEVENTID")
	private Long resolvedEventId;

	@Column(name = "WAYBILLNUMBER", length = 50)
	private String railBillingNumber;

	@Column(name = "CONTAINERBOOKINGNUMBER", length = 100)
	private String containerBookingNumber;

	@Column(name = "CUSTOMERREFERENCE", length = 100)  
	private String customerReference;
	
	@Column(name = "CUSTOMERORDERREFERENCENUMBER", length = 50)  
	private String customerOrderReferenceNumber;

	@Column(name = "RESOLVEDEVENTSTATUS", length = 15)
	private String resolvedEventStatus;

	@Column(name = "ASSOCIATEDASSETSIZE", length = 50)
	private String associatedAssetSize;

	@Column(name = "RESOLVEDEVENTSOURCE", length = 15)
	private String resolvedEventSource;

	@Column(name = "RESOLVEDEVENTORIGINALID")
	private Long resolvedEventOriginalId;
	
	@Column(name = "POSTALCODE", length = 50)
	private String postalCode;
	
	@Column(name = "UTCEVENTTIME")
	private Timestamp utcEventTime;
	
	@Column(name = "SOURCETIMEZONE", length = 80)
	private String sourceTimeZone;
	
	@Column(name = "BILLOFLADINGNUMBER", length = 50)
	private String billOfLadingNumber;
	
	@Column(name = "IMPORTREFERENCENUMBER", length = 50)
	private String importReferenceNumber;
	
	@Column(name = "PURCHASEORDERREFERENCENUMBER", length = 50)
	private String purchaseOrderReferenceNumber;
	
	@Column(name = "BOOKINGNUMBER", length = 50)
	private String bookingNumber;  
	
	@Column(name = "ISLEGFOUND")
	private Integer isLegFound;
	// Constructors

	/** default constructor */
	public ShipmentEvent() {
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

	public String getOnHandNumber() {
		return onHandNumber;
	}

	public void setOnHandNumber(String onHandNumber) {
		this.onHandNumber = onHandNumber;
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

	public Long getShipmentLegRef() {
		return shipmentLegRef;
	}

	public void setShipmentLegRef(Long shipmentLegRef) {
		this.shipmentLegRef = shipmentLegRef;
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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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

	public Timestamp getUtcEventTime() {
		return utcEventTime;
	}

	public void setUtcEventTime(Timestamp utcEventTime) {
		this.utcEventTime = utcEventTime;
	}

	public String getSourceTimeZone() {
		return sourceTimeZone;
	}

	public void setSourceTimeZone(String sourceTimeZone) {
		this.sourceTimeZone = sourceTimeZone;
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

	public Integer getIsLegFound() {
		return isLegFound;
	}

	public void setIsLegFound(Integer isLegFound) {
		this.isLegFound = isLegFound;
	}

	public String getCustomerReference() {
		return customerReference;
	}

	public void setCustomerReference(String customerReference) {
		this.customerReference = customerReference;
	}
	
}