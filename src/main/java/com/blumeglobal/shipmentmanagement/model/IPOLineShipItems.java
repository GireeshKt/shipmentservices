package com.blumeglobal.shipmentmanagement.model;

import java.util.Date;

public interface IPOLineShipItems {

	public String getSkuId();
	public String getShipmentNumber();
	public String getUnitId();
	public String getSkuDescription();
	public Long getQuantity();
	public Long getCommittedQuantity();
	public Date getRequestDate() ;
	public Date getPromisedDate();
	public String getUnitOfMeasure();
	public String getDestination();
	public String getShipToState();
	public String getLineItemStatus();
	public double getPrice();
	public String getShipToLocationCode();
	public String getShipToCompany();
	public String getShipToAddress();
	public String getShipToZip();
	public String getShipToCountry();
	
}
