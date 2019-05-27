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
import org.springframework.web.bind.annotation.RestController;

import com.blumeglobal.shipmentmanagement.request.ShipmentWorkOrderRequest;
import com.blumeglobal.shipmentmanagement.response.KafkaShipmentResponse;
import com.blumeglobal.shipmentmanagement.service.IKafkaShipmentService;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The ShipmentControllerImpl is used to manage the shipment details.
 *
 */
@RestController
@RequestMapping("/v1")
@Api(value = "Kafka Shipment Service")
@REZ1Logger
@REZ1PerformanceLogger
public class KafkaShipmentController {
	private static final Logger logger = LogManager.getLogger(KafkaShipmentController.class);
	
	@Autowired
	IKafkaShipmentService shipmentService;
	
	/**
	 * This Controller method is used to create shipment using API
	 * 
	 * @param createRequest
	 *            This is used to pass shipment request data
	 * @return ShipmentResponse upload To get response of ShipmentResponse
	 * @throws Exception
	 *             Handle the exceptions
	 */
	@RequestMapping(value = "/bv/kafkashipments", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Kafka Shipment WorkOrder", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
public ResponseEntity<?> kafkaCallShipmentV1(@RequestBody ShipmentWorkOrderRequest shipmentWorkOrderRequest)throws Exception {	
		logger.info("Entering into KafkaShipmentController.kafkaCallShipmentV1");
		KafkaShipmentResponse upload = shipmentService.kafkaCallShipmentV1(shipmentWorkOrderRequest);
		return new ResponseEntity<>(upload, HttpStatus.OK);
	}
	
}
