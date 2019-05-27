package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;

public class DrayEventRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String workOrderNumber;
	private String originatorCode;
	private String receiverCode;
	private String stopNumber;
	private String stopType;
	private String stopName;
	private String address1;//not avail in le_shipmentevents
    private String address2;//not avail in le_shipmentevents
    private String city;
    private String state;
    private String postalCode;//not avail in le_shipmentevents
    private String country;
	private EventDetailsRequest eventDetails;
	private String reportSource="DRAYEVENT";
	
	public DrayEventRequest() {
		
	}

	public String getWorkOrderNumber() {
		return workOrderNumber;
	}

	public void setWorkOrderNumber(String workOrderNumber) {
		this.workOrderNumber = workOrderNumber;
	}

	public String getOriginatorCode() {
		return originatorCode;
	}

	public void setOriginatorCode(String originatorCode) {
		this.originatorCode = originatorCode;
	}

	public String getReceiverCode() {
		return receiverCode;
	}

	public void setReceiverCode(String receiverCode) {
		this.receiverCode = receiverCode;
	}

	public String getStopNumber() {
		return stopNumber;
	}

	public void setStopNumber(String stopNumber) {
		this.stopNumber = stopNumber;
	}

	public String getStopType() {
		return stopType;
	}

	public void setStopType(String stopType) {
		this.stopType = stopType;
	}

	public String getStopName() {
		return stopName;
	}

	public void setStopName(String stopName) {
		this.stopName = stopName;
	}

	public EventDetailsRequest getEventDetails() {
		return eventDetails;
	}

	public void setEventDetails(EventDetailsRequest eventDetails) {
		this.eventDetails = eventDetails;
	}

    public String getReportSource() {
        return reportSource;
    }

    public void setReportSource(String reportSource) {
        this.reportSource = reportSource;
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

}
