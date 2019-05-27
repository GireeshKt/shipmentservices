package com.blumeglobal.shipmentmanagement.external.hub.endpoint.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.ias.hub.core.dto.UserGroupsDTO;

@XmlRootElement(name = "UserGroupsDTO")
public class UserGroupResponse extends BaseResponse {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The data. */
	private List<UserGroupsDTO>  data;
    
    /** The errors. */
    private UserGroupsDTO errors;

	/**
	 * @return the data
	 */
	public List<UserGroupsDTO> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<UserGroupsDTO> data) {
		this.data = data;
	}

	/**
	 * @return the errors
	 */
	public UserGroupsDTO getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(UserGroupsDTO errors) {
		this.errors = errors;
	}
    
    
}
