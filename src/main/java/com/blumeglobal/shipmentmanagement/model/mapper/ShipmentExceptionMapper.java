package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.converter.ShipmentExceptionResponseConverter;
import com.blumeglobal.shipmentmanagement.model.ShipmentException;
import com.blumeglobal.shipmentmanagement.request.ShipmentExceptionRequest;
import com.blumeglobal.shipmentmanagement.response.ShipmentExceptionResponse;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
/**
 * The ShipmentExceptionMapper is used to handle the Object mapping for ShipmentException model
 *
 */
@Component
public class ShipmentExceptionMapper extends ConfigurableMapper {
	@Override
	protected void configure(MapperFactory factory) {
		
		factory.classMap(ShipmentException.class, ShipmentExceptionResponse.class)
		.exclude("exceptionType")
		.byDefault()
		.customize(new ShipmentExceptionResponseConverter())
		.register();
		
		factory.classMap(ShipmentExceptionRequest.class, ShipmentException.class)
		.byDefault().register();
		
		}
}