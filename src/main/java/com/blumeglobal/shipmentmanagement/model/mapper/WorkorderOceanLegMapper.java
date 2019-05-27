package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.request.ShipmentRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentWorkOrderRequest;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
/**
 * map workorder data for system generated Ocean log shipment.
 *
 */
@Component
public class WorkorderOceanLegMapper extends ConfigurableMapper {
	
	@Override
	protected void configure(MapperFactory factory) {
		
		factory.classMap(ShipmentWorkOrderRequest.class, ShipmentRequest.class)
		.field("workorderid", "referenceId")
		.field("exportcutoffdate", "exportCutOffDate")
		.field("dateCreated", "createdDate")
		.field("portofdischarge", "destination")
		.field("portofloading", "origin")
		.field("eta", "expectedDeliveryDate")
		.field("eta", "scheduleDeliveryDate")
		.field("dateCreated", "workOrderCreatedDate")
		.field("statusDescription", "status")
		.field("originatorCode","organizationCode")
		.byDefault().register();	
	}
}
