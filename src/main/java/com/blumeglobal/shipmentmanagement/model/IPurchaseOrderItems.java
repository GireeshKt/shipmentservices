package com.blumeglobal.shipmentmanagement.model;

import java.util.Date;

public interface IPurchaseOrderItems {

	public String getPurchaseOrderNumber();
	public String getPoReferenceId();
	public Date getPoRequestDate();
	public Date getPoPromiseDate();
	public Date getPoIssueDate() ;
	public String getCreatedOn();
	public String getVendorName();
	public String getVendorAddress();
	public String getBuyerName();
	public String getBillingAddress();
}
