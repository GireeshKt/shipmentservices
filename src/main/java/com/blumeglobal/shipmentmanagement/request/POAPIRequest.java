package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class POAPIRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes="purchaseOrderId is an auto generated number incrementing with every upload", required=true)
	private Long purchaseOrderId;
	
	@ApiModelProperty(notes="purchaseOrderNumber is actual purchase order number picked from excel upload", required=true)
	@Size(max = 20, message = "Purchase Order Number: Purchase Order Number must be maximum of 20 characters")
	private String purchaseOrderNumber;
	
	@ApiModelProperty(notes="reference id for purchase order", required=true)
	@Size(max = 12, message = "PO Reference Id: PO Reference Id must be maximum of 12 characters")
	private String poReferenceId;
	
	@ApiModelProperty(notes="expenditure code for purchase order", required=true)
	@Size(max = 10, message = "PO Expenditure Code Id: PO Expenditure Code Id must be maximum of 10 characters")
	private String poExpenditureCodeId;
	
	@ApiModelProperty(notes="purchase order creation date and time", required=true)
	private Date poDate;
	
	@ApiModelProperty(notes="name of company or oraganization requested for purchase order", required=true)
	@Size(max = 12, message = "PO Requistioned By: PO Requistioned By must be maximum of 12 characters")
	private String poRequistionedBy;
	
	@ApiModelProperty(notes="purchase order recieved date and time", required=true)
	private Date poReceivedDate;
	
	@ApiModelProperty(notes="purchase order promised date and time", required=true)
	private Date poPromisedDate;
	
	@ApiModelProperty(notes="tax amount for purchase order", required=true)
	private Long poTax;
	
	@ApiModelProperty(notes="purchase order freight handling", required=true)
	private String poFreightHandling;
	
	@ApiModelProperty(notes="number of purchase order cancelled ", required=true)
	private Long poCancelled;
	
	@ApiModelProperty(notes="number of purchase order completed", required=true)
	private Long poCompleted;
		
	@ApiModelProperty(notes="purchase order created date an time through UI", required=true)
	private String createdOn;
	
	@ApiModelProperty(notes="purchase order created username through UI", required=true)
	private String createdBy;
	
	@ApiModelProperty(notes="purchase order updated date an time through UI", required=true)
	private String updatedOn;
	
	@ApiModelProperty(notes="purchase order updated username through UI", required=true)
	private String updatedBy;
	
	@ApiModelProperty(notes="status of purchase order", required=true)
	private String poStatus;
	
	@ApiModelProperty(notes="name of purchase order logistics provider", required=true)
	private String logisticProvider;

	@ApiModelProperty(notes="lead time of purchase order", required=true)
	private Long leadTime;

	@ApiModelProperty(notes="vendor name for particular purchase order", required=true)
	private String vendorName;
	
	@ApiModelProperty(notes="vendor address for particular purchase order", required=true)
	private String vendorAddress;
	
	@ApiModelProperty(notes="vendor city for particular purchase order", required=true)
	private String vendorCity;
	
	@ApiModelProperty(notes="vendor state for particular purchase order", required=true)
	private String vendorState;
	
	@ApiModelProperty(notes="vendor country for particular purchase order", required=true)
	private String vendorCountry;
	
	@ApiModelProperty(notes="vendor city zip code for particular purchase order", required=true)
	private String vendorZip;
	
	@ApiModelProperty(notes="vendor Duns for particular purchase order", required=true)
	private String vendorDuns;
	
	@ApiModelProperty(notes="buyer name for particular purchase order", required=true)
	private String buyerName;
	
	@ApiModelProperty(notes="buyer Duns for particular purchase order", required=true)
	private String buyerDuns;
	
	@ApiModelProperty(notes="billing complete address for particular purchase order", required=true)
	private String billingAddress;
	
	@ApiModelProperty(notes="notes referred from notes table to map the genaral notes", required=true)
	private String poNotes;
	
	@ApiModelProperty(notes="origin city, state details for purchase order", required=true)
	private String origin;
	
	@ApiModelProperty(notes="destination city, state details for purchase order", required=true)
	private String destination;
	
	@ApiModelProperty(notes="the unique organization code for purchase order", required=true)
	private String organizationCode;
	
	@ApiModelProperty(notes="deleteFlag is Active when purchase order is not deleted using delete api , "
			+ "is Inactive when purchase order is deleted using delete api for an order", required=true)
	private String deleteFlag;

	private Set<POLineItemsAPIRequest> poLineItems;
	
	public POAPIRequest() {
		
	}

	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
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

	public Date getPoDate() {
		return poDate;
	}

	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}

	public String getPoRequistionedBy() {
		return poRequistionedBy;
	}

	public void setPoRequistionedBy(String poRequistionedBy) {
		this.poRequistionedBy = poRequistionedBy;
	}

	public Date getPoReceivedDate() {
		return poReceivedDate;
	}

	public void setPoReceivedDate(Date poReceivedDate) {
		this.poReceivedDate = poReceivedDate;
	}	
	
	

	public Date getPoPromisedDate() {
		return poPromisedDate;
	}

	public void setPoPromisedDate(Date poPromisedDate) {
		this.poPromisedDate = poPromisedDate;
	}

	public Long getPoTax() {
		return poTax;
	}

	public void setPoTax(Long poTax) {
		this.poTax = poTax;
	}

	public String getPoFreightHandling() {
		return poFreightHandling;
	}

	public void setPoFreightHandling(String poFreightHandling) {
		this.poFreightHandling = poFreightHandling;
	}

	public Long getPoCancelled() {
		return poCancelled;
	}

	public void setPoCancelled(Long poCancelled) {
		this.poCancelled = poCancelled;
	}

	public Long getPoCompleted() {
		return poCompleted;
	}

	public void setPoCompleted(Long poCompleted) {
		this.poCompleted = poCompleted;
	}
	
	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Set<POLineItemsAPIRequest> getPoLineItems() {
		return poLineItems;
	}

	public void setPoLineItems(Set<POLineItemsAPIRequest> poLineItems) {
		this.poLineItems = poLineItems;
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

	public Long getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(Long leadTime) {
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

	public String getPoNotes() {
		return poNotes;
	}

	public void setPoNotes(String poNotes) {
		this.poNotes = poNotes;
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

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
	
	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
