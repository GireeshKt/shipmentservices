package com.blumeglobal.shipmentmanagement.enums;

import java.util.HashMap;
import java.util.Map;

public enum RailBillingStatus {
	Submitted("Submitted",  "Submitted",   1L),
	  Accepted    ("Accepted",       "RB_ACCEPTED",   2L),
	  Rejected("Rejected", "RB_REJECTED", 3L),
	  Cancelled("Cancelled", "RB_CANCELLED", 4L),
	  Completed("Completed", "Completed", 5L),
	  AcceptWithErrors("AcceptWithErrors", "RB_ACCEPT_WITH_ERRORS", 6L),
	  CancellationAccepted("CancellationAccepted", "CANCELLATION_ACCEPTED", 7L),
	  CancellationRejected("CancellationRejected", "CANCELLATION_REJECTED", 8L),
	  Active("Active", "Active", 9L);
	  

	  public final String fullName;
	  public final String ediName;
	  public final Long Value;

	  private RailBillingStatus(String full, String ediName, Long statusId) {
	      this.fullName = full;
	      this.ediName = ediName;
	      this.Value = statusId;
	  }

	  public String getFullName() {
	      return fullName;
	  }

	  public String getEdiStatusName() {
	      return ediName;
	  }

	  public Long getStatusId(){
	      return Value;
	  }

	  private static final Map<Long, RailBillingStatus> _map = new HashMap<>();
	  static {
	    for (RailBillingStatus status : RailBillingStatus.values())
	      _map.put(status.Value, status);
	  }

	  public static RailBillingStatus from(Long value) {
	    return _map.get(value);
	  }

	  public static Long fromName(String name) {
	    for (RailBillingStatus status : RailBillingStatus.values()) {
	      if (status.getFullName().equalsIgnoreCase(name) || status.getEdiStatusName().equalsIgnoreCase(name)  ) {
	        return status.Value;
	      }
	    }
	    return null;
	  }

	  public static boolean isValid(String str) {

	    if (str == null || str.isEmpty()) {
	      return false;
	    }
	    for (RailBillingStatus status : RailBillingStatus.values()) {
	      if (status.getFullName().equalsIgnoreCase(str) || status.getEdiStatusName().equalsIgnoreCase(str) ) {
	        return true;
	      }
	    }
	    return false;
	  }

	  public static boolean isValidValue(long statusValue) {

	    for (RailBillingStatus status : RailBillingStatus.values()) {
	      if (status.Value.longValue() == statusValue) {
	        return true;
	      }
	    }
	    return false;
	  }
}
