package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;
import java.util.Date;

public class ShipmentWorkOrderRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long workorderid;
	//	“workorderid”,
	
	// @Size(max = 20, message = "referenceType must be maximum of 20 characters")
	private String workordernumber;
	//    “workordernumber”,VARCHAR2(60 BYTE)
	
	private String category;
	//    “category”,CATEGORY	VARCHAR2(50 BYTE)
	
	private String vessel;
	//    “vessel”,VESSEL	VARCHAR2(50 BYTE)
	
	private String voyage;
	//    “voyage”,VOYAGE	VARCHAR2(50 BYTE)
	
	private Date eta;
	//    “eta”,ETA	DATE
	
	private String portofloading;
	//    “portofloading”,PORTOFLOADING	VARCHAR2(50 BYTE)
	
	private String portofdischarge;
	//    “portofdischarge”,PORTOFDISCHARGE	VARCHAR2(50 BYTE)
	
	private String shipmentReferenceNumber;
	//    “shipmentReferenceNumber”,SHIPMENTREFERENCENUMBER	VARCHAR2(50 BYTE)
	
	private String unitId;
	
	private String masterAirWayBillNumber;
	//    “masterAirWayBillNumber”,MASTERAIRWAYBILLNUMBER	VARCHAR2(50 BYTE)
	
	private String houseAirWayBillNumber;
	//    “houseAirWayBillNumber”,HOUSEAIRWAYBILLNUMBER	VARCHAR2(50 BYTE)
	
	private Date exportcutoffdate;
	//    “exportcutoffdate”,EXPORTCUTOFFDATE	DATE
	
	private String bookingnumber;
	//    “bookingnumber”,BOOKINGNUMBER	VARCHAR2(50 BYTE)
	
	private String originatorname;
	//    “originatorname”,ORIGINATORNAME	VARCHAR2(250 BYTE)
	
	private Date dateCreated;
	//    “dateCreated”,DATECREATED	DATE
	
	private Date dateCompleted;
	//    “dateCompleted”,DATECOMPLETED	DATE
	
	private String billofladingnumber;
	//    “billofladingnumber”,BILLOFLADINGNUMBER	VARCHAR2(50 BYTE)
	
	private String railbillingnumber;
	//    “railbillingnumber”,RAILBILLINGNUMBER	VARCHAR2(50 BYTE)
	
	private String origin_name;
	//    “origin_name”,NAME	VARCHAR2(200 BYTE)
	
	private String origin_address1;
	//    “origin_address1",ADDRESS1	VARCHAR2(200 BYTE)
	
	private String origin_address2;
	//    “origin_address2”,ADDRESS2	VARCHAR2(200 BYTE)
	
	private String origin_city;
	//    “origin_city”,CITY	VARCHAR2(50 BYTE)
	
	private String origin_state;
	//    “origin_state”,STATE	VARCHAR2(10 BYTE)
	
	private String origin_postalcode;
	//    “origin_postalcode”,POSTALCODE	VARCHAR2(15 BYTE)
	
	private String origin_country;
	//    “origin_country”,COUNTRY	VARCHAR2(2 BYTE)
	
	private String destination_name;
	//    “destination_name”,NAME	VARCHAR2(200 BYTE)
	
	private String destination_address1;
	//    “destination_address1",ADDRESS1	VARCHAR2(200 BYTE)
	
	private String destination_address2;
	//    “destination_address2”,ADDRESS2	VARCHAR2(200 BYTE)
	
	private String destination_city;
	//    “destination_city”,CITY	VARCHAR2(50 BYTE)
	
	private String destination_state;
	//    “destination_state”,STATE	VARCHAR2(10 BYTE)
	
	private String destination_postalcode;
	//    “destination_postalcode”,POSTALCODE	VARCHAR2(15 BYTE)
	
	private String destination_country;
	//    “destination_country”COUNTRY	VARCHAR2(2 BYTE)

	private String statusDescription;
	
	private String originatorOrderReference;
	
	//TODO add source

	private String originatorCode;
	
	private String shipper;
	
	private String carrierCode;
	
	private ShipmentStopListRequest shipmentStopListRequest;
	
	private Date workorderdate;

	public ShipmentWorkOrderRequest() {
		
	}


	public Long getWorkorderid() {
		return workorderid;
	}


	public void setWorkorderid(Long workorderid) {
		this.workorderid = workorderid;
	}


	public String getWorkordernumber() {
		return workordernumber;
	}


	public void setWorkordernumber(String workordernumber) {
		this.workordernumber = workordernumber;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getVessel() {
		return vessel;
	}


	public void setVessel(String vessel) {
		this.vessel = vessel;
	}


	public String getVoyage() {
		return voyage;
	}


	public void setVoyage(String voyage) {
		this.voyage = voyage;
	}


	public Date getEta() {
		return eta;
	}


	public void setEta(Date eta) {
		this.eta = eta;
	}


	public String getPortofloading() {
		return portofloading;
	}


	public void setPortofloading(String portofloading) {
		this.portofloading = portofloading;
	}


	public String getPortofdischarge() {
		return portofdischarge;
	}


	public void setPortofdischarge(String portofdischarge) {
		this.portofdischarge = portofdischarge;
	}


	public String getShipmentReferenceNumber() {
		return shipmentReferenceNumber;
	}


	public void setShipmentReferenceNumber(String shipmentReferenceNumber) {
		this.shipmentReferenceNumber = shipmentReferenceNumber;
	}


	public String getUnitId() {
		return unitId;
	}


	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}


	public String getMasterAirWayBillNumber() {
		return masterAirWayBillNumber;
	}


	public void setMasterAirWayBillNumber(String masterAirWayBillNumber) {
		this.masterAirWayBillNumber = masterAirWayBillNumber;
	}


	public String getHouseAirWayBillNumber() {
		return houseAirWayBillNumber;
	}


	public void setHouseAirWayBillNumber(String houseAirWayBillNumber) {
		this.houseAirWayBillNumber = houseAirWayBillNumber;
	}


	public Date getExportcutoffdate() {
		return exportcutoffdate;
	}


	public void setExportcutoffdate(Date exportcutoffdate) {
		this.exportcutoffdate = exportcutoffdate;
	}


	public String getBookingnumber() {
		return bookingnumber;
	}


	public void setBookingnumber(String bookingnumber) {
		this.bookingnumber = bookingnumber;
	}


	public String getOriginatorname() {
		return originatorname;
	}


	public void setOriginatorname(String originatorname) {
		this.originatorname = originatorname;
	}


	public Date getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}


	public Date getDateCompleted() {
		return dateCompleted;
	}


	public void setDateCompleted(Date dateCompleted) {
		this.dateCompleted = dateCompleted;
	}


	public String getBillofladingnumber() {
		return billofladingnumber;
	}


	public void setBillofladingnumber(String billofladingnumber) {
		this.billofladingnumber = billofladingnumber;
	}


	public String getRailbillingnumber() {
		return railbillingnumber;
	}


	public void setRailbillingnumber(String railbillingnumber) {
		this.railbillingnumber = railbillingnumber;
	}


	public String getOrigin_name() {
		return origin_name;
	}


	public void setOrigin_name(String origin_name) {
		this.origin_name = origin_name;
	}


	public String getOrigin_address1() {
		return origin_address1;
	}


	public void setOrigin_address1(String origin_address1) {
		this.origin_address1 = origin_address1;
	}


	public String getOrigin_address2() {
		return origin_address2;
	}


	public void setOrigin_address2(String origin_address2) {
		this.origin_address2 = origin_address2;
	}


	public String getOrigin_city() {
		return origin_city;
	}


	public void setOrigin_city(String origin_city) {
		this.origin_city = origin_city;
	}


	public String getOrigin_state() {
		return origin_state;
	}


	public void setOrigin_state(String origin_state) {
		this.origin_state = origin_state;
	}


	public String getOrigin_postalcode() {
		return origin_postalcode;
	}


	public void setOrigin_postalcode(String origin_postalcode) {
		this.origin_postalcode = origin_postalcode;
	}


	public String getOrigin_country() {
		return origin_country;
	}


	public void setOrigin_country(String origin_country) {
		this.origin_country = origin_country;
	}


	public String getDestination_name() {
		return destination_name;
	}


	public void setDestination_name(String destination_name) {
		this.destination_name = destination_name;
	}


	public String getDestination_address1() {
		return destination_address1;
	}


	public void setDestination_address1(String destination_address1) {
		this.destination_address1 = destination_address1;
	}


	public String getDestination_address2() {
		return destination_address2;
	}


	public void setDestination_address2(String destination_address2) {
		this.destination_address2 = destination_address2;
	}


	public String getDestination_city() {
		return destination_city;
	}


	public void setDestination_city(String destination_city) {
		this.destination_city = destination_city;
	}


	public String getDestination_state() {
		return destination_state;
	}


	public void setDestination_state(String destination_state) {
		this.destination_state = destination_state;
	}


	public String getDestination_postalcode() {
		return destination_postalcode;
	}


	public void setDestination_postalcode(String destination_postalcode) {
		this.destination_postalcode = destination_postalcode;
	}


	public String getDestination_country() {
		return destination_country;
	}


	public void setDestination_country(String destination_country) {
		this.destination_country = destination_country;
	}


	public String getStatusDescription() {
		return statusDescription;
	}


	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}


	public String getOriginatorOrderReference() {
		return originatorOrderReference;
	}


	public void setOriginatorOrderReference(String originatorOrderReference) {
		this.originatorOrderReference = originatorOrderReference;
	}

	public String getOriginatorCode() {
		return originatorCode;
	}


	public void setOriginatorCode(String originatorCode) {
		this.originatorCode = originatorCode;
	}


	public String getShipper() {
		return shipper;
	}


	public void setShipper(String shipper) {
		this.shipper = shipper;
	}


	public String getCarrierCode() {
		return carrierCode;
	}


	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}


	public ShipmentStopListRequest getShipmentStopListRequest() {
		return shipmentStopListRequest;
	}


	public void setShipmentStopListRequest(ShipmentStopListRequest shipmentStopListRequest) {
		this.shipmentStopListRequest = shipmentStopListRequest;
	}


	public Date getWorkorderdate() {
		return workorderdate;
	}


	public void setWorkorderdate(Date workorderdate) {
		this.workorderdate = workorderdate;
	}
	
}
