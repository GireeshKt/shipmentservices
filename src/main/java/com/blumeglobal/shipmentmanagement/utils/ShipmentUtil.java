package com.blumeglobal.shipmentmanagement.utils;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.blumeglobal.shipmentmanagement.exceptions.DataAlreadyExistException;
import com.blumeglobal.shipmentmanagement.exceptions.DataNotFoundException;
import com.blumeglobal.shipmentmanagement.exceptions.InValidDataException;
import com.blumeglobal.shipmentmanagement.model.E2EShipment;
import com.blumeglobal.shipmentmanagement.model.Shipment;
import com.blumeglobal.shipmentmanagement.response.LocationAPIResponse;
/**
 * ShipmentUtil component class is used to maintain the Shipment application utilities
 *
 */
@Component
public class ShipmentUtil {
	
	@Value("${client.locationMaster.locationMasterFindOrCreate}")
	private String findOrCreateUrl;
	
	@Value("${client.locationMaster.locationMasterFindAddress}")
	private String findAddressUrl;
	
	@Value("${client.locationMaster.locationMasterFindById}")
	private String findByIdUrl;
	
    @Value("${client.locationMaster.locationMasterFindByUnlocode}")
    private String findByUnlocodeUrl;
	
	
	
	private static final Logger logger = LogManager.getLogger(ShipmentUtil.class);
	private static final String EMPTY_STRING = "";
	
	public static final String AddrSplitChar="|";
	
	public static final String AddrSplitCharComma=",";
	
	private ShipmentUtil() {
		
	}
	/**
	 * To Verify Null String
	 * 
	 * @param str - This is used to pass input string to check null or empty
	 * @return boolean - return boolean value for null check
	 *
	 */
	public static boolean isNullString(String str) {
		boolean outVal = false;
		if(str==null || str.isEmpty()) {
			outVal =  true;
		}
		return outVal;
	}
	
	/**
	 * To Check whether it is decimal or not
	 * 
	 * @param str - This is used to pass input string to check number string
	 * @return boolean - return boolean value for number string
	 *
	 */
	public static boolean isDecimalNumber(String inputval) {
		Pattern patternDecimal = Pattern.compile("-?\\d+(\\.\\d+)?");
		return patternDecimal.matcher(inputval).matches(); 
	}
	
	
	/**
	 * To Create InValidDataException instance based on the exception code and message
	 * 
	 * @param exceptionCode - This is used to pass exception code detail
	 * @param exMsg - This is used to pass defined exception message into value map
	 * @param errorMsg - This is used to pass exception message into exception class constructor
	 * 
	 * @return InValidDataException - return InValidDataException
	 *
	 */
	public InValidDataException throwInValidDataException(String exceptionCode,String exMsg) {
		InValidDataException invalidEx = new InValidDataException("");
		invalidEx.setExceptionCode(exceptionCode);
		Map<String,String> valuemap = new HashMap<>();
		valuemap.put("1", exMsg);
		invalidEx.setValuesMap(valuemap);
		return invalidEx;
	}
	/**
	 * To Create InValidDataException instance based on the exception code and message with out value map
	 * 
	 * @param exceptionCode - This is used to pass exception code detail
	 * @param errorMsg - This is used to pass exception message into exception class constructor
	 * 
	 * @return InValidDataException - return InValidDataException
	 *
	 */
	public InValidDataException throwInValidDataException1(String exceptionCode,String errorMsg) {
		InValidDataException invalidEx = new InValidDataException(errorMsg);
		invalidEx.setExceptionCode(exceptionCode);
		return invalidEx;
	}
	
