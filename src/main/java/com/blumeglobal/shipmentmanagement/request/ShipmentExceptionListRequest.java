package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;
import java.util.List;



public class ShipmentExceptionListRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	List<ShipmentExceptionRequest> shipmentExceptions;

    public List<ShipmentExceptionRequest> getShipmentExceptions() {
        return shipmentExceptions;
    }

    public void setShipmentExceptions(List<ShipmentExceptionRequest> shipmentExceptions) {
        this.shipmentExceptions = shipmentExceptions;
    }
	
}