package com.blumeglobal.shipmentmanagement.enums;
/**
 * The ExceptionCode is used to define the Exception code enums with application, language and message.
 *
 */
public enum ExceptionCode {

	SHIPMENTINVALID("SCV","ENG","Invalid Input Request for Shipment"),
	SHIPMENTINVALIDCUSTOM("SCV","ENG","${1}"),
	SHIPMENTCONFLICT("SCV","ENG","Shipment Data Already Exist"),
	SHIPMENTNOTFOUND("SCV","ENG","Shipment Data is not Found"),
	SHIPMENTNOTFOUNDCUSTOM("SCV","ENG","${1}"),
	SHIPMENTEVENTNOTFOUNDCUSTOM("SCV","ENG","${1}"),
	SHIPMENTEVENTINVALIDCUSTOM("SCV","ENG","${1}"),
	KPIINVALID("SCV","ENG","Invalid Input Request for KPI"),
	KPINOTFOUND("SCV","ENG","KPI Data is not Found"),
	KPIINVALIDCUSTOM("SCV","ENG","${1}"),
	KPINOTFOUNDCUSTOM("SCV","ENG","${1}"),
	SHIPMENTLEGINVALID("SCV","ENG","Invalid Input Request for ShipmentLeg"),
	SHIPMENTLEGNOTFOUND("SCV","ENG","Shipment Leg Data is not Found"),
	SHIPMENTLEGNOTFOUNDCUSTOM("SCV","ENG","${1}"),
	SHIPMENTLEGINVALIDCUSTOM("SCV","ENG","${1}"),
	SHIPMENTEVENTINVALID("SCV","ENG","Invalid Input Request for ShipmentEvent"),
	SHIPMENTEVENTNOTFOUND("SCV","ENG","ShipmentEvent Data is not Found"),
	STOPNOTFOUND("SCV","ENG","Stop Data is not Found"),
	CATCHBLOCKRUNTIMEEXCEPTION("SCV","ENG","Runtime Catch Block Exceptions"),
	STOPNUMBERNULL("SCV","ENG","StopNumber should not null for Stop Model"),
	STOPMAPPERREQUESTNOTFOUND("SCV","ENG","StopMapperRequest Data is not Found");
	
	private final String exceptionMessage;
	private final String language;
	private final String applicationName;
	
	private ExceptionCode(String applicationName,String language,String exceptionMessage) {
		this.applicationName = applicationName;
		this.language = language;
		this.exceptionMessage = exceptionMessage;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public String getLanguage() {
		return language;
	}
	public String getApplicationName() {
		return applicationName;
	}
}
