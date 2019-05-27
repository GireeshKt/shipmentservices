package com.blumeglobal.shipmentmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * The DataException is used to handle any data relates exceptions.
 *
 */
@ResponseStatus(HttpStatus.OK)
public class DataException  extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -961510934072343007L;
	
	
	/**
	 * Create a new DataException.
	 * @param msg the detail message (used as exception message as-is)
	 */
	public DataException(String msg) {
		super(msg);
	}

	/**
	 * Create a new DataException.
	 * @param msg the detail message (used as exception message as-is)
	 * @param cause the root cause (may be <code>null</code>)
	 */
	public DataException(String msg, Throwable cause) {
		super(msg, cause);
	}	
	
	/**
	 * Instantiates a new DataException
	 *
	 * @param cause the cause
	 */
	public DataException(Throwable cause) {
		super(cause);
	}

}
