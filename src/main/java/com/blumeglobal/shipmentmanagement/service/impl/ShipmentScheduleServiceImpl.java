package com.blumeglobal.shipmentmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentSchedulesRepository;
import com.blumeglobal.shipmentmanagement.enums.ExceptionCode;
import com.blumeglobal.shipmentmanagement.model.ShipmentSchedule;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentScheduleStopMapper;
import com.blumeglobal.shipmentmanagement.request.ShipmentScheduleRequest;
import com.blumeglobal.shipmentmanagement.response.ShipmentScheduleResponse;
import com.blumeglobal.shipmentmanagement.service.IShipmentScheduleService;
import com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants;
import com.blumeglobal.shipmentmanagement.utils.ShipmentUtil;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;


/**
 * The ShipmentScheduleServiceImpl implements to manage the shipment schedules.
 *
 */
@Service
@REZ1Logger
@REZ1PerformanceLogger
public class ShipmentScheduleServiceImpl implements IShipmentScheduleService{
	
	@Autowired
	ShipmentUtil shipmentUtil;
	
	@Autowired
	ShipmentScheduleStopMapper shipmentScheduleStopMapper;
	
	@Autowired
	ShipmentSchedulesRepository shipmentSchedulesRepository;
	
	private static final Logger logger = LogManager.getLogger(ShipmentScheduleServiceImpl.class);
	
