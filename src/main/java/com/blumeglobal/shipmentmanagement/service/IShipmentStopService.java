package com.blumeglobal.shipmentmanagement.service;

import com.blumeglobal.shipmentmanagement.request.ShipmentStopRequest;

import java.util.List;

import com.blumeglobal.shipmentmanagement.response.ShipmentStopResponse;

public interface IShipmentStopService {
		
	/**
	 * Create shipment through API
	 * 
	 * @param createStop
	 *            This is request which has shipment details
	 * @return ShipmentStopResponse Shipment Stop Response after save
	 * @throws Exception
	 *             Handle service exceptions
	 */
	public ShipmentStopResponse createShipmentStop(ShipmentStopRequest createStop)throws Exception;
	public ShipmentStopResponse updateStop(ShipmentStopRequest updateRequest, Long id)throws Exception;
	public List<ShipmentStopResponse> getShipmentList(String id);
}