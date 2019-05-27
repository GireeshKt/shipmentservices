package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.model.E2EShipment;
import com.blumeglobal.shipmentmanagement.model.Shipment;
import com.blumeglobal.shipmentmanagement.request.ShipmentRequest;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class ShipmentModelMapper extends ConfigurableMapper {
	
	@Override
	protected void configure(MapperFactory factory) {
		
		factory.classMap(ShipmentRequest.class, Shipment.class)
		.byDefault().register();
		
		factory.classMap(ShipmentRequest.class, E2EShipment.class)
		.field("billTo", "billToName")
		.field("consigneeCountry", "consigneeToCountry")
		.field("consigneeCity", "consineeToCity")
		.field("consigneeState", "consingeeToState")
		.field("createdDate", "createdDateTime")
		.field("destinationZipcode", "destinationZipCode")
		.field("isTempControlled", "isTemperatureController")
		.field("originZipcode", "originZipCode")
		.field("source", "sourceEntry")
		.field("weightUom", "weigthUom")
		.field("expectedDeliveryDate", "expectedDeliveryDate")

		.byDefault().register();
	}
}
