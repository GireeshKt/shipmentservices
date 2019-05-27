package com.blumeglobal.shipmentmanagement.kafka.producer;

import java.util.Date;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaWorkorderProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaWorkorderProducer.class);
	
	@Value("${kafka.topic.tenderShipment}")
	private String topicName;
	 
	public void sendShipment(String jsonWorkOrder) {
		
		logger.info("Came to KafkaWorkorderProducer, Arrived at: "+new Date()+ " Message: "+jsonWorkOrder);
		kafkaTemplate.send(new ProducerRecord<String, String>(topicName, 
		        jsonWorkOrder));
	}   
}
