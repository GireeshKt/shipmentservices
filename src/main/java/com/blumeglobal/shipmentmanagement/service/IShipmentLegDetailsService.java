package com.blumeglobal.shipmentmanagement.service;

import java.util.List;

import com.blumeglobal.shipmentmanagement.response.ShipmentLegDetailsResponse;

/**
* The IShipmentLegDetailsService is an interface. To manage the Shipment Leg details
*
*/
public interface IShipmentLegDetailsService {

	/**
	 * Search shipment leg through criteria
	 * 
	 * @param criteriaSpecList
	 *            This is used to pass the criteriaSpecList value
	 * @param operation    To define criteria operation type       
	 * @param legMode    To pass criteria leg mode            
	 * @return List Of ShipmentLegDetailsResponse  return the Shipment Leg Response 
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	public List<ShipmentLegDetailsResponse> searchShipmentLegs(String criteriaSpecList,String operation,String legMode) throws Exception;
	
	/**
	 * Search shipment leg through criteria
	 * 
	 * @param id
	 *            This is used to pass the key id value
	 * @param legMode    To pass leg mode      
	 *            
	 * @return List Of ShipmentLegDetailsResponse  return the Shipment Leg Response 
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	public List<ShipmentLegDetailsResponse> findShipmentLegByModeAndIDs(String id,String legMode) throws Exception;
}
