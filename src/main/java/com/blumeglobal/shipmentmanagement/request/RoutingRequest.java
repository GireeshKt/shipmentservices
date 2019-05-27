package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Size;

import com.blumeglobal.shipmentmanagement.model.BookingDetails;

/**
 * ShipmentSchedule entity.
 */

public class RoutingRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Size(max = 50, message = "routing ID must be a maximum of 50 characters")
	private String routingid;
	
	@Size(max = 20, message = "Mode of transport must be maximum of 20 characters")
	private String modeOfTransport;
	
	@Size(max = 200, message = "Origin Location Id must be maximum of 200 characters")
	private String originLocationId;
	
	@Size(max = 200, message = "Destination Location Id must be maximum of 200 characters")
	private String destinationLocationId;
	
	private Double transitTime;
	
	@Size(max = 50, message = "UOM must be maximum of 50 characters")
	private String uom;
	
	@Size(max = 50, message = "Lane Group Id must be maximum of 50 characters")
	private String laneGroupId;
	
	@Size(max = 200, message = "UOM must be maximum of 200 characters")
	private String routeDescription;
	
	@Size(max = 50, message = "Equipment class must be maximum of 50 characters")
	private String equipmentClass;
	
	@Size(max = 50, message = "Equipment_snt must be maximum of 50 characters")
	private String equipment_snt;
	
	private Long maximumWeight;
	
	private Long maximumVolume;
	
	@Size(max = 50, message = "Commodity must be maximum of 50 characters")
	private String commodity;
	
	private Long totalCost;
	
	@Size(max = 1, message = "IsActive must be maximum of 1 characters")
	private String isActive;
	
	private Long stopDuration;
	
	@Size(max = 50, message = "Default Carrier must be maximum of 50 characters")
	private String default_carrier;
	
	@Size(max = 50, message = "Selection Criteria must be maximum of 50 characters")
	private String selectionCriteria;
	
	@Size(max = 50, message = "Capacity Basis must be maximum of 50 characters")
	private String capacity_basis;
	
	@Size(max = 1, message = "Town Crossing must be maximum of 1 characters")
	private String townCrossing;
	
	public RoutingRequest() {
		
	}
	
	public String getRoutingid() {
		return routingid;
	}

	public void setRoutingid(String routingid) {
		this.routingid = routingid;
	}

	public String getModeOfTransport() {
		return modeOfTransport;
	}

	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
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

	public Double getTransitTime() {
		return transitTime;
	}

	public void setTransitTime(Double transitTime) {
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

	public String getDefault_carrier() {
		return default_carrier;
	}

	public void setDefault_carrier(String default_carrier) {
		this.default_carrier = default_carrier;
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

}