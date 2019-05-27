package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
import java.util.Date;

public class ActiveShipmentResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long shipmentId;
	private String status;
	private String destination;
	private String origin;
	private Date eta;
	private String legMode;
	private String currentMoveDesc; 
	private String lastMoveDesc;
	private String nextMoveDesc;
	private String originAddress1;
	private String originAddress2;
	private String originCity;
	private String originState;
	private String originZipcode;
	private String originCountry;
	private String destinationAddress1;
	private String destinationAddress2;
	private String destinationCity;
	private String destinationState;
	private String destinationZipcode;
	private String destinationCountry;
	private Date actualDate;
    private Integer completedShipments;
    private Integer totalShipments;
    private boolean withE2eShipmentException;
    private String statusColor;
    private ShipmentExceptionResponse shipmentException;

	public ActiveShipmentResponse() {
	}

	public Long getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(Long shipmentId) {
		this.shipmentId = shipmentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getEta() {
		return eta;
	}

	public void setEta(Date eta) {
		this.eta = eta;
	}

	public String getLegMode() {
		return legMode;
	}

	public void setLegMode(String legMode) {
		this.legMode = legMode;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getCurrentMoveDesc() {
		return currentMoveDesc;
	}

	public void setCurrentMoveDesc(String currentMoveDesc) {
		this.currentMoveDesc = currentMoveDesc;
	}

	public String getLastMoveDesc() {
		return lastMoveDesc;
	}

	public void setLastMoveDesc(String lastMoveDesc) {
		this.lastMoveDesc = lastMoveDesc;
	}

	public String getNextMoveDesc() {
		return nextMoveDesc;
	}

	public void setNextMoveDesc(String nextMoveDesc) {
		this.nextMoveDesc = nextMoveDesc;
	}

	public String getOriginAddress1() {
		return originAddress1;
	}

	public void setOriginAddress1(String originAddress1) {
		this.originAddress1 = originAddress1;
	}

	public String getOriginAddress2() {
		return originAddress2;
	}

	public void setOriginAddress2(String originAddress2) {
		this.originAddress2 = originAddress2;
	}

	public String getOriginCity() {
		return originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	public String getOriginState() {
		return originState;
	}

	public void setOriginState(String originState) {
		this.originState = originState;
	}

	public String getOriginZipcode() {
		return originZipcode;
	}

	public void setOriginZipcode(String originZipcode) {
		this.originZipcode = originZipcode;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getDestinationAddress1() {
		return destinationAddress1;
	}

	public void setDestinationAddress1(String destinationAddress1) {
		this.destinationAddress1 = destinationAddress1;
	}

	public String getDestinationAddress2() {
		return destinationAddress2;
	}

	public void setDestinationAddress2(String destinationAddress2) {
		this.destinationAddress2 = destinationAddress2;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public String getDestinationState() {
		return destinationState;
	}

	public void setDestinationState(String destinationState) {
		this.destinationState = destinationState;
	}

	public String getDestinationZipcode() {
		return destinationZipcode;
	}

	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public Date getActualDate() {
		return actualDate;
	}

	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
	}

	public Integer getCompletedShipments() {
		return completedShipments;
	}

	public void setCompletedShipments(Integer completedShipments) {
		this.completedShipments = completedShipments;
	}

	public Integer getTotalShipments() {
		return totalShipments;
	}

	public void setTotalShipments(Integer totalShipments) {
		this.totalShipments = totalShipments;
	}

	public boolean isWithE2eShipmentException() {
		return withE2eShipmentException;
	}

	public void setWithE2eShipmentException(boolean withE2eShipmentException) {
		this.withE2eShipmentException = withE2eShipmentException;
	}

	public String getStatusColor() {
		return statusColor;
	}

	public void setStatusColor(String statusColor) {
		this.statusColor = statusColor;
	}

	public ShipmentExceptionResponse getShipmentException() {
		return shipmentException;
	}

	public void setShipmentException(ShipmentExceptionResponse shipmentException) {
		this.shipmentException = shipmentException;
	}

	
}
