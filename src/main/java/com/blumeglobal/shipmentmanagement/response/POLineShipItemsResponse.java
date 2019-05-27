package com.blumeglobal.shipmentmanagement.response;

import java.util.Date;

public class POLineShipItemsResponse implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String skuId;
	private String shipmentNumber;
	private String unitId;
	private String skuDescription;
	private Long quantity;
	private Long committedQuantity;
	private Date requestDate;
	private Date promisedDate;
	private String unitOfMeasure;
	private String destination;
	private String shipToState;
	private String lineItemStatus;
	private double price;
	private String shipToLocationCode;
	private String shipToCompany;
	private String shipToAddress;
	private String shipToZip;
	private String shipToCountry;
	private ActiveShipmentResponse activeShipment;
	
	public POLineShipItemsResponse() {
		
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}

	
	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getSkuDescription() {
		return skuDescription;
	}

	public void setSkuDescription(String skuDescription) {
		this.skuDescription = skuDescription;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getCommittedQuantity() {
		return committedQuantity;
	}

	public void setCommittedQuantity(Long committedQuantity) {
		this.committedQuantity = committedQuantity;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getPromisedDate() {
		return promisedDate;
	}

	public void setPromisedDate(Date promisedDate) {
		this.promisedDate = promisedDate;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getShipToState() {
		return shipToState;
	}

	public void setShipToState(String shipToState) {
		this.shipToState = shipToState;
	}

	public String getLineItemStatus() {
		return lineItemStatus;
	}

	public void setLineItemStatus(String lineItemStatus) {
		this.lineItemStatus = lineItemStatus;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public ActiveShipmentResponse getActiveShipment() {
		return activeShipment;
	}

	public void setActiveShipment(ActiveShipmentResponse activeShipment) {
		this.activeShipment = activeShipment;
	}
	
}
