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

import com.blumeglobal.shipmentmanagement.request.ShipmentWorkOrderRequest;
import com.blumeglobal.shipmentmanagement.response.E2EShipmentDetailsResponse;
import com.blumeglobal.shipmentmanagement.response.E2EShipmentResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentFreightDetailResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentSummaryResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentWOResponse;
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
@Api(value = "Shipment Service")
@REZ1Logger
@REZ1PerformanceLogger
public class ShipmentController {
	private static final Logger logger = LogManager.getLogger(ShipmentController.class);
	
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
	 
	
	/**
	 * This Controller method is used to create shipment using API
	 * 
	 * @param createRequest
	 *            This is used to pass shipment request data
	 * @return ShipmentResponse upload To get response of ShipmentResponse
	 * @throws Exception
	 *             Handle the exceptions
	 */
	@RequestMapping(value = "/bv/shipments", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create or Update Shipment ", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
public ResponseEntity<?> createShipment(@RequestBody ShipmentWorkOrderRequest createShipmentFromWorkOrderRequest)throws Exception {	
		logger.info("Entering into ShipmentController.createShipment");
		ShipmentResponse upload = shipmentService.createShipmentWO(createShipmentFromWorkOrderRequest);
		logger.debug("Inside ShipmentController.createShipment - Insert Shipment");
		return new ResponseEntity<>(upload, HttpStatus.OK);
	}
	
	
	

	@RequestMapping(value = "/bv/shipments/e2eshipments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get E2E shipment list by organization code and view type", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?>  getE2EShipmentViewList(@ApiParam(name = "startRow", value = "Enter startRow", required = true) @RequestParam("startRow")int startRow,
			@ApiParam(name = "endRow", value = "Enter endRow", required = true) @RequestParam("endRow")int endRow,
			@ApiParam(name = "organizationCode", value = "Enter organization code", required = true) @RequestParam("organizationCode")String organizationCode, 
			@ApiParam(name = "viewType", value = "Enter shipment summary view type such as ACTIVE, EXCEPTION, COMPLETEDLASTDAY", required = true) @RequestParam("viewType")String viewType){
		List<E2EShipmentResponse> shipmentList = shipmentViewService.e2eshipmentList(startRow,endRow,organizationCode,viewType);
		return new ResponseEntity<>(shipmentList, HttpStatus.OK); 
	}

//	@RequestMapping(value = "/bv/shipments/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "get shipment by shipment ID", produces = "application/json", consumes = "application/json")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
//			@ApiResponse(code = 500, message = "The GET call is Failed"),
//			@ApiResponse(code = 404, message = "The API could not be found"),
//			@ApiResponse(code = 400, message = "Invalid input") })
//	public ResponseEntity<?> getShipmentbyId(@ApiParam(name = "id", value = "Enter shipment id", required = true) @PathVariable("id") String id) throws Exception {
//		logger.info("Entering into ShipmentController.getShipmentbyId");
//		ShipmentWOResponse shipmentResponse = shipmentViewService.findShipmentbyId(Long.parseLong(id));
//		return new ResponseEntity<>(shipmentResponse, HttpStatus.OK);
//	}

//	@RequestMapping(value = "/bv/shipments/e2eid/{e2eid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "get shipments by E2E shipment ID", produces = "application/json", consumes = "application/json")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
//			@ApiResponse(code = 500, message = "The GET call is Failed"),
//			@ApiResponse(code = 404, message = "The API could not be found"),
//			@ApiResponse(code = 400, message = "Invalid input") })
//	public ResponseEntity<?> getShipmentsbyE2EShipmentId(@ApiParam(name = "e2eid", value = "Enter E2E shipment id", required = true) @PathVariable("e2eid") Long e2eid) throws Exception {
//		logger.info("Entering into ShipmentController.getShipmentsbyE2EShipmentId");
//		List<ShipmentWOResponse> shipmentList = shipmentViewService.getShipmentbyE2EShipmentId(e2eid);
//		return new ResponseEntity<>(shipmentList, HttpStatus.OK);
//	}
	
	@RequestMapping(value = "/bv/shipmentsummary", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get shipment count summary", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?>  getShipmentViewSummary(
			@ApiParam(name = "organizationCode", value = "Enter organization code", required = true) @RequestParam("organizationCode")String organizationCode
			) throws Exception{
		ShipmentSummaryResponse summary = shipmentViewService.getShipmentViewSummary(organizationCode);
		return new ResponseEntity<>(summary, HttpStatus.OK); 
	}
	
	/**
	 * This Controller method is used to retrieve shipment detail data by a shipment reference
	 * @param id
	 *            This is used to pass a woBookingNumber or masterAirwayBillNumber or billOfLadingNumber or bookingNumber or railBillingNumber or houseAirwayBillNumber or shipment reference 
	 * @return E2EShipmentDetailsResponse To get the list of Shipment legs, events and shipment milestones
	 * @throws Exception
	 *             Handle the exceptions
	 */
	@RequestMapping(value = "/bv/shipments/e2eshipmentref", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get E2E Shipment legs, events and milestones details by reference", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
			@ApiResponse(code = 500, message = "The GET call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getE2EShipmentDetailsByRef(@ApiParam(name = "ref", value = "Enter shipment search value like workorderno,billOfLadingNumber,railBillingNumber,etc..", required = true) @RequestParam("ref") String ref, 
			@ApiParam(name = "organizationCode", value = "Enter organization code", required = true) @RequestParam("organizationCode")String organizationCode)
			throws Exception {
		logger.info("Entering into ShipmentLegController.getE2EShipmentDetailsByRef");
		E2EShipmentDetailsResponse e2eShipmentDetails = shipmentViewService.findE2EShipmentDetailsByRef(ref, organizationCode);
		return new ResponseEntity<>(e2eShipmentDetails, HttpStatus.OK); 
	}


	@RequestMapping(value = "/bv/shipments/e2eshipmentsearch", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Search E2E Shipment by reference type and value", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
			@ApiResponse(code = 500, message = "The GET call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> searchE2EShipmentByRefTypeValue(@ApiParam(name = "refType", value = "Enter shipment reference type such as WORKORDERNUM,BOOKINGNUM,"
			+ "SHIPMENTREFNUM,BILLOFLADING", required = true) @RequestParam("refType") String refType, 
			@ApiParam(name = "refValue", value = "Enter searched reference value", required = true) @RequestParam("refValue") String refValue,
			@ApiParam(name = "organizationCode", value = "Enter organization code", required = true) @RequestParam("organizationCode")String organizationCode)
			throws Exception {
		logger.info("Entering into ShipmentLegController.searchE2EShipmentByRefTypeValue");
		List<E2EShipmentResponse> e2eShipmentResponse = shipmentViewService.searchE2EShipmentByRefTypeValue(refType, refValue, organizationCode);
		return new ResponseEntity<>(e2eShipmentResponse, HttpStatus.OK); 
		
	}

	@RequestMapping(value = "/bv/shipmentFreightList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get E2E shipment freight details list by organization code", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?>  getShipmentFreightDetailsList(@ApiParam(name = "startRow", value = "Enter startRow", required = true) @RequestParam("startRow")int startRow,
			@ApiParam(name = "endRow", value = "Enter endRow", required = true) @RequestParam("endRow")int endRow,
			@ApiParam(name = "organizationCode", value = "Enter organization code", required = true) @RequestParam("organizationCode")String organizationCode){
		List<ShipmentFreightDetailResponse> shipmentFreightList = shipmentViewService.shipmentFreightList(startRow,endRow,organizationCode);
		return new ResponseEntity<>(shipmentFreightList, HttpStatus.OK); 
	}

	@RequestMapping(value = "/bv/shipmentFreightSearch", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Search E2E Shipment freight details by data type and value", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
			@ApiResponse(code = 500, message = "The GET call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") }) 
	public ResponseEntity<?> searchShipmentFreightDetailsByDataTypeValue(@ApiParam(name = "dataType", value = "Enter search data type - PO, ASN, SKU,"
			+ "SHIPMENTREFNUM,UNITID", required = true) @RequestParam("dataType") String dataType, 
			@ApiParam(name = "searchValue", value = "Enter searched data value", required = true) @RequestParam("searchValue") String searchValue,
			@ApiParam(name = "organizationCode", value = "Enter organization code", required = true) @RequestParam("organizationCode")String organizationCode)
			throws Exception {
		logger.info("Entering into ShipmentLegController.searchShipmentFreightDetailsByDataTypeValue");
		List<ShipmentFreightDetailResponse> shipmentFreightList = shipmentViewService.searchShipmentFreightDetailsByRefTypeValue(dataType, searchValue, organizationCode);
		return new ResponseEntity<>(shipmentFreightList, HttpStatus.OK); 
		
	}

}
