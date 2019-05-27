package com.blumeglobal.shipmentmanagement.exceptionlist.response;

import java.util.List;

public class ExceptionListResponse {
	private ASNExceptionListResponse asn;
	
	private POExceptionListResponse po;
	
	ExceptionListSizeResponse exceptionListSize;
	
	private List<ExceptionsResponse> exceptions;

	
	public ExceptionListResponse() {
	
	}


	public ASNExceptionListResponse getAsn() {
		return asn;
	}


	public void setAsn(ASNExceptionListResponse asn) {
		this.asn = asn;
	}


	public POExceptionListResponse getPo() {
		return po;
	}


	public void setPo(POExceptionListResponse po) {
		this.po = po;
	}


	public List<ExceptionsResponse> getExceptions() {
		return exceptions;
	}


	public void setExceptions(List<ExceptionsResponse> exceptions) {
		this.exceptions = exceptions;
	}


	public ExceptionListSizeResponse getExceptionListSize() {
		return exceptionListSize;
	}


	public void setExceptionListSize(ExceptionListSizeResponse exceptionListSize) {
		this.exceptionListSize = exceptionListSize;
	}
	
	
}
