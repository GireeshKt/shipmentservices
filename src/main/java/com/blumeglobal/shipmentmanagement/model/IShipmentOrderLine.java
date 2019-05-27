package com.blumeglobal.shipmentmanagement.model;

import java.util.Date;

public interface IShipmentOrderLine {
	
	public String getPo_number();
	public String getSku_id();
	public String getShip_to_country();
	public String getShip_to_state();
	public Date getPromised_date();
	public Long getCommitted_quantity();
	public String getItem_quantity_uom();
	public Long getShipmentCount();
}
