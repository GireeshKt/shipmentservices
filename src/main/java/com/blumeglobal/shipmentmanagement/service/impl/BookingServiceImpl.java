package com.blumeglobal.shipmentmanagement.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.blumeglobal.shipmentmanagement.dao.repositories.BookingDetailsRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.BookingRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.LaneGroupsRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.LaneRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.RoutingRepository;
import com.blumeglobal.shipmentmanagement.enums.ExceptionCode;
import com.blumeglobal.shipmentmanagement.model.Booking;
import com.blumeglobal.shipmentmanagement.model.BookingDetails;
import com.blumeglobal.shipmentmanagement.model.Lane;
import com.blumeglobal.shipmentmanagement.model.LaneGroups;
import com.blumeglobal.shipmentmanagement.model.Routing;
import com.blumeglobal.shipmentmanagement.model.mapper.BookingMapper;
import com.blumeglobal.shipmentmanagement.request.BookingDetailsRequest;
import com.blumeglobal.shipmentmanagement.request.BookingFrontRequest;
import com.blumeglobal.shipmentmanagement.request.BookingRequest;
import com.blumeglobal.shipmentmanagement.request.EquipmentListRequest;
import com.blumeglobal.shipmentmanagement.request.FacilityRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentWorkOrderRequest;
import com.blumeglobal.shipmentmanagement.request.StopListRequest;
import com.blumeglobal.shipmentmanagement.request.WORequest;
import com.blumeglobal.shipmentmanagement.request.WorkOrderRequest;
import com.blumeglobal.shipmentmanagement.request.tempReq;
import com.blumeglobal.shipmentmanagement.response.BookingResponse;
import com.blumeglobal.shipmentmanagement.response.RoutingResponse;
import com.blumeglobal.shipmentmanagement.service.IBookingService;
import com.blumeglobal.shipmentmanagement.utils.ShipmentUtil;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

import org.apache.http.conn.ssl.TrustStrategy;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;



import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


/**
 * The BookingServiceImpl implements to manage the Booking.
 *
 */
@Service
@REZ1Logger
@REZ1PerformanceLogger
public class BookingServiceImpl implements IBookingService {
	private static final Logger logger = LogManager.getLogger(BookingServiceImpl.class);
	
	
	@Autowired
	ShipmentUtil shipmentUtil;
	
	
	@Autowired
	BookingMapper bookingMapperObj;
	
	
	@Autowired
	BookingRepository bookingRepository; 
	
	
	@Autowired
	BookingDetailsRepository bookingDetailsRepository;
	
	
	@Autowired
	LaneRepository laneRepository;
	
	
	@Autowired
	LaneGroupsRepository laneGroupsRepository;
	
	
	@Autowired
	RoutingRepository routingRepository;
	
	@Value("${client.wo.workOrderURL}")
	private String woCreateURL;
	
	
	@Value("${client.wo.authenticationapiKey}")
	private String woCreateAPIKey;
	
	
	
	
	//To maintain workOrder Date
	Date woPickupDate = new Date();
	
	
	
	
	
