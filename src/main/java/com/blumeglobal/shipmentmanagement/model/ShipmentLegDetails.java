package com.blumeglobal.shipmentmanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ShipmentLeg entity.
 */
@Entity
@Table(name = "VW_WO_SHIPMENTLEGDETAILS")
public class ShipmentLegDetails {

	@Id
	@Column(name = "WORKORDERID")
	private Long woBookingId;

	@Column(name = "WORKORDERNUMBER")
	private String woBookingNumber;

	@Column(name = "LEGMODE")
	private String legMode;

	@Column(name = "ORIGINATORID")
	private Long originatorid;

	@Column(name = "RECEIVERID")
	private Long receiverid;

	@Column(name = "ORIGINATORNAME")
	private String originatorName;

	@Column(name = "RECEIVERNAME")
	private String receiverName;

	@Column(name = "REPORTSOURCE")
	private String reportSource;

	@Column(name = "TRANSACTIONCODE")
	private String transactionCode;

	@Column(name = "WORKORDERDATE")
	private Date woBookingDate;

	@Column(name = "CARRIERCODE")
	private String carrierCode;

	@Column(name = "SHIPMENTREFERENCENUMBER")
	private String shipmentReferenceNumber;

	@Column(name = "PAYMENTINDICATOR")
	private String paymentIndicator;

	@Column(name = "BILLOFLADINGNUMBER")
	private String billOfLadingNumber;

	@Column(name = "BOOKINGNUMBER")
	private String bookingNumber;

	@Column(name = "RAILBILLINGNUMBER")
	private String railBillingNumber;

	@Column(name = "VESSEL")
	private String vessel;

	@Column(name = "VOYAGE")
	private String voyage;

	@Column(name = "VENDORNUMBER")
	private String vendorNumber;

	@Column(name = "MASTERAIRWAYBILLNUMBER")
	private String masterAirwayBillNumber;

	@Column(name = "HOUSEAIRWAYBILLNUMBER")
	private String houseAirwayBillNumber;

	@Column(name = "REFERENCEVALUE")
	private String referenceValue;

	@Column(name = "ORIGINATORONHANDNUMBER")
	private String originatorOnHandNumber;

	@Column(name = "ORIGINATORORDERREFERENCE")
	private String originatorOrderReference;

	@Column(name = "ORIGINATORIMPORTREFNUMBER")
	private String originatorImportRefNumber;

	@Column(name = "PURCHASEORDER")
	private String purchaseOrder;

	@Column(name = "CUSTOMERREFERENCENUMBER")
	private String customerReferenceNumber;

	@Column(name = "CUSTOMERORDERNUMBER")
	private String customerOrderNumber;

	@Column(name = "RESPONDBYDATE")
	private Date respondByDate;

	@Column(name = "EXPORTCUTOFFDATE")
	private Date exportCutOffDate;

	@Column(name = "LASTFREEDAY")
	private Date lastFreeDay;

	@Column(name = "ETA")
	private Date eta;

	@Column(name = "PORTOFLOADING")
	private String portOfLoading;

	@Column(name = "PORTOFDISCHARGE")
	private String portOfDischarge;

	@Column(name = "LOADLOCATION")
	private String loadLocation;

	@Column(name = "DISCHARGELOCATION")
	private String dischargeLocation;

	@Column(name = "SHIPPER")
	private String shipper;

	@Column(name = "BILLTO")
	private String billTo;

	@Column(name = "UNITID")
	private String unitId;

	@Column(name = "UNITTYPECODE")
	private String unitTypeCode;

	@Column(name = "ASSOCIATEDUNITID")
	private String associatedUnitId;

	@Column(name = "STATUS")
	private String woStatus;

	@Column(name = "STATUSID")
	private Long statusID;

	@Column(name = "ORIGIN")
	private String origin;

	@Column(name = "ORIGINADDRESS1")
	private String originAddress1;

	@Column(name = "ORIGINADDRESS2")
	private String originAddress2;

	@Column(name = "ORIGINCITY")
	private String originCity;

	@Column(name = "ORIGINSTATE")
	private String originState;

