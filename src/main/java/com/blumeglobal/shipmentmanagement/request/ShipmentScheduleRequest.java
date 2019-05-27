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

public class ShipmentScheduleRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Size(max = 200, message = "Origin Location Id: Origin Location Id must be maximum of 200 characters")
	private String originLocationId;
	
	@Size(max = 200, message = "Destination Location Id: Destination Location Id must be maximum of 200 characters")
	private String destinationLocationId;
	
	@Size(max = 50, message = "Vessel: Vessel must be maximum of 50 characters")
	private String vessel;
	
	@Size(max = 50, message = "Voyage: Voyage must be maximum of 50 characters")
	private String voyage;
	
	private Date arrivalDate;
	
	private Date departureDate;
	
	@Size(max = 100, message = "Carrier Name: Carrier Name must be maximum of 100 characters")
	private String carrierName;
	
	@Size(max = 100, message = "Schedule Source: Schedule Source must be maximum of 100 characters")
	private String scheduleSource;
	
	@Size(max = 100, message = "Service Lane: Service Lane must be maximum of 100 characters")
	private String serviceLane;
	
	public ShipmentScheduleRequest() {
		super();
	}
	
	public String getoriginLocationId() {
		return originLocationId;
	}

	public void setoriginLocationId(String originLocationId) {
		this.originLocationId = originLocationId;
	}
	
	public String getdestinationLocationId() {
		return destinationLocationId;
	}

	public void setdestinationLocationId(String destinationLocationId) {
		this.destinationLocationId = destinationLocationId;
	}
	
	public String getvessel() {
		return vessel;
	}

	public void setvessel(String vessel) {
		this.vessel = vessel;
	}
	
	public String getvoyage() {
		return voyage;
	}

	public void setvoyage(String voyage) {
		this.voyage = voyage;
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
	
	public String getcarrierName() {
		return carrierName;
	}

	public void setcarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	
	public String getscheduleSource() {
		return scheduleSource;
	}

	public void setscheduleSource(String scheduleSource) {
		this.scheduleSource = scheduleSource;
	}
	
	public String getserviceLane() {
		return serviceLane;
	}

	public void setserviceLane(String serviceLane) {
		this.serviceLane = serviceLane;
	}
}