package com.blumeglobal.shipmentmanagement.service.impl;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentExceptionRepository;
import com.blumeglobal.shipmentmanagement.model.ShipmentException;
import com.blumeglobal.shipmentmanagement.service.IKafkaShipmentExceptionService;

import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

@Service
@REZ1Logger
@REZ1PerformanceLogger
@Transactional
public class KafkaShipmentExceptionServiceImpl implements IKafkaShipmentExceptionService {

	@Autowired
	ShipmentExceptionRepository shipmentExceptionRepository;
	
	private static final Logger logger = LogManager.getLogger(KafkaShipmentExceptionServiceImpl.class);

	
	@Override
	public void createShipmentException(ShipmentException shipmentException) {
		logger.info("Entering into KafkaShipmentExceptionServiceImpl.createShipmentException...");
		ShipmentException row = shipmentExceptionRepository.save(shipmentException);
		logger.info("inside kafkaShipmentException service impl, Arrived at: "+new Date());
		if(row != null) {
			logger.info("Data inserted to Database Successfully");
		}
		else {
			logger.error("Data not inserted to Database");
		}
		
	}
	
}