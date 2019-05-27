package com.blumeglobal.shipmentmanagement.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
/**
 * The ASNUtils component class is used to manage simple conversions.
 *
 */
@Component
public class ASNUtils {
	
	private  static Log logger = LogFactory.getLog(ASNUtils.class);
	private ASNUtils() {
		
	}
	public Boolean isDouble(String strDouble) {

		
		try{
			Double.parseDouble(strDouble);
			return true;
		}
		catch(NumberFormatException e){
		  //not a double
		}
		return false;
	
	}
	public Boolean isLongNumber(String num) {
		try {
			Long.parseLong(num);
			return true;
		}catch(NumberFormatException e) {
			
		}
		return false;
	}
}
