package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;


public class StopMapperRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long stopId;
	
	private Long shipmentId;
	
	private Long stopNumber;
	
	private String stopType;
	
	private String modeOfTransport;
	
	private String name;
	
	@JsonIgnore
	private ShipmentFacilityRequest facility;
	
	private String address1;
	
	private String address2;
	
	private String city;
	
	private String state;
	
	private String postalCode;
	
	private String country;
	
	private String telephone;
	
	private String fax;
	
	private String comments;
	
	private String referenceNumber;
	
	private String timeZone;
	
	private String lastModifiedBy;
	
	private Date lastModified;
	
	private String facilityCode;
	
	private String contactName;
	
	private String contactPhone;
	
	private Long facilityId;
	
	private Double latitude;
	
	private Double longitude;
	
	private Double geoCodeLocationId;
	
	private String sourceType;
	
	private String unlocode;
	
	private String locationId;
	
	private String locataionType;
	
	private Date scheduledGateInStart;
	
	private Date scheduledGateInEnd;
	
	private String scheduledGateInForm;

	
	public StopMapperRequest() {
		
	}


	public Long getStopId() {
		return stopId;
	}


	public void setStopId(Long stopId) {
		this.stopId = stopId;
	}


	public Long getShipmentId() {
		return shipmentId;
	}


	public void setShipmentId(Long shipmentId) {
		this.shipmentId = shipmentId;
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


	public ShipmentFacilityRequest getFacility() {
		return facility;
	}


	public void setFacility(ShipmentFacilityRequest facility) {
		this.facility = facility;
	}


	@JsonGetter("address1")
	public String getAddress1() {
		if(facility != null) {
			address1 = facility.getAddress1();
		}
		return address1;
	}


	@JsonSetter("address1")
	public void setAddress1(String address1) {
		if (address1 != null) {
			facility = new ShipmentFacilityRequest();
			facility.setAddress1(address1);
        }
		this.address1 = address1;
	}


	@JsonGetter("address2")
	public String getAddress2() {
		if(facility != null) {
			address2 = facility.getAddress2();
		}
		return address2;
	}


	@JsonSetter("address2")
	public void setAddress2(String address2) {
		if (address2 != null) {
			facility = new ShipmentFacilityRequest();
			facility.setAddress2(address2);
        }
		this.address2 = address2;
	}


	@JsonGetter("city")
	public String getCity() {
		if(facility != null) {
			city = facility.getCity();
		}
		return city;
	}


	@JsonSetter("city")
	public void setCity(String city) {
		if (city != null) {
			facility = new ShipmentFacilityRequest();
			facility.setCity(city);
        }
		this.city = city;
	}


	@JsonGetter("state")
	public String getState() {
		if(facility != null) {
			state = facility.getState();
		}
		return state;
	}


	@JsonSetter("state")
	public void setState(String state) {
		if (state != null) {
			facility = new ShipmentFacilityRequest();
			facility.setState(state);
        }
		this.state = state;
	}


	@JsonGetter("postalCode")
	public String getPostalCode() {
		if(facility != null) {
			postalCode = facility.getPostalCode();
		}
		return postalCode;
	}


	@JsonSetter("postalCode")
	public void setPostalCode(String postalCode) {
		if (postalCode != null) {
			facility = new ShipmentFacilityRequest();
			facility.setPostalCode(postalCode);
        }
		this.postalCode = postalCode;
	}


	@JsonGetter("country")
	public String getCountry() {
		if(facility != null) {
			country = facility.getCountry();
		}
		return country;
	}


	@JsonSetter("country")
	public void setCountry(String country) {
		if (country != null) {
			facility = new ShipmentFacilityRequest();
			facility.setCountry(country);
        }
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


	@JsonGetter("comments")
	public String getComments() {
		if(facility != null) {
			comments = facility.getComments();
		}
		return comments;
	}


	@JsonSetter("comments")
	public void setComments(String comments) {
		if (comments != null) {
			facility = new ShipmentFacilityRequest();
			facility.setComments(comments);
        }
		this.comments = comments;
	}


	public String getReferenceNumber() {
		return referenceNumber;
	}


	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}


	@JsonGetter("timeZone")
	public String getTimeZone() {
		if(facility != null) {
			timeZone = facility.getTimezone();
		}
		return timeZone;
	}


	@JsonSetter("timeZone")
	public void setTimeZone(String timeZone) {
		if (timeZone != null) {
			facility = new ShipmentFacilityRequest();
			facility.setTimezone(timeZone);
        }
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


	@JsonGetter("facilityCode")
	public String getFacilityCode() {
		if(facility != null) {
			facilityCode = facility.getFacilityCode();
		}
		return facilityCode;
	}


	@JsonSetter("facilityCode")
	public void setFacilityCode(String facilityCode) {
		if (facilityCode != null) {
			facility = new ShipmentFacilityRequest();
			facility.setFacilityCode(facilityCode);
        }
		this.facilityCode = facilityCode;
	}


	@JsonGetter("contactName")
	public String getContactName() {
		if(facility != null) {
			contactName = facility.getContactName();
		}
		return contactName;
	}


	@JsonSetter("contactName")
	public void setContactName(String contactName) {
		if (contactName != null) {
			facility = new ShipmentFacilityRequest();
			facility.setContactName(contactName);
        }
		this.contactName = contactName;
	}


	@JsonGetter("contactPhone")
	public String getContactPhone() {
		if(facility != null) {
			contactPhone = facility.getContactPhone();
		}
		return contactPhone;
	}


	@JsonSetter("contactPhone")
	public void setContactPhone(String contactPhone) {
		if (contactPhone != null) {
			facility = new ShipmentFacilityRequest();
			facility.setContactPhone(contactPhone);
        }
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
	

	@JsonGetter("unlocode")
	public String getUnlocode() {
		if(facility != null) {
			unlocode = facility.getUnlocode();
		}
		return unlocode;
	}


	@JsonSetter("unlocode")
	public void setUnlocode(String unlocode) {
		if (unlocode != null) {
			facility = new ShipmentFacilityRequest();
			facility.setUnlocode(unlocode);
        }
		this.unlocode = unlocode;
	}


	@JsonGetter("locationId")
	public String getLocationId() {
		if(facility != null) {
			locationId = facility.getLocationId();
		}
		return locationId;
	}


	@JsonSetter("locationId")
	public void setLocationId(String locationId) {
		if (locationId != null) {
			facility = new ShipmentFacilityRequest();
			facility.setLocationId(locationId);
        }
		this.locationId = locationId;
	}


	@JsonGetter("locataionType")
	public String getLocataionType() {
		if(facility != null) {
			locataionType = facility.getLocataionType();
		}
		return locataionType;
	}


	@JsonSetter("locataionType")
	public void setLocataionType(String locataionType) {
		if (locataionType != null) {
			facility = new ShipmentFacilityRequest();
			facility.setLocataionType(locataionType);
        }
		this.locataionType = locataionType;
	}


	@JsonGetter("scheduledGateInStart")
	public Date getScheduledGateInStart() {
		if(facility != null) {
			scheduledGateInStart = facility.getScheduledGateInStart();
		}
		return scheduledGateInStart;
	}


	@JsonSetter("scheduledGateInStart")
	public void setScheduledGateInStart(Date scheduledGateInStart) {
		if (scheduledGateInStart != null) {
			facility = new ShipmentFacilityRequest();
			facility.setScheduledGateInStart(scheduledGateInStart);
        }
		this.scheduledGateInStart = scheduledGateInStart;
	}


	@JsonGetter("scheduledGateInEnd")
	public Date getScheduledGateInEnd() {
		if(facility != null) {
			scheduledGateInEnd = facility.getScheduledGateInEnd();
		}
		return scheduledGateInEnd;
	}


	@JsonSetter("scheduledGateInEnd")
	public void setScheduledGateInEnd(Date scheduledGateInEnd) {
		if (scheduledGateInEnd != null) {
			facility = new ShipmentFacilityRequest();
			facility.setScheduledGateInEnd(scheduledGateInEnd);
        }
		this.scheduledGateInEnd = scheduledGateInEnd;
	}


	@JsonGetter("scheduledGateInForm")
	public String getScheduledGateInForm() {
		if(facility != null) {
			scheduledGateInForm = facility.getScheduledGateInForm();
		}
		return scheduledGateInForm;
	}


	@JsonSetter("scheduledGateInForm")
	public void setScheduledGateInForm(String scheduledGateInForm) {
		if (scheduledGateInForm != null) {
			facility = new ShipmentFacilityRequest();
			facility.setScheduledGateInForm(scheduledGateInForm);
        }
		this.scheduledGateInForm = scheduledGateInForm;
	}

}
