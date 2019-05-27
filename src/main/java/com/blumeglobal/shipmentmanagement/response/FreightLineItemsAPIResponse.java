package com.blumeglobal.shipmentmanagement.response;

import java.util.Date;

import com.blumeglobal.shipmentmanagement.model.AdvanceShipmentNotice;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class FreightLineItemsAPIResponse {
	private Long freightLineId;
	
	@JsonIgnore
	private AdvanceShipmentNotice freightLineItemsRef;
	
	private Long shipmentReference;	
	
	private String poNumber;
	
	private Long lineItemNo;
	
	private String itemNumber;
	
	private String itemDescription;
	
	private Long itemQuantity;
	
	private String itemQuantityUom;
	
	private String commodity;
	
	private String commodityCode;
	
	private String innerPackageType;
	
	private Long innerPackageQty;
	
	private Long piecesCount;
	
	private Long weightUom;
	
	private Long grossWeight;
	
	private Long netWeight;
	
	private Long volumeUom;
	
	private Long volume;
	
	private Long netVolume;
	
	private String contentDescription;
	
	private String sourceEntry;
	
	private String ladingDescription;
	
	private String commodityCodeQualifier;
	
	private String packagingCode;
	
	private String marksAndNumbers;
	
	private String compartmentId;
	
	private Long billedQuantity;
	
	private String billedQualifier;
	
	private Long weight;
	
	private String weightQualifier;
	
	private String volumeQualifier;
	
	private Long ladingQuantity;
	
	private String packagingFormCode;
	
	private String dunnageDescription;
	
	private Long freightRate;
	
	private String rateValueQualifier;
	
	private Long charges;
	
	private Long prePaidAmount;
	
	private String createdBy;
	
	private Date createdDateTime;
	
	private String updatedBy;
	
	private Date updatedDateTime;
	
	private String deleteFlag;
	
	
	public FreightLineItemsAPIResponse() {
		
	}

	public Long getFreightLineId() {
		return freightLineId;
	}

	public void setFreightLineId(Long freightLineId) {
		this.freightLineId = freightLineId;
	}

	public AdvanceShipmentNotice getFreightLineItemsRef() {
		return freightLineItemsRef;
	}

	public void setFreightLineItemsRef(AdvanceShipmentNotice freightLineItemsRef) {
		this.freightLineItemsRef = freightLineItemsRef;
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
	
}
