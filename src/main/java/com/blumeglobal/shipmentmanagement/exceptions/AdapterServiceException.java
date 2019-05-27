package com.blumeglobal.shipmentmanagement.exceptions;

public class AdapterServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -961510934072343007L;

	/**
	 * Create a new AdapterServiceException.
	 * @param msg the detail message (used as exception message as-is)
	 */
	public AdapterServiceException(String msg) {
		super(msg);
	}

	/**
	 * Create a new AdapterServiceException.
	 * @param msg the detail message (used as exception message as-is)
	 * @param cause the root cause (may be <code>null</code>)
	 */
	public AdapterServiceException(String msg, Throwable cause) {
		super(msg, cause);
	}	
	
	/**
	 * Instantiates a new adapter service exception.
	 *
	 * @param cause the cause
	 */
	public AdapterServiceException(Throwable cause) {
		super(cause);
	}
	
}

