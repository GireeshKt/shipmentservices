package com.blumeglobal.shipmentmanagement.kafka.producer;

import java.util.Date;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaShipmentEventProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private static final Logger logger = LoggerFactory.getLogger(KafkaShipmentEventProducer.class);

	@Value("${kafka.topic.shipmentEvents}")
	private String shipmentDrayEvent;

	@Value("${kafka.topic.shipmentOceanEvents}")
	private String shipmentOceanEvent;

	public void sendShipmentDrayEvents(String jsonShipmentDrayEvents) {

		logger.info("Came to KafkaShipmentEventProducer, dray events, Arrived at: "+new Date()+ " Message: "+jsonShipmentDrayEvents);
		kafkaTemplate.send(new ProducerRecord<String, String>(shipmentDrayEvent, 
				jsonShipmentDrayEvents));
		logger.info("Message sent to topic : "+shipmentDrayEvent);
	}   

	public void sendShipmentOceanEvents(String jsonShipmentOceanEvents) {

		logger.info("Came to KafkaShipmentEventProducer, Ocean events, Arrived at: "+new Date());
		kafkaTemplate.send(new ProducerRecord<String, String>(shipmentOceanEvent, 
				jsonShipmentOceanEvents));
		logger.info("Message sent to topic : "+shipmentOceanEvent);
	}   
}
