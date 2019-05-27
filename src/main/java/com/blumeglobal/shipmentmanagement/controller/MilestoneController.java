package com.blumeglobal.shipmentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blumeglobal.shipmentmanagement.request.MilestoneNewRequest;
import com.blumeglobal.shipmentmanagement.request.MilestoneRequest;
import com.blumeglobal.shipmentmanagement.response.MilestoneAPICreateUpdateResponse;
import com.blumeglobal.shipmentmanagement.response.MilestoneAPIDeleteResponse;
import com.blumeglobal.shipmentmanagement.response.MilestoneGetResponse;
import com.blumeglobal.shipmentmanagement.response.MilestoneResponse;
import com.blumeglobal.shipmentmanagement.service.IMilestoneService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1")
@Api(value = "Milestone Service")
public class MilestoneController {
	
	@Autowired
	private IMilestoneService milestoneService;
		
	/**
	 * This Controller method is used to retrieve list of milestone 
	 * @throws Exception Handle the exceptions
	 */

	@RequestMapping(value = "/getAllMilestone", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get list of Milestone", produces = "application/json", consumes = "application/json",tags = {"Milestone Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
			@ApiResponse(code = 500, message = "The GET call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getAllMilestones() throws Exception {

		List<MilestoneResponse> milestones = milestoneService.getAllMilestones();
		return new ResponseEntity<>(milestones, HttpStatus.OK);
	}


	/**
	 * This Controller method is used to Delete milestone Based on ID
	 * @throws Exception Handle the exceptions
	 */

	@RequestMapping(value = "/deleteMilestone/{milestoneId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete Milestone By Id through API", produces = "application/json", consumes = "application/json",tags = {"Milestone Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The DELETE call is Successful"),
			@ApiResponse(code = 500, message = "The DELETE call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> deleteMilestoneById(
			@ApiParam(name = "milestoneId", value = "Enter Milestone ID", required = true)  @PathVariable("milestoneId") Long milestoneId
			) throws Exception {

		MilestoneAPIDeleteResponse dlt= milestoneService.deleteMilestoneById(milestoneId);
		return new ResponseEntity<MilestoneAPIDeleteResponse>(dlt,HttpStatus.OK);
	}

	/**
	 * This Controller method is used to update milestone from API
	 * @throws Exception Handle the exceptions
	 */
	@RequestMapping(value = "/updateMilestone", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update Milestone through API", produces = "application/json", consumes = "application/json",tags = {"Milestone Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The PUT call is Successful"),
			@ApiResponse(code = 500, message = "The PUT call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> updateMilestone(@RequestBody MilestoneRequest milestoneRequest
			) throws Exception {

		MilestoneResponse poRes = milestoneService.updateMilestone(milestoneRequest, milestoneRequest.getMilestoneId());
		return new ResponseEntity<>(poRes,HttpStatus.OK);
	}

	/**
	 * This Controller method is used to retrieve list of milestone based on ID
	 * @throws Exception Handle the exceptions
	 */

	@RequestMapping(value = "/getMilestoneByID/{milestoneId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Milestone By Id", produces = "application/json", consumes = "application/json",tags = {"Milestone Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
			@ApiResponse(code = 500, message = "The GET call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input")})
	public ResponseEntity<?> getMilestoneById(
			@ApiParam(name = "milestoneId", value = "Enter Milestone ID", required = true) @PathVariable("milestoneId") Long milestoneId)throws Exception {

		MilestoneGetResponse milestone = milestoneService.getMilestoneById(milestoneId);
		return new ResponseEntity<>(milestone, HttpStatus.OK);
	}
}
