package com.blumeglobal.shipmentmanagement.config;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.blumeglobal.shipmentmanagement.enums.ExceptionCode;
import com.blumeglobal.shipmentmanagement.model.ExceptionDataKey;

/**
 * The ExceptionConfig is used to configure the exception code and details.
 *
 */
@Configuration
public class ExceptionConfig {
	
	/**
	 * This bean method load all the exception code and details in Map.
	 *
	 */
	@Bean 
	Map<String,String> exceptionData(){
		Map<String,String> exceptionDataMap = new HashMap <>();
		for(ExceptionCode exCode:ExceptionCode.values()) {
			ExceptionDataKey exceptionDataKey = new ExceptionDataKey();
			exceptionDataKey.setAppName(exCode.getApplicationName());
			exceptionDataKey.setCode(exCode.toString());
			exceptionDataKey.setLanguage(exCode.getLanguage());
			exceptionDataMap.put(exceptionDataKey.toString(), exCode.getExceptionMessage());
		}
		return exceptionDataMap;
	}
	
}