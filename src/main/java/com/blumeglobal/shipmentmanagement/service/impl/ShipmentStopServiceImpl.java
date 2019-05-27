package com.blumeglobal.shipmentmanagement.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blumeglobal.shipmentmanagement.dao.repositories.E2EShipmentRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.MilestonesRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentMilestonesRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentStopsRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentSchedulesRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentWorkOrderRepository;
import com.blumeglobal.shipmentmanagement.enums.ExceptionCode;
import com.blumeglobal.shipmentmanagement.enums.ShipmentMode;
import com.blumeglobal.shipmentmanagement.enums.ShipmentReferenceIdType;
import com.blumeglobal.shipmentmanagement.enums.ShipmentReferenceType;
import com.blumeglobal.shipmentmanagement.model.E2EShipment;
import com.blumeglobal.shipmentmanagement.model.Milestone;
import com.blumeglobal.shipmentmanagement.model.Shipment;
import com.blumeglobal.shipmentmanagement.model.ShipmentMilestone;
import com.blumeglobal.shipmentmanagement.model.ShipmentReferences;
import com.blumeglobal.shipmentmanagement.model.ShipmentRelationship;
import com.blumeglobal.shipmentmanagement.model.ShipmentSchedule;
import com.blumeglobal.shipmentmanagement.model.ShipmentStop;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentMilestoneMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentModelMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentReferenceMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentRequestMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentScheduleStopMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.WorkorderOceanLegMapper;
import com.blumeglobal.shipmentmanagement.request.ShipmentReferenceRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentStopRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentWorkOrderRequest;
import com.blumeglobal.shipmentmanagement.response.ShipmentResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentScheduleResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentStopResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentWOResponse;
import com.blumeglobal.shipmentmanagement.service.IShipmentMilestoneService;
import com.blumeglobal.shipmentmanagement.service.IShipmentStopService;
import com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants;
import com.blumeglobal.shipmentmanagement.service.IShipmentService;
import com.blumeglobal.shipmentmanagement.utils.ShipmentUtil;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;


/**
 * The ShipmentStopServiceImpl implements to manage the shipment Stops.
 *
 */
@Service
@REZ1Logger
@REZ1PerformanceLogger
public class ShipmentStopServiceImpl implements IShipmentStopService{
	
	private static final Logger logger = LogManager.getLogger(ShipmentStopServiceImpl.class);
	
	@Autowired
	ShipmentUtil shipmentUtil;
	
	@Autowired
	ShipmentScheduleStopMapper shipmentScheduleStopMapper;
	
	@Autowired
	ShipmentStopsRepository shipmentStopsRepository;
	
	public ShipmentStopResponse createShipmentStop(ShipmentStopRequest createStop) throws Exception {
		try {
			if(createStop == null) {
				if(logger.isDebugEnabled()) {
					logger.debug("ShipmentStopServiceImpl.createShipmentStop - Throwing Exception as Invalid Stop Passed");
				}
				
				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTINVALID.toString(), ShipmentConstants.INVALID_SHIPMENT_STOP_MESSAGE);
			}
			String status = "";
			String message = "";
			ShipmentStop createStopsReq = shipmentScheduleStopMapper.map(createStop, ShipmentStop.class);
			ShipmentStop upload = shipmentStopsRepository.save(createStopsReq);
			ShipmentStopResponse stopResponse = shipmentScheduleStopMapper.map(createStopsReq, ShipmentStopResponse.class);
			stopResponse.setId(upload.getSailingStopId().toString());
			stopResponse.setStatus(status);
			stopResponse.setMessage(message);
			return stopResponse;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public ShipmentStopResponse updateStop(ShipmentStopRequest updateRequest, Long id) throws Exception {
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
			ShipmentStop createReq = shipmentScheduleStopMapper.map(updateRequest, ShipmentStop.class);
			
			Optional<ShipmentStop> shipmentScheduleObj = shipmentStopsRepository.findById(id);
			ShipmentStop upload = null;
			
			if(shipmentScheduleObj.isPresent()) {
				createReq.setSailingStopId(id);
				upload = shipmentStopsRepository.save(createReq);
				ShipmentStopResponse stopResponse = shipmentScheduleStopMapper.map(updateRequest, ShipmentStopResponse.class);
				stopResponse.setId(upload.getSailingStopId().toString());
				stopResponse.setStatus(status);
				stopResponse.setMessage(message);
				return stopResponse;
			} else {
				return createShipmentStop(updateRequest);
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<ShipmentStopResponse> getShipmentList(String id){
		if(id==null ) throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTINVALID.toString(), "E2E Shipment ID should not be null");
		List<ShipmentStopResponse> results = new ArrayList<>();
		List<ShipmentStop> stops = shipmentStopsRepository.getShipmentStopBysailingScheduleId(Long.parseLong(id));
		stops.forEach(stop -> {
			ShipmentStopResponse shipmentResponse=shipmentScheduleStopMapper.map(stop, ShipmentStopResponse.class);
			results.add(shipmentResponse);
		});
		return results;
	}
}