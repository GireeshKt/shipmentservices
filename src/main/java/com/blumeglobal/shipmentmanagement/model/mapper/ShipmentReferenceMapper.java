package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.model.ShipmentReferences;
import com.blumeglobal.shipmentmanagement.request.ShipmentReferenceRequest;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class ShipmentReferenceMapper extends ConfigurableMapper {
	@Override
	protected void configure(MapperFactory factory) {
		
		factory.classMap(ShipmentReferenceRequest.class, ShipmentReferences.class)
		.byDefault().register();
			
	}
}
