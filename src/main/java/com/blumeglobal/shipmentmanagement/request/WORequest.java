package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;

public class WORequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private WorkOrderRequest workOrder;

	
	public WORequest() {
		
	}

	public WorkOrderRequest getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrderRequest workOrder) {
		this.workOrder = workOrder;
	}

	@Override
	public String toString() {
		return "WORequest [workOrder=" + workOrder + "]";
	}

}
