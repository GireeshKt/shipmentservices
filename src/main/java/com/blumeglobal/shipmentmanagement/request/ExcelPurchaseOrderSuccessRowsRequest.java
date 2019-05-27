package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;

import javax.validation.constraints.Size;

/**
 * The ExcelShipmentSuccessRowsRequest is used to handle the request of Valid Excel Rows for PO Excel Uploads operation
 *
 */
public class ExcelPurchaseOrderSuccessRowsRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Size(max = 20, message = "Purchase Order Number: Purchase Order Number must be maximum of 20 characters")
	private String purchaseOrderNumber;

	@Size(max = 12, message = "PO Reference Id: PO Reference Id must be maximum of 12 characters")
	private String poReferenceId;

	@Size(max = 10, message = "PO Expenditure Code Id: PO Expenditure Code Id must be maximum of 10 characters")
	private String poExpenditureCodeId;

	@Size(max = 12, message = "PO Requistioned By: PO Requistioned By must be maximum of 12 characters")
	private String poRequistionedBy;

	private String poIssueDate;

	private String poResponseDate;

	private String PODate;

	private String poTax;

	private String poFreightHandling;

	private String poStatus;

	private String logisticProvider;

	private String leadTime;

	private String vendorName;

	private String vendorAddress;

	private String vendorCity;

	private String vendorState;

	private String vendorCountry;

	private String vendorZip;

	private String vendorDuns;

	private String buyerName;

	private String buyerDuns;

	private String billingAddress;

	private String notes;

	private String origin;

	private String destination;

	public ExcelPurchaseOrderSuccessRowsRequest()  {

	}

	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	public String getPoReferenceId() {
		return poReferenceId;
	}

	public void setPoReferenceId(String poReferenceId) {
		this.poReferenceId = poReferenceId;
	}

	public String getPoExpenditureCodeId() {
		return poExpenditureCodeId;
	}

	public void setPoExpenditureCodeId(String poExpenditureCodeId) {
		this.poExpenditureCodeId = poExpenditureCodeId;
	}

	public String getPoRequistionedBy() {
		return poRequistionedBy;
	}

	public void setPoRequistionedBy(String poRequistionedBy) {
		this.poRequistionedBy = poRequistionedBy;
	}


	public String getPoIssueDate() {
		return poIssueDate;
	}

	public void setPoIssueDate(String poIssueDate) {
		this.poIssueDate = poIssueDate;
	}

	public String getPoResponseDate() {
		return poResponseDate;
	}

	public void setPoResponseDate(String poResponseDate) {
		this.poResponseDate = poResponseDate;
	}

	public String getPODate() {
		return PODate;
	}

	public void setPODate(String pODate) {
		PODate = pODate;
	}

	public String getPoTax() {
		return poTax;
	}

	public void setPoTax(String poTax) {
		this.poTax = poTax;
	}

	public String getPoFreightHandling() {
		return poFreightHandling;
	}

	public void setPoFreightHandling(String poFreightHandling) {
		this.poFreightHandling = poFreightHandling;
	}

	public String getPoStatus() {
		return poStatus;
	}

	public void setPoStatus(String poStatus) {
		this.poStatus = poStatus;
	}

	public String getLogisticProvider() {
		return logisticProvider;
	}

	public void setLogisticProvider(String logisticProvider) {
		this.logisticProvider = logisticProvider;
	}

	public String getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorAddress() {
		return vendorAddress;
	}

	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}

	public String getVendorCity() {
		return vendorCity;
	}

	public void setVendorCity(String vendorCity) {
		this.vendorCity = vendorCity;
	}

	public String getVendorState() {
		return vendorState;
	}

	public void setVendorState(String vendorState) {
		this.vendorState = vendorState;
	}

	public String getVendorCountry() {
		return vendorCountry;
	}

	public void setVendorCountry(String vendorCountry) {
		this.vendorCountry = vendorCountry;
	}

	public String getVendorZip() {
		return vendorZip;
	}

	public void setVendorZip(String vendorZip) {
		this.vendorZip = vendorZip;
	}

	public String getVendorDuns() {
		return vendorDuns;
	}

	public void setVendorDuns(String vendorDuns) {
		this.vendorDuns = vendorDuns;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerDuns() {
		return buyerDuns;
	}

	public void setBuyerDuns(String buyerDuns) {
		this.buyerDuns = buyerDuns;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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
}