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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blumeglobal.shipmentmanagement.request.ShipmentEventRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentExceptionListRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentExceptionRequest;
import com.blumeglobal.shipmentmanagement.response.EventResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentEventResponse;
import com.blumeglobal.shipmentmanagement.service.IShipmentEventService;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The ShipmentEventControllerImpl is used to manage the shipment event details.
 *
 */
@RestController
@RequestMapping("/v1")
@Api(value = "Shipment Event Service")
@REZ1Logger
@REZ1PerformanceLogger
public class ShipmentEventController {

	private static final Logger logger = LogManager.getLogger(ShipmentEventController.class);
	@Autowired
	private IShipmentEventService shipmentEventService;

	

	/**
	 * This Controller method is used to track list of shipment events
	 criteriaSpecListy
	 * @param criteriaSpecList
	 *            This is used to pass a criteria
	 * @return List<ShipmentEventResponse> To get the list of ShipmentEvent
	 * @throws Exception
	 *             Handle the exceptions
	 */
	@RequestMapping(value = "/bv/shipmentevents/tracking", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Tracking Event list", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
			@ApiResponse(code = 500, message = "The GET call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getTrackingEvents(@ApiParam(name = "search", value = "Enter criteria", required = true)  @RequestParam("search") String criteriaSpecList)
			throws Exception {

		logger.info("Entering into ShipmentEventControllerImpl.getTrackingEvents");
		List<ShipmentEventResponse> shipmentevent = shipmentEventService.getSearchShipmentEvents(criteriaSpecList);
		return new ResponseEntity<>(shipmentevent, HttpStatus.OK);

	}
	


	/**
	 * This Controller method is used to create shipment event using API
	 * 
	 * @param createEventsRequest
	 *            This is used to pass shipment event request data
	 * 
	 * 
	 * @return ShipmentEventResponse To get response of ShipmentEventResponse
	 * @throws Exception
	 *             Handle the exceptions
	 */
	@RequestMapping(value = "/bv/shipmentevents", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create Shipment Event ", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> createShipmentEvent(@RequestBody ShipmentEventRequest createEventsRequest)
			throws Exception {

		logger.info("Entering into ShipmentEventControllerImpl.createShipmentEvent");
		EventResponse upload = shipmentEventService.createShipmentEvent(createEventsRequest);
		logger.debug("Inside ShipmentEventControllerImpl.createShipmentEvent - Insert ShipmentEvent");
		return new ResponseEntity<>(upload, HttpStatus.OK);

	}
	
	/**
	 * This Controller method is used to delete shipment event using API
	 * 
	 * @param shipmentEventId
	 *            This is used to pass shipment event id
	 * 
	 * 
	 * @return EventResponse To get response of EventResponse
	 * @throws Exception
	 *             Handle the exceptions
	 */
	@RequestMapping(value = "/bv/shipmentevents/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "delete shipment event by shipment event Id ", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> deleteShipmentEvent(@ApiParam(name = "shipmentEventId", value = "shipment event id", required = true) @RequestParam("shipmentEventId")Long shipmentEventId)
			throws Exception {

		logger.info("Entering into ShipmentEventControllerImpl.deleteShipmentEvent");
		EventResponse upload = shipmentEventService.deleteShipmentEvent(shipmentEventId);

		logger.debug("Inside ShipmentEventControllerImpl.deleteShipmentEvent - delete ShipmentEvent");
		return new ResponseEntity<>(upload, HttpStatus.OK);

	}	
	
	/**
	 * This Controller method is used to retrieve shipment event by shipmentNumber or houseBill or masterBill or onHand
	 * 
	 * @param id
	 *            This is used to pass shipment event shipmentNumber or houseBill or masterBill or onHand
	 
	 * @return List<ShipmentEventResponse> To get the list of
	 *         ShipmentEventResponse
	 * @throws Exception
	 *             Handle the exceptions
	 */
	@RequestMapping(value = "/bv/shipmentevents/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Shipment Event by id", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
			@ApiResponse(code = 500, message = "The GET call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getShipmentEvents(
			@ApiParam(name = "id", value = "Enter any shipment references details like Shipment No,UnitId,etc..", required = true) @PathVariable("id") String id)	throws Exception {

		logger.info("Entering into ShipmentEventControllerImpl.getShipmentEvents");
		List<ShipmentEventResponse> shipmentevents = shipmentEventService.getShipmentEvents(id);
		return new ResponseEntity<>(shipmentevents, HttpStatus.OK);

	}
	
	/**
	 * This Controller method is used to post a Shipment Event Exception
	 * @param createExceptionRequest
	 *            This is used to pass an exception to create
	 * @throws Exception
	 *             Handle the exceptions
	 */
	@RequestMapping(value = "/bv/shipmentevents/exception", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create Shipment Event Exception", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> createException(@RequestBody ShipmentExceptionRequest createExceptionRequest) throws Exception {
		logger.info("Entering into ShipmentEventControllerImpl.createException");
		EventResponse shipmentexception = shipmentEventService.createShipmentException(createExceptionRequest);
		return new ResponseEntity<>(shipmentexception, HttpStatus.OK);

	}
	
    /**
     * This Controller method is used to post a list of Shipment Event Exceptions
     * @param createExceptionListRequest
     *            This is used to pass a list of exception to create
     * @throws Exception
     *             Handle the exceptions
     */
    @RequestMapping(value = "/bv/shipmentevents/exceptions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Shipment Event Exception", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
            @ApiResponse(code = 500, message = "The POST call is Failed"),
            @ApiResponse(code = 404, message = "The API could not be found"),
            @ApiResponse(code = 400, message = "Invalid input") })
    public ResponseEntity<?> createException(@RequestBody ShipmentExceptionListRequest createExceptionListRequest) throws Exception {
        logger.info("Entering into ShipmentEventControllerImpl.createException");
        EventResponse shipmentexception = shipmentEventService.createShipmentExceptions(createExceptionListRequest);
        return new ResponseEntity<>(shipmentexception, HttpStatus.OK);

    }
}
