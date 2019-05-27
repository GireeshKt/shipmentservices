package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
import java.util.Date;


public class LaneResponse implements Serializable {
	private static final long serialVersionUID = 1L;	
	private String laneId;
	private String laneDescription;
	private String origin_locationId;
	private String destination_locationId;
	private Date lane_expiryDate;
	private String isActive;
	private Long tariffId;
	private String autoTender;
	private String laneSelectionCriteria;
	private String borderCrossing;
	public LaneResponse() {
		
	}


	public String getLaneId() {
		return laneId;
	}


	public void setLaneId(String laneId) {
		this.laneId = laneId;
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


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
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
