package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.model.ShipmentDetails;

import com.blumeglobal.shipmentmanagement.response.ShipmentDetailsResponse;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class ShipmentDetailsMapper extends ConfigurableMapper{

	@Override
	protected void configure(MapperFactory factory)  {
		
		factory.classMap(ShipmentDetails.class, ShipmentDetailsResponse.class)
		.byDefault().register();	
		}

	
}
