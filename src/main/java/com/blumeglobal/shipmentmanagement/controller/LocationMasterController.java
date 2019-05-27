package com.blumeglobal.shipmentmanagement.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blumeglobal.shipmentmanagement.model.LocationMaster;
import com.blumeglobal.shipmentmanagement.request.LaneLocationRequest;
import com.blumeglobal.shipmentmanagement.request.LocationMasterRequest;
import com.blumeglobal.shipmentmanagement.request.LocationObjectRequest;
import com.blumeglobal.shipmentmanagement.response.LocationMasterResponse;
import com.blumeglobal.shipmentmanagement.response.Response;
import com.blumeglobal.shipmentmanagement.response.RoutingResponse;
import com.blumeglobal.shipmentmanagement.service.ILaneService;
import com.blumeglobal.shipmentmanagement.service.ILocationMasterService;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The LocationMasterController is used to manage the LocationMaster details.
 *
 */
@RestController
@RequestMapping("/v1")
@Api(value = "LocationMaster Service")
@REZ1Logger
@REZ1PerformanceLogger
public class LocationMasterController {
	
	private static final Logger logger = LogManager.getLogger(LocationMasterController.class);
	
	@Autowired
	ILocationMasterService locationMasterService;
	
	
	@RequestMapping(value = "/bv/LocationMasters/{typeDescription}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get Locations by TypeDescription", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
	@ApiResponse(code = 500, message = "The GET call is Failed"),
	@ApiResponse(code = 404, message = "The API could not be found"),
	@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getLocationByTypeDescription(@ApiParam(name = "typeDescription", value = "Enter Type Description", required = true) @PathVariable("typeDescription") String typeDescription) throws Exception {
		logger.info("Entering into LocationMasterController.getLocationByTypeDescription");
		List<String> locations = locationMasterService.getLocationByTypeDescription(typeDescription);
		System.out.println(locations);
		return new ResponseEntity<>(locations, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/bv/Locations", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get Location Details by Location", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
	@ApiResponse(code = 500, message = "The GET call is Failed"),
	@ApiResponse(code = 404, message = "The API could not be found"),
	@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getAddressDetailsByLocation(@RequestBody LocationObjectRequest locationObjectRequest) throws Exception {
		logger.info("Entering into LocationMasterController.getAddressDetailsByLocation");
		LocationMasterResponse locationMasterResponse = locationMasterService.getAddressDetailsByLocation(locationObjectRequest.getLocation());
		return new ResponseEntity<>(locationMasterResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bv/AllLocationMasters", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get All Locations", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
	@ApiResponse(code = 500, message = "The GET call is Failed"),
	@ApiResponse(code = 404, message = "The API could not be found"),
	@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getAllMasterLocations() throws Exception {
		logger.info("Entering into LocationMasterController.getAllMasterLocations");
		List<LocationMaster> locationList = locationMasterService.getAllMasterLocations();
		return new ResponseEntity<>(locationList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bv/UpdateLocationMaster", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "update Location", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The PUT call is Successful"),
	@ApiResponse(code = 500, message = "The PUT call is Failed"),
	@ApiResponse(code = 404, message = "The API could not be found"),
	@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> updateLocation (@RequestBody LocationMasterRequest LocationMasterRequest) throws Exception {
	  logger.info("Entering into LocationMasterController.updateLocation");
	  LocationMasterResponse upload = locationMasterService.updateLocation(LocationMasterRequest);
	  return new ResponseEntity<>(upload, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bv/AddLocationMaster", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "add Location", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
	@ApiResponse(code = 500, message = "The POST call is Failed"),
	@ApiResponse(code = 404, message = "The API could not be found"),
	@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> addLocation (@RequestBody LocationMasterRequest LocationMasterRequest) throws Exception {
	  logger.info("Entering into LocationMasterController.addLocation");
	  LocationMasterResponse upload = locationMasterService.addLocation(LocationMasterRequest);
	  return new ResponseEntity<>(upload, HttpStatus.OK);
	}
	//Created the method as PUT as we cannot pass body for delete from UI side, Since this table, does not have integer primary key, 
	//we had to pass the lengthy string in the URL
	@RequestMapping(value = "/bv/locationMaster", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete  Location", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The DELETE call is Successful"),
	@ApiResponse(code = 500, message = "The DELETE call is Failed"),
	@ApiResponse(code = 404, message = "The API could not be found"),
	@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> deleteLocation (@RequestBody LocationMasterRequest LocationMasterRequest) throws Exception {
	  logger.info("Entering into LocationMasterController.deleteLocation");
	  Response response = locationMasterService.deleteLocation(LocationMasterRequest);
	  return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
}







