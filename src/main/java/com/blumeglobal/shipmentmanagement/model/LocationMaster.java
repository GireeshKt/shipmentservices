package com.blumeglobal.shipmentmanagement.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOCATIONMASTER")
public class LocationMaster implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "LOCATION", length = 200)
	private String location;

	@Column(name = "LOCATIONTYPE", length = 20)
	private String locationType;
	
	@Column(name = "TYPEDESCRIPTION", length = 50)
	private String typeDescription;

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

}
