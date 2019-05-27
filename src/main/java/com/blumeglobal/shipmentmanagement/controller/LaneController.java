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

import com.blumeglobal.shipmentmanagement.request.LaneGroupsRequest;
import com.blumeglobal.shipmentmanagement.request.LaneLocationRequest;
import com.blumeglobal.shipmentmanagement.request.LaneRequest;
import com.blumeglobal.shipmentmanagement.response.LaneAPICreateUpdateResponse;
import com.blumeglobal.shipmentmanagement.response.LaneAPIDeleteResponse;
import com.blumeglobal.shipmentmanagement.response.LaneGroupAPIDeleteResponse;
import com.blumeglobal.shipmentmanagement.response.LaneGroupsResponse;
import com.blumeglobal.shipmentmanagement.response.LaneLocationResponse;
import com.blumeglobal.shipmentmanagement.response.LaneResponse;
import com.blumeglobal.shipmentmanagement.service.ILaneService;
import com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The LaneController is used to manage the Lane details.
 *
 */
@RestController
@RequestMapping("/v1")
@Api(value = "Lane Service")
@REZ1Logger
@REZ1PerformanceLogger
public class LaneController {

	private static final Logger logger = LogManager.getLogger(LaneController.class);

	@Autowired
	ILaneService laneService;
// LaneController service is not used as a part of shipment service. It is a part of rating and routing project now. 
	
//	@RequestMapping(value = "/bv/Lanes/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "get Lane by Lane ID", produces = "application/json", consumes = "application/json")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
//			@ApiResponse(code = 500, message = "The GET call is Failed"),
//			@ApiResponse(code = 404, message = "The API could not be found"),
//			@ApiResponse(code = 400, message = "Invalid input") })
//	public ResponseEntity<?> getLanebyId(
//			@ApiParam(name = "id", value = "Enter Lane id", required = true) @PathVariable("id") String id)
//			throws Exception {
//		logger.info("Entering into LaneController.getLanebyId");
//		LaneLocationRequest laneLocationRequest = laneService.getLaneById(id);
//		return new ResponseEntity<>(laneLocationRequest, HttpStatus.OK);
//	}

//	@RequestMapping(value = "/bv/Lanes/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "Create Lane", produces = "application/json", consumes = "application/json")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
//			@ApiResponse(code = 500, message = "The POST call is Failed"),
//			@ApiResponse(code = 404, message = "The API could not be found"),
//			@ApiResponse(code = 400, message = "Invalid input") })
//	public ResponseEntity<?> createLane(@RequestBody LaneRequest laneRequest) throws Exception {
//		LaneLocationResponse laneLocationRequest = laneService.createLane(laneRequest);
//		if (null == laneLocationRequest) {
//			laneLocationRequest = new LaneLocationResponse(ShipmentConstants.LANE_ERROR_STATUS,
//					ShipmentConstants.LANE_ERROR_MESSAGE);
//			return new ResponseEntity<>(laneLocationRequest, HttpStatus.BAD_REQUEST);
//		} else {
//			return new ResponseEntity<>(laneLocationRequest, HttpStatus.OK);
//		}		
//	}

//	@RequestMapping(value = "/bv/lanegroup/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "Create Lane Group", produces = "application/json", consumes = "application/json", tags = {
//			"Lane Group" })
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
//			@ApiResponse(code = 500, message = "The POST call is Failed"),
//			@ApiResponse(code = 404, message = "The API could not be found"),
//			@ApiResponse(code = 400, message = "Invalid input") })
//	public ResponseEntity<?> createLaneGroup(@RequestBody LaneGroupsRequest laneGroupRequest) throws Exception {
//		LaneGroupsResponse laneLocationResponse = laneService.createLaneGroup(laneGroupRequest);
//		return new ResponseEntity<>(laneLocationResponse, HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "/bv/Lanes/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "Get all Lane Ids", produces = "application/json", consumes = "application/json")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
//			@ApiResponse(code = 500, message = "The GET call is Failed"),
//			@ApiResponse(code = 404, message = "The API could not be found"),
//			@ApiResponse(code = 400, message = "Invalid input") })
//	public ResponseEntity<?> getAllLaneIds() throws Exception {
//		List<String> idList = laneService.getAllLaneIds();
//		return new ResponseEntity<>(idList, HttpStatus.OK);
//	}

//	@RequestMapping(value = "/bv/laneGroupIds", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "Get all LaneGroup Ids", produces = "application/json", consumes = "application/json", tags = {
//			"Lane Group" })
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
//			@ApiResponse(code = 500, message = "The GET call is Failed"),
//			@ApiResponse(code = 404, message = "The API could not be found"),
//			@ApiResponse(code = 400, message = "Invalid input") })
//	public ResponseEntity<?> getAllLaneGroupIds() throws Exception {
//		List<String> idList = laneService.getAllLaneGroupIds();
//		return new ResponseEntity<>(idList, HttpStatus.OK);
//	}

