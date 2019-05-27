package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.model.Routing;
import com.blumeglobal.shipmentmanagement.request.RoutingRequest;
import com.blumeglobal.shipmentmanagement.response.RoutingResponse;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

/**
 * The DashboardMapper is used to handle the Object mapping for ShipmentEvent model
 *
 */
@Component
public class RoutingMapper extends ConfigurableMapper {
	@Override
	protected void configure(MapperFactory factory) {

	factory.classMap(RoutingRequest.class, Routing.class)
	// .field("", "")
	.byDefault()
	.register();
	
	factory.classMap(RoutingRequest.class, RoutingResponse.class)
	// .field("", "")
	.byDefault()
	.register();
	
	}
}
