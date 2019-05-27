package com.blumeglobal.shipmentmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * The DataAlreadyExistException is used to handle data already exist exceptions.
 *
 */
@ResponseStatus(HttpStatus.OK)
public class DataAlreadyExistException extends Rez1BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -961510934072343007L;
	
	
	/**
	 * Create a new DataAlreadyExistException.
	 * @param msg the detail message (used as exception message as-is)
	 */
	public DataAlreadyExistException(String msg) {
		super(msg);
	}

	/**
	 * Create a new DataAlreadyExistException.
	 * @param msg the detail message (used as exception message as-is)
	 * @param cause the root cause (may be <code>null</code>)
	 */
	public DataAlreadyExistException(String msg, Throwable cause) {
		super(msg, cause);
	}	
	
	/**
	 * Instantiates a new DataAlreadyExistException
	 *
	 * @param cause the cause
	 */
	public DataAlreadyExistException(Throwable cause) {
		super(cause);
	}
	
	
}
