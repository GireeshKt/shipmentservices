package com.blumeglobal.shipmentmanagement.request.common;

import java.io.Serializable;

public class ShipmentNote implements Serializable{

    private static final long serialVersionUID = 1L;
    private String shipmentNote;

    public String getShipmentNote() {
        return shipmentNote;
    }

    public void setShipmentNote(String shipmentNote) {
        this.shipmentNote = shipmentNote;
    }
    
}
