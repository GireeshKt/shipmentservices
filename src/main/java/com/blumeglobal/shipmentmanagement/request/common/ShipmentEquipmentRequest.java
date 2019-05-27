package com.blumeglobal.shipmentmanagement.request.common;

import java.io.Serializable;
import java.util.List;

public class ShipmentEquipmentRequest implements Serializable{

    private static final long serialVersionUID = 1L;
    private String equipmentCode;
    private String equipmentNumber;
    private String equpipmentType;
    private Boolean isEmpty;
    private Long pieces;
    private Double grossWeight;
    private Double netWeight;
    private Double tareWeight;
    private String weightMeasurement;
    private Double volume;
    private String volumeMeasurement;
    private String seal;
    private String hazmat;
    private String freightDescription;
    private String containerMode;
    private List<ChargeDetailRequest> charge;
    public String getEquipmentCode() {
        return equipmentCode;
    }
    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }
    public String getEquipmentNumber() {
        return equipmentNumber;
    }
    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }
    public String getEqupipmentType() {
        return equpipmentType;
    }
    public void setEqupipmentType(String equpipmentType) {
        this.equpipmentType = equpipmentType;
    }
    public Boolean getIsEmpty() {
        return isEmpty;
    }
    public void setIsEmpty(Boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
    public Long getPieces() {
        return pieces;
    }
    public void setPieces(Long pieces) {
        this.pieces = pieces;
    }
    public Double getGrossWeight() {
        return grossWeight;
    }
    public void setGrossWeight(Double grossWeight) {
        this.grossWeight = grossWeight;
    }
    public Double getNetWeight() {
        return netWeight;
    }
    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }
    public Double getTareWeight() {
        return tareWeight;
    }
    public void setTareWeight(Double tareWeight) {
        this.tareWeight = tareWeight;
    }
    public String getWeightMeasurement() {
        return weightMeasurement;
    }
    public void setWeightMeasurement(String weightMeasurement) {
        this.weightMeasurement = weightMeasurement;
    }
    public Double getVolume() {
        return volume;
    }
    public void setVolume(Double volume) {
        this.volume = volume;
    }
    public String getVolumeMeasurement() {
        return volumeMeasurement;
    }
    public void setVolumeMeasurement(String volumeMeasurement) {
        this.volumeMeasurement = volumeMeasurement;
    }
    public String getSeal() {
        return seal;
    }
    public void setSeal(String seal) {
        this.seal = seal;
    }
    public String getHazmat() {
        return hazmat;
    }
    public void setHazmat(String hazmat) {
        this.hazmat = hazmat;
    }
    public String getFreightDescription() {
        return freightDescription;
    }
    public void setFreightDescription(String freightDescription) {
        this.freightDescription = freightDescription;
    }
    public String getContainerMode() {
        return containerMode;
    }
    public void setContainerMode(String containerMode) {
        this.containerMode = containerMode;
    }
    public List<ChargeDetailRequest> getCharge() {
        return charge;
    }
    public void setCharge(List<ChargeDetailRequest> charge) {
        this.charge = charge;
    }
   
}