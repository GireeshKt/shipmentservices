package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.converter.ExcelPurchaseOrderDTConverter;
import com.blumeglobal.shipmentmanagement.converter.PurchaseOrderRequestDTConverter;
import com.blumeglobal.shipmentmanagement.converter.PurchaseOrderResponseDTConverter;
import com.blumeglobal.shipmentmanagement.model.PurchaseOrder;
import com.blumeglobal.shipmentmanagement.request.POAPIRequest;
import com.blumeglobal.shipmentmanagement.response.ExcelPurchaseOrderSuccessRowsResponse;
import com.blumeglobal.shipmentmanagement.response.POAPIResponse;
import com.blumeglobal.shipmentmanagement.response.PurchaseOrderResponse;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class PurchaseOrderMapper extends ConfigurableMapper{
	@Override
	protected void configure(MapperFactory factory) {
		factory.classMap(PurchaseOrder.class, PurchaseOrderResponse.class)
		.register();
		factory.classMap(POAPIRequest.class, PurchaseOrder.class)
		.exclude("createdOn")
		.exclude("updatedOn")
		.byDefault()
		.customize(new PurchaseOrderRequestDTConverter("MM-dd-yyyy HH:mm:ss"))
		.register();
		
		factory.classMap(PurchaseOrder.class, POAPIResponse.class)
		.exclude("createdOn")
		.exclude("updatedOn")
		.byDefault()
		.customize(new PurchaseOrderResponseDTConverter("MM-dd-yyyy HH:mm:ss"))
		.register();

		factory.classMap(ExcelPurchaseOrderSuccessRowsResponse.class, PurchaseOrder.class)
		.exclude("createdOn")
		.exclude("updatedOn")
		.byDefault()
		.customize(new ExcelPurchaseOrderDTConverter("MM-dd-yyyy HH:mm:ss"))
		.register();
		

	}


}
