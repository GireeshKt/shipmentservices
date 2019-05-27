package com.blumeglobal.shipmentmanagement.enums;

public enum LocationTypeDesc {
	
	MFG_PLANT("MFG_PLANT", "Factory"),
	PORT("PORT", "Port"), 
	RAIL_YARD("RAIL_YARD", "Rail Yard"), 
	DC("DC", "Distribution Center"), 
	CONTAINER_YARD("CONTAINER_YARD", "Container Yard"),
	AIRPORT("AIRPORT", "Airport");
	
	public final String locationType;
	public final String locationTypeDescription;
	
	
	private LocationTypeDesc(String locationType, String locationTypeDescription) {
		this.locationType = locationType;
		this.locationTypeDescription = locationTypeDescription;
	}

	public String getLocationType() {
		return locationType;
	}
	
	public String getLocationTypeDescription() {
		return locationTypeDescription;
	}
	
	public static String getTypeDesc(String type) {
	    try {
	        LocationTypeDesc td=LocationTypeDesc.valueOf(type.toUpperCase());
	        return td.getLocationTypeDescription();
	    }catch (Exception e) {
	        return type;
	    }
	}
	
}
