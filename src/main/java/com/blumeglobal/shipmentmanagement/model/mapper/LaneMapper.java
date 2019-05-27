package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.model.Lane;
import com.blumeglobal.shipmentmanagement.response.LaneLocationResponse;
import com.blumeglobal.shipmentmanagement.response.LaneResponse;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class LaneMapper extends ConfigurableMapper{
	
	@Override
	protected void configure(MapperFactory factory) {
		
		factory.classMap(Lane.class, LaneLocationResponse.class)
		.byDefault().register();
		factory.classMap(Lane.class, LaneResponse.class)
		.byDefault().register();
	}

}
