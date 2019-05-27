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

import com.blumeglobal.shipmentmanagement.model.mapper.DrayEventMapper;
import com.blumeglobal.shipmentmanagement.request.DrayEventRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentEventRequest;
import com.blumeglobal.shipmentmanagement.response.KafkaShipmentEventResponse;
import com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants;
import com.blumeglobal.shipmentmanagement.service.impl.ShipmentEventServiceImpl;
import com.google.gson.Gson;

/**
 * 
 *
 */
@Controller
public class KafkaShipmentDrayEventListenerConsumer {

	@Autowired
	ShipmentEventServiceImpl shipmentEventCall;
	
	@Autowired
    DrayEventMapper drayEventMapper;

	private static final Logger logger = LoggerFactory.getLogger(KafkaShipmentDrayEventListenerConsumer.class);
	List<String> inputEvents = new ArrayList<String>();   
	Gson gson = new Gson();

	@KafkaListener(topics = "${kafka.topic.shipmentEvents}")
	public void shipmentDrayEventReceiver(String shipmentEvent) throws ParseException {
		logger.info("Inside KafkaShipmentDrayEventListenerConsumer.shipmentDrayEvents topic reciever");
		DrayEventRequest drayEventReq = gson.fromJson(shipmentEvent, DrayEventRequest.class);
		
        ShipmentEventRequest EventsRequest = drayEventMapper.map(drayEventReq, ShipmentEventRequest.class);

        if(drayEventReq.getStopType().equalsIgnoreCase("Pickup")) {
            EventsRequest.setEventCode("X3");
        } else if(drayEventReq.getStopType().equalsIgnoreCase("Delivery")) {
            EventsRequest.setEventCode("X1");
        } else {
            KafkaShipmentEventResponse response = new KafkaShipmentEventResponse();
            response.setMessage(ShipmentConstants.SET_STOPTYPE_MESSAGE);
            return;
        }
        

		try {
			logger.info("Consumer listening to topic-shipmentDrayEvents, Arrived at: "+new Date()+ " Message: "+shipmentEvent);
			shipmentEventCall.createShipmentEvent(EventsRequest);
			logger.info("shipment Dray event data processed to createShipmentEvent api successfully..");
		} catch (Exception e) {
			logger.error("Could not send dray event data to api createShipmentEvent :", e);
		}

	}

}
