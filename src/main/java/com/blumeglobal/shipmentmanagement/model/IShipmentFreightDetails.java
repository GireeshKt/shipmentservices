package com.blumeglobal.shipmentmanagement.model;

import java.util.Date;

public interface IShipmentFreightDetails {
	public String getFreightDescription() ;
	public Long getShipmentId();
	public String getBookingNumber();
	public String getShipmentReferenceNumber();
	public String getStatus();
	public String getConsignee();
	public String getOrigin();
	public String getOriginAddress1();
	public String getOriginAddress2();
	public String getOriginCity();
	public String getOriginCountry();
	public String getOriginState();
	public String getOriginZipCode();
	public String getDestination();
	public String getDestinationAddress1();
	public String getDestinationAddress2();
	public String getDestinationCity();
	public String getDestinationState();
	public String getDestinationZipCode();
	public String getDestinationCountry();
	public String getPurchaseOrderNumber();
	public String getSkuNumber();
	public Date getRequestedDate();
	public Date getPromisedDate();
	public String getLogisticProvider();
	public String getPoBuyerName();
	public String getAsnNumber();
	public String getUnitId();
	public Long getRequestedQuantity();
	public Long getPromisedQuantity();
	public String getOrderStatus();
	public Long getPoItemId();
}
