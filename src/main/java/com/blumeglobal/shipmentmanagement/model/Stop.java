package com.blumeglobal.shipmentmanagement.model;

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
@Table(name = "STOP")
public class Stop {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	@Column(name = "STOPID", nullable = false,updatable = false)
	private Long stopId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SHIPMENTID", nullable = false)
	private Shipment shipment;
	
	@Column(name = "STOPNUMBER")
	private Long stopNumber;
	
	@Column(name = "STOPTYPE", length = 50)
	private String stopType;
	
	@Column(name = "MODEOFTRANSPORT", length = 20)
	private String modeOfTransport;
	
	@Column(name = "NAME", length = 200)
	private String name;
	
	@Column(name = "ADDRESS1", length = 200)
	private String address1;
	
	@Column(name = "ADDRESS2", length = 200)
	private String address2;
	
	@Column(name = "CITY", length = 50)
	private String city;
	
	@Column(name = "STATE", length = 10)
	private String state;
	
	@Column(name = "POSTALCODE", length = 15)
	private String postalCode;
	
	@Column(name = "COUNTRY", length = 2)
	private String country;
	
	@Column(name = "TELEPHONE", length = 50)
	private String telephone;
	
	@Column(name = "FAX", length = 50)
	private String fax;
	
	@Column(name = "COMMENTS", length = 4000)
	private String comments;
	
	@Column(name = "REFERENCENUMBER", length = 50)
	private String referenceNumber;
	
	@Column(name = "TIMEZONE", length = 5)
	private String timeZone;
	
	@Column(name = "LASTMODIFIEDBY", length = 100)
	private String lastModifiedBy;
	
	@Column(name = "LASTMODIFIED")
	private Date lastModified;
	
	@Column(name = "FACILITYCODE", length = 20)
	private String facilityCode;
	
	@Column(name = "CONTACTNAME", length = 50)
	private String contactName;
	
	@Column(name = "CONTACTPHONE", length = 50)
	private String contactPhone;
	
	@Column(name = "FACILITYID")
	private Long facilityId;
	
	@Column(name = "LATITUDE")
	private Double latitude;
	
	@Column(name = "LONGITUDE")
	private Double longitude;
	
	@Column(name = "GEOCODELOCATIONID")
	private Double geoCodeLocationId;
	
	@Column(name = "SOURCE_TYPE", length = 20)
	private String sourceType;
	
	@Column(name = "UNLOCODE")
    private String unlocode;
    
	@Column(name = "LOCATIONID")
    private String locationId;
    
	@Column(name = "LOCATIONTYPE")
    private String locataionType;
    
	@Column(name = "SCHEDULEDGATEINSTART")
    private Date scheduledGateInStart;
    
	@Column(name = "SCHEDULEDGATEINEND")
    private Date scheduledGateInEnd;
    
	@Column(name = "SCHEDULEDGATEINFORM")
    private String scheduledGateInForm;

	
	public Stop() {
		
	}


	public Long getStopId() {
		return stopId;
	}


	public void setStopId(Long stopId) {
		this.stopId = stopId;
	}


	public Shipment getShipment() {
		return shipment;
	}


	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
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


	public String getModeOfTransport() {
		return modeOfTransport;
	}


	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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


	public String getLastModifiedBy() {
		return lastModifiedBy;
	}


	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}


	public Date getLastModified() {
		return lastModified;
	}


	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}


	public String getFacilityCode() {
		return facilityCode;
	}


	public void setFacilityCode(String facilityCode) {
		this.facilityCode = facilityCode;
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


	public Long getFacilityId() {
		return facilityId;
	}


	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
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


	public Double getGeoCodeLocationId() {
		return geoCodeLocationId;
	}


	public void setGeoCodeLocationId(Double geoCodeLocationId) {
		this.geoCodeLocationId = geoCodeLocationId;
	}


	public String getSourceType() {
		return sourceType;
	}


	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}


    public String getUnlocode() {
        return unlocode;
    }


    public void setUnlocode(String unlocode) {
        this.unlocode = unlocode;
    }


    public String getLocationId() {
        return locationId;
    }


    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }


    public String getLocataionType() {
        return locataionType;
    }


    public void setLocataionType(String locataionType) {
        this.locataionType = locataionType;
    }


    public Date getScheduledGateInStart() {
        return scheduledGateInStart;
    }


    public void setScheduledGateInStart(Date scheduledGateInStart) {
        this.scheduledGateInStart = scheduledGateInStart;
    }


    public Date getScheduledGateInEnd() {
        return scheduledGateInEnd;
    }


    public void setScheduledGateInEnd(Date scheduledGateInEnd) {
        this.scheduledGateInEnd = scheduledGateInEnd;
    }


    public String getScheduledGateInForm() {
        return scheduledGateInForm;
    }


    public void setScheduledGateInForm(String scheduledGateInForm) {
        this.scheduledGateInForm = scheduledGateInForm;
    }

}
