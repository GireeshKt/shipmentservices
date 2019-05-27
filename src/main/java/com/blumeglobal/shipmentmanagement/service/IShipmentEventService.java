package com.blumeglobal.shipmentmanagement.service;

import java.util.List;

import com.blumeglobal.shipmentmanagement.request.ShipmentEventRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentExceptionListRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentExceptionRequest;
import com.blumeglobal.shipmentmanagement.response.EventResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentEventResponse;


/**
* The IShipmentEventService is an interface. To manage the Shipment Event details
*
*/
public interface IShipmentEventService {

	
	/**
	 * Create shipment event through API
	 * 
	 * @param createEventsRequest
	 *            This is request which has shipment event details
	 * @return ShipmentEventResponse Shipment Event Response after save
	 * @throws Exception
	 *             Handle service exceptions
	 */
	public EventResponse createShipmentEvent(ShipmentEventRequest createEventsRequest)throws Exception;
	
	/**
	 * Create shipment exception through API
	 * 
	 * @param createExceptionRequest
	 *            This is request which has shipment exception details
	 * @return ShipmentExceptionResponse Shipment Event Response after save
	 * @throws Exception
	 *             Handle service exceptions
	 */
	public EventResponse createShipmentException(ShipmentExceptionRequest createExceptionRequest)throws Exception;
	
	
	/**
	 * Retrieve shipment events through criteria
	 * 
	 * @param criteriaSpecStr
	 *            Passing a criteria
	 * @return List<ShipmentEventResponse> List of Shipment Events Response after searching
	 * @throws Exception
	 *             Handle service exceptions
	 */
	public List<ShipmentEventResponse> getSearchShipmentEvents(String criteriaSpecList) throws Exception;
	
	
	/**
	 * Retrieve shipment event data by shipmentNumber or houseBill or masterBill or onHand
	 * 
	 * @param id
	 *            This is shipment shipmentNumber or houseBill or masterBill or onHand
	 
	 * @return List<ShipmentEventResponse> Shipment Event Response
	 * @throws Exception
	 *             Handle service exceptions
	 */
	public List<ShipmentEventResponse> getShipmentEvents(String id)throws Exception;

	EventResponse deleteShipmentEvent(Long shipmentEventId) throws Exception;

    public EventResponse createShipmentExceptions(ShipmentExceptionListRequest createExceptionListRequest);
	
}
