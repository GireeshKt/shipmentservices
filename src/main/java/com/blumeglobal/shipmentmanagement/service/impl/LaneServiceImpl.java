package com.blumeglobal.shipmentmanagement.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blumeglobal.shipmentmanagement.dao.repositories.LaneGroupsRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.LaneRepository;
import com.blumeglobal.shipmentmanagement.model.Lane;
import com.blumeglobal.shipmentmanagement.model.LaneGroups;
import com.blumeglobal.shipmentmanagement.model.ShipmentStop;
import com.blumeglobal.shipmentmanagement.model.mapper.LaneGroupMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.LaneMapper;
import com.blumeglobal.shipmentmanagement.request.LaneGroupsRequest;
import com.blumeglobal.shipmentmanagement.request.LaneLocationRequest;
import com.blumeglobal.shipmentmanagement.request.LaneRequest;
import com.blumeglobal.shipmentmanagement.request.LocationRequest;
import com.blumeglobal.shipmentmanagement.response.LaneAPICreateUpdateResponse;
import com.blumeglobal.shipmentmanagement.response.LaneAPIDeleteResponse;
import com.blumeglobal.shipmentmanagement.response.LaneGroupAPIDeleteResponse;
import com.blumeglobal.shipmentmanagement.response.LaneGroupsResponse;
import com.blumeglobal.shipmentmanagement.response.LaneLocationResponse;
import com.blumeglobal.shipmentmanagement.response.LaneResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentStopResponse;
import com.blumeglobal.shipmentmanagement.service.ILaneService;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;
import com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants;

/**
* The LaneServiceImpl implements to manage the Lane.
*
*/
@Service
@REZ1Logger
@REZ1PerformanceLogger
public class LaneServiceImpl implements ILaneService {
	private static final Logger logger = LogManager.getLogger(LaneServiceImpl.class);
	
	
	@Autowired
	LaneRepository laneRepository;
	
	@Autowired
	LaneGroupsRepository laneGroupsRepository;
	
	@Autowired
	LaneMapper laneMapper;
	
	@Autowired
	LaneGroupMapper laneGroupMapper;
	
	
	/**
	 * Get Lane API
	 * 
	 * @param laneIdDesc
	 *            This is string which has Id details
	 * @return LaneLocationRequest Lane Location Response after get call
	 * @throws Exception
	 *             Handle service exceptions
	 */ 
	public LaneLocationRequest getLaneById(String laneIdDesc)throws Exception {
		try {
			logger.info("Entering into LaneServiceImpl.getLanebyId");
			
			String laneId = null;
			
			logger.info("Inside into LaneServiceImpl.getLanebyId and split laneIdDesc with '-'");
			
			List<String> laneIdWithDesc = new ArrayList<String>(Arrays.asList(laneIdDesc.split("\\-")));
			if(laneIdWithDesc.size() < 2 || laneIdWithDesc == null) {
				laneId = "LN0001";
		    } else {
		    	laneId = laneIdWithDesc.get(0).trim();
		    }
			
			logger.info("Inside into LaneServiceImpl.getLanebyId and call getLaneById method to fire query to fetch record into database table.");
			
			Lane lane = laneRepository.getLaneById(laneId);
			LaneLocationResponse laneLocationResponse = laneMapper.map(lane, LaneLocationResponse.class);
			
			LaneLocationRequest laneLocationRequest = new LaneLocationRequest();
			
			//Origin Location field extract
			LocationRequest locationRequestOrigin = setLocationRequest(laneLocationResponse.getOrigin_locationId(), "S");
			
			//Origin Location field extract
			LocationRequest locationRequestDest = setLocationRequest(laneLocationResponse.getDestination_locationId(), "D");
			
			laneLocationRequest.setOrigin(locationRequestOrigin);
			laneLocationRequest.setDestination(locationRequestDest);
			
			System.out.println(laneLocationRequest);
			
			return laneLocationRequest;
		} catch (Exception ex) {
			logger.error("Exception inside LaneServiceImpl.getLanebyId.Catch_Block:" + ex.getMessage());
			throw ex;
		}
		
	}

	
	/**
	 * set Location Request Values
	 * 
	 * @param location
	 *            This is string which has location details separated with '|'
	 * @return LocationRequest
	 */ 
	public LocationRequest setLocationRequest(String location, String flag) {
		logger.info("Entering into LaneServiceImpl.setLocationRequest");
		
		LocationRequest locationRequest = new LocationRequest();
		
		List<String> locationList = new ArrayList<String>(Arrays.asList(location.split("\\|")));
		
		if(flag == "S" && (locationList.size() < 5 || locationList == null)) {
			locationRequest.setAddress("Zhuhai Ave Jinwan Qu");
			locationRequest.setCity("Zhuhai Shi");
			locationRequest.setState("Guangdong Sheng");
			locationRequest.setZip("519090");
			locationRequest.setCountry("CN");
	    } else if(flag == "D" && (locationList.size() < 5 || locationList == null)) {
	    	locationRequest.setAddress("6262 S. Route 83 Suite#210");
	    	locationRequest.setCity("Willowbrook");
	    	locationRequest.setState("IL");
	    	locationRequest.setZip("60527");
	    	locationRequest.setCountry("US");
	    } else {
	    	locationRequest.setAddress(locationList.get(0));
	    	locationRequest.setCity(locationList.get(1));
	    	locationRequest.setState(locationList.get(2));
	    	locationRequest.setZip(locationList.get(3));
	    	locationRequest.setCountry(locationList.get(4));
	    }
		
		return locationRequest;
	}


	
	@Override
	public LaneLocationResponse createLane(LaneRequest lane) {
		LaneLocationResponse laneResponse =null;
		Lane upload = laneMapper.map(lane, Lane.class);
		try {
			logger.info(upload);
			// Added for Handled already existing ID.
			Lane check_lanID = laneRepository.getLaneById(lane.getLaneId());
			if(null !=check_lanID) {
				return laneResponse;				
			}else {
			upload = laneRepository.save(upload);
			laneResponse = laneMapper.map(upload, LaneLocationResponse.class);
			laneResponse.setStatus(ShipmentConstants.LANE_SUCCESS_STATUS);
			laneResponse.setMessage(ShipmentConstants.LANE_SUCCESS_MESSAGE +upload.getLaneId());
			}
		} catch (Exception e) {
			throw e;
		}
		
		return laneResponse;
	}


