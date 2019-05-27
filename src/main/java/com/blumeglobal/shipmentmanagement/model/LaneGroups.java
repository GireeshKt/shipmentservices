package com.blumeglobal.shipmentmanagement.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "LANEGROUPS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class LaneGroups {
	private static final long serialVersionUID = 1L;
	
	@Id
	//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lane_id")
	//	@SequenceGenerator(name = "seq_lane_id", allocationSize = 1, sequenceName = "SEQ_LANE_ID")
	@Column(name = "LANEGROUPID", nullable = false, updatable = false, length = 50)
	private String lanegroupId;
	//	LANEGROUPID	VARCHAR2(50 BYTE)
	
	@Column(name = "GROUPDESCRIPTION", length = 100)
	private String groupDescription;
	//	GROUPDESCRIPTION	VARCHAR2(100 BYTE)
	
	/*@Column(name = "LANEID", length = 50)
	private String laneLinkLG;*/
	//	LANEID	VARCHAR2(50 BYTE)
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "LANEID", nullable = false)	
	private Lane laneRef;
	
	@Column(name = "DEAFULTGROUP", length = 1)
	private String deafultGroup;
	//	DEAFULTGROUP	CHAR(1 BYTE)
	
	@Column(name = "ISACTIVE", length = 1)
	private String isactive;
	//	ISACTIVE	CHAR(1 BYTE)
	
	@Column(name = "MAJORMODE", length = 50)
	private String majorMode;
	//	MAJORMODE	VARCHAR2(50 BYTE)
	
						   
	@OneToMany(mappedBy = "laneGroupsLink", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<BookingDetails> bookingDetails;	
	
	
	
	public Lane getLaneRef() {
		return laneRef;
	}


	public void setLaneRef(Lane laneRef) {
		this.laneRef = laneRef;
	}


	public LaneGroups() {
		
	}


	public String getLanegroupId() {
		return lanegroupId;
	}


	public void setLanegroupId(String lanegroupId) {
		this.lanegroupId = lanegroupId;
	}


	public String getGroupDescription() {
		return groupDescription;
	}


	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}


//	public String getLaneLinkLG() {
//		return laneLinkLG;
//	}
//
//
//	public void setLaneLinkLG(String laneLinkLG) {
//		this.laneLinkLG = laneLinkLG;
//	}


	public String getDeafultGroup() {
		return deafultGroup;
	}


	public void setDeafultGroup(String deafultGroup) {
		this.deafultGroup = deafultGroup;
	}


	public String getIsactive() {
		return isactive;
	}


	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}


	public String getMajorMode() {
		return majorMode;
	}


	public void setMajorMode(String majorMode) {
		this.majorMode = majorMode;
	}


	public Set<BookingDetails> getBookingDetails() {
		return bookingDetails;
	}


	public void setBookingDetails(Set<BookingDetails> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}
	
}
