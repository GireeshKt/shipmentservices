package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.model.AdvanceShipmentNotice;
import com.blumeglobal.shipmentmanagement.response.E2EShipmentAPIResponse;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class AdvanceShipmentNoticeMapper extends ConfigurableMapper{

	@Override
	protected void configure(MapperFactory factory) {
		factory.classMap(AdvanceShipmentNotice.class, E2EShipmentAPIResponse.class)
		.byDefault().register();
	}
}