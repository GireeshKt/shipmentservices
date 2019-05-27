package com.blumeglobal.shipmentmanagement.service;

import com.blumeglobal.shipmentmanagement.request.ShipmentStatusRequest;
import com.blumeglobal.shipmentmanagement.request.common.GenericShipmentRequest;
import com.blumeglobal.shipmentmanagement.response.ShipmentResponse;

public interface IGenericShipmentService {

    /**
     * Create generic shipment through API
     * 
     * @param createShipmentRequest This is request which has shipment details
     * @return ShipmentResponse Shipment Response after save
     * @throws Exception Handle service exceptions
     */
    ShipmentResponse createShipment(GenericShipmentRequest createShipmentRequest) throws Exception;


    /**
	 * Update shipment status through API
	 * 
	 * @param shipmentStatusRequest
	 *            This is request which has shipment references and status details
	 * @param shipmentId
	 *            This is shipment ID
	 * @return ShipmentResponse Shipment Response after save
	 * @throws Exception
	 *             Handle service exceptions
	 */
	public ShipmentResponse updateShipmentStatus(ShipmentStatusRequest shipmentStatusRequest) throws Exception;
}
