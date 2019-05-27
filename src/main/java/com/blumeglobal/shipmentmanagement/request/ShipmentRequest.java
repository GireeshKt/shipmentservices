package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import com.blumeglobal.shipmentmanagement.enums.ShipmentReferenceType;
import com.blumeglobal.shipmentmanagement.utils.ShipmentUtil;

public class ShipmentRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long shipmentId;

    private Long e2eShipmentId;

    @Size(max = 20, message = "referenceType must be maximum of 20 characters")
    private String referenceType;

    private Long referenceId;

    @Size(max = 20, message = "legMode must be maximum of 20 characters")
    private String legMode;

    @Size(max = 50, message = "status must be maximum of 50 characters")
    private String status;

    private Date schedulePickupDate;

    private Date scheduleDeliveryDate;

    private Date exportCutOffDate;

    private Date lastFreeDate;

    private Date workOrderCreatedDate;

    private String origin_LocationId;

    @Size(max = 100, message = "origin must be maximum of 100 characters")
    private String origin;

    @Size(max = 200, message = "originAddress1 must be maximum of 200 characters")
    private String originAddress1;

    @Size(max = 200, message = "originAddress2 must be maximum of 200 characters")
    private String originAddress2;

    @Size(max = 50, message = "originCity must be maximum of 50 characters")
    private String originCity;

    @Size(max = 50, message = "originState must be maximum of 50 characters")
    private String originState;

    @Size(max = 20, message = "originZipcode must be maximum of 20 characters")
    private String originZipcode;

    @Size(max = 50, message = "originCountry must be maximum of 50 characters")
    private String originCountry;

    private String destination_LocationId;

    @Size(max = 100, message = "destination must be maximum of 100 characters")
    private String destination;

    @Size(max = 200, message = "destinationAddress1 must be maximum of 200 characters")
    private String destinationAddress1;

    @Size(max = 200, message = "destinationAddress2 must be maximum of 200 characters")
    private String destinationAddress2;

    @Size(max = 50, message = "destinationCity must be maximum of 50 characters")
    private String destinationCity;

    @Size(max = 50, message = "destinationState must be maximum of 50 characters")
    private String destinationState;

    @Size(max = 20, message = "destinationZipcode must be maximum of 20 characters")
    private String destinationZipcode;

    @Size(max = 50, message = "destinationCountry must be maximum of 50 characters")
    private String destinationCountry;

    private String consignor_LocationId;

    @Size(max = 100, message = "consignor must be maximum of 100 characters")
    private String consignor;

    @Size(max = 200, message = "consignorAddress1 must be maximum of 100 characters")
    private String consignorAddress1;

    @Size(max = 200, message = "consignorAddress2 must be maximum of 200 characters")
    private String consignorAddress2;

    @Size(max = 50, message = "consignorCity must be maximum of 50 characters")
    private String consignorCity;

    @Size(max = 50, message = "consignorState must be maximum of 50 characters")
    private String consignorState;

    @Size(max = 20, message = "consignorZipcode must be maximum of 20 characters")
    private String consignorZipcode;

    @Size(max = 50, message = "consignorCountry must be maximum of 50 characters")
    private String consignorCountry;


    private String consignee_LocationId;

    @Size(max = 100, message = "consignee must be maximum of 100 characters")
    private String consignee;

    @Size(max = 200, message = "consigneeAddress1 must be maximum of 200 characters")
    private String consigneeAddress1;

    @Size(max = 200, message = "consigneeAddress2 must be maximum of 200 characters")
    private String consigneeAddress2;

    @Size(max = 50, message = "consigneeCity must be maximum of 50 characters")
    private String consigneeCity;

    @Size(max = 50, message = "consigneeState must be maximum of 50 characters")
    private String consigneeState;

    @Size(max = 20, message = "consigneeZipcode must be maximum of 20 characters")
    private String consigneeZipcode;

    @Size(max = 50, message = "consigneeCountry must be maximum of 50 characters")
    private String consigneeCountry;

    private String billTo_LocationId;

    @Size(max = 100, message = "billTo must be maximum of 100 characters")
    private String billTo;

    @Size(max = 200, message = "billToAddress1 must be maximum of 200 characters")
    private String billToAddress1;

    @Size(max = 200, message = "billToAddress2 must be maximum of 200 characters")
    private String billToAddress2;

    @Size(max = 50, message = "billToCity must be maximum of 50 characters")
    private String billToCity;

    @Size(max = 50, message = "billToState must be maximum of 50 characters")
    private String billToState;

    @Size(max = 20, message = "billToZipcode must be maximum of 20 characters")
    private String billToZipcode;

    @Size(max = 50, message = "billToCountry must be maximum of 50 characters")
    private String billToCountry;

    private String shipTo_LocationId;

    @Size(max = 200, message = "shipperToAddress1 must be maximum of 200 characters")
    private String shipperToAddress1;

    @Size(max = 200, message = "shipperToAddress2 must be maximum of 200 characters")
    private String shipperToAddress2;

    @Size(max = 50, message = "shipperToCity must be maximum of 50 characters")
    private String shipperToCity;

    @Size(max = 50, message = "shipperToState must be maximum of 50 characters")
    private String shipperToState;

    @Size(max = 20, message = "shipperToZipcode must be maximum of 20 characters")
    private String shipperToZipcode;

    @Size(max = 50, message = "shipperToCountry must be maximum of 50 characters")
    private String shipperToCountry;

    private Date expectedDeliveryDate;

    private Double totalWeight;

    @Size(max = 20, message = "weightUom must be maximum of 20 characters")
    private String weightUom;

    @Column(name = "TOTALVOLUME")
    private Double totalVolume;

    @Size(max = 20, message = "volumeUom must be maximum of 20 characters")
    private String volumeUom;

    private Double packageCount;

    @Size(max = 20, message = "packageType must be maximum of 20 characters")
    private String packageType;

    private Double totalDeclaredValue;

    @Size(max = 1, message = "isHazmat must be maximum of 4 characters of Y/N yes/no true/false value")
    private String isHazmat;

    @Size(max = 1, message = "isTempControlled must be maximum of 4 characters of Y/N yes/no true/false value")
    private String isTempControlled;

    /*
     * private Boolean isHazmat;
     * 
     * private Boolean isTempControlled;
     * 
     */

    @Size(max = 100, message = "podNumber must be maximum of 100 characters")
    private String podNumber;

    private Date podDate;

    @Size(max = 100, message = "unitId must be maximum of 100 characters")
    private String unitId;

    @Size(max = 100, message = "createdBy must be maximum of 100 characters")
    private String createdBy;

    private Date createdDate;

    @Size(max = 100, message = "lastModifiedBy must be maximum of 100 characters")
    private String lastModifiedBy;

    private Date lastModified;

    private Set<ShipmentReferenceRequest> shipmentReferences;

    private String carrier;

    private String source;

    private String vessel;

    private String voyage;

    private String organizationCode;

    private String freightDescription;
    private String masterShipmentReferenceNumber;
    private String originatorCode;
    private Long receiverId;
    private String originatorName;
    private String receiverName;
    private String category;
    private String vendorNumber;
    private String portOfLoading;
    private String portOfDischarge;
    private String paymentMethodIndicator;
    private Double freightCharge;
    private Double fuelSurchargeTotal;
    private String currency;
    private Date respondBy;
    private String originUnlocode;
    private String destinationUnlocode;

    private ShipmentStopListRequest shipmentStopListRequest;

    public ShipmentRequest() {

    }


    public Long getShipmentId() {
        return shipmentId;
    }


    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }



    public Long getE2eShipmentId() {
        return e2eShipmentId;
    }


    public void setE2eShipmentId(Long e2eShipmentId) {
        this.e2eShipmentId = e2eShipmentId;
    }


    public String getCarrier() {
        return carrier;
    }


    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }


    public String getSource() {
        return source;
    }


    public void setSource(String source) {
        this.source = source;
    }


    public String getReferenceType() {
        return referenceType;
    }


    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }


    public Long getReferenceId() {
        return referenceId;
    }


    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }


    public String getLegMode() {
        return legMode;
    }


    public void setLegMode(String legMode) {
        this.legMode = legMode;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public Date getSchedulePickupDate() {
        return schedulePickupDate;
    }


    public void setSchedulePickupDate(Date schedulePickupDate) {
        this.schedulePickupDate = schedulePickupDate;
    }


    public Date getScheduleDeliveryDate() {
        return scheduleDeliveryDate;
    }


    public void setScheduleDeliveryDate(Date scheduleDeliveryDate) {
        this.scheduleDeliveryDate = scheduleDeliveryDate;
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


    public Date getWorkOrderCreatedDate() {
        return workOrderCreatedDate;
    }


    public void setWorkOrderCreatedDate(Date workOrderCreatedDate) {
        this.workOrderCreatedDate = workOrderCreatedDate;
    }


    public String getOrigin_LocationId() {
        return origin_LocationId;
    }


    public void setOrigin_LocationId(String origin_LocationId) {
        this.origin_LocationId = origin_LocationId;
    }


    public String getOrigin() {
        return origin;
    }


    public void setOrigin(String origin) {
        this.origin = origin;
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


    public String getDestination_LocationId() {
        return destination_LocationId;
    }


    public void setDestination_LocationId(String destination_LocationId) {}


    public String getDestination() {
        return destination;
    }


    public void setDestination(String destination) {
        this.destination = destination;
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


    public String getConsignor_LocationId() {
        return consignor_LocationId;
    }


    public void setConsignor_LocationId(String consignor_LocationId) {
        this.consignor_LocationId = consignor_LocationId;
    }


    public String getConsignor() {
        return consignor;
    }


    public void setConsignor(String consignor) {
        this.consignor = consignor;
    }


    public String getConsignorAddress1() {
        return consignorAddress1;
    }


    public void setConsignorAddress1(String consignorAddress1) {
        this.consignorAddress1 = consignorAddress1;
    }


    public String getConsignorAddress2() {
        return consignorAddress2;
    }


    public void setConsignorAddress2(String consignorAddress2) {
        this.consignorAddress2 = consignorAddress2;
    }


    public String getConsignorCity() {
        return consignorCity;
    }


    public void setConsignorCity(String consignorCity) {
        this.consignorCity = consignorCity;
    }


    public String getConsignorState() {
        return consignorState;
    }


    public void setConsignorState(String consignorState) {
        this.consignorState = consignorState;
    }


    public String getConsignorZipcode() {
        return consignorZipcode;
    }


    public void setConsignorZipcode(String consignorZipcode) {
        this.consignorZipcode = consignorZipcode;
    }


    public String getConsignorCountry() {
        return consignorCountry;
    }


    public void setConsignorCountry(String consignorCountry) {
        this.consignorCountry = consignorCountry;
    }


    public String getConsignee_LocationId() {
        return consignee_LocationId;
    }


    public void setConsignee_LocationId(String consignee_LocationId) {
        this.consignee_LocationId = consignee_LocationId;
    }


    public String getConsignee() {
        return consignee;
    }


    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }


    public String getConsigneeAddress1() {
        return consigneeAddress1;
    }


    public void setConsigneeAddress1(String consigneeAddress1) {
        this.consigneeAddress1 = consigneeAddress1;
    }


    public String getConsigneeAddress2() {
        return consigneeAddress2;
    }


    public void setConsigneeAddress2(String consigneeAddress2) {
        this.consigneeAddress2 = consigneeAddress2;
    }


    public String getConsigneeCity() {
        return consigneeCity;
    }


    public void setConsigneeCity(String consigneeCity) {
        this.consigneeCity = consigneeCity;
    }


    public String getConsigneeState() {
        return consigneeState;
    }


    public void setConsigneeState(String consigneeState) {
        this.consigneeState = consigneeState;
    }


    public String getConsigneeZipcode() {
        return consigneeZipcode;
    }


    public void setConsigneeZipcode(String consigneeZipcode) {
        this.consigneeZipcode = consigneeZipcode;
    }


    public String getConsigneeCountry() {
        return consigneeCountry;
    }


    public void setConsigneeCountry(String consigneeCountry) {
        this.consigneeCountry = consigneeCountry;
    }


    public String getBillTo_LocationId() {
        return billTo_LocationId;
    }


    public void setBillTo_LocationId(String billTo_LocationId) {
        this.billTo_LocationId = billTo_LocationId;
    }


    public String getBillTo() {
        return billTo;
    }


    public void setBillTo(String billTo) {
        this.billTo = billTo;
    }


    public String getBillToAddress1() {
        return billToAddress1;
    }


    public void setBillToAddress1(String billToAddress1) {
        this.billToAddress1 = billToAddress1;
    }


    public String getBillToAddress2() {
        return billToAddress2;
    }


    public void setBillToAddress2(String billToAddress2) {
        this.billToAddress2 = billToAddress2;
    }


    public String getBillToCity() {
        return billToCity;
    }


    public void setBillToCity(String billToCity) {
        this.billToCity = billToCity;
    }


    public String getBillToState() {
        return billToState;
    }


    public void setBillToState(String billToState) {
        this.billToState = billToState;
    }


    public String getBillToZipcode() {
        return billToZipcode;
    }


    public void setBillToZipcode(String billToZipcode) {
        this.billToZipcode = billToZipcode;
    }


    public String getBillToCountry() {
        return billToCountry;
    }


    public void setBillToCountry(String billToCountry) {
        this.billToCountry = billToCountry;
    }


    public String getShipTo_LocationId() {
        return shipTo_LocationId;
    }


    public void setShipTo_LocationId(String shipTo_LocationId) {
        this.shipTo_LocationId = shipTo_LocationId;
    }


    public String getShipperToAddress1() {
        return shipperToAddress1;
    }


    public void setShipperToAddress1(String shipperToAddress1) {
        this.shipperToAddress1 = shipperToAddress1;
    }


    public String getShipperToAddress2() {
        return shipperToAddress2;
    }


    public void setShipperToAddress2(String shipperToAddress2) {
        this.shipperToAddress2 = shipperToAddress2;
    }


    public String getShipperToCity() {
        return shipperToCity;
    }


    public void setShipperToCity(String shipperToCity) {
        this.shipperToCity = shipperToCity;
    }


    public String getShipperToState() {
        return shipperToState;
    }


    public void setShipperToState(String shipperToState) {
        this.shipperToState = shipperToState;
    }


    public String getShipperToZipcode() {
        return shipperToZipcode;
    }


    public void setShipperToZipcode(String shipperToZipcode) {
        this.shipperToZipcode = shipperToZipcode;
    }


    public String getShipperToCountry() {
        return shipperToCountry;
    }


    public void setShipperToCountry(String shipperToCountry) {
        this.shipperToCountry = shipperToCountry;
    }


    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }


    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }


    public Double getTotalWeight() {
        return totalWeight;
    }


    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }


    public String getWeightUom() {
        return weightUom;
    }


    public void setWeightUom(String weightUom) {
        this.weightUom = weightUom;
    }


    public Double getTotalVolume() {
        return totalVolume;
    }


    public void setTotalVolume(Double totalVolume) {
        this.totalVolume = totalVolume;
    }


    public String getVolumeUom() {
        return volumeUom;
    }


    public void setVolumeUom(String volumeUom) {
        this.volumeUom = volumeUom;
    }


    public Double getPackageCount() {
        return packageCount;
    }


    public void setPackageCount(Double packageCount) {
        this.packageCount = packageCount;
    }


    public String getPackageType() {
        return packageType;
    }


    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }


    public Double getTotalDeclaredValue() {
        return totalDeclaredValue;
    }


    public void setTotalDeclaredValue(Double totalDeclaredValue) {
        this.totalDeclaredValue = totalDeclaredValue;
    }


    public String getIsHazmat() {
        return isHazmat;
    }


    public void setIsHazmat(String isHazmat) {
        this.isHazmat = isHazmat;
    }


    public String getIsTempControlled() {
        return isTempControlled;
    }


    public void setIsTempControlled(String isTempControlled) {
        this.isTempControlled = isTempControlled;
    }


    public String getPodNumber() {
        return podNumber;
    }


    public void setPodNumber(String podNumber) {
        this.podNumber = podNumber;
    }


    public Date getPodDate() {
        return podDate;
    }


    public void setPodDate(Date podDate) {
        this.podDate = podDate;
    }


    public String getUnitId() {
        return unitId;
    }


    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }


    public String getCreatedBy() {
        return createdBy;
    }


    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


    public Date getCreatedDate() {
        return createdDate;
    }


    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    public String getLastModifiedBy() {
        return lastModifiedBy;
    }


    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }


    public Date getLastModified() {
        return lastModified;
    }


    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
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


    public String getOrganizationCode() {
        return organizationCode;
    }


    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getFreightDescription() {
        return freightDescription;
    }


    public void setFreightDescription(String freightDescription) {
        this.freightDescription = freightDescription;
    }


    public Set<ShipmentReferenceRequest> getShipmentReferences() {
        return shipmentReferences;
    }


    public void setShipmentReferences(Set<ShipmentReferenceRequest> shipmentReferences) {
        this.shipmentReferences = shipmentReferences;
    }


    public void addShipmentReference(String refType, String refValue) {
        ShipmentReferenceRequest shipmentReferenceRequestObj = new ShipmentReferenceRequest();
        shipmentReferenceRequestObj.setShipmentRefType(refType);
        shipmentReferenceRequestObj.setShipmentRefValue(refValue);
        if (this.shipmentReferences == null)
            this.shipmentReferences = new HashSet<>();
        this.shipmentReferences.add(shipmentReferenceRequestObj);
    }


    public String getShipmentNumber() {
        if (this.shipmentReferences == null||this.shipmentReferences.isEmpty())
            return null;
        String shipmentNumber = null;
        for (ShipmentReferenceRequest ref : this.shipmentReferences) {
            if (ShipmentReferenceType.SHIPMENTREFNUM.name().equals(ref.getShipmentRefType())) {
                shipmentNumber=ref.getShipmentRefValue();
                break;
            }
        }
        if(ShipmentUtil.isNullString(shipmentNumber)) {
            shipmentNumber=getWayBillNumber();
        }
        if(ShipmentUtil.isNullString(shipmentNumber)) {
            ShipmentReferenceRequest firstRef=getShipmentReferences().iterator().next();
            shipmentNumber=firstRef.getShipmentRefValue();
        }

        return shipmentNumber;
    }

    public String getWayBillNumber() {
        String wayBillNumber=null;
        for (ShipmentReferenceRequest shipmentRef : this.shipmentReferences) {
             if (ShipmentReferenceType.isBillNumberType(shipmentRef.getShipmentRefType())) {
                 wayBillNumber=shipmentRef.getShipmentRefValue();
                 break;
            } 
        }
        return wayBillNumber;
    }

    public List<String> getBolNumbers() {
        List<String> bolNumbers = new ArrayList<>();
        if (this.shipmentReferences == null)
            return bolNumbers;
        for (ShipmentReferenceRequest ref : this.shipmentReferences) {
            if (ShipmentReferenceType.isBillNumberType(ref.getShipmentRefType())) {
                bolNumbers.add(ref.getShipmentRefValue());
            }
        }
        return bolNumbers;
    }


    public ShipmentStopListRequest getShipmentStopListRequest() {
        return shipmentStopListRequest;
    }


    public void setShipmentStopListRequest(ShipmentStopListRequest shipmentStopListRequest) {
        this.shipmentStopListRequest = shipmentStopListRequest;
    }


    public String getMasterShipmentReferenceNumber() {
        return masterShipmentReferenceNumber;
    }


    public void setMasterShipmentReferenceNumber(String masterShipmentReferenceNumber) {
        this.masterShipmentReferenceNumber = masterShipmentReferenceNumber;
    }


  

    public String getOriginatorCode() {
        return originatorCode;
    }


    public void setOriginatorCode(String originatorCode) {
        this.originatorCode = originatorCode;
    }


    public Long getReceiverId() {
        return receiverId;
    }


    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
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


    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public String getVendorNumber() {
        return vendorNumber;
    }


    public void setVendorNumber(String vendorNumber) {
        this.vendorNumber = vendorNumber;
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


    public String getPaymentMethodIndicator() {
        return paymentMethodIndicator;
    }


    public void setPaymentMethodIndicator(String paymentMethodIndicator) {
        this.paymentMethodIndicator = paymentMethodIndicator;
    }


    public Double getFreightCharge() {
        return freightCharge;
    }


    public void setFreightCharge(Double freightCharge) {
        this.freightCharge = freightCharge;
    }


    public Double getFuelSurchargeTotal() {
        return fuelSurchargeTotal;
    }


    public void setFuelSurchargeTotal(Double fuelSurchargeTotal) {
        this.fuelSurchargeTotal = fuelSurchargeTotal;
    }


    public String getCurrency() {
        return currency;
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public Date getRespondBy() {
        return respondBy;
    }


    public void setRespondBy(Date respondBy) {
        this.respondBy = respondBy;
    }


    public String getOriginUnlocode() {
        return originUnlocode;
    }


    public void setOriginUnlocode(String originUnlocode) {
        this.originUnlocode = originUnlocode;
    }


    public String getDestinationUnlocode() {
        return destinationUnlocode;
    }


    public void setDestinationUnlocode(String destinationUnlocode) {
        this.destinationUnlocode = destinationUnlocode;
    }



}