	/**
	 * To Create DataNotFoundException instance to handle data not found exception
	 * 
	 * @param exceptionCode - This is used to pass exception code detail
	 * @param exMsg - This is used to pass defined exception message into value map
	 * @param errorMsg - This is used to pass exception message into exception class constructor
	 * 
	 * @return DataNotFoundException - return DataNotFoundException
	 *
	 */
	public DataNotFoundException throwDataNotFoundException(String exceptionCode,String exMsg) {
		DataNotFoundException notFoundEx = new DataNotFoundException("");
		notFoundEx.setExceptionCode(exceptionCode);
		Map<String,String> valuemap = new HashMap<>();
		valuemap.put("1", exMsg);
		notFoundEx.setValuesMap(valuemap);
		return notFoundEx;
	}
	
	/**
	 * To Create DataAlreadyExistException instance for Data already exist with out value map
	 * 
	 * @param exceptionCode - This is used to pass exception code detail
	 * @param errorMsg - This is used to pass exception message into exception class constructor
	 * 
	 * @return DataAlreadyExistException - return DataAlreadyExistException
	 *
	 */
	public DataAlreadyExistException throwDataAlreadyExistException(String exceptionCode,String errorMsg) {
		DataAlreadyExistException notFoundEx = new DataAlreadyExistException(errorMsg);
		notFoundEx.setExceptionCode(exceptionCode);
		return notFoundEx;
	}
	
	/**
	 * This ShipmentServiceImpl.parseBooleanValue method is used to parse string to boolean value
	 * 
	 * @param booleanValue
	 *            This is used to pass String value
	 * @return boolean 
	 */
	public boolean parseBooleanValue(String booleanValue) {
		if(booleanValue!=null) {
			if(("Y").equalsIgnoreCase(booleanValue)||"YES".equalsIgnoreCase(booleanValue)||
					"TRUE".equalsIgnoreCase(booleanValue)) {
				return true;
			} else {
				return false;
			}
		} 
		return false;
	}
	public static Date getShipmentCompareDate(E2EShipment shipment) {
		if(shipment.getExpectedDeliveryDate()!=null) return shipment.getExpectedDeliveryDate();
		else if(shipment.getPodDate()!=null) return shipment.getPodDate();
		else return shipment.getCreatedDateTime();
	}

	public static Date getShipmentCompareDate(Shipment shipment) {
		if(shipment.getScheduleDeliveryDate()!=null) return shipment.getScheduleDeliveryDate();
		else if(shipment.getExpectedDeliveryDate()!=null) return shipment.getExpectedDeliveryDate();
		else if(shipment.getExportCutOffDate()!=null) return shipment.getExportCutOffDate();
		else if(shipment.getLastFreeDate()!=null) return shipment.getLastFreeDate();
		else if(shipment.getWorkOrderCreatedDate()!=null) return shipment.getWorkOrderCreatedDate();
		else return shipment.getCreatedDate();
	}

	
	public static String getLocationRoute(String addr1, String addr2, String city, String state, String zip, String country) {
		//TODO create address string to lookup for locationId or set as locationId initially
		StringBuilder sb = new StringBuilder().append(addr1!=null?addr1+AddrSplitChar:"")
				.append(addr2!=null?addr2+AddrSplitChar:"")
				.append(city!=null?city+AddrSplitChar:"")
				.append(state!=null?state+AddrSplitChar:"")
				.append(zip!=null?zip+AddrSplitChar:"")
				.append(country!=null?country:"");
				
		return sb.toString();
	}
	
