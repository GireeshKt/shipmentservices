package com.blumeglobal.shipmentmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.StopRepository;
import com.blumeglobal.shipmentmanagement.enums.ExceptionCode;
import com.blumeglobal.shipmentmanagement.enums.ShipmentMode;
import com.blumeglobal.shipmentmanagement.model.Shipment;
import com.blumeglobal.shipmentmanagement.model.Stop;
import com.blumeglobal.shipmentmanagement.model.mapper.StopMapper;
import com.blumeglobal.shipmentmanagement.request.ShipmentStopListRequest;
import com.blumeglobal.shipmentmanagement.request.StopMapperRequest;
import com.blumeglobal.shipmentmanagement.request.StopRequest;
import com.blumeglobal.shipmentmanagement.response.StopResponse;
import com.blumeglobal.shipmentmanagement.service.IShipmentMilestoneService;
import com.blumeglobal.shipmentmanagement.service.IStopService;
import com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants;
import com.blumeglobal.shipmentmanagement.utils.BlumeVisibilityUtil;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

/**
 * The StopServiceImpl implements to manage the Stop.
 *
 */
@Service
@REZ1Logger
@REZ1PerformanceLogger
public class StopServiceImpl implements IStopService {
	private static final Logger logger = LogManager.getLogger(StopServiceImpl.class);
	
	@Autowired
	BlumeVisibilityUtil blumeVisibilityUtil;
	
	@Autowired
	StopRepository stopRepository;
	
	@Autowired
	StopMapper stopMapper;
	
	@Autowired
	ShipmentRepository shipmentRepository;
	
	@Autowired
	IShipmentMilestoneService shipmentMilestoneService;
	
	/**
	 * Create Stop through API
	 * 
	 * @param stopListRequest
	 *            This is request which has Stop details
	 * @return StopResponse Stop Response after save
	 * @throws Exception
	 *             Handle service exceptions
	 */
	public StopResponse createStop(ShipmentStopListRequest shipmentStopListRequest, Long shipmentId) throws Exception {
		try {
			
			logger.info("Entering into StopServiceImpl.createStop.");
			
			if (shipmentStopListRequest == null || shipmentStopListRequest.getStopList() == null) {
				logger.error("StopServiceImpl.createStop - Throwing Exception as Invalid Event Code Passed");
				throw blumeVisibilityUtil.throwInValidDataException(ExceptionCode.STOPNOTFOUND.toString(), 
						ShipmentConstants.EMPTY_SHIPMENT_STOP_LIST_REQUEST_MESSAGE);
			}
			
			logger.info("Entering into StopServiceImpl.createStop: Find shipment by its shipmentId.");
			
			Shipment shipment = shipmentRepository.findByShipmentId(shipmentId);
			if(shipment == null) {
				throw blumeVisibilityUtil.throwDataNotFoundException(ExceptionCode.SHIPMENTNOTFOUND.toString(), 
						ShipmentConstants.SHIPMENT_ID_DATA_AVAILABILITY_MESSAGE);
			}
			
			List<Long> stopIds = new ArrayList<>();
			List<Stop> stops = new ArrayList<>();
			
			String status = "done";
			String message = "complete";
			
			logger.info("Entering into StopServiceImpl.createStop: loop through stopRequest object and save into database");
			for(StopRequest stopRequest : shipmentStopListRequest.getStopList()) {
				logger.info("Entering into StopServiceImpl.createStop: map stopRequest with StopMapperRequest class");
				
				StopMapperRequest stopMapperRequest = stopMapper.map(stopRequest, StopMapperRequest.class);
				if(stopMapperRequest == null) {
					throw blumeVisibilityUtil.throwDataNotFoundException(ExceptionCode.STOPMAPPERREQUESTNOTFOUND.toString(), 
							ShipmentConstants.EMPTY_STOP_MAPPER_REQUEST_MESSAGE);
				}
				
				logger.info("Entering into StopServiceImpl.createStop: map stopMapperRequest with Stop class");
				
				Stop stop = stopMapper.map(stopMapperRequest, Stop.class);
				if(stop == null) {
					throw blumeVisibilityUtil.throwDataNotFoundException(ExceptionCode.STOPNOTFOUND.toString(), 
							ShipmentConstants.EMPTY_STOP_MAPPER_REQUEST_MESSAGE);
				}
				
				logger.info("Entering into StopServiceImpl.createStop: set predefined values");
				stop.setShipment(shipment);
				stop.setModeOfTransport(ShipmentMode.DRAY.name());
				
				if(stop.getStopNumber() == null) {
					throw blumeVisibilityUtil.throwDataNotFoundException(ExceptionCode.STOPNUMBERNULL.toString(), 
							ShipmentConstants.NULL_STOP_NUMBER_FOR_STOP_MODEL_MESSAGE);
				}
				
				
				logger.info("Entering into StopServiceImpl.createStop: Add stop to stops list.");
				stops.add(stop);
			}
			
			logger.info("Entering into StopServiceImpl.createStop: run insert query");
			stopRepository.saveAll(stops);
			logger.info("Entering into StopServiceImpl.createStop: after run insert query");
			
			
			
			logger.info("Entering into StopServiceImpl.createStop: call method findByShipmentOrderByStopNumber to fetch list of stops.");
			List<Stop> listOfStops = stopRepository.findByShipmentOrderByStopNumber(shipment);
			if(listOfStops.size() > 2) {
				logger.info("Entering into StopServiceImpl.createStop: call method updateShipmentMilestones if no.of stops are greater than 2.");
			
				/*
				 * Commented list of stops to Sublist, it is causing Array out of bound Exception.
				 * Assuming it is copy pasted from demo
				*/	
					
				//	listOfStops = listOfStops.subList(26,30);
				
				shipmentMilestoneService.updateShipmentMilestones(listOfStops, shipment);
			}
			
			
			
			ListIterator<Stop> stopObjects = stops.listIterator();
			while(stopObjects.hasNext()){
				stopIds.add(stopObjects.next().getStopId());			
			}
			
			// Response
			StopResponse stopResponse = new StopResponse();
			stopResponse.setIds(stopIds);
			stopResponse.setStatus(status);
			stopResponse.setMessage(message);
			
			return stopResponse;
			
		} catch (Exception ex) {
			logger.error("Exception inside StopServiceImpl.createStop.Catch_Block:" + ex.getMessage());
			throw blumeVisibilityUtil.throwRuntimeException(ExceptionCode.CATCHBLOCKRUNTIMEEXCEPTION.toString(), ex.getMessage(), 
					ShipmentConstants.RUN_TIME_ERROR_MESSAGE);
		}
	}
	
}
