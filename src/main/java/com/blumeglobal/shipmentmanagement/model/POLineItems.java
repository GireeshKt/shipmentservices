package com.blumeglobal.shipmentmanagement.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PO_LINE_ITEMS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class POLineItems implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	@Column(name = "PO_ITEM_ID", nullable = false, updatable = false)
	private Long poItemId;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "PURCHASE_ORDER_ID", nullable = false)	
	private PurchaseOrder poLineItemsRef;
	
	@Column(name = "QUANTITY")
	private Long quantity;
	
	@Column(name = "UNIT_OF_MEASUREMENT",length=30)
	private String unitOfMeasurement;
	
	@Column(name = "PRICE")
	private double price;
	
	@Column(name = "DELETE_FLAG")
	private String deleteFlag;
	
	@Column(name = "SKU_ID") 
	private String skuNumber;
	
	@Column(name = "SKU_DESCRIPTION")
	private String skuDescription;
	
	@Column(name = "COMMITTED_QUANTITY")
	private Long commitedQuantity;
	
	@Column(name = "DUE_DATE")
	private Date dueDate;
	
	@Column(name = "PROMISED_DATE")
	private Date promisedDate;
	
	@Column(name = "SHIP_TO_LOCATION_CODE", length = 20)
	private String shipToLocationCode;
	
	@Column(name = "SHIP_TO_COMPANY", length = 120)
	private String shipToCompany;
	
	@Column(name = "SHIP_TO_ADDRESS")
	private String shipToAddress;
	
	@Column(name = "SHIP_TO_CITY")
	private String shipToCity;
	
	@Column(name = "SHIP_TO_STATE")
	private String shipToState;
	
	@Column(name = "SHIP_TO_ZIP", length = 10)
	private String shipToZip;
	
	@Column(name = "SHIP_TO_COUNTRY")
	private String shipToCountry;
	
	@Column(name = "CREATED_ON",updatable = false)
	private Timestamp createdOn;

	@Column(name = "CREATED_BY", length = 180)
	private String createdBy;
	
	@Column(name = "UPDATED_ON")
	private Timestamp updatedOn;

	@Column(name = "UPDATED_BY", length = 180)
	private String updatedBy;
	
	@Column(name = "LINE_ITEM_STATUS")
	private String lineItemStatus;
	
	@OneToMany(mappedBy = "poTransactionRef", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<POTransactions> poTransactions;
	
	public POLineItems() {
		
	}

	public Long getPoItemId() {
		return poItemId;
	}

	public void setPoItemId(Long poItemId) {
		this.poItemId = poItemId;
	}
	
	public PurchaseOrder getPoLineItemsRef() {
		return poLineItemsRef;
	}

	public void setPoLineItemsRef(PurchaseOrder poLineItemsRef) {
		this.poLineItemsRef = poLineItemsRef;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getSkuNumber() {
		return skuNumber;
	}

	public void setSkuNumber(String skuNumber) {
		this.skuNumber = skuNumber;
	}

	public String getSkuDescription() {
		return skuDescription;
	}

	public void setSkuDescription(String skuDescription) {
		this.skuDescription = skuDescription;
	}

	public Long getCommitedQuantity() {
		return commitedQuantity;
	}

	public void setCommitedQuantity(Long commitedQuantity) {
		this.commitedQuantity = commitedQuantity;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPromisedDate() {
		return promisedDate;
	}

	public void setPromisedDate(Date promisedDate) {
		this.promisedDate = promisedDate;
	}

	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
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

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Set<POTransactions> getPoTransactions() {
		return poTransactions;
	}

	public void setPoTransactions(Set<POTransactions> poTransactions) {
		this.poTransactions = poTransactions;
	}

	public String getLineItemStatus() {
		return lineItemStatus;
	}

	public void setLineItemStatus(String lineItemStatus) {
		this.lineItemStatus = lineItemStatus;
	}

}
