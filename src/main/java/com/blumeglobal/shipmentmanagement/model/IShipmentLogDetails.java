package com.blumeglobal.shipmentmanagement.model;

import java.sql.Timestamp;
import java.util.Date;

public interface IShipmentLogDetails {

	public String getShipmentNumber();
	public String getUnitId();
	public Long getItemQuantity();
	public Date getPromisedDate();
	public String getBillToName();
	public String getTransportationMethod();
	public String getStatus();
	public String getShippingCompany();
	public String getWayBillNumber();
	public String getAsnNumber();
	public String getTrackingNumber();
	public Timestamp getExpectedPickupDate();
	public String getOrigin();
	public String getOriginAddress1();
	public String getOriginAddress2();
	public String getOriginCity();
	public String getOriginState();
	public String getOriginZipCode();
	public String getOriginCountry();
	public String getDestination();
	public String getDestinationAddress1();
	public String getDestinationAddress2();
	public String getDestinationCity();
	public String getDestinationState();
	public String getDestinationZipCode();
	public String getDestinationCountry();
	public Long getTotalWeight();
	public Long getShipmentNumberPart();
	public Long getShipmentNumberItems();
	public Long getNumberOfLineItems();
	public String getPoNumber();
	public String getItemNumber();
	public String getItemDescription();
	public String getItemQuantityUOM();
	public Long getPrice();
	public String getContentDescription();
	public String getWeightQualifier();
}