	/**
	 * This Controller method is used to Delete Lane Based on ID
	 * 
	 * @throws Exception Handle the exceptions
	 */

//	@RequestMapping(value = "/deleteLane/{laneId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "Delete Lane By Id through API", produces = "application/json", consumes = "application/json")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The DELETE call is Successful"),
//			@ApiResponse(code = 500, message = "The DELETE call is Failed"),
//			@ApiResponse(code = 404, message = "The API could not be found"),
//			@ApiResponse(code = 400, message = "Invalid input") })
//	public ResponseEntity<?> deleteLaneById(
//			@ApiParam(name = "laneId", value = "Enter Lane ID", required = true) @PathVariable("laneId") String laneId)
//			throws Exception {
//		LaneAPIDeleteResponse dlt = laneService.deleteLaneById(laneId);
//		return new ResponseEntity<LaneAPIDeleteResponse>(dlt, HttpStatus.OK);
//	}

	/**
	 * This Controller method is used to update Lane from API
	 * 
	 * @throws Exception Handle the exceptions
	 */
//	@RequestMapping(value = "/updateLane", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "Update Lane through API", produces = "application/json", consumes = "application/json")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The PUT call is Successful"),
//			@ApiResponse(code = 500, message = "The PUT call is Failed"),
//			@ApiResponse(code = 404, message = "The API could not be found"),
//			@ApiResponse(code = 400, message = "Invalid input") })
//	public ResponseEntity<?> updateLane(@RequestBody LaneRequest laneRequest) throws Exception {
//
//		LaneAPICreateUpdateResponse poRes = laneService.updateLane(laneRequest, laneRequest.getLaneId());
//		return new ResponseEntity<>(poRes, HttpStatus.OK);
//	}

	/**
	 * @Description : This service will get all the available lane records
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value = "/lanes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "Get all Lane Records", produces = "application/json", consumes = "application/json")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
//			@ApiResponse(code = 500, message = "The GET call is Failed"),
//			@ApiResponse(code = 404, message = "The API could not be found"),
//			@ApiResponse(code = 400, message = "Invalid input") })
//	public ResponseEntity<?> getAllLanes() throws Exception {
//		List<LaneResponse> response = laneService.getAllLanes();
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}

	/**
	 * @Description : This service will get all the available lane records
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value = "/laneGroups", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "Get all Lane Group Records", produces = "application/json", consumes = "application/json", tags = {
//			"Lane Group" })
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
//			@ApiResponse(code = 500, message = "The GET call is Failed"),
//			@ApiResponse(code = 404, message = "The API could not be found"),
//			@ApiResponse(code = 400, message = "Invalid input") })
//	public ResponseEntity<?> getAllLaneGroupss() throws Exception {
//		List<LaneGroupsResponse> response = laneService.getAllLaneGroups();
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}

	/**
	 * This Controller method is used to update LaneGroup from API
	 * 
	 * @throws Exception Handle the exceptions
	 */
//	@RequestMapping(value = "/LaneGroups", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "Update Lane through API", produces = "application/json", consumes = "application/json", tags = {
//			"Lane Group" })
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The PUT call is Successful"),
//			@ApiResponse(code = 500, message = "The PUT call is Failed"),
//			@ApiResponse(code = 404, message = "The API could not be found"),
//			@ApiResponse(code = 400, message = "Invalid input") })
//	public ResponseEntity<?> updateLaneGroup(@RequestBody LaneGroupsRequest laneGroupsRequest) throws Exception {
//
//		LaneGroupsResponse poRes = laneService.updateLaneGroup(laneGroupsRequest, laneGroupsRequest.getLanegroupId());
//		return new ResponseEntity<>(poRes, HttpStatus.OK);
//	}

	/**
	 * This Controller method is used to Delete Lane Group Based on ID
	 * 
	 * @throws Exception Handle the exceptions
	 */

//	@RequestMapping(value = "/LaneGroup/{laneGroupId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "Delete Lane Group By Id through API", produces = "application/json", consumes = "application/json", tags = {
//			"Lane Group" })
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The DELETE call is Successful"),
//			@ApiResponse(code = 500, message = "The DELETE call is Failed"),
//			@ApiResponse(code = 404, message = "The API could not be found"),
//			@ApiResponse(code = 400, message = "Invalid input") })
//	public ResponseEntity<?> deleteLaneGroupById(
//			@ApiParam(name = "laneGroupId", value = "Enter LaneGroup ID", required = true) @PathVariable("laneGroupId") String laneGroupId)
//			throws Exception {
//		LaneGroupAPIDeleteResponse dltG = laneService.deleteLaneGroupById(laneGroupId);
//		return new ResponseEntity<LaneGroupAPIDeleteResponse>(dltG, HttpStatus.OK);
//	}
}
