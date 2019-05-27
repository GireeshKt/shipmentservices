package com.blumeglobal.shipmentmanagement.model;


import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.blumeglobal.shipmentmanagement.enums.ShipmentReferenceType;



@Entity
@Table(name = "SHIPMENT")
public class Shipment implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "SHIPMENTID", nullable = false, updatable = false)
    private Long shipmentId;


    @Column(name = "E2E_SHIPMENTID")
    private Long e2eShipmentId;


    @Column(name = "REFERENCETYPE", length = 20)
    private String referenceType;


    @Column(name = "REFERENCEID")
    private Long referenceId;


    @Column(name = "LEGMODE", length = 20)
    private String legMode;


    @Column(name = "STATUS", length = 50)
    private String status;


    @Column(name = "SCHEDULEPICKUPDATE")
    private Date schedulePickupDate;


    @Column(name = "SCHEDULEDELIVERYDATE")
    private Date scheduleDeliveryDate;


    @Column(name = "EXPORTCUTOFFDATE")
    private Date exportCutOffDate;


    @Column(name = "LASTFREEDATE")
    private Date lastFreeDate;


    @Column(name = "WORKORDERCREATEDDATE")
    private Date workOrderCreatedDate;


    @Column(name = "ORIGIN_LOCATIONID")
    private String origin_LocationId;


    @Column(name = "ORIGIN", length = 100)
    private String origin;


    @Column(name = "ORIGINADDRESS1", length = 200)
    private String originAddress1;


    @Column(name = "ORIGINADDRESS2", length = 200)
    private String originAddress2;


    @Column(name = "ORIGINCITY", length = 50)
    private String originCity;


    @Column(name = "ORIGINSTATE", length = 50)
    private String originState;


    @Column(name = "ORIGINZIPCODE", length = 20)
    private String originZipcode;


    @Column(name = "ORIGINCOUNTRY", length = 50)
    private String originCountry;


    @Column(name = "DESTINATION_LOCATIONID")
    private String destination_LocationId;


    @Column(name = "DESTINATION", length = 100)
    private String destination;


    @Column(name = "DESTINATIONADDRESS1", length = 200)
    private String destinationAddress1;


    @Column(name = "DESTINATIONADDRESS2", length = 200)
    private String destinationAddress2;


    @Column(name = "DESTINATIONCITY", length = 50)
    private String destinationCity;


    @Column(name = "DESTINATIONSTATE", length = 50)
    private String destinationState;


    @Column(name = "DESTINATIONZIPCODE", length = 20)
    private String destinationZipcode;


    @Column(name = "DESTINATIONCOUNTRY", length = 50)
    private String destinationCountry;


    @Column(name = "CONSIGNOR_LOCATIONID")
    private String consignor_LocationId;


    @Column(name = "CONSIGNOR", length = 100)
    private String consignor;


    @Column(name = "CONSIGNORADDRESS1", length = 200)
    private String consignorAddress1;


    @Column(name = "CONSIGNORADDRESS2", length = 200)
    private String consignorAddress2;


    @Column(name = "CONSIGNORCITY", length = 50)
    private String consignorCity;


    @Column(name = "CONSIGNORSTATE", length = 50)
    private String consignorState;


    @Column(name = "CONSIGNORZIPCODE", length = 20)
    private String consignorZipcode;


    @Column(name = "CONSIGNORCOUNTRY", length = 50)
    private String consignorCountry;


    @Column(name = "CONSIGNEE_LOCATIONID")
    private String consignee_LocationId;


    @Column(name = "CONSIGNEE", length = 100)
    private String consignee;


    @Column(name = "CONSIGNEEADDRESS1", length = 200)
    private String consigneeAddress1;


    @Column(name = "CONSIGNEEADDRESS2", length = 200)
    private String consigneeAddress2;


    @Column(name = "CONSIGNEECITY", length = 50)
    private String consigneeCity;


    @Column(name = "CONSIGNEESTATE", length = 50)
    private String consigneeState;


    @Column(name = "CONSIGNEEZIPCODE", length = 20)
    private String consigneeZipcode;


    @Column(name = "CONSIGNEECOUNTRY", length = 50)
    private String consigneeCountry;


    @Column(name = "BILLTO_LOCATIONID")
    private String billTo_LocationId;


    @Column(name = "BILLTO", length = 100)
    private String billTo;


    @Column(name = "BILLTOADDRESS1", length = 200)
    private String billToAddress1;


    @Column(name = "BILLTOADDRESS2", length = 200)
    private String billToAddress2;


    @Column(name = "BILLTOCITY", length = 50)
    private String billToCity;


    @Column(name = "BILLTOSTATE", length = 50)
    private String billToState;


    @Column(name = "BILLTOZIPCODE", length = 20)
    private String billToZipcode;


    @Column(name = "BILLTOCOUNTRY", length = 50)
    private String billToCountry;


    @Column(name = "SHIPTO_LOCATIONID")
    private String shipTo_LocationId;


    @Column(name = "SHIPPERTOADDRESS1", length = 200)
    private String shipperToAddress1;


    @Column(name = "SHIPPERTOADDRESS2", length = 200)
    private String shipperToAddress2;


    @Column(name = "SHIPPERTOCITY", length = 50)
    private String shipperToCity;


    @Column(name = "SHIPPERTOSTATE", length = 50)
    private String shipperToState;


    @Column(name = "SHIPPERTOZIPCODE", length = 20)
    private String shipperToZipcode;


    @Column(name = "SHIPPERTOCOUNTRY", length = 50)
    private String shipperToCountry;


    @Column(name = "EXPECTEDDELIVERYDATE")
    private Date expectedDeliveryDate;


    @Column(name = "TOTALWEIGHT")
    private Double totalWeight;


    @Column(name = "WEIGHTUOM", length = 20)
    private String weightUom;


    @Column(name = "TOTALVOLUME")
    private Double totalVolume;


    @Column(name = "VOLUMEUOM", length = 20)
    private String volumeUom;


    @Column(name = "PACKAGECOUNT")
    private Double packageCount;


    @Column(name = "PACKAGETYPE", length = 20)
    private String packageType;


    @Column(name = "TOTALDECLAREDVALUE")
    private Double totalDeclaredValue;

    @Type(type = "yes_no")
    @Column(name = "ISHAZMAT")
    private boolean isHazmat;

    @Type(type = "yes_no")
    @Column(name = "ISTEMPCONTROLLED")
    private boolean isTempControlled;


    @Column(name = "PODNUMBER", length = 100)
    private String podNumber;


    @Column(name = "PODDATE")
    private Date podDate;


    @Column(name = "UNITID", length = 100)
    private String unitId;


    @Column(name = "CREATEDBY", length = 100)
    private String createdBy;


    @Column(name = "CREATEDATE")
    private Date createdDate;


    @Column(name = "LASTMODIFIEDBY", length = 100)
    private String lastModifiedBy;


    @Column(name = "LASTMODIFIED")
    private Date lastModified;

    @Column(name = "CARRIER", length = 50)
    private String carrier;

    @Column(name = "SOURCE", length = 20)
    private String source;

    @Column(name = "MLESTONESTATE", length = 50)
    private String mileStoneState;

    @Column(name = "VESSEL", length = 50)
    private String vessel;

    @Column(name = "VOYAGE", length = 50)
    private String voyage;

    @Column(name = "ORGANIZATIONCODE", length = 12)
    private String organizationCode;
    @Column(name = "FREIGHTDESCRIPTION", length = 200)
    private String freightDescription;
    @Column(name = "MASTERSHIPMENTREFERENCENUMBER", length = 50)
    private String masterShipmentReferenceNumber;
    @Column(name = "ORIGINATORCODE")
    private String originatorCode;
    @Column(name = "RECEIVERID")
    private Long receiverId;
    @Column(name = "ORIGINATORNAME", length = 200)
    private String originatorName;
    @Column(name = "RECEIVERNAME", length = 200)
    private String receiverName;
    @Column(name = "CATEGORY", length = 200)
    private String category;
    @Column(name = "VENDORNUMBER", length = 200)
    private String vendorNumber;
    @Column(name = "PORTOFLOADING", length = 200)
    private String portOfLoading;
    @Column(name = "PORTOFDISCHARGE", length = 200)
    private String portOfDischarge;
    @Column(name = "PAYMENTMETHODINDICATOR", length = 200)
    private String paymentMethodIndicator;
    @Column(name = "STATUSID")
    private Long statusId;
    @Column(name = "FREIGHTCHARGE")
    private Double freightCharge;
    @Column(name = "FUELSURCHARGETOTAL")
    private Double fuelSurchargeTotal;
    @Column(name = "CURRENCY", length = 20)
    private String currency;
    @Column(name = "RESPONDBY")
    private Date respondBy;
    @Column(name = "ORIGINUNLOCODE", length = 20)
    private String originUnlocode;
    @Column(name = "DESTINATIONUNLOCODE", length = 20)
    private String destinationUnlocode;
    
    

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Stop> stops;

    public String getMileStoneState() {
        return mileStoneState;
    }


    public void setMileStoneState(String mileStoneState) {
        this.mileStoneState = mileStoneState;
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


    @OneToMany(mappedBy = "shipmentReferencesLink", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ShipmentReferences> shipmentReferences;

    public Shipment() {

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


    public void setDestination_LocationId(String destination_LocationId) {
        this.destination_LocationId = destination_LocationId;
    }


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



    public boolean isHazmat() {
        return isHazmat;
    }


    public void setHazmat(boolean isHazmat) {
        this.isHazmat = isHazmat;
    }


    public boolean isTempControlled() {
        return isTempControlled;
    }


    public void setTempControlled(boolean isTempControlled) {
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


    public Set<ShipmentReferences> getShipmentReferences() {
        return shipmentReferences;
    }


    public void setShipmentReferences(Set<ShipmentReferences> shipmentReferences) {
        this.shipmentReferences = shipmentReferences;
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


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((shipmentId == null) ? 0 : shipmentId.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Shipment other = (Shipment) obj;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        return true;
    }


    public String getReferenceValue(ShipmentReferenceType refType) {
        if (this.getShipmentReferences() == null)
            return null;
        for (ShipmentReferences refr : this.getShipmentReferences()) {
            if (refr.getShipmentRefType().equals(refType.name())) {
                return refr.getShipmentRefValue();
            }
        }
        return null;
    }

    /*
     * 
     * public String getOriginLocationIdAll() { return ShipmentUtil.getLocationAll(this.originAddress1,
     * this.originAddress2, this.originCity, this.originState, this.originZipcode, this.originCountry); }
     * 
     * 
     * public String getDestinationLocationIdAll() { return ShipmentUtil.getLocationAll(this.destinationAddress1,
     * this.destinationAddress2, this.destinationCity, this.destinationState, this.destinationZipcode,
     * this.destinationCountry); }
     * 
     * public String getOriginLocationId() { return ShipmentUtil.getLocation(this.originAddress1, this.originAddress2,
     * this.originCity, this.originState, this.originZipcode, this.originCountry); }
     * 
     * 
     * public String getDestinationLocationId() { return ShipmentUtil.getLocation(this.destinationAddress1,
     * this.destinationAddress2, this.destinationCity, this.destinationState, this.destinationZipcode,
     * this.destinationCountry); }
     */
    public String getOriginLocationId() {
        return origin_LocationId;
    }


    public String getDestinationLocationId() {
        return destination_LocationId;
    }

    public List<Stop> getStops() {
        return stops;
    }


    public void setStops(List<Stop> stops) {
        this.stops = stops;
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


    public Long getStatusId() {
        return statusId;
    }


    public void setStatusId(Long statusId) {
        this.statusId = statusId;
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
