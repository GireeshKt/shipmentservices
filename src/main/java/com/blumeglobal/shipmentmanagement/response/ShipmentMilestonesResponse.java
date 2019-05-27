package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
import java.util.List;

public class ShipmentMilestonesResponse implements Serializable {

	private static final long serialVersionUID = 1L;
    private Long e2eshipmentId;
    private String status;
	private List<ShipmentMilestoneForRuleEngine> shipmentMilestoneLocations;
   
    public List<ShipmentMilestoneForRuleEngine> getShipmentMilestoneLocations() {
        return shipmentMilestoneLocations;
    }
    public void setShipmentMilestoneLocations(List<ShipmentMilestoneForRuleEngine> shipmentMilestoneLocations) {
        this.shipmentMilestoneLocations = shipmentMilestoneLocations;
    }
    public Long getE2eshipmentId() {
        return e2eshipmentId;
    }
    public void setE2eshipmentId(Long e2eshipmentId) {
        this.e2eshipmentId = e2eshipmentId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
}
