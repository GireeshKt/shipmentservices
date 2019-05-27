package com.blumeglobal.shipmentmanagement.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blumeglobal.shipmentmanagement.response.ShipmentLegDetailsResponse;
import com.blumeglobal.shipmentmanagement.service.IShipmentLegDetailsService;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
/**
 * The ShipmentLegController is used to manage the shipment leg details.
 *
 */
@RestController
@RequestMapping("/v1")
@Api(value = "Shipment Leg Details Service")
@REZ1Logger
@REZ1PerformanceLogger
public class ShipmentLegController {
	
	private static final Logger logger = LogManager.getLogger(ShipmentLegController.class);
	@Autowired
	private IShipmentLegDetailsService shipmentLegDetailsService;
	
	/**
	 * This Controller method is used to retrieve list of shipment legs
	 * @param criteriaSpecList
	 *            This is used to pass a criteria
	 * @param operation
	 *            This is used to pass an operation
	 * @param legMode
	 *            This is used to pass a leg mode
	 * @return List<ShipmentLegDetailsResponse> To get the list of Shipment legs
	 * @throws Exception
	 *             Handle the exceptions
	 */
//	@RequestMapping(value = "/shipmentlegs/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "Get Shipment Leg Details list", produces = "application/json", consumes = "application/json")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
//			@ApiResponse(code = 500, message = "The GET call is Failed"),
//			@ApiResponse(code = 404, message = "The API could not be found"),
//			@ApiResponse(code = 400, message = "Invalid input") })
//	public ResponseEntity<?> searchShipmentLegs(@ApiParam(name = "query", value = "Enter criteria", required = true)  @RequestParam("query") String criteriaSpecList, @ApiParam(value = "Supported operation for Query Criteria. Here are the supported operation.[AND, OR]", required = true) @RequestParam("operation") String operation, @ApiParam(value = "Supported legmode of workorder. Here are the supported legmode.[ALL, DRAY, RAIL]", required = true) @RequestParam("legmode") String legMode)
//			throws Exception {
//		logger.info("Entering into ShipmentLegController.searchShipmentLegs");
//		List<ShipmentLegDetailsResponse> shipmentlegs = shipmentLegDetailsService.searchShipmentLegs(criteriaSpecList, operation,legMode);
//		return new ResponseEntity<>(shipmentlegs, HttpStatus.OK); 
//	}
	
	
	/**
	 * This Controller method is used to retrieve list of shipment legs
	 * @param id
	 *            This is used to pass a woBookingNumber or unitId or masterAirwayBillNumber or billOfLadingNumber or bookingNumber or railBillingNumber or houseAirwayBillNumber
	 * @param legMode
	 *            This is used to pass a leg mode
	 * @return List<ShipmentLegDetailsResponse> To get the list of Shipment legs
	 * @throws Exception
	 *             Handle the exceptions
	 */
//	@RequestMapping(value = "/shipmentlegs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "Get Shipment Leg Details By IDs and Mode", produces = "application/json", consumes = "application/json")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
//			@ApiResponse(code = 500, message = "The GET call is Failed"),
//			@ApiResponse(code = 404, message = "The API could not be found"),
//			@ApiResponse(code = 400, message = "Invalid input") })
//	public ResponseEntity<?> getShipmentLegbyIdAndMode(@ApiParam(name = "id", value = "Enter shipment search value like workorderno,unitid,billOfLadingNumber,railBillingNumber,etc..", required = true) @PathVariable("id") String id, @ApiParam(value = "Supported legmode for workorder. Here are the supported legmode.[ALL,DRAY, RAIL]", required = true) @RequestParam("legmode") String legMode)
//			throws Exception {
//		logger.info("Entering into ShipmentLegController.getShipmentLegbyIdAndMode");
//		List<ShipmentLegDetailsResponse> shipmentlegs = shipmentLegDetailsService.findShipmentLegByModeAndIDs(id, legMode);
//		return new ResponseEntity<>(shipmentlegs, HttpStatus.OK); 
//	}

}
