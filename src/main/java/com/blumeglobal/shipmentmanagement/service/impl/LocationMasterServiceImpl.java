package com.blumeglobal.shipmentmanagement.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.blumeglobal.shipmentmanagement.dao.repositories.LocationMasterRepository;
import com.blumeglobal.shipmentmanagement.model.Lane;
import com.blumeglobal.shipmentmanagement.model.LocationMaster;
import com.blumeglobal.shipmentmanagement.request.LaneLocationRequest;
import com.blumeglobal.shipmentmanagement.request.LocationRequest;
import com.blumeglobal.shipmentmanagement.response.LaneLocationResponse;
import com.blumeglobal.shipmentmanagement.model.mapper.LocationMasterMapper;
import com.blumeglobal.shipmentmanagement.request.LocationMasterRequest;
import com.blumeglobal.shipmentmanagement.response.LocationMasterResponse;
import com.blumeglobal.shipmentmanagement.response.Response;
import com.blumeglobal.shipmentmanagement.service.ILocationMasterService;
import com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;



/**
* The LocationMasterServiceImpl implements to manage the LocationMaster.
*
*/
@Service
@REZ1Logger
@REZ1PerformanceLogger
public class LocationMasterServiceImpl implements ILocationMasterService{
	
	private static final Logger logger = LogManager.getLogger(LocationMasterServiceImpl.class);
	
	
	@Autowired
	LocationMasterRepository locationMasterRepository;
	
	@Autowired
	LocationMasterMapper locationMasterMapper;
	
	
	/**
	 * Get Location from LocationMaster API
	 * 
	 * @param TypeDescription
	 *            This is Type Description
	 * @return List of Locations' value as String
	 * @throws Exception
	 *             Handle service exceptions
	 */
	public List<String> getLocationByTypeDescription(String typeDescription) throws Exception {
		try {
			logger.info("Entering into LocationMasterServiceImpl.getLocationByTypeDescription");
			
			if(typeDescription.trim().length() == 0 || typeDescription == null) {
				return new ArrayList<String>();
			}
			
			return locationMasterRepository.getLocationByTypeDescription(typeDescription.trim());
			
		} catch (Exception ex) {
			logger.error("Exception inside LocationMasterServiceImpl.getLocationByTypeDescription.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}


	/**
	 * Get Location API
	 * 
	 * @param location
	 *            This is location
	 * @return LocationMasterResponse Location Response after fetch record
	 * @throws Exception
	 *             Handle service exceptions
	 */
	public LocationMasterResponse getAddressDetailsByLocation(String location) throws Exception {
		try {
			logger.info("Entering into LocationMasterServiceImpl.LocationMasterResponse");
			
			LocationMasterResponse locationMasterResponse = setLocationRequest(location.trim());
			
			System.out.println(locationMasterResponse);
			
			return locationMasterResponse;
			
		} catch (Exception ex) {
			logger.error("Exception inside LocationMasterServiceImpl.LocationMasterResponse.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}
	
	
	/**
	 * set Location Request Values
	 * 
	 * @param location
	 *            This is string which has location details separated with '|'
	 * @return LocationMasterResponse
	 */ 
	public LocationMasterResponse setLocationRequest(String location) {
		logger.info("Entering into LocationMasterServiceImpl.setLocationRequest");
		
		LocationMasterResponse locationMasterResponse = new LocationMasterResponse();
		
		List<String> locationList = new ArrayList<String>(Arrays.asList(location.split("\\|")));
		
		if(locationList.size() < 5 || locationList == null) {
			
	    } else {
	    	locationMasterResponse.setAddress(locationList.get(0));
	    	locationMasterResponse.setCity(locationList.get(1));
	    	locationMasterResponse.setState(locationList.get(2));
	    	locationMasterResponse.setZip(locationList.get(3));
	    	locationMasterResponse.setCountry(locationList.get(4));
	    }
		
		return locationMasterResponse;
	}


	@Override
	public List<LocationMaster> getAllMasterLocations() throws Exception {
		try {
			logger.info("Entering into LocationMasterServiceImpl.getAllMasterLocations");
			
			return locationMasterRepository.getAllMasterLocations();
			
		} catch (Exception ex) {
			logger.error("Exception inside LocationMasterServiceImpl.getLocationByTypeDescription.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}
	
	@Override
	public LocationMasterResponse updateLocation(LocationMasterRequest locationMaster) throws Exception {
		try {
			logger.info("Entering into LocationMasterServiceImpl.updateShipment");
			
			LocationMaster createReq = locationMasterMapper.map(locationMaster, LocationMaster.class);
			
			LocationMaster upload = null;
			
			createReq.setTypeDescription(createReq.getTypeDescription());
			upload = locationMasterRepository.save(createReq);
			LocationMasterResponse locationMasterResponse = locationMasterMapper.map(createReq, LocationMasterResponse.class);
			locationMasterResponse.setTypeDescription(upload.getTypeDescription());
			return locationMasterResponse;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public LocationMasterResponse addLocation(LocationMasterRequest locationMaster) throws Exception {
		LocationMasterResponse locationMasterResponse = null;
		try {
			LocationMaster location = locationMasterMapper.map(locationMaster, LocationMaster.class);
			LocationMaster upload = locationMasterRepository.save(location);
			locationMasterResponse = locationMasterMapper.map(location, LocationMasterResponse.class);
			locationMasterResponse.setLocation(upload.getLocation());
			locationMasterResponse.setLocationType(upload.getLocationType());
			locationMasterResponse.setTypeDescription(upload.getTypeDescription());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return locationMasterResponse;
	}


	@Override
	public Response deleteLocation(LocationMasterRequest locationMasterRequest) {
		Response response =null;
		String location= null;
		if(null!= locationMasterRequest & StringUtils.isNotBlank(locationMasterRequest.getLocation())) {
			location = locationMasterRequest.getLocation();
			logger.info("Location which is goine to delete is " + location);
		}else {
			response = new Response(ShipmentConstants.LOCATION_ERROR_STATUS,ShipmentConstants.LOCATION_ERROR_MESSAGE);
		}
		try {
			locationMasterRepository.deleteById(location);
			response = new Response(ShipmentConstants.LOCATION_SUCCESS_STATUS,ShipmentConstants.LOCATION_SUCCESS_MESSAGE);
		}catch (IllegalArgumentException ex) {
			logger.error("The entity does not exist"+ location+"exception is" + ex.getMessage());
			response = new Response(ShipmentConstants.LOCATION_ERROR_STATUS,ShipmentConstants.LOCATION_ERROR_MESSAGE);
		}
		catch (EmptyResultDataAccessException ex) {
			logger.error("The entity does not exist"+ location+"exception is" + ex.getMessage());
			response = new Response(ShipmentConstants.LOCATION_ERROR_STATUS,ShipmentConstants.LOCATION_ERROR_MESSAGE);
		}
		catch (Exception ex) {
			logger.error("Exception inside LocationMasterServiceImpl.deleteLocation.Catch_Block:" + ex.getMessage());
			response = new Response(ShipmentConstants.LOCATION_ERROR_STATUS,ShipmentConstants.LOCATION_ERROR_MESSAGE);
		}
		return response;
	}
	
}
