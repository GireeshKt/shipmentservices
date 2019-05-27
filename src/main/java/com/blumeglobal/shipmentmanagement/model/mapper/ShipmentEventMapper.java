package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.converter.EventRequestConverter;
import com.blumeglobal.shipmentmanagement.dm.ShipmentEvent;
import com.blumeglobal.shipmentmanagement.request.ShipmentEventRequest;
import com.blumeglobal.shipmentmanagement.response.ShipmentEventResponse;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

/**
 * The DashboardMapper is used to handle the Object mapping for ShipmentEvent model
 *
 */
@Component
public class ShipmentEventMapper extends ConfigurableMapper {
	@Override
	protected void configure(MapperFactory factory) {
		
		factory.classMap(ShipmentEventRequest.class, ShipmentEvent.class)
		.exclude("eventTime")
		.byDefault()
		.customize(new EventRequestConverter("MM-dd-yyyy HH:mm:ss"))
		.register();
		
		factory.classMap(ShipmentEvent.class, ShipmentEventResponse.class)
		.field("shipmentReferenceNumber", "shipmentNumber")
		.field("onHandNumber", "onHand")
        .field("railBillingNumber", "wayBillNumber")
        .field("onHandNumber", "onHand")
		.byDefault()
		.register();	
		}
}
