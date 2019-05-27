package com.blumeglobal.shipmentmanagement.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="SHIPMENTEXCEPTIONS")
public class ShipmentException implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	@Column(name = "SHIPMENTEXCEPTIONID")
	private Long shipmentExceptionId;
		
	@Column(name = "SHIPMENTID")
	private Long shipmentId;
	
	@Column(name = "E2E_SHIPMENTID")
	private Long e2eShipmentId;
	
	@Column(name = "MODEOFTRANSPORT")
	private String modeOfTransport;
	
	@Column(name = "EXCEPTIONTYPE")
	private String exceptionType;
	
	@Column(name = "DATEOFOCCURANCE")
	private Date occuranceDate;
	
	@Column(name = "EXCEPTIONNOTES")
	private String exceptionNotes;
	
	@Column(name = "EXCEPTIONLOCATION")
	private String exceptionLocation;
	
	@Column(name = "CATEGORY")
	private String category;
	
	@Column(name = "SHIPMENTMILESTONEID")
	private Long shipmentMilestoneId;
	
	@Column(name = "LOCATIONID")
	private String locationId;
	
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
	
	public ShipmentException() {
		super();
	}


	public Long getShipmentExceptionId() {
		return shipmentExceptionId;
	}


	public void setShipmentExceptionId(Long shipmentExceptionId) {
		this.shipmentExceptionId = shipmentExceptionId;
	}


	public String getExceptionType() {
		return exceptionType;
	}


	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}


	public Date getOccuranceDate() {
		return occuranceDate;
	}


	public void setOccuranceDate(Date occuranceDate) {
		this.occuranceDate = occuranceDate;
	}


	public String getExceptionNotes() {
		return exceptionNotes;
	}


	public void setExceptionNotes(String exceptionNotes) {
		this.exceptionNotes = exceptionNotes;
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

	public String getModeOfTransport() {
		return modeOfTransport;
	}

	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}
	
	public String getExceptionLocation() {
		return exceptionLocation;
	}

	public void setExceptionLocation(String exceptionLocation) {
		this.exceptionLocation = exceptionLocation;
	}


	public Long getShipmentMilestoneId() {
		return shipmentMilestoneId;
	}


	public void setShipmentMilestoneId(Long shipmentMilestoneId) {
		this.shipmentMilestoneId = shipmentMilestoneId;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}

}
