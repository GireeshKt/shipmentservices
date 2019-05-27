package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;
import java.util.Date;

public class ShipmentFacilityRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String address1;
	
	private String address2;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String postalCode;
	
	private String stopReferenceNumber;
	
	private String contactPhone;
	
	private String facilityCode;
	
	private String comments;
	
	private String contactName;
	
	private String timezone;
	
	private String unlocode;
	
	private String locationId;
	
	private String locataionType;
	
	private Date scheduledGateInStart;
	
	private Date scheduledGateInEnd;
	
	private String scheduledGateInForm;
	
	public ShipmentFacilityRequest() {
		
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


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}


	public String getStopReferenceNumber() {
		return stopReferenceNumber;
	}


	public void setStopReferenceNumber(String stopReferenceNumber) {
		this.stopReferenceNumber = stopReferenceNumber;
	}


	public String getContactPhone() {
		return contactPhone;
	}


	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}


	public String getFacilityCode() {
		return facilityCode;
	}


	public void setFacilityCode(String facilityCode) {
		this.facilityCode = facilityCode;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getContactName() {
		return contactName;
	}


	public void setContactName(String contactName) {
		this.contactName = contactName;
	}


	public String getTimezone() {
		return timezone;
	}


	public void setTimezone(String timezone) {
		this.timezone = timezone;
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
