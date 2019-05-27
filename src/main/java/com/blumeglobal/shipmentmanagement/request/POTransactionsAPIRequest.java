package com.blumeglobal.shipmentmanagement.request;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class POTransactionsAPIRequest implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes="PoTransactionId is an auto generated number incrementing with every upload", required=true)
	private Long PoTransactionID;
	
	@ApiModelProperty(notes="poItemId is an auto generated number incrementing with every upload", required=true)
	private POLineItemsAPIRequest poTransactionRef;
	
	@ApiModelProperty(notes="purchase order line items status for each item in line item table", required=true)
	private String poStatus;
	
	@ApiModelProperty(notes="PO transaction status", required=true)
	private String poTransactionStatus;
	
	@ApiModelProperty(notes="PO transaction date an time when line item for each order is uploaded", required=true)
	private Date poTransactionDate;
	
	@ApiModelProperty(notes="PO transaction created date an time through UI", required=true)
	private String createdOn;
	
	@ApiModelProperty(notes="PO transaction created username through UI", required=true)
	private String createdBy;
	
	@ApiModelProperty(notes="PO transaction updated date an time through UI", required=true)
	private String updatedOn;

	@ApiModelProperty(notes="PO transaction updated username through UI", required=true)
	private String updatedBy;
	
	@ApiModelProperty(notes="the unique organization code for purchase order", required=true)
	private String organizationCode;
	
	@ApiModelProperty(notes="deleteFlag is Active when PO transaction is not deleted using delete api , "
			+ "is Inactive when PO transaction is deleted using delete api for an order", required=true)
	private String deleteFlag;

	public Long getPoTransactionID() {
		return PoTransactionID;
	}

	public void setPoTransactionID(Long poTransactionID) {
		PoTransactionID = poTransactionID;
	}

	public String getPoStatus() {
		return poStatus;
	}

	public void setPoStatus(String poStatus) {
		this.poStatus = poStatus;
	}

	public String getPoTransactionStatus() {
		return poTransactionStatus;
	}

	public void setPoTransactionStatus(String poTransactionStatus) {
		this.poTransactionStatus = poTransactionStatus;
	}

	public Date getPoTransactionDate() {
		return poTransactionDate;
	}

	public void setPoTransactionDate(Date poTransactionDate) {
		this.poTransactionDate = poTransactionDate;
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

	public POLineItemsAPIRequest getPoTransactionRef() {
		return poTransactionRef;
	}

	public void setPoTransactionRef(POLineItemsAPIRequest poTransactionRef) {
		this.poTransactionRef = poTransactionRef;
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
