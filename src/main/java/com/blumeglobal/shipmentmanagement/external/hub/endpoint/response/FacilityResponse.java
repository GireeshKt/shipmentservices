package com.blumeglobal.shipmentmanagement.external.hub.endpoint.response;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.ias.hub.core.dto.FacilityDTO;




@XmlRootElement(name = "FacilityDTO")
public class FacilityResponse extends BaseResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<FacilityDTO>  data;
    private FacilityDTO errors;

    /**
	 * Returns the List of Facility objects
	 * @return  data
	 */
	public List<FacilityDTO> getData() {
		return data;
	}

	/**
	 * Sets the List of Facility objects
	 * @param data the data to set
	 */
	public void setData(List<FacilityDTO> data) {
		this.data = data;
	}

	/**
	 * Returns the erroneous Facility object
	 * @return  errors
	 */
	public FacilityDTO getErrors() {
		return errors;
	}

	/**
	 * Sets the erroneous Facility object
	 * @param errors the errors to set
	 */
	public void setErrors(FacilityDTO errors) {
		this.errors = errors;
	}


}
