package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class ShipmentScheduleResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String status;
	private String message;
	private Long shipmentScheduleId;
	private String originLocationId;
	private String destinationLocationId;
	private String vessel;
	private String voyage;
	private Date arrivalDate;
	private Date departureDate;
	private String carrierName;
	private String scheduleSource;
	private String serviceLane;
	public ShipmentScheduleResponse() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Long getShipmentScheduleId() {
		return shipmentScheduleId;
	}

	public void setShipmentScheduleId(Long shipmentScheduleId) {
		this.shipmentScheduleId = shipmentScheduleId;
	}

	public String getOriginLocationId() {
		return originLocationId;
	}

	public void setOriginLocationId(String originLocationId) {
		this.originLocationId = originLocationId;
	}

	public String getDestinationLocationId() {
		return destinationLocationId;
	}

	public void setDestinationLocationId(String destinationLocationId) {
		this.destinationLocationId = destinationLocationId;
	}

	public String getVessel() {
		return vessel;
	}

	public void setVessel(String vessel) {
		this.vessel = vessel;
	}

	public String getVoyage() {
		return voyage;
	}

	public void setVoyage(String voyage) {
		this.voyage = voyage;
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

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getScheduleSource() {
		return scheduleSource;
	}

	public void setScheduleSource(String scheduleSource) {
		this.scheduleSource = scheduleSource;
	}

	public String getServiceLane() {
		return serviceLane;
	}

	public void setServiceLane(String serviceLane) {
		this.serviceLane = serviceLane;
	}
	
}
