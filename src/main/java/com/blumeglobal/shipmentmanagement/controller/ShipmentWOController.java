package com.blumeglobal.shipmentmanagement.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blumeglobal.shipmentmanagement.model.ShipmentDetails;
import com.blumeglobal.shipmentmanagement.model.ShipmentWO;
import com.blumeglobal.shipmentmanagement.response.ShipmentWOResponse;
import com.blumeglobal.shipmentmanagement.service.IShipmentWOService;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1")
@Api(value = "Shipment Details Service")
@REZ1Logger
@REZ1PerformanceLogger
public class ShipmentWOController {
	
	private static final Logger logger = LogManager.getLogger(ShipmentWOController.class);
	@Autowired
	private IShipmentWOService shipmentWOService;
	
//	@RequestMapping(value = "/shipments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?>  shipmentWOList(@ApiParam(name = "startRow", value = "Enter startRow", required = true) @RequestParam("startRow")int startRow,
//			@ApiParam(name = "endRow", value = "Enter endRow", required = true) @RequestParam("endRow")int endRow,
//			@ApiParam(name = "officeId", value = "Enter OfficeId", required = true) @RequestParam("officeId")String officeId, 
//			@ApiParam(name = "officeType", value = "Enter officeType Originator / Receiver", required = true) @RequestParam("officeType")String officeType){
//		List<ShipmentWO> shipmentList = shipmentWOService.shipmentList(startRow,endRow,officeId,officeType);
//		return new ResponseEntity<>(shipmentList, HttpStatus.OK); 
//	}
	
	
//	@RequestMapping(value = "/shipments/searchByDate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?>  searchByDate(@ApiParam(name = "fromDate", value = "Enter fromDate (dd-MMM-yyyy)", required = true) @RequestParam("fromDate")String fromDate,
//			@ApiParam(name = "toDate", value = "Enter toDate (dd-MMM-yyyy)", required = true) @RequestParam("toDate")String toDate,
//			@ApiParam(name = "officeId", value = "Enter OfficeId", required = true) @RequestParam("officeId")Long officeId, 
//			@ApiParam(name = "officeType", value = "Enter officeType Originator / Receiver", required = true) @RequestParam("officeType")String officeType) throws Exception{
//		List<ShipmentWO> shipmentList = shipmentWOService.getShipmentByDateRange(fromDate,toDate,officeId,officeType);
//		if(shipmentList!=null) {
//			return new ResponseEntity<>(shipmentList, HttpStatus.OK);
//		}else {
//			return new ResponseEntity<>("Something weent wrong", HttpStatus.INTERNAL_SERVER_ERROR); 
//		}
//	}
	
	
//	@RequestMapping(value = "/shipment/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "Get Shipment Details list", produces = "application/json", consumes = "application/json")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
//			@ApiResponse(code = 500, message = "The GET call is Failed"),
//			@ApiResponse(code = 404, message = "The API could not be found"),
//			@ApiResponse(code = 400, message = "Invalid input") })
//	public ResponseEntity<?> searchShipmentLegs(@ApiParam(name = "query", value = "Enter criteria", required = true)  @RequestParam("query") String criteriaSpecList)
//			throws Exception {
//		logger.info("Entering into ShipmentWOController.searchShipment");
//		List<ShipmentWOResponse> shipments = shipmentWOService.searchShipment(criteriaSpecList);		
//		if(shipments!=null) {
//			return new ResponseEntity<>(shipments, HttpStatus.OK);
//		}else {
//			return new ResponseEntity<>("No data found for the given search", HttpStatus.NO_CONTENT); 
//		}
//	}
	
	// pointing to table
//	@RequestMapping(value = "/shipmentlist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?>  shipmentList(@ApiParam(name = "startRow", value = "Enter startRow", required = true) @RequestParam("startRow")int startRow,
//			@ApiParam(name = "endRow", value = "Enter endRow", required = true) @RequestParam("endRow")int endRow,
//			@ApiParam(name = "officeId", value = "Enter OfficeId", required = true) @RequestParam("officeId")String officeId, 
//			@ApiParam(name = "officeType", value = "Enter officeType Originator / Receiver", required = true) @RequestParam("officeType")String officeType)
//			{
//		List<ShipmentDetails> shipmentList = shipmentWOService.shipmentDetailsList(startRow, endRow, officeId, officeType);
//		return new ResponseEntity<>(shipmentList, HttpStatus.OK); 
//	}
}
