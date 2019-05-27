package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class WorkOrderRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String workOrderNumber;
	
	private String workOrderDate;
	
	private String eta;
	
	private Long billOfLadingNumber;
	
	private String carrierCode;
	
	private String originatorCode;
	
	private String createdBy;
	
	private Long bookingNumber;
	
	private String shipmentReferenceNumber;
	
	private String originatorOrderReference;
	
	private String shipper;
	
	private String category;
	
	private String vessel;
	
	private String voyage;
	
	private String portOfDischarge;
	
	private String portOfLoading;
	
	private List<EquipmentListRequest> equipmentList;
	
	private List<StopListRequest> stopList;
	
	public WorkOrderRequest() {
		
	}

	public String getWorkOrderNumber() {
		return workOrderNumber;
	}

	public void setWorkOrderNumber(String workOrderNumber) {
		this.workOrderNumber = workOrderNumber;
	}

	public String getWorkOrderDate() {
		return workOrderDate;
	}

	public void setWorkOrderDate(String workOrderDate) {
		this.workOrderDate = workOrderDate;
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	public Long getBillOfLadingNumber() {
		return billOfLadingNumber;
	}

	public void setBillOfLadingNumber(Long billOfLadingNumber) {
		this.billOfLadingNumber = billOfLadingNumber;
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public String getOriginatorCode() {
		return originatorCode;
	}

	public void setOriginatorCode(String originatorCode) {
		this.originatorCode = originatorCode;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Long getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(Long bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public String getShipmentReferenceNumber() {
		return shipmentReferenceNumber;
	}

	public void setShipmentReferenceNumber(String shipmentReferenceNumber) {
		this.shipmentReferenceNumber = shipmentReferenceNumber;
	}

	public String getOriginatorOrderReference() {
		return originatorOrderReference;
	}

	public void setOriginatorOrderReference(String originatorOrderReference) {
		this.originatorOrderReference = originatorOrderReference;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getPortOfDischarge() {
		return portOfDischarge;
	}

	public void setPortOfDischarge(String portOfDischarge) {
		this.portOfDischarge = portOfDischarge;
	}

	public String getPortOfLoading() {
		return portOfLoading;
	}

	public void setPortOfLoading(String portOfLoading) {
		this.portOfLoading = portOfLoading;
	}

	public List<EquipmentListRequest> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<EquipmentListRequest> equipmentList) {
		this.equipmentList = equipmentList;
	}

	public List<StopListRequest> getStopList() {
		return stopList;
	}

	public void setStopList(List<StopListRequest> stopList) {
		this.stopList = stopList;
	}
	
}
