package com.blumeglobal.shipmentmanagement.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.exceptions.DataAlreadyExistException;
import com.blumeglobal.shipmentmanagement.exceptions.DataNotFoundException;
import com.blumeglobal.shipmentmanagement.exceptions.InValidDataException;
import com.blumeglobal.shipmentmanagement.model.E2EShipment;
import com.blumeglobal.shipmentmanagement.model.Shipment;
/**
 * ShipmentUtil component class is used to maintain the Shipment application utilities
 *
 */
@Component
public class OldShipmentUtil{
	public static final String AddrSplitChar="|";
	private OldShipmentUtil() {
		
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
	
}
