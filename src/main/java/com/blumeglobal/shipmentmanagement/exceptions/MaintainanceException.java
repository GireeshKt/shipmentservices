package com.blumeglobal.shipmentmanagement.exceptions;
/**
 * The MaintainanceException is used to handle custom maintenance related exceptions.
 *
 */
public class MaintainanceException   extends Rez1BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -961510934072343007L;
	
	/**
	 * Create a new MaintainanceException.
	 * @param msg the detail message (used as exception message as-is)
	 */
	public MaintainanceException(String msg) {
		super(msg);
	}

	/**
	 * Create a new MaintainanceException.
	 * @param msg the detail message (used as exception message as-is)
	 * @param cause the root cause (may be <code>null</code>)
	 */
	public MaintainanceException(String msg, Throwable cause) {
		super(msg, cause);
	}	
	
	/**
	 * Instantiates a new MaintainanceException
	 *
	 * @param cause the cause
	 */
	public MaintainanceException(Throwable cause) {
		super(cause);
	}
	
}
