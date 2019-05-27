package com.blumeglobal.shipmentmanagement.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blumeglobal.shipmentmanagement.dao.repositories.MilestonesRepository;
import com.blumeglobal.shipmentmanagement.model.Milestone;
import com.blumeglobal.shipmentmanagement.model.mapper.MilestoneMapper;
import com.blumeglobal.shipmentmanagement.request.LaneGroupsRequest;
import com.blumeglobal.shipmentmanagement.request.MilestoneNewRequest;
import com.blumeglobal.shipmentmanagement.request.MilestoneRequest;
import com.blumeglobal.shipmentmanagement.response.MilestoneAPICreateUpdateResponse;
import com.blumeglobal.shipmentmanagement.response.MilestoneAPIDeleteResponse;
import com.blumeglobal.shipmentmanagement.response.MilestoneGetResponse;
import com.blumeglobal.shipmentmanagement.response.MilestoneResponse;
import com.blumeglobal.shipmentmanagement.service.IMilestoneService;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;


/**
 * The ShipmentScheduleServiceImpl implements to manage the shipment schedules.
 *
 */
@Service
@REZ1Logger
@REZ1PerformanceLogger
public class MilestoneServiceImpl implements IMilestoneService{

	@Autowired
	MilestoneMapper milestoneMapper;
	
	@Autowired
	MilestonesRepository milestoneRepository;
	
	private static final Logger logger = LogManager.getLogger(MilestoneServiceImpl.class);
	
	@Override
	public MilestoneResponse createMilestone(MilestoneRequest milestoneRequest) {
		try {
			Milestone upload = milestoneMapper.map(milestoneRequest, Milestone.class);
			milestoneRepository.save(upload);
			return milestoneMapper.map(upload, MilestoneResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}	
	
	/**
	 * Retrieve list of Milestone data
	 * @return MilestoneResponse List of Milestone Response
	 * @throws Exception
	 *             Handle service Impl exceptions
	 */
	@Override
	public List<MilestoneResponse> getAllMilestones() throws Exception {
		logger.info("Entering into MilestoneServiceImpl.getMilestone");
		List<MilestoneResponse> milestoneViewResponses = new ArrayList<>(); 
		try {
			List<Milestone> milestones =  milestoneRepository.findAllMilestones();
			if (milestones.isEmpty()) {
				logger.debug("Entering into MilestoneServiceImpl.No Milestone available");
				
				return milestoneViewResponses;
			} else { 
				logger.debug("MilestoneServiceImpl.getMilestones - Mapping Milestone data with Milestone"); 
				milestoneViewResponses = milestoneMapper.mapAsList(milestones, MilestoneResponse.class); 
				return milestoneViewResponses; 
			} 
		} catch (Exception ex) {
			logger.error("Exception inside MilestonesServiceImpl.getMilestones:" +ex.getMessage());
			throw ex; 
		} 
	}

	@Override
	public MilestoneAPIDeleteResponse deleteMilestoneById(Long milestoneId) {
		MilestoneAPIDeleteResponse response = new MilestoneAPIDeleteResponse();
		if (milestoneId != null && milestoneId >= 0 ) {
			milestoneRepository.deleteById(milestoneId);
			response.setStatus("Success");
			response.setMessage("Deleted row of ID : " +milestoneId);
			return response;
		} else {
			response.setStatus("failure");
			response.setMessage("Validation Failed...milestoneId cannot be null..");
			logger.error("Validation Failed To Update the Milestone");
			return response;
		}
	}

	@Override
	public MilestoneResponse updateMilestone(MilestoneRequest milestoneRequest,Long milestoneId) {
		try {
			Milestone upload = milestoneMapper.map(milestoneRequest, Milestone.class);
			milestoneRepository.save(upload);
			return milestoneMapper.map(upload, MilestoneResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public MilestoneGetResponse getMilestoneById(Long milestoneId) {
		MilestoneGetResponse res = new MilestoneGetResponse();
		MilestoneResponse resp = new MilestoneResponse();
		if(milestoneId != null && milestoneId >=0 ) {
			Milestone r = milestoneRepository.findByMileStoneId(milestoneId);
			resp = milestoneMapper.map(r, MilestoneResponse.class);

			res.setStatus("Success");
			res.setMessage("Successfully retrieved data for milestoneId.."+milestoneId);
			res.setSuccessfullData(resp);
			logger.error("Validation Failed To get the Milestone");
			return res;
		}else {
			res.setStatus("failure");
			res.setMessage("Validation Failed...milestoneId cannot be null..");
			logger.error("Validation Failed To get the Milestone");
			return res;
		}
	}		
}