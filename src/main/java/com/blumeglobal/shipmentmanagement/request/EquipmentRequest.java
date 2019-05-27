package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;

public class EquipmentRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String equipmentTypeCode;
	private String shipmentNumber;
	private String equipmentNumber;
	
	public EquipmentRequest() {
		
	}

	public String getEquipmentTypeCode() {
		return equipmentTypeCode;
	}

	public void setEquipmentTypeCode(String equipmentTypeCode) {
		this.equipmentTypeCode = equipmentTypeCode;
	}

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}

	public String getEquipmentNumber() {
		return equipmentNumber;
	}

	public void setEquipmentNumber(String equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
	}
	
}
