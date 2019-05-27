package com.blumeglobal.shipmentmanagement.response.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.blumeglobal.shipmentmanagement.request.ShipmentStopListRequest;
import com.blumeglobal.shipmentmanagement.request.common.LocationDetail;
import com.blumeglobal.shipmentmanagement.request.common.ShipmentNote;
import com.blumeglobal.shipmentmanagement.request.common.ShipmentReference;

public class GenericShipmentResponse implements Serializable{

    private static final long serialVersionUID = 1L;
    private Long shipmentId;
    private String receiverCode;
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
    private Date requestedDeliveryDate;
    private Date exportCutOffDate;
    private String vesselName;
    private String voyageFlightNo;
    private String portOdLoading;
    private String portOfDischarge;
    private Date lastFreeDate;
    private String carrierName;
    private String status;
    private Date respondBy;
    private List<ShipmentReference> shipmentReferences;
    private ShipmentEquipmentResponse shipmentEquipment;
    private LocationDetail shipFrom;
    private LocationDetail shipTo;
    private ShipmentStopListRequest shipmentStopListRequest;
    private List<ShipmentNote> shipmentNotes;

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

    public List<ShipmentReference> getShipmentReferences() {
        return shipmentReferences;
    }

    public void setShipmentReferences(List<ShipmentReference> shipmentReferences) {
        this.shipmentReferences = shipmentReferences;
    }

    public String getOriginatorName() {
        return originatorName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public String getConsignerName() {
        return consignerName;
    }

    public String getShipmentType() {
        return shipmentType;
    }

    public String getModeofTransportation() {
        return modeofTransportation;
    }

    public String getShipFromReference() {
        return shipFromReference;
    }

    public String getCustomerReference() {
        return customerReference;
    }

    public String getUniqueShipmentId() {
        return uniqueShipmentId;
    }

    public String getShipmentINCOterm() {
        return shipmentINCOterm;
    }

    public String getShipToReference() {
        return shipToReference;
    }



    public String getVesselName() {
        return vesselName;
    }

    public String getVoyageFlightNo() {
        return voyageFlightNo;
    }

    public String getPortOdLoading() {
        return portOdLoading;
    }

    public String getPortOfDischarge() {
        return portOfDischarge;
    }

    public String getCarrierName() {
        return carrierName;
    }


    public void setOriginatorName(String originatorName) {
        this.originatorName = originatorName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public void setConsignerName(String consignerName) {
        this.consignerName = consignerName;
    }

    public void setShipmentType(String shipmentType) {
        this.shipmentType = shipmentType;
    }

    public void setModeofTransportation(String modeofTransportation) {
        this.modeofTransportation = modeofTransportation;
    }

    public void setShipFromReference(String shipFromReference) {
        this.shipFromReference = shipFromReference;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    public void setUniqueShipmentId(String uniqueShipmentId) {
        this.uniqueShipmentId = uniqueShipmentId;
    }

    public void setShipmentINCOterm(String shipmentINCOterm) {
        this.shipmentINCOterm = shipmentINCOterm;
    }

    public void setShipToReference(String shipToReference) {
        this.shipToReference = shipToReference;
    }


    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    public void setVoyageFlightNo(String voyageFlightNo) {
        this.voyageFlightNo = voyageFlightNo;
    }

    public void setPortOdLoading(String portOdLoading) {
        this.portOdLoading = portOdLoading;
    }

    public void setPortOfDischarge(String portOfDischarge) {
        this.portOfDischarge = portOfDischarge;
    }
    
    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public void setShipmentNotes(List<ShipmentNote> shipmentNotes) {
        this.shipmentNotes = shipmentNotes;
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

    public List<ShipmentNote> getShipmentNotes() {
        return shipmentNotes;
    }

    public ShipmentEquipmentResponse getShipmentEquipment() {
        return shipmentEquipment;
    }

    public void setShipmentEquipment(ShipmentEquipmentResponse shipmentEquipment) {
        this.shipmentEquipment = shipmentEquipment;
    }

    public ShipmentStopListRequest getShipmentStopListRequest() {
        return shipmentStopListRequest;
    }

    public void setShipmentStopListRequest(ShipmentStopListRequest shipmentStopListRequest) {
        this.shipmentStopListRequest = shipmentStopListRequest;
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

    public Date getLastFreeDate() {
        return lastFreeDate;
    }

    public void setLastFreeDate(Date lastFreeDate) {
        this.lastFreeDate = lastFreeDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRespondBy() {
        return respondBy;
    }

    public void setRespondBy(Date respondBy) {
        this.respondBy = respondBy;
    }

}
