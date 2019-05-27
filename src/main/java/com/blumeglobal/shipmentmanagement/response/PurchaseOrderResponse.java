package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.blumeglobal.shipmentmanagement.request.POLineItemsAPIRequest;



public class PurchaseOrderResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long purchaseOrderId;
	
	private String purchaseOrderNumber;
	
	private String poReferenceId;
	
	private String poExpenditureCodeId;
	
	private Date poDate;
	
	private String poRequistionedBy;
	
	private Date poReceivedDate;
	
	private Date poPromisedDate;	
	
	private Long poTax;
	
	private String poFreightHandling;
	
	private Long poCancelled;
	
	private Long poCompleted;
	
	private Date createdOn;
	
	private String createdBy;
	
	private Date updatedOn;
	
	private String updatedBy;
	
	private String poStatus;
	
	private String logisticProvider;

	private Long leadTime;

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
	
	private String poNotes;
	
	private String origin;
	
	private String destination;
	
	private String organizationCode;
	
	private String deleteFlag;

	private Set<POLineItemsAPIRequest> poLineItems;
	
	public PurchaseOrderResponse() {
		
	}

	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public String getPurchaseOrder() {
		return purchaseOrderNumber;
	}

	public void setPurchaseOrder(String purchaseOrderNumber) {
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

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
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

	public Set<POLineItemsAPIRequest> getPoLineItems() {
		return poLineItems;
	}

	public void setPoLineItems(Set<POLineItemsAPIRequest> poLineItems) {
		this.poLineItems = poLineItems;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}
