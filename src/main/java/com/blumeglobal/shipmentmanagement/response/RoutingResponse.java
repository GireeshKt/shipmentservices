package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;


public class RoutingResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String routingid;
	
	private String originLocationId;
	
	private String destinationLocationId;
	
	private String modeOfTransport;
	
	private String default_carrier;
	
	private Long transitTime;

	public Long getTransitTime() {
		return transitTime;
	}

	public void setTransitTime(Long transitTime) {
		this.transitTime = transitTime;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getLaneGroupId() {
		return laneGroupId;
	}

	public void setLaneGroupId(String laneGroupId) {
		this.laneGroupId = laneGroupId;
	}

	public String getRouteDescription() {
		return routeDescription;
	}

	public void setRouteDescription(String routeDescription) {
		this.routeDescription = routeDescription;
	}

	public String getEquipmentClass() {
		return equipmentClass;
	}

	public void setEquipmentClass(String equipmentClass) {
		this.equipmentClass = equipmentClass;
	}

	public String getEquipment_snt() {
		return equipment_snt;
	}

	public void setEquipment_snt(String equipment_snt) {
		this.equipment_snt = equipment_snt;
	}

	public Long getMaximumWeight() {
		return maximumWeight;
	}

	public void setMaximumWeight(Long maximumWeight) {
		this.maximumWeight = maximumWeight;
	}

	public Long getMaximumVolume() {
		return maximumVolume;
	}

	public void setMaximumVolume(Long maximumVolume) {
		this.maximumVolume = maximumVolume;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public Long getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Long totalCost) {
		this.totalCost = totalCost;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Long getStopDuration() {
		return stopDuration;
	}

	public void setStopDuration(Long stopDuration) {
		this.stopDuration = stopDuration;
	}

	public String getSelectionCriteria() {
		return selectionCriteria;
	}

	public void setSelectionCriteria(String selectionCriteria) {
		this.selectionCriteria = selectionCriteria;
	}

	public String getCapacity_basis() {
		return capacity_basis;
	}

	public void setCapacity_basis(String capacity_basis) {
		this.capacity_basis = capacity_basis;
	}

	public String getTownCrossing() {
		return townCrossing;
	}

	public void setTownCrossing(String townCrossing) {
		this.townCrossing = townCrossing;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String uom;

	private String laneGroupId;

	private String routeDescription;

	private String equipmentClass;

	private String equipment_snt;

	private Long maximumWeight;

	private Long maximumVolume;

	private String commodity;

	private Long totalCost;

	private String isActive;

	private Long stopDuration;

	private String selectionCriteria;

	private String capacity_basis;

	private String townCrossing;

	public RoutingResponse() {
		
	}

	public String getRoutingid() {
		return routingid;
	}

	public void setRoutingid(String routingid) {
		this.routingid = routingid;
	}

	public String getOriginLocationId() {
		return originLocationId;
	}

	public void setOriginLocationId(String originLocationId) {
		this.originLocationId = originLocationId;
	}

	public String getDestinationLocationId() {
		return destinationLocationId;
	}

	public void setDestinationLocationId(String destinationLocationId) {
		this.destinationLocationId = destinationLocationId;
	}

	public String getModeOfTransport() {
		return modeOfTransport;
	}

	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}

	public String getDefault_carrier() {
		return default_carrier;
	}

	public void setDefault_carrier(String default_carrier) {
		this.default_carrier = default_carrier;
	}
	
	
}
