package com.blumeglobal.shipmentmanagement.service;

import com.blumeglobal.shipmentmanagement.request.ShipmentRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentWorkOrderRequest;
import com.blumeglobal.shipmentmanagement.response.ShipmentResponse;

public interface IShipmentService {
	/**
	 * Create shipmentWO through API
	 * 
	 * @param createWorkOrderRequest
	 *            This is request which has shipment details
	 * @return ShipmentResponse Shipment Response after save
	 * @throws Exception
	 *             Handle service exceptions
	 */
	public ShipmentResponse createShipmentWO(ShipmentWorkOrderRequest createWorkOrderRequest)throws Exception;
	
	
	/**
	 * Create shipment through API
	 * 
	 * @param createRequest
	 *            This is request which has shipment details
	 * @return ShipmentResponse Shipment Response after save
	 * @throws Exception
	 *             Handle service exceptions
	 */
	public ShipmentResponse createShipment(ShipmentRequest createRequest)throws Exception;
	
	
	/**
	 * Update shipment through API
	 * 
	 * @param createRequest
	 *            This is request which has shipment details
	 * @param shipmentId
	 *            This is shipment ID
	 * @return ShipmentResponse Shipment Response after save
	 * @throws Exception
	 *             Handle service exceptions
	 */
	public ShipmentResponse updateShipment(ShipmentRequest createRequest, Long shipmentId)throws Exception;
}
