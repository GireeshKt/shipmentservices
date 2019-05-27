package com.blumeglobal.shipmentmanagement.service;

import com.blumeglobal.shipmentmanagement.request.ShipmentWorkOrderRequest;
import com.blumeglobal.shipmentmanagement.request.common.GenericShipmentRequest;
import com.blumeglobal.shipmentmanagement.response.KafkaShipmentResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentResponse;

public interface IKafkaShipmentService {
	/**
	 * Kafka callShipment through API
	 * 
	 * @param createWorkOrderRequest
	 *            This is request which has shipment details
	 * @return KafkaShipmentResponse Shipment Response after save
	 * @throws Exception
	 *             Handle service exceptions
	 */
	public KafkaShipmentResponse kafkaCallShipmentV1(ShipmentWorkOrderRequest createWorkOrderRequest)throws Exception;

    ShipmentResponse kafkaCallGenericShipment(GenericShipmentRequest shipmentRequest) throws Exception;

    ShipmentResponse kafkaCallWorkorder(GenericShipmentRequest createShipmentRequest);
	
	
}
