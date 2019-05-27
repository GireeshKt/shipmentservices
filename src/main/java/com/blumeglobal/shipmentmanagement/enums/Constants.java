package com.blumeglobal.shipmentmanagement.enums;

public enum Constants {
	
	CREATED("Created"),
	UPDATED("Updated"),
	ACTIVE("Active"),
	INACTIVE("In Active"),
	SYSTEM("System"),
	ORDERSHIPPED("Order Shipped"),
	READYTOSHIP("Ready To Ship"),
	ORDERACCEPTED("Order Accepted"),
	ORDERPLACED("Order Placed"),
	ORDERUPLOADED("Order Uploaded"),
	ORDERDELIVERED("Delivered"),
	SUCCESS("Success"),
	FAILURE("Failure"),
	ORDERINTRANSIT("In Transit"),
	EXCEPTIONTYPE("Late PickUp"),
	EXCEPTIONNOTES("Late PickUp");
	
	private final String constatns;
	private Constants(String constatns) {
		this.constatns = constatns;
	}
	public String getConstants() {
		return constatns;
	}
	
}
