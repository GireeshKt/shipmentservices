package com.blumeglobal.shipmentmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentLegDetailsRepository;
import com.blumeglobal.shipmentmanagement.dao.specifications.CriteriaSpecification;
import com.blumeglobal.shipmentmanagement.dao.specifications.ShipmentLegTrackingSpecification;
import com.blumeglobal.shipmentmanagement.enums.ExceptionCode;
import com.blumeglobal.shipmentmanagement.model.ShipmentLegDetails;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentLegDetailsMapper;
import com.blumeglobal.shipmentmanagement.response.ShipmentLegDetailsResponse;
import com.blumeglobal.shipmentmanagement.service.IShipmentLegDetailsService;
import com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants;
import com.blumeglobal.shipmentmanagement.utils.ShipmentUtil;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

@Service
@REZ1Logger
@REZ1PerformanceLogger
public class ShipmentLegDetailsServiceImpl implements IShipmentLegDetailsService {
	@Autowired
	ShipmentLegDetailsRepository shipmentLegDetailsRepository;
	
	@Autowired
	ShipmentLegDetailsMapper shipmentLegDetailsMapper;
	
	@Autowired
	ShipmentLegTrackingSpecification shipmentLegTrackingSpecification;
	
	@Autowired
	ShipmentUtil shipmentUtil;
	
	private static final Logger logger = LogManager.getLogger(ShipmentLegDetailsServiceImpl.class);
	
	/**
	 * Search shipment leg through criteria
	 * 
	 * @param criteriaSpecList
	 *            This is used to pass the criteriaSpecList value
	 * @param operation    To define criteria operation type       
	 * @param legMode    To pass criteria leg mode            
	 * @return List Of ShipmentLegDetailsResponse  return the Shipment Leg Response 
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Override
	public List<ShipmentLegDetailsResponse> searchShipmentLegs(String criteriaSpecStr, String operation,String legMode)
			throws Exception {
		try {
		List<CriteriaSpecification> criteriaSpecList = new ArrayList<>();
		String[] splitComma = criteriaSpecStr.split("&");
		for (String criteria : splitComma) {
			Pattern pattern = Pattern.compile("(.*?)(:|<|>|=)(.*)", Pattern.UNICODE_CHARACTER_CLASS);
			Matcher matcher = pattern.matcher(criteria);
			while (matcher.find()) {
				String keyfield = matcher.group(1);
				String opr = matcher.group(2);
				String value = matcher.group(3);
				criteriaSpecList.add(new CriteriaSpecification(keyfield, opr, value));
			}
		}		
		Specification<ShipmentLegDetails> shipmentLegSpec = shipmentLegTrackingSpecification.searchShipmentLegswithOpr(criteriaSpecList, operation,legMode);
		List<ShipmentLegDetails> shipmentLegs = new ArrayList<>();
		if (shipmentLegSpec != null) {
			shipmentLegs = shipmentLegDetailsRepository.findAll(shipmentLegSpec);
		}
		if (shipmentLegs.isEmpty()) {
			throw shipmentUtil.throwDataNotFoundException(ExceptionCode.SHIPMENTNOTFOUNDCUSTOM.toString(),
					ShipmentConstants.NO_SHIPMENT_LEG_DATA_SEARCH_MESSAGE);
		} else {
			return shipmentLegDetailsMapper.mapAsList(shipmentLegs, ShipmentLegDetailsResponse.class);
		}
		} catch (Exception ex) {
			logger.error("Exception inside ShipmentLegDetailsServiceImpl.searchShipmentLegs.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}
	
	/**
	 * Search shipment leg through criteria
	 * 
	 * @param id
	 *            This is used to pass the key id value
	 * @param legMode    To pass leg mode      
	 *            
	 * @return List Of ShipmentLegDetailsResponse  return the Shipment Leg Response 
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Override
	public List<ShipmentLegDetailsResponse> findShipmentLegByModeAndIDs(String id, String legMode) throws Exception {
		try {
			if(id!=null) {
				Specification<ShipmentLegDetails> shipmentLegSpec = shipmentLegTrackingSpecification.searchShipmentLegswithIds(id, legMode);
			List<ShipmentLegDetails> shipmentLegs = shipmentLegDetailsRepository.findAll(shipmentLegSpec);
			if (shipmentLegs.isEmpty()) {
				throw shipmentUtil.throwDataNotFoundException(ExceptionCode.SHIPMENTEVENTNOTFOUNDCUSTOM.toString(),
						ShipmentConstants.NO_SHIPMENT_LEG_DATA_ID_MODE_MESSAGE);
			} else {
				return shipmentLegDetailsMapper.mapAsList(shipmentLegs, ShipmentLegDetailsResponse.class);
			}
			}
			else {
				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(),
						ShipmentConstants.SHIPMENT_WORKORDER_ID_NULL_MESSAGE);
			}
		} catch (Exception ex) {
			logger.error("Exception inside ShipmentLegDetailsServiceImpl.findShipmentLegByModeAndID.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}

	

}
