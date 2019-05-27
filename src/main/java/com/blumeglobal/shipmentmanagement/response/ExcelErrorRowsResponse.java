package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;
/**
 * The ExcelErrorRowsResponse is used to handle the response of Error Rows from Upload Excel handling Operation
 *
 */
public class ExcelErrorRowsResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private int rowsNumber;
	private String columnName;
	private String spreadSheetValue;
	private String errorMessage;
	
	
	public ExcelErrorRowsResponse() {
		
	}


	public ExcelErrorRowsResponse(int rowsNumber, String columnName, String spreadSheetValue, String errorMessage) {
		super();
		this.rowsNumber = rowsNumber;
		this.columnName = columnName;
		this.spreadSheetValue = spreadSheetValue;
		this.errorMessage = errorMessage;
	}


	public int getRowsNumber() {
		return rowsNumber;
	}


	public void setRowsNumber(int rowsNumber) {
		this.rowsNumber = rowsNumber;
	}


	public String getColumnName() {
		return columnName;
	}


	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}


	public String getSpreadSheetValue() {
		return spreadSheetValue;
	}


	public void setSpreadSheetValue(String spreadSheetValue) {
		this.spreadSheetValue = spreadSheetValue;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}