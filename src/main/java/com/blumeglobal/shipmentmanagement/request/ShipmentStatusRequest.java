package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;
import java.util.List;

public class ShipmentStatusRequest  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String status;
	private List<ShipmentReferencesRequest> shipmentReferences;
	
	public ShipmentStatusRequest() {
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ShipmentReferencesRequest> getShipmentReferences() {
		return shipmentReferences;
	}

	public void setShipmentReferences(List<ShipmentReferencesRequest> shipmentReferences) {
		this.shipmentReferences = shipmentReferences;
	}
}
