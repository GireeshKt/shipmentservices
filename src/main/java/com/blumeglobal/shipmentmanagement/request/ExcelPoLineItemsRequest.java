package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;

/**
 * The ExcelShipmentSuccessRowsRequest is used to handle the request of Valid Excel Rows for PO Excel Uploads operation
 *
 */
public class ExcelPoLineItemsRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String skuNumber;
	
	private String skuDescription;
	
	private String quantity;
	
	private String unitOfMeasurement;
	
	private String price;
	
	private String commitedQuantity;
	
	private String dueDate;
	
	private String promisedDate;
	
	private String shipToLocationCode;
	
	private String shipToCompany;
	
	private String shipToAddress;
	
	private String shipToCity;
	
	private String shipToState;
	
	private String shipToZip;
	
	private String shipToCountry;
	
	
	public ExcelPoLineItemsRequest()  {

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

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCommitedQuantity() {
		return commitedQuantity;
	}

	public void setCommitedQuantity(String commitedQuantity) {
		this.commitedQuantity = commitedQuantity;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getPromisedDate() {
		return promisedDate;
	}

	public void setPromisedDate(String promisedDate) {
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

}