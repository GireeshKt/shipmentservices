package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.model.Shipment;
import com.blumeglobal.shipmentmanagement.model.ShipmentLegDetails;
import com.blumeglobal.shipmentmanagement.response.ShipmentLegDetailsResponse;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
/**
 * The ShipmentLegDetailsMapper is used to handle the Object mapping for ShipmentLegDetails model
 *
 */
@Component
public class ShipmentLegDetailsMapper extends ConfigurableMapper {
	@Override
	protected void configure(MapperFactory factory) {
		
		factory.classMap(ShipmentLegDetails.class, ShipmentLegDetailsResponse.class)
		.byDefault().register();	
		
		factory.classMap(Shipment.class, ShipmentLegDetailsResponse.class)
		.field("referenceId", "workOrderId")
		.field("expectedDeliveryDate", "eta")
		.field("status", "woStatus")
		.field("destinationZipcode", "destinationZipCode")
		.byDefault().register();	
	
		}
}
