package com.blumeglobal.shipmentmanagement.enums;

/**
 * The ShipmentReferenceType is used to define list of shipment reference type for ShipmentReferences table.
 *
 * More values could be added or input from data accepted by API. 
 */

public enum ShipmentReferenceType {
	SHIPMENTREFNUM,WAYBILLNUMBER,BILLOFLADING, RAILBILLINGNUMBER, HOUSEAIRWAYBILLNUM, MASTERAIRWAYBILLNUMBER,
	WORKORDERNUM, BOOKINGNUM, WORKORDERID;
	
	public static boolean isBillNumberType(String type) {
		if(type==null||type.isEmpty()) return false;
		
		if(ShipmentReferenceType.BILLOFLADING.name().equals(type)
				||ShipmentReferenceType.WAYBILLNUMBER.name().equals(type)
				||ShipmentReferenceType.RAILBILLINGNUMBER.name().equals(type)
				||ShipmentReferenceType.HOUSEAIRWAYBILLNUM.name().equals(type)
				||ShipmentReferenceType.MASTERAIRWAYBILLNUMBER.name().equals(type)
				||ShipmentReferenceType.BOOKINGNUM.name().equals(type)) {
			return true;
		}
		return false;
	}
}
