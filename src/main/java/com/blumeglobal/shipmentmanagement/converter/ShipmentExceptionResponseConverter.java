package com.blumeglobal.shipmentmanagement.converter;

import com.blumeglobal.shipmentmanagement.enums.ShipmentExceptionType;
import com.blumeglobal.shipmentmanagement.model.ShipmentException;
import com.blumeglobal.shipmentmanagement.response.ShipmentExceptionResponse;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class ShipmentExceptionResponseConverter extends CustomMapper<ShipmentException, ShipmentExceptionResponse> {
	
	public ShipmentExceptionResponseConverter() {
	}
	
	@Override
    public void mapAtoB(ShipmentException exception, ShipmentExceptionResponse exceptionRes, MappingContext context) {
		if(ShipmentExceptionType.LATE_MILESTONE.getDescription().equals(exception.getExceptionType())
				||ShipmentExceptionType.EARLY_MILESTONE.getDescription().equals(exception.getExceptionType())) {
			exceptionRes.setExceptionType(exception.getExceptionType().replace("milestone", exception.getExceptionNotes()));
		}else {
			exceptionRes.setExceptionType(exception.getExceptionType());
		}
    }
}
