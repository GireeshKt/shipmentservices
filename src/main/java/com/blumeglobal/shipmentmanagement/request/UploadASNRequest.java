package com.blumeglobal.shipmentmanagement.request;

import javax.validation.constraints.Size;

public class UploadASNRequest implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String asnId;
	
	private String asnNumber;
	
	@Size(max = 20, message = "purchaseOrderNumber: purchaseOrderNumber must be maximum of 20 characters")
	private String purchaseOrderNumber;
	
	private String totalGrossWeight;
	
	@Size(max = 30, message = "destination: destination must be maximum of 30 characters")
	private String destination;
	
	@Size(max = 30, message = "shippingCompany: shippingCompany must be maximum of 30 characters")
	private String shippingCompany;
	
	@Size(max = 30, message = "buyerName: buyerName must be maximum of 30 characters")
	private String buyerName;
	
	@Size(max = 30, message = "shippingTo: shippingTo must be maximum of 30 characters")
	private String shippingTo;
	
	@Size(max = 150, message = "pickUpAddress: pickUpAddress must be maximum of 150 characters")
	private String pickUpAddress;
	
	@Size(max = 150, message = "deliveryAddress: deliveryAddress must be maximum of 150 characters")
	private String deliveryAddress;
	
	private String expectedPickupDate;
	
	private String deliveryDate;
	
	@Size(max = 15, message = "trackingNumber: trackingNumber must be maximum of 15 characters")
	private String trackingNumber;
	
	private String createdOn;
	
	@Size(max = 25, message = "createdBy: createdBy must be maximum of 25 characters")
	private String createdBy;
	
	private String updatedOn;
	
	@Size(max = 25, message = "updatedBy: updatedBy must be maximum of 25 characters")
	private String updatedBy;
	
	private String shipmentNumberPart;
	
	private String shipmentNumberTotal;
	
	
	@Size(max = 10, message = "weightQualifier: weightQualifier must be maximum of 10 characters")
	private String weightQualifier;
	
	@Size(max = 10, message = "basisForMeasurement: basisForMeasurement must be maximum of 10 characters")
	private String basisForMeasurement;
	
	@Size(max = 30, message = "transportationMethod: transportationMethod must be maximum of 30 characters")
	private String transportationMethod;
	
	@Size(max = 80, message = "routing: routing must be maximum of 80 characters")
	private String routing;
	
	@Size(max = 15, message = "routingSequenceCode: routingSequenceCode must be maximum of 15 characters")
	private String routingSequenceCode;
	
	@Size(max = 10, message = "shippingCountryCode: shippingCountryCode must be maximum of 10 characters")
	private String shippingCountryCode;
	
	@Size(max = 40, message = "cityName: cityName must be maximum of 40 characters")
	private String cityName;
	
	@Size(max = 40, message = "stateCode: stateCode must be maximum of 40 characters")
	private String stateCode;

	
	@Size(max = 10, message = "shippingPostalCode: shippingPostalCode must be maximum of 10 characters")
	private String shippingPostalCode;
	
	private String numberOfLineItems;
	
	@Size(max = 10, message = "refIdentificationQualifier: refIdentificationQualifier must be maximum of 10 characters")
	private String refIdentificationQualifier;
	
	private String referenceIdentification;
	
	@Size(max = 10, message = "entityIdentifierCode: entityIdentifierCode must be maximum of 10 characters")
	private String entityIdentifierCode;
	
	@Size(max = 15, message = "storeIdentificationCode: storeIdentificationCode must be maximum of 15 characters")
	private String storeIdentificationCode;
	
	@Size(max = 20, message = "containerID: containerID must be maximum of 20 characters")
	private String containerId;
	
	private String shipmentId;
	
	private String wayBillNumber;
	
	private String asnStatus;
	
	private String organizationCode;
	
	public String getOrganizationCode() {
		return organizationCode;
	}
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
	public UploadASNRequest() {
		
	}
	public String getAsnId() {
		return asnId;
	}
	public void setAsnId(String asnId) {
		this.asnId = asnId;
	}
	public String getAsnNumber() {
		return asnNumber;
	}
	public void setAsnNumber(String asnNumber) {
		this.asnNumber = asnNumber;
	}
	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}
	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}
	public String getTotalGrossWeight() {
		return totalGrossWeight;
	}
	public void setTotalGrossWeight(String totalGrossWeight) {
		this.totalGrossWeight = totalGrossWeight;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getShippingCompany() {
		return shippingCompany;
	}
	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getShippingTo() {
		return shippingTo;
	}
	public void setShippingTo(String shippingTo) {
		this.shippingTo = shippingTo;
	}
	public String getPickUpAddress() {
		return pickUpAddress;
	}
	public void setPickUpAddress(String pickUpAddress) {
		this.pickUpAddress = pickUpAddress;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getExpectedPickupDate() {
		return expectedPickupDate;
	}
	public void setExpectedPickupDate(String expectedPickupDate) {
		this.expectedPickupDate = expectedPickupDate;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
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
	public String getWeightQualifier() {
		return weightQualifier;
	}
	public void setWeightQualifier(String weightQualifier) {
		this.weightQualifier = weightQualifier;
	}
	public String getBasisForMeasurement() {
		return basisForMeasurement;
	}
	public void setBasisForMeasurement(String basisForMeasurement) {
		this.basisForMeasurement = basisForMeasurement;
	}
	public String getTransportationMethod() {
		return transportationMethod;
	}
	public void setTransportationMethod(String transportationMethod) {
		this.transportationMethod = transportationMethod;
	}
	public String getRouting() {
		return routing;
	}
	public void setRouting(String routing) {
		this.routing = routing;
	}
	public String getRoutingSequenceCode() {
		return routingSequenceCode;
	}
	public void setRoutingSequenceCode(String routingSequenceCode) {
		this.routingSequenceCode = routingSequenceCode;
	}
	public String getShippingCountryCode() {
		return shippingCountryCode;
	}
	public void setShippingCountryCode(String shippingCountryCode) {
		this.shippingCountryCode = shippingCountryCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getShippingPostalCode() {
		return shippingPostalCode;
	}
	public void setShippingPostalCode(String shippingPostalCode) {
		this.shippingPostalCode = shippingPostalCode;
	}
	public String getNumberOfLineItems() {
		return numberOfLineItems;
	}
	public void setNumberOfLineItems(String numberOfLineItems) {
		this.numberOfLineItems = numberOfLineItems;
	}
	public String getRefIdentificationQualifier() {
		return refIdentificationQualifier;
	}
	public void setRefIdentificationQualifier(String refIdentificationQualifier) {
		this.refIdentificationQualifier = refIdentificationQualifier;
	}
	public String getReferenceIdentification() {
		return referenceIdentification;
	}
	public void setReferenceIdentification(String referenceIdentification) {
		this.referenceIdentification = referenceIdentification;
	}
	public String getEntityIdentifierCode() {
		return entityIdentifierCode;
	}
	public void setEntityIdentifierCode(String entityIdentifierCode) {
		this.entityIdentifierCode = entityIdentifierCode;
	}
	public String getStoreIdentificationCode() {
		return storeIdentificationCode;
	}
	public void setStoreIdentificationCode(String storeIdentificationCode) {
		this.storeIdentificationCode = storeIdentificationCode;
	}
	public String getContainerId() {
		return containerId;
	}
	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}
	public String getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}
	public String getWayBillNumber() {
		return wayBillNumber;
	}
	public void setWayBillNumber(String wayBillNumber) {
		this.wayBillNumber = wayBillNumber;
	}
	public String getAsnStatus() {
		return asnStatus;
	}
	public void setAsnStatus(String asnStatus) {
		this.asnStatus = asnStatus;
	}
	


}
