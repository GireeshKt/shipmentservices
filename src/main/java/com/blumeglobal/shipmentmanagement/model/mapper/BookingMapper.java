package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.model.Booking;
import com.blumeglobal.shipmentmanagement.model.BookingDetails;
import com.blumeglobal.shipmentmanagement.request.BookingDetailsRequest;
import com.blumeglobal.shipmentmanagement.request.BookingFrontRequest;
import com.blumeglobal.shipmentmanagement.request.BookingRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentWorkOrderRequest;
import com.blumeglobal.shipmentmanagement.request.tempReq;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class BookingMapper extends ConfigurableMapper{
	
	@Override
	protected void configure(MapperFactory factory) {
		
		factory.classMap(BookingRequest.class, Booking.class)
		.byDefault().register();
	
		
		factory.classMap(BookingDetailsRequest.class, BookingDetails.class)
		.byDefault().register();
		
		
		factory.classMap(BookingFrontRequest.class, BookingRequest.class)
		.byDefault().register();
		
		
		factory.classMap(BookingFrontRequest.class, BookingDetailsRequest.class)
		.byDefault().register();
		
		//New Code
		factory.classMap(Booking.class, ShipmentWorkOrderRequest.class)
		.field("bookingId", "workorderid")
		.field("shipmentReferenceNum", "shipmentReferenceNumber")
		.field("shipper", "originatorname")
		.byDefault().register();
		
		factory.classMap(Booking.class, tempReq.class)
		.field("bookingId", "name")
		.field("shipper", "job")
		.byDefault().register();
		
		
	}

}
