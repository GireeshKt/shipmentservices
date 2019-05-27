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

import com.blumeglobal.shipmentmanagement.model.ShipmentStop;
import com.blumeglobal.shipmentmanagement.model.ShipmentWO;
import com.blumeglobal.shipmentmanagement.request.ShipmentWorkOrderRequest;
import com.blumeglobal.shipmentmanagement.request.RoutingRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentScheduleRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentStopRequest;
import com.blumeglobal.shipmentmanagement.response.E2EShipmentDetailsResponse;
import com.blumeglobal.shipmentmanagement.response.RoutingResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentLegDetailsResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentMilestoneResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentWOResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentScheduleResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentStopResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentSummaryResponse;
import com.blumeglobal.shipmentmanagement.service.IShipmentMilestoneService;
import com.blumeglobal.shipmentmanagement.service.IShipmentService;
import com.blumeglobal.shipmentmanagement.service.IShipmentScheduleService;
import com.blumeglobal.shipmentmanagement.service.IShipmentStopService;
import com.blumeglobal.shipmentmanagement.service.IShipmentViewService;
import com.blumeglobal.shipmentmanagement.service.IRoutingService;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The ScheduleControllerImpl is used to manage the shipment details.
 *
 */
@RestController
@RequestMapping("/v1")
@Api(value = "Shipment Service")
@REZ1Logger
@REZ1PerformanceLogger
public class ScheduleController {
	private static final Logger logger = LogManager.getLogger(ScheduleController.class);
	
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
	IRoutingService routingService;
	// Schedule controller is not used as a part of shipment service production release.
	
//	@RequestMapping(value = "/bv/schedules", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)	  
//	  @ApiOperation(value = "Create Schedule ", produces = "application/json", consumes = "application/json")	  
//	  @ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
//		  @ApiResponse(code = 500, message = "The POST call is Failed"),
//		  @ApiResponse(code = 404, message = "The API could not be found"),
//		  @ApiResponse(code = 400, message = "Invalid input") }) 
//	  public ResponseEntity<?> createSchedule(@RequestBody ShipmentScheduleRequest createScheduleRequest) throws Exception {
//		  logger.info("Entering into ScheduleController.createSchedule");
//		  ShipmentScheduleResponse upload = shipmentScheduleService.createShipmentSchedule(createScheduleRequest);
//		  logger.debug("Inside ScheduleController.createSchedule - Insert Schedule");
//		  return new ResponseEntity<>(upload, HttpStatus.OK);
//	  }
	  
//	  @RequestMapping(value = "/bv/schedules/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//		@ApiOperation(value = "Update Shipment Schedule by shipmentId", produces = "application/json", consumes = "application/json")
//		@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
//				@ApiResponse(code = 500, message = "The GET call is Failed"),
//				@ApiResponse(code = 404, message = "The API could not be found"),
//				@ApiResponse(code = 400, message = "Invalid input") })
//		public ResponseEntity<?> updateShipmentSchedule(@RequestBody ShipmentScheduleRequest updateRequest, @ApiParam(name = "id", value = "Enter shipment Schedule Id", required = true) @PathVariable("id") Long id) throws Exception {
//		  logger.info("Entering into ScheduleController.updateShipmentSchedule");
//		  	ShipmentScheduleResponse upload = shipmentScheduleService.updateSchedule(updateRequest, id);
//		  	logger.debug("Inside ScheduleController.updateShipmentSchedule - Insert Schedule");
//			return new ResponseEntity<>(upload, HttpStatus.OK);
//		}
	  
//	  @RequestMapping(value = "/bv/schedules/carrier", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	  @ApiOperation(value = "View Carrier Schedules ", produces = "application/json", consumes = "application/json")
//	  @ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
//				@ApiResponse(code = 500, message = "The GET call is Failed"),
//				@ApiResponse(code = 404, message = "The API could not be found"),
//				@ApiResponse(code = 400, message = "Invalid input") })
//	  public ResponseEntity<?> getSchedulesByCarrier (@ApiParam(name = "carrier", value = "Enter carrier name", required = true) @RequestParam("carrier") String carrier){
//		  logger.info("Entering into ScheduleController.getSchedulesbyCarrier");
//		  List<ShipmentScheduleResponse> scheduleList = shipmentScheduleService.getSchedulebyCarrier(carrier);
//		  return new ResponseEntity<>(scheduleList, HttpStatus.OK);
//	  }
	  
//	  @RequestMapping(value = "/bv/schedules/vessel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	  @ApiOperation(value = "View Vessel Schedules", produces = "application/json", consumes = "application/json")
//	  @ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
//				@ApiResponse(code = 500, message = "The GET call is Failed"),
//				@ApiResponse(code = 404, message = "The API could not be found"),
//				@ApiResponse(code = 400, message = "Invalid input") })
//	 public ResponseEntity<?> getScheduleByVessel (@ApiParam(name = "vessel", value = "Enter vessel name", required = true) @RequestParam("vessel") String vessel){
//		  logger.info("Entering into ScheduleController.getSchedulebyVessel");
//		  List<ShipmentScheduleResponse> scheduleList = shipmentScheduleService.getSchedulebyVessel(vessel);
//		  return new ResponseEntity<>(scheduleList, HttpStatus.OK);
//	  }
	  
//	  @RequestMapping(value = "/bv/schedules/lane", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	  @ApiOperation(value = "View Shipping Schedules by Shipping Lane", produces = "application/json", consumes = "application/json")
//	  @ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
//				@ApiResponse(code = 500, message = "The GET call is Failed"),
//				@ApiResponse(code = 404, message = "The API could not be found"),
//				@ApiResponse(code = 400, message = "Invalid input") })
//	 public ResponseEntity<?> getScheduleByLane (@ApiParam(name = "lane", value = "Enter lane code", required = true) @RequestParam("lane") String lane){
//		  logger.info("Entering into ScheduleController.getSchedulebyLane");
//		  List<ShipmentScheduleResponse> laneList = shipmentScheduleService.getSchedulebyLane(lane);
//		  return new ResponseEntity<>(laneList, HttpStatus.OK);
//	  }
	  
//	  @RequestMapping(value = "/bv/schedules/originDestination", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	  @ApiOperation(value = "View Shipping Schedules by Origin and Destination", produces = "application/json", consumes = "application/json")
//	  @ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
//				@ApiResponse(code = 500, message = "The GET call is Failed"),
//				@ApiResponse(code = 404, message = "The API could not be found"),
//				@ApiResponse(code = 400, message = "Invalid input") })
//	  public ResponseEntity<?> getSchedulesByOriginandDestination (@ApiParam(name = "origin", value = "Enter origin", required = true) @RequestParam("origin") String origin, @ApiParam(name = "destination", value = "Enter destination name", required = true) @RequestParam("destination") String destination){
//		  logger.info("Entering into ScheduleController.getSchedulesbyOriginandDestination");
//		  List<ShipmentScheduleResponse> scheduleList = shipmentScheduleService.getSchedulebyOriginAndDestination(origin, destination);
//		  return new ResponseEntity<>(scheduleList, HttpStatus.OK);
//	  }
	  
//	  @RequestMapping(value = "/bv/schedules/origin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	  @ApiOperation(value = "View Shipping Schedules by Origin", produces = "application/json", consumes = "application/json")
//	  @ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
//				@ApiResponse(code = 500, message = "The GET call is Failed"),
//				@ApiResponse(code = 404, message = "The API could not be found"),
//				@ApiResponse(code = 400, message = "Invalid input") })
//	  public ResponseEntity<?> getSchedulesByOrigin (@ApiParam(name = "origin", value = "Enter origin", required = true) @RequestParam("origin") String origin){
//		  logger.info("Entering into ScheduleController.getSchedulesbyOriginandDestination");
//		  List<ShipmentScheduleResponse> scheduleList = shipmentScheduleService.getSchedulebyOrigin(origin);
//		  return new ResponseEntity<>(scheduleList, HttpStatus.OK);
//	  }
	  
//	  @RequestMapping(value = "/bv/schedules/destination", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	  @ApiOperation(value = "View Shipping Schedules by Destination", produces = "application/json", consumes = "application/json")
//	  @ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
//				@ApiResponse(code = 500, message = "The GET call is Failed"),
//				@ApiResponse(code = 404, message = "The API could not be found"),
//				@ApiResponse(code = 400, message = "Invalid input") })
//	  public ResponseEntity<?> getSchedulesByDestination (@ApiParam(name = "destination", value = "Enter destination name", required = true) @RequestParam("destination") String destination){
//		  logger.info("Entering into ScheduleController.getSchedulesbyDestination");
//		  List<ShipmentScheduleResponse> scheduleList = shipmentScheduleService.getSchedulebyDestination(destination);
//		  return new ResponseEntity<>(scheduleList, HttpStatus.OK);
//	  }
	  
//	  @RequestMapping(value = "/bv/stops", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)	  
//	  @ApiOperation(value = "Create or Update Stop ", produces = "application/json", consumes = "application/json")	  
//	  @ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
//				@ApiResponse(code = 500, message = "The POST call is Failed"),
//				@ApiResponse(code = 404, message = "The API could not be found"),
//				@ApiResponse(code = 400, message = "Invalid input") })
//	  public ResponseEntity<?> createStop(@RequestBody ShipmentStopRequest createStopRequest) throws Exception {
//		  logger.info("Entering into ScheduleController.createSchedule");
//		  ShipmentStopResponse upload = shipmentStopService.createShipmentStop(createStopRequest);
//		  logger.debug("Inside ScheduleController.createStop - Insert Stop");
//		  return new ResponseEntity<>(upload, HttpStatus.OK);
//	  }
	  
//		@RequestMapping(value = "/bv/stops/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//		@ApiOperation(value = "Update Schedule Stop by Stop Id", produces = "application/json", consumes = "application/json")
//		@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
//				@ApiResponse(code = 500, message = "The GET call is Failed"),
//				@ApiResponse(code = 404, message = "The API could not be found"),
//				@ApiResponse(code = 400, message = "Invalid input") })
//		public ResponseEntity<?> updateShipmentStop(@RequestBody ShipmentStopRequest updateRequest, @ApiParam(name = "id", value = "Enter Schedule Stop Id", required = true) @PathVariable("id") Long id) throws Exception {
//			logger.info("Entering into ScheduleController.updateShipmentStop");
//			ShipmentStopResponse upload = shipmentStopService.updateStop(updateRequest, id);
//			logger.debug("Inside ScheduleController.updateShipmentStop - Update Stop");
//			return new ResponseEntity<>(upload, HttpStatus.OK);
//		}
	  
//	  @RequestMapping(value = "/bv/stops/id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	  @ApiOperation(value = "get stops by shipment ID", produces = "application/json", consumes = "application/json")
//	  @ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
//				@ApiResponse(code = 500, message = "The GET call is Failed"),
//				@ApiResponse(code = 404, message = "The API could not be found"),
//				@ApiResponse(code = 400, message = "Invalid input") })
//	 public ResponseEntity<?> getStopsByShipment (@ApiParam(name = "id", value = "Enter shipment stop id", required = true) @RequestParam("id") String id){
//		  logger.info("Entering into ScheduleController.getStopsByShipment");
//		  List<ShipmentStopResponse> stopsList = shipmentStopService.getShipmentList(id);
//		  return new ResponseEntity<>(stopsList, HttpStatus.OK);
//	  }
	  
//	  @RequestMapping(value = "/bv/routing", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	  @ApiOperation(value = "Create Routing", produces = "application/json", consumes = "application/json")
//	  @ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
//				@ApiResponse(code = 500, message = "The POST call is Failed"),
//				@ApiResponse(code = 404, message = "The API could not be found"),
//				@ApiResponse(code = 400, message = "Invalid input") })
//	  public ResponseEntity<?> createRouting (@RequestBody RoutingRequest RouteRequest) throws Exception {
//		  logger.info("Entering into ScheduleController.createRouting");
//		  RoutingResponse upload = routingService.createRouting(RouteRequest);
//		  logger.debug("Inside ScheduleController.createSchedule - Insert Schedule");
//		  return new ResponseEntity<>(upload, HttpStatus.OK);
//	  }
	  
}