package com.blumeglobal.shipmentmanagement.model;

import java.util.Date;
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

@Entity
@Table(name = "E2E_SHIPMENT")
public class E2EShipment implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "SHIPMENT_ID", nullable = false, updatable = false)
    private Long shipmentId;

    @Column(name = "SHIPMENT_NUMBER")
    private String shipmentNumber;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "SHIPMENT_NUMBER_PART")
    private Long shipmentNumberPart;

    @Column(name = "SHIPMENT_NUMBER_TOTAL")
    private Long shipmentNumberTotal;

    @Column(name = "SHIPMENT_ORDER_NUMBER")
    private String shipmentOrderNumber;

    @Column(name = "NUMBER_OF_UNIQUE_ITEMS")
    private Long numberOfUniqItems;

    @Column(name = "SHIPMENT_TYPE")
    private String shipmentType;

    @Column(name = "CONSIGNOR")
    private String consignor;

    @Column(name = "CONSIGNEE")
    private String consignee;

    @Column(name = "SHIPPING_COMPANY")
    private String shippingCompany;

    @Column(name = "ORIGIN")
    private String origin;

    @Column(name = "ORIGIN_ADDRESS1")
    private String originAddress1;

    @Column(name = "ORIGIN_ADDRESS2")
    private String originAddress2;

    @Column(name = "ORIGIN_CITY")
    private String originCity;

    @Column(name = "ORIGIN_STATE")
    private String originState;

    @Column(name = "ORIGIN_ZIP_CODE")
    private String originZipCode;

    @Column(name = "ORIGIN_COUNTRY")
    private String originCountry;

    @Column(name = "ORIGINATOR_CODE")
    private String originatorCode;

    @Column(name = "ORIGINATOR_NAME")
    private String originatorName;

    @Column(name = "DESTINATION")
    private String destination;

    @Column(name = "DESTINATION_ADDRESS1")
    private String destinationAddress1;

    @Column(name = "DESTINATION_ADDRESS2")
    private String destinationAddress2;

    @Column(name = "DESTINATION_CITY")
    private String destinationCity;

    @Column(name = "DESTINATION_STATE")
    private String destinationState;

    @Column(name = "DESTINATION_ZIP_CODE")
    private String destinationZipCode;

    @Column(name = "DESTINATION_COUNTRY")
    private String destinationCountry;

    @Column(name = "EXPECTED_PICKUP_DATE")
    private Date expectdPickupDate;

    @Column(name = "EXPECTED_DELIVERY_DATE")
    private Date expectedDeliveryDate;

    @Column(name = "TOTAL_WEIGHT")
    private Long totalWeight;

    @Column(name = "WEIGHT_UOM")
    private String weigthUom;

    @Column(name = "TOTAL_VOLUME")
    private Long totalVolume;

    @Column(name = "VOLUME_UOM")
    private String volumeUom;

    @Column(name = "PACKAGE_COUNT")
    private Long packageCount;

    @Column(name = "PACKAGE_TYPE")
    private String packageType;

    @Column(name = "TOTAL_DECLARED_VALUE")
    private Long totalDeclaredValue;

    @Column(name = "PRIMARY_MODE")
    private String primaryMode;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "IS_HAZMAT")
    private String isHazmat;

    @Column(name = "IS_TEMPERATURE_CONTROLLER")
    private String isTemperatureController;

    @Column(name = "SOURCE_ENTRY")
    private String sourceEntry;

    @Column(name = "IS_EXPORT")
    private String isExport;

    @Column(name = "POD_NUMBER")
    private String podNumber;

    @Column(name = "POD_DATE")
    private Date podDate;

    @Column(name = "UNIT_ID")
    private String unitId;

    @Column(name = "BILL_TO_NAME")
    private String billToName;

    @Column(name = "BILL_TO_ADDRESS")
    private String billToAddress;

    @Column(name = "BILL_TO_CITY")
    private String billToCity;

    @Column(name = "BILL_TO_STATE")
    private String billToState;

    @Column(name = "BILL_TO_COUNTRY")
    private String billToCountry;

    @Column(name = "SHIPPER_TO_ADDRESS")
    private String shipperToAddress;

    @Column(name = "SHIPPER_TO_CITY")
    private String shipperToCity;

    @Column(name = "SHIPPER_TO_STATE")
    private String shipperToState;

    @Column(name = "SHIPPER_TO_COUNTRY")
    private String shipperToCountry;

    @Column(name = "CONSIGNEE_TO_ADDRESS")
    private String consigneeToAddress;

    @Column(name = "CONSIGNEE_TO_CITY")
    private String consineeToCity;

    @Column(name = "CONSIGNEE_TO_STATE")
    private String consingeeToState;

    @Column(name = "CONSIGNEE_TO_COUNTRY")
    private String consigneeToCountry;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DATE_TIME")
    private Date createdDateTime;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATED_DATE_TIME")
    private Date updatedDateTime;

    @Column(name = "ORGANIZATION_CODE")
    private String organizationCode;

    @Column(name = "TRANSPORTATION_METHOD")
    private String transportationMethod;

    @Column(name = "WAY_BILL_NUMBER")
    private String wayBillNumber;

    @Column(name = "TRACKING_NUMBER")
    private String trackingNumber;

    @Column(name = "DELETE_FLAG")
    private String deleteFlag;

    @Column(name = "STATUSID")
    private Long statusId;

    @Column(name = "MASTERSHIPMENTREFERENCENUMBER")
    private String masterShipmentReferenceNumber;

    @Column(name = "ORIGINUNLOCODE", length = 20)
    private String originUnlocode;

    @Column(name = "DESTINATIONUNLOCODE", length = 20)
    private String destinationUnlocode;

    
    @OneToMany(mappedBy = "freightLineItemsRef", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<FreightLineItems> freightLineItems;


    public E2EShipment() {

    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getShipmentNumber() {
        return shipmentNumber;
    }

    public void setShipmentNumber(String shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public Long getShipmentNumberPart() {
        return shipmentNumberPart;
    }

    public void setShipmentNumberPart(Long shipmentNumberPart) {
        this.shipmentNumberPart = shipmentNumberPart;
    }

    public Long getShipmentNumberTotal() {
        return shipmentNumberTotal;
    }

    public void setShipmentNumberTotal(Long shipmentNumberTotal) {
        this.shipmentNumberTotal = shipmentNumberTotal;
    }

    public String getShipmentOrderNumber() {
        return shipmentOrderNumber;
    }

    public void setShipmentOrderNumber(String shipmentOrderNumber) {
        this.shipmentOrderNumber = shipmentOrderNumber;
    }

    public Long getNumberOfUniqItems() {
        return numberOfUniqItems;
    }

    public void setNumberOfUniqItems(Long numberOfUniqItems) {
        this.numberOfUniqItems = numberOfUniqItems;
    }

    public String getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(String shipmentType) {
        this.shipmentType = shipmentType;
    }

    public String getConsignor() {
        return consignor;
    }

    public void setConsignor(String consignor) {
        this.consignor = consignor;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getShippingCompany() {
        return shippingCompany;
    }

    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
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

    public String getOriginZipCode() {
        return originZipCode;
    }

    public void setOriginZipCode(String originZipCode) {
        this.originZipCode = originZipCode;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
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

    public String getDestinationZipCode() {
        return destinationZipCode;
    }

    public void setDestinationZipCode(String destinationZipCode) {
        this.destinationZipCode = destinationZipCode;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public Date getExpectdPickupDate() {
        return expectdPickupDate;
    }

    public void setExpectdPickupDate(Date expectdPickupDate) {
        this.expectdPickupDate = expectdPickupDate;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public Long getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Long totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getWeigthUom() {
        return weigthUom;
    }

    public void setWeigthUom(String weigthUom) {
        this.weigthUom = weigthUom;
    }

    public Long getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Long totalVolume) {
        this.totalVolume = totalVolume;
    }

    public String getVolumeUom() {
        return volumeUom;
    }

    public void setVolumeUom(String volumeUom) {
        this.volumeUom = volumeUom;
    }

    public Long getPackageCount() {
        return packageCount;
    }

    public void setPackageCount(Long packageCount) {
        this.packageCount = packageCount;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public Long getTotalDeclaredValue() {
        return totalDeclaredValue;
    }

    public void setTotalDeclaredValue(Long totalDeclaredValue) {
        this.totalDeclaredValue = totalDeclaredValue;
    }

    public String getPrimaryMode() {
        return primaryMode;
    }

    public void setPrimaryMode(String primaryMode) {
        this.primaryMode = primaryMode;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getIsHazmat() {
        return isHazmat;
    }

    public void setIsHazmat(String isHazmat) {
        this.isHazmat = isHazmat;
    }

    public String getIsTemperatureController() {
        return isTemperatureController;
    }

    public void setIsTemperatureController(String isTemperatureController) {
        this.isTemperatureController = isTemperatureController;
    }

    public String getSourceEntry() {
        return sourceEntry;
    }

    public void setSourceEntry(String sourceEntry) {
        this.sourceEntry = sourceEntry;
    }

    public String getIsExport() {
        return isExport;
    }

    public void setIsExport(String isExport) {
        this.isExport = isExport;
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

    public String getBillToName() {
        return billToName;
    }

    public void setBillToName(String billToName) {
        this.billToName = billToName;
    }

    public String getBillToAddress() {
        return billToAddress;
    }

    public void setBillToAddress(String billToAddress) {
        this.billToAddress = billToAddress;
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

    public String getBillToCountry() {
        return billToCountry;
    }

    public void setBillToCountry(String billToCountry) {
        this.billToCountry = billToCountry;
    }

    public String getShipperToAddress() {
        return shipperToAddress;
    }

    public void setShipperToAddress(String shipperToAddress) {
        this.shipperToAddress = shipperToAddress;
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

    public String getShipperToCountry() {
        return shipperToCountry;
    }

    public void setShipperToCountry(String shipperToCountry) {
        this.shipperToCountry = shipperToCountry;
    }

    public String getConsigneeToAddress() {
        return consigneeToAddress;
    }

    public void setConsigneeToAddress(String consigneeToAddress) {
        this.consigneeToAddress = consigneeToAddress;
    }

    public String getConsineeToCity() {
        return consineeToCity;
    }

    public void setConsineeToCity(String consineeToCity) {
        this.consineeToCity = consineeToCity;
    }

    public String getConsingeeToState() {
        return consingeeToState;
    }

    public void setConsingeeToState(String consingeeToState) {
        this.consingeeToState = consingeeToState;
    }

    public String getConsigneeToCountry() {
        return consigneeToCountry;
    }

    public void setConsigneeToCountry(String consigneeToCountry) {
        this.consigneeToCountry = consigneeToCountry;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(Date updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }



    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getTransportationMethod() {
        return transportationMethod;
    }

    public void setTransportationMethod(String transportationMethod) {
        this.transportationMethod = transportationMethod;
    }

    public String getWayBillNumber() {
        return wayBillNumber;
    }

    public void setWayBillNumber(String wayBillNumber) {
        this.wayBillNumber = wayBillNumber;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }



    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Set<FreightLineItems> getFreightLineItems() {
        return freightLineItems;
    }

    public void setFreightLineItems(Set<FreightLineItems> freightLineItems) {
        this.freightLineItems = freightLineItems;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getMasterShipmentReferenceNumber() {
        return masterShipmentReferenceNumber;
    }

    public void setMasterShipmentReferenceNumber(String masterShipmentReferenceNumber) {
        this.masterShipmentReferenceNumber = masterShipmentReferenceNumber;
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
