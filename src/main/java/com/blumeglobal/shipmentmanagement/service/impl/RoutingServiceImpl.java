package com.blumeglobal.shipmentmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blumeglobal.shipmentmanagement.dao.repositories.RoutingRepository;
import com.blumeglobal.shipmentmanagement.enums.ExceptionCode;
import com.blumeglobal.shipmentmanagement.model.Lane;
import com.blumeglobal.shipmentmanagement.model.Routing;
import com.blumeglobal.shipmentmanagement.model.mapper.RoutingMapper;
import com.blumeglobal.shipmentmanagement.request.RoutingRequest;
import com.blumeglobal.shipmentmanagement.response.RoutingAPICreateUpdateResponse;
import com.blumeglobal.shipmentmanagement.response.RoutingAPIDeleteResponse;
import com.blumeglobal.shipmentmanagement.response.RoutingGetResponse;
import com.blumeglobal.shipmentmanagement.response.RoutingResponse;
import com.blumeglobal.shipmentmanagement.service.IRoutingService;
import com.blumeglobal.shipmentmanagement.utils.ShipmentUtil;
import com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;


/**
 * The ShipmentScheduleServiceImpl implements to manage the shipment schedules.
 *
 */
@Service
@REZ1Logger
@REZ1PerformanceLogger
public class RoutingServiceImpl implements IRoutingService{
	
	@Autowired
	ShipmentUtil shipmentUtil;
	
	@Autowired
	RoutingMapper routeMapper;
	
	@Autowired
	RoutingRepository routeRepository;
	
