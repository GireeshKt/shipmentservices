package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
import java.util.Date;

public class ShipmentMilestoneForRuleEngine implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String uid;
    private Long shipmentId;
	private Long shipmentMilestoneId;
	private String eventCode;
	private String eventName;
    private String milestoneName;
	private String mode;
	private Date estimatedDate;
	private Date actualDate;
	private Double estimatedLeadTime;
	private Date plannedDate;
	private Long orderSeq;
	
	public ShipmentMilestoneForRuleEngine() {
		super();
	}

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Long getShipmentMilestoneId() {
        return shipmentMilestoneId;
    }

    public void setShipmentMilestoneId(Long shipmentMilestoneId) {
        this.shipmentMilestoneId = shipmentMilestoneId;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Date getEstimatedDate() {
        return estimatedDate;
    }

    public void setEstimatedDate(Date estimatedDate) {
        this.estimatedDate = estimatedDate;
    }

    public Date getActualDate() {
        return actualDate;
    }

    public void setActualDate(Date actualDate) {
        this.actualDate = actualDate;
    }

    public Double getEstimatedLeadTime() {
        return estimatedLeadTime;
    }

    public void setEstimatedLeadTime(Double estimatedLeadTime) {
        this.estimatedLeadTime = estimatedLeadTime;
    }

    public Date getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }

    public Long getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(Long orderSeq) {
        this.orderSeq = orderSeq;
    }

		
}
