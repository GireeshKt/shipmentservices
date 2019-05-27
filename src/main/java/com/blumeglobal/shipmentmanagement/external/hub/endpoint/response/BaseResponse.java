package com.blumeglobal.shipmentmanagement.external.hub.endpoint.response;

/**
 * This is an abstract class containing getters and setters methods
 * for startRow,endRow,totalRows and status properties of a response
 * 
 * @author Ashish Gupta
 */
public abstract class BaseResponse {
	
    private int startRow ;
    private int endRow;
    private int totalRows; 
    private int status  = 200;
    
    
    /**
	 * Returns an int object
	 * @return  startRow
	 */
	public int getStartRow() {
		return startRow;
	}
	
	/**
	 * Sets an int object startRow
	 * @param startRow
	 */
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	
	/**
	 * Returns an int object endRow
	 * @return endRow
	 */
	public int getEndRow() {
		return endRow;
	}
	
	/**
	 * Sets an int object endRow
	 * @param endRow 
	 */
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	
	/**
	 * Returns an int object totalRows
	 * @return totalRows
	 */
	public int getTotalRows() {
		return totalRows;
	}
	
	/**
	 * Sets an int object totalRows
	 * @param totalRows the totalRows to set
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	
	/**
	 * Returns the status
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * Sets the status 
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
    

}
