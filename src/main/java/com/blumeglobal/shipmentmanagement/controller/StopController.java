package com.blumeglobal.shipmentmanagement.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.blumeglobal.shipmentmanagement.request.ShipmentStopListRequest;
import com.blumeglobal.shipmentmanagement.response.StopResponse;
import com.blumeglobal.shipmentmanagement.service.IStopService;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The StopController is used to manage the Stop details.
 */
@RestController
@RequestMapping("/v1")
@Api(value = "Stop Service")
@REZ1Logger
@REZ1PerformanceLogger
public class StopController {
	private static final Logger logger = LogManager.getLogger(StopController.class);

	@Autowired
	private IStopService stopService;

	/**
	 * This Controller method is used to create Stop using API
	 * 
	 * @param shipmentStopListRequest This is used to pass ShipmentStopListRequest
	 *                                request data
	 * @return StopResponse upload To get response of List of StopResponses
	 * @throws Exception Handle the exceptions
	 */
	@RequestMapping(value = "/bv/shipmentStops", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create Stop ", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> createStop(@RequestBody ShipmentStopListRequest shipmentStopListRequest,

			@ApiParam(name = "shipmentId", value = "Enter Shipment Id", required = true) @RequestParam("shipmentId") Long shipmentId)
			throws Exception {

		logger.info("Entering into StopController.createStop");

		logger.debug("Inside StopController.createStop - Insert Stop");

		StopResponse stopResponse = stopService.createStop(shipmentStopListRequest, shipmentId);

		logger.debug("Outside StopController.createStop - Insert Stop");

		return new ResponseEntity<>(stopResponse, HttpStatus.OK);
	}
}
