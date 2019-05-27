package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;

/**
 * The KafkaShipmentEventResponse is used to handle the response of Shipment Event
 * Operation
 *
 */
public class KafkaShipmentEventResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//kafka service
	private String message;

	public KafkaShipmentEventResponse() {

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
