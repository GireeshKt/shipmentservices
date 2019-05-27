package com.blumeglobal.shipmentmanagement.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="SHIPMENTMILESTONES")
public class ShipmentMilestone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	@Column(name = "SHIPMENTMILESTONEID")
	private Long shipmentMilestoneId;
		
	@Column(name = "SHIPMENTID")
	private Long shipmentId;
	
	@Column(name = "E2E_SHIPMENTID")
	private Long e2eShipmentId;
	
	@Column(name = "MODEOFTRANSPORT")
	private String modeOfTransport;
	
	@Column(name = "MILESTONENAME")
	private String milestoneName;
	
	@Column(name = "ESTIMATEDDATE")
	private Date estimatedDate;
	
	@Column(name = "ACTUALDATE")
	private Date actualDate;
	
	@Column(name = "LOCATIONID")
	private String locationId;
	
	@Column(name = "ESTIMATEDLEADTIME")
	private Double estimatedLeadTime;
	
	@Column(name = "ACTUALLEADTIME")
	private Double actualLeadTime;

	@Column(name = "UOM")
	private String uom;

	@Column(name = "PLANNEDDATE")
	private Date plannedDate;
	
	@Column(name = "LOCATIONNAME")
	private String locationName;
	
	@Column(name = "LOCATIONTYPE")
	private String locationType;
	
	@Column(name = "ADDRESS1")
	private String address1;
	
	@Column(name = "ADDRESS2")
	private String address2;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "ZIPCODE")
	private String zipcode;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "UNLOCODE", length = 20)
    private String unlocode;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "MILESTONEID", nullable = false)
	private Milestone milestone;

	public ShipmentMilestone() {
		super();
	}

	public Long getShipmentMilestoneId() {
		return shipmentMilestoneId;
	}

	public void setShipmentMilestoneId(Long shipmentMilestoneId) {
		this.shipmentMilestoneId = shipmentMilestoneId;
	}

	public Long getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(Long shipmentId) {
		this.shipmentId = shipmentId;
	}

	public Long getE2eShipmentId() {
		return e2eShipmentId;
	}

	public void setE2eShipmentId(Long e2eShipmentId) {
		this.e2eShipmentId = e2eShipmentId;
	}

	public Milestone getMilestone() {
		return milestone;
	}

	public void setMilestone(Milestone milestone) {
		this.milestone = milestone;
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

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public Double getEstimatedLeadTime() {
		return estimatedLeadTime;
	}

	public void setEstimatedLeadTime(Double estimatedLeadTime) {
		this.estimatedLeadTime = estimatedLeadTime;
	}

	public Double getActualLeadTime() {
		return actualLeadTime;
	}

	public void setActualLeadTime(Double actualLeadTime) {
		this.actualLeadTime = actualLeadTime;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public Date getPlannedDate() {
		return plannedDate;
	}

	public void setPlannedDate(Date plannedDate) {
		this.plannedDate = plannedDate;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUnlocode() {
		return unlocode;
	}

	public void setUnlocode(String unlocode) {
		this.unlocode = unlocode;
	}
	
}
