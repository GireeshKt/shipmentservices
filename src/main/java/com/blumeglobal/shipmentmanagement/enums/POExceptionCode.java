package com.blumeglobal.shipmentmanagement.enums;
/**
 * The ExceptionCode is used to define the Exception code enums with application, language and message.
 *
 */
public enum POExceptionCode {

	PURCHASEORDERVALID("BLUMEVISIBLEAPI","ENG","Invalid Input Request for PurchaseOrder"),
	PURCHASEORDERINVALIDCUSTOM("BLUMEVISIBLEAPI","ENG","${1}"),
	SHIPMENTLOGSNOTFOUNDCUSTOM("BLUMEVISIBLEAPI","ENG","${1}");

	private final String exceptionMessage;
	private final String language;
	private final String applicationName;
	
	private POExceptionCode(String applicationName,String language,String exceptionMessage) {
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
