package com.blumeglobal.shipmentmanagement.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * The InValidDataException is used to handle invalid input exceptions.
 *
 */
@ResponseStatus(HttpStatus.OK)
public class InValidDataException  extends Rez1BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -961510934072343007L;
	
	private List<String> errorList;
	/**
	 * Create a new InValidDataException.
	 * @param msg the detail message (used as exception message as-is)
	 */
	public InValidDataException(String msg) {
		super(msg);
	}

	/**
	 * Create a new InValidDataException.
	 * @param msg the detail message (used as exception message as-is)
	 * @param cause the root cause (may be <code>null</code>)
	 */
	public InValidDataException(String msg, Throwable cause) {
		super(msg, cause);
	}	
	
	/**
	 * Instantiates a new InValidDataException
	 *
	 * @param cause the cause
	 */
	public InValidDataException(Throwable cause) {
		super(cause);
	}
	
	public List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}

}
