package com.blumeglobal.shipmentmanagement.request;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;



import io.swagger.annotations.ApiModelProperty;

public class E2EShipmentAPIRequest implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
			
	@ApiModelProperty(notes="shipmentId is an auto generated number incrementing with every upload", required=true)
	private Long shipmentId;

	@ApiModelProperty(notes="shipmentNumber is actual shipment number picked from excel upload", required=true)
	private String shipmentNumber;

	@ApiModelProperty(notes="status is in which state the shipment is present", required=true)
	private String status;

	@ApiModelProperty(notes="asnNumber is Advanced Shipment Number for the particular shipment, picked from excel upload", required=true)
	private String asnNumber;

	@ApiModelProperty(notes="in case shipment is sent in multiple parts, this will give the part of shipments", required=true)
	private Long shipmentNumberPart;

	@ApiModelProperty(notes="total number of shipments", required=true)
	private Long shipmentNumberTotal;
	
	@ApiModelProperty(notes="purschase order number for a shipment", required=true)
	private String shipmentOrderNumber;
	
	@ApiModelProperty(notes="total number of unique SKUs present in shipment ", required=true)
	private Long numberOfUniqItems;
	
	private String shipmentType;
	
	@ApiModelProperty(notes="party sending the shipment", required=true)
	private String consignor;
	
	@ApiModelProperty(notes="party receiving the shipment", required=true)
	private String consignee;
	
	@ApiModelProperty(notes="shippingCompany is the organization name of the Shipper", required=true)
	private String shippingCompany;
	
	@ApiModelProperty(notes="origin country name from where shipment starts", required=true)
	private String origin;

	@ApiModelProperty(notes="origin address from where shipment starts", required=true)
	private String originAddress1;

	@ApiModelProperty(notes="extended origin address from where shipment starts", required=true)
	private String originAddress2;

	@ApiModelProperty(notes="origin city from where shipment starts", required=true)
	private String originCity;

	@ApiModelProperty(notes="origin state from where shipment starts", required=true)
	private String originState;

	@ApiModelProperty(notes="origin zipcode from where shipment starts", required=true)
	private String originZipCode;

	@ApiModelProperty(notes="origin country code from where shipment starts", required=true)
	@Size(max = 2, message = "originCountry: originCountry must be maximum of 2 characters")
	private String originCountry;
	
	@ApiModelProperty(notes="the code that identifies a unique originator", required=true)
	private String originatorCode;
	
	@ApiModelProperty(notes="name of the originator", required=true)
	private String originatorName;

	@ApiModelProperty(notes="destination country name to where shipment reaches", required=true)
	private String destination;

	@ApiModelProperty(notes="destination country address to where shipment reaches", required=true)
	private String destinationAddress1;

	@ApiModelProperty(notes="destination country extended address to where shipment reaches", required=true)
	private String destinationAddress2;

	@ApiModelProperty(notes="destination city to where shipment reaches", required=true)
	private String destinationCity;

	@ApiModelProperty(notes="destination state to where shipment reaches", required=true)
	private String destinationState;

	@ApiModelProperty(notes="destination country zipcode to where shipment reaches", required=true)
	private String destinationZipCode;

	@ApiModelProperty(notes="destination country code to where shipment reaches", required=true)
	@Size(max = 2, message = "destinationCountry: destinationCountry must be maximum of 2 characters")
	private String destinationCountry;

	@ApiModelProperty(notes="date when shipment is expected to be picked up", required=true)
	private Date expectdPickupDate;

	@ApiModelProperty(notes="date when shipment is expected to be delivered", required=true)
	private Date expectedDeliveryDate;

	@ApiModelProperty(notes="weight of the total shipment", required=true)
	private Long totalWeight;

	@ApiModelProperty(notes="Unit of Mesaurement of the shipment weight", required=true)
	private String weigthUom;

	@ApiModelProperty(notes="volume of total shipment", required=true)
	private Long totalVolume;

	@ApiModelProperty(notes="Unit of Mesaurement of the shipment volume", required=true)
	private String volumeUom;

	@ApiModelProperty(notes="count of packages in the shipment", required=true)
	private Long packageCount;

	@ApiModelProperty(notes="type of package in the shipment", required=true)
	private String packageType;
	
	@ApiModelProperty(notes="toal value of shipment in USD", required=true)
	private Long totalDeclaredValue;

	@ApiModelProperty(notes="primary mode of shipment", required=true)
	private String primaryMode;

	@ApiModelProperty(notes="comments, if any", required=true)
	private String comments;
	
	@Size(max = 1, message = "isHazmat: isHazmat must be eigther 'Y' / 'N' characters")
	private String isHazmat;

	@ApiModelProperty(notes="whether shipment needs to be temperature controlled", required=true)
	@Size(max = 1, message = "isTemperatureController: isTemperatureController must be 'Y' / 'N' characters")
	private String isTemperatureController;
	
	@ApiModelProperty(notes="", required=true)
	private String sourceEntry;

	@ApiModelProperty(notes="whether shipment needs to be exported", required=true)
	@Size(max = 1, message = "isExport: isExport must be 'Y' / 'N' characters")
	private String isExport;
	
	@ApiModelProperty(notes="Date on which proof of delivery is obtained", required=false)
	private Date podDate;
	
	@ApiModelProperty(notes="ID of the container or ULD", required=true)
	private String unitId;

	@ApiModelProperty(notes="Shipment bill to be on whose name", required=true)
	private String billToName;

	@ApiModelProperty(notes="Shipment bill to be on which address", required=true)
	private String billToAddress;

	@ApiModelProperty(notes="Shipment bill to be on which city", required=true)
	private String billToCity;

	@ApiModelProperty(notes="Shipment bill to be on which state", required=true)
	private String billToState;

	@ApiModelProperty(notes="Shipment bill to be on which country", required=true)
	private String billToCountry;

	@ApiModelProperty(notes="Detailed address of shipper", required=true)
	private String shipperToAddress;

	@ApiModelProperty(notes="City of shipper", required=true)
	private String shipperToCity;

	@ApiModelProperty(notes="State of shipper", required=true)
	private String shipperToState;

	@ApiModelProperty(notes="Country of shipper", required=true)
	private String shipperToCountry;
	
	@ApiModelProperty(notes="address of consignee", required=true)
	private String consigneeToAddress;
	
	@ApiModelProperty(notes="city of consignee", required=true)
	private String consineeToCity;
	
	@ApiModelProperty(notes="State of consignee", required=true)
	private String consingeeToState;
	
	@ApiModelProperty(notes="Country of consignee", required=true)
	private String consigneeToCountry;

	@ApiModelProperty(notes="Shipment to be created by which date", required=true)
	private String createdBy;

	@ApiModelProperty(notes="Shipment to be created by which date & time", required=true)
	private Date createdDateTime;

	@ApiModelProperty(notes="Shipment to be updated by which date", required=true)
	private String updatedBy;

	@ApiModelProperty(notes="Shipment to be updated by which date & time", required=true)
	private Date updatedDateTime;

	@ApiModelProperty(notes="Transportation method of the shipment", required=true)
	private String transportationMethod;

	@ApiModelProperty(notes="Way Bill Number of the shipment", required=true)
	private String wayBillNumber;

	@ApiModelProperty(notes="Tracking number of the shipment", required=true)
	private String trackingNumber;
	
	
	
	@OneToMany(mappedBy = "freightLineItemsRef", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<FreightLineItemsAPIRequest> freightLineItems;
	
	public E2EShipmentAPIRequest() {
		
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

	

	public String getAsnNumber() {
		return asnNumber;
	}

	public void setAsnNumber(String asnNumber) {
		this.asnNumber = asnNumber;
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

	
	
	public Set<FreightLineItemsAPIRequest> getFreightLineItems() {
		return freightLineItems;
	}

	public void setFreightLineItems(Set<FreightLineItemsAPIRequest> freightLineItems) {
		this.freightLineItems = freightLineItems;
	}
	
}
