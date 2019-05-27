package com.blumeglobal.shipmentmanagement.enums;

public enum ActiveShipmentStatusColor {
	///red - exception and leadtime + current > expected eta
	///yellow - exception and current + leadtime <= expected eta
	///green - no exception	
	GREEN, YELLOW, RED
}
