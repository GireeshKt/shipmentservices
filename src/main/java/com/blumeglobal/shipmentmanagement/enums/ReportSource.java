package com.blumeglobal.shipmentmanagement.enums;

public enum ReportSource {
DRAYEVENT,OCEANEVENT,RAILEVENT,RAMPEVENT,CLMEVENT,AIREVENT;
public static boolean isValid(String str) {

	if (str == null || str.isEmpty()) {
		return false;
	}
	for (ReportSource mode : ReportSource.values()) {
		if (mode.name().equalsIgnoreCase(str)) {
			return true;
		}
	}
	return false;
}

public static boolean isRailEvent(String str) {
	if (str == null || str.isEmpty()) {
		return false;
	}else if (ReportSource.RAILEVENT.name().equalsIgnoreCase(str)||ReportSource.RAMPEVENT.name().equalsIgnoreCase(str)||ReportSource.CLMEVENT.name().equalsIgnoreCase(str)) {
		return true;
	}
	return false;
}
public static boolean isDrayEvent(String str) {
	if (str == null || str.isEmpty()) {
		return false;
	}else if (ReportSource.DRAYEVENT.name().equalsIgnoreCase(str)) {
		return true;
	}
	return false;
}
public static boolean isOceanEvent(String str) {
	if (str == null || str.isEmpty()) {
		return false;
	}else if (ReportSource.OCEANEVENT.name().equalsIgnoreCase(str)) {
		return true;
	}
	return false;
}
}
