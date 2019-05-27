package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;
import java.util.List;

public class ShipmentStopListRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<StopRequest> stopList;

	public ShipmentStopListRequest() {
		
	}

	public List<StopRequest> getStopList() {
		return stopList;
	}

	public void setStopList(List<StopRequest> stopList) {
		this.stopList = stopList;
	}
}
