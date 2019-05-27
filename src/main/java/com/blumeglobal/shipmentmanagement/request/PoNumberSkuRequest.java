package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;

public class PoNumberSkuRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private String poNumber;
	private String sku;
	private String organizationCode;
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getOrganizationCode() {
		return organizationCode;
	}
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
	
}
