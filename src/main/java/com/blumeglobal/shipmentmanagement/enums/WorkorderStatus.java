package com.blumeglobal.shipmentmanagement.enums;

/**
 * list of DM workorder status
 */
public enum WorkorderStatus {
    Created("Created"),
	Unassigned("Unassigned"),
	Assigned("Assigned"),
	Accepted("Accepted"),
	Rejected("Rejected"),
	Active("Active"),
	Completed("Completed"),
	Cancelled("Cancelled"),
	NotActive("Not Active");
	
	  public final String description;

	  private WorkorderStatus(String description) {
	      this.description = description;
	  }

	public String getDescription() {
		return description;
	}

}
