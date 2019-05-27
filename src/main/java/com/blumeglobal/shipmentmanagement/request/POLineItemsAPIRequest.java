package com.blumeglobal.shipmentmanagement.request;

import java.util.Date;
import java.util.Set;

import io.swagger.annotations.ApiModelProperty;

public class POLineItemsAPIRequest implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes="poItemId is an auto generated number incrementing with every upload", required=true)
	private Long poItemId;
	
	@ApiModelProperty(notes="purchaseOrderId is an auto generated number incrementing with every upload", required=true)
	private POAPIRequest poLineItemsRef;
	
	@ApiModelProperty(notes="total quantity for each line item in particular order", required=true)
	private Long quantity;
	
	@ApiModelProperty(notes="unit used for measurement of particular line item in order", required=true)
	private String unitOfMeasurement;
	
	@ApiModelProperty(notes="Price per unit for the particular item in the purchase order", required=true)
	private double price;
	
	@ApiModelProperty(notes="deleteFlag is Active when purchase order line items is not deleted using delete api , "
			+ "is Inactive when purchase order line items is deleted using delete api for an order", required=true)
	private String deleteFlag;
	
	@ApiModelProperty(notes="SKU ID is an unique identification for SKU", required=true)
	private String skuNumber;
	
	@ApiModelProperty(notes="SKU description", required=true)
	private String skuDescription;
	
	@ApiModelProperty(notes="promised quantity", required=true)
	private Long commitedQuantity;
	
	@ApiModelProperty(notes="Due date to process the purchase order items", required=true)
	private Date dueDate;
	
	@ApiModelProperty(notes="Promised date for Purchase order items completion", required=true)
	private Date promisedDate;
	
	@ApiModelProperty(notes="location code for item to be ship", required=true)
	private String shipToLocationCode;
	
	@ApiModelProperty(notes="company details for item to be ship", required=true)
	private String shipToCompany;
	
	@ApiModelProperty(notes="address for item to be ship", required=true)
	private String shipToAddress;
	
	@ApiModelProperty(notes="name of city for item to be ship", required=true)
	private String shipToCity;
	
	@ApiModelProperty(notes="name of state for item to be ship", required=true)
	private String shipToState;
	
	@ApiModelProperty(notes="city zip code for item to be ship", required=true)
	private String shipToZip;
	
	@ApiModelProperty(notes="country name for item to be ship", required=true)
	private String shipToCountry;
	
	@ApiModelProperty(notes="purchase order line items created date an time through UI", required=true)
	private String createdOn;

	@ApiModelProperty(notes="purchase order line items created username through UI", required=true)
	private String createdBy;
	
	@ApiModelProperty(notes="purchase order line items updated date an time through UI", required=true)
	private String updatedOn;

	@ApiModelProperty(notes="purchase order line items updated username through UI", required=true)
	private String updatedBy;
	
	@ApiModelProperty(notes="purchase order line items status", required=true)
	private String lineItemStatus;
	
	private Set<POTransactionsAPIRequest> poTransactions;
	
	public POLineItemsAPIRequest() {
		
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

	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
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

	public Set<POTransactionsAPIRequest> getPoTransactions() {
		return poTransactions;
	}

	public void setPoTransactions(Set<POTransactionsAPIRequest> poTransactions) {
		this.poTransactions = poTransactions;
	}

}
