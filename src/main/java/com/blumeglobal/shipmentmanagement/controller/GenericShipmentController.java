package com.blumeglobal.shipmentmanagement.controller;

import javax.validation.Valid;
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

import com.blumeglobal.shipmentmanagement.request.ShipmentStatusRequest;
import com.blumeglobal.shipmentmanagement.request.common.GenericShipmentRequest;
import com.blumeglobal.shipmentmanagement.response.ShipmentResponse;
import com.blumeglobal.shipmentmanagement.response.common.GenericShipmentResponse;
import com.blumeglobal.shipmentmanagement.service.IGenericShipmentService;
import com.blumeglobal.shipmentmanagement.service.IGenericShipmentViewService;
import com.blumeglobal.shipmentmanagement.service.IKafkaShipmentService;
import com.blumeglobal.shipmentmanagement.service.IMilestoneService;
import com.blumeglobal.shipmentmanagement.service.IShipmentMilestoneService;
import com.blumeglobal.shipmentmanagement.service.IShipmentScheduleService;
import com.blumeglobal.shipmentmanagement.service.IShipmentStopService;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The generic shipment service controller is used to manage the generic shipment request.
 *
 */
@RestController
@RequestMapping("/v2")
@Api(value = "Generic Shipment Service")
@REZ1Logger
@REZ1PerformanceLogger
public class GenericShipmentController {
    private static final Logger logger = LogManager.getLogger(GenericShipmentController.class);

    @Autowired
    IGenericShipmentService shipmentService;

    @Autowired
    IGenericShipmentViewService shipmentViewService;

    @Autowired
    IShipmentMilestoneService shipmentMilestoneService;

    @Autowired
    IShipmentScheduleService shipmentScheduleService;

    @Autowired
    IShipmentStopService shipmentStopService;

    @Autowired
    IMilestoneService milestoneService;

    @Autowired
    IKafkaShipmentService kafkaShipmentService;


    /**
     * This Controller method is used to create shipment using API
     * 
     * @param createShipmentRequest This is used to pass shipment request data
     * @return ShipmentResponse upload To get response of ShipmentResponse
     * @throws Exception Handle the exceptions
     */
    @RequestMapping(value = "/bv/shipment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates Shipment ", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "The POST call is Successful"),
            @ApiResponse(code = 500, message = "The POST call is Failed"),
            @ApiResponse(code = 404, message = "The API could not be found"),
            @ApiResponse(code = 400, message = "Invalid input")})
    public ResponseEntity<?> createShipment(@RequestBody @Valid GenericShipmentRequest createShipmentRequest)
            throws Exception {
        logger.info("Entering into GenericShipmentController.createShipment");
        ShipmentResponse upload = shipmentService.createShipment(createShipmentRequest);
        // publish to Kafka topic for generic shipment
        if (upload.getId() != null) {
            createShipmentRequest.setShipmentId(Long.valueOf(upload.getId()));
           // kafkaShipmentService.kafkaCallGenericShipment(createShipmentRequest);
        }
        logger.debug("Inside GenericShipmentController.createShipment - Insert Shipment");
        return new ResponseEntity<>(upload, HttpStatus.OK);
    }

    /**
     * This Controller method is used to update shipment using API
     * 
     * @param updateShipmentRequest This is used to pass shipment request data
     * @return ShipmentResponse upload To get response of ShipmentResponse
     * @throws Exception Handle the exceptions
     */
    @RequestMapping(value = "/bv/shipment/update", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Shipment ", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "The POST call is Successful"),
            @ApiResponse(code = 500, message = "The POST call is Failed"),
            @ApiResponse(code = 404, message = "The API could not be found"),
            @ApiResponse(code = 400, message = "Invalid input")})
    public ResponseEntity<?> updateShipment(@RequestBody @Valid GenericShipmentRequest updateShipmentRequest)
            throws Exception {
        logger.info("Entering into GenericShipmentController.updateShipment");
        updateShipmentRequest.setUpdate(true);
        ShipmentResponse upload = shipmentService.createShipment(updateShipmentRequest);
        // publish to Kafka topic for creating workorder
        if (upload.getId() != null) {
            updateShipmentRequest.setShipmentId(Long.valueOf(upload.getId()));
            kafkaShipmentService.kafkaCallWorkorder(updateShipmentRequest);
        }
        logger.debug("Inside GenericShipmentController.updateShipment");
        return new ResponseEntity<>(upload, HttpStatus.OK);
    }

    @RequestMapping(value = "/bv/shipment/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get shipment by shipment ID", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "The GET call is Successful"),
            @ApiResponse(code = 500, message = "The GET call is Failed"),
            @ApiResponse(code = 404, message = "The API could not be found"),
            @ApiResponse(code = 400, message = "Invalid input")})
    public ResponseEntity<?> getShipmentbyId(
            @ApiParam(name = "id", value = "Enter shipment id", required = true) @PathVariable("id") String id)
            throws Exception {
        logger.info("Entering into GenericShipmentController.getShipmentbyId");
        GenericShipmentResponse shipmentResponse = shipmentViewService.getShipmentDetailById(Long.parseLong(id));
        return new ResponseEntity<>(shipmentResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/bv/shipment/shipmentsearch", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Shipment by reference type and value", produces = "application/json",
            consumes = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "The GET call is Successful"),
            @ApiResponse(code = 500, message = "The GET call is Failed"),
            @ApiResponse(code = 404, message = "The API could not be found"),
            @ApiResponse(code = 400, message = "Invalid input")})
    public ResponseEntity<?> searchShipmentByRefTypeValue(
            @ApiParam(name = "refType",
                    value = "Enter shipment reference type such as WORKORDERNUM,BOOKINGNUM,"
                            + "SHIPMENTREFNUM,BILLOFLADING, etc.",
                    required = true) @RequestParam("refType") String refType,
            @ApiParam(name = "refValue", value = "Enter searched reference value",
                    required = true) @RequestParam("refValue") String refValue)
            throws Exception {
        logger.info("Entering into GenericShipmentController.searchShipmentByRefTypeValue");
        //FIXME this API return should be changed to List of shipments
        GenericShipmentResponse shipmentResponse = shipmentViewService.searchShipmentByRefTypeValue(refType, refValue);
        return new ResponseEntity<>(shipmentResponse, HttpStatus.OK);

    }
    
    
    @RequestMapping(value = "/bv/shipmentStatus", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update Shipments' status by shipment reference type and shipment reference value", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
	@ApiResponse(code = 500, message = "The GET call is Failed"),
	@ApiResponse(code = 404, message = "The API could not be found"),
	@ApiResponse(code = 400, message = "Invalid input") }) 
	public ResponseEntity<?> updateShipmentStatusByrefTypeValue(@RequestBody ShipmentStatusRequest shipmentStatusRequest) throws Exception {
		logger.info("Entering into ShipmentController.updateShipmentStatusByrefTypeValue");
		ShipmentResponse shipmentResponse = shipmentService.updateShipmentStatus(shipmentStatusRequest);
		return new ResponseEntity<>(shipmentResponse, HttpStatus.OK); 
		
	}
}
