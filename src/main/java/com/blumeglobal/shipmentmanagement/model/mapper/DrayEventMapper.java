package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;
import com.blumeglobal.shipmentmanagement.request.DrayEventRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentEventRequest;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class DrayEventMapper extends ConfigurableMapper{
	
	@Override
	protected void configure(MapperFactory factory) {
	
	    factory.classMap(DrayEventRequest.class, ShipmentEventRequest.class)
	    .field("workOrderNumber", "workOrderNumber")
        .field("originatorCode", "originatorCode")
        .field("receiverCode", "receiverCode")
        .field("stopType", "stopType")
        .field("stopType", "eventName")
        .field("eventDetails.reportedDate", "eventTime")
        .field("stopName", "location")
	    .byDefault()
	    .customize(
	    		new CustomMapper<DrayEventRequest, ShipmentEventRequest>() {
					      public void mapAtoB(DrayEventRequest a, ShipmentEventRequest b, MappingContext context) {
					    	  b.setUnitId(a.getEventDetails().getEquipment().getEquipmentNumber());
					    	  b.setShipmentReferenceNumber(a.getEventDetails().getEquipment().getShipmentNumber());
					      }
				   })
	    .register();

	}

}