	/**
	 * This BookingServiceImpl.createBookingAPI method is used to create booking using BookingRequest request
	 * 
	 * @param createBookingFrontRequest
	 *            This is used to pass BookingFrontRequest request data
	 * @return BookingResponse upload To get response of BookingResponse
	 * @throws Exception
	 *             Handle the exceptions
	 */
	public BookingResponse createBookingAPI(BookingFrontRequest createBookingFrontRequest) throws Exception {
		try {
			logger.info("Entering into BookingServiceImpl.createBookingAPI");
			createBookingFrontRequest.setDestination_locationId("string");
			createBookingFrontRequest.setOrigin_locationId("string");
			createBookingFrontRequest.setBookingDate(new Date());
			createBookingFrontRequest.setCreatedBy("test.oa");
			
			BookingRequest bookingRequest = bookingMapperObj.map(createBookingFrontRequest, BookingRequest.class);
			bookingRequest.setBookingId(new Long(0));
			
			//Add bookingDetails into BookingRequest
			BookingDetailsRequest bookingDetailsRequest = bookingMapperObj.map(createBookingFrontRequest, BookingDetailsRequest.class);
			List<BookingDetailsRequest> bookingDetailsObj = new ArrayList<>();
			bookingDetailsRequest.setBookingDetailsId(new Long(0));
			bookingDetailsObj.add(bookingDetailsRequest);
			bookingRequest.setBookingDetails(bookingDetailsObj);
			
			
			return createBooking(bookingRequest);
			
		} catch (Exception ex) {
			logger.error("Exception inside BookingServiceImpl.createBookingAPI.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}
	
	
	
	
	/**
	 * This BookingServiceImpl.createBooking method is used to create booking using BookingRequest request
	 * 
	 * @param createBookingRequest
	 *            This is used to pass BookingRequest request data
	 * @return BookingResponse upload To get response of BookingResponse
	 * @throws Exception
	 *             Handle the exceptions
	 */
	public BookingResponse createBooking(BookingRequest createBookingRequest) throws Exception {
		try {
			logger.info("Entering into BookingServiceImpl.createBooking");
			
			if (createBookingRequest == null) {
				logger.debug("BookingServiceImpl.createBooking - Throwing Exception as Invalid Event Code Passed");
				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(), 
						"Booking Request should not be Empty for Create Booking");
			}
			
			String status = "done";
			String message = "complete";
			
			Booking booking = insertBooking(createBookingRequest);
			
			
			logger.debug("Entering into BookingServiceImpl.createBooking - Data Inserted Successfully");
			
			BookingResponse bookingResponse = new BookingResponse();
			bookingResponse.setId(booking.getBookingId().toString());
			bookingResponse.setStatus(status);
			bookingResponse.setMessage(message);
			
			logger.debug("Entering into BookingServiceImpl.createBooking - Mapping Shipment to CreateResponse");
			
			
			logger.debug("Inside BookingController.callWorkOrderAPI - Call WO API");
			callWorkOrderAPI(booking);
			logger.debug("Outside BookingController.callWorkOrderAPI - Call WO API");
			
			
			return bookingResponse;
			
		} catch (Exception ex) {
			logger.error("Exception inside BookingServiceImpl.createBooking.Catch_Block:" + ex.getMessage());
			throw ex;
		}
		
	}

	
	
	
	/**
	 * This BookingServiceImpl.insertBooking method is used to insert into booking using BookingRequest request
	 * 
	 * @param bookingRequestObj
	 *            This is used to pass BookingRequest request data
	 * @return Booking upload To get response of Booking
	 * @throws Exception
	 *             Handle the exceptions
	 */
	public Booking insertBooking(BookingRequest bookingRequestObj) throws Exception {
		try {
			logger.info("Entering into BookingServiceImpl.insertBooking");
			
			Booking booking = bookingMapperObj.map(bookingRequestObj, Booking.class);
			
			booking.setHazmat(shipmentUtil.parseBooleanValue(bookingRequestObj.getHazmat()));
			booking.setEmptyConatinerNeeded(shipmentUtil.parseBooleanValue(bookingRequestObj.getEmptyConatinerNeeded()));
			
			setBookingDetails(bookingRequestObj, booking);
			
			return bookingRepository.save(booking);
			
		} catch (Exception ex) {
			logger.error("Exception inside BookingServiceImpl.insertBooking.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}
	
	
	
	
	/**
	 * This BookingServiceImpl.setBookingDetails method is used to insert into BookingDetails using BookingRequest request
	 * 
	 * @param bookingRequestObj
	 *            This is used to pass BookingRequest request data
	 * @throws Exception
	 *             Handle the exceptions
	 */
	public void setBookingDetails(BookingRequest bookingRequestObj, Booking bookingObj) throws Exception {
		try {
			logger.info("Entering into BookingServiceImpl.setBookingDetails");
			
			
			List<BookingDetailsRequest> bookingDetailsObj = bookingRequestObj.getBookingDetails();
			List<BookingDetails> setBookingDetailsObj = new ArrayList<>();
			
			if(bookingDetailsObj != null) {
			
				for(BookingDetailsRequest bookingDetailsRequestObj : bookingDetailsObj) {
					
					//get lane Id
					Lane lane = getLaneId(bookingObj.getOrigin_locationId(), bookingObj.getDestination_locationId());
		
					if (lane == null) {
						logger.info("BookingServiceImpl.setBookingDetails - Throwing Exception as Lane object is NULL");
						throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(), 
								"There is no record in Lane which Origin Location or Destination Location match with Booking field values.");
					}
					
					
					//get LaneGroup Id
					LaneGroups laneGroup = getLaneGroupId(lane.getLaneId());
					
					if (laneGroup == null) {
						logger.info("BookingServiceImpl.setBookingDetails - Throwing Exception as LaneGroup object is NULL");
						throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(), 
								"There is no record in LaneGroup which contain LaneId: " + lane.getLaneId());
					}
					
					
					//get routeId
					List<Routing> routingRecords = getRoutingRecords(laneGroup.getLanegroupId());
					
					if (routingRecords == null) {
						logger.info("BookingServiceImpl.setBookingDetails - Throwing Exception as Routing object is NULL");
						throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(), 
								"There is no record in Routing which contain LaneGroupId: " + laneGroup.getLanegroupId());
					}
					
					
					for(Routing routingObj: routingRecords) {
						BookingDetails bookingDetails = bookingMapperObj.map(bookingDetailsRequestObj, BookingDetails.class);
						
						bookingDetails.setBookingLink(bookingObj);
						bookingDetails.setLaneLink(lane);
						bookingDetails.setLaneGroupsLink(laneGroup);
						bookingDetails.setRoutingLink(routingObj);
						
						bookingDetails.setRouteOriginLocationId(routingObj.getOriginLocationId());
						bookingDetails.setRouteDestinationLocationId(routingObj.getDestinationLocationId());
						bookingDetails.setRouteModeOfTransport(routingObj.getModeOfTransport());
						bookingDetails.setDefaultCarrier(routingObj.getDefault_carrier());
						
						setBookingDetailsObj.add(bookingDetails);
					}
					
				}
			}
			
			bookingObj.setBookingDetails(setBookingDetailsObj);
			
		} catch (Exception ex) {
			logger.error("Exception inside BookingServiceImpl.setBookingDetails.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}
	
	
	
	
	/**
	 * This BookingServiceImpl.getLaneId method is used to fetch laneId from Lane table
	 * 
	 * @return LaneId as String 
	 * @throws Exception
	 *             Handle the exceptions
	 */
	public Lane getLaneId(String origin_locationId, String destination_locationId) throws Exception {
		try {
			logger.info("Entering into BookingServiceImpl.getLaneId");
			
			List<Lane> laneRecords = laneRepository.getLaneId(origin_locationId, destination_locationId);
			
			if(laneRecords.size() == 0 || laneRecords == null) {
				return  null;
			} else {
				return laneRecords.get(0);
				
			}
			
		} catch (Exception ex) {
			logger.error("Exception inside BookingServiceImpl.getLaneId.Catch_Block:" + ex.getMessage());
			throw ex;
		}
		
	}
	
	
	
	
	/**
	 * This BookingServiceImpl.getLaneGroupId method is used to fetch laneGroupId from LaneGroup table
	 * 
	 * @param laneId
	 *            This is used to fetch LaneGroup records
	 * @return LaneGroupId as String 
	 * @throws Exception
	 *             Handle the exceptions
	 */
	public LaneGroups getLaneGroupId(String laneId) throws Exception {
		try {
			logger.info("Entering into BookingServiceImpl.getLaneGroupId");
			
			List<LaneGroups> laneGroupsRecords = laneGroupsRepository.getLaneGroupsId(laneId);
			
			if(laneGroupsRecords.size() == 0 || laneGroupsRecords == null) {
				return null;
			} else {
				return laneGroupsRecords.get(0);
			}
			
		} catch (Exception ex) {
			logger.error("Exception inside BookingServiceImpl.getLaneGroupId.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}
	
	
	
	
	/**
	 * This BookingServiceImpl.getRoutingRecords method is used to fetch laneGroupId from LaneGroup table
	 * 
	 * @param laneGroupId
	 *            This is used to fetch routing records
	 * @return routing records as List 
	 * @throws Exception
	 *             Handle the exceptions
	 */
	public List<Routing> getRoutingRecords(String laneGroupId) throws Exception {
		try {
			logger.info("Entering into BookingServiceImpl.getRoutingRecords");
			
			List<Routing> routingRecords = routingRepository.getLaneGroupsId(laneGroupId);
			
			if(routingRecords.size() == 0 || routingRecords == null) {
				return null;
			} else {
				return routingRecords;
			}
			
		} catch (Exception ex) {
			logger.error("Exception inside BookingServiceImpl.getRoutingRecords.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}
	
	
	
	/**
	 * This BookingServiceImpl.callWorkOrderAPI method is used to fetch laneGroupId from LaneGroup table
	 * 
	 * @param laneGroupId
	 *            This is used to fetch routing records
	 */
	@Async
	public void callWorkOrderAPI(Booking booking) {
		try {	
			
			logger.info("Entering into BookingServiceImpl.callWorkOrderAPI");
			
			if (booking == null) {
				logger.debug("BookingServiceImpl.createBooking - Throwing Exception as Invalid Event Code Passed");
				throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(), 
						"Booking Object should not be Empty for Create WorkOrder");
			}
			
			
			final String URL_CREATE_WO_API = woCreateURL + "/dmsservices/workorders/v1/create?apikey=" + woCreateAPIKey;
			
			TrustStrategy acceptingTrustStrategy = new TrustStrategy() {		  
				@Override
				public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
					return true;
				}
		    };
		    
		    RestTemplate restTemplate = new RestTemplate();
			
		    
		    logger.info("Inside into BookingServiceImpl.callWorkOrderAPI and SSL context setting");
		    SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
		    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
		    
		    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		    requestFactory.setHttpClient(httpClient);		    
		    
		    
		    restTemplate = new RestTemplate(requestFactory);
		    
		    logger.info("Entering into BookingServiceImpl.callWorkOrderAPI and HttpHeaders set as JSON object");
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    
		    
		    
		    List<BookingDetails> bookingDetailsSet = booking.getBookingDetails();
		    
		    if(bookingDetailsSet.size() == 0 || bookingDetailsSet == null) {
		    	return;
		    }
		    
		    int transitTime = 0;
		    
		    
		    //Set WO_Date
		    if(booking.getRequestedPickupDate() != null) {
		    	woPickupDate = booking.getRequestedPickupDate();
		    } else {
		    	woPickupDate = new Date();
		    }
		    
		    
		    for(BookingDetails bookingDetails : bookingDetailsSet) {
		    	logger.info("Entering into BookingServiceImpl.callWorkOrderAPI and call mappedWORequest");
		    	WORequest woRequest = mappedWORequest(booking, bookingDetails, transitTime);
		    	
		    	if(woRequest == null) {
		    		continue;
		    	}
		    	String s = woRequest.toString();
				System.out.print(s);
				
				logger.info("Entering into BookingServiceImpl.callWorkOrderAPI and set transit time value");
				transitTime = bookingDetails.getRoutingLink().getTransitTime().intValue();
				
				logger.info("Entering into BookingServiceImpl.callWorkOrderAPI and call javaToJson method to convert java object to json string");
				HttpEntity<String> entity = new HttpEntity<String>(javaToJson(woRequest), headers);
				System.out.println(javaToJson(woRequest));
				
				// Send request with POST method.
				logger.info("Entering into BookingServiceImpl.callWorkOrderAPI and do POST Call");
				ResponseEntity<Object> response = restTemplate.postForEntity(URL_CREATE_WO_API, entity, Object.class);
				
				logger.error("bookingToWorkOrderEventResponse");
				logger.error("status" + response.getStatusCode());
				logger.error(response.getBody());
				response.getBody().toString();	
				
			}
		   
		} catch (Exception e) {
			logger.error("Exception occurred while creating shipmentEvents - exception: ", e);
		}
	}
	
	
	
	public WORequest mappedWORequest(Booking booking, BookingDetails bookingDetails, int transitTime) {
		String portOfLoading = "";
		String portOfDischarge = "";
		
		logger.info("Entering into BookingServiceImpl.mappedWORequest");
		
		WORequest woRequest = new WORequest();
		
		
		//Set EquipmentListRequest Values
		logger.info("Inside BookingServiceImpl.mappedWORequest --> Set EquipmentListRequest Values");
	       
		EquipmentListRequest equipmentListRequest = new EquipmentListRequest();
		
		equipmentListRequest.setEquipmentNumber("DETV" + (100000 + new Random().nextInt(900000)));
		
		if(booking.getWeight() == null || booking.getWeight() < 1.0) {
			equipmentListRequest.setTareWeight(1.0);
		} else {
			equipmentListRequest.setTareWeight(booking.getWeight());
		}
		equipmentListRequest.setWeightUOM("KG");
		
		if(booking.getVolume() == null|| booking.getVolume() < 1.0) {
			equipmentListRequest.setVolume(1.0);
		} else {
			equipmentListRequest.setVolume(booking.getVolume());
		}
		
		equipmentListRequest.setVolumeUOM("MTQ");
		equipmentListRequest.setIsOverweight("false");
		equipmentListRequest.setShipmentnumber("1");
		
		if(Boolean.toString(booking.isHazmat()) == null) {
			equipmentListRequest.setIsHazmat("true");
		} else {
			equipmentListRequest.setIsHazmat(Boolean.toString(booking.isHazmat()));
		}
		
		logger.info("set default value for EquipmentType.");
		
		
		switch(booking.getEquipmentDimensions().trim()) {
			case "40 feet":
				equipmentListRequest.setEquipmentTypeCode("40ST");
			
			case "20 feet":
				equipmentListRequest.setEquipmentTypeCode("20ST");
				
			case "45 feet":
				equipmentListRequest.setEquipmentTypeCode("45HC");
				
			default:
				equipmentListRequest.setEquipmentTypeCode("40ST");
		}
		
		
		//equipmentListRequest.setFreightDescription("Printers and Laptops");
		equipmentListRequest.setFreightDescription(booking.getCommodity());
		
		
		List<EquipmentListRequest> equipmentListRequestSet = new ArrayList<>();
		equipmentListRequestSet.add(equipmentListRequest);
		
		
		
		
		//Set StopList Values
		logger.info("Inside BookingServiceImpl.mappedWORequest --> Set List of StopLists Values");
	    
		List<StopListRequest> stopListRequestSet = new ArrayList<>();
		
		
		logger.info("Inside BookingServiceImpl.mappedWORequest --> Set StopListRequest for stopListRequestPickUp Values");
		StopListRequest stopListRequestPickUp = new StopListRequest();
		stopListRequestPickUp.setStopType("Pickup");
		stopListRequestPickUp.setStopNumber("1");
		
//		if(bookingDetails.getRoutingLink().getRouteDescription() != null) {
//			stopListRequestPickUp.setStopName(bookingDetails.getRoutingLink().getRouteDescription());
//		}
		
		//forPickup
		logger.info("Inside BookingServiceImpl.mappedWORequest --> Set location for PickUp Values");
		List<String> locationPickUp = new ArrayList<String>(Arrays.asList(bookingDetails.getRouteOriginLocationId().split(Pattern.quote("|"))));
//		List<String> locationPickUp = new ArrayList<String>(Arrays.asList(bookingDetails.getRouteOriginLocationId().split("\\|")));
		FacilityRequest facilityRequestPickUp = new FacilityRequest();
		
		if(locationPickUp.size() < 5 || locationPickUp == null) {
			portOfLoading = "SHENZEN";
	    } else {
	    	portOfLoading = locationPickUp.get(1);
			facilityRequestPickUp.setAddress1(locationPickUp.get(0));
			facilityRequestPickUp.setCity(locationPickUp.get(1));
			facilityRequestPickUp.setState(locationPickUp.get(2));
			facilityRequestPickUp.setPostalCode(locationPickUp.get(3));
			facilityRequestPickUp.setCountry(locationPickUp.get(4));
			
			stopListRequestPickUp.setStopName(locationPickUp.get(0));
	    }
		// facilityRequestPickUp.setCountry("US");
		
		stopListRequestPickUp.setFacility(facilityRequestPickUp);
		
		
		logger.info("Inside BookingServiceImpl.mappedWORequest --> Set StopListRequest for stopListRequestDelivery Values");
		StopListRequest stopListRequestDelivery = new StopListRequest();
		stopListRequestDelivery.setStopType("Delivery");
		
//		stopListRequestDelivery.setStopName(bookingDetails.getRoutingLink().getRouteDescription());		
		
		stopListRequestDelivery.setStopNumber("2");

		
		//for delivery
		logger.info("Inside BookingServiceImpl.mappedWORequest --> Set location for Delivery Values");
		List<String> locationDelivery = new ArrayList<String>(Arrays.asList(bookingDetails.getRouteDestinationLocationId().split("\\|")));
		FacilityRequest facilityRequestDelivery = new FacilityRequest();
		
		if(locationDelivery.size() < 5 || locationDelivery == null) {
			portOfDischarge = "LONG BEACH";
	    } else {
	    	portOfDischarge = locationDelivery.get(1);
			facilityRequestDelivery.setAddress1(locationDelivery.get(0));
			facilityRequestDelivery.setCity(locationDelivery.get(1));
			facilityRequestDelivery.setState(locationDelivery.get(2));
			facilityRequestDelivery.setPostalCode(locationDelivery.get(3));
			facilityRequestDelivery.setCountry(locationDelivery.get(4));
			
			stopListRequestDelivery.setStopName(locationDelivery.get(0));
	    }
		// facilityRequestDelivery.setCountry("US");
		
		stopListRequestDelivery.setFacility(facilityRequestDelivery);
		
		stopListRequestSet.add(0,stopListRequestPickUp);
		stopListRequestSet.add(1,stopListRequestDelivery);
		
		

		//Set WorkOrderRequest Values
		logger.info("Inside BookingServiceImpl.mappedWORequest --> Set workOrderRequest Values");
		WorkOrderRequest workOrderRequest = new WorkOrderRequest();
		workOrderRequest.setWorkOrderNumber("SHI" + (10000000 + new Random().nextInt(90000000)));
		
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm");
		
		// if(booking.getRequestedPickupDate() != null) {
		if(woPickupDate != null) {
			Calendar woDate = Calendar.getInstance();
			// woDate.setTime(booking.getRequestedPickupDate());
			woDate.setTime(woPickupDate);
			// woDate.add(Calendar.HOUR_OF_DAY, transitTime);
			workOrderRequest.setWorkOrderDate(dateFormat.format(woDate.getTime()));
			
			Calendar eta = Calendar.getInstance();
			// eta.setTime(booking.getRequestedPickupDate());
			eta.setTime(woPickupDate);
			eta.add(Calendar.HOUR_OF_DAY, bookingDetails.getRoutingLink().getTransitTime().intValue());
			workOrderRequest.setEta(dateFormat.format(eta.getTime()));
			
			woPickupDate = eta.getTime();
		}
		
		if(booking.getBookingId() != null) {
			workOrderRequest.setBillOfLadingNumber(booking.getBookingId());
		}
		
		//carrierCode
		if(bookingDetails.getRoutingLink().getDefault_carrier() != null) {
			String carrier = bookingDetails.getRoutingLink().getDefault_carrier();
			workOrderRequest.setCarrierCode(carrier.substring(0, Math.min(carrier.length(), 19)));
		} else {
			workOrderRequest.setCarrierCode("Carreir");
		}
		
		// workOrderRequest.setOriginatorCode(booking.getShipper());
		workOrderRequest.setOriginatorCode("OOOA");
		
		if(bookingDetails.getCreatedBy() != null) {
			workOrderRequest.setCreatedBy(bookingDetails.getCreatedBy());
		}
		
		if(booking.getBookingId() != null) {
			workOrderRequest.setBookingNumber(booking.getBookingId());
		}
		
		if(booking.getShipmentReferenceNum() != null) {
			workOrderRequest.setShipmentReferenceNumber(booking.getShipmentReferenceNum() + booking.getBookingId());
		}
		
		if(bookingDetails.getRouteModeOfTransport() != null) {
			workOrderRequest.setOriginatorOrderReference(bookingDetails.getRouteModeOfTransport());
		}
		
		if(booking.getShipper() != null) {
			workOrderRequest.setShipper(booking.getShipper());
		} else {
			workOrderRequest.setShipper("Walmart");
		}
		
		
		workOrderRequest.setEquipmentList(equipmentListRequestSet);
		
		workOrderRequest.setStopList(stopListRequestSet);
		
		
		//Remaining with map
		workOrderRequest.setCategory("Domestic");
		workOrderRequest.setVessel("ONE OLYMPUS");
		workOrderRequest.setVoyage("054E");
		
		workOrderRequest.setPortOfDischarge(portOfDischarge);
		workOrderRequest.setPortOfLoading(portOfLoading);
		
		
		// Set WORequest Value
		woRequest.setWorkOrder(workOrderRequest);
		
		return woRequest;
	}
	


   /*
    Convert java object to JSON string
   */
   public static String javaToJson(Object o) {
	   logger.info("Entering into BookingServiceImpl.javaToJson");
       String jsonString = null;
       try {
           ObjectMapper objectMapper = new ObjectMapper();
           objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.UNWRAP_ROOT_VALUE,true);  
           jsonString = objectMapper.writeValueAsString(o);
       } catch (JsonGenerationException e) {
           logger.error(e);
       } catch (JsonMappingException e) {
           logger.error(e);
       } catch (IOException e) {
           logger.error(e);
       }
       return jsonString;
   }
}

















