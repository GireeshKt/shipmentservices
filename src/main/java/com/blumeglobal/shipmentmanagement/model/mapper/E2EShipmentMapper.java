package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.model.E2EShipment;
import com.blumeglobal.shipmentmanagement.response.E2EShipmentDetailsResponse;
import com.blumeglobal.shipmentmanagement.response.E2EShipmentResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentWOResponse;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class E2EShipmentMapper extends ConfigurableMapper{
	
	@Override
	protected void configure(MapperFactory factory) {
		factory.classMap(E2EShipment.class, E2EShipmentResponse.class)
		.field("wayBillNumber", "bookingNumber")
		.field("shipmentNumber", "shipmentReferenceNumber")
		.byDefault().register();
		
		factory.classMap(E2EShipment.class, ShipmentWOResponse.class)
		.field("wayBillNumber", "billOfLadingNumber")
		.field("shipmentNumber", "shipmentReferenceNumber")
		.field("expectedDeliveryDate", "eta")
		.field("status", "shipmentStatus")
		.field("shipmentId", "woBookingId")
		.byDefault().register();

		factory.classMap(E2EShipment.class, E2EShipmentDetailsResponse.class)
		.field("shipmentId", "e2eshipmentId")
		.field("shipmentNumber", "shipmentReferenceNumber")
		.field("wayBillNumber", "billOfLadingNumber")
		.byDefault().register();
		
		}

}
