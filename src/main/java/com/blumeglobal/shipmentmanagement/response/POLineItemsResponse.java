package com.blumeglobal.shipmentmanagement.response;

import java.util.Date;
import java.util.Set;

import com.blumeglobal.shipmentmanagement.request.POAPIRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class POLineItemsResponse implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
    private Long poItemId;
    
    @JsonIgnore
	private POAPIRequest poLineItemsRef;
	
	private Long quantity;
	
	private String unitOfMeasurement;
	
	private double price;
	
	private String deleteFlag;
	
	private String skuNumber;
	
	private String skuDescription;
	
	private Long commitedQuantity;
	
	private Date dueDate;
	
	private Date promisedDate;
	
	private String shipToLocationCode;
	
	private String shipToCompany;
	
	private String shipToAddress;
	
	private String shipToCity;
	
	private String shipToState;
	
	private String shipToZip;
	
	private String shipToCountry;
	
	private String createdOn;

	private String createdBy;
	
	private String updatedOn;

	private String updatedBy;
	
	private String lineItemStatus;
	
	private Set<POTransactionsAPIResponse> poTransactions;
	
	public POLineItemsResponse() {
		
	}

	public Long getPoItemId() {
		return poItemId;
	}

	public void setPoItemId(Long poItemId) {
		this.poItemId = poItemId;
	}

	public POAPIRequest getPoLineItemsRef() {
		return poLineItemsRef;
	}

	public void setPoLineItemsRef(POAPIRequest poLineItemsRef) {
		this.poLineItemsRef = poLineItemsRef;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getSkuNumber() {
		return skuNumber;
	}

	public void setSkuNumber(String skuNumber) {
		this.skuNumber = skuNumber;
	}

	public String getSkuDescription() {
		return skuDescription;
	}

	public void setSkuDescription(String skuDescription) {
		this.skuDescription = skuDescription;
	}

	public Long getCommitedQuantity() {
		return commitedQuantity;
	}

	public void setCommitedQuantity(Long commitedQuantity) {
		this.commitedQuantity = commitedQuantity;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPromisedDate() {
		return promisedDate;
	}

	public void setPromisedDate(Date promisedDate) {
		this.promisedDate = promisedDate;
	}

	public String getShipToLocationCode() {
		return shipToLocationCode;
	}

	public void setShipToLocationCode(String shipToLocationCode) {
		this.shipToLocationCode = shipToLocationCode;
	}

	public String getShipToCompany() {
		return shipToCompany;
	}

	public void setShipToCompany(String shipToCompany) {
		this.shipToCompany = shipToCompany;
	}

	public String getShipToAddress() {
		return shipToAddress;
	}

	public void setShipToAddress(String shipToAddress) {
		this.shipToAddress = shipToAddress;
	}

	public String getShipToCity() {
		return shipToCity;
	}

	public void setShipToCity(String shipToCity) {
		this.shipToCity = shipToCity;
	}

	public String getShipToState() {
		return shipToState;
	}

	public void setShipToState(String shipToState) {
		this.shipToState = shipToState;
	}

	public String getShipToZip() {
		return shipToZip;
	}

	public void setShipToZip(String shipToZip) {
		this.shipToZip = shipToZip;
	}

	public String getShipToCountry() {
		return shipToCountry;
	}

	public void setShipToCountry(String shipToCountry) {
		this.shipToCountry = shipToCountry;
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

	public String getLineItemStatus() {
		return lineItemStatus;
	}

	public void setLineItemStatus(String lineItemStatus) {
		this.lineItemStatus = lineItemStatus;
	}

	public Set<POTransactionsAPIResponse> getPoTransactions() {
		return poTransactions;
	}

	public void setPoTransactions(Set<POTransactionsAPIResponse> poTransactions) {
		this.poTransactions = poTransactions;
	}
	
}
