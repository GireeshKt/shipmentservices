package com.blumeglobal.shipmentmanagement.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.blumeglobal.shipmentmanagement.kafka.producer.KafkaGenericShipmentProducer;
import com.blumeglobal.shipmentmanagement.kafka.producer.KafkaShipmentEventProducer;
import com.blumeglobal.shipmentmanagement.kafka.producer.KafkaShipmentProducer;
import com.blumeglobal.shipmentmanagement.kafka.producer.KafkaWorkorderProducer;


@Configuration
public class ShipmentManagementProducerConfig {

	@Value("${kafka.bootstrap-servers}")
	  private String bootstrapServers;
	
	@Bean
	  public Map<String, Object> producerConfigs() {
	    Map<String, Object> props = new HashMap<>();
	    //Set acknowledgement for producer requests.      
	    props.put(ProducerConfig.ACKS_CONFIG, "all");
	    //props.put(ProducerConfig.RETRIES_CONFIG, 3);
	    // list of host:port pairs used for establishing the initial connections to the Kakfa cluster
	    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
	        bootstrapServers);
	    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
	        StringSerializer.class);
	    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
	        StringSerializer.class);

	    return props;
	  }

	  @Bean
	  public ProducerFactory<String, String> producerFactory() {
	    return new DefaultKafkaProducerFactory<>(producerConfigs());
	  }

	  @Bean
	  public KafkaTemplate<String, String> kafkaTemplate() {
	    return new KafkaTemplate<>(producerFactory());
	  }

	  @Bean
	  public KafkaShipmentEventProducer sendEvent() {
	    return new KafkaShipmentEventProducer();
	  }
	  
	  @Bean
	  public KafkaShipmentProducer sendShipment() {
		    return new KafkaShipmentProducer();
		  }
      @Bean
      public KafkaWorkorderProducer sendWorkorder() {
            return new KafkaWorkorderProducer();
          }
      @Bean
      public KafkaGenericShipmentProducer sendGenericShipments() {
            return new KafkaGenericShipmentProducer();
          }
	
}
