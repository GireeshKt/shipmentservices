package com.blumeglobal.shipmentmanagement.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class POTransactionsAPIResponse implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long PoTransactionID;

	@JsonIgnore
	private POLineItemsResponse poTransactionRef;

	private String poStatus;
	
	private String poTransactionStatus;

	private Date poTransactionDate;

	private String createdOn;

	private String createdBy;
	
	private String updatedOn;

	private String updatedBy;
	
	private String organizationCode;
	
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

	public POLineItemsResponse getPoTransactionRef() {
		return poTransactionRef;
	}

	public void setPoTransactionRef(POLineItemsResponse poTransactionRef) {
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
