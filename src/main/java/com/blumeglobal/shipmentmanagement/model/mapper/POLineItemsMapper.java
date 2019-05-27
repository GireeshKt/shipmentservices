package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.converter.POLineItemsRequestDTConverter;
import com.blumeglobal.shipmentmanagement.converter.POLineItemsResponseDTConverter;
import com.blumeglobal.shipmentmanagement.model.POLineItems;
import com.blumeglobal.shipmentmanagement.request.POLineItemsAPIRequest;
import com.blumeglobal.shipmentmanagement.response.POLineItemsResponse;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class POLineItemsMapper extends ConfigurableMapper{
	@Override
	protected void configure(MapperFactory factory) {
		factory.classMap(POLineItems.class, POLineItemsResponse.class)
		.register();
		factory.classMap(POLineItemsAPIRequest.class, POLineItems.class)
		.exclude("createdOn")
		.exclude("updatedOn")
		.byDefault()
		.customize(new POLineItemsRequestDTConverter("MM-dd-yyyy HH:mm:ss"))
		.register();
		factory.classMap(POLineItems.class, POLineItemsResponse.class)
	    .exclude("createdOn")
	    .exclude("updatedOn")
	    .byDefault()
		.customize(new POLineItemsResponseDTConverter("MM-dd-yyyy HH:mm:ss"))
		.register();
		
	}

}
