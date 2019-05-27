package com.blumeglobal.shipmentmanagement.request.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.blumeglobal.shipmentmanagement.request.ShipmentReferenceRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentStopListRequest;

public class GenericShipmentRequest implements Serializable{
 
    private static final long serialVersionUID = 1L;
    private Long shipmentId;
    private String receiverCode;
    @NotNull
    @NotEmpty
    private String originatorCode;
    private String originatorName;
    private String receiverName;
    private String consigneeName;
    private String consignerName;
    private String shipmentType;
    private String modeofTransportation;
    private String shipFromReference;
    private String customerReference;
    private String uniqueShipmentId;
    private String shipmentINCOterm;
    private String shipToReference;
    private Date scheduledPickupDate;
    private Date scheduledDeliveryDate;
    private Date requestedPickupDate;
    @NotNull
    private Date requestedDeliveryDate;
    private Date exportCutOffDate;
    private String vesselName;
    private String voyageFlightNo;
    private String portOfLoading;
    private String portOfDischarge;
    private Date lastFreeDate;
    private String carrierName;
    private String status;
    private Date respondBy;
    private String organisationCode;
    @NotEmpty
    private List<ShipmentReferenceRequest> shipmentReferences;
    private ShipmentEquipmentRequest shipmentEquipment;
    @Valid
    private LocationDetail shipFrom;
    @Valid
    private LocationDetail shipTo;
    private ShipmentStopListRequest shipmentStopListRequest;
    private List<ShipmentNote> shipmentNotes;
    //Added to identify the update request
    private boolean isUpdate;
    
    public Long getShipmentId() {
        return shipmentId;
    }
    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }
    public String getReceiverCode() {
        return receiverCode;
    }
    public void setReceiverCode(String receiverCode) {
        this.receiverCode = receiverCode;
    }
    public String getOriginatorCode() {
        return originatorCode;
    }
    public void setOriginatorCode(String originatorCode) {
        this.originatorCode = originatorCode;
    }
    public String getOriginatorName() {
        return originatorName;
    }
    public void setOriginatorName(String originatorName) {
        this.originatorName = originatorName;
    }
    public String getReceiverName() {
        return receiverName;
    }
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
    public String getConsigneeName() {
        return consigneeName;
    }
    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }
    public String getConsignerName() {
        return consignerName;
    }
    public void setConsignerName(String consignerName) {
        this.consignerName = consignerName;
    }
    public String getShipmentType() {
        return shipmentType;
    }
    public void setShipmentType(String shipmentType) {
        this.shipmentType = shipmentType;
    }
    public String getModeofTransportation() {
        return modeofTransportation;
    }
    public void setModeofTransportation(String modeofTransportation) {
        this.modeofTransportation = modeofTransportation;
    }
    public String getShipFromReference() {
        return shipFromReference;
    }
    public void setShipFromReference(String shipFromReference) {
        this.shipFromReference = shipFromReference;
    }
    public String getCustomerReference() {
        return customerReference;
    }
    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }
    public String getUniqueShipmentId() {
        return uniqueShipmentId;
    }
    public void setUniqueShipmentId(String uniqueShipmentId) {
        this.uniqueShipmentId = uniqueShipmentId;
    }
    public String getShipmentINCOterm() {
        return shipmentINCOterm;
    }
    public void setShipmentINCOterm(String shipmentINCOterm) {
        this.shipmentINCOterm = shipmentINCOterm;
    }
    public String getShipToReference() {
        return shipToReference;
    }
    public void setShipToReference(String shipToReference) {
        this.shipToReference = shipToReference;
    }
    public Date getScheduledPickupDate() {
        return scheduledPickupDate;
    }
    public void setScheduledPickupDate(Date scheduledPickupDate) {
        this.scheduledPickupDate = scheduledPickupDate;
    }
    public Date getScheduledDeliveryDate() {
        return scheduledDeliveryDate;
    }
    public void setScheduledDeliveryDate(Date scheduledDeliveryDate) {
        this.scheduledDeliveryDate = scheduledDeliveryDate;
    }
    public Date getRequestedPickupDate() {
        return requestedPickupDate;
    }
    public void setRequestedPickupDate(Date requestedPickupDate) {
        this.requestedPickupDate = requestedPickupDate;
    }
    public Date getRequestedDeliveryDate() {
        return requestedDeliveryDate;
    }
    public void setRequestedDeliveryDate(Date requestedDeliveryDate) {
        this.requestedDeliveryDate = requestedDeliveryDate;
    }
    public Date getExportCutOffDate() {
        return exportCutOffDate;
    }
    public void setExportCutOffDate(Date exportCutOffDate) {
        this.exportCutOffDate = exportCutOffDate;
    }
    public String getVesselName() {
        return vesselName;
    }
    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }
    public String getVoyageFlightNo() {
        return voyageFlightNo;
    }
    public void setVoyageFlightNo(String voyageFlightNo) {
        this.voyageFlightNo = voyageFlightNo;
    }
    public String getPortOfLoading() {
        return portOfLoading;
    }
    public void setPortOfLoading(String portOfLoading) {
        this.portOfLoading = portOfLoading;
    }
    public String getPortOfDischarge() {
        return portOfDischarge;
    }
    public void setPortOfDischarge(String portOfDischarge) {
        this.portOfDischarge = portOfDischarge;
    }
    public Date getLastFreeDate() {
        return lastFreeDate;
    }
    public void setLastFreeDate(Date lastFreeDate) {
        this.lastFreeDate = lastFreeDate;
    }
    public String getCarrierName() {
        return carrierName;
    }
    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<ShipmentReferenceRequest> getShipmentReferences() {
        return shipmentReferences;
    }
    public void setShipmentReferences(List<ShipmentReferenceRequest> shipmentReferences) {
        this.shipmentReferences = shipmentReferences;
    }
    public ShipmentEquipmentRequest getShipmentEquipment() {
        return shipmentEquipment;
    }
    public void setShipmentEquipment(ShipmentEquipmentRequest shipmentEquipment) {
        this.shipmentEquipment = shipmentEquipment;
    }
    public LocationDetail getShipFrom() {
        return shipFrom;
    }
    public void setShipFrom(LocationDetail shipFrom) {
        this.shipFrom = shipFrom;
    }
    public LocationDetail getShipTo() {
        return shipTo;
    }
    public void setShipTo(LocationDetail shipTo) {
        this.shipTo = shipTo;
    }
    public ShipmentStopListRequest getShipmentStopListRequest() {
        return shipmentStopListRequest;
    }
    public void setShipmentStopListRequest(ShipmentStopListRequest shipmentStopListRequest) {
        this.shipmentStopListRequest = shipmentStopListRequest;
    }
    public List<ShipmentNote> getShipmentNotes() {
        return shipmentNotes;
    }
    public void setShipmentNotes(List<ShipmentNote> shipmentNotes) {
        this.shipmentNotes = shipmentNotes;
    }
    public Date getRespondBy() {
        return respondBy;
    }
    public void setRespondBy(Date respondBy) {
        this.respondBy = respondBy;
    }
    public String getOrganisationCode() {
        return organisationCode;
    }
    public void setOrganisationCode(String organisationCode) {
        this.organisationCode = organisationCode;
    }
	public boolean isUpdate() {
		return isUpdate;
	}
	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}


}
