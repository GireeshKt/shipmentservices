package com.blumeglobal.shipmentmanagement.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VW_SHIPMENTWORKORDERS")
public class ShipmentWO {

	@Id
	@Column(name = "WORKORDERID")
	private Long woBookingId;

	@Column(name = "WORKORDERNUMBER")
	private String woBookingNumber;

	@Column(name = "LEGMODE")
	private String legMode;

	@Column(name = "ORIGINATORID")
	private String originatorid;

	@Column(name = "RECEIVERID")
	private String receiverid;

	@Column(name = "ORIGINATORNAME")
	private String originatorName;

	@Column(name = "RECEIVERNAME")
	private String receiverName;

	@Column(name = "REPORTSOURCE")
	private String reportSource;

	@Column(name = "TRANSACTIONCODE")
	private String transactionCode;

	@Column(name = "WORKORDERDATE")
	private Date workorderDate;

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

	@Column(name = "FREIGHTDESCRIPTION")
	private String freightDescription;

	@Column(name = "GROSSWEIGHT")
	private BigDecimal grossWeight;

	@Column(name = "STATUS")
	private String woStatus;

	@Column(name = "RN1")
	private Long shipmentRefRowNumber;

	@Column(name = "RN2")
	private Long bookingNoRowNumber;

	@Column(name = "RN3")
	private Long bolNoRowNumber;

	@Column(name = "ORIGIN")
	private String origin;

	@Column(name = "DESTINATION")
	private String destination;

	@Column(name = "SHIPMENTSTATUS")
	private String shipmentStatus;

	public ShipmentWO() {

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

	public String getOriginatorid() {
		return originatorid;
	}

	public void setOriginatorid(String originatorid) {
		this.originatorid = originatorid;
	}

	public String getReceiverid() {
		return receiverid;
	}

	public void setReceiverid(String receiverid) {
		this.receiverid = receiverid;
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

	public String getFreightDescription() {
		return freightDescription;
	}

	public void setFreightDescription(String freightDescription) {
		this.freightDescription = freightDescription;
	}

	public BigDecimal getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
	}

	public String getWoStatus() {
		return woStatus;
	}

	public void setWoStatus(String woStatus) {
		this.woStatus = woStatus;
	}

	public Long getShipmentRefRowNumber() {
		return shipmentRefRowNumber;
	}

	public void setShipmentRefRowNumber(Long shipmentRefRowNumber) {
		this.shipmentRefRowNumber = shipmentRefRowNumber;
	}

	public Long getBookingNoRowNumber() {
		return bookingNoRowNumber;
	}

	public void setBookingNoRowNumber(Long bookingNoRowNumber) {
		this.bookingNoRowNumber = bookingNoRowNumber;
	}

	public Long getBolNoRowNumber() {
		return bolNoRowNumber;
	}

	public void setBolNoRowNumber(Long bolNoRowNumber) {
		this.bolNoRowNumber = bolNoRowNumber;
	}

	/**
	 * @return the workorderDate
	 */
	public Date getWorkorderDate() {
		return workorderDate;
	}

	/**
	 * @param workorderDate
	 *            the workorderDate to set
	 */
	public void setWorkorderDate(Date workorderDate) {
		this.workorderDate = workorderDate;
	}

	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * @param origin
	 *            the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @param destination
	 *            the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * @return the shipmentStatus
	 */
	public String getShipmentStatus() {
		return shipmentStatus;
	}

	/**
	 * @param shipmentStatus
	 *            the shipmentStatus to set
	 */
	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}

}
