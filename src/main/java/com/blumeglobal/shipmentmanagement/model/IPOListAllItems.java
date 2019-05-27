package com.blumeglobal.shipmentmanagement.model;

import java.sql.Timestamp;
import java.util.Date;

public interface IPOListAllItems {
	
	public String getPurchaseOrderNumber();
	public Long getPurchaseOrderId();
	public String getLogisticProvider();
	public String getOrigin();
	public Date getPoReceiveDate();
	public Date getPoPromiseDate();
	public String getPoVendorName();
	public String getPoVendorDuns();
	public String getPoVendorAddress();
	public String getPoVendorCity(); 
	public String getPoVendorState();
	public String getPoVendorZipcode();
	public String getPoVendorCountry();
	public String getPoBuyerName();
	public String getPoBuyerDuns();
	public String getPoBillingAddress();
	public String getPoReferenceId();
	public String getPoExpenditureCode();
	public String getPoRequistionedBy();
	public Long getPoTax();
	public String getPoFreightHandling();
	public String getPoStatus();
	public Long getPoLeadTime();
	public Timestamp getPoDate();
	public String getPoNotes();
	public String getPoDestination();
	
	public Long getPoItemId();
	public String getSkuNumber();
	public String getShipToCity();
	public String getShipToState();
	public Date getPromisedDate() ;
	public String getLineItemStatus();
	public Long getQuantity();
	public String getSkuDescription();
	public Long getCommittedQuantity();
	public Date getDueDate();
	public String getUnitOfMeasurement();
	public double getPrice();
	public String getShipToLocationCode();
	public String getShipToCompany();
	public String getShipToAddress();
	public String getShipToZip();
	public String getShipToCountry();
}
