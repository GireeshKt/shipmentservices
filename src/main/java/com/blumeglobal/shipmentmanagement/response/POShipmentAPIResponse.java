package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


public class POShipmentAPIResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String purchaseOrderNumber;
	private String poReferenceId;
	private Date poRequestDate;
	private Date poPromiseDate;	
	private Date poIssueDate;
	private String createdOn;
	private String vendorName;
	private String vendorAddress;
	private String buyerName;
	private String billingAddress;
	private Set<POLineShipItemsResponse> poLineItems;
	
	public POShipmentAPIResponse() {

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

	public Date getPoRequestDate() {
		return poRequestDate;
	}

	public void setPoRequestDate(Date poRequestDate) {
		this.poRequestDate = poRequestDate;
	}

	public Date getPoPromiseDate() {
		return poPromiseDate;
	}

	public void setPoPromiseDate(Date poPromiseDate) {
		this.poPromiseDate = poPromiseDate;
	}

	public Date getPoIssueDate() {
		return poIssueDate;
	}

	public void setPoIssueDate(Date poIssueDate) {
		this.poIssueDate = poIssueDate;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
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

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Set<POLineShipItemsResponse> getPoLineItems() {
		return poLineItems;
	}

	public void setPoLineItems(Set<POLineShipItemsResponse> poLineItems) {
		this.poLineItems = poLineItems;
	}

}
