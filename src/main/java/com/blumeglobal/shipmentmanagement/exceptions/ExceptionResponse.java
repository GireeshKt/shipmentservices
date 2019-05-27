package com.blumeglobal.shipmentmanagement.exceptions;

import java.io.Serializable;
import java.util.Date;
/**
 * The ExceptionResponse bean class is used to handle the error response.
 *
 */
public class ExceptionResponse  implements Serializable  {


	private static final long serialVersionUID = 1L;
	
	private String exceptionCode;
	public String getExceptionCode() {
		return exceptionCode;
	}
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	private String exceptionMessage;
    private Date timestamp = new Date();

	

	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}		
}
