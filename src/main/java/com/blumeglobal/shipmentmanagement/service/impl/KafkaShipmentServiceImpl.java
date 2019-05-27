package com.blumeglobal.shipmentmanagement.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentNotificationRepository;
import com.blumeglobal.shipmentmanagement.kafka.producer.KafkaGenericShipmentProducer;
import com.blumeglobal.shipmentmanagement.kafka.producer.KafkaShipmentProducer;
import com.blumeglobal.shipmentmanagement.kafka.producer.KafkaWorkorderProducer;
import com.blumeglobal.shipmentmanagement.model.ShipmentNotification;
import com.blumeglobal.shipmentmanagement.request.ShipmentWorkOrderRequest;
import com.blumeglobal.shipmentmanagement.request.common.GenericShipmentRequest;
import com.blumeglobal.shipmentmanagement.response.KafkaShipmentResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentResponse;
import com.blumeglobal.shipmentmanagement.service.IKafkaShipmentService;
import com.blumeglobal.shipmentmanagement.service.helper.DispatchConstants;
import com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;


/**
 * The KafkaShipmentServiceImpl implements to manage the shipment.
 *
 */
@Service
@REZ1Logger
@REZ1PerformanceLogger
public class KafkaShipmentServiceImpl implements IKafkaShipmentService {
    private static final Logger logger = LogManager.getLogger(KafkaShipmentServiceImpl.class);

    @Autowired
    private KafkaShipmentProducer sendShipments;

    @Autowired
    private KafkaGenericShipmentProducer sendGenericShipments;

    @Autowired
    private KafkaWorkorderProducer sendWorkorder;

    @Autowired
    private ShipmentNotificationRepository shipmentNotificationRepository;

    /**
     * This KafkaShipmentServiceImpl.kafkaCallShipmentV1 method is used to call kafka publisher using
     * ShipmentWorkOrderRequest request also checks ShipmentNotification table , is "ISSHIPMENT" value is 1 or 0. If 1
     * process data and ) ignores it.
     * 
     * @param kafkaCallShipment This is used to pass ShipmentRequest data
     * @return ShipmentResponse upload To get response of ShipmentResponse
     * @throws Exception Handle the exceptions
     */
    @Override
	public KafkaShipmentResponse kafkaCallShipmentV1(ShipmentWorkOrderRequest shipmentRequest)throws Exception {
		try {

			logger.info("Entering into KafkaShipmentServiceImpl.kafkaCallShipmentV1");
			KafkaShipmentResponse resp = new KafkaShipmentResponse();
			String originatorCode = shipmentRequest.getOriginatorCode();
			if(originatorCode != null && !originatorCode.isEmpty()) {
				//checks isshipment value is 1 or 0 from shipmentnotification table
				ShipmentNotification isEnabled = shipmentNotificationRepository.findByOriginatorCode(originatorCode);
				//forming json from input events for kafka feed
				if(isEnabled.getIsShipment() == 1) {
					Gson gson = new Gson();
					String jsonShipment = gson.toJson(shipmentRequest);
					sendShipments.sendShipmentsV1(jsonShipment);
					resp.setStatus(ShipmentConstants.SUCCESS);
					resp.setMessage(ShipmentConstants.SENT_JSON_SHIPMENT_DATA_SUCCESS_MESSAGE);

					return resp;
				}
				else if(isEnabled.getIsShipment() == 0){
					resp.setStatus(ShipmentConstants.IGNORED);
					resp.setMessage(ShipmentConstants.SENT_JSON_SHIPMENT_DATA_IGNORE_MESSAGE);
					return resp;
				}
				else {
					resp.setStatus(ShipmentConstants.FAILED);
					resp.setMessage(ShipmentConstants.SENT_JSON_SHIPMENT_DATA_FAILURE_MESSAGE);
					return resp;
				}
			}
			else {
				resp.setStatus(ShipmentConstants.FAILED);
				resp.setMessage(ShipmentConstants.EMPTY_ORG_CODE_MESSAGE);
				if(logger.isDebugEnabled()) {
					logger.debug("Organization code is Empty");
				}
				
				return resp;
			}
		} catch (Exception ex) {
			logger.error("Exception inside KafkaShipmentServiceImpl.kafkaCallShipmentV1.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}


    @Override
    public ShipmentResponse kafkaCallGenericShipment(GenericShipmentRequest shipmentRequest) throws Exception {
        try {

            logger.info("Entering into KafkaShipmentServiceImpl.kafkaCallGenericShipment");
            ShipmentResponse resp = new ShipmentResponse();
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .setDateFormat(DispatchConstants.DATE_FORMAT_YYYY_MM_DD_HHMMSS_SSS_Z)
                    .create();
            String jsonShipment = gson.toJson(shipmentRequest);
            sendGenericShipments.sendShipment(jsonShipment);
            resp.setStatus(ShipmentConstants.SUCCESS);
            resp.setMessage(ShipmentConstants.SENT_JSON_SHIPMENT_DATA_SUCCESS_MESSAGE);

            return resp;
        } catch (Exception ex) {
            logger.error("Exception inside KafkaShipmentServiceImpl.kafkaCallGenericShipment.Catch_Block:"
                    + ex.getMessage());
            throw ex;
        }
    }

    @Override
    public ShipmentResponse kafkaCallWorkorder(GenericShipmentRequest shipmentRequest) {
        try {
            logger.info("Entering into KafkaShipmentServiceImpl.kafkaCallWorkorder");
            ShipmentResponse resp = new ShipmentResponse();
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .setDateFormat(DispatchConstants.DATE_FORMAT_YYYY_MM_DD_HHMMSS_SSS_Z)
                    .create();
            String jsonShipment = gson.toJson(shipmentRequest);
            sendWorkorder.sendShipment(jsonShipment);
            resp.setStatus(ShipmentConstants.SUCCESS);
            resp.setMessage(ShipmentConstants.SENT_JSON_SHIPMENT_DATA_SUCCESS_MESSAGE);
            return resp;
        } catch (Exception ex) {
            logger.error("Exception inside KafkaShipmentServiceImpl.kafkaCallWorkorder.Catch_Block:" + ex.getMessage());
            throw ex;
        }
    }

}
