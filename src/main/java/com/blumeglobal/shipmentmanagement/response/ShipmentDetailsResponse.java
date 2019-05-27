package com.blumeglobal.shipmentmanagement.response;

import java.util.Date;
import java.util.List;

import com.blumeglobal.shipmentmanagement.model.ShipmentLegs;

public class ShipmentDetailsResponse {
	
	private Long shipmentId;
	private Long originatorid;
	private String originatorName;
	private String legMode;
	private String shipmentReferenceNumber;
	private String billOfLadingNumber;
	private String bookingNumber;
	private String railBillingNumber;
	private String unitId;
	private String vessel;
	private String voyage;
	private String vendorNumber;
	private String originatorOnHandNumber;
	private String originatorOrderReference;
	private String originatorImportRefNumber;
	private String purchaseOrder;
	private String customerReferenceNumber;
	private String customerOrderNumber;
	private Date eta;
	private String asnReferenceNumber;
	private String origin;
	private String destination;
	private List<ShipmentLegs> legs;
	public Long getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(Long shipmentId) {
		this.shipmentId = shipmentId;
	}
	public Long getOriginatorid() {
		return originatorid;
	}
	public void setOriginatorid(Long originatorid) {
		this.originatorid = originatorid;
	}
	public String getOriginatorName() {
		return originatorName;
	}
	public void setOriginatorName(String originatorName) {
		this.originatorName = originatorName;
	}
	public String getLegMode() {
		return legMode;
	}
	public void setLegMode(String legMode) {
		this.legMode = legMode;
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
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
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
	public Date getEta() {
		return eta;
	}
	public void setEta(Date eta) {
		this.eta = eta;
	}
	public String getAsnReferenceNumber() {
		return asnReferenceNumber;
	}
	public void setAsnReferenceNumber(String asnReferenceNumber) {
		this.asnReferenceNumber = asnReferenceNumber;
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
	public List<ShipmentLegs> getLegs() {
		return legs;
	}
	public void setLegs(List<ShipmentLegs> legs) {
		this.legs = legs;
	}

}
