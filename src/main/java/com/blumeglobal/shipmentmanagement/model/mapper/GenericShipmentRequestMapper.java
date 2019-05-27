package com.blumeglobal.shipmentmanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.model.Charge;
import com.blumeglobal.shipmentmanagement.model.EquipmentOnShipment;
import com.blumeglobal.shipmentmanagement.model.Shipment;
import com.blumeglobal.shipmentmanagement.request.ShipmentRequest;
import com.blumeglobal.shipmentmanagement.request.common.ChargeDetailRequest;
import com.blumeglobal.shipmentmanagement.request.common.GenericShipmentRequest;
import com.blumeglobal.shipmentmanagement.request.common.ShipmentEquipmentRequest;
import com.blumeglobal.shipmentmanagement.response.common.ChargeDetailResponse;
import com.blumeglobal.shipmentmanagement.response.common.GenericShipmentResponse;
import com.blumeglobal.shipmentmanagement.response.common.ShipmentEquipmentResponse;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class GenericShipmentRequestMapper extends ConfigurableMapper {
	
	@Override
	protected void configure(MapperFactory factory) {
		
		factory.classMap(GenericShipmentRequest.class, ShipmentRequest.class)
		.field("modeofTransportation","legMode")
        .field("consigneeName","consignee")
        .field("consignerName","consignor")
        .field("carrierName","carrier")
        .field("scheduledPickupDate", "schedulePickupDate")
        .field("scheduledDeliveryDate", "scheduleDeliveryDate")
        .field("requestedDeliveryDate", "expectedDeliveryDate")//?
        .field("requestedPickupDate", "workOrderCreatedDate")//?
        .field("shipmentEquipment.freightDescription","freightDescription")
        .field("shipmentEquipment.equipmentNumber","unitId")
        .field("vesselName","vessel")
        .field("voyageFlightNo","voyage")
        .field("shipmentType","category")
        .field("receiverCode","receiverName")

        .field("shipFrom.locationName", "origin")
        .field("shipFrom.address1", "originAddress1")
        .field("shipFrom.address2", "originAddress2")
        .field("shipFrom.city", "originCity")
        .field("shipFrom.country", "originCountry")
        .field("shipFrom.state", "originState")
        .field("shipFrom.zip", "originZipcode")
        .field("shipFrom.uuid", "origin_LocationId")
        .field("shipFrom.unlocode", "originUnlocode")
        
        .field("shipTo.locationName", "destination")
		.field("shipTo.address1", "destinationAddress1")
		.field("shipTo.address2", "destinationAddress2")
		.field("shipTo.city", "destinationCity")
		.field("shipTo.country", "destinationCountry")
		.field("shipTo.state", "destinationState")
		.field("shipTo.zip", "destinationZipcode")
        .field("shipTo.uuid", "destination_LocationId")
        .field("shipTo.unlocode", "destinationUnlocode")
		
        .byDefault().register();    
//		  not mapped fields
//		  "shipFromReference": "string","
//		  "shipToReference": "string","
//		  "shipmentINCOterm": "string",
//        "uniqueShipmentId": "string",
//        list of Notes
        factory.classMap(ShipmentEquipmentRequest.class, EquipmentOnShipment.class)
        .field("equipmentCode", "equipmentTypeCode")
        .byDefault().register();
        
        factory.classMap(EquipmentOnShipment.class, ShipmentEquipmentResponse.class)
        .field("equipmentTypeCode", "equipmentCode")
        .byDefault().register();

        factory.classMap(ChargeDetailRequest.class, Charge.class)
        .field("freightCharge", "amount")
        .field("fuelSurchargeTotal", "fscAmount")
        .field("fuelSurchagePercent", "fscPercent")
        .byDefault().register();

        factory.classMap(Charge.class, ChargeDetailResponse.class)
        .field("amount", "freightCharge")
        .field("fscAmount", "fuelSurchargeTotal")
        .field("fscPercent", "fuelSurchagePercent")
        .byDefault().register();

        factory.classMap(Shipment.class, GenericShipmentResponse.class)
        .field("legMode","modeofTransportation")
        .field("consignee","consigneeName")
        .field("consignor", "consignerName")
        .field("carrier", "carrierName")
        .field("schedulePickupDate", "scheduledPickupDate")
        .field("scheduleDeliveryDate", "scheduledDeliveryDate")
        .field("expectedDeliveryDate", "requestedDeliveryDate")//?
        .field("workOrderCreatedDate", "requestedPickupDate")//?
        .field("vessel","vesselName")
        .field("voyage","voyageFlightNo")
        .field("category", "shipmentType")
        .field("receiverName","receiverCode")
        .byDefault().register();

        
     }
}
