package com.blumeglobal.shipmentmanagement.kafka.producer;

/*import java.util.Date;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;*/


public class ShipmentEventExceptionProducer {

	/*@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private static final Logger logger = LogManager.getLogger(ShipmentEventExceptionProducer.class);
	*/
	/*@Value("${kafka.topic.shipmentException}")
	  private String topicName;
	 
	public void send(String jsonShipmentEvents) {
		logger.info("Came to ShipmentEventProducer- exception publisher, Arrived at: "+new Date()+" Message: "+jsonShipmentEvents);
	  kafkaTemplate.send(new ProducerRecord<String, String>(topicName, jsonShipmentEvents));
	  System.out.println("exception came");
	  logger.info("Message sent to topic : "+topicName);
	}   */
}
