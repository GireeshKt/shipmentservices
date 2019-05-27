package com.blumeglobal.shipmentmanagement.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentWorkOrderRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentWorkOrderRepository1;
import com.blumeglobal.shipmentmanagement.dao.specifications.CriteriaSpecification;
import com.blumeglobal.shipmentmanagement.dao.specifications.ShipmentWOSpecification;
import com.blumeglobal.shipmentmanagement.enums.ExceptionCode;
import com.blumeglobal.shipmentmanagement.enums.OfficeType;
import com.blumeglobal.shipmentmanagement.exceptions.BusinessException;
import com.blumeglobal.shipmentmanagement.exceptions.DataException;
import com.blumeglobal.shipmentmanagement.exceptions.DataNotFoundException;
import com.blumeglobal.shipmentmanagement.exceptions.InValidDataException;
import com.blumeglobal.shipmentmanagement.model.ShipmentDetails;
import com.blumeglobal.shipmentmanagement.model.ShipmentWO;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentMapper;
import com.blumeglobal.shipmentmanagement.response.ShipmentWOResponse;
import com.blumeglobal.shipmentmanagement.service.IShipmentWOService;
import com.blumeglobal.shipmentmanagement.service.helper.PaginationHelper;
import com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants;
import com.blumeglobal.shipmentmanagement.utils.ShipmentUtil;


@Service
public class ShipmentWOServiceImpl implements IShipmentWOService {

	@Autowired
	ShipmentWorkOrderRepository shipmentWorkOrderRepository;
	
	@Autowired
	ShipmentWorkOrderRepository1 shipmentWorkOrderRepository1;

	@Autowired
	ShipmentWOSpecification shipmentWOSpecification;

	@Autowired
	PaginationHelper paginationHelper;

	@Autowired
	ShipmentUtil shipmentUtil;

	@Autowired
	ShipmentMapper shipmentMapper;

	private static final Logger logger = LogManager.getLogger(ShipmentWOServiceImpl.class);

	@Override
	public List<ShipmentWO> shipmentList(int startRow, int endRow, String officeIds, String officeType) {
		List<ShipmentWO> shipmentDetails = new ArrayList<>();
		
		// Handling Multiple officeids  
		if (officeIds == null || officeIds.isEmpty()) {

			if (logger.isDebugEnabled()) {
				logger.debug("exiting getworkOrders()");
			}
			throw new DataNotFoundException(ShipmentConstants.EMPTY_OFFICE_ID_MESSAGE);
		}
		
		List<String> officeIdsinString = null;
		if (officeIds != null && !officeIds.isEmpty()) {
			try {
				officeIdsinString = Arrays.asList(officeIds.split(","));
				if (officeIdsinString.isEmpty()) {

					if (logger.isDebugEnabled()) {
						logger.debug("exiting getshipment list");
					}
					throw new InValidDataException(ShipmentConstants.INVALID_FACILITY_ID_MESSAGE);
				}
			} catch (Exception ex) {

				logger.error("error occured ", ex.getMessage());
				if (logger.isDebugEnabled()) {
					logger.debug("exiting getshipment list");
				}
				throw new DataException(ShipmentConstants.UNABLE_TO_GET_WO_MESSAGE + ex.getMessage());
			}

		}
		List<Long> officeId = new ArrayList<Long>();

		try {
			if (officeIdsinString != null && officeIdsinString.size() > 0) {
				for (String rid : officeIdsinString) {
					officeId.add(Long.valueOf(rid));
				}
			}
		} catch (NumberFormatException ex) {

			if (logger.isDebugEnabled()) {
				logger.debug("exiting getworkOrders()");
			}
			// return Response.serverError().status(Status.BAD_REQUEST).entity("Invalid
			// facilityIds").build();
			throw new DataException(ShipmentConstants.INVALID_FACILITY_ID_MESSAGE);
		}

		// only date older up to 90 days will be fetched.
		Date fromDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(fromDate);
		c.add(Calendar.DATE, -90);
		fromDate = c.getTime();

		if (OfficeType.Originator.toString().equalsIgnoreCase(officeType)) {
			shipmentDetails = shipmentWorkOrderRepository.getShipmentDetailsByOriginatorId(officeId, startRow, endRow,
					new Date(), fromDate);
		} else if (OfficeType.Receiver.toString().equalsIgnoreCase(officeType)) {
			shipmentDetails = shipmentWorkOrderRepository.getShipmentDetailsByReceiverId(officeId, startRow, endRow, new Date(), fromDate);
		} else {
			throw new BusinessException(ShipmentConstants.INVALID_OFFICE_TYPE_MESSAGE);
		}

		if (shipmentDetails != null) {
			return shipmentDetails;
		} else {
			throw new DataNotFoundException(ShipmentConstants.INVALID_OFFICE_TYPE_MESSAGE);
		}
	}

