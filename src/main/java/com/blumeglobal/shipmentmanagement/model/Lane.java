package com.blumeglobal.shipmentmanagement.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "LANE")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Lane {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lane_id")
	//	@SequenceGenerator(name = "seq_lane_id", allocationSize = 1, sequenceName = "SEQ_LANE_ID")
	@Column(name = "LANEID", nullable = false, updatable = false, length = 50)
	private String laneId;
	//	LANEID	VARCHAR2(50 BYTE)
	
	@Column(name = "LANEDESCRIPTION", length = 100)
	private String laneDescription;
	//	LANEDESCRIPTION	VARCHAR2(100 BYTE)
	
	@Column(name = "ORIGIN_LOCATIONID", length = 200)
	private String origin_locationId;
	//	ORIGIN_LOCATIONID	VARCHAR2(200 BYTE)
	
	@Column(name = "DESTINATION_LOCATIONID", length = 200)
	private String destination_locationId;
	//	DESTINATION_LOCATIONID	VARCHAR2(200 BYTE)
	
	@Column(name = "LANE_EXPIRYDATE")
	private Date lane_expiryDate;
	//	LANE_EXPIRYDATE	DATE
	
	@Column(name = "ISACTIVE", length = 1)
	private String isActive;
	//	ISACTIVE	CHAR(1 BYTE)
	
	@Column(name = "TARIFFID")
	private Long tariffId;
	//	TARIFFID	NUMBER
	
	@Column(name = "AUTOTENDER", length = 1)
	private String autoTender;
	//	AUTOTENDER	CHAR(1 BYTE)
	
	@Column(name = "LANESELECTIONCRITERIA", length = 100)
	private String laneSelectionCriteria;
	//	LANESELECTIONCRITERIA	VARCHAR2(100 BYTE)
	
	@Column(name = "BORDERCROSSING", length = 1)
	private String borderCrossing;
	//	BORDERCROSSING	CHAR(1 BYTE)
	
						  
	@OneToMany(mappedBy = "laneLink", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<BookingDetails> bookingDetails;
						 

	@OneToMany(mappedBy = "laneRef", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<LaneGroups> laneGroups;
	
	public Set<LaneGroups> getLaneGroups() {
		return laneGroups;
	}


	public void setLaneGroups(Set<LaneGroups> laneGroups) {
		this.laneGroups = laneGroups;
	}


	public Lane() {
		
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


	public Set<BookingDetails> getBookingDetails() {
		return bookingDetails;
	}


	public void setBookingDetails(Set<BookingDetails> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}


}
