package com.blumeglobal.shipmentmanagement.external.hub.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.ws.soap.SOAPFaultException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class HubServicesHandlerProxy {

	
	/** The logger. */
	private static transient Log logger = LogFactory.getLog(HubServicesHandlerProxy.class);
	
	/** The Constant RETRY_OPTIONS. */
	//Increase to 20. Invoke hub service succeed after up 9 times of failure in local test. 
	private static final int RETRY_OPTIONS = 20;
	

	/**
	 * Invoke hub service.
	 *
	 * @param beanName the bean name
	 * @param methodName the method name
	 * @param args the args
	 * @return the object
	 * @throws RuntimeException the runtime exception
	 */
	public static Object invokeHubService(String beanName, String methodName, Object... args) throws RuntimeException {
		
		if(StringUtils.isBlank(beanName)|| StringUtils.isBlank(methodName)){
			throw new RuntimeException ("beanName or methodName is null.");
		}
		
		if(logger.isInfoEnabled()){
			logger.info("Starting method HubServicesHandlerProxy.invokeHubService. beanName :" + beanName + ",methodName :" + methodName + ", args:" + args);
		}
		
		Object response = null;

		Object interfaceObj = SpringApplicationContext.getBean(beanName);
		Class<?>[] classTypes = getClassTypesArray(args);

		for (int i = 0; i < RETRY_OPTIONS; i++) {
			try {
				response = interfaceObj.getClass().getMethod(methodName, classTypes).invoke(interfaceObj, args);
				break;
			} catch (Exception ex) {	
				if(methodName.equalsIgnoreCase("getUserProfileByUserName") || methodName.equalsIgnoreCase("authenticate")){ //If no profile found, service throw an Exception.
					break;
				}
				logger.error("Try # i="+i+". Error occured while invoking bean " + beanName + " method name " + methodName);				
				if (i + 1 < RETRY_OPTIONS) {
					continue;
				}else{
					if (ex.getCause() instanceof SOAPFaultException) {
						throw new RuntimeException("Internal Error: Please reprocess this transaction",ex);
					}else{
						throw new RuntimeException(ex);
					}
				}				
			} 
		}
		if(logger.isDebugEnabled()){
			logger.debug("End of invokeHubService");
		}
		return response;
	}

	/**
	 * Returns the array of class types of the objects in list of args passed.
	 *
	 * @param args the args
	 * @return the class types array
	 */
	private static Class<?>[] getClassTypesArray(Object... args) {
		
		if(logger.isDebugEnabled()){
			logger.debug("Start of getClassTypesArray");
		}
		Class<?>[] classTypes = null;
		if (args != null && args.length > 0) {
			if(logger.isDebugEnabled()){
				logger.debug("args.length =" + args.length);
			}
			List<Class<?>> classTypesList = new ArrayList<Class<?>>();
			for (Object arg : args) {
				if(null != arg){
					if (arg instanceof List) {
						classTypesList.add(List.class);
					} else if (arg instanceof Map) {
						classTypesList.add(Map.class);
					} else {
						classTypesList.add(arg.getClass());
					}
				}				
			}
			classTypes = classTypesList.toArray(new Class<?>[args.length]);
		} else {
			if(logger.isDebugEnabled()){
				logger.debug("args is NULL or args.length is 0");
			}
		}
		if(logger.isDebugEnabled()){
			logger.debug("End of getClassTypesArray");
		}
		return classTypes;
	}	



}