	@SuppressWarnings("null")
	@Override
	public LaneGroupsResponse createLaneGroup(LaneGroupsRequest laneGroup) {
		LaneGroupsResponse laneGroupsResponse = null;
		try {
			LaneGroups laneGroups = laneMapper.map(laneGroup, LaneGroups.class);	
			String laneId= laneGroup.getLaneRef();
			Lane lane = laneRepository.getLaneById(laneId);
			laneGroups.setLaneRef(lane);	
			LaneGroups upload = laneGroupsRepository.save(laneGroups);
			laneGroupsResponse = laneMapper.map(upload, LaneGroupsResponse.class);
			//set lanId in response
			laneGroupsResponse=setLanId(laneGroupsResponse,upload);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return laneGroupsResponse;
	}

	@Override
	public List<String> getAllLaneIds() {
		return laneRepository.getAllIds();
	}
	
	@Override
	public LaneAPIDeleteResponse deleteLaneById(String laneId) {
		LaneAPIDeleteResponse response = new LaneAPIDeleteResponse();
		if (laneId != null && !laneId.isEmpty()) {
			laneRepository.deleteById(laneId);
			response.setStatus("Success");
			response.setMessage("Deleted row of ID : " +laneId);
			return response;
		} else {
			response.setStatus("failure");
			response.setMessage("Validation Failed...laneId cannot be null..");
			logger.error("Validation Failed To Delete the Lane");
			return response;
		}
	}
	
	@Override
	public LaneAPICreateUpdateResponse updateLane(LaneRequest laneRequest, String laneId) {
		logger.info("Entering into LaneServiceImpl.updateLane");
		try {
			LaneAPICreateUpdateResponse commonResponse = new LaneAPICreateUpdateResponse();
			if (laneRequest.getIsActive().length()<=1 && laneRequest.getAutoTender().length()<=1 && laneRequest.getBorderCrossing().length() <=1 
					&& laneRequest.getLaneId() != null && !laneRequest.getLaneId().isEmpty()) {
				Lane lane = laneRepository.getLaneById(laneId);
				if (lane!=null){
					List<String> errorMsgList = new ArrayList<>();
					ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
					Validator validator = factory.getValidator();
					Set<ConstraintViolation<LaneRequest>> violations = validator.validate(laneRequest);
					boolean validflag = true;
					StringBuilder sb = new StringBuilder("");
					for (ConstraintViolation<LaneRequest> violation : violations) {
						String message = violation.getMessage();
						errorMsgList.add(message);
						sb.append(message);
						sb.append(";");
						validflag = false;
					}
					if (validflag) {
						Lane laneMap = laneMapper.map(laneRequest, Lane.class);
						Lane updateLane = laneRepository.save(laneMap);
						if (updateLane!=null) {
							commonResponse.setStatus("Success");
							commonResponse.setMessage("Lane Updated Successfully... for laneId : "+updateLane.getLaneId());
							logger.error("Lane Updated Successfully... PO ID : "+updateLane.getLaneId());
						} else {
							commonResponse.setStatus("Failure");
							commonResponse.setMessage("Unable to Update Lane... for laneId : "+laneId);
							logger.error("Unable to Update Lane... PO ID : "+laneId);
						}
						return commonResponse;
					} else {
						commonResponse.setStatus("Failure");
						commonResponse.setMessage("Validation Failed To Update the Lane Based on Lane ID");
						logger.error("Validation Failed To Update the Lane Based on Lane ID");
						return commonResponse;
					}
				} else {
					commonResponse.setStatus("Failure");
					commonResponse.setMessage("Lane ID not Found : "+laneId);
					logger.error("Lane ID not found : "+laneId);
					return commonResponse;
				}

			} else {
				commonResponse.setStatus("failure");
				commonResponse.setMessage("Validation Failed To Lane....Parameter isActive / autoTender / borderCrossing / defaultGroup should be of char(1)..and laneId cannot be null..");
				logger.error("Validation Failed To Update the Lane");
				return commonResponse;
			}
		} catch (Exception ex) {
			logger.error("Exception inside LaneServiceImpl.updateLane.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}
	
	public Boolean getLaneGroupItems(Set<LaneGroupsRequest> laneGroup) {
		Boolean val = true;
		if (laneGroup != null) {
			Iterator<LaneGroupsRequest> laneReq = laneGroup.iterator();
			while (laneReq.hasNext()) {
				LaneGroupsRequest laneGroupsReq = laneReq.next();
				if(laneGroupsReq.getDeafultGroup().length() <=1 && laneGroupsReq.getIsactive().length() <=1) {
					val = true;
					return val;
				}
				else {
					val = false;
					return val;
				}
			}
		}
		return val;
	}
	
	@Override
	public List<LaneResponse> getAllLanes() throws Exception {
		List<LaneResponse> response =null;
		try {
			List<Lane> laneRecords =(List<Lane>) laneRepository.findAll();
			if(null!= laneRecords && !laneRecords.isEmpty()) {
				response = laneMapper.mapAsList(laneRecords, LaneResponse.class);
			}
		}catch (Exception ex) {
			logger.error("Exception inside LaneServiceImpl.getAllLanes.Catch_Block:" + ex.getMessage());
			throw ex;
		}
		return response;
	}
	
	@Override
	public List<LaneGroupsResponse> getAllLaneGroups() throws Exception {
		List<LaneGroupsResponse> response =null;
		LaneGroupsResponse laneGroupResponse=null;
		try {
			List<LaneGroups> laneGroupRecords =(List<LaneGroups>) laneGroupsRepository.findAll();
			if(null!= laneGroupRecords && !laneGroupRecords.isEmpty()) {
				response = new ArrayList<>();
				String laneId=null;
				for(LaneGroups laneGroup:laneGroupRecords) {
					laneGroupResponse = new LaneGroupsResponse();
					laneGroupResponse=laneMapper.map(laneGroup, LaneGroupsResponse.class);
					if(null!= laneGroup.getLaneRef() && StringUtils.isNotBlank(laneGroup.getLaneRef().getLaneId())) {
					   laneId = laneGroup.getLaneRef().getLaneId();
					   laneGroupResponse.setLaneId(laneId);
				     }
					response.add(laneGroupResponse);
				}				
			}
		}catch (Exception ex) {
			logger.error("Exception inside LaneServiceImpl.getAllLaneGroups.Catch_Block:" + ex.getMessage());
			throw ex;
		}
		return response;
	}
	
	
	@Override
	public LaneGroupsResponse updateLaneGroup(LaneGroupsRequest laneGroup, String laneGroupId) {
		LaneGroupsResponse laneGroupsResponse = null;
		try {
			LaneGroups laneGroupData = laneGroupsRepository.getLaneGroupById(laneGroupId);
			laneGroupData = mapRequest(laneGroup,laneGroupData);
			LaneGroups upload = laneGroupsRepository.save(laneGroupData);
			laneGroupsResponse = laneGroupMapper.map(upload, LaneGroupsResponse.class);
			//set lanId in response
			laneGroupsResponse=setLanId(laneGroupsResponse,upload);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return laneGroupsResponse;
	}
	

	@Override
	public LaneGroupAPIDeleteResponse deleteLaneGroupById(String laneGroupId) {
		LaneGroupAPIDeleteResponse response = new LaneGroupAPIDeleteResponse();
		if (laneGroupId != null && !laneGroupId.isEmpty()) {
			laneGroupsRepository.deleteById(laneGroupId);
			response.setStatus("Success");
			response.setMessage("Deleted row of ID : " +laneGroupId);
			return response;
		} else {
			response.setStatus("failure");
			response.setMessage("Validation Failed...laneId cannot be null..");
			logger.error("Validation Failed To Delete the Lane");
			return response;
		}
	}

	/**
	 * @param laneGroupsResponse
	 * @param upload
	 * @return
	 */
	private LaneGroupsResponse setLanId(LaneGroupsResponse laneGroupsResponse, LaneGroups upload) {
		// TODO Auto-generated method stub
		if(null !=laneGroupsResponse && null != upload && null != upload.getLaneRef()) {
		String laneId = upload.getLaneRef().getLaneId();
		laneGroupsResponse.setLaneId(laneId);
		}
		return laneGroupsResponse;
	}
	private LaneGroups mapRequest(LaneGroupsRequest laneGroup, LaneGroups laneGroupData) {
		laneGroupData.setDeafultGroup(laneGroup.getDeafultGroup());
		laneGroupData.setGroupDescription(laneGroup.getGroupDescription());
		laneGroupData.setIsactive(laneGroup.getIsactive());
		laneGroupData.setMajorMode(laneGroup.getMajorMode());
		return laneGroupData;
	}
	@Override
	public List<String> getAllLaneGroupIds() {
		return laneGroupsRepository.getAllLaneGroupIds();
	}
}




//	LN0001-Zhuhai, Willowbrook
//	LN0002-Zhuhai Ave, Eastvale
