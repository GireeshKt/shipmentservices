/**
 * 
 */
package com.blumeglobal.shipmentmanagement.external.hub.endpoint.response;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.ias.hub.core.dto.OrganizationDTO;

/**
 * @author Harish.Sharma
 *
 */
@XmlRootElement(name = "OrganizationDTO")
public class OrganizationResponse extends BaseResponse implements Serializable {


	private static final long serialVersionUID = 1L;
	private List<OrganizationDTO>  data;
    private OrganizationDTO errors;
	/**
	 * @return the data
	 */
	public List<OrganizationDTO> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<OrganizationDTO> data) {
		this.data = data;
	}
	/**
	 * @return the errors
	 */
	public OrganizationDTO getErrors() {
		return errors;
	}
	/**
	 * @param errors the errors to set
	 */
	public void setErrors(OrganizationDTO errors) {
		this.errors = errors;
	}

}
