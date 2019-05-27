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

/**
 * ShipmentLegs entity.
 */
@Entity
@Table(name = "SHIPMENTLEGS")
public class ShipmentLegs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6252521691100228971L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	@Column(name = "SHIPMENTLEGID")
	private Long shipmentLegId;

	@Column(name = "SHIPMENTID")
	private Long shipmentId;

	@Column(name = "WORKORDERID")
	private Long woBookingId;

	@Column(name = "WORKORDERNUMBER")
	private String woBookingNumber;

	@Column(name = "LEGMODE")
	private String legMode;

	@Column(name = "LEGSEQUENCE")
	private String legSequence;

	@Column(name = "ORIGINATORID")
	private Long originatorid;

	@Column(name = "RECEIVERID")
	private Long receiverid;

	@Column(name = "ORIGINATORNAME")
	private String originatorName;

	@Column(name = "RECEIVERNAME")
	private String receiverName;

	@Column(name = "REPORTSOURCE")
	private String reportSource;

	@Column(name = "TRANSACTIONCODE")
	private String transactionCode;

	@Column(name = "WORKORDERDATE")
	private Date woBookingDate;

	@Column(name = "CARRIERCODE")
	private String carrierCode;

	@Column(name = "FREIGHTDESCRIPTION")
	private String freightDescription;

	@Column(name = "SHIPMENTREFERENCENUMBER")
	private String shipmentReferenceNumber;

	@Column(name = "PAYMENTINDICATOR")
	private String paymentIndicator;

	@Column(name = "BILLOFLADINGNUMBER")
	private String billOfLadingNumber;

	@Column(name = "BOOKINGNUMBER")
	private String bookingNumber;

	@Column(name = "RAILBILLINGNUMBER")
	private String railBillingNumber;

	@Column(name = "VESSEL")
	private String vessel;

	@Column(name = "VOYAGE")
	private String voyage;

	@Column(name = "VENDORNUMBER")
	private String vendorNumber;

	@Column(name = "MASTERAIRWAYBILLNUMBER")
	private String masterAirwayBillNumber;

	@Column(name = "HOUSEAIRWAYBILLNUMBER")
	private String houseAirwayBillNumber;

	@Column(name = "REFERENCEVALUE")
	private String referenceValue;

	@Column(name = "ORIGINATORONHANDNUMBER")
	private String originatorOnHandNumber;

	@Column(name = "ORIGINATORORDERREFERENCE")
	private String originatorOrderReference;

	@Column(name = "ORIGINATORIMPORTREFNUMBER")
	private String originatorImportRefNumber;

	@Column(name = "RESPONDBYDATE")
	private Date respondByDate;

	@Column(name = "EXPORTCUTOFFDATE")
	private Date exportCutOffDate;

	@Column(name = "LASTFREEDAY")
	private Date lastFreeDay;

	@Column(name = "ETA")
	private Date eta;

	@Column(name = "PORTOFLOADING")
	private String portOfLoading;

	@Column(name = "PORTOFDISCHARGE")
	private String portOfDischarge;

	@Column(name = "LOADLOCATION")
	private String loadLocation;

	@Column(name = "DISCHARGELOCATION")
	private String dischargeLocation;

	@Column(name = "SHIPPER")
	private String shipper;

	@Column(name = "BILLTO")
	private String billTo;

	@Column(name = "STATUS")
	private String woStatus;

	@Column(name = "STATUSID")
	private Long statusID;

	@Column(name = "UNITID")
	private String unitId;

	@Column(name = "UNITTYPECODE")
	private String unitTypeCode;

	@Column(name = "ASSOCIATEDUNITID")
	private String associatedUnitId;

	@Column(name = "PIECECOUNT")
	private Long pieceCount;

	@Column(name = "WEIGHT")
	private Long weight;

	@Column(name = "LENGTH")
	private Long lenght;

	@Column(name = "HEIGHT")
	private Long height;

	@Column(name = "WIDTH")
	private Long width;

	@Column(name = "ORIGIN")
	private String origin;

	@Column(name = "ORIGINADDRESS1")
	private String originAddress1;

	@Column(name = "ORIGINADDRESS2")
	private String originAddress2;

	@Column(name = "ORIGINCITY")
	private String originCity;

	@Column(name = "ORIGINSTATE")
	private String originState;

	@Column(name = "ORIGINZIPCODE")
	private String originZipcode;

	@Column(name = "ORIGINCOUNTRY")
	private String originCountry;

	@Column(name = "DESTINATION")
	private String destination;

	@Column(name = "DESTINATIONADDRESS1")
	private String destinationAddress1;

	@Column(name = "DESTINATIONADDRESS2")
	private String destinationAddress2;

	@Column(name = "DESTINATIONCITY")
	private String destinationCity;

	@Column(name = "DESTINATIONSTATE")
	private String destinationState;

	@Column(name = "DESTINATIONZIPCODE")
	private String destinationZipCode;

	@Column(name = "DESTINATIONCOUNTRY")
	private String destinationCountry;

	/**
	 * @return the shipmentLegId
	 */
	public Long getShipmentLegId() {
		return shipmentLegId;
	}

	/**
	 * @param shipmentLegId
	 *            the shipmentLegId to set
	 */
	public void setShipmentLegId(Long shipmentLegId) {
		this.shipmentLegId = shipmentLegId;
	}

	/**
	 * @return the shipmentId
	 */
	public Long getShipmentId() {
		return shipmentId;
	}

	/**
	 * @param shipmentId the shipmentId to set
	 */
	public void setShipmentId(Long shipmentId) {
		this.shipmentId = shipmentId;
	}

	/**
	 * @return the woBookingId
	 */
	public Long getWoBookingId() {
		return woBookingId;
	}

	/**
	 * @param woBookingId
	 *            the woBookingId to set
	 */
	public void setWoBookingId(Long woBookingId) {
		this.woBookingId = woBookingId;
	}

	/**
	 * @return the woBookingNumber
	 */
	public String getWoBookingNumber() {
		return woBookingNumber;
	}

	/**
	 * @param woBookingNumber
	 *            the woBookingNumber to set
	 */
	public void setWoBookingNumber(String woBookingNumber) {
		this.woBookingNumber = woBookingNumber;
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
	 * @return the legSequence
	 */
	public String getLegSequence() {
		return legSequence;
	}

	/**
	 * @param legSequence
	 *            the legSequence to set
	 */
	public void setLegSequence(String legSequence) {
		this.legSequence = legSequence;
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
	 * @return the receiverid
	 */
	public Long getReceiverid() {
		return receiverid;
	}

	/**
	 * @param receiverid
	 *            the receiverid to set
	 */
	public void setReceiverid(Long receiverid) {
		this.receiverid = receiverid;
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
	 * @return the receiverName
	 */
	public String getReceiverName() {
		return receiverName;
	}

	/**
	 * @param receiverName
	 *            the receiverName to set
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	/**
	 * @return the reportSource
	 */
	public String getReportSource() {
		return reportSource;
	}

	/**
	 * @param reportSource
	 *            the reportSource to set
	 */
	public void setReportSource(String reportSource) {
		this.reportSource = reportSource;
	}

	/**
	 * @return the transactionCode
	 */
	public String getTransactionCode() {
		return transactionCode;
	}

	/**
	 * @param transactionCode
	 *            the transactionCode to set
	 */
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	/**
	 * @return the woBookingDate
	 */
	public Date getWoBookingDate() {
		return woBookingDate;
	}

	/**
	 * @param woBookingDate
	 *            the woBookingDate to set
	 */
	public void setWoBookingDate(Date woBookingDate) {
		this.woBookingDate = woBookingDate;
	}

	/**
	 * @return the carrierCode
	 */
	public String getCarrierCode() {
		return carrierCode;
	}

	/**
	 * @param carrierCode
	 *            the carrierCode to set
	 */
	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	/**
	 * @return the freightDescription
	 */
	public String getFreightDescription() {
		return freightDescription;
	}

	/**
	 * @param freightDescription
	 *            the freightDescription to set
	 */
	public void setFreightDescription(String freightDescription) {
		this.freightDescription = freightDescription;
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
	 * @return the paymentIndicator
	 */
	public String getPaymentIndicator() {
		return paymentIndicator;
	}

	/**
	 * @param paymentIndicator
	 *            the paymentIndicator to set
	 */
	public void setPaymentIndicator(String paymentIndicator) {
		this.paymentIndicator = paymentIndicator;
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

	/**
	 * @return the masterAirwayBillNumber
	 */
	public String getMasterAirwayBillNumber() {
		return masterAirwayBillNumber;
	}

	/**
	 * @param masterAirwayBillNumber
	 *            the masterAirwayBillNumber to set
	 */
	public void setMasterAirwayBillNumber(String masterAirwayBillNumber) {
		this.masterAirwayBillNumber = masterAirwayBillNumber;
	}

	/**
	 * @return the houseAirwayBillNumber
	 */
	public String getHouseAirwayBillNumber() {
		return houseAirwayBillNumber;
	}

	/**
	 * @param houseAirwayBillNumber
	 *            the houseAirwayBillNumber to set
	 */
	public void setHouseAirwayBillNumber(String houseAirwayBillNumber) {
		this.houseAirwayBillNumber = houseAirwayBillNumber;
	}

	/**
	 * @return the referenceValue
	 */
	public String getReferenceValue() {
		return referenceValue;
	}

	/**
	 * @param referenceValue
	 *            the referenceValue to set
	 */
	public void setReferenceValue(String referenceValue) {
		this.referenceValue = referenceValue;
	}

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
	 * @return the respondByDate
	 */
	public Date getRespondByDate() {
		return respondByDate;
	}

	/**
	 * @param respondByDate
	 *            the respondByDate to set
	 */
	public void setRespondByDate(Date respondByDate) {
		this.respondByDate = respondByDate;
	}

	/**
	 * @return the exportCutOffDate
	 */
	public Date getExportCutOffDate() {
		return exportCutOffDate;
	}

	/**
	 * @param exportCutOffDate
	 *            the exportCutOffDate to set
	 */
	public void setExportCutOffDate(Date exportCutOffDate) {
		this.exportCutOffDate = exportCutOffDate;
	}

	/**
	 * @return the lastFreeDay
	 */
	public Date getLastFreeDay() {
		return lastFreeDay;
	}

	/**
	 * @param lastFreeDay
	 *            the lastFreeDay to set
	 */
	public void setLastFreeDay(Date lastFreeDay) {
		this.lastFreeDay = lastFreeDay;
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
	 * @return the portOfLoading
	 */
	public String getPortOfLoading() {
		return portOfLoading;
	}

	/**
	 * @param portOfLoading
	 *            the portOfLoading to set
	 */
	public void setPortOfLoading(String portOfLoading) {
		this.portOfLoading = portOfLoading;
	}

	/**
	 * @return the portOfDischarge
	 */
	public String getPortOfDischarge() {
		return portOfDischarge;
	}

	/**
	 * @param portOfDischarge
	 *            the portOfDischarge to set
	 */
	public void setPortOfDischarge(String portOfDischarge) {
		this.portOfDischarge = portOfDischarge;
	}

	/**
	 * @return the loadLocation
	 */
	public String getLoadLocation() {
		return loadLocation;
	}

	/**
	 * @param loadLocation
	 *            the loadLocation to set
	 */
	public void setLoadLocation(String loadLocation) {
		this.loadLocation = loadLocation;
	}

	/**
	 * @return the dischargeLocation
	 */
	public String getDischargeLocation() {
		return dischargeLocation;
	}

	/**
	 * @param dischargeLocation
	 *            the dischargeLocation to set
	 */
	public void setDischargeLocation(String dischargeLocation) {
		this.dischargeLocation = dischargeLocation;
	}

	/**
	 * @return the shipper
	 */
	public String getShipper() {
		return shipper;
	}

	/**
	 * @param shipper
	 *            the shipper to set
	 */
	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	/**
	 * @return the billTo
	 */
	public String getBillTo() {
		return billTo;
	}

	/**
	 * @param billTo
	 *            the billTo to set
	 */
	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}

	/**
	 * @return the woStatus
	 */
	public String getWoStatus() {
		return woStatus;
	}

	/**
	 * @param woStatus
	 *            the woStatus to set
	 */
	public void setWoStatus(String woStatus) {
		this.woStatus = woStatus;
	}

	/**
	 * @return the statusID
	 */
	public Long getStatusID() {
		return statusID;
	}

	/**
	 * @param statusID
	 *            the statusID to set
	 */
	public void setStatusID(Long statusID) {
		this.statusID = statusID;
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
	 * @return the unitTypeCode
	 */
	public String getUnitTypeCode() {
		return unitTypeCode;
	}

	/**
	 * @param unitTypeCode
	 *            the unitTypeCode to set
	 */
	public void setUnitTypeCode(String unitTypeCode) {
		this.unitTypeCode = unitTypeCode;
	}

	/**
	 * @return the associatedUnitId
	 */
	public String getAssociatedUnitId() {
		return associatedUnitId;
	}

	/**
	 * @param associatedUnitId
	 *            the associatedUnitId to set
	 */
	public void setAssociatedUnitId(String associatedUnitId) {
		this.associatedUnitId = associatedUnitId;
	}

	/**
	 * @return the pieceCount
	 */
	public Long getPieceCount() {
		return pieceCount;
	}

	/**
	 * @param pieceCount
	 *            the pieceCount to set
	 */
	public void setPieceCount(Long pieceCount) {
		this.pieceCount = pieceCount;
	}

	/**
	 * @return the weight
	 */
	public Long getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(Long weight) {
		this.weight = weight;
	}

	/**
	 * @return the lenght
	 */
	public Long getLenght() {
		return lenght;
	}

	/**
	 * @param lenght
	 *            the lenght to set
	 */
	public void setLenght(Long lenght) {
		this.lenght = lenght;
	}

	/**
	 * @return the height
	 */
	public Long getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(Long height) {
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public Long getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(Long width) {
		this.width = width;
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
	 * @return the originAddress1
	 */
	public String getOriginAddress1() {
		return originAddress1;
	}

	/**
	 * @param originAddress1
	 *            the originAddress1 to set
	 */
	public void setOriginAddress1(String originAddress1) {
		this.originAddress1 = originAddress1;
	}

	/**
	 * @return the originAddress2
	 */
	public String getOriginAddress2() {
		return originAddress2;
	}

	/**
	 * @param originAddress2
	 *            the originAddress2 to set
	 */
	public void setOriginAddress2(String originAddress2) {
		this.originAddress2 = originAddress2;
	}

	/**
	 * @return the originCity
	 */
	public String getOriginCity() {
		return originCity;
	}

	/**
	 * @param originCity
	 *            the originCity to set
	 */
	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	/**
	 * @return the originState
	 */
	public String getOriginState() {
		return originState;
	}

	/**
	 * @param originState
	 *            the originState to set
	 */
	public void setOriginState(String originState) {
		this.originState = originState;
	}

	/**
	 * @return the originZipcode
	 */
	public String getOriginZipcode() {
		return originZipcode;
	}

	/**
	 * @param originZipcode
	 *            the originZipcode to set
	 */
	public void setOriginZipcode(String originZipcode) {
		this.originZipcode = originZipcode;
	}

	/**
	 * @return the originCountry
	 */
	public String getOriginCountry() {
		return originCountry;
	}

	/**
	 * @param originCountry
	 *            the originCountry to set
	 */
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
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
	 * @return the destinationAddress1
	 */
	public String getDestinationAddress1() {
		return destinationAddress1;
	}

	/**
	 * @param destinationAddress1
	 *            the destinationAddress1 to set
	 */
	public void setDestinationAddress1(String destinationAddress1) {
		this.destinationAddress1 = destinationAddress1;
	}

	/**
	 * @return the destinationAddress2
	 */
	public String getDestinationAddress2() {
		return destinationAddress2;
	}

	/**
	 * @param destinationAddress2
	 *            the destinationAddress2 to set
	 */
	public void setDestinationAddress2(String destinationAddress2) {
		this.destinationAddress2 = destinationAddress2;
	}

	/**
	 * @return the destinationCity
	 */
	public String getDestinationCity() {
		return destinationCity;
	}

	/**
	 * @param destinationCity
	 *            the destinationCity to set
	 */
	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	/**
	 * @return the destinationState
	 */
	public String getDestinationState() {
		return destinationState;
	}

	/**
	 * @param destinationState
	 *            the destinationState to set
	 */
	public void setDestinationState(String destinationState) {
		this.destinationState = destinationState;
	}

	/**
	 * @return the destinationZipCode
	 */
	public String getDestinationZipCode() {
		return destinationZipCode;
	}

	/**
	 * @param destinationZipCode
	 *            the destinationZipCode to set
	 */
	public void setDestinationZipCode(String destinationZipCode) {
		this.destinationZipCode = destinationZipCode;
	}

	/**
	 * @return the destinationCountry
	 */
	public String getDestinationCountry() {
		return destinationCountry;
	}

	/**
	 * @param destinationCountry
	 *            the destinationCountry to set
	 */
	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

}
