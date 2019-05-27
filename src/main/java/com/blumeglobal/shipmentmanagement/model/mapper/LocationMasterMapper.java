package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.model.LocationMaster;
import com.blumeglobal.shipmentmanagement.request.LocationMasterRequest;
import com.blumeglobal.shipmentmanagement.response.LocationAPIResponse;
import com.blumeglobal.shipmentmanagement.response.LocationMasterResponse;
import com.blumeglobal.shipmentmanagement.response.LocationResponse;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class LocationMasterMapper extends ConfigurableMapper{
	@Override
	protected void configure(MapperFactory factory) {
		
		factory.classMap(LocationMasterRequest.class, LocationMaster.class)
		.byDefault().register();
		
		factory.classMap(LocationMasterRequest.class, LocationMasterResponse.class)
		.byDefault().register();
		factory.classMap(LocationResponse.class, LocationAPIResponse.class)
		.byDefault().register();
		
	}
}


