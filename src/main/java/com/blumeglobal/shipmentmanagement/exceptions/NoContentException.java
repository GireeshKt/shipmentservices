package com.blumeglobal.shipmentmanagement.exceptions;
/**
 * The NoContentException is used to handle incorrect content exceptions.
 *
 */
public class NoContentException   extends Rez1BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -961510934072343007L;
	
	
	/**
	 * Create a new NoContentException.
	 * @param msg the detail message (used as exception message as-is)
	 */
	public NoContentException(String msg) {
		super(msg);
	}

	/**
	 * Create a new NoContentException.
	 * @param msg the detail message (used as exception message as-is)
	 * @param cause the root cause (may be <code>null</code>)
	 */
	public NoContentException(String msg, Throwable cause) {
		super(msg, cause);
	}	
	
	/**
	 * Instantiates a new NoContentException
	 *
	 * @param cause the cause
	 */
	public NoContentException(Throwable cause) {
		super(cause);
	}
	

}
