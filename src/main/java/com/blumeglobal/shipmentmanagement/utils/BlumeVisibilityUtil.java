package com.blumeglobal.shipmentmanagement.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.exceptions.DataAlreadyExistException;
import com.blumeglobal.shipmentmanagement.exceptions.DataNotFoundException;
import com.blumeglobal.shipmentmanagement.exceptions.InValidDataException;
import com.blumeglobal.shipmentmanagement.exceptions.Rez1BaseException;




/**
 * ShipmentUtil component class is used to maintain the Shipment application utilities
 *
 */
@Component
public class BlumeVisibilityUtil {
	
	private BlumeVisibilityUtil() {
		
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
	public InValidDataException throwInValidDataException(String exceptionCode,String exMsg,String errorMsg) {
		InValidDataException invalidEx = new InValidDataException(errorMsg);
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
	public InValidDataException throwInValidDataException(String exceptionCode,String errorMsg) {
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
	public DataNotFoundException throwDataNotFoundException(String exceptionCode,String exMsg,String errorMsg) {
		DataNotFoundException notFoundEx = new DataNotFoundException(errorMsg);
		notFoundEx.setExceptionCode(exceptionCode);
		Map<String,String> valuemap = new HashMap<>();
		valuemap.put("1", exMsg);
		notFoundEx.setValuesMap(valuemap);
		return notFoundEx;
	}

	
	/**
	 * To Create DataNotFoundException instance to handle data not found exception
	 * 
	 * @param exceptionCode - This is used to pass exception code detail
	 * @param errorMsg - This is used to pass exception message into exception class constructor
	 * 
	 * @return DataNotFoundException - return DataNotFoundException
	 *
	 */
	public DataNotFoundException throwDataNotFoundException(String exceptionCode, String errorMsg) {
		DataNotFoundException notFoundEx = new DataNotFoundException(errorMsg);
		notFoundEx.setExceptionCode(exceptionCode);
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
	 * To Create RuntimeException instance to handle Runtime errors exception
	 * 
	 * @param exceptionCode - This is used to pass exception code detail
	 * @param exMsg - This is used to pass defined exception message into value map
	 * @param errorMsg - This is used to pass exception message into exception class constructor
	 * 
	 * @return Rez1BaseException - return Rez1BaseException
	 *
	 */
	public Rez1BaseException throwRuntimeException(String exceptionCode,String exMsg,String errorMsg) {
		Rez1BaseException notFoundEx = new Rez1BaseException(errorMsg);
		notFoundEx.setExceptionCode(exceptionCode);
		Map<String,String> valuemap = new HashMap<>();
		valuemap.put("1", exMsg);
		notFoundEx.setValuesMap(valuemap);
		return notFoundEx;
	}
	
	

}
