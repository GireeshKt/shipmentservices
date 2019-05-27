package com.blumeglobal.shipmentmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * The DataException is used to handle data not found exceptions.
 *
 */
@ResponseStatus(HttpStatus.OK)
public class DataNotFoundException extends Rez1BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -961510934072343007L;
	
	
	/**
	 * Create a new DataNotFoundException.
	 * @param msg the detail message (used as exception message as-is)
	 */
	public DataNotFoundException(String msg) {
		super(msg);
	}

	/**
	 * Create a new DataNotFoundException.
	 * @param msg the detail message (used as exception message as-is)
	 * @param cause the root cause (may be <code>null</code>)
	 */
	public DataNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}	
	
	/**
	 * Instantiates a new DataNotFoundException
	 *
	 * @param cause the cause
	 */
	public DataNotFoundException(Throwable cause) {
		super(cause);
	}

}