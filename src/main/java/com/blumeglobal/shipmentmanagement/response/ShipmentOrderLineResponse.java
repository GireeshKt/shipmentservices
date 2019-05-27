package com.blumeglobal.shipmentmanagement.response;

import java.util.Date;

public class ShipmentOrderLineResponse {
	private String poNumber;
	private String skuId;
	private String shipToCountry;
	private String shipToState;
	private Date promisedDate;
	private Long promisedQuantity;
	private String quantityUom;
	private Long shipmentCount;
	
	public ShipmentOrderLineResponse(String poNumber, String skuId, String shipToCountry, String shipToState,
			Date promisedDate, Long promisedQuantity, String quantityUom, Long shipmentCount) {
		super();
		this.poNumber = poNumber;
		this.skuId = skuId;
		this.shipToCountry = shipToCountry;
		this.shipToState = shipToState;
		this.promisedDate = promisedDate;
		this.promisedQuantity = promisedQuantity;
		this.quantityUom = quantityUom;
		this.shipmentCount = shipmentCount;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public Date getPromisedDate() {
		return promisedDate;
	}
	public void setPromisedDate(Date promisedDate) {
		this.promisedDate = promisedDate;
	}
	public Long getPromisedQuantity() {
		return promisedQuantity;
	}
	public void setPromisedQuantity(Long promisedQuantity) {
		this.promisedQuantity = promisedQuantity;
	}
	public Long getShipmentCount() {
		return shipmentCount;
	}
	public void setShipmentCount(Long shipmentCount) {
		this.shipmentCount = shipmentCount;
	}
	public String getShipToCountry() {
		return shipToCountry;
	}
	public void setShipToCountry(String shipToCountry) {
		this.shipToCountry = shipToCountry;
	}
	public String getShipToState() {
		return shipToState;
	}
	public void setShipToState(String shipToState) {
		this.shipToState = shipToState;
	}
	public String getQuantityUom() {
		return quantityUom;
	}
	public void setQuantityUom(String quantityUom) {
		this.quantityUom = quantityUom;
	}
	
	
}
