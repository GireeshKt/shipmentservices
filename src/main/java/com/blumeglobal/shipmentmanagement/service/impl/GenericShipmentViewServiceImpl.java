package com.blumeglobal.shipmentmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blumeglobal.shipmentmanagement.dao.repositories.ChargeRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.EquipmentOnShipmentRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.StopRepository;
import com.blumeglobal.shipmentmanagement.enums.ExceptionCode;
import com.blumeglobal.shipmentmanagement.external.hub.adapter.IOrganizationAdapter;
import com.blumeglobal.shipmentmanagement.model.Charge;
import com.blumeglobal.shipmentmanagement.model.EquipmentOnShipment;
import com.blumeglobal.shipmentmanagement.model.Shipment;
import com.blumeglobal.shipmentmanagement.model.Stop;
import com.blumeglobal.shipmentmanagement.model.mapper.GenericShipmentRequestMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.StopMapper;
import com.blumeglobal.shipmentmanagement.request.ShipmentFacilityRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentStopListRequest;
import com.blumeglobal.shipmentmanagement.request.StopRequest;
import com.blumeglobal.shipmentmanagement.request.common.LocationDetail;
import com.blumeglobal.shipmentmanagement.response.common.ChargeDetailResponse;
import com.blumeglobal.shipmentmanagement.response.common.GenericShipmentResponse;
import com.blumeglobal.shipmentmanagement.response.common.ShipmentEquipmentResponse;
import com.blumeglobal.shipmentmanagement.service.IGenericShipmentViewService;
import com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants;
import com.blumeglobal.shipmentmanagement.utils.ShipmentUtil;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;


/**
 * The ShipmentServiceImpl implements to manage the shipment.
 *
 */
@Service
@REZ1Logger
@REZ1PerformanceLogger
public class GenericShipmentViewServiceImpl implements IGenericShipmentViewService {
    private static final Logger logger = LogManager.getLogger(GenericShipmentViewServiceImpl.class);

    @Autowired
    ShipmentUtil shipmentUtil;

    @Autowired
    ShipmentRepository shipmentRepository;

    @Autowired
    GenericShipmentRequestMapper shipmentMapper;
    
    @Autowired
    EquipmentOnShipmentRepository equipmentRepository;
    
    @Autowired
    ChargeRepository chargeRepository;
    
    @Autowired
    IOrganizationAdapter organizationAdapter;
    
    @Autowired
    StopRepository stopRepository;
    
    @Autowired
    StopMapper stopMapper;
    
    public GenericShipmentResponse getShipmentDetailById(Long id) throws Exception {
        try {
            if (id != null) {
                Shipment shipment = shipmentRepository.findByShipmentId(id);
                if (shipment == null)
                    throw shipmentUtil.throwDataNotFoundException(ExceptionCode.SHIPMENTEVENTNOTFOUNDCUSTOM.toString(),
                            ShipmentConstants.SHIPMENT_ID_DATA_AVAILABILITY_MESSAGE);
                else {
                    return mapShipmentResponse(shipment);
                }
                
            } else
                throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(),
                        ShipmentConstants.SHIPMENT_ID_NULL_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(
                    "Exception inside GenericShipmentViewServiceImpl.findShipmentLegById.Catch_Block:" + ex.getMessage());
            throw ex;
        }
    }


    @Override
    public GenericShipmentResponse searchShipmentByRefTypeValue(String refType, String refValue) {
        Shipment shipment =
                shipmentRepository.findShipmentByRefTypeValue(refType.toUpperCase(), refValue);
        if (shipment == null )
            throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTNOTFOUND.toString(),
                    ShipmentConstants.NO_RESULT_FOUND_MESSAGE);
        return mapShipmentResponse(shipment);
    }
    
    private GenericShipmentResponse mapShipmentResponse(Shipment shipment) {
        GenericShipmentResponse shipmentResponse = shipmentMapper.map(shipment, GenericShipmentResponse.class);
        shipmentResponse.setShipFrom(new LocationDetail(shipment.getOriginAddress1(), shipment.getOriginAddress2(), 
                shipment.getOriginCity(), shipment.getOrigin(), null, null, shipment.getOriginState(), 
                shipment.getOriginCountry(), shipment.getOriginZipcode(), shipment.getOrigin_LocationId(), shipment.getOriginUnlocode()));
        shipmentResponse.setShipTo(new LocationDetail(shipment.getDestinationAddress1(), shipment.getDestinationAddress2(), 
                shipment.getDestinationCity(), shipment.getDestination(), null, null, shipment.getDestinationState(), 
                shipment.getDestinationCountry(), shipment.getDestinationZipcode(), shipment.getDestination_LocationId(), shipment.getDestinationUnlocode()));
       List<EquipmentOnShipment> equipments = equipmentRepository.findByShipmentLegId(shipment.getShipmentId());
       if(!equipments.isEmpty()) {
           ShipmentEquipmentResponse shipmentEquipment=shipmentMapper.map(equipments.get(0), ShipmentEquipmentResponse.class);
           shipmentResponse.setShipmentEquipment(shipmentEquipment);
           List<Charge> charges = chargeRepository.findByEquipmentOnShipmentId(shipmentEquipment.getEquipmentOnShipmentId());
           List<ChargeDetailResponse> chargeResponses=new ArrayList<>();
           for(Charge ch:charges) {
               ChargeDetailResponse charge = shipmentMapper.map(ch, ChargeDetailResponse.class);
               chargeResponses.add(charge);
           }
           shipmentEquipment.setCharge(chargeResponses);
       }
       //add stop lists
       ShipmentStopListRequest shipmentStopListRequest= new ShipmentStopListRequest();
       List<StopRequest> stopList = new ArrayList<>();
       List<Stop> stops = stopRepository.findByShipmentOrderByStopNumber(shipment);
       for(Stop stop:stops) {
           StopRequest stopRequest = new StopRequest();
           stopRequest.setStopName(stop.getName());
           stopRequest.setStopNumber(stop.getStopNumber());
           stopRequest.setStopType(stop.getStopType());
           ShipmentFacilityRequest facility = stopMapper.map(stop, ShipmentFacilityRequest.class);
           stopRequest.setFacility(facility);
           stopList.add(stopRequest);
       }
       shipmentStopListRequest.setStopList(stopList);
       
       shipmentResponse.setShipmentStopListRequest(shipmentStopListRequest);
       
       shipmentResponse.setShipmentId(shipment.getShipmentId());
       
        return shipmentResponse;
    }
}
