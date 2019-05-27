package com.blumeglobal.shipmentmanagement.request.common;

import java.io.Serializable;

import javax.validation.constraints.Size;


public class LocationDetail implements Serializable{

    private static final long serialVersionUID = 1L;
    private String address1;
    private String address2;
    private String city;
    private String locationName;
    private String phoneExtension;
    private String phoneNumber;
    private String state;
    @Size(max=2, message="country code must be 2 characters")
    private String country;
    private String zip;
    private String uuid;
    private String unlocode;

    public LocationDetail() {
        super();
    }
    public LocationDetail(String address1, String address2, String city, String locationName, String phoneExtension,
            String phoneNumber, String state, String country, String zip, String uuid, String unlocode) {
        super();
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.locationName = locationName;
        this.phoneExtension = phoneExtension;
        this.phoneNumber = phoneNumber;
        this.state = state;
        this.country = country;
        this.zip = zip;
        this.uuid = uuid;
        this.unlocode = unlocode;
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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getPhoneExtension() {
        return phoneExtension;
    }

    public void setPhoneExtension(String phoneExtension) {
        this.phoneExtension = phoneExtension;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUnlocode() {
        return unlocode;
    }

    public void setUnlocode(String unlocode) {
        this.unlocode = unlocode;
    }


}
