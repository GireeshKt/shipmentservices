package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * ShipmentSchedule entity.
 */

public class ShipmentStopRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Size(max = 22, message = "Sailing Schedule Id: Sailing Schedule Id must be maximum of 22 characters")
	private Long sailingScheduleId;
	
	@Size(max = 200, message = "Stop Location Id: Stop Location Id must be maximum of 200 characters")
	private String stopLocationId;
	
	private Date arrivalDate;
	
	private Date departureDate;
	
	public ShipmentStopRequest() {
		super();
	}
	
	public Long getsailingScheduleId() {
		return sailingScheduleId;
	}

	public void setsailingScheduleId(Long sailingScheduleId) {
		this.sailingScheduleId = sailingScheduleId;
	}
	
	public String getstopLocationId() {
		return stopLocationId;
	}

	public void setstopLocationId(String stopLocationId) {
		this.stopLocationId = stopLocationId;
	}
	
	public Date getarrivalDate() {
		return arrivalDate;
	}

	public void setarrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	public Date getdepartureDate() {
		return departureDate;
	}

	public void setdepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	
}