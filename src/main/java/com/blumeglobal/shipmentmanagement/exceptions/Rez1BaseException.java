package com.blumeglobal.shipmentmanagement.exceptions;


import java.util.Map;
/**
 * The Rez1BaseException is the base exception class for others in shipment application. This is being used in rest controller response exception handling
 *
 */
public class Rez1BaseException  extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -961510934072343007L;
	
	private Map<String, String> valuesMap;
	
	private String exceptionCode;
	/**
	 * @return the exceptionCode
	 */
	public String getExceptionCode() {
		return exceptionCode;
	}

	/**
	 * @param exceptionCode the exceptionCode to set
	 */
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	public Map<String, String> getValuesMap() {
		return valuesMap;
	}

	public void setValuesMap(Map<String, String> valuesMap) {
		this.valuesMap = valuesMap;
	}
	
	/**
	 * Create a new Rez1BaseException.
	 * @param msg the detail message (used as exception message as-is)
	 */
	public Rez1BaseException(String msg) {
		super(msg);
	}

	/**
	 * Create a new Rez1BaseException.
	 * @param msg the detail message (used as exception message as-is)
	 * @param cause the root cause (may be <code>null</code>)
	 */
	public Rez1BaseException(String msg, Throwable cause) {
		super(msg, cause);
	}	
	
	/**
	 * Instantiates a new Rez1BaseException
	 *
	 * @param cause the cause
	 */
	public Rez1BaseException(Throwable cause) {
		super(cause);
	}

}