	/*
	public static String getLocation(String addr1, String addr2, String city, String state, String zip, String country) {
		//TODO create address string to lookup for locationId or set as locationId initially
		StringBuilder sb = new StringBuilder().append(addr1!=null?addr1+AddrSplitChar:"")
				.append(addr2!=null?addr2+AddrSplitChar:"")
				.append(city!=null?city+AddrSplitChar:"")
				.append(state!=null?state+AddrSplitChar:"")
				.append(zip!=null?zip+AddrSplitChar:"")
				.append(country!=null?country:"");
				
		return sb.toString();
	}
	public static String getLocationAll(String addr1, String addr2, String city, String state, String zip, String country) {
		StringBuilder sb = new StringBuilder().append(addr1!=null?addr1:"").append(AddrSplitChar)
				.append(addr2!=null?addr2:"").append(AddrSplitChar)
				.append(city!=null?city:"").append(AddrSplitChar)
				.append(state!=null?state:"").append(AddrSplitChar)
				.append(zip!=null?zip:"").append(AddrSplitChar)
				.append(country!=null?country:"");
		return sb.toString();
	}
	*/
	
	
	/** This ShipmentUtil.getLocationsByLocationId method is used to fetch uuid based on address
	 * 
	 * @param uuid
	 *            This is contain locationId
	 * @return LocationAPIResponse 
	 */
	public LocationAPIResponse getLocationsByLocationId(String uuid) throws Exception {
		try {
			
			if (isNullOrEmpty(uuid)) { // All callers are handling NULL condition
				return null;
			}
			
			Map<String, String> params = new HashMap<String, String>();
		    params.put("uuid", uuid);
		    
			
			logger.info("Entering into ShipmentUtil.getLocationsByLocationId: trusting certificate");
			TrustStrategy acceptingTrustStrategy = new TrustStrategy() {		  
				@Override
				public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
					return true;
				}
		    };
		    
		    RestTemplate restTemplate = new RestTemplate();
		   
		    SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
		    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
		    
		    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		    requestFactory.setHttpClient(httpClient);		    
		    
		    
		    restTemplate = new RestTemplate(requestFactory);
		   	    
		    // Send request with POST method.
			logger.info("Entering into ShipmentUtil.getLocationsByLocationId and do Get Call");
			LocationAPIResponse locationAPIResponse = restTemplate.getForObject(findByIdUrl, LocationAPIResponse.class, params);
			
			System.out.println("Response:" + locationAPIResponse);
//			locationAPIResponse.setLocationType("DC");
			return locationAPIResponse;
//			return null;
		} catch (Exception e) {
			logger.error("Exception occurred while creating ShipmentUtil.getLocationsByLocationId - exception: ", e);
			throw e;
		}
	}
	
    /** This ShipmentUtil.getLocationsByUnlocode method 
     * 
     * @param unlocode
     *            This is contain locationId
     * @return LocationAPIResponse 
     */
    public List<LocationAPIResponse> getLocationsByUnlocode(String unlocode) throws Exception {
        try {
            
            if (isNullOrEmpty(unlocode)) { // All callers are handling NULL condition
                return null;
            }
            
            Map<String, String> params = new HashMap<String, String>();
            params.put("unlocode", unlocode);
            
            
            logger.info("Entering into ShipmentUtil.getLocationsByUnlocode: trusting certificate");
            TrustStrategy acceptingTrustStrategy = new TrustStrategy() {          
                @Override
                public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                    return true;
                }
            };
            
            RestTemplate restTemplate = new RestTemplate();
           
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
            
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            requestFactory.setHttpClient(httpClient);           
            
            
            restTemplate = new RestTemplate(requestFactory);
                
            // Send request with POST method.
            logger.info("Entering into ShipmentUtil.getLocationsByUnlocode and do Get Call");
            
            //restTemplate.exchange
            ResponseEntity<List<LocationAPIResponse>> response = restTemplate
                    .exchange(
                            findByUnlocodeUrl,
              HttpMethod.GET,
              null,
              new ParameterizedTypeReference<List<LocationAPIResponse>>(){}, params);
            List<LocationAPIResponse> locations = response.getBody();
            
            return locations;
        } catch (Exception e) {
            logger.error("Exception occurred while creating ShipmentUtil.getLocationsByLocationId - exception: ", e);
            throw e;
        }
    }

    
	/**
	 * This ShipmentUtil.getLocationIdByAddress method is used to fetch address information based on uuid
	 * 
	 * @param List of LocationAPIResponse
	 *            This is used to pass address, city, state etc. value
	 * @return locationId as String  
	 */
	public String getLocationIdByAddress(List<LocationAPIResponse> locationAPIResponse) throws Exception {
		try {
			
			logger.info("Entering into ShipmentUtil.locationAPIResponse: trusting certificate");
			TrustStrategy acceptingTrustStrategy = new TrustStrategy() {		  
				@Override
				public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
					return true;
				}
		    };
		    
		    RestTemplate restTemplate = new RestTemplate();
		   
		    SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
		    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
		    
		    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		    requestFactory.setHttpClient(httpClient);		    
		    
		    
		    restTemplate = new RestTemplate(requestFactory);
		   	    
		   
			logger.info("Entering into ShipmentUtil.locationAPIResponse and do Get Call");
			
			
			logger.info("Entering into ShipmentUtil.locationAPIResponse and HttpHeaders set as JSON object");
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    
			
			logger.info("Entering into ShipmentUtil.locationAPIResponse and call javaToJson method to convert java object to json string");
			HttpEntity<String> entity = new HttpEntity<String>(javaToJson(locationAPIResponse), headers);
			System.out.println(javaToJson(locationAPIResponse));
			
			
			
			// Send request with POST method.
			logger.info("Entering into ShipmentUtil.locationAPIResponse and do POST Call");
			ResponseEntity<Object> response = restTemplate.postForEntity(findOrCreateUrl, entity, Object.class);
			
			if (null == response) {
				logger.error("Location Not Found for Location Address :" + javaToJson(locationAPIResponse));
				return EMPTY_STRING;
			} else {
				logger.error("status" + response.getStatusCode());
				logger.error(response.getBody());
				List<LinkedHashMap<Integer, String>> l1 = (List<LinkedHashMap<Integer, String>>) response.getBody();

				logger.info("Response:" + l1.get(0).get("uuid"));

				return l1.get(0).get("uuid");
			}
			
		} catch (Exception e) {
			logger.error("Exception occurred while creating ShipmentUtil.locationAPIResponse - exception: ", e);
			return null;
//			throw e;
		}
	}
	
	
	
	
	/**
	 * This ShipmentUtil.getLocationResponseByAddress method is used to fetch List of Location response based on given address
	 * 
	 * @param List of LocationAPIResponse
	 *            This is used to pass address, city, state etc. value
	 * @return locationId as String  
	 */
	public List<LinkedHashMap<String, String>> getLocationResponseByAddress(LocationAPIResponse locationAPIResponse) {
		try {
			
			logger.info("Entering into ShipmentUtil.getLocationResponseByAddress: trusting certificate");
			TrustStrategy acceptingTrustStrategy = new TrustStrategy() {		  
				@Override
				public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
					return true;
				}
		    };
		    
		    RestTemplate restTemplate = new RestTemplate();
		   
		    SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
		    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
		    
		    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		    requestFactory.setHttpClient(httpClient);		    
		    
		    
		    restTemplate = new RestTemplate(requestFactory);
		   	    
		   
			logger.info("Entering into ShipmentUtil.getLocationResponseByAddress and do Get Call");
			
			
			logger.info("Entering into ShipmentUtil.getLocationResponseByAddress and HttpHeaders set as JSON object");
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    
			
			logger.info("Entering into ShipmentUtil.getLocationResponseByAddress and call javaToJson method to convert java object to json string");
			HttpEntity<String> entity = new HttpEntity<String>(javaToJson(locationAPIResponse), headers);
			System.out.println(javaToJson(locationAPIResponse));
			
			
			
			// Send request with POST method.
			logger.info("Entering into ShipmentUtil.getLocationResponseByAddress and do POST Call");
			ResponseEntity<Object> response = restTemplate.postForEntity(findAddressUrl, entity, Object.class);
			
			@SuppressWarnings("unchecked")
			List<LinkedHashMap<String, String>> locationAPIResponses = (List<LinkedHashMap<String, String>>) response.getBody();
			
			System.out.println(locationAPIResponses);
			
			return locationAPIResponses;
		} catch (Exception e) {
			logger.error("Exception occurred while creating ShipmentUtil.getLocationResponseByAddress - exception: ", e);
			return null;
		}
	}
	
	
	
	
	
	/**
	 * This ShipmentUtil.getLocationIdAll method is used to fetch uuid based on address information
	 * 
	 * @param String of values which is used to pass address, city, state etc. value
	 * @return uuid as String  
	 */
	public String getLocationIdAll(String name, String address1, String address2, String city, String state, String zipcode, String country)  {
		List<LocationAPIResponse> locationAPIResponses = new ArrayList<>();
		
		LocationAPIResponse locationAPIResponse = new LocationAPIResponse();
		locationAPIResponse.setName(name);
		locationAPIResponse.setAddress1(address1);
		locationAPIResponse.setAddress2(address2);
		locationAPIResponse.setCity(city);
		locationAPIResponse.setState(state);
		locationAPIResponse.setPostalCode(zipcode);
		locationAPIResponse.setCountry(country);
		locationAPIResponse.setUuid("");
		// originLocationAPIResponse.setLocationType(locationType);
		locationAPIResponses.add(locationAPIResponse);
		
		try {
            return getLocationIdByAddress(locationAPIResponses);
        } catch (Exception e) {
            logger.error("Exception in getLocationIdAll", e);
            return null;
        }
	}
	
	
	public String getLocationIdAsStringSeparatedByPipe(String addr1, String addr2, String city, String state, String zip, String country) {
		//TODO create address string to lookup for locationId or set as locationId initially
		StringBuilder sb = new StringBuilder().append(addr1!=null?addr1+AddrSplitChar:"")
				.append(addr2!=null?addr2+AddrSplitChar:"")
				.append(city!=null?city+AddrSplitChar:"")
				.append(state!=null?state+AddrSplitChar:"")
				.append(zip!=null?zip+AddrSplitChar:"")
				.append(country!=null?country:"");
				
		return sb.toString();
	}
	
	
	
	/**
	 * This ShipmentUtil.getLocationType method is used to fetch location type based on address information
	 * 
	 * @param String of values which is used to pass address, city, state etc. value
	 * @return location type as String  
	 */
	public String getLocationType(String name, String address1, String address2, String city, String state, String zip, String country) {
		//TODO create address string to lookup for locationId or set as locationId initially
		String locationType = null;
		try {
			String locationId = getLocationIdAll(name, address1, address2, city, state, zip, country);
			LocationAPIResponse locationAPIResponse = getLocationsByLocationId(locationId);
			if(locationAPIResponse != null) {
				locationType = locationAPIResponse.getLocationType();
			}
		} catch (Exception e) {
		    logger.error("Exception in getLocationType",e);
			locationType = null;
		}
		
		return locationType;
	}
	
    /**
     * This ShipmentUtil.getLocationType method is used to fetch location type based on location uuid
     * 
     * @param String of uuid value
     * @return location type as String  
     */
    public String getLocationType(String uuid) {
        String locationType = null;
        try {
            LocationAPIResponse locationAPIResponse = getLocationsByLocationId(uuid);
            if(locationAPIResponse != null) {
                locationType = locationAPIResponse.getLocationType();
            }
        } catch (Exception e) {
            logger.error(e);
            locationType = null;
        }
        
        return locationType;
    }	
	/**
	 * This ShipmentUtil.javaToJson method is used to Convert java object to JSON string
	 * 
	 * @param Object
	 *            Object type value
	 * @return Object value in JSON format as String  
	 */
	public static String javaToJson(Object o) {
		logger.info("Enter into ShipmentUtil.javaToJson");
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
	
	private boolean isNullOrEmpty(String stringToCheck) {
		if (stringToCheck == null || stringToCheck.trim().length() == 0) {
			return true;
		}

		return false;
	}
	/**
	 * @param locationDetails
	 * @return
	 */
	public String formatLocation(String locationDetails) {
		if (StringUtils.isNotBlank(locationDetails) & locationDetails.contains(AddrSplitChar)) {
			locationDetails = locationDetails.replace(AddrSplitChar, AddrSplitCharComma);
		}

		return locationDetails;
	}
}