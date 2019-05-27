package com.blumeglobal.shipmentmanagement.request;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

public class UploadE2EShipmentRequest {
	
	private static final long serialVersionUID = 1L;
			
	
	private String shipmentNumber;
	
	private String status;
	
	private String asnNumber;
	
	private String shipmentNumberPart;
	
	private String shipmentNumberTotal;
	
	private String shipmentOrderNumber;
	
	private String numberOfUniqItems;
	
	private String shipmentType;
	
	private String consignor;
	
	private String consignee;
	
	private String shippingCompany;
	
	private String origin;
	
	private String originAddress1;
	
	private String originAddress2;
	
	private String originCity;
	
	private String originState;
	
	private String originZipCode;
	
	@Size(max = 2, message = "originCountry: originCountry must be maximum of 2 characters")
	private String originCountry;
	
	private String originatorCode;
	
	private String originatorName;
	
	private String destination;
	
	private String destinationAddress1;
	
	private String destinationAddress2;
	
	private String destinationCity;
	
	private String destinationState;
	
	private String destinationZipCode;
	
	@Size(max = 2, message = "destinationCountry: destinationCountry must be maximum of 2 characters")
	private String destinationCountry;
	
	private String expectdPickupDate;
	
	private String expectedDeliveryDate;
	
	private String totalWeight;
	
	private String weigthUom;
	
	private String totalVolume;
	
	private String volumeUom;
	
	private String packageCount;
	
	private String packageType;
	
	private String totalDeclaredValue;
	
	private String primaryMode;
	
	private String comments;
	
	
	@Size(max = 1, message = "isHazmat: isHazmat must be eigther 'Y' / 'N' characters")
	private String isHazmat;
	
	@Size(max = 1, message = "isTemperatureController: isTemperatureController must be 'Y' / 'N' characters")
	private String isTemperatureController;
	
	private String sourceEntry;
	
	@Size(max = 1, message = "isExport: isExport must be 'Y' / 'N' characters")
	private String isExport;
	
	private String podDate;
	
	private String unitId;
	
	private String billToName;
	
	private String billToAddress;
	
	private String billToCity;
	
	private String billToState;
	
	private String billToCountry;
	
	private String shipperToAddress;
	
	private String shipperToCity;
	
	private String shipperToState;
	
	private String shipperToCountry;
	
	private String consigneeToAddress;
	
	private String consineeToCity;
	
	private String consingeeToState;
	
	private String consigneeToCountry;
	
	private String createdBy;
	
	private String createdDateTime;
	
	private String updatedBy;
	
	private String updatedDateTime;
	
	private String transportationMethod;
	
	private String wayBillNumber;
	
	private String trackingNumber;
	
	
	public UploadE2EShipmentRequest() {
		
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

	public String getShipmentNumberPart() {
		return shipmentNumberPart;
	}

	public void setShipmentNumberPart(String shipmentNumberPart) {
		this.shipmentNumberPart = shipmentNumberPart;
	}

	public String getShipmentNumberTotal() {
		return shipmentNumberTotal;
	}

	public void setShipmentNumberTotal(String shipmentNumberTotal) {
		this.shipmentNumberTotal = shipmentNumberTotal;
	}

	public String getShipmentOrderNumber() {
		return shipmentOrderNumber;
	}

	public void setShipmentOrderNumber(String shipmentOrderNumber) {
		this.shipmentOrderNumber = shipmentOrderNumber;
	}

	public String getNumberOfUniqItems() {
		return numberOfUniqItems;
	}

	public void setNumberOfUniqItems(String numberOfUniqItems) {
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

	public String getExpectdPickupDate() {
		return expectdPickupDate;
	}

	public void setExpectdPickupDate(String expectdPickupDate) {
		this.expectdPickupDate = expectdPickupDate;
	}

	public String getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(String expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	public String getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(String totalWeight) {
		this.totalWeight = totalWeight;
	}

	public String getWeigthUom() {
		return weigthUom;
	}

	public void setWeigthUom(String weigthUom) {
		this.weigthUom = weigthUom;
	}

	public String getTotalVolume() {
		return totalVolume;
	}

	public void setTotalVolume(String totalVolume) {
		this.totalVolume = totalVolume;
	}

	public String getVolumeUom() {
		return volumeUom;
	}

	public void setVolumeUom(String volumeUom) {
		this.volumeUom = volumeUom;
	}

	public String getPackageCount() {
		return packageCount;
	}

	public void setPackageCount(String packageCount) {
		this.packageCount = packageCount;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getTotalDeclaredValue() {
		return totalDeclaredValue;
	}

	public void setTotalDeclaredValue(String totalDeclaredValue) {
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

	public String getPodDate() {
		return podDate;
	}

	public void setPodDate(String podDate) {
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

	public String getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(String updatedDateTime) {
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

	

}
