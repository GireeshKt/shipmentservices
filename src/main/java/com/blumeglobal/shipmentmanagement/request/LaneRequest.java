package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;

public class LaneRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Size(max = 50, message = "Lane Id must be a maximum of 50 characters")
	private String LaneId;
	
	@Size(max = 100, message = "Group Description must be a maximum of 100 characters")
	private String laneDescription;
	
	@Size(max = 200, message = "Origin Location Id must be a maximum of 200 characters")
	private String origin_locationId;
	
	@Size(max = 200, message = "Destination Location Id must be a maximum of 200 characters")
	private String destination_locationId;
	
	private Date lane_expiryDate;
	
	@Size(max = 1, message = "IsActive must be a maximum of 1 character")
	private String isActive;
	
//	@Size(max = 22, message = "Tarrif Id must be a maximum of 22 characters")
	private Long tariffId;
	
	@Size(max = 1, message = "AutoTender must be a maximum of 1 character")
	private String autoTender;
	
	@Size(max = 100, message = "Lane Selection Criteria must be a maximum of 100 characters")
	private String laneSelectionCriteria;
		
	@Size(max = 1, message = "Border Crossing must be a maximum of 1 character")
	private String borderCrossing;
	
	
	public LaneRequest() {
		super();
	}

	public String getLaneId() {
		return LaneId;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public void setLaneId(String laneId) {
		this.LaneId = laneId;
	}

	public String getLaneDescription() {
		return laneDescription;
	}

	public void setLaneDescription(String laneDescription) {
		this.laneDescription = laneDescription;
	}

	public String getOrigin_locationId() {
		return origin_locationId;
	}

	public void setOrigin_locationId(String origin_locationId) {
		this.origin_locationId = origin_locationId;
	}

	public String getDestination_locationId() {
		return destination_locationId;
	}

	public void setDestination_locationId(String destination_locationId) {
		this.destination_locationId = destination_locationId;
	}

	public Date getLane_expiryDate() {
		return lane_expiryDate;
	}

	public void setLane_expiryDate(Date lane_expiryDate) {
		this.lane_expiryDate = lane_expiryDate;
	}
	public Long getTariffId() {
		return tariffId;
	}

	public void setTariffId(Long tariffId) {
		this.tariffId = tariffId;
	}

	public String getAutoTender() {
		return autoTender;
	}

	public void setAutoTender(String autoTender) {
		this.autoTender = autoTender;
	}

	public String getLaneSelectionCriteria() {
		return laneSelectionCriteria;
	}

	public void setLaneSelectionCriteria(String laneSelectionCriteria) {
		this.laneSelectionCriteria = laneSelectionCriteria;
	}

	public String getBorderCrossing() {
		return borderCrossing;
	}

	public void setBorderCrossing(String borderCrossing) {
		this.borderCrossing = borderCrossing;
	}

	
	
}