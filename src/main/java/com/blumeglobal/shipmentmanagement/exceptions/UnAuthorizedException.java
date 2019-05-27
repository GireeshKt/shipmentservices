package com.blumeglobal.shipmentmanagement.exceptions;
/**
 * The UnAuthorizedException is used to handle access related exceptions.
 *
 */
public class UnAuthorizedException  extends Rez1BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -961510934072343007L;
	
	/**
	 * Create a new UnAuthorizedException.
	 * @param msg the detail message (used as exception message as-is)
	 */
	public UnAuthorizedException(String msg) {
		super(msg);
	}

	/**
	 * Create a new UnAuthorizedException.
	 * @param msg the detail message (used as exception message as-is)
	 * @param cause the root cause (may be <code>null</code>)
	 */
	public UnAuthorizedException(String msg, Throwable cause) {
		super(msg, cause);
	}	
	
	/**
	 * Instantiates a new UnAuthorizedException
	 *
	 * @param cause the cause
	 */
	public UnAuthorizedException(Throwable cause) {
		super(cause);
	}
	
}
