package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.model.Shipment;
import com.blumeglobal.shipmentmanagement.model.ShipmentWO;
import com.blumeglobal.shipmentmanagement.response.ActiveShipmentResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentWOResponse;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class ShipmentMapper extends ConfigurableMapper{
	
	@Override
	protected void configure(MapperFactory factory) {
		
		factory.classMap(ShipmentWO.class, ShipmentWOResponse.class)
		.byDefault().register();	
		factory.classMap(Shipment.class, ActiveShipmentResponse.class)
		.field("expectedDeliveryDate", "eta")
		.byDefault().register();	
		
		factory.classMap(Shipment.class, ShipmentWOResponse.class)
		.field("workOrderCreatedDate", "workorderDate")
		.field("expectedDeliveryDate", "eta")
		.field("status", "shipmentStatus")
		.field("shipmentId", "woBookingId")
		.field("lastFreeDate", "lastFreeDay")
		.byDefault().register();		
		}

}
