package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ShipmentWOResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long woBookingId;

	private String woBookingNumber;

	private String legMode;

	private String originatorName;

	private String receiverName;

	private String reportSource;

	private String transactionCode;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date workorderDate;

	private String carrierCode;

	private String shipmentReferenceNumber;

	private String paymentIndicator;

	private String billOfLadingNumber;

	private String bookingNumber;

	private String railBillingNumber;

	private String vessel;

	private String voyage;

	private String vendorNumber;

	private String masterAirwayBillNumber;

	private String houseAirwayBillNumber;

	private String referenceValue;

	private String originatorOnHandNumber;

	private String originatorOrderReference;

	private String originatorImportRefNumber;

	private String purchaseOrder;

	private String customerReferenceNumber;

	private String customerOrderNumber;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date respondByDate;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date exportCutOffDate;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date lastFreeDay;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date eta;

	private String portOfLoading;

	private String portOfDischarge;

	private String loadLocation;

	private String dischargeLocation;

	private String shipper;

	private String billTo;

	private String unitId;

	private String unitTypeCode;

	private String associatedUnitId;

	private String freightDescription;

	private BigDecimal grossWeight;

	private String woStatus;

	private Long rowNumber;

	private String origin;

	private String destination;

	private String shipmentStatus;

	public ShipmentWOResponse() {

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

	public Long getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(Long rowNumber) {
		this.rowNumber = rowNumber;
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
