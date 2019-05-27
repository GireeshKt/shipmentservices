package com.blumeglobal.shipmentmanagement.service;

import com.blumeglobal.shipmentmanagement.response.common.GenericShipmentResponse;

public interface IGenericShipmentViewService {

    /**
     * Get shipment detail by Id through API
     * 
     * @param id This is shipment Id
     * @return GenericShipmentResponse Shipment details
     * @throws Exception Handle service exceptions
     */
    GenericShipmentResponse getShipmentDetailById(Long id) throws Exception;

    GenericShipmentResponse searchShipmentByRefTypeValue(String refType, String refValue); 
    
}
