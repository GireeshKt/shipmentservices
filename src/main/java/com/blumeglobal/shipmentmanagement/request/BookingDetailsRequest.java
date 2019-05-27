package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class BookingDetailsRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long bookingDetailsId;
	
	@Size(max = 200, message = "workOrders must be maximum of 200 characters")
	private String workOrders;
	
	@Size(max = 50, message = "defaultCarrier must be maximum of 50 characters")
	private String defaultCarrier;
	
	@Size(max = 50, message = "createdBy must be maximum of 50 characters")
	private String createdBy;

	
	public BookingDetailsRequest() {
		
	}


	public Long getBookingDetailsId() {
		return bookingDetailsId;
	}


	public void setBookingDetailsId(Long bookingDetailsId) {
		this.bookingDetailsId = bookingDetailsId;
	}


	public String getWorkOrders() {
		return workOrders;
	}


	public void setWorkOrders(String workOrders) {
		this.workOrders = workOrders;
	}


	public String getDefaultCarrier() {
		return defaultCarrier;
	}


	public void setDefaultCarrier(String defaultCarrier) {
		this.defaultCarrier = defaultCarrier;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