	@Column(name = "ORIGINZIPCODE")
	private String originZipcode;

	@Column(name = "ORIGINCOUNTRY")
	private String originCountry;

	@Column(name = "DESTINATION")
	private String destination;

	@Column(name = "DESTINATIONADDRESS1")
	private String destinationAddress1;

	@Column(name = "DESTINATIONADDRESS2")
	private String destinationAddress2;

	@Column(name = "DESTINATIONCITY")
	private String destinationCity;

	@Column(name = "DESTINATIONSTATE")
	private String destinationState;

	@Column(name = "DESTINATIONZIPCODE")
	private String destinationZipCode;

	@Column(name = "DESTINATIONCOUNTRY")
	private String destinationCountry;

	@Column(name = "PIECECOUNT")
	private String pieceCount;

	@Column(name = "WEIGHT")
	private String weight;

	@Column(name = "LENGTH")
	private String lenght;

	@Column(name = "HEIGHT")
	private String height;

	@Column(name = "WIDTH")
	private String width;

	public ShipmentLegDetails() {

	}

	public Long getWoBookingId() {
		return woBookingId;
	}

	public void setWoBookingId(Long woBookingId) {
		this.woBookingId = woBookingId;
	}

	public String getWoBookingNumber() {
		return woBookingNumber;
	}

	public void setWoBookingNumber(String woBookingNumber) {
		this.woBookingNumber = woBookingNumber;
	}

	public String getLegMode() {
		return legMode;
	}

	public void setLegMode(String legMode) {
		this.legMode = legMode;
	}

	public String getOriginatorName() {
		return originatorName;
	}

