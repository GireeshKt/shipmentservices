package com.blumeglobal.shipmentmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * The BusinessException is used to handle business functionalities exceptions.
 *
 */
@ResponseStatus(HttpStatus.OK)
public class BusinessException extends Rez1BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -961510934072343007L;
	
	
	/**
	 * Create a new BusinessException.
	 * @param msg the detail message (used as exception message as-is)
	 */
	public BusinessException(String msg) {
		super(msg);
	}

	/**
	 * Create a new BusinessException.
	 * @param msg the detail message (used as exception message as-is)
	 * @param cause the root cause (may be <code>null</code>)
	 */
	public BusinessException(String msg, Throwable cause) {
		super(msg, cause);
	}	
	
	/**
	 * Instantiates a new BusinessException
	 *
	 * @param cause the cause
	 */
	public BusinessException(Throwable cause) {
		super(cause);
	}
	
	
}