	@Override
	public List<ShipmentWOResponse> searchShipment(String criteriaSpecStr) throws Exception {
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
			Specification<ShipmentWO> shipmentSpec = shipmentWOSpecification.viewShipmentWO(criteriaSpecList, "OR");
			List<ShipmentWO> shipments = new ArrayList<>();
			if (shipmentSpec != null) {
				shipments = shipmentWorkOrderRepository.findAll(shipmentSpec);
				if (shipments.isEmpty() || shipments != null) {
					ShipmentWO shipment = shipments.get(0);
					shipments.clear();
					shipments.add(shipment);
				}
			}
			return shipmentMapper.mapAsList(shipments, ShipmentWOResponse.class);
		} catch (Exception ex) {
			logger.error("Exception inside ShipmentWOServiceImpl.searchShipmentLegs.Catch_Block:" + ex.getMessage());
			throw shipmentUtil.throwDataNotFoundException(ExceptionCode.SHIPMENTNOTFOUNDCUSTOM.toString(),
					ShipmentConstants.SHIPMENT_DATA_NOT_AVAILABLE_FOR_SEARCH_MESSAGE);
		}
	}

	@Override
	public List<ShipmentWO> getShipmentByDateRange(String fromDate, String toDate, Long officeId, String officeType)
			throws Exception {

		List<ShipmentWO> shipmentDetails = new ArrayList<>();

		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
		Date fromDt = dateFormatter.parse(fromDate);
		Date toDt = dateFormatter.parse(toDate);

		if (OfficeType.Originator.toString().equalsIgnoreCase(officeType)) {
			shipmentDetails = shipmentWorkOrderRepository.getShipmentByDateRangeForOriginator(fromDt, toDt, officeId);
		} else if (OfficeType.Receiver.toString().equalsIgnoreCase(officeType)) {
			shipmentDetails = shipmentWorkOrderRepository.getShipmentByDateRangeForReceiver(fromDt, toDt, officeId);
		} else {
			throw new BusinessException(ShipmentConstants.INVALID_OFFICE_TYPE_MESSAGE);
		}
		if (shipmentDetails != null) {
			return shipmentDetails;
		} else {
			throw new DataNotFoundException(ShipmentConstants.NO_RESULT_FOUND_MESSAGE);
		}
	}
	
	public List<ShipmentDetails> shipmentDetailsList(int startRow, int endRow, String officeIds,String officeType) {
List<ShipmentDetails> shipmentDetails = new ArrayList<>();
		
		// Handling Multiple officeids  
		if (officeIds == null || officeIds.isEmpty()) {

			if (logger.isDebugEnabled()) {
				logger.debug("exiting getworkOrders()");
			}
			throw new DataNotFoundException(ShipmentConstants.EMPTY_OFFICE_ID_MESSAGE);
		}
		
		List<String> officeIdsinString = null;
		if (officeIds != null && !officeIds.isEmpty()) {
			try {
				officeIdsinString = Arrays.asList(officeIds.split(","));
				if (officeIdsinString.isEmpty()) {

					if (logger.isDebugEnabled()) {
						logger.debug("exiting getshipment list");
					}
					throw new InValidDataException(ShipmentConstants.INVALID_FACILITY_ID_MESSAGE);
				}
			} catch (Exception ex) {

				logger.error("error occured ", ex.getMessage());
				if (logger.isDebugEnabled()) {
					logger.debug("exiting getshipment list");
				}
				throw new DataException(ShipmentConstants.UNABLE_TO_GET_WO_MESSAGE + ex.getMessage());
			}

		}
		List<Long> officeId = new ArrayList<Long>();

		try {
			if (officeIdsinString != null && officeIdsinString.size() > 0) {
				for (String rid : officeIdsinString) {
					officeId.add(Long.valueOf(rid));
				}
			}
		} catch (NumberFormatException ex) {

			if (logger.isDebugEnabled()) {
				logger.debug("exiting getworkOrders()");
			}
			// return Response.serverError().status(Status.BAD_REQUEST).entity("Invalid
			// facilityIds").build();
			throw new DataException(ShipmentConstants.INVALID_FACILITY_ID_MESSAGE);
		}

		// only date older up to 90 days will be fetched.
//		Date fromDate = new Date();
//		Calendar c = Calendar.getInstance();
//		c.setTime(fromDate);
//		c.add(Calendar.DATE, -90);
//		fromDate = c.getTime();

		if (OfficeType.Originator.toString().equalsIgnoreCase(officeType)) {
			shipmentDetails = shipmentWorkOrderRepository1.getShipmentDetailsByOriginatorId(officeId, startRow, endRow);
		}
		 else if (OfficeType.Receiver.toString().equalsIgnoreCase(officeType)) {
			shipmentDetails = shipmentWorkOrderRepository1.getShipmentDetailsByReceiverId(officeId, startRow, endRow);
		} 
			else {
			throw new BusinessException(ShipmentConstants.INVALID_OFFICE_TYPE_MESSAGE);
		}

		if (shipmentDetails != null) {
			return shipmentDetails;
		} else {
			throw new DataNotFoundException(ShipmentConstants.NO_RESULT_FOUND_MESSAGE);
		}
		
	}
	

}
