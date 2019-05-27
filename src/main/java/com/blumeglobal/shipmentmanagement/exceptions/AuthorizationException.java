package com.blumeglobal.shipmentmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * The InValidDataException is used to handle access related exception.
 *
 */
@ResponseStatus(HttpStatus.OK)
public class AuthorizationException  extends Rez1BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -961510934072343007L;
	
	/**
	 * Create a new AuthorizationException.
	 * @param msg the detail message (used as exception message as-is)
	 */
	public AuthorizationException(String msg) {
		super(msg);
	}

	/**
	 * Create a new AuthorizationException.
	 * @param msg the detail message (used as exception message as-is)
	 * @param cause the root cause (may be <code>null</code>)
	 */
	public AuthorizationException(String msg, Throwable cause) {
		super(msg, cause);
	}	
	
	/**
	 * Instantiates a new AuthorizationException
	 *
	 * @param cause the cause
	 */
	public AuthorizationException(Throwable cause) {
		super(cause);
	}
	
}
