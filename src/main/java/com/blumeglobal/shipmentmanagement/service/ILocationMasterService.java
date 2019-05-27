package com.blumeglobal.shipmentmanagement.service;

import java.util.List;

import com.blumeglobal.shipmentmanagement.model.LocationMaster;
import com.blumeglobal.shipmentmanagement.request.LocationMasterRequest;
import com.blumeglobal.shipmentmanagement.response.LocationMasterResponse;
import com.blumeglobal.shipmentmanagement.response.Response;

public interface ILocationMasterService {
	
	/**
	 * Get Location from LocationMaster API
	 * 
	 * @param TypeDescription
	 *            This is Type Description
	 * @return List of Locations' value as String
	 * @throws Exception
	 *             Handle service exceptions
	 */
	public List<String> getLocationByTypeDescription(String typeDescription) throws Exception;
	
	
	
	/**
	 * Get Location API
	 * 
	 * @param location
	 *            This is location
	 * @return LocationMasterResponse Location Response after fetch record
	 * @throws Exception
	 *             Handle service exceptions
	 */
	public LocationMasterResponse getAddressDetailsByLocation(String location) throws Exception;
	
	public List<LocationMaster> getAllMasterLocations() throws Exception;
	
	public LocationMasterResponse updateLocation(LocationMasterRequest locationMaster) throws Exception;
	
	public LocationMasterResponse addLocation(LocationMasterRequest locationMaster) throws Exception;
	
	/**@Description: Delete the location from the Location master table
	 * @param locationMasterRequest
	 * @return
	 */
	public Response deleteLocation(LocationMasterRequest locationMasterRequest);
	
}
