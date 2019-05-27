package com.blumeglobal.shipmentmanagement.service;

import java.util.List;

import com.blumeglobal.shipmentmanagement.model.Lane;
import com.blumeglobal.shipmentmanagement.model.LaneGroups;
import com.blumeglobal.shipmentmanagement.request.LaneGroupsRequest;
import com.blumeglobal.shipmentmanagement.request.LaneLocationRequest;
import com.blumeglobal.shipmentmanagement.request.LaneRequest;
import com.blumeglobal.shipmentmanagement.response.LaneAPICreateUpdateResponse;
import com.blumeglobal.shipmentmanagement.response.LaneAPIDeleteResponse;
import com.blumeglobal.shipmentmanagement.response.LaneGroupAPIDeleteResponse;
import com.blumeglobal.shipmentmanagement.response.LaneGroupsResponse;
import com.blumeglobal.shipmentmanagement.response.LaneLocationResponse;
import com.blumeglobal.shipmentmanagement.response.LaneResponse;

public interface ILaneService {
	
	/**
	 * Get Lane API
	 * 
	 * @param laneId
	 *            This is laneId
	 * @return LaneLocationRequest Lane Response after fetch record
	 * @throws Exception
	 *             Handle service exceptions
	 */
	public LaneLocationRequest getLaneById(String laneId)throws Exception;

	public LaneLocationResponse createLane(LaneRequest lane);

	public LaneGroupsResponse createLaneGroup(LaneGroupsRequest laneGroup);

	public List<String> getAllLaneIds();
	
	public LaneAPIDeleteResponse deleteLaneById(String laneId);

	public LaneAPICreateUpdateResponse updateLane(LaneRequest routingRequest, String routingId);
		
	/**@Description : This method will get all the available lane records
	 * @return
	 * @throws Exception
	 */
	public List<LaneResponse> getAllLanes() throws Exception;
	
	public List<LaneGroupsResponse> getAllLaneGroups() throws Exception;
	
    public LaneGroupsResponse updateLaneGroup(LaneGroupsRequest laneGroup, String laneId);
	
	public LaneGroupAPIDeleteResponse deleteLaneGroupById(String laneGroupId);
	
	public List<String> getAllLaneGroupIds();
	
}
