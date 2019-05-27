package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class ShipmentStopResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Id;
	private String status;
	private String message;
	private Long sailingStopId;	
	private Long sailingScheduleId;	
	private String stopLocationId;
	private Date arrivalDate;	
	private Date departureDate;
	
	public ShipmentStopResponse() {

	}
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getSailingStopId() {
		return sailingStopId;
	}

	public void setSailingStopId(Long sailingStopId) {
		this.sailingStopId = sailingStopId;
	}

	public Long getSailingScheduleId() {
		return sailingScheduleId;
	}

	public void setSailingScheduleId(Long sailingScheduleId) {
		this.sailingScheduleId = sailingScheduleId;
	}

	public String getStopLocationId() {
		return stopLocationId;
	}

	public void setStopLocationId(String stopLocationId) {
		this.stopLocationId = stopLocationId;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	
}