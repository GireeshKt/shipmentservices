package com.blumeglobal.shipmentmanagement.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name="MILESTONE")
public class Milestone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	@Column(name = "MILESTONEID")
	private Long milestoneId;
		
	@Column(name = "EVENTCODE")
	private String eventCode;
	
	@Column(name = "EVENTNAME")
	private String eventName;
	
	@Column(name = "MODEOFTRANSPORT")
	private String modeOfTransport;
	
	@Column(name = "MILESTONENAME")
	private String milestoneName;
	
	@Column(name = "ORGANIZATIONID")
	private String organizationId;

	@Type(type="yes_no")
	@Column(name = "WITHLEADTIME")
	private boolean withLeadTime;
	
	@Column(name = "ORDERSEQ")
	private Long orderSeq;
	
	@Type(type="yes_no")
	@Column(name = "DISPLAY")
	private boolean display;	
	
	@Column(name = "MILESTONECODE")
	private String milestoneCode;
	
	@Column(name = "TIEDTOLOCATION")
	private String tiedToLocation;
	
	@Column(name = "LEADTIME")
	private Long leadTime;
	

	public Milestone() {
		super();
	}

	public String getModeOfTransport() {
		return modeOfTransport;
	}

	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}

	public String getMilestoneName() {
		return milestoneName;
	}

	public void setMilestoneName(String milestoneName) {
		this.milestoneName = milestoneName;
	}


	public Long getMilestoneId() {
		return milestoneId;
	}


	public void setMilestoneId(Long milestoneId) {
		this.milestoneId = milestoneId;
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

	public String getOrganizationId() {
		return organizationId;
	}


	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public boolean isWithLeadTime() {
		return withLeadTime;
	}

	public void setWithLeadTime(boolean withLeadTime) {
		this.withLeadTime = withLeadTime;
	}

	public Long getOrderSeq() {
		return orderSeq;
	}

	public void setOrderSeq(Long orderSeq) {
		this.orderSeq = orderSeq;
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	public String getMilestoneCode() {
		return milestoneCode;
	}

	public void setMilestoneCode(String milestoneCode) {
		this.milestoneCode = milestoneCode;
	}

	public String getTiedToLocation() {
		return tiedToLocation;
	}

	public void setTiedToLocation(String tiedToLocation) {
		this.tiedToLocation = tiedToLocation;
	}

	public Long getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(Long leadTime) {
		this.leadTime = leadTime;
	}

	
}
