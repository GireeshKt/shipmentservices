package com.blumeglobal.shipmentmanagement.service;

import java.util.List;

import com.blumeglobal.shipmentmanagement.request.MilestoneNewRequest;
import com.blumeglobal.shipmentmanagement.request.MilestoneRequest;
import com.blumeglobal.shipmentmanagement.response.MilestoneAPICreateUpdateResponse;
import com.blumeglobal.shipmentmanagement.response.MilestoneAPIDeleteResponse;
import com.blumeglobal.shipmentmanagement.response.MilestoneGetResponse;
import com.blumeglobal.shipmentmanagement.response.MilestoneResponse;

public interface IMilestoneService {
	MilestoneResponse createMilestone(MilestoneRequest milestoneRequest);
		
	public List<MilestoneResponse> getAllMilestones() throws Exception;

	public MilestoneAPIDeleteResponse deleteMilestoneById(Long milestoneId);

	public MilestoneResponse updateMilestone(MilestoneRequest milestoneRequest, Long milestoneId);

	public MilestoneGetResponse getMilestoneById(Long milestoneId);
	
}