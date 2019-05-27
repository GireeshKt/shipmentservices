package com.blumeglobal.shipmentmanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ShipmentStop entity.
 */
 
 @Entity
@Table(name = "SAILINGSSTOPS")

public class ShipmentStop implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	@Column(name = "SAILINGSTOPID")
	private Long sailingStopId;
	
	@Column(name = "SAILINGSCHEDULEID")
	private Long sailingScheduleId;
	
	@Column(name = "STOPLOCATIONID")
	private String stopLocationId;
	
	@Column(name = "ARRIVALDATE")
	private Date arrivalDate;
	
	@Column(name = "DEPARTUREDATE")
	private Date departureDate;
	
	public ShipmentStop() {
		super();
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