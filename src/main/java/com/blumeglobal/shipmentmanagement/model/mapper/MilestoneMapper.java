package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.model.Milestone;
import com.blumeglobal.shipmentmanagement.request.MilestoneRequest;
import com.blumeglobal.shipmentmanagement.response.MilestoneResponse;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;


@Component
public class MilestoneMapper extends ConfigurableMapper {
	
	@Override
	protected void configure(MapperFactory factory) {
		
		factory.classMap(Milestone.class, MilestoneResponse.class)
		.byDefault().register();
		
		factory.classMap(MilestoneRequest.class, Milestone.class)
		.byDefault().register();
	}
}
