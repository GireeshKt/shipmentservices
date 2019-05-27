package com.blumeglobal.shipmentmanagement.model;

import java.util.Date;
import java.util.List;
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

import org.hibernate.annotations.Type;


@Entity
@Table(name = "booking")
public class Booking {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_booking_id")
	@SequenceGenerator(name = "seq_booking_id", allocationSize = 1, sequenceName = "SEQ_BOOKING_ID")
	@Column(name = "BOOKINGID", nullable = false,updatable = false)
	private Long bookingId;
	//	BOOKINGID	NUMBER
	
	@Column(name = "BOOKINGDATE")
	private Date bookingDate;
	//	BOOKINGDATE	DATE
	
	@Column(name = "SHIPMENTREFERENCENUM", length = 50)
	private String shipmentReferenceNum;
	//	SHIPMENTREFERENCENUM	VARCHAR2(50 BYTE)
	
	@Column(name = "SHIPPER", length = 100)
	private String shipper;
	//	SHIPPER	VARCHAR2(100 BYTE)
	
	@Column(name = "CONSIGNEE", length = 100)
	private String consignee;
	//	CONSIGNEE	VARCHAR2(100 BYTE)
	
	@Column(name = "ORIGIN_LOCATIONID", length = 200)
	private String origin_locationId;
	//	ORIGIN_LOCATIONID	VARCHAR2(200 BYTE)
	
	@Column(name = "DESTINATION_LOCATIONID", length = 200)
	private String destination_locationId;
	//	DESTINATION_LOCATIONID	VARCHAR2(200 BYTE)
	
	@Column(name = "MOVETYPE", length = 20)
	private String moveType;
	//	MOVETYPE	VARCHAR2(20 BYTE)
	
	@Column(name = "COMMODITY", length = 200)
	private String commodity;
	//	COMMODITY	VARCHAR2(200 BYTE)
	
	@Column(name = "LOADTYPE", length = 50)
	private String loadType;
	//	LOADTYPE	VARCHAR2(50 BYTE)
	
	@Column(name = "EQUIPMENTDIMENSIONS", length = 100)
	private String equipmentDimensions;
	//	EQUIPMENTDIMENSIONS	VARCHAR2(100 BYTE)
	
	@Column(name = "NUMOFCONTAINERS")
	private Long numOfContainers;
	//	NUMOFCONTAINERS	NUMBER
	
	@Column(name = "REQUESTEDPICKUPDATE")
	private Date requestedPickupDate;
	//	REQUESTEDPICKUPDATE	DATE
	
	@Column(name = "REQUESTEDDELIVERYDATE")
	private Date requestedDeliveryDate;
	//	REQUESTEDDELIVERYDATE	DATE
	
	@Column(name = "INCOTERM", length = 20)
	private String incoTerm;
	//	INCOTERM	VARCHAR2(20 BYTE)
	
	@Type(type="yes_no")
	@Column(name = "HAZMAT")
	private boolean hazmat;
	//	HAZMAT	CHAR(1 BYTE)
	
	@Column(name = "WEIGHT")
	private Double weight;
	//	WEIGHT	NUMBER(18,0)
	
	@Column(name = "VOLUME")
	private Double volume;
	//	VOLUME	NUMBER(18,0)
	
	@Column(name = "CARRIERNAME", length = 50)
	private String carrierName;
	//	CARRIERNAME	VARCHAR2(50 BYTE)
	
	@Type(type="yes_no")
	@Column(name = "EMPTYCONATINERNEEDED")
	private boolean emptyConatinerNeeded;
	//	EMPTYCONATINERNEEDED	CHAR(1 BYTE)
	
	@Column(name = "EMPTYCONTPICKUP_LOCID", length = 200)
	private String emptyContPickup_locId;
	//	EMPTYCONTPICKUP_LOCID	VARCHAR2(200 BYTE)
	
	@Column(name = "EMPTYCONTDROPOFF_LOCID", length = 200)
	private String emptyContDropoff_locId;
	//	EMPTYCONTDROPOFF_LOCID	VARCHAR2(200 BYTE)
	
	@Column(name = "NOTES", length = 600)
	private String notes;
	//	NOTES	VARCHAR2(600 BYTE)
						   
	@OneToMany(mappedBy = "bookingLink", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<BookingDetails> bookingDetails;

	
	public Booking() {
		
	}


	public Long getBookingId() {
		return bookingId;
	}


	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}


	public Date getBookingDate() {
		return bookingDate;
	}


	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}


	public String getShipmentReferenceNum() {
		return shipmentReferenceNum;
	}


	public void setShipmentReferenceNum(String shipmentReferenceNum) {
		this.shipmentReferenceNum = shipmentReferenceNum;
	}


	public String getShipper() {
		return shipper;
	}


	public void setShipper(String shipper) {
		this.shipper = shipper;
	}


	public String getConsignee() {
		return consignee;
	}


	public void setConsignee(String consignee) {
		this.consignee = consignee;
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


	public String getMoveType() {
		return moveType;
	}


	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}


	public String getCommodity() {
		return commodity;
	}


	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}


	public String getLoadType() {
		return loadType;
	}


	public void setLoadType(String loadType) {
		this.loadType = loadType;
	}


	public String getEquipmentDimensions() {
		return equipmentDimensions;
	}


	public void setEquipmentDimensions(String equipmentDimensions) {
		this.equipmentDimensions = equipmentDimensions;
	}


	public Long getNumOfContainers() {
		return numOfContainers;
	}


	public void setNumOfContainers(Long numOfContainers) {
		this.numOfContainers = numOfContainers;
	}


	public Date getRequestedPickupDate() {
		return requestedPickupDate;
	}


	public void setRequestedPickupDate(Date requestedPickupDate) {
		this.requestedPickupDate = requestedPickupDate;
	}


	public Date getRequestedDeliveryDate() {
		return requestedDeliveryDate;
	}


	public void setRequestedDeliveryDate(Date requestedDeliveryDate) {
		this.requestedDeliveryDate = requestedDeliveryDate;
	}


	public String getIncoTerm() {
		return incoTerm;
	}


	public void setIncoTerm(String incoTerm) {
		this.incoTerm = incoTerm;
	}


	public boolean isHazmat() {
		return hazmat;
	}


	public void setHazmat(boolean hazmat) {
		this.hazmat = hazmat;
	}


	public Double getWeight() {
		return weight;
	}


	public void setWeight(Double weight) {
		this.weight = weight;
	}


	public Double getVolume() {
		return volume;
	}


	public void setVolume(Double volume) {
		this.volume = volume;
	}


	public String getCarrierName() {
		return carrierName;
	}


	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}


	public boolean isEmptyConatinerNeeded() {
		return emptyConatinerNeeded;
	}


	public void setEmptyConatinerNeeded(boolean emptyConatinerNeeded) {
		this.emptyConatinerNeeded = emptyConatinerNeeded;
	}


	public String getEmptyContPickup_locId() {
		return emptyContPickup_locId;
	}


	public void setEmptyContPickup_locId(String emptyContPickup_locId) {
		this.emptyContPickup_locId = emptyContPickup_locId;
	}


	public String getEmptyContDropoff_locId() {
		return emptyContDropoff_locId;
	}


	public void setEmptyContDropoff_locId(String emptyContDropoff_locId) {
		this.emptyContDropoff_locId = emptyContDropoff_locId;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public List<BookingDetails> getBookingDetails() {
		return bookingDetails;
	}


	public void setBookingDetails(List<BookingDetails> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}
	
}
