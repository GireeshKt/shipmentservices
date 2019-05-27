package com.blumeglobal.shipmentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blumeglobal.shipmentmanagement.request.RoutingRequest;
import com.blumeglobal.shipmentmanagement.response.RoutingAPICreateUpdateResponse;
import com.blumeglobal.shipmentmanagement.response.RoutingAPIDeleteResponse;
import com.blumeglobal.shipmentmanagement.response.RoutingGetResponse;
import com.blumeglobal.shipmentmanagement.response.RoutingResponse;
import com.blumeglobal.shipmentmanagement.service.IRoutingService;
import com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants;

import io.swagger.annotations.*;

@RestController
@RequestMapping("/v1")
@Api(value = "Routing Service")
public class RoutingController {

	@Autowired
	private IRoutingService routingService;

// RoutingController is not used as a part of shipment service. It is a part of routing service project now. 
	/**
	 * This Controller method is used to create routing from API
	 * 
	 * @param routingApiRequest This is used to pass routing form data
	 * 
	 * @return routingApiRequest To return the routingApiRequest after save
	 * @throws Exception Handle the exceptions
	 */
//	@RequestMapping(value = "/createRouting", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "Create Routing through API", produces = "application/json", consumes = "application/json",tags = {"Routing Service"})
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
//			@ApiResponse(code = 500, message = "The POST call is Failed"),
//			@ApiResponse(code = 404, message = "The API could not be found"),
//			@ApiResponse(code = 400, message = "Invalid input") })
//	public ResponseEntity<?> createRoutingNew(@RequestBody RoutingRequest routingRequest) throws Exception {
//		RoutingAPICreateUpdateResponse responseRouting = routingService.createRoutingNew(routingRequest);
//		if(null == responseRouting) {
//			responseRouting =new RoutingAPICreateUpdateResponse(ShipmentConstants.ROUTING_ERROR_STATUS,ShipmentConstants.ROUTING_ERROR_MESSAGE);
//			return new ResponseEntity<>(responseRouting, HttpStatus.BAD_REQUEST);
//		}else {
//			return new ResponseEntity<>(responseRouting, HttpStatus.OK);
//		}
//		
//	}


	/**
	 * This Controller method is used to retrieve list of routing 
	 * @throws Exception Handle the exceptions
	 */

//	@RequestMapping(value = "/getAllRoutings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "Get list of Routing", produces = "application/json", consumes = "application/json",tags = {"Routing Service"})
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
//			@ApiResponse(code = 500, message = "The GET call is Failed"),
//			@ApiResponse(code = 404, message = "The API could not be found"),
//			@ApiResponse(code = 400, message = "Invalid input") })
//	public ResponseEntity<?> getAllRoutings() throws Exception {
//
//		List<RoutingResponse> routings = routingService.getAllRoutings();
//		return new ResponseEntity<>(routings, HttpStatus.OK);
//	}	

	
	/**
	 * This Controller method is used to Delete routing Based on ID
	 * @throws Exception Handle the exceptions
	 */

//	@RequestMapping(value = "/deleteRouting/{routingId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "Delete Routing By Id through API", produces = "application/json", consumes = "application/json",tags = {"Routing Service"})
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The DELETE call is Successful"),
//			@ApiResponse(code = 500, message = "The DELETE call is Failed"),
//			@ApiResponse(code = 404, message = "The API could not be found"),
//			@ApiResponse(code = 400, message = "Invalid input") })
//	public ResponseEntity<?> deleteRoutingById(
//			@ApiParam(name = "routingId", value = "Enter Routing ID", required = true)  @PathVariable("routingId") String routingId
//			) throws Exception {
//
//		RoutingAPIDeleteResponse dlt= routingService.deleteRoutingById(routingId);
//		return new ResponseEntity<RoutingAPIDeleteResponse>(dlt,HttpStatus.OK);
//	}
	
	/**
	 * This Controller method is used to update Routing from API
	 * @throws Exception Handle the exceptions
	 */
//	@RequestMapping(value = "/updateRouting", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "Update Routing through API", produces = "application/json", consumes = "application/json",tags = {"Routing Service"})
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The PUT call is Successful"),
//			@ApiResponse(code = 500, message = "The PUT call is Failed"),
//			@ApiResponse(code = 404, message = "The API could not be found"),
//			@ApiResponse(code = 400, message = "Invalid input") })
//	public ResponseEntity<?> updateRouting(@RequestBody RoutingRequest routingRequest
//			) throws Exception {
//
//		RoutingAPICreateUpdateResponse routingRes = routingService.updateRouting(routingRequest, routingRequest.getRoutingid());
//		return new ResponseEntity<>(routingRes,HttpStatus.OK);
//	}

	/**
	 * This Controller method is used to retrieve list of routing based on ID
	 * @throws Exception Handle the exceptions
	 */

//	@RequestMapping(value = "/getRoutingByID/{routingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "Get Routing By Id", produces = "application/json", consumes = "application/json",tags = {"Routing Service"})
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
//			@ApiResponse(code = 500, message = "The GET call is Failed"),
//			@ApiResponse(code = 404, message = "The API could not be found"),
//			@ApiResponse(code = 400, message = "Invalid input")})
//	public ResponseEntity<?> getRoutingById(
//			@ApiParam(name = "routingId", value = "Enter Routing ID", required = true) @PathVariable("routingId") String routingId)throws Exception {
//
//		RoutingGetResponse routing = routingService.getRoutingById(routingId);
//		return new ResponseEntity<>(routing, HttpStatus.OK);
//	}

}
