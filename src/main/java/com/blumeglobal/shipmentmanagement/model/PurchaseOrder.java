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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PURCHASE_ORDER")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PurchaseOrder implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	@Column(name = "PURCHASE_ORDER_ID", nullable = false, updatable = false)
	private Long purchaseOrderId;

	@Column(name = "PURCHASE_ORDER_NUMBER", length = 20)
	private String purchaseOrderNumber;
	
	@Column(name = "PO_REFERENCE_ID", length = 12)
	private String poReferenceId;

	@Column(name = "PO_EXPENDITURE_CODE_ID", length = 10)
	private String poExpenditureCodeId;

	@Column(name = "PO_DATE")
	private Timestamp poDate;

	@Column(name = "PO_REQUISTIONED_BY", length = 180)
	private String poRequistionedBy;

	@Column(name = "PO_RECEIVE_DATE")
	private Date poReceivedDate;

	@Column(name = "PO_PROMISE_DATE")
	private Date poPromisedDate;
 
	@Column(name = "PO_TAX")
	private Long poTax;

	@Column(name = "PO_FREIGHT_HANDLING", length = 20)
	private String poFreightHandling;

	@Column(name = "PO_CANCELLED")
	private Long poCancelled;

	@Column(name = "PO_COMPLETED")
	private Long poCompleted;

	@Column(name = "CREATED_ON",updatable = false)
	private Timestamp createdOn;

	@Column(name = "CREATED_BY", length = 180)
	private String createdBy;

	@Column(name = "UPDATED_ON")
	private Timestamp updatedOn;

	@Column(name = "UPDATED_BY", length = 180)
	private String updatedBy;

	@Column(name = "DELETE_FLAG")
	private String deleteFlag;

	@Column(name = "PO_STATUS")
	private String poStatus;

	@Column(name = "LOGISTIC_PROVIDER", length = 180)
	private String logisticProvider;

	@Column(name = "LEAD_TIME", length = 12)
	private Long leadTime;

	//extra columns to be added
	@Column(name = "VENDOR_NAME", length = 180)
	private String vendorName;
	
	@Column(name = "VENDOR_ADDRESS")
	private String vendorAddress;
	
	@Column(name = "VENDOR_CITY")
	private String vendorCity;
	
	@Column(name = "VENDOR_STATE")
	private String vendorState;
	
	@Column(name = "VENDOR_COUNTRY")
	private String vendorCountry;
	
	@Column(name = "VENDOR_ZIPCODE", length = 8)
	private String vendorZip;
	
	@Column(name = "VENDOR_DUNS")
	private String vendorDuns;
	
	@Column(name = "BUYER_NAME", length = 180)
	private String buyerName;
	
	@Column(name = "BUYER_DUNS")
	private String buyerDuns;
	
	@Column(name = "BILLING_ADDRESS")
	private String billingAddress;
	
	@Column(name = "NOTES")
	private String poNotes;
	
	@Column(name = "ORIGIN")
	private String origin;
	
	@Column(name = "DESTINATION")
	private String destination;
	
	@Column(name = "ORGANIZATION_CODE")
	private String organizationCode;

	@OneToMany(mappedBy = "poLineItemsRef", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<POLineItems> poLineItems;
	
	public PurchaseOrder() {

	}

	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	public String getPoReferenceId() {
		return poReferenceId;
	}

	public void setPoReferenceId(String poReferenceId) {
		this.poReferenceId = poReferenceId;
	}

	public String getPoExpenditureCodeId() {
		return poExpenditureCodeId;
	}

	public void setPoExpenditureCodeId(String poExpenditureCodeId) {
		this.poExpenditureCodeId = poExpenditureCodeId;
	}

	public Timestamp getPoDate() {
		return poDate;
	}

	public void setPoDate(Timestamp poDate) {
		this.poDate = poDate;
	}

	public String getPoRequistionedBy() {
		return poRequistionedBy;
	}

	public void setPoRequistionedBy(String poRequistionedBy) {
		this.poRequistionedBy = poRequistionedBy;
	}

	public Date getPoReceivedDate() {
		return poReceivedDate;
	}

	public void setPoReceivedDate(Date poReceivedDate) {
		this.poReceivedDate = poReceivedDate;
	}

	public Date getPoPromisedDate() {
		return poPromisedDate;
	}

	public void setPoPromisedDate(Date poPromisedDate) {
		this.poPromisedDate = poPromisedDate;
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

	public Long getPoCancelled() {
		return poCancelled;
	}

	public void setPoCancelled(Long poCancelled) {
		this.poCancelled = poCancelled;
	}

	public Long getPoCompleted() {
		return poCompleted;
	}

	public void setPoCompleted(Long poCompleted) {
		this.poCompleted = poCompleted;
	}

	public Set<POLineItems> getPoLineItems() {
		return poLineItems;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public void setPoLineItems(Set<POLineItems> poLineItems) {
		this.poLineItems = poLineItems;
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

	public String getPoStatus() {
		return poStatus;
	}

	public void setPoStatus(String poStatus) {
		this.poStatus = poStatus;
	}

	public String getLogisticProvider() {
		return logisticProvider;
	}

	public void setLogisticProvider(String logisticProvider) {
		this.logisticProvider = logisticProvider;
	}

	public Long getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(Long leadTime) {
		this.leadTime = leadTime;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorAddress() {
		return vendorAddress;
	}

	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}

	public String getVendorCity() {
		return vendorCity;
	}

	public void setVendorCity(String vendorCity) {
		this.vendorCity = vendorCity;
	}

	public String getVendorState() {
		return vendorState;
	}

	public void setVendorState(String vendorState) {
		this.vendorState = vendorState;
	}

	public String getVendorZip() {
		return vendorZip;
	}

	public void setVendorZip(String vendorZip) {
		this.vendorZip = vendorZip;
	}

	public String getVendorDuns() {
		return vendorDuns;
	}

	public void setVendorDuns(String vendorDuns) {
		this.vendorDuns = vendorDuns;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerDuns() {
		return buyerDuns;
	}

	public void setBuyerDuns(String buyerDuns) {
		this.buyerDuns = buyerDuns;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getVendorCountry() {
		return vendorCountry;
	}

	public void setVendorCountry(String vendorCountry) {
		this.vendorCountry = vendorCountry;
	}

	public String getPoNotes() {
		return poNotes;
	}

	public void setPoNotes(String poNotes) {
		this.poNotes = poNotes;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
	
}
