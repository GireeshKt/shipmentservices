package com.blumeglobal.shipmentmanagement.dm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "STOP")
public class WoStop implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STOPID", nullable = false,updatable = false)
	private Long stopId;
	
	@Column(name = "WORKORDERID")
	private Long workorderId;
	
	@Column(name = "STOPNUMBER")
	private Long stopNumber;

	@Column(name = "STOPTYPE")
	private String stopType;
	
	@Column(name = "FACILITYCODE")
	private String facilityCode;
	
	@Column(name = "ADDRESS1")
	private String address1;
	
	@Column(name = "ADDRESS2")
	private String address2;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "POSTALCODE")
	private String postalCode;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "TELEPHONE")
	private String telephone;
	
	@Column(name = "FAX")
	private String fax;
	
	@Column(name = "COMMENTS")
	private String comments;
	
	@Column(name = "REFERENCENUMBER")
	private String referenceNumber;
	
	@Column(name = "TIMEZONE")
	private String timeZone;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "CONTACTNAME")
	private String contactName;
	
	@Column(name = "CONTACTPHONE")
	private String contactPhone;
	
	@Column(name = "LATITUDE")
	private Double latitude;
	
	@Column(name = "LONGITUDE")
	private Double longitude;
	
	@Column(name = "GEOCODELOCATIONID")
	private Long geoCodeLocationId;
	
	@Column(name = "FACILITYID")
	private Long facilityId;
	
	@Column(name = "LASTMODIFIED")
	private Date lastModified;

	@Column(name = "LASTMODIFIEDBY")
	private String lastModifiedBy;

	@Column(name = "SOURCE_TYPE")
	private String sourceType;

	
	public Long getStopId() {
		return stopId;
	}

	public void setStopId(Long stopId) {
		this.stopId = stopId;
	}

	public Long getWorkorderId() {
		return workorderId;
	}

	public void setWorkorderId(Long workorderId) {
		this.workorderId = workorderId;
	}

	public Long getStopNumber() {
		return stopNumber;
	}

	public void setStopNumber(Long stopNumber) {
		this.stopNumber = stopNumber;
	}

	public String getStopType() {
		return stopType;
	}

	public void setStopType(String stopType) {
		this.stopType = stopType;
	}

	public String getFacilityCode() {
		return facilityCode;
	}

	public void setFacilityCode(String facilityCode) {
		this.facilityCode = facilityCode;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Long getGeoCodeLocationId() {
		return geoCodeLocationId;
	}

	public void setGeoCodeLocationId(Long geoCodeLocationId) {
		this.geoCodeLocationId = geoCodeLocationId;
	}

	public Long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

//	public String getStopLocationIdAll() {
//		return ShipmentUtil.getLocationAll(this.address1, this.address2, this.city, this.state, 
//				this.postalCode, this.country);
//	}
//	
//	public String getStopLocationId() {		
//		return ShipmentUtil.getLocation(this.address1, this.address2, this.city, this.state, 
//				this.postalCode, this.country);
//	}
	
//	@Autowired
//	ShipmentUtil shipmentUtil;
//	
//	public String getStopLocationIdAll() {
//		try {
//			return shipmentUtil.setLocationIdAll(this.address1, this.address2, this.city, this.state, 
//					this.postalCode, this.country);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	public String getStopLocationId() {		
//		try {
//			String locationId = shipmentUtil.setLocationIdAll(this.address1, this.address2, this.city, this.state, 
//					this.postalCode, this.country);
//			
//			return shipmentUtil.getLocationsByLocationId(locationId).getLocationType();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//	}
//

}