	public void setOriginatorName(String originatorName) {
		this.originatorName = originatorName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReportSource() {
		return reportSource;
	}

	public void setReportSource(String reportSource) {
		this.reportSource = reportSource;
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public Date getWoBookingDate() {
		return woBookingDate;
	}

	public void setWoBookingDate(Date woBookingDate) {
		this.woBookingDate = woBookingDate;
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public String getShipmentReferenceNumber() {
		return shipmentReferenceNumber;
	}

	public void setShipmentReferenceNumber(String shipmentReferenceNumber) {
		this.shipmentReferenceNumber = shipmentReferenceNumber;
	}

	public String getPaymentIndicator() {
		return paymentIndicator;
	}

	public void setPaymentIndicator(String paymentIndicator) {
		this.paymentIndicator = paymentIndicator;
	}

	public String getBillOfLadingNumber() {
		return billOfLadingNumber;
	}

	public void setBillOfLadingNumber(String billOfLadingNumber) {
		this.billOfLadingNumber = billOfLadingNumber;
	}

	public String getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public String getRailBillingNumber() {
		return railBillingNumber;
	}

	public void setRailBillingNumber(String railBillingNumber) {
		this.railBillingNumber = railBillingNumber;
	}

	public String getVessel() {
		return vessel;
	}

	public void setVessel(String vessel) {
		this.vessel = vessel;
	}

	public String getVoyage() {
		return voyage;
	}

	public void setVoyage(String voyage) {
		this.voyage = voyage;
	}

	public String getVendorNumber() {
		return vendorNumber;
	}

	public void setVendorNumber(String vendorNumber) {
		this.vendorNumber = vendorNumber;
	}

	public String getMasterAirwayBillNumber() {
		return masterAirwayBillNumber;
	}

	public void setMasterAirwayBillNumber(String masterAirwayBillNumber) {
		this.masterAirwayBillNumber = masterAirwayBillNumber;
	}

	public String getHouseAirwayBillNumber() {
		return houseAirwayBillNumber;
	}

	public void setHouseAirwayBillNumber(String houseAirwayBillNumber) {
		this.houseAirwayBillNumber = houseAirwayBillNumber;
	}

	public String getReferenceValue() {
		return referenceValue;
	}

	public void setReferenceValue(String referenceValue) {
		this.referenceValue = referenceValue;
	}

	public String getOriginatorOnHandNumber() {
		return originatorOnHandNumber;
	}

	public void setOriginatorOnHandNumber(String originatorOnHandNumber) {
		this.originatorOnHandNumber = originatorOnHandNumber;
	}

	public String getOriginatorOrderReference() {
		return originatorOrderReference;
	}

	public void setOriginatorOrderReference(String originatorOrderReference) {
		this.originatorOrderReference = originatorOrderReference;
	}

	public String getOriginatorImportRefNumber() {
		return originatorImportRefNumber;
	}

	public void setOriginatorImportRefNumber(String originatorImportRefNumber) {
		this.originatorImportRefNumber = originatorImportRefNumber;
	}

	public String getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(String purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public String getCustomerReferenceNumber() {
		return customerReferenceNumber;
	}

	public void setCustomerReferenceNumber(String customerReferenceNumber) {
		this.customerReferenceNumber = customerReferenceNumber;
	}

	public String getCustomerOrderNumber() {
		return customerOrderNumber;
	}

	public void setCustomerOrderNumber(String customerOrderNumber) {
		this.customerOrderNumber = customerOrderNumber;
	}

	public Date getRespondByDate() {
		return respondByDate;
	}

	public void setRespondByDate(Date respondByDate) {
		this.respondByDate = respondByDate;
	}

	public Date getExportCutOffDate() {
		return exportCutOffDate;
	}

	public void setExportCutOffDate(Date exportCutOffDate) {
		this.exportCutOffDate = exportCutOffDate;
	}

	public Date getLastFreeDay() {
		return lastFreeDay;
	}

	public void setLastFreeDay(Date lastFreeDay) {
		this.lastFreeDay = lastFreeDay;
	}

	public Date getEta() {
		return eta;
	}

	public void setEta(Date eta) {
		this.eta = eta;
	}

	public String getPortOfLoading() {
		return portOfLoading;
	}

	public void setPortOfLoading(String portOfLoading) {
		this.portOfLoading = portOfLoading;
	}

	public String getPortOfDischarge() {
		return portOfDischarge;
	}

	public void setPortOfDischarge(String portOfDischarge) {
		this.portOfDischarge = portOfDischarge;
	}

	public String getLoadLocation() {
		return loadLocation;
	}

	public void setLoadLocation(String loadLocation) {
		this.loadLocation = loadLocation;
	}

	public String getDischargeLocation() {
		return dischargeLocation;
	}

	public void setDischargeLocation(String dischargeLocation) {
		this.dischargeLocation = dischargeLocation;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public String getBillTo() {
		return billTo;
	}

	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitTypeCode() {
		return unitTypeCode;
	}

	public void setUnitTypeCode(String unitTypeCode) {
		this.unitTypeCode = unitTypeCode;
	}

	public String getAssociatedUnitId() {
		return associatedUnitId;
	}

	public void setAssociatedUnitId(String associatedUnitId) {
		this.associatedUnitId = associatedUnitId;
	}

	public String getWoStatus() {
		return woStatus;
	}

	public void setWoStatus(String woStatus) {
		this.woStatus = woStatus;
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

	public String getOriginZipcode() {
		return originZipcode;
	}

	public void setOriginZipcode(String originZipcode) {
		this.originZipcode = originZipcode;
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

	public Long getOriginatorid() {
		return originatorid;
	}

	public void setOriginatorid(Long originatorid) {
		this.originatorid = originatorid;
	}

	public Long getReceiverid() {
		return receiverid;
	}

	public void setReceiverid(Long receiverid) {
		this.receiverid = receiverid;
	}

	public Long getStatusID() {
		return statusID;
	}

	public void setStatusID(Long statusID) {
		this.statusID = statusID;
	}


	/**
	 * @return the pieceCount
	 */
	public String getPieceCount() {
		return pieceCount;
	}

	/**
	 * @param pieceCount
	 *            the pieceCount to set
	 */
	public void setPieceCount(String pieceCount) {
		this.pieceCount = pieceCount;
	}

	/**
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * @return the lenght
	 */
	public String getLenght() {
		return lenght;
	}

	/**
	 * @param lenght
	 *            the lenght to set
	 */
	public void setLenght(String lenght) {
		this.lenght = lenght;
	}

	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(String width) {
		this.width = width;
	}

}
