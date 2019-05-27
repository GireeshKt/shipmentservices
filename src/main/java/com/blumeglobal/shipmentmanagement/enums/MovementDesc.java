package com.blumeglobal.shipmentmanagement.enums;
/**
 * 
 * Display description for active shipment in summary view
 *
 */
public enum MovementDesc {
	  BOOKING("Planned"),
	  PICKUP("Pickup"),
	  REST("At Location in"),
	  TOSHIP("Yet to Ship"),
	  INTRANSIT("In Transit to"),
	  SHIPPED("Shipped"),
	  DELIVERED("Delivered");
	
	  public final String description;

	  private MovementDesc(String description) {
	      this.description = description;
	  }

	public String getDescription() {
		return description;
	}

}