	private static final Logger logger = LogManager.getLogger(RoutingServiceImpl.class);
	
	
	public RoutingResponse createRouting(RoutingRequest RouteRequest) {
		try {
			if(RouteRequest == null) {
				logger.debug("ShipmentScheduleServiceImpl.createRouting - Throwing Exception as Invalid Routing Passed");
				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTINVALID.toString(), "Routing Invalid");
			}
			logger.debug("ShipmentScheduleServiceImpl.createRouting - inside");
			Routing createRouteReq = routeMapper.map(RouteRequest, Routing.class);
			try {
				Routing upload = routeRepository.save(createRouteReq);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RoutingResponse routeResponse = routeMapper.map(createRouteReq, RoutingResponse.class);
			return routeResponse;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}

	@Override
	public RoutingAPICreateUpdateResponse createRoutingNew(RoutingRequest routingRequest) throws Exception {
		logger.info("Entering into RoutingServiceImpl.createRouting");
		RoutingAPICreateUpdateResponse commonResponse =null;
		try {
			// Added for Handled already existing ID.
			Routing routingID = routeRepository.getRoutingById(routingRequest.getRoutingid());			
			if(null != routingID) {
				return commonResponse;
			}else if(routingRequest.getIsActive().length()<=1 && routingRequest.getTownCrossing().length()<=1 && routingRequest.getRoutingid() != null && !routingRequest.getRoutingid().isEmpty()) {
				commonResponse = new RoutingAPICreateUpdateResponse();
				List<String> errorMsgList = new ArrayList<>();
				ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
				Validator validator = factory.getValidator();
				Set<ConstraintViolation<RoutingRequest>> violations = validator.validate(routingRequest);
				boolean validflag = true;
				StringBuilder sb = new StringBuilder("");
				for (ConstraintViolation<RoutingRequest> violation : violations) {
					String message = violation.getMessage();
					errorMsgList.add(message);
					sb.append(message);
					sb.append(";");
					validflag = false;
				}
				if (validflag) {
					Routing routingMap = routeMapper.map(routingRequest, Routing.class);
					routingMap.setRoutingid(routingRequest.getRoutingid());

					Routing createPO = routeRepository.save(routingMap);
					if (createPO!=null) {
						commonResponse.setStatus("success");
						commonResponse.setMessage("Routing Created Successfully..");
						logger.error("Routing Created Successfully...");
					} else {
						commonResponse.setStatus("failure");
						commonResponse.setMessage("Unable to create Routing..");
						logger.error("Unable to created Routing..");
					}
					return commonResponse;
				} else {
					commonResponse.setStatus("failure");
					commonResponse.setMessage("Validation Failed To Routing");
					logger.error("Validation Failed To Create the Routing");
					return commonResponse;
				} 

			}else {
				commonResponse.setStatus("failure");
				commonResponse.setMessage("Validation Failed To Routing....Parameter isActive / townCrossing should be of char(1)..and routingId cannot be null..");
				logger.error("Validation Failed To Create the Routing");
				return commonResponse;
			}

		} catch (Exception ex) {
			logger.error("Exception inside RoutingServiceImpl.createRouting.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}

	@Override
	public List<RoutingResponse> getAllRoutings() {
		logger.info("Entering into RoutingServiceImpl.getRouting");
		List<RoutingResponse> routingViewResponses = new ArrayList<>(); 
		try {
			List<Routing> routings =  (List<Routing>) routeRepository.findAll(); 
			if (routings.isEmpty()) {
				logger.debug("Entering into RoutingServiceImpl.No Routing available");
				
				return routingViewResponses;
			} else { 
				logger.debug("RoutingServiceImpl.getRoutings - Mapping Routing data with RoutingResponse"); 
				routingViewResponses = routeMapper.mapAsList(routings, RoutingResponse.class); 
				return routingViewResponses; 
			} 
		} catch (Exception ex) {
			logger.error("Exception inside RoutingServiceImpl.getRouting:" +ex.getMessage());
			throw ex; 
	
		}
		
	} 
	
	@Override
	public RoutingAPIDeleteResponse deleteRoutingById(String routingId) {
		RoutingAPIDeleteResponse response = new RoutingAPIDeleteResponse();
		if(routingId != null && !routingId.isEmpty()) {
			routeRepository.deleteById(routingId);
			response.setStatus("Success");
			response.setMessage("Deleted row of ID : " +routingId);
			return response;
		}else {
			response.setStatus("failure");
			response.setMessage("Validation Failed...routingId cannot be null..");
			logger.error("Validation Failed To Update the Routing");
			return response;
		}
	}
	
	@Override
	public RoutingAPICreateUpdateResponse updateRouting(RoutingRequest routingRequest, String routingId) {
		logger.info("Entering into RoutingServiceImpl.updateRouting");
		try {
			RoutingAPICreateUpdateResponse commonResponse = new RoutingAPICreateUpdateResponse();
			if (routingRequest.getIsActive().length()<=1 && routingRequest.getTownCrossing().length()<=1 && routingRequest.getRoutingid()!=null && !routingRequest.getRoutingid().isEmpty()) {
				Routing routing = routeRepository.getRoutingById(routingId);
				if (routing!=null){
					List<String> errorMsgList = new ArrayList<>();
					ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
					Validator validator = factory.getValidator();
					Set<ConstraintViolation<RoutingRequest>> violations = validator.validate(routingRequest);
					boolean validflag = true;
					StringBuilder sb = new StringBuilder("");
					for (ConstraintViolation<RoutingRequest> violation : violations) {
						String message = violation.getMessage();
						errorMsgList.add(message);
						sb.append(message);
						sb.append(";");
						validflag = false;
					}
					if (validflag) {
						Routing routingMap = routeMapper.map(routingRequest, Routing.class);
						routingMap.setRoutingid(routing.getRoutingid());
						Routing updateRouting = routeRepository.save(routingMap);
						if (updateRouting!=null) {
							commonResponse.setStatus("Success");
							commonResponse.setMessage("Routing Updated Successfully... for routingId : "+updateRouting.getRoutingid());
							logger.error("Routing Updated Successfully... routingId: "+updateRouting.getRoutingid());
						} else {
							commonResponse.setStatus("Failure");
							commonResponse.setMessage("Unable to Update Routing... for routingId : "+routingId);
							logger.error("Unable to Update Routing... routingId : "+routingId);
						}
						return commonResponse;
					} else {
						commonResponse.setStatus("Failure");
						commonResponse.setMessage("Validation Failed To Update the Routing Based on Routing ID");
						logger.error("Validation Failed To Update the Routing Based on Routing ID");
						return commonResponse;
					}
				} else {
					commonResponse.setStatus("Failure");
					commonResponse.setMessage("Routing ID not Found : "+routingId);
					logger.error("Routing ID not found : "+routingId);
					return commonResponse;
				}

			}else {
				commonResponse.setStatus("failure");
				commonResponse.setMessage("Validation Failed....Parameter isActive / townCrossing should be of char(1).. and routingId cannot be null..");
				logger.error("Validation Failed To Update the Routing");
				return commonResponse;
			}
		} catch (Exception ex) {
			logger.error("Exception inside RoutingServiceImpl.updateRouting.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}


	@Override
	public RoutingGetResponse getRoutingById(String routingId) {
		RoutingGetResponse res = new RoutingGetResponse();
		RoutingResponse resp = new RoutingResponse();
		if(routingId != null && !routingId.isEmpty()) {
			Routing r = routeRepository.getRoutingById(routingId);			
			resp = routeMapper.map(r, RoutingResponse.class);

			res.setStatus("Success");
			res.setMessage("Successfully retrieved data for routingId.."+routingId);
			res.setSuccessfullData(resp);
			logger.error("Validation Failed To get the Routing");
			return res;
		}else {
			res.setStatus("failure");
			res.setMessage("Validation Failed...routingId cannot be null..");
			logger.error("Validation Failed To get the Routing");
			return res;
		}
	}
}