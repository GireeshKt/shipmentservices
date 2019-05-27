package com.blumeglobal.shipmentmanagement.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.blumeglobal.shipmentmanagement.dao.repositories.EventClassRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentExceptionRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentLegDetailsRepository;
import com.blumeglobal.shipmentmanagement.dao.specifications.CriteriaSpecification;
import com.blumeglobal.shipmentmanagement.dao.specifications.ShipmentLegTrackingSpecification;
import com.blumeglobal.shipmentmanagement.dao.specifications.TrackingEventSpecification;
import com.blumeglobal.shipmentmanagement.dm.ShipmentEvent;
import com.blumeglobal.shipmentmanagement.dm.ShipmentEventRepository;
import com.blumeglobal.shipmentmanagement.enums.EventCodeCompletion;
import com.blumeglobal.shipmentmanagement.enums.ExceptionCode;
import com.blumeglobal.shipmentmanagement.enums.RailBillingStatus;
import com.blumeglobal.shipmentmanagement.enums.ReportSource;
import com.blumeglobal.shipmentmanagement.enums.ShipmentMode;
//import com.blumeglobal.shipmentmanagement.kafka.producer.ShipmentEventExceptionProducer;
import com.blumeglobal.shipmentmanagement.model.EventClass;
import com.blumeglobal.shipmentmanagement.model.ShipmentException;
import com.blumeglobal.shipmentmanagement.model.ShipmentLegDetails;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentEventMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentExceptionMapper;
import com.blumeglobal.shipmentmanagement.request.RailBillingStatusUpdateRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentEventRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentExceptionListRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentExceptionRequest;
import com.blumeglobal.shipmentmanagement.response.EventResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentEventResponse;
import com.blumeglobal.shipmentmanagement.service.IShipmentEventService;
import com.blumeglobal.shipmentmanagement.service.IShipmentMilestoneService;
import com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants;
import com.blumeglobal.shipmentmanagement.utils.ShipmentUtil;
import com.blumeglobal.shipmentmanagement.validator.EventsValidator;
//import com.google.gson.Gson;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

/**
 * The ShipmentEventServiceImpl implements to manage the shipment event.
 *
 */
@Service
@REZ1Logger
@REZ1PerformanceLogger
public class ShipmentEventServiceImpl implements IShipmentEventService {

	@Autowired
	private ShipmentEventRepository shipmentEventRepository;

	@Autowired
	private ShipmentEventMapper shipmenteventMapper;

	@Autowired
	private ShipmentExceptionMapper shipmentExceptionMapper;

	@Autowired
	private TrackingEventSpecification trackSpecification;

	@Autowired
	private EventClassRepository eventClassRepository;

	@Autowired
	private ShipmentExceptionRepository shipmentExceptionRepository;

	@Autowired
	private ShipmentLegDetailsRepository shipmentLegDetailsRepository;

	@Autowired
	private ShipmentLegTrackingSpecification shipmentLegTrackingSpecification;

	@Autowired
	private IShipmentMilestoneService shipmentMilestoneService;

	@Autowired
	ShipmentUtil shipmentUtil;

	@Autowired
	EventsValidator eventsValidator;

	@Autowired
	RestTemplate restTemplate;

	//	@Autowired
	//    private ShipmentEventExceptionProducer sender;

	@Value("${client.le.railBillingUpdateAddress}")
	private String railBillingUpdateAddress;

	@Value("${kafka.isException}")
	private String isException;

	private static final Logger logger = LogManager.getLogger(ShipmentEventServiceImpl.class);

	/**
	 * Delete shipment event by shipmenteventId through API
	 * 
	 * @param shipmentEventId
	 *            This is requested shipmenteventId to be deleted
	 * @return EventResponse Shipment Event Response after delete
	 * @throws Exception
	 *             Handle service exceptions
	 */
	@Override
	public EventResponse deleteShipmentEvent(Long shipmentEventId) throws Exception {
		try {
			logger.info("Entering into ShipmentEventServiceImpl.deleteShipmentEvent");
			if (shipmentEventId == null) {
				logger.debug(
						"ShipmentEventServiceImpl.deleteShipmentEvent - Throwing Exception as shipmentEventId is required");
				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(),
						"Shipment Event Request should not be Empty for deleting Shipment Event");
			}

			ShipmentEvent shipmentEvent=shipmentEventRepository.findByshipmentEventId(shipmentEventId);
			if(shipmentEvent ==null) {
				logger.debug(
						"ShipmentEventServiceImpl.deleteShipmentEvent - Throwing Exception as No shipment event is found");
				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTNOTFOUND.toString(),
						"Shipment Event No found for deleting Shipment Event");
			}

