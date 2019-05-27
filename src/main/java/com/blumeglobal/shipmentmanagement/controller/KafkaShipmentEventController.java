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

import com.blumeglobal.shipmentmanagement.request.DrayEventRequest;
import com.blumeglobal.shipmentmanagement.response.KafkaShipmentEventResponse;
import com.blumeglobal.shipmentmanagement.service.IKafkaShipmentEventService;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The KafkaShipmentEventController is used to manage the shipment event details.
 *
 */
@RestController
@RequestMapping("/v1")
@Api(value = "Kafka Shipment Event Service")
@REZ1Logger
@REZ1PerformanceLogger
public class KafkaShipmentEventController {

	private static final Logger logger = LogManager.getLogger(KafkaShipmentEventController.class);
	@Autowired
	private IKafkaShipmentEventService shipmentEventService;
	
	/**
	 * This Controller method is used to create shipment event using API
	 * 
	 * @param kafkaCallShipmentEvent
	 *            This is used to pass shipment event request data
	 * 
	 * 
	 * @return ShipmentEventResponse To get response of ShipmentEventResponse
	 * @throws Exception
	 *             Handle the exceptions
	 */
	@RequestMapping(value = "/bv/kafkashipmentevents", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Kafka Shipment Event ", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> kafkaCallShipmentEvent(@RequestBody DrayEventRequest drayEventRequest)
			throws Exception {

		logger.info("Entering into KafkaShipmentEventController.kafkaCallShipmentEvent");
		KafkaShipmentEventResponse upload = shipmentEventService.kafkaCallShipmentEvent(drayEventRequest);
		return new ResponseEntity<>(upload, HttpStatus.OK);

	}
	/**
	 * This Controller method is used to create shipment ocean event using API
	 * 
	 * @param kafkaCallShipmentOceanEvent
	 *            This is used to pass shipment ocean event request data
	 * 
	 * 
	 * @return ShipmentEventResponse To get response of ShipmentEventResponse
	 * @throws Exception
	 *             Handle the exceptions
	 */
	@RequestMapping(value = "/bv/kafkashipmentoceanevents", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Kafka Shipment Ocean Event ", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> kafkaCallShipmentOceanEvent(@RequestBody String shipmentEventsRequest)
			throws Exception {

		logger.info("Entering into KafkaShipmentEventController.kafkaCallShipmentOceanEvent");
		KafkaShipmentEventResponse upload = shipmentEventService.kafkaCallShipmentOceanEvent(shipmentEventsRequest);
		return new ResponseEntity<>(upload, HttpStatus.OK);

	}
	
	
}
