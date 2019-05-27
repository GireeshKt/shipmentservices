/**
 * 
 */
package com.blumeglobal.shipmentmanagement.external.hub.endpoint.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.ias.hub.core.dto.UsersDTO;


/**
 * This class represents the response of Users entity
 * which contains the requested Users entity data.
 */

@XmlRootElement(name = "UsersDTO")
public class UsersResponse extends BaseResponse {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The data. */
	private List<UsersDTO>  data;
    
    /** The errors. */
    private UsersDTO errors;
	
	/**
	 * Gets the data.
	 * 
	 * @return the data
	 */
	public List<UsersDTO> getData() {
		return data;
	}
	
	/**
	 * Sets the data.
	 * 
	 * @param data the data to set
	 */
	public void setData(List<UsersDTO> data) {
		this.data = data;
	}
	
	/**
	 * Gets the errors.
	 * 
	 * @return the errors
	 */
	public UsersDTO getErrors() {
		return errors;
	}
	
	/**
	 * Sets the errors.
	 * 
	 * @param errors the errors to set
	 */
	public void setErrors(UsersDTO errors) {
		this.errors = errors;
	}
	
  
    
}
