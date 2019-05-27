package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.blumeglobal.shipmentmanagement.enums.ShipmentReferenceType;


public class ShipmentReferenceRequest implements Serializable, Comparable<Object> {
    private static final long serialVersionUID = 1L;

    @Size(max = 50, message = "shipmentRefType must be maximum of 50 characters")
    private String shipmentRefType;

    @Size(max = 100, message = "shipmentRefValue must be maximum of 100 characters")
    private String shipmentRefValue;

    public ShipmentReferenceRequest() {

    }

    public String getShipmentRefType() {
        return shipmentRefType;
    }

    public void setShipmentRefType(String shipmentRefType) {
        this.shipmentRefType = shipmentRefType;
    }

    public String getShipmentRefValue() {
        return shipmentRefValue;
    }

    public void setShipmentRefValue(String shipmentRefValue) {
        this.shipmentRefValue = shipmentRefValue;
    }

    @Override
    public int compareTo(Object obj) {
        if (obj == null)
            return 1;
        ShipmentReferenceRequest other = (ShipmentReferenceRequest) obj;
        ShipmentReferenceType thisType = getRefTypeEnum(this.shipmentRefType);
        ShipmentReferenceType otherType = getRefTypeEnum(other.getShipmentRefType());
        if (thisType != null && otherType != null) {
            return thisType.compareTo(otherType);
        } else {
            return this.shipmentRefType.compareTo(other.getShipmentRefType());
        }
    }

    private ShipmentReferenceType getRefTypeEnum(String type) {
        ShipmentReferenceType etype = null;
        try {
            etype = ShipmentReferenceType.valueOf(type);
        } catch (Exception e) {

        }
        return etype;
    }


}
