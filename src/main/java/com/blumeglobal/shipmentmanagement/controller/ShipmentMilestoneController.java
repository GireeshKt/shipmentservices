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

import com.blumeglobal.shipmentmanagement.request.MilestoneRequest;
import com.blumeglobal.shipmentmanagement.response.MilestoneResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentMilestoneResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentMilestonesResponse;
import com.blumeglobal.shipmentmanagement.service.IMilestoneService;
import com.blumeglobal.shipmentmanagement.service.IShipmentMilestoneService;
import com.blumeglobal.shipmentmanagement.service.IShipmentScheduleService;
import com.blumeglobal.shipmentmanagement.service.IShipmentService;
import com.blumeglobal.shipmentmanagement.service.IShipmentStopService;
import com.blumeglobal.shipmentmanagement.service.IShipmentViewService;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The ShipmentControllerImpl is used to manage the shipment details.
 *
 */
@RestController
@RequestMapping("/v1")
@Api(value = "Shipment Milestone Service")
@REZ1Logger
@REZ1PerformanceLogger
public class ShipmentMilestoneController {
	private static final Logger logger = LogManager.getLogger(ShipmentMilestoneController.class);
	
	@Autowired
	IShipmentService shipmentService;
	
	@Autowired
	IShipmentViewService shipmentViewService;
	
	@Autowired
	IShipmentMilestoneService shipmentMilestoneService;
	
	@Autowired
	IShipmentScheduleService shipmentScheduleService;
	
	@Autowired
	IShipmentStopService shipmentStopService;
	
	@Autowired
	IMilestoneService milestoneService;
	 
	
	
	@RequestMapping(value = "/bv/milestones/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create Milestone", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> createMilestone(@RequestBody MilestoneRequest milestoneRequest){
		MilestoneResponse upload = milestoneService.createMilestone(milestoneRequest);
		return new ResponseEntity<>(upload, HttpStatus.OK);
	}
	
	/**
	 * This Controller method is used to get shipment milestones by shipment Id. It creates shipment milestones if there are no shipment milestones.
	 * @param id shipment Id 
     * @param organizationCode Organization code
	 * @return List<ShipmentMilestoneResponse>  List of shipment milestones for searched shipments
	 * @throws Exception
	 *             Handle the exceptions
	 */
	@RequestMapping(value = "/bv/shipmentmilestones/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get shipment milestones by shipment ID", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
			@ApiResponse(code = 500, message = "The GET call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getShipmentMilestonesbyShipmentId(@ApiParam(name = "id", value = "Enter shipment id", required = true) @PathVariable("id") Long id) throws Exception {
		logger.info("Entering into ShipmentController.getShipmentMilestonesbyShipmentId");
		List<ShipmentMilestoneResponse> shipmentMilestones = shipmentMilestoneService.getShipmentMilestones(id);
		return new ResponseEntity<>(shipmentMilestones, HttpStatus.OK);
	}

	/**
	 * This Controller method is used to update shipment milestones actuals and shipment status using le_shipmentevents data
     * @param refType
     *            This is used to pass a reference type value - SHIPMENTREFNUM,WAYBILLNUMBER,BILLOFLADING, RAILBILLINGNUMBER, HOUSEAIRWAYBILLNUM, 
     *  MASTERAIRWAYBILLNUMBER, WORKORDERNUM, BOOKINGNUM 
     * @param refValue
     *            This is used to pass a reference value for the specified refType 
	 * @return List<ShipmentMilestoneResponse>  List of shipment milestones for searched shipment
	 * @throws Exception
	 *             Handle the exceptions
	 */
	@RequestMapping(value = "/bv/shipmentmilestones/events", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "check and update shipment milestones and status by reference", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> updateShipmentMilestoneAndStatusByRef(@ApiParam(name = "refType", value = "Enter shipment reference type such as WORKORDERNUM,BOOKINGNUM,"
            + "SHIPMENTREFNUM,BILLOFLADING, RAILBILLINGNUMBER etc.", required = true) @RequestParam("refType") String refType, 
            @ApiParam(name = "refValue", value = "Enter searched reference value", required = true) @RequestParam("refValue") String refValue)
			throws Exception {
		logger.info("Entering into ShipmentLegController.updateShipmentMilestoneStatusByRef");
		List<ShipmentMilestoneResponse>  shipmentMilestones = shipmentMilestoneService.updateShipmentMilestoneAndStatusByRef(refType, refValue);
		return new ResponseEntity<>(shipmentMilestones, HttpStatus.OK); 
	}

	
	/**
	 * This Controller method is used to get shipment milestones by reference value
	 * @param refType
	 *            This is used to pass a reference type value - SHIPMENTREFNUM,WAYBILLNUMBER,BILLOFLADING, RAILBILLINGNUMBER, HOUSEAIRWAYBILLNUM, 
     *  MASTERAIRWAYBILLNUMBER, WORKORDERNUM, BOOKINGNUM 
     * @param refValue
     *            This is used to pass a reference value for the specified refType 
	 * @return   ShipmentMilestonesResponse  response for rule engine with list of ShipmentMilestoneForRuleEngine
	 * @throws Exception
	 *             Handle the exceptions
	 */
	@RequestMapping(value = "/bv/shipmentmilestones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get shipment milestones by reference type and value.", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
			@ApiResponse(code = 500, message = "The GET call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getShipmentMilestonesByRef(@ApiParam(name = "refType", value = "Enter shipment reference type such as WORKORDERNUM,BOOKINGNUM,"
            + "SHIPMENTREFNUM,BILLOFLADING, RAILBILLINGNUMBER etc.", required = true) @RequestParam("refType") String refType, 
            @ApiParam(name = "refValue", value = "Enter searched reference value", required = true) @RequestParam("refValue") String refValue)
			throws Exception {
		logger.info("Entering into ShipmentLegController.getShipmentMilestonesByRef");
		ShipmentMilestonesResponse shipmentMilestones = shipmentMilestoneService.getShipmentMilestonesByRef(refType, refValue);
		return new ResponseEntity<>(shipmentMilestones, HttpStatus.OK); 
	}


}
