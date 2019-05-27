package com.blumeglobal.shipmentmanagement.service;

import java.util.List;

import com.blumeglobal.shipmentmanagement.request.RoutingRequest;
import com.blumeglobal.shipmentmanagement.response.RoutingAPICreateUpdateResponse;
import com.blumeglobal.shipmentmanagement.response.RoutingAPIDeleteResponse;
import com.blumeglobal.shipmentmanagement.response.RoutingGetResponse;
import com.blumeglobal.shipmentmanagement.response.RoutingResponse;

public interface IRoutingService {
	public RoutingResponse createRouting(RoutingRequest RouteRequest);
	
	public RoutingAPICreateUpdateResponse createRoutingNew(RoutingRequest routingRequest) throws Exception;

	public List<RoutingResponse> getAllRoutings();
	
	public RoutingAPIDeleteResponse deleteRoutingById(String routingId);
	
	public RoutingAPICreateUpdateResponse updateRouting(RoutingRequest routingRequest, String routingId);

	public RoutingGetResponse getRoutingById(String routingId);
}
