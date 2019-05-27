package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;

public class ShipmentReferencesRequest  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String shipmentRefType;
	private String shipmentRefValue;
	
	public ShipmentReferencesRequest() {
		
	}

	public String getShipmentRefType() {
		return shipmentRefType;
	}

	public void setShipmentRefType(String shipmentRefType) {
		this.shipmentRefType = shipmentRefType;
	}

	public String getShipmentRefValue() {
		return shipmentRefValue;
	}

	public void setShipmentRefValue(String shipmentRefValue) {
		this.shipmentRefValue = shipmentRefValue;
	}

}
