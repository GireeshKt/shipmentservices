package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;

public class ShipmentSummaryResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long totalExceptionCount;
	private Long lastdayCompletedE2EShipmentCount;
	private Long activeE2EShipmentCount;
	
	public ShipmentSummaryResponse() {

	}
	
	public Long getTotalExceptionCount() {
		return totalExceptionCount;
	}
	
	public void setTotalExceptionCount(Long totalExceptionCount) {
		this.totalExceptionCount = totalExceptionCount;
	}
	
	public Long getLastdayCompletedE2EShipmentCount() {
		return lastdayCompletedE2EShipmentCount;
	}

	public void setLastdayCompletedShipmentCount(Long lastdayCompletedE2EShipmentCount) {
		this.lastdayCompletedE2EShipmentCount = lastdayCompletedE2EShipmentCount;
	}
	
	public Long getActiveE2EShipmentCount() {
		return activeE2EShipmentCount;
	}

	public void setActiveE2EShipmentCount(Long activeE2EShipmentCount) {
		this.activeE2EShipmentCount = activeE2EShipmentCount;
	}

}
 