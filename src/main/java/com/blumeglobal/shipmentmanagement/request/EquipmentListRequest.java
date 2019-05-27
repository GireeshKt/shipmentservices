package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;

public class EquipmentListRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String equipmentNumber;
	
	private Double tareWeight;
	
	private String weightUOM;
	
	private Double volume;
	
	private String volumeUOM;
	
	private String isOverweight;
	
	private String shipmentnumber;
	
	private String isHazmat;
	
	private String equipmentTypeCode;
	
	private String freightDescription;
	

	public EquipmentListRequest() {
		
	}


	public String getEquipmentNumber() {
		return equipmentNumber;
	}


	public void setEquipmentNumber(String equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
	}


	public Double getTareWeight() {
		return tareWeight;
	}


	public void setTareWeight(Double tareWeight) {
		this.tareWeight = tareWeight;
	}


	public String getWeightUOM() {
		return weightUOM;
	}


	public void setWeightUOM(String weightUOM) {
		this.weightUOM = weightUOM;
	}


	public Double getVolume() {
		return volume;
	}


	public void setVolume(Double volume) {
		this.volume = volume;
	}


	public String getVolumeUOM() {
		return volumeUOM;
	}


	public void setVolumeUOM(String volumeUOM) {
		this.volumeUOM = volumeUOM;
	}


	public String getIsOverweight() {
		return isOverweight;
	}


	public void setIsOverweight(String isOverweight) {
		this.isOverweight = isOverweight;
	}


	public String getShipmentnumber() {
		return shipmentnumber;
	}


	public void setShipmentnumber(String shipmentnumber) {
		this.shipmentnumber = shipmentnumber;
	}


	public String getIsHazmat() {
		return isHazmat;
	}


	public void setIsHazmat(String isHazmat) {
		this.isHazmat = isHazmat;
	}


	public String getEquipmentTypeCode() {
		return equipmentTypeCode;
	}


	public void setEquipmentTypeCode(String equipmentTypeCode) {
		this.equipmentTypeCode = equipmentTypeCode;
	}


	public String getFreightDescription() {
		return freightDescription;
	}


	public void setFreightDescription(String freightDescription) {
		this.freightDescription = freightDescription;
	}
	
}
