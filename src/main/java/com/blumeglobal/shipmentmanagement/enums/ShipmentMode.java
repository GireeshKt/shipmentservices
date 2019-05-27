package com.blumeglobal.shipmentmanagement.enums;

/**
 * The ShipmentMode is used to define list of modes.
 *
 */

public enum ShipmentMode {
	AIR, OCEAN, RAIL, DRAY, FIRSTMILE, LASTMILE,ALL, INTERMODAL;

	public static boolean isValid(String str) {

		if (str == null || str.isEmpty()) {
			return false;
		}
		for (ShipmentMode mode : ShipmentMode.values()) {
			if (mode.name().equalsIgnoreCase(str)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isDrayMode(String str) {

		if (str == null || str.isEmpty()) {
			return false;
		} else if (ShipmentMode.DRAY.name().equalsIgnoreCase(str)) {
			return true;
		}
		return false;
	}

	public static boolean isRailMode(String str) {

		if (str == null || str.isEmpty()) {
			return false;
		} else if (ShipmentMode.RAIL.name().equalsIgnoreCase(str)) {
			return true;
		}
		return false;
	}

	public static boolean isOceanMode(String str) {

		if (str == null || str.isEmpty()) {
			return false;
		} else if (ShipmentMode.OCEAN.name().equalsIgnoreCase(str)) {
			return true;
		}
		return false;
	}
}
