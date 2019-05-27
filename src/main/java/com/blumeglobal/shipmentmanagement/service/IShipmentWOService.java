package com.blumeglobal.shipmentmanagement.service;

import java.util.List;

import com.blumeglobal.shipmentmanagement.model.ShipmentDetails;
import com.blumeglobal.shipmentmanagement.model.ShipmentWO;
import com.blumeglobal.shipmentmanagement.response.ShipmentWOResponse;

public interface IShipmentWOService {

	List<ShipmentWO> shipmentList(int startRow,int endRow,String officeId, String officeType);

	List<ShipmentWOResponse> searchShipment(String criteriaSpecStr) throws Exception;
	
	public List<ShipmentWO> getShipmentByDateRange(String fromDate, String toDate, Long officeId, String officeType) throws Exception;
	public List<ShipmentDetails> shipmentDetailsList(int startRow, int endRow, String officeIds,String officeType);
}
