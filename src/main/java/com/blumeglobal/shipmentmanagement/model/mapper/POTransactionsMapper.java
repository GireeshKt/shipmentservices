package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.converter.POTransactionsRequestDTConverter;
import com.blumeglobal.shipmentmanagement.converter.POTransactionsResponseDTConverter;
import com.blumeglobal.shipmentmanagement.model.POTransactions;
import com.blumeglobal.shipmentmanagement.request.POTransactionsAPIRequest;
import com.blumeglobal.shipmentmanagement.response.POTransactionsAPIResponse;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class POTransactionsMapper extends ConfigurableMapper{
	@Override
	protected void configure(MapperFactory factory) {
		factory.classMap(POTransactions.class, POTransactionsAPIResponse.class)
		.register();
		factory.classMap(POTransactionsAPIRequest.class, POTransactions.class)
		.exclude("createdOn")
		.exclude("updatedOn")
		.byDefault()
		.customize(new POTransactionsRequestDTConverter("MM-dd-yyyy HH:mm:ss"))
		.register();
		factory.classMap(POTransactions.class, POTransactionsAPIResponse.class)
	    .exclude("createdOn")
	    .exclude("updatedOn")
	    .byDefault()
		.customize(new POTransactionsResponseDTConverter("MM-dd-yyyy HH:mm:ss"))
		.register();
		
	}
	
	
}
