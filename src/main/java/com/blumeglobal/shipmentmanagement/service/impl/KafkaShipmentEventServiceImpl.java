package com.blumeglobal.shipmentmanagement.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blumeglobal.shipmentmanagement.kafka.producer.KafkaShipmentEventProducer;
import com.blumeglobal.shipmentmanagement.model.mapper.DrayEventMapper;
import com.blumeglobal.shipmentmanagement.request.DrayEventRequest;
import com.blumeglobal.shipmentmanagement.response.KafkaShipmentEventResponse;
import com.blumeglobal.shipmentmanagement.service.IKafkaShipmentEventService;
import com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants;
import com.google.gson.Gson;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

/**
 * The KafkaShipmentEventServiceImpl implements to manage the shipment event.
 *
 */
@Service
@REZ1Logger
@REZ1PerformanceLogger
public class KafkaShipmentEventServiceImpl implements IKafkaShipmentEventService {

	@Autowired
	private KafkaShipmentEventProducer sendEvent;
	
	@Autowired
	DrayEventMapper drayEventMapper;

	private static final Logger logger = LogManager.getLogger(KafkaShipmentEventServiceImpl.class);


	/**
	 * Create shipment event through API
	 * 
	 * @param createEventsRequest
	 *            This is request which has shipment event details
	 * @return ShipmentEventResponse Shipment Event Response after save
	 * @throws Exception
	 *             Handle service exceptions
	 */
	@Override
	public KafkaShipmentEventResponse kafkaCallShipmentEvent(DrayEventRequest drayEventRequest) throws Exception {
		try {
			
			logger.info("Entering into KafkaShipmentEventServiceImpl.kafkaCallShipmentEvent");

			//forming json from input events for kafka feed
			Gson gson = new Gson();
			String jsonShipmentEvents = gson.toJson(drayEventRequest);
			sendEvent.sendShipmentDrayEvents(jsonShipmentEvents);
			KafkaShipmentEventResponse resp = new KafkaShipmentEventResponse();
			resp.setMessage(ShipmentConstants.SENT_JSON_SHIPMENT_EVENT_DATA_SUCCESS_MESSAGE);

			return resp;
		} catch (Exception ex) {
			logger.error(
					"Exception inside KafkaShipmentEventServiceImpl.kafkaCallShipmentEvent.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * Create shipment event through API
	 * 
	 * @param createEventsRequest
	 *            This is request which has shipment event details
	 * @return ShipmentEventResponse Shipment Event Response after save
	 * @throws Exception
	 *             Handle service exceptions
	 */
	@Override
	public KafkaShipmentEventResponse kafkaCallShipmentOceanEvent(String createEventsRequest) throws Exception {
		try {
			logger.info("Entering into KafkaShipmentEventServiceImpl.kafkaCallShipmentOceanEvent");
			
			sendEvent.sendShipmentOceanEvents(createEventsRequest);
			KafkaShipmentEventResponse resp = new KafkaShipmentEventResponse();
			resp.setMessage(ShipmentConstants.SENT_JSON_SHIPMENT_EVENT_DATA_SUCCESS_MESSAGE);

			return resp;
		} catch (Exception ex) {
			logger.error(
					"Exception inside KafkaShipmentEventServiceImpl.kafkaCallShipmentEvent.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}



}
