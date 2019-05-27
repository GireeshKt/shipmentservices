package com.blumeglobal.shipmentmanagement.kafka.producer;

import java.util.Date;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaShipmentProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaShipmentProducer.class);
	
	@Value("${kafka.topic.shipmentV1}")
	private String shipmentV1;
	
	public void sendShipmentsV1(String jsonShipmentV1) {

		logger.info("Came to KafkaShipmentProducer workorder/shipmentV1, Arrived at: "+new Date()+ " Message: "+jsonShipmentV1);
		kafkaTemplate.send(new ProducerRecord<String, String>(shipmentV1, 
				jsonShipmentV1));
		logger.info("Message sent to topic : "+shipmentV1);
	}   

}