			shipmentEventRepository.delete(shipmentEvent);

			//Process shipment event for Demo. Work order number is available in ShipmentEvent for Demo deployment
			//TODO reset actual date for shipment milestone and re-evaluate shipment milestones 
			shipmentMilestoneService.processUpdateAfterDeleteEvent(shipmentEvent);

			EventResponse eventResponse = new EventResponse();
			String id = shipmentEvent.getShipmentEventId().toString();
			eventResponse.setId(id);
			eventResponse.setStatus("deleted");
			eventResponse.setMessage("success");
			logger.debug(
					"Entering into ShipmentEventServiceImpl.deleteShipmentEvent - Mapping ShipmentEvent  to CreateEventsResponse  ");
			return eventResponse;

		} catch (Exception ex) {
			logger.error(
					"Exception inside ShipmentEventServiceImpl.deleteShipmentEvent.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * Create shipment event through API
	 * 
	 * @param createEventsRequest
	 *            This is request which has shipment event details
	 * @return ShipmentEventResponse Shipment Event Response after save
	 * @throws Exception
	 *             Handle service exceptions
	 */
	@Override
	public EventResponse createShipmentEvent(ShipmentEventRequest createEventsRequest) throws Exception {
		try {
			logger.info("Entering into ShipmentEventServiceImpl.createShipmentEvent, Arrived at: "+new Date());
			if (createEventsRequest == null) {
				if(logger.isDebugEnabled()) {
					logger.debug(
							"ShipmentEventServiceImpl.createShipmentEvent - Throwing Exception as Invalid Event Code Passed");
				}

				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(),
						"Shipment Event Request should not be Empty for Create Shipment Event");
			}
			List<String> errorMsgList = new ArrayList<>();
			StringBuilder errorMsg = new StringBuilder("");
			boolean validflag = eventsValidator.validateCreateEventsRequest(createEventsRequest, errorMsgList,
					errorMsg);

			//boolean validHub = eventsValidator.validateHub(createEventsRequest, errorMsgList, errorMsg);

			if (!validflag) {
				logger.error(
						"ShipmentEventServiceImpl.createShipmentEvent for API - ShipmentEvent Request has validation failure");
				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(),
						"Input Request has validation failure for Create Shipment Event:" + errorMsg.toString());
			}
			String status = "";
			String message = "";
			ShipmentEvent createEventsReq = shipmenteventMapper.map(createEventsRequest, ShipmentEvent.class);
			List<EventClass> eventCodes = new ArrayList<>();
			if (createEventsReq.getEventCode() != null) {
				eventCodes = eventClassRepository.findByEventCode(createEventsReq.getEventCode());
			} else if (createEventsReq.getEventName() != null) {
				eventCodes = eventClassRepository.findByEventName(createEventsReq.getEventName());
			}

			if(logger.isDebugEnabled()) {
				logger.debug("ShipmentServiceImpl.createShipment: New Shipment Data Creation");
			}

			if (eventCodes.isEmpty()) {
				logger.error(
						"ShipmentEventServiceImpl.createShipmentEvent - Throwing Exception as Invalid Event Code Passed Event Code:"
								+ createEventsReq.getEventCode());
				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(),
						createEventsReq.getEventCode() + "/" + createEventsReq.getEventName()
						+ " Event code/ EventName is not available");
			} else {
				createEventsReq.setEventCode(eventCodes.get(0).getEventCode());
				createEventsReq.setEventName(eventCodes.get(0).getEventName());
			}
			if(logger.isDebugEnabled()) {
				logger.debug("Entering into ShipmentEventServiceImpl.createShipmentEvent - Event Code is Valid ");
			}


			createEventsReq.setCreateDate(new Date());			
			if (createEventsReq.getWorkOrderNumber() == null || createEventsReq.getWorkOrderNumber().isEmpty()) {
				ShipmentLegDetails shipmentWO = getWorkOrderstoLink(createEventsReq,
						createEventsRequest.getReportSource());
				if (shipmentWO != null) {
					createEventsReq.setIsLegFound(1);
					createEventsReq.setWorkOrderNumber(shipmentWO.getWoBookingNumber());
					status = ShipmentConstants.SUCCESS;
					message =ShipmentConstants.EVENTS_ADD_SUCCESS_MESSAGE;
				} else {
					createEventsReq.setIsLegFound(0);
					status = ShipmentConstants.WARNING;
					message =ShipmentConstants.EVENTS_ADD_SUCCESS_WARNING_MESSAGE;
				}

			}
			else {
			    //TODO need verify this - workorder number must have equipmentOnWorkorder, pickup and delivery stops
				List<ShipmentLegDetails> shipmentLegDetailsList=shipmentLegDetailsRepository.findByWoBookingNumberOrderByWoBookingDateDesc(createEventsReq.getWorkOrderNumber());
				if(shipmentLegDetailsList.isEmpty()) {
					createEventsReq.setIsLegFound(0);
					//createEventsReq.setWorkOrderNumber(null);
					status = ShipmentConstants.WARNING;
					message =ShipmentConstants.EVENTS_ADD_SUCCESS_WARNING_MESSAGE;
				}			
			}

			if (ReportSource.isOceanEvent(createEventsRequest.getReportSource())) {
				oceanEventsValidateUpdate(createEventsRequest, createEventsReq);
			} else if (ReportSource.isRailEvent(createEventsRequest.getReportSource())) {
				rampAndCLMEventsUpdate(createEventsRequest, createEventsReq);
			}else if(ReportSource.isDrayEvent(createEventsRequest.getReportSource())) {
				drayEventsUpdate(createEventsRequest, createEventsReq);
			}

			createEventsReq.setUpdatedDate(new Date());

			ShipmentEvent upload = shipmentEventRepository.save(createEventsReq);

			//sends event request to kafka producer for exception evaluation
			if(isException.equalsIgnoreCase("true")) {
				//logger.info("kafka call flag is enabled ..");
				//kafkaCallEventEvaluator(createEventsReq);
			}else {
				logger.info("kafka call flag is disabled ..");
			}
			shipmentMilestoneService.processShipmentEvent(upload);

			if (upload.hashCode() > 0) {
				if(logger.isDebugEnabled()) {
					logger.debug("Entering into ShipmentEventServiceImpl.createShipmentEvent - Data Inserted Successfully");
				}

				EventResponse eventResponse = new EventResponse();
				String id = upload.getShipmentEventId().toString();
				eventResponse.setId(id);
				eventResponse.setStatus(status);
				eventResponse.setMessage(message);
				///ShipmentEventResponse createEventsRes = shipmenteventMapper.map(upload, ShipmentEventResponse.class);
				if(logger.isDebugEnabled()) {
					logger.debug(
							"Entering into ShipmentEventServiceImpl.createShipmentEvent - Mapping ShipmentEvent  to CreateEventsResponse  ");
				}

				return eventResponse;
			} else {
				if(logger.isDebugEnabled()) {
					logger.debug(
							"ShipmentEventServiceImpl.createShipmentEvent - Throwing Exception as Due to some reason Data not inserted ");
				}

				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(),
						"Invalid Request data for Save Operation");
			}

		} catch (Exception ex) {
			logger.error(
					"Exception inside ShipmentEventServiceImpl.createShipmentEvent.Catch_Block:" + ex.getMessage(), ex);
			throw ex;
		}
	}

	public void kafkaCallEventEvaluator(ShipmentEvent createEventsReq) {
		//sends event request to kafka producer for exception evaluation
		if(createEventsReq != null) {
			logger.info("sending event data to ShipmentEventEvaluator producer..");
			//Gson gson = new Gson();
			//String jsonShipmentEvents = gson.toJson(createEventsReq);
			//sender.send(jsonShipmentEvents);
		}
	}
	public EventResponse createShipmentException(ShipmentExceptionRequest createExceptionRequest)throws Exception {
		try {
			logger.info("Entering into ShipmentEventServiceImpl.createShipmentEvent");
			if (createExceptionRequest == null) {
				if(logger.isDebugEnabled()) {
					logger.debug( "ShipmentEventServiceImpl.createException - Throwing Exception as Invalid Event Code Passed");
				}

				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(), "Shipment Exception Request should not be Empty for Create Exception Event");
			}
			ShipmentException createExceptionReq = shipmentExceptionMapper.map(createExceptionRequest, ShipmentException.class);
			ShipmentException upload = shipmentExceptionRepository.save(createExceptionReq);
			EventResponse exceptionResponse = new EventResponse();
			exceptionResponse.setId(upload.getShipmentExceptionId().toString());
			exceptionResponse.setMessage("Success");
			exceptionResponse.setStatus("Done");
			return exceptionResponse;
		} catch (Exception e) {
			throw e;
		}
	}

	private ShipmentLegDetails getWorkOrderstoLink(ShipmentEvent shipmentEvents, String reportSource) {
		ShipmentLegDetails shipmentLeg = null;
		String legMode = "ALL";
		if (ReportSource.isRailEvent(reportSource)) {
			legMode = ShipmentMode.RAIL.toString();
		} else if (ReportSource.isDrayEvent(reportSource)) {
			legMode = ShipmentMode.DRAY.toString();
		}
		Specification<ShipmentLegDetails> shipmentLegSpec = shipmentLegTrackingSpecification
				.searchWorkOrderWithEvents(shipmentEvents, legMode);
		List<ShipmentLegDetails> shipmentLegs = shipmentLegDetailsRepository.findAll(shipmentLegSpec);
		if (!shipmentLegs.isEmpty()) {
			shipmentLeg = shipmentLegs.get(0);
			if (ShipmentMode.isRailMode(shipmentLeg.getLegMode())) {
				updateWoStatusRail(shipmentEvents, shipmentLeg);
			}
		}
		return shipmentLeg;
	}


	private void updateWoStatusRail(ShipmentEvent shipmentEvents, ShipmentLegDetails legDetails) {
		String city = shipmentEvents.getCity();
		String postalCode = shipmentEvents.getPostalCode();
		String eventCode = shipmentEvents.getEventCode();
		Long railBookingId = legDetails.getWoBookingId();
		String legDestinatonCity = legDetails.getDestinationCity();
		String legDestinatonZipcode = legDetails.getDestinationZipCode();
		RailBillingStatusUpdateRequest railBillingStatusUpdateRequest = new RailBillingStatusUpdateRequest();
		if (EventCodeCompletion.isValidCodeForRailWOCompletion(eventCode)) {
			if(legDestinatonCity!=null&&legDestinatonCity.equalsIgnoreCase(city)&&legDestinatonZipcode!=null&&legDestinatonZipcode.equalsIgnoreCase(postalCode)) {
				railBillingStatusUpdateRequest.setStatus(RailBillingStatus.Completed.getFullName());
			}
		}else {
			railBillingStatusUpdateRequest.setStatus(RailBillingStatus.Active.getFullName());
		}
		if(railBillingStatusUpdateRequest.getStatus()!=null) {
			String url = railBillingUpdateAddress + railBookingId;
			try {
				restTemplate.put(url, railBillingStatusUpdateRequest, Object.class);

			} catch (Exception ex) {
				logger.error(
						"Exception inside ShipmentEventServiceImpltCreateShipmentEvents.updateWoStatusRailCatch_Block:"
								+ ex.getMessage());				
			}
		}		
	}

	private void drayEventsUpdate(ShipmentEventRequest shipmentEventRequest, ShipmentEvent shipmentEvents)
			throws Exception {
		if(shipmentEvents.getWorkOrderNumber() != null  && shipmentEvents.getEventCode()!=null && shipmentEvents.getReportSource()!=null)
		{
			List<ShipmentEvent> existingShipmentEvents = shipmentEventRepository
					.findByWorkOrderNumberAndEventCodeAndReportSource(shipmentEvents.getWorkOrderNumber(),shipmentEvents.getEventCode(),shipmentEvents.getReportSource());
			if (!existingShipmentEvents.isEmpty()) {
				ShipmentEvent shipmentEventUpdate = existingShipmentEvents.get(0);
				shipmentEvents.setShipmentEventId(shipmentEventUpdate.getShipmentEventId());
				shipmentEvents.setCreateDate(shipmentEventUpdate.getCreateDate());
				shipmentEvents.setCreatedBy(shipmentEventUpdate.getCreatedBy());
			}
		}
	}

	private void rampAndCLMEventsUpdate(ShipmentEventRequest shipmentEventRequest, ShipmentEvent shipmentEvents)
			throws Exception {
		if(shipmentEventRequest.getResolvedEventId() != null  &&  !StringUtils.isEmpty(shipmentEventRequest.getResolvedEventSource()))
		{
			List<ShipmentEvent> existingShipmentEvents = shipmentEventRepository
					.findByResolvedEventIdAndResolvedEventSource(shipmentEventRequest.getResolvedEventId(),
							shipmentEventRequest.getResolvedEventSource());
			if (!existingShipmentEvents.isEmpty()) {
				ShipmentEvent shipmentEventUpdate = existingShipmentEvents.get(0);
				shipmentEvents.setShipmentEventId(shipmentEventUpdate.getShipmentEventId());
				shipmentEvents.setCreateDate(shipmentEventUpdate.getCreateDate());
				shipmentEvents.setCreatedBy(shipmentEventUpdate.getCreatedBy());
			}
		}
	}

	private void oceanEventsValidateUpdate(ShipmentEventRequest shipmentEventRequest, ShipmentEvent shipmentEvents)
			throws Exception {
		String vessel = shipmentEventRequest.getVessel();
		String voyage = shipmentEventRequest.getVoyageNumber();
		String eventCode = shipmentEventRequest.getEventCode();
		String equipmentNumber = shipmentEventRequest.getUnitId();
		String city = shipmentEventRequest.getCity();
		String country = shipmentEventRequest.getCountry();
		String postalCode = shipmentEventRequest.getPostalCode();
		String codeType = shipmentEventRequest.getCodeType();
		if (!ShipmentUtil.isNullString(vessel) && !ShipmentUtil.isNullString(voyage)
				&& !ShipmentUtil.isNullString(eventCode) && !ShipmentUtil.isNullString(equipmentNumber)) {
			if(!ShipmentUtil.isNullString(codeType) && codeType.equalsIgnoreCase(ShipmentConstants.CODE_TYPE)
					|| !ShipmentUtil.isNullString(city) && !ShipmentUtil.isNullString(country) 
					&& !ShipmentUtil.isNullString(postalCode)) {
				
					List<ShipmentEvent> existingShipmentEvent = shipmentEventRepository
							.findByVesselAndVoyageNumberAndCityAndCountryAndEventCodeAndUnitId(vessel, voyage, city, country,
									eventCode, equipmentNumber);
					if (!existingShipmentEvent.isEmpty()) {
						ShipmentEvent shipmentEventUpdate = existingShipmentEvent.get(0);
						shipmentEvents.setShipmentEventId(shipmentEventUpdate.getShipmentEventId());
						shipmentEvents.setCreateDate(shipmentEventUpdate.getCreateDate());
						shipmentEvents.setCreatedBy(shipmentEventUpdate.getCreatedBy());
					}
			}
			else {
				logger.error(
						"ShipmentEventServiceImpl.createShipmentEvent for API - Shipment Number and Order Number should not be blank as "
								 + codeType + "and codeType value is not equal to " + ShipmentConstants.CODE_TYPE + " OR " + city + " and " + country + " and " + postalCode);
				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTINVALIDCUSTOM.toString(),
						"codeType should not be blank And codeType value should be equal to "+ ShipmentConstants.CODE_TYPE +" OR City, Country, PostalCode should not be blank");
			}
		} else {
			logger.error(
					"ShipmentEventServiceImpl.createShipmentEvent for API - Shipment Number and Order Number should not be blank as "
							+ vessel + " and " + voyage + " and " + eventCode + " and " + equipmentNumber);
			throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTINVALIDCUSTOM.toString(),
					"Vessel, Voyage, Event Code, Equipment Number should not be blank");
		}
	}

	/**
	 * Retrieve shipment events through criteria
	 * 
	 * @param criteriaSpecStr
	 *            Passing a criteria
	 * @return List<ShipmentEventResponse> List of Shipment Events Response after
	 *         searching
	 * @throws Exception
	 *             Handle service exceptions
	 */
	@Override
	public List<ShipmentEventResponse> getSearchShipmentEvents(String criteriaSpecStr) throws Exception {
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
					logger.info("keyfield:" + keyfield + ",opr:" + opr + ",value" + value);
					criteriaSpecList.add(new CriteriaSpecification(keyfield, opr, value));
				}
			}
			Specification<ShipmentEvent> shipmentEventSpec = trackSpecification
					.searchANDSpecification(criteriaSpecList);
			List<ShipmentEvent> shipmentEvent = new ArrayList<>();
			if (shipmentEventSpec != null) {
				shipmentEvent = shipmentEventRepository.findAll(shipmentEventSpec);
			}
			if (shipmentEvent.isEmpty()) {
				throw shipmentUtil.throwDataNotFoundException(ExceptionCode.SHIPMENTEVENTNOTFOUNDCUSTOM.toString(),
						"No Shipment Event Data is Available for the Shipment");
			} else {
				return shipmenteventMapper.mapAsList(shipmentEvent, ShipmentEventResponse.class);
			}

		} catch (Exception ex) {
			logger.error(
					"Exception inside ShipmentEventServiceImpl.createShipmentEvent.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * Retrieve shipment event data by shipmentNumber or houseBill or masterBill or
	 * onHand
	 * 
	 * @param id
	 *            This is shipment shipmentNumber or houseBill or masterBill or
	 *            onHand
	 * 
	 * @return List<ShipmentEventResponse> Shipment Event Response
	 * @throws Exception
	 *             Handle service exceptions
	 */
	@Override
	public List<ShipmentEventResponse> getShipmentEvents(String id) throws Exception {
		try {
			if (id != null) {
				Specification<ShipmentEvent> shipmentEventSpec = trackSpecification.getTrackingEventsByReferenceIds(id);
				List<ShipmentEvent> events = shipmentEventRepository.findAll(shipmentEventSpec);
				if (events.isEmpty()) {
					throw shipmentUtil.throwDataNotFoundException(ExceptionCode.SHIPMENTEVENTNOTFOUNDCUSTOM.toString(),
							"No Shipment Event Data is Available for the Shipment");
				} else {
					return shipmenteventMapper.mapAsList(events, ShipmentEventResponse.class);
				}
			} else {
				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(),
						"Shipment References should not be null");
			}
		} catch (Exception ex) {
			logger.error("Exception inside ShipmentEventServiceImpl.getShipmentEvents.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}

	@Override
	public EventResponse createShipmentExceptions(ShipmentExceptionListRequest createExceptionListRequest) {
		try {
			logger.info("Entering into ShipmentEventServiceImpl.createShipmentExceptions");
			if (createExceptionListRequest == null||createExceptionListRequest.getShipmentExceptions()==null
					||createExceptionListRequest.getShipmentExceptions().isEmpty()) {
				if(logger.isDebugEnabled()) {
					logger.debug( "ShipmentEventServiceImpl.createShipmentExceptions - Throwing Exception as No Shipment Exceptions data Passed");
				}

				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(), "Shipment Exception Request should not be Empty for Create Exception Event");
			}
			String ids="";
			for(ShipmentExceptionRequest createExceptionRequest:createExceptionListRequest.getShipmentExceptions()) {
				ShipmentException createExceptionReq = shipmentExceptionMapper.map(createExceptionRequest, ShipmentException.class);
				ShipmentException upload = shipmentExceptionRepository.save(createExceptionReq);
				ids+=","+upload.getShipmentExceptionId().toString();
			}
			EventResponse exceptionResponse = new EventResponse();
			exceptionResponse.setId(ids.replaceFirst(",", ""));
			exceptionResponse.setMessage("Success");
			exceptionResponse.setStatus("Done");
			return exceptionResponse;
		} catch (Exception e) {
			throw e;
		}
	}

}
