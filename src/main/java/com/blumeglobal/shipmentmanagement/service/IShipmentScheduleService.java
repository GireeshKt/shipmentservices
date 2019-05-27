package com.blumeglobal.shipmentmanagement.service;

import java.util.List;

import com.blumeglobal.shipmentmanagement.request.ShipmentScheduleRequest;
import com.blumeglobal.shipmentmanagement.response.ShipmentScheduleResponse;

public interface IShipmentScheduleService {
		
	/**
	 * Create shipment through API
	 * 
	 * @param createSchedule
	 *            This is request which has shipment details
	 * @return ShipmentScheduleResponse Shipment Schedule Response after save
	 * @throws Exception
	 *             Handle service exceptions
	 */
	public ShipmentScheduleResponse createShipmentSchedule(ShipmentScheduleRequest createSchedule)throws Exception;
	
	public ShipmentScheduleResponse updateSchedule(ShipmentScheduleRequest updateRequest, Long id)throws Exception;
	
	public List<ShipmentScheduleResponse> getSchedulebyVessel(String vessel);
	
	public List<ShipmentScheduleResponse> getSchedulebyCarrier(String carrier);
	
	public List<ShipmentScheduleResponse> getSchedulebyLane(String lane);
	
	public List<ShipmentScheduleResponse> getSchedulebyOriginAndDestination(String origin, String destination);
	
	public List<ShipmentScheduleResponse> getSchedulebyOrigin(String origin);
	
	public List<ShipmentScheduleResponse> getSchedulebyDestination(String destination);
}