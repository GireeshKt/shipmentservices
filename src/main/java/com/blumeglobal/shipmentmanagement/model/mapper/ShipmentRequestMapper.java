package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.request.ShipmentRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentWorkOrderRequest;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class ShipmentRequestMapper extends ConfigurableMapper {
	
	@Override
	protected void configure(MapperFactory factory) {
		
		factory.classMap(ShipmentWorkOrderRequest.class, ShipmentRequest.class)
		.field("workorderid", "referenceId")
		.field("exportcutoffdate", "exportCutOffDate")
		.field("dateCreated", "createdDate")
		.field("destination_name", "destination")
		.field("destination_address1", "destinationAddress1")
		.field("destination_address2", "destinationAddress2")
		.field("destination_city", "destinationCity")
		.field("destination_country", "destinationCountry")
		.field("destination_state", "destinationState")
		.field("destination_postalcode", "destinationZipcode")
		.field("origin_name", "origin")
		.field("origin_address1", "originAddress1")
		.field("origin_address2", "originAddress2")
		.field("origin_city", "originCity")
		.field("origin_country", "originCountry")
		.field("origin_state", "originState")
		.field("origin_postalcode", "originZipcode")
		.field("eta", "expectedDeliveryDate")
		.field("eta", "scheduleDeliveryDate")
		.field("dateCreated", "workOrderCreatedDate")
		.field("originatorCode","organizationCode")
		.field("originatorOrderReference","legMode")
		.field("shipper","consignee")
		.field("carrierCode","carrier")
		.field("statusDescription", "status")
		.field("workorderdate", "schedulePickupDate")
		.byDefault().register();	
	}
}
