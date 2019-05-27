package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


public class POListAPIResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String purchaseOrderNumber;
	private Long purchaseOrderId;
	private String logisticProvider;
	private String origin;
	private Date poReceiveDate;
	private Date poPromiseDate;
	private String poVendorName;
	private String poVendorDuns;
	private String poVendorAddress;
	private String poVendorCity;
	private String poVendorState;
	private String poVendorZipcode;
	private String poVendorCountry;
	private String poBuyerName;
	private String poBuyerDuns;
	private String poBillingAddress;
	private String poReferenceId;
	private String poExpenditureCode;
	private String poRequistionedBy;
	private Long poTax;
	private String poFreightHandling;
	private String poStatus;
	private Long poLeadTime;
	private Timestamp poDate;
	private String poNotes;
	private String poDestination;
	
	//po line items
	private Long poItemId;
	private String skuNumber;
	private String shipToCity;
	private String shipToState;
	private Date promisedDate;
	private String lineItemStatus;
	private Long quantity;
	private String skuDescription;
	private Long committedQuantity;
	private Date dueDate;
	private String unitOfMeasurement;
	private double price;
	private String shipToLocationCode;
	private String shipToCompany;
	private String shipToAddress;
	private String shipToZip;
	private String shipToCountry;
	
	public POListAPIResponse() {
		
	}

	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public String getLogisticProvider() {
		return logisticProvider;
	}

	public void setLogisticProvider(String logisticProvider) {
		this.logisticProvider = logisticProvider;
	}

	public Long getPoItemId() {
		return poItemId;
	}

	public void setPoItemId(Long poItemId) {
		this.poItemId = poItemId;
	}

	public String getSkuNumber() {
		return skuNumber;
	}

	public void setSkuNumber(String skuNumber) {
		this.skuNumber = skuNumber;
	}

	public String getShipToCity() {
		return shipToCity;
	}

	public void setShipToCity(String shipToCity) {
		this.shipToCity = shipToCity;
	}

	public String getShipToState() {
		return shipToState;
	}

	public void setShipToState(String shipToState) {
		this.shipToState = shipToState;
	}

	public Date getPromisedDate() {
		return promisedDate;
	}

	public void setPromisedDate(Date promisedDate) {
		this.promisedDate = promisedDate;
	}

	public String getLineItemStatus() {
		return lineItemStatus;
	}

	public void setLineItemStatus(String lineItemStatus) {
		this.lineItemStatus = lineItemStatus;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Date getPoReceiveDate() {
		return poReceiveDate;
	}

	public void setPoReceiveDate(Date poReceiveDate) {
		this.poReceiveDate = poReceiveDate;
	}

	public Date getPoPromiseDate() {
		return poPromiseDate;
	}

	public void setPoPromiseDate(Date poPromiseDate) {
		this.poPromiseDate = poPromiseDate;
	}

	public String getPoVendorName() {
		return poVendorName;
	}

	public void setPoVendorName(String poVendorName) {
		this.poVendorName = poVendorName;
	}

	public String getPoVendorDuns() {
		return poVendorDuns;
	}

	public void setPoVendorDuns(String poVendorDuns) {
		this.poVendorDuns = poVendorDuns;
	}

	public String getPoVendorAddress() {
		return poVendorAddress;
	}

	public void setPoVendorAddress(String poVendorAddress) {
		this.poVendorAddress = poVendorAddress;
	}

	public String getPoVendorCity() {
		return poVendorCity;
	}

	public void setPoVendorCity(String poVendorCity) {
		this.poVendorCity = poVendorCity;
	}

	public String getPoVendorState() {
		return poVendorState;
	}

	public void setPoVendorState(String poVendorState) {
		this.poVendorState = poVendorState;
	}

	public String getPoVendorZipcode() {
		return poVendorZipcode;
	}

	public void setPoVendorZipcode(String poVendorZipcode) {
		this.poVendorZipcode = poVendorZipcode;
	}

	public String getPoVendorCountry() {
		return poVendorCountry;
	}

	public void setPoVendorCountry(String poVendorCountry) {
		this.poVendorCountry = poVendorCountry;
	}

	public String getPoBuyerName() {
		return poBuyerName;
	}

	public void setPoBuyerName(String poBuyerName) {
		this.poBuyerName = poBuyerName;
	}

	public String getPoBuyerDuns() {
		return poBuyerDuns;
	}

	public void setPoBuyerDuns(String poBuyerDuns) {
		this.poBuyerDuns = poBuyerDuns;
	}

	public String getPoBillingAddress() {
		return poBillingAddress;
	}

	public void setPoBillingAddress(String poBillingAddress) {
		this.poBillingAddress = poBillingAddress;
	}

	public String getPoReferenceId() {
		return poReferenceId;
	}

	public void setPoReferenceId(String poReferenceId) {
		this.poReferenceId = poReferenceId;
	}

	public String getPoExpenditureCode() {
		return poExpenditureCode;
	}

	public void setPoExpenditureCode(String poExpenditureCode) {
		this.poExpenditureCode = poExpenditureCode;
	}

	public String getPoRequistionedBy() {
		return poRequistionedBy;
	}

	public void setPoRequistionedBy(String poRequistionedBy) {
		this.poRequistionedBy = poRequistionedBy;
	}

	public Long getPoTax() {
		return poTax;
	}

	public void setPoTax(Long poTax) {
		this.poTax = poTax;
	}

	public String getPoFreightHandling() {
		return poFreightHandling;
	}

	public void setPoFreightHandling(String poFreightHandling) {
		this.poFreightHandling = poFreightHandling;
	}

	public String getPoStatus() {
		return poStatus;
	}

	public void setPoStatus(String poStatus) {
		this.poStatus = poStatus;
	}

	public Long getPoLeadTime() {
		return poLeadTime;
	}

	public void setPoLeadTime(Long poLeadTime) {
		this.poLeadTime = poLeadTime;
	}

	public Timestamp getPoDate() {
		return poDate;
	}

	public void setPoDate(Timestamp poDate) {
		this.poDate = poDate;
	}

	public String getPoNotes() {
		return poNotes;
	}

	public void setPoNotes(String poNotes) {
		this.poNotes = poNotes;
	}

	public String getPoDestination() {
		return poDestination;
	}

	public void setPoDestination(String poDestination) {
		this.poDestination = poDestination;
	}

	public String getSkuDescription() {
		return skuDescription;
	}

	public void setSkuDescription(String skuDescription) {
		this.skuDescription = skuDescription;
	}

	public Long getCommittedQuantity() {
		return committedQuantity;
	}

	public void setCommittedQuantity(Long committedQuantity) {
		this.committedQuantity = committedQuantity;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getShipToLocationCode() {
		return shipToLocationCode;
	}

	public void setShipToLocationCode(String shipToLocationCode) {
		this.shipToLocationCode = shipToLocationCode;
	}

	public String getShipToCompany() {
		return shipToCompany;
	}

	public void setShipToCompany(String shipToCompany) {
		this.shipToCompany = shipToCompany;
	}

	public String getShipToAddress() {
		return shipToAddress;
	}

	public void setShipToAddress(String shipToAddress) {
		this.shipToAddress = shipToAddress;
	}

	public String getShipToZip() {
		return shipToZip;
	}

	public void setShipToZip(String shipToZip) {
		this.shipToZip = shipToZip;
	}

	public String getShipToCountry() {
		return shipToCountry;
	}

	public void setShipToCountry(String shipToCountry) {
		this.shipToCountry = shipToCountry;
	}
	
}
