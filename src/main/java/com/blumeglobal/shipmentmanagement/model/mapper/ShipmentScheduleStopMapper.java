package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.converter.EventRequestConverter;
import com.blumeglobal.shipmentmanagement.converter.EventResponseConverter;
import com.blumeglobal.shipmentmanagement.dm.ShipmentEvent;
import com.blumeglobal.shipmentmanagement.model.ShipmentSchedule;
import com.blumeglobal.shipmentmanagement.model.ShipmentStop;
import com.blumeglobal.shipmentmanagement.request.ShipmentEventRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentScheduleRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentStopRequest;
import com.blumeglobal.shipmentmanagement.response.ShipmentScheduleResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentStopResponse;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

/**
 * The DashboardMapper is used to handle the Object mapping for ShipmentEvent model
 *
 */
@Component
public class ShipmentScheduleStopMapper extends ConfigurableMapper {
	@Override
	protected void configure(MapperFactory factory) {

	factory.classMap(ShipmentScheduleRequest.class, ShipmentSchedule.class)
	// .field("", "")
	.byDefault()
	.register();
	
	factory.classMap(ShipmentScheduleRequest.class, ShipmentScheduleResponse.class)
	// .field("", "")
	.byDefault()
	.register();
	
	factory.classMap(ShipmentStopRequest.class, ShipmentStop.class)
	// .field("", "")
	.byDefault()
	.register();
	
	factory.classMap(ShipmentStopRequest.class, ShipmentStopResponse.class)
	// .field("", "")
	.byDefault()
	.register();
	
	}
	
}