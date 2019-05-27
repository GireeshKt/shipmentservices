package com.blumeglobal.shipmentmanagement.service;

import com.blumeglobal.shipmentmanagement.model.ShipmentException;

public interface IKafkaShipmentExceptionService {
	
	public void createShipmentException(ShipmentException shipmentException);
}
