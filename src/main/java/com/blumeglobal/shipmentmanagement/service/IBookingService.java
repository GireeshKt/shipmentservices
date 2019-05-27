package com.blumeglobal.shipmentmanagement.service;

import com.blumeglobal.shipmentmanagement.model.Booking;
import com.blumeglobal.shipmentmanagement.request.BookingFrontRequest;
import com.blumeglobal.shipmentmanagement.request.BookingRequest;
import com.blumeglobal.shipmentmanagement.response.BookingResponse;


public interface IBookingService {
	
	/**
	 * Create Booking through API
	 * 
	 * @param createBookingRequest
	 *            This is request which has booking details
	 * @return BookingResponse Booking Response after save
	 * @throws Exception
	 *             Handle service exceptions
	 */
	public BookingResponse createBooking(BookingRequest createBookingRequest)throws Exception;
	
	
	
	
	/**
	 * Create Booking through API
	 * 
	 * @param createBookingFrontRequest
	 *            This is request which has booking details
	 * @return BookingResponse Booking Response after save
	 * @throws Exception
	 *             Handle service exceptions
	 */
	public BookingResponse createBookingAPI(BookingFrontRequest createBookingFrontRequest) throws Exception;
	
	
	
	
	/**
	 * Call WO API through API
	 * 
	 * @param booking
	 *            This is request which has booking details
	 */
	public void callWorkOrderAPI(Booking booking);
	

}