	public ShipmentScheduleResponse createShipmentSchedule(ShipmentScheduleRequest createSchedule) throws Exception {
		try {
			if(createSchedule == null) {
				if(logger.isDebugEnabled()) {
					logger.debug("ShipmentScheduleServiceImpl.createShipmentSchedule - Throwing Exception as Invalid Schedule Passed");
				}
				
				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTINVALID.toString(), ShipmentConstants.INVALID_SHIPMENT_SCHEDULE_MESSAGE);
			}
			String status = "";
			String message = "";
			ShipmentSchedule createSchedulesReq = shipmentScheduleStopMapper.map(createSchedule, ShipmentSchedule.class);
			ShipmentSchedule upload = shipmentSchedulesRepository.save(createSchedulesReq);
			ShipmentScheduleResponse scheduleResponse = shipmentScheduleStopMapper.map(createSchedulesReq, ShipmentScheduleResponse.class);
			scheduleResponse.setId(upload.getshipmentScheduleId().toString());
			scheduleResponse.setStatus(status);
			scheduleResponse.setMessage(message);
			return scheduleResponse;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public ShipmentScheduleResponse updateSchedule(ShipmentScheduleRequest updateRequest, Long id) throws Exception {
		try {
			logger.info("Entering into ShipmentServiceImpl.updateShipment");
			
			if (updateRequest == null) {
				if(logger.isDebugEnabled()) {
					logger.debug("ShipmentScheduleServiceImpl.updateShipmentSchedule - Throwing Exception as Invalid Schedule Id Passed");
				}
				
				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(), ShipmentConstants.EMPTY_SHIPMENT_SCHEDULE_REQUEST_MESSAGE);
			}
			
			String status = ShipmentConstants.DONE_MESSAGE;
			String message = ShipmentConstants.COMPLETE_MESSAGE;
			ShipmentSchedule createReq = shipmentScheduleStopMapper.map(updateRequest, ShipmentSchedule.class);
			
			Optional<ShipmentSchedule> shipmentScheduleObj = shipmentSchedulesRepository.findById(id);
			ShipmentSchedule upload = null;
			
			if(shipmentScheduleObj.isPresent()) {
				createReq.setshipmentScheduleId(id);
				upload = shipmentSchedulesRepository.save(createReq);
				ShipmentScheduleResponse scheduleResponse = shipmentScheduleStopMapper.map(createReq, ShipmentScheduleResponse.class);
				scheduleResponse.setId(upload.getshipmentScheduleId().toString());
				scheduleResponse.setStatus(status);
				scheduleResponse.setMessage(message);
				return scheduleResponse;
			} else {
				return createShipmentSchedule(updateRequest);
			}
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public List<ShipmentScheduleResponse> getSchedulebyVessel(String vessel){
		try {
			if(vessel == null) {
				if(logger.isDebugEnabled()) {
					logger.debug("ShipmentScheduleServiceImpl.getSchedulebyVessel - Throwing Exception as Invalid Schedule Passed");
				}
				
				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTINVALID.toString(), ShipmentConstants.INVALID_VESSEL_MESSAGE);
			}
			List<ShipmentScheduleResponse> results = new ArrayList<>();
			List<ShipmentSchedule> scheduleList = shipmentSchedulesRepository.getShipmentScheduleByvessel(vessel);
			scheduleList.forEach(schedule -> {
				ShipmentScheduleResponse shipmentScheduleResponse = shipmentScheduleStopMapper.map(schedule, ShipmentScheduleResponse.class);
				results.add(shipmentScheduleResponse);
			});
			return results;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<ShipmentScheduleResponse> getSchedulebyCarrier(String carrier){
		try {
			logger.info("BEGINNING ShipmentScheduleServiceImpl.getSchedulebyCarrier");
			List<ShipmentScheduleResponse> results = new ArrayList<>();
			List<ShipmentSchedule> scheduleList = shipmentSchedulesRepository.getShipmentScheduleBycarrierName(carrier);
			scheduleList.forEach(schedule -> {
				ShipmentScheduleResponse shipmentScheduleResponse = shipmentScheduleStopMapper.map(schedule, ShipmentScheduleResponse.class);
				results.add(shipmentScheduleResponse);
			});
			logger.info("END ShipmentScheduleServiceImpl.getSchedulebyCarrier");
			return results;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<ShipmentScheduleResponse> getSchedulebyLane(String lane){
		try {
			if(lane == null) {
				if(logger.isDebugEnabled()) {
					logger.debug("ShipmentScheduleServiceImpl.getSchedulebyVessel - Throwing Exception as Invalid Lane Passed");
				}
				
				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTINVALID.toString(), ShipmentConstants.INVALID_LANE_CODE_MESSAGE);
			}
			logger.info("BEGINNING ShipmentScheduleServiceImpl.getSchedulebyLane");
			List<ShipmentScheduleResponse> results = new ArrayList<>();
			List<ShipmentSchedule> scheduleList = shipmentSchedulesRepository.getShipmentScheduleByserviceLane(lane);
			scheduleList.forEach(schedule -> {
				ShipmentScheduleResponse shipmentScheduleResponse = shipmentScheduleStopMapper.map(schedule, ShipmentScheduleResponse.class);
				results.add(shipmentScheduleResponse);
			});
			logger.info("END ShipmentScheduleServiceImpl.getSchedulebyLane");
			return results;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<ShipmentScheduleResponse> getSchedulebyOriginAndDestination(String origin, String destination){
		try {
			if(origin == null || destination == null) {
				if(logger.isDebugEnabled()) {
					logger.debug("ShipmentScheduleServiceImpl.getSchedulebyVessel - Throwing Exception as Invalid Origin or Destination Passed");
				}
				
				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTINVALID.toString(), ShipmentConstants.INVALID_ORIGIN_DESTINATION_MESSAGE);
			}
			logger.info("BEGINNING ShipmentScheduleServiceImpl.getSchedulebyOriginAndDestinatio");
			List<ShipmentScheduleResponse> results = new ArrayList<>();
			List<ShipmentSchedule> scheduleList = shipmentSchedulesRepository.getShipmentScheduleByOriginLocationIdAndDestinationLocationId(origin, destination);
			scheduleList.forEach(schedule -> {
				ShipmentScheduleResponse shipmentScheduleResponse = shipmentScheduleStopMapper.map(schedule, ShipmentScheduleResponse.class);
				results.add(shipmentScheduleResponse);
			});
			logger.info("END ShipmentScheduleServiceImpl.getSchedulebyOriginAndDestinatio");
			return results;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<ShipmentScheduleResponse> getSchedulebyOrigin(String origin){
		try {
			if(origin == null) {
				if(logger.isDebugEnabled()) {
					logger.debug("ShipmentScheduleServiceImpl.getSchedulebyVessel - Throwing Exception as Invalid Origin");
				}
				
				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTINVALID.toString(), ShipmentConstants.INVALID_ORIGIN_MESSAGE);
			}
			logger.info("BEGINNING ShipmentScheduleServiceImpl.getSchedulebyOrigin");
			List<ShipmentScheduleResponse> results = new ArrayList<>();
			List<ShipmentSchedule> scheduleList = shipmentSchedulesRepository.getShipmentScheduleByOriginLocationId(origin);
			scheduleList.forEach(schedule -> {
				ShipmentScheduleResponse shipmentScheduleResponse = shipmentScheduleStopMapper.map(schedule, ShipmentScheduleResponse.class);
				results.add(shipmentScheduleResponse);
			});
			logger.info("END ShipmentScheduleServiceImpl.getSchedulebyOrigin");
			return results;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<ShipmentScheduleResponse> getSchedulebyDestination(String destination){
		try {
			if(destination == null) {
				if(logger.isDebugEnabled()) {
					logger.debug("ShipmentScheduleServiceImpl.getSchedulebyVessel - Throwing Exception as Invalid Destination Passed");
				}
				
				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTINVALID.toString(), ShipmentConstants.INVALID_DESTINATION_MESSAGE);
			}
			logger.info("BEGINNING ShipmentScheduleServiceImpl.getSchedulebyDestination");
			List<ShipmentScheduleResponse> results = new ArrayList<>();
			List<ShipmentSchedule> scheduleList = shipmentSchedulesRepository.getShipmentScheduleByDestinationLocationId(destination);
			scheduleList.forEach(schedule -> {
				ShipmentScheduleResponse shipmentScheduleResponse = shipmentScheduleStopMapper.map(schedule, ShipmentScheduleResponse.class);
				results.add(shipmentScheduleResponse);
			});
			logger.info("END ShipmentScheduleServiceImpl.getSchedulebyDestinaton");
			return results;
		} catch (Exception e) {
			throw e;
		}
	}
}