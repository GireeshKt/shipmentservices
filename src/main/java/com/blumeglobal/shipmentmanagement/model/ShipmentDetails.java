package com.blumeglobal.shipmentmanagement.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ShipmentDetails entity.
 */
@Entity
@Table(name = "SHIPMENTDETAILS")
public class ShipmentDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7492997785914512975L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	@Column(name = "SHIPMENTID")
	private Long shipmentId;

	@Column(name = "ORIGINATORID")
	private Long originatorid;

	@Column(name = "ORIGINATORNAME")
	private String originatorName;

	@Column(name = "LEGMODE")
	private String legMode;

	@Column(name = "SHIPMENTREFERENCENUMBER")
	private String shipmentReferenceNumber;

	@Column(name = "BILLOFLADINGNUMBER")
	private String billOfLadingNumber;

	@Column(name = "BOOKINGNUMBER")
	private String bookingNumber;

	@Column(name = "RAILBILLINGNUMBER")
	private String railBillingNumber;

	@Column(name = "UNITID")
	private String unitId;

	@Column(name = "VESSEL")
	private String vessel;

	@Column(name = "VOYAGE")
	private String voyage;

	@Column(name = "VENDORNUMBER")
	private String vendorNumber;

	// @Column(name = "MASTERAIRWAYBILLNUMBER")
	// private String masterAirwayBillNumber;
	//
	// @Column(name = "HOUSEAIRWAYBILLNUMBER")
	// private String houseAirwayBillNumber;

	@Column(name = "ORIGINATORONHANDNUMBER")
	private String originatorOnHandNumber;

	@Column(name = "ORIGINATORORDERREFERENCE")
	private String originatorOrderReference;

	@Column(name = "ORIGINATORIMPORTREFNUMBER")
	private String originatorImportRefNumber;

	@Column(name = "PURCHASEORDER")
	private String purchaseOrder;

	@Column(name = "CUSTOMERREFERENCENUMBER")
	private String customerReferenceNumber;

	@Column(name = "CUSTOMERORDERNUMBER")
	private String customerOrderNumber;

	@Column(name = "ETA")
	private Date eta;

	@Column(name = "ASNREFERENCENUMBER")
	private String asnReferenceNumber;

	@Column(name = "ORIGIN")
	private String origin;

	@Column(name = "DESTINATION")
	private String destination;

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "SHIPMENTID")
	private List<ShipmentLegs> legs;

	/**
	 * @return the shipmentId
	 */
	public Long getShipmentId() {
		return shipmentId;
	}

	/**
	 * @param shipmentId
	 *            the shipmentId to set
	 */
	public void setShipmentId(Long shipmentId) {
		this.shipmentId = shipmentId;
	}

	/**
	 * @return the originatorid
	 */
	public Long getOriginatorid() {
		return originatorid;
	}

	/**
	 * @param originatorid
	 *            the originatorid to set
	 */
	public void setOriginatorid(Long originatorid) {
		this.originatorid = originatorid;
	}

	/**
	 * @return the originatorName
	 */
	public String getOriginatorName() {
		return originatorName;
	}

	/**
	 * @param originatorName
	 *            the originatorName to set
	 */
	public void setOriginatorName(String originatorName) {
		this.originatorName = originatorName;
	}

	/**
	 * @return the legMode
	 */
	public String getLegMode() {
		return legMode;
	}

	/**
	 * @param legMode
	 *            the legMode to set
	 */
	public void setLegMode(String legMode) {
		this.legMode = legMode;
	}

	/**
	 * @return the shipmentReferenceNumber
	 */
	public String getShipmentReferenceNumber() {
		return shipmentReferenceNumber;
	}

	/**
	 * @param shipmentReferenceNumber
	 *            the shipmentReferenceNumber to set
	 */
	public void setShipmentReferenceNumber(String shipmentReferenceNumber) {
		this.shipmentReferenceNumber = shipmentReferenceNumber;
	}

	/**
	 * @return the billOfLadingNumber
	 */
	public String getBillOfLadingNumber() {
		return billOfLadingNumber;
	}

	/**
	 * @param billOfLadingNumber
	 *            the billOfLadingNumber to set
	 */
	public void setBillOfLadingNumber(String billOfLadingNumber) {
		this.billOfLadingNumber = billOfLadingNumber;
	}

	/**
	 * @return the bookingNumber
	 */
	public String getBookingNumber() {
		return bookingNumber;
	}

	/**
	 * @param bookingNumber
	 *            the bookingNumber to set
	 */
	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	/**
	 * @return the railBillingNumber
	 */
	public String getRailBillingNumber() {
		return railBillingNumber;
	}

	/**
	 * @param railBillingNumber
	 *            the railBillingNumber to set
	 */
	public void setRailBillingNumber(String railBillingNumber) {
		this.railBillingNumber = railBillingNumber;
	}

	/**
	 * @return the unitId
	 */
	public String getUnitId() {
		return unitId;
	}

	/**
	 * @param unitId
	 *            the unitId to set
	 */
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	/**
	 * @return the vessel
	 */
	public String getVessel() {
		return vessel;
	}

	/**
	 * @param vessel
	 *            the vessel to set
	 */
	public void setVessel(String vessel) {
		this.vessel = vessel;
	}

	/**
	 * @return the voyage
	 */
	public String getVoyage() {
		return voyage;
	}

	/**
	 * @param voyage
	 *            the voyage to set
	 */
	public void setVoyage(String voyage) {
		this.voyage = voyage;
	}

	/**
	 * @return the vendorNumber
	 */
	public String getVendorNumber() {
		return vendorNumber;
	}

	/**
	 * @param vendorNumber
	 *            the vendorNumber to set
	 */
	public void setVendorNumber(String vendorNumber) {
		this.vendorNumber = vendorNumber;
	}

	// /**
	// * @return the masterAirwayBillNumber
	// */
	// public String getMasterAirwayBillNumber() {
	// return masterAirwayBillNumber;
	// }
	//
	// /**
	// * @param masterAirwayBillNumber
	// * the masterAirwayBillNumber to set
	// */
	// public void setMasterAirwayBillNumber(String masterAirwayBillNumber) {
	// this.masterAirwayBillNumber = masterAirwayBillNumber;
	// }

	// /**
	// * @return the houseAirwayBillNumber
	// */
	// public String getHouseAirwayBillNumber() {
	// return houseAirwayBillNumber;
	// }
	//
	// /**
	// * @param houseAirwayBillNumber
	// * the houseAirwayBillNumber to set
	// */
	// public void setHouseAirwayBillNumber(String houseAirwayBillNumber) {
	// this.houseAirwayBillNumber = houseAirwayBillNumber;
	// }

	/**
	 * @return the originatorOnHandNumber
	 */
	public String getOriginatorOnHandNumber() {
		return originatorOnHandNumber;
	}

	/**
	 * @param originatorOnHandNumber
	 *            the originatorOnHandNumber to set
	 */
	public void setOriginatorOnHandNumber(String originatorOnHandNumber) {
		this.originatorOnHandNumber = originatorOnHandNumber;
	}

	/**
	 * @return the originatorOrderReference
	 */
	public String getOriginatorOrderReference() {
		return originatorOrderReference;
	}

	/**
	 * @param originatorOrderReference
	 *            the originatorOrderReference to set
	 */
	public void setOriginatorOrderReference(String originatorOrderReference) {
		this.originatorOrderReference = originatorOrderReference;
	}

	/**
	 * @return the originatorImportRefNumber
	 */
	public String getOriginatorImportRefNumber() {
		return originatorImportRefNumber;
	}

	/**
	 * @param originatorImportRefNumber
	 *            the originatorImportRefNumber to set
	 */
	public void setOriginatorImportRefNumber(String originatorImportRefNumber) {
		this.originatorImportRefNumber = originatorImportRefNumber;
	}

	/**
	 * @return the purchaseOrder
	 */
	public String getPurchaseOrder() {
		return purchaseOrder;
	}

	/**
	 * @param purchaseOrder
	 *            the purchaseOrder to set
	 */
	public void setPurchaseOrder(String purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	/**
	 * @return the customerReferenceNumber
	 */
	public String getCustomerReferenceNumber() {
		return customerReferenceNumber;
	}

	/**
	 * @param customerReferenceNumber
	 *            the customerReferenceNumber to set
	 */
	public void setCustomerReferenceNumber(String customerReferenceNumber) {
		this.customerReferenceNumber = customerReferenceNumber;
	}

	/**
	 * @return the customerOrderNumber
	 */
	public String getCustomerOrderNumber() {
		return customerOrderNumber;
	}

	/**
	 * @param customerOrderNumber
	 *            the customerOrderNumber to set
	 */
	public void setCustomerOrderNumber(String customerOrderNumber) {
		this.customerOrderNumber = customerOrderNumber;
	}

	/**
	 * @return the eta
	 */
	public Date getEta() {
		return eta;
	}

	/**
	 * @param eta
	 *            the eta to set
	 */
	public void setEta(Date eta) {
		this.eta = eta;
	}

	/**
	 * @return the asnReferenceNumber
	 */
	public String getAsnReferenceNumber() {
		return asnReferenceNumber;
	}

	/**
	 * @param asnReferenceNumber
	 *            the asnReferenceNumber to set
	 */
	public void setAsnReferenceNumber(String asnReferenceNumber) {
		this.asnReferenceNumber = asnReferenceNumber;
	}

	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * @param origin
	 *            the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @param destination
	 *            the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * @return the legs
	 */
	public List<ShipmentLegs> getLegs() {
		return legs;
	}

	/**
	 * @param legs
	 *            the legs to set
	 */
	public void setLegs(List<ShipmentLegs> legs) {
		this.legs = legs;
	}

	/**
	 * @param shipmentId
	 * @param originatorid
	 * @param originatorName
	 * @param legMode
	 * @param shipmentReferenceNumber
	 * @param billOfLadingNumber
	 * @param bookingNumber
	 * @param railBillingNumber
	 * @param unitId
	 * @param vessel
	 * @param voyage
	 * @param vendorNumber
	 * @param masterAirwayBillNumber
	 * @param houseAirwayBillNumber
	 * @param originatorOnHandNumber
	 * @param originatorOrderReference
	 * @param originatorImportRefNumber
	 * @param purchaseOrder
	 * @param customerReferenceNumber
	 * @param customerOrderNumber
	 * @param eta
	 * @param asnReferenceNumber
	 * @param origin
	 * @param destination
	 */
	public ShipmentDetails(Long shipmentId, Long originatorid, String originatorName, String legMode,
			String shipmentReferenceNumber, String billOfLadingNumber, String bookingNumber, String railBillingNumber,
			String unitId, String vessel, String voyage, String vendorNumber, String masterAirwayBillNumber,
			String houseAirwayBillNumber, String originatorOnHandNumber, String originatorOrderReference,
			String originatorImportRefNumber, String purchaseOrder, String customerReferenceNumber,
			String customerOrderNumber, Date eta, String asnReferenceNumber, String origin, String destination) {
		super();
		this.shipmentId = shipmentId;
		this.originatorid = originatorid;
		this.originatorName = originatorName;
		this.legMode = legMode;
		this.shipmentReferenceNumber = shipmentReferenceNumber;
		this.billOfLadingNumber = billOfLadingNumber;
		this.bookingNumber = bookingNumber;
		this.railBillingNumber = railBillingNumber;
		this.unitId = unitId;
		this.vessel = vessel;
		this.voyage = voyage;
		this.vendorNumber = vendorNumber;
		// this.masterAirwayBillNumber = masterAirwayBillNumber;
		// this.houseAirwayBillNumber = houseAirwayBillNumber;
		this.originatorOnHandNumber = originatorOnHandNumber;
		this.originatorOrderReference = originatorOrderReference;
		this.originatorImportRefNumber = originatorImportRefNumber;
		this.purchaseOrder = purchaseOrder;
		this.customerReferenceNumber = customerReferenceNumber;
		this.customerOrderNumber = customerOrderNumber;
		this.eta = eta;
		this.asnReferenceNumber = asnReferenceNumber;
		this.origin = origin;
		this.destination = destination;
	}

	/**
	 * 
	 */
	public ShipmentDetails() {
		super();
	}

}
