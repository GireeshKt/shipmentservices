package com.blumeglobal.shipmentmanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FREIGHT_LINE_ITEMS")
public class FreightLineItems implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	@Column(name = "FREIGHT_LINE_ID", nullable = false,updatable = false)
	private Long freightLineId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SHIPMENT_ID", nullable = false)
	private AdvanceShipmentNotice freightLineItemsRef;
	
	@Column(name = "SHIPMENT_REFERENCE")
	private Long shipmentReference;
	
	@Column(name = "PO_NUMBER")
	private String poNumber;
	
	@Column(name = "LINE_ITEM_NO")
	private Long lineItemNo;
	
	@Column(name = "ITEM_NUMBER")
	private String itemNumber;
	
	@Column(name = "ITEM_DESCRIPTION")
	private String itemDescription;
	
	@Column(name = "ITEM_QUANTITY")
	private Long itemQuantity;
	
	@Column(name = "ITEM_QUANTITY_UOM")
	private String itemQuantityUom;
	
	@Column(name = "COMMODITY")
	private String commodity;
	
	@Column(name = "COMMODITY_CODE")
	private String commodityCode;
	
	@Column(name = "INNER_PACKAGE_TYPE")
	private String innerPackageType;
	
	@Column(name = "INNER_PACKAGE_QTY")
	private Long innerPackageQty;
	
	@Column(name = "PIECES_COUNT")
	private Long piecesCount;
	
	@Column(name = "WEIGHT_UOM")
	private Long weightUom;
	
	@Column(name = "GROSS_WEIGHT")
	private Long grossWeight;
	
	@Column(name = "NET_WEIGHT")
	private Long netWeight;
	
	@Column(name = "VOLUME_UOM")
	private Long volumeUom;
	
	@Column(name = "VOLUMME")
	private Long volume;
	
	@Column(name = "NET_VOLUME")
	private Long netVolume;
	
	@Column(name = "CONTENT_DESCRIPTION")
	private String contentDescription;
	
	@Column(name = "SOURCE_ENTRY")
	private String sourceEntry;
	
	@Column(name = "LADING_DESCRIPTION")
	private String ladingDescription;
	
	@Column(name = "COMMODITY_CODE_QUALIFIER")
	private String commodityCodeQualifier;
	
	@Column(name = "PACKAGING_CODE")
	private String packagingCode;
	
	@Column(name = "MARKS_AND_NUMBERS")
	private String marksAndNumbers;
	
	@Column(name = "COMPARTMENT_ID")
	private String compartmentId;
	
	@Column(name = "BILLED_QUANTITY")
	private Long billedQuantity;
	
	@Column(name = "BILLED_QUALIFIER")
	private String billedQualifier;
	
	@Column(name = "WEIGHT")
	private Long weight;
	
	@Column(name = "WEIGHT_QUALIFIER")
	private String weightQualifier;
	
	@Column(name = "VOLUME_QUALIFIER")
	private String volumeQualifier;
	
	@Column(name = "LADING_QUANTITY")
	private Long ladingQuantity;
	
	@Column(name = "PACKAGING_FORM_CODE")
	private String packagingFormCode;
	
	@Column(name = "DUNNAGE_DESCRIPTION")
	private String dunnageDescription;
	
	@Column(name = "FREIGHT_RATE")
	private Long freightRate;
	
	@Column(name = "RATE_VALUE_QUALIFIER")
	private String rateValueQualifier;
	
	@Column(name = "CHARGES")
	private Long charges;
	
	@Column(name = "PRE_PAID_AMOUNT")
	private Long prePaidAmount;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "CREATED_DATE_TIME")
	private Date createdDateTime;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE_TIME")
	private Date updatedDateTime;
	
	@Column(name = "DELETE_FLAG")
	private String deleteFlag;
	
	public FreightLineItems() {
		
	}

	public Long getFreightLineId() {
		return freightLineId;
	}

	public void setFreightLineId(Long freightLineId) {
		this.freightLineId = freightLineId;
	}

	

	public Long getShipmentReference() {
		return shipmentReference;
	}

	public void setShipmentReference(Long shipmentReference) {
		this.shipmentReference = shipmentReference;
	}
	
	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public Long getLineItemNo() {
		return lineItemNo;
	}

	public void setLineItemNo(Long lineItemNo) {
		this.lineItemNo = lineItemNo;
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

	public Long getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Long itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getItemQuantityUom() {
		return itemQuantityUom;
	}

	public void setItemQuantityUom(String itemQuantityUom) {
		this.itemQuantityUom = itemQuantityUom;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public String getCommodityCode() {
		return commodityCode;
	}

	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}

	public String getInnerPackageType() {
		return innerPackageType;
	}

	public void setInnerPackageType(String innerPackageType) {
		this.innerPackageType = innerPackageType;
	}

	public Long getInnerPackageQty() {
		return innerPackageQty;
	}

	public void setInnerPackageQty(Long innerPackageQty) {
		this.innerPackageQty = innerPackageQty;
	}

	public Long getPiecesCount() {
		return piecesCount;
	}

	public void setPiecesCount(Long piecesCount) {
		this.piecesCount = piecesCount;
	}

	public Long getWeightUom() {
		return weightUom;
	}

	public void setWeightUom(Long weightUom) {
		this.weightUom = weightUom;
	}

	public Long getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Long grossWeight) {
		this.grossWeight = grossWeight;
	}

	public Long getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Long netWeight) {
		this.netWeight = netWeight;
	}

	public Long getVolumeUom() {
		return volumeUom;
	}

	public void setVolumeUom(Long volumeUom) {
		this.volumeUom = volumeUom;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public Long getNetVolume() {
		return netVolume;
	}

	public void setNetVolume(Long netVolume) {
		this.netVolume = netVolume;
	}

	public String getContentDescription() {
		return contentDescription;
	}

	public void setContentDescription(String contentDescription) {
		this.contentDescription = contentDescription;
	}

	public String getSourceEntry() {
		return sourceEntry;
	}

	public void setSourceEntry(String sourceEntry) {
		this.sourceEntry = sourceEntry;
	}

	public String getLadingDescription() {
		return ladingDescription;
	}

	public void setLadingDescription(String ladingDescription) {
		this.ladingDescription = ladingDescription;
	}

	public String getCommodityCodeQualifier() {
		return commodityCodeQualifier;
	}

	public void setCommodityCodeQualifier(String commodityCodeQualifier) {
		this.commodityCodeQualifier = commodityCodeQualifier;
	}

	public String getPackagingCode() {
		return packagingCode;
	}

	public void setPackagingCode(String packagingCode) {
		this.packagingCode = packagingCode;
	}

	public String getMarksAndNumbers() {
		return marksAndNumbers;
	}

	public void setMarksAndNumbers(String marksAndNumbers) {
		this.marksAndNumbers = marksAndNumbers;
	}

	public String getCompartmentId() {
		return compartmentId;
	}

	public void setCompartmentId(String compartmentId) {
		this.compartmentId = compartmentId;
	}

	public Long getBilledQuantity() {
		return billedQuantity;
	}

	public void setBilledQuantity(Long billedQuantity) {
		this.billedQuantity = billedQuantity;
	}

	public String getBilledQualifier() {
		return billedQualifier;
	}

	public void setBilledQualifier(String billedQualifier) {
		this.billedQualifier = billedQualifier;
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	public String getWeightQualifier() {
		return weightQualifier;
	}

	public void setWeightQualifier(String weightQualifier) {
		this.weightQualifier = weightQualifier;
	}

	public String getVolumeQualifier() {
		return volumeQualifier;
	}

	public void setVolumeQualifier(String volumeQualifier) {
		this.volumeQualifier = volumeQualifier;
	}

	public Long getLadingQuantity() {
		return ladingQuantity;
	}

	public void setLadingQuantity(Long ladingQuantity) {
		this.ladingQuantity = ladingQuantity;
	}

	public String getPackagingFormCode() {
		return packagingFormCode;
	}

	public void setPackagingFormCode(String packagingFormCode) {
		this.packagingFormCode = packagingFormCode;
	}

	public String getDunnageDescription() {
		return dunnageDescription;
	}

	public void setDunnageDescription(String dunnageDescription) {
		this.dunnageDescription = dunnageDescription;
	}

	public Long getFreightRate() {
		return freightRate;
	}

	public void setFreightRate(Long freightRate) {
		this.freightRate = freightRate;
	}

	public String getRateValueQualifier() {
		return rateValueQualifier;
	}

	public void setRateValueQualifier(String rateValueQualifier) {
		this.rateValueQualifier = rateValueQualifier;
	}

	public Long getCharges() {
		return charges;
	}

	public void setCharges(Long charges) {
		this.charges = charges;
	}

	public Long getPrePaidAmount() {
		return prePaidAmount;
	}

	public void setPrePaidAmount(Long prePaidAmount) {
		this.prePaidAmount = prePaidAmount;
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

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public AdvanceShipmentNotice getFreightLineItemsRef() {
		return freightLineItemsRef;
	}

	public void setFreightLineItemsRef(AdvanceShipmentNotice freightLineItemsRef) {
		this.freightLineItemsRef = freightLineItemsRef;
	}
	
	
}
