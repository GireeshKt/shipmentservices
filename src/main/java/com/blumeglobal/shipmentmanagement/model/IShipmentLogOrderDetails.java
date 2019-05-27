package com.blumeglobal.shipmentmanagement.model;

import java.util.Date;

public interface IShipmentLogOrderDetails {
	
	String getPurchaseOrderNumber();
	
	Long getQuantity();
	
	Date getPromisedDate();
	
	String getSkuNumber();
	
    String getOrigin();
	
    String getShipToAddress();
    
    String getShipToCity();
	
    String getShipToState();
    
    String getShipToZip();
}
