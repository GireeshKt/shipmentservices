package com.blumeglobal.shipmentmanagement.kafka.producer;

import java.util.Date;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaGenericShipmentProducer {
             
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaGenericShipmentProducer.class);
	
	@Value("${kafka.topic.genericShipment}")
	private String topicName;
	 
	public void sendShipment(String jsonShipment) {
		
		logger.info("Came to KafkaGenericShipmentProducer, Arrived at: "+new Date()+ " Message: "+jsonShipment);
		kafkaTemplate.send(new ProducerRecord<String, String>(topicName, 
            jsonShipment));
	}   
}
