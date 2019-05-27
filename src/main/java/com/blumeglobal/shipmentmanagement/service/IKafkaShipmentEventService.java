package com.blumeglobal.shipmentmanagement.service;

import com.blumeglobal.shipmentmanagement.request.DrayEventRequest;
import com.blumeglobal.shipmentmanagement.response.KafkaShipmentEventResponse;

/**
* The IKafkaShipmentEventService is an interface. To manage the Shipment Event details
*
*/
public interface IKafkaShipmentEventService {

	
	/**
	 * Create shipment event through API
	 * 
	 * @param createEventsRequest This is request which has shipment event details
	 * @return ShipmentEventResponse  Shipment Event Response after save
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	public KafkaShipmentEventResponse kafkaCallShipmentEvent(DrayEventRequest drayEventRequest)throws Exception;
	
	/**
	 * Create shipment ocean event through API
	 * 
	 * @param createEventsRequest This is request which has shipment ocean event details
	 * @return ShipmentEventResponse  Shipment Event Response after save
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	public KafkaShipmentEventResponse kafkaCallShipmentOceanEvent(String createEventsRequest)throws Exception;
	
	}
