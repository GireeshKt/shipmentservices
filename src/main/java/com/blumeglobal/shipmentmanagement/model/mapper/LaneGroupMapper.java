package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.model.LaneGroups;
import com.blumeglobal.shipmentmanagement.response.LaneGroupsResponse;
import com.blumeglobal.shipmentmanagement.response.LaneLocationResponse;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class LaneGroupMapper extends ConfigurableMapper{
	
	@Override
	protected void configure(MapperFactory factory) {
		
		factory.classMap(LaneGroups.class, LaneLocationResponse.class)
		.byDefault().register();
		factory.classMap(LaneGroups.class, LaneGroupsResponse.class)
		.byDefault().register();
		
	}

}