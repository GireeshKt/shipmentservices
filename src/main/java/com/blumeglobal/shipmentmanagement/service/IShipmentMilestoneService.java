package com.blumeglobal.shipmentmanagement.service;

import java.util.Date;
import java.util.List;

import com.blumeglobal.shipmentmanagement.dm.ShipmentEvent;
import com.blumeglobal.shipmentmanagement.model.E2EShipment;
import com.blumeglobal.shipmentmanagement.model.Shipment;
import com.blumeglobal.shipmentmanagement.model.ShipmentMilestone;
import com.blumeglobal.shipmentmanagement.model.Stop;
import com.blumeglobal.shipmentmanagement.request.common.GenericShipmentRequest;
import com.blumeglobal.shipmentmanagement.response.GenericUpdateResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentMilestoneResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentMilestonesResponse;

public interface IShipmentMilestoneService {
	/**
	 * Get shipment milestones for shipment by shipmentId
	 * 
	 * @param shipmentId 
	 * @return List<ShipmentMilestoneResponse> shimentmilestones created
	 * @throws Exception
	 *             Handle service exceptions
	 */
	List<ShipmentMilestoneResponse> getShipmentMilestones(Long shipmentId)  throws Exception;

	List<ShipmentMilestone> createShipmentMilestones(Shipment shipment);

	void processShipmentEvents(List<Shipment> shipments, E2EShipment e2eShipment);

	List<ShipmentMilestoneResponse> updateShipmentMilestoneAndStatusByRef(String ref, String refValue);

	GenericUpdateResponse updateShipmentMilestoneAndStatus();

	GenericUpdateResponse updateAllShipmentStatusAndActuals();

	void processShipmentEvent(ShipmentEvent upload);

	void addPlannedStopsFromDM(Shipment shipment);

	ShipmentMilestonesResponse getShipmentMilestonesByRef(String refType, String refValue);

	void processUpdateAfterDeleteEvent(ShipmentEvent shipmentEvent);

    void updateShipmentMilestones(List<Stop> shipmentStops, Shipment shipment);
    public void updateShipmentMilestones(GenericShipmentRequest createShipmentRequest, Shipment existingShipment);
    List<ShipmentMilestone> reCreateShipmentMilestones(Shipment shipment);
}
