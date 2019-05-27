package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
import java.util.Date;

public class ShipmentFreightDetailResponse  implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long shipmentId;//e2e_shipmentid
	private String bookingNumber;	
	private String freightDescription;
	private String shipmentReferenceNumber;
	private String status;
	private String consignee;
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
	private String purchaseOrderNumber;
	private String skuNumber;
	private Date requestedDate;
	private Date promisedDate;
	private String logisticProvider;
	private String poBuyerName;
	private String asnNumber;
	private String orderStatus;
	private String unitId;
	private Long requestedQuantity; 
	private Long promisedQuantity;
	private Long poItemId;
	private ActiveShipmentResponse activeShipment;
	
	public ShipmentFreightDetailResponse(Long shipmentId, String bookingNumber, String freightDescription,
			String shipmentReferenceNumber,String status, String consignee, String origin, String originAddress1,
			String originAddress2, String originCity, String originState, String originZipCode, String originCountry,
			String destination, String destinationAddress1, String destinationAddress2, String destinationCity,
			String destinationState, String destinationZipCode, String destinationCountry, String purchaseOrderNumber,
			String skuNumber, Date requestedDate, Date promisedDate, String logisticProvider, String poBuyerName,
			String asnNumber, String orderStatus, String unitId, Long requestedQuantity, Long promisedQuantity, Long poItemId) {
		super();
		this.shipmentId = shipmentId;
		this.bookingNumber = bookingNumber;
		this.freightDescription = freightDescription;
		this.shipmentReferenceNumber = shipmentReferenceNumber;
		this.status = status;
		this.consignee = consignee;
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
		this.purchaseOrderNumber = purchaseOrderNumber;
		this.skuNumber = skuNumber;
		this.requestedDate = requestedDate;
		this.promisedDate = promisedDate;
		this.logisticProvider = logisticProvider;
		this.poBuyerName = poBuyerName;
		this.asnNumber = asnNumber;
		this.orderStatus = orderStatus;
		this.unitId = unitId;
		this.requestedQuantity = requestedQuantity;
		this.promisedQuantity = promisedQuantity;
		this.poItemId = poItemId;
	}
	public Long getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(Long shipmentId) {
		this.shipmentId = shipmentId;
	}
	public String getBookingNumber() {
		return bookingNumber;
	}
	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}
	public String getFreightDescription() {
		return freightDescription;
	}
	public void setFreightDescription(String freightDescription) {
		this.freightDescription = freightDescription;
	}
	public String getShipmentReferenceNumber() {
		return shipmentReferenceNumber;
	}
	public void setShipmentReferenceNumber(String shipmentReferenceNumber) {
		this.shipmentReferenceNumber = shipmentReferenceNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
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
	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}
	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}
	public String getSkuNumber() {
		return skuNumber;
	}
	public void setSkuNumber(String skuNumber) {
		this.skuNumber = skuNumber;
	}
	public Date getRequestedDate() {
		return requestedDate;
	}
	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}
	public Date getPromisedDate() {
		return promisedDate;
	}
	public void setPromisedDate(Date promisedDate) {
		this.promisedDate = promisedDate;
	}
	public String getLogisticProvider() {
		return logisticProvider;
	}
	public void setLogisticProvider(String logisticProvider) {
		this.logisticProvider = logisticProvider;
	}
	public String getPoBuyerName() {
		return poBuyerName;
	}
	public void setPoBuyerName(String poBuyerName) {
		this.poBuyerName = poBuyerName;
	}
	public String getAsnNumber() {
		return asnNumber;
	}
	public void setAsnNumber(String asnNumber) {
		this.asnNumber = asnNumber;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public Long getRequestedQuantity() {
		return requestedQuantity;
	}
	public void setRequestedQuantity(Long requestedQuantity) {
		this.requestedQuantity = requestedQuantity;
	}
	public Long getPromisedQuantity() {
		return promisedQuantity;
	}
	public void setPromisedQuantity(Long promisedQuantity) {
		this.promisedQuantity = promisedQuantity;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Long getPoItemId() {
		return poItemId;
	}
	public void setPoItemId(Long poItemId) {
		this.poItemId = poItemId;
	}
	public ActiveShipmentResponse getActiveShipment() {
		return activeShipment;
	}
	public void setActiveShipment(ActiveShipmentResponse activeShipment) {
		this.activeShipment = activeShipment;
	}
	
}
