package com.blumeglobal.shipmentmanagement.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blumeglobal.shipmentmanagement.request.BookingFrontRequest;
import com.blumeglobal.shipmentmanagement.request.BookingRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentWorkOrderRequest;
import com.blumeglobal.shipmentmanagement.response.BookingResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentResponse;
import com.blumeglobal.shipmentmanagement.service.IBookingService;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The BookingController is used to manage the Booking details.
 *
 */
@RestController
@RequestMapping("/v1")
@Api(value = "Booking Service")
@REZ1Logger
@REZ1PerformanceLogger
public class BookingController {
	
	private static final Logger logger = LogManager.getLogger(BookingController.class);
	
	@Autowired
	private IBookingService bookingService;
	
	
// Booking service is not used as a part of shipment service	
//	@RequestMapping(value = "/bmvHealth", method = RequestMethod.GET)
//	public @ResponseBody String bmvHealth() {
//		return "Blume Visibility Health is sucessful";
//	}
		
		
	/**
	 * This Controller method is used to create booking using API
	 * 
	 * @param createBookingRequest
	 *            This is used to pass booking request data
	 * @return BookingResponse upload To get response of BookingResponse
	 * @throws Exception
	 *             Handle the exceptions
	 */
//	@RequestMapping(value = "/bv/bookings", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "Create Booking ", produces = "application/json", consumes = "application/json")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
//	@ApiResponse(code = 500, message = "The POST call is Failed"),
//	@ApiResponse(code = 404, message = "The API could not be found"),
//	@ApiResponse(code = 400, message = "Invalid input") })
//	public ResponseEntity<?> createBookings(@RequestBody BookingFrontRequest createBookingFrontRequest)throws Exception {	
//		logger.info("Entering into BookingController.createBooking");
//		
//		logger.debug("Inside BookingController.createBookingAPI - Insert Booking");
//		BookingResponse bookingResponse = bookingService.createBookingAPI(createBookingFrontRequest);
//		logger.debug("Outside BookingController.createBooking - Insert Booking");
//		
//		return new ResponseEntity<>(bookingResponse, HttpStatus.OK);
//	}

}
