package com.blumeglobal.shipmentmanagement.service;

import java.util.List;

import com.blumeglobal.shipmentmanagement.request.ShipmentStopListRequest;
import com.blumeglobal.shipmentmanagement.response.StopResponse;

public interface IStopService {
	
	/**
	 * Create Stop through API
	 * 
	 * @param stopListRequest
	 *            This is request which has Stop details
	 * @return StopResponse Stop Response after save
	 * @throws Exception
	 *             Handle service exceptions
	 */
	public StopResponse createStop(ShipmentStopListRequest shipmentStopListRequest, Long shipmentId) throws Exception;
	

}
