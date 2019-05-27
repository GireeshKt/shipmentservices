package com.blumeglobal.shipmentmanagement.response;

import java.sql.Timestamp;
import java.util.Date;

public class ShipmentLogsDetailsResponse {

	private String shipmentNumber;
	private String unitId;
	private Long itemQuantity;
	private Date promisedDate;	
	private String billToName;
	private String transportationMethod;
	private String status;
	private String shippingCompany;
	private String wayBillNumber;
	private String asnNumber;
	private String trackingNumber;
	private Timestamp expectedPickupDate;
	private String origin;
	private String originAddress1;
	private String originAddress2;
	private String originCity;
	private String originState;
	private String originZipCode;
	private String originCountry;
	private String destination;
	private String destinationAddress1;
	private String destinationAddress2;
	private String destinationCity;
	private String destinationState;
	private String destinationZipCode;
	private String destinationCountry;
	private Long totalWeight;
	private Long shipmentNumberPart;
	private Long shipmentNumberItems;
	private Long numberOfLineItems;
	private String poNumber;
	private String itemNumber;
	private String itemDescription;
	private String itemQuantityUOM;
	private Long price;
	private String contentDescription;
	private String weightQualifier;

	public ShipmentLogsDetailsResponse() {

	}

	public ShipmentLogsDetailsResponse(String shipmentNumber, String unitId,Long itemQuantity,
			Date promisedDate, String billToName, String transportationMethod, String status,String shippingCompany,
			String wayBillNumber,String asnNumber,String trackingNumber,Timestamp expectedPickupDate,String origin,String originAddress1,
			String originAddress2,String originCity,String originState,String originZipCode,String originCountry,String destination,
			String destinationAddress1,String destinationAddress2,String destinationCity,String destinationState,String destinationZipCode,
			String destinationCountry,Long totalWeight,Long shipmentNumberPart,Long shipmentNumberItems,Long numberOfLineItems,
			String poNumber,String itemNumber,String itemDescription,String itemQuantityUOM,Long price,String contentDescription,
			String weightQualifier) {
		this.shipmentNumber = shipmentNumber;
		this.unitId = unitId;
		this.itemQuantity = itemQuantity;
		this.promisedDate = promisedDate;
		this.billToName = billToName;
		this.transportationMethod = transportationMethod;
		this.status = status;
		this.shippingCompany = shippingCompany;
		this.wayBillNumber = wayBillNumber;
		this.asnNumber = asnNumber;
		this.trackingNumber = trackingNumber;
		this.expectedPickupDate = expectedPickupDate;
		this.origin = origin;
		this.originAddress1 = originAddress1;
		this.originAddress2 = originAddress2;
		this.originCity = originCity;
		this.originState = originState;
		this.originZipCode = originZipCode;
		this.originCountry = originCountry;
		this.destination = destination;
		this.destinationAddress1 = destinationAddress1;
		this.destinationAddress2 = destinationAddress2;
		this.destinationCity = destinationCity;
		this.destinationState = destinationState;
		this.destinationZipCode = destinationZipCode;
		this.destinationCountry = destinationCountry;
		this.totalWeight = totalWeight;
		this.shipmentNumberPart = shipmentNumberPart;
		this.shipmentNumberItems = shipmentNumberItems;
		this.numberOfLineItems = numberOfLineItems;
		this.poNumber = poNumber;
		this.itemNumber = itemNumber;
		this.itemDescription = itemDescription;
		this.itemQuantityUOM = itemQuantityUOM;
		this.price = price;
		this.contentDescription = contentDescription;
		this.weightQualifier = weightQualifier;
	}

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public Long getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Long itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public Date getPromisedDate() {
		return promisedDate;
	}

	public void setPromisedDate(Date promisedDate) {
		this.promisedDate = promisedDate;
	}

	public String getBillToName() {
		return billToName;
	}

	public void setBillToName(String billToName) {
		this.billToName = billToName;
	}

	public String getTransportationMethod() {
		return transportationMethod;
	}

	public void setTransportationMethod(String transportationMethod) {
		this.transportationMethod = transportationMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getShippingCompany() {
		return shippingCompany;
	}

	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}

	public String getWayBillNumber() {
		return wayBillNumber;
	}

	public void setWayBillNumber(String wayBillNumber) {
		this.wayBillNumber = wayBillNumber;
	}

	public String getAsnNumber() {
		return asnNumber;
	}

	public void setAsnNumber(String asnNumber) {
		this.asnNumber = asnNumber;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public Timestamp getExpectedPickupDate() {
		return expectedPickupDate;
	}

	public void setExpectedPickupDate(Timestamp expectedPickupDate) {
		this.expectedPickupDate = expectedPickupDate;
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

	public Long getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Long totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Long getShipmentNumberPart() {
		return shipmentNumberPart;
	}

	public void setShipmentNumberPart(Long shipmentNumberPart) {
		this.shipmentNumberPart = shipmentNumberPart;
	}

	public Long getShipmentNumberItems() {
		return shipmentNumberItems;
	}

	public void setShipmentNumberItems(Long shipmentNumberItems) {
		this.shipmentNumberItems = shipmentNumberItems;
	}

	public Long getNumberOfLineItems() {
		return numberOfLineItems;
	}

	public void setNumberOfLineItems(Long numberOfLineItems) {
		this.numberOfLineItems = numberOfLineItems;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getItemQuantityUOM() {
		return itemQuantityUOM;
	}

	public void setItemQuantityUOM(String itemQuantityUOM) {
		this.itemQuantityUOM = itemQuantityUOM;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getContentDescription() {
		return contentDescription;
	}

	public void setContentDescription(String contentDescription) {
		this.contentDescription = contentDescription;
	}

	public String getWeightQualifier() {
		return weightQualifier;
	}

	public void setWeightQualifier(String weightQualifier) {
		this.weightQualifier = weightQualifier;
	}
	
}