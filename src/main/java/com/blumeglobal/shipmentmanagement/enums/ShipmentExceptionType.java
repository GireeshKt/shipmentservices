package com.blumeglobal.shipmentmanagement.enums;

/**
 * The ShipmentExceptionType is used to define list of exceptions.
 *
 */

public enum ShipmentExceptionType {
	LATE_MILESTONE("Late milestone", 2), EARLY_MILESTONE("Early milestone", 3), CUSTOMS_DELAY("Customs Delay", 1);

	Integer priority;
	String description;
	
	private ShipmentExceptionType(String description, Integer priority) {
		this.priority = priority;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public Integer getPriority() {
		return priority;
	}

	public static ShipmentExceptionType getException(String input) {
		if(input==null) return null;
		for(ShipmentExceptionType st:ShipmentExceptionType.values()) {
			if(st.name().equals(input)||st.getDescription().equalsIgnoreCase(input)) {
				return st;
			}
		}
		return null;
	}
	
}
