package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.dm.WoStop;
import com.blumeglobal.shipmentmanagement.model.Stop;
import com.blumeglobal.shipmentmanagement.request.ShipmentFacilityRequest;
import com.blumeglobal.shipmentmanagement.request.StopMapperRequest;
import com.blumeglobal.shipmentmanagement.request.StopRequest;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class StopMapper extends ConfigurableMapper{
	
	@Override
	protected void configure(MapperFactory factory) {
		factory.classMap(WoStop.class, Stop.class)
		.byDefault().register();
		
		

//		factory.classMap(StopRequest.class, Stop.class)
//		.field("stopName", "name")
//		.byDefault().register();
//		
//		factory.classMap(ShipmentFacilityRequest.class, Stop.class)
//		.field("timezone", "timeZone")
//		.byDefault().register();
		
		
		factory.classMap(StopMapperRequest.class, Stop.class)
		.byDefault().register();
		
		
		factory.classMap(StopRequest.class, StopMapperRequest.class)
		.field("stopName", "name")
		.byDefault().register();

	    factory.classMap(Stop.class, ShipmentFacilityRequest.class)
	    .field("timeZone", "timezone")
	    .byDefault().register();

	}

}
