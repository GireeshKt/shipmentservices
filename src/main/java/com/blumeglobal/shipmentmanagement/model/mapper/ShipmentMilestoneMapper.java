package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.model.ShipmentMilestone;
import com.blumeglobal.shipmentmanagement.response.ShipmentMilestoneForRuleEngine;
import com.blumeglobal.shipmentmanagement.response.ShipmentMilestoneResponse;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;


@Component
public class ShipmentMilestoneMapper extends ConfigurableMapper {
	
	@Override
	protected void configure(MapperFactory factory) {
		
		factory.classMap(ShipmentMilestone.class, ShipmentMilestoneResponse.class)
        .field("milestone.orderSeq", "orderSeq")
        .field("milestone.eventCode", "eventCode")
        .field("milestone.eventName", "eventName")
		.byDefault().register();	
		
        factory.classMap(ShipmentMilestone.class, ShipmentMilestoneForRuleEngine.class)
        .field("locationId", "uid")
        .field("modeOfTransport", "mode")
        .field("milestone.orderSeq", "orderSeq")
        .field("milestone.eventCode", "eventCode")
        .field("milestone.eventName", "eventName")
        .byDefault().register();    
        
	}
}
