package com.blumeglobal.shipmentmanagement.request;

import java.util.Date;

public class E2EShipmentPOStatusRequest implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String poNumber;
	private Long poLineItemId;
	private String status;
	private String unitId;
	private String shipmentNumber;
	private String origin;
	private String destination;
	private String itemNumber;
	private String date;
	
	public E2EShipmentPOStatusRequest() {
		
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public Long getPoLineItemId() {
		return poLineItemId;
	}

	public void setPoLineItemId(Long poLineItemId) {
		this.poLineItemId = poLineItemId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
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

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
