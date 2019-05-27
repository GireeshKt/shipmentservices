package com.blumeglobal.shipmentmanagement.enums;

public enum EventCodeCompletion {
	AD, D,OA;
	public static boolean isValid(String str) {

		if (str == null || str.isEmpty()) {
			return false;
		}
		for (EventCodeCompletion mode : EventCodeCompletion.values()) {
			if (mode.name().equalsIgnoreCase(str)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isValidCodeForDrayWOCompletion(String str) {

		if (str == null || str.isEmpty()) {
			return false;
		}else if (EventCodeCompletion.AD.name().equalsIgnoreCase(str)) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isValidCodeForRailWOCompletion(String str) {

		if (str == null || str.isEmpty()) {
			return false;
		}else if (EventCodeCompletion.OA.name().equalsIgnoreCase(str)) {
			return true;
		}
		return false;
	}
}
