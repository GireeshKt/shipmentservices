package com.blumeglobal.shipmentmanagement.kafka.consumer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

import com.blumeglobal.shipmentmanagement.request.ShipmentWorkOrderRequest;
import com.blumeglobal.shipmentmanagement.service.impl.ShipmentServiceImpl;
import com.google.gson.Gson;

/**
 * 
 *
 */
@Controller
public class KafkaShipmentListenerConsumer {
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaShipmentListenerConsumer.class);
	List<String> inputEvents = new ArrayList<String>();   
	Gson gson = new Gson();
	
	@Autowired
	ShipmentServiceImpl shipmentCall;
	
	@KafkaListener(topics = "${kafka.topic.shipmentV1}")
	public void ShipmentV1Receiver(String shipmentV1) throws ParseException {
		logger.info("Inside KafkaShipmentManageConsumer.shipmentV1 topic reciever");
		ShipmentWorkOrderRequest shipmentReq = gson.fromJson(shipmentV1, ShipmentWorkOrderRequest.class);
		try {
			logger.info("Consumer listening to topic-shipmentV1, Arrived at: "+new Date()+ " Message : "+shipmentV1);
			shipmentCall.createShipmentWO(shipmentReq);
			logger.info("shipment data processed to createShipmentWO api successfully..");
		} catch (Exception e) {
			logger.error("Could not send shipment data to api createShipmentWO :"+e);
		}
	}
	
	/*@KafkaListener(topics = "${kafka.topic.shipmentV2}")
	public void ShipmentV2Receiver(String shipmentV2) throws ParseException {
		logger.info("Inside KafkaShipmentManageConsumer.shipmentV2 topic reciever");
		//ShipmentWorkOrderRequest shipmentReq = gson.fromJson(shipmentV2, ShipmentWorkOrderRequest.class);
		try {
			logger.info("Consumer listening to topic-shipmentV2, Arrived at: "+new Date()+ " Message : "+shipmentV2);
			//shipmentCall.createShipmentWO(shipmentReq);
			logger.info("shipment data processed to createShipment api successfully..");
		} catch (Exception e) {
			logger.error("Could not send shipment data to api createShipment :"+e);
		}
	}*/

}
