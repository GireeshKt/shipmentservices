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

import com.blumeglobal.shipmentmanagement.request.OceanLocationDetails;
import com.blumeglobal.shipmentmanagement.request.ShipmentEventRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentOceanEventRequest;
import com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants;
import com.blumeglobal.shipmentmanagement.service.impl.ShipmentEventServiceImpl;
import com.blumeglobal.shipmentmanagement.utils.DateUtils;
import com.google.gson.Gson;

/**
 * 
 *
 */
@Controller
public class KafkaShipmentOceanEventListenerConsumer {

	@Autowired
	ShipmentEventServiceImpl shipmentEventCall;
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaShipmentOceanEventListenerConsumer.class);
	List<String> inputEvents = new ArrayList<String>();   
	Gson gson = new Gson();

	@KafkaListener(topics = "${kafka.topic.shipmentOceanEvents}")
	public void shipmentOceanEventReceiver(String shipmentOceanEvent) throws ParseException {
		logger.info("Inside KafkaShipmentOceanEventListenerConsumer.shipmentOceanEvents topic reciever, Arrived at: "+new Date());
		try {
			ShipmentOceanEventRequest mapShipmentOceanEvents = gson.fromJson(shipmentOceanEvent,ShipmentOceanEventRequest.class);
			String mappedOceanEvents = kafkaMapOceanEvents(mapShipmentOceanEvents);
			if(mappedOceanEvents.equalsIgnoreCase(ShipmentConstants.SUCCESS)) {
				logger.info("shipment Ocean event data processed to createShipmentEvent api successfully..");
			}
			logger.error("Could not send ocean event data to api createShipmentEvent..");
			
		} catch (Exception e) {
			logger.error("Could not send ocean event data to api createShipmentEvent :"+e);
		}

	}

	public String kafkaMapOceanEvents(ShipmentOceanEventRequest jsonShipmentEvents) throws Exception {
		try {
			logger.info("Entering into KafkaShipmentOceanEventListenerConsumer.kafkaMapOceanEvents");

			//forming json from input events for kafka feed
			
			ShipmentEventRequest createOceanRequest = new ShipmentEventRequest();

			if(jsonShipmentEvents.getEvent().getRef1_type().equalsIgnoreCase("BOL") ||jsonShipmentEvents.getEvent().getRef2_type().equalsIgnoreCase("VoyageNumber") || jsonShipmentEvents.getEvent().getRef3_type().equalsIgnoreCase("EQUIPMENTID")) {
				createOceanRequest.setBillOfLadingNumber(jsonShipmentEvents.getEvent().getReference1());
				createOceanRequest.setVoyageNumber(jsonShipmentEvents.getEvent().getReference2());
				createOceanRequest.setUnitId(jsonShipmentEvents.getEvent().getReference3());
			}
			createOceanRequest.setBookingNumber(jsonShipmentEvents.getEvent().getContent().getBookingNumber());
			createOceanRequest.setCarrierCode(jsonShipmentEvents.getEvent().getContent().getSsSCAC());
			createOceanRequest.setEventCode(jsonShipmentEvents.getEvent().getContent().getShipmentStatusCode());
			createOceanRequest.setReportSource(ShipmentConstants.REPORT_SOURCE);
			if(jsonShipmentEvents.getEvent().getCategory().equalsIgnoreCase("I")) {
				createOceanRequest.setEventName(ShipmentConstants.I);
			}else if(jsonShipmentEvents.getEvent().getCategory().equalsIgnoreCase("AE")) {
				createOceanRequest.setEventName(ShipmentConstants.AE);
			}else if(jsonShipmentEvents.getEvent().getCategory().equalsIgnoreCase("OA")) {
				createOceanRequest.setEventName(ShipmentConstants.OA);
			}else if(jsonShipmentEvents.getEvent().getCategory().equalsIgnoreCase("AP")) {
				createOceanRequest.setEventName(ShipmentConstants.AP);
			}else if(jsonShipmentEvents.getEvent().getCategory().equalsIgnoreCase("AR")) {
				createOceanRequest.setEventName(ShipmentConstants.AR);
			}else if(jsonShipmentEvents.getEvent().getCategory().equalsIgnoreCase("AL")) {
				createOceanRequest.setEventName(ShipmentConstants.AL);
			}else if(jsonShipmentEvents.getEvent().getCategory().equalsIgnoreCase("AV")) {
				createOceanRequest.setEventName(ShipmentConstants.AV);
			}else if(jsonShipmentEvents.getEvent().getCategory().equalsIgnoreCase("AF")) {
				createOceanRequest.setEventName(ShipmentConstants.AF);
			}else if(jsonShipmentEvents.getEvent().getCategory().equalsIgnoreCase("CR")) {
				createOceanRequest.setEventName(ShipmentConstants.CR);
			}else if(jsonShipmentEvents.getEvent().getCategory().equalsIgnoreCase("CT")) {
				createOceanRequest.setEventName(ShipmentConstants.CT);
			}else if(jsonShipmentEvents.getEvent().getCategory().equalsIgnoreCase("EE")) {
				createOceanRequest.setEventName(ShipmentConstants.EE);
			}else if(jsonShipmentEvents.getEvent().getCategory().equalsIgnoreCase("RD")) {
				createOceanRequest.setEventName(ShipmentConstants.RD);
			}else if(jsonShipmentEvents.getEvent().getCategory().equalsIgnoreCase("RL")) {
				createOceanRequest.setEventName(ShipmentConstants.RL);
			}else if(jsonShipmentEvents.getEvent().getCategory().equalsIgnoreCase("VD")) {
				createOceanRequest.setEventName(ShipmentConstants.VD);
			}else if(jsonShipmentEvents.getEvent().getCategory().equalsIgnoreCase("VA")) {
				createOceanRequest.setEventName(ShipmentConstants.VA);
			}else if(jsonShipmentEvents.getEvent().getCategory().equalsIgnoreCase("UV")) {
				createOceanRequest.setEventName(ShipmentConstants.UV);
			}else if(jsonShipmentEvents.getEvent().getCategory().equalsIgnoreCase("UR")) {
				createOceanRequest.setEventName(ShipmentConstants.UR);
			}
			createOceanRequest.setEventTime(DateUtils.convertToString(jsonShipmentEvents.getEvent().getEventTimestamp()));
			createOceanRequest.setUnitState(jsonShipmentEvents.getEvent().getContent().getEquipmentStatusCode());
			createOceanRequest.setUnitTypeCode(jsonShipmentEvents.getEvent().getContent().getEquipmentType());
			createOceanRequest.setVessel(jsonShipmentEvents.getEvent().getContent().getVesselName());
			for(OceanLocationDetails oceanLoc: jsonShipmentEvents.getEvent().getContent().getLocation()) {
				if(oceanLoc.getType().equalsIgnoreCase("activityPlace")) {
					createOceanRequest.setCity(oceanLoc.getCity());
					createOceanRequest.setCountry(oceanLoc.getCountry());
					createOceanRequest.setLatitude(oceanLoc.getLatitude());
					createOceanRequest.setLongitude(oceanLoc.getLongitude());
					//createOceanRequest.setLocation(oceanLoc);
					createOceanRequest.setPostalCode(oceanLoc.getPostalCode());
					createOceanRequest.setState(oceanLoc.getState());
					createOceanRequest.setCodeType(oceanLoc.getCodeType());
					createOceanRequest.setLocation(oceanLoc.getUuid());
				}
				if(oceanLoc.getType().equalsIgnoreCase("deliveryPlace")) {
					createOceanRequest.setDestinationCity(oceanLoc.getCity());
					//createOceanRequest.setDestinationSPLC(oceanLoc);
					createOceanRequest.setDestinationState(oceanLoc.getState());
				}
			}

			String jsonShipmentOceanEvents = gson.toJson(createOceanRequest);
			ShipmentEventRequest shipmentOceanEventReq = gson.fromJson(jsonShipmentOceanEvents, ShipmentEventRequest.class);
			
			logger.info("Consumer.. Mapped Ocean Events, Message : "+shipmentOceanEventReq);
			shipmentEventCall.createShipmentEvent(shipmentOceanEventReq);
			
			return ShipmentConstants.SUCCESS;
		} catch (Exception ex) {
			logger.error(
					"Exception inside KafkaShipmentEventListenerConsumer.kafkaMapOceanEvents.Catch_Block:" + ex.getMessage());
			throw ex;
		}

	}

}
