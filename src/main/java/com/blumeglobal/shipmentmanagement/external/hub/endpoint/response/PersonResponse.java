package com.blumeglobal.shipmentmanagement.external.hub.endpoint.response;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.ias.hub.core.dto.PersonDTO;



@XmlRootElement(name = "PersonDTO")
public class PersonResponse extends BaseResponse  implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<PersonDTO>  data;
    private PersonDTO errors;

    /**
	 * Returns the List of Person objects
	 * @return  data
	 */
	public List<PersonDTO> getData() {
		return data;
	}

	/**
	 * Sets the List of Person objects
	 * @param data the data to set
	 */
	public void setData(List<PersonDTO> data) {
		this.data = data;
	}

	/**
	 * Returns the erroneous Person object
	 * @return  errors
	 */
	
	public PersonDTO getErrors() {
		return errors;
	}

	/**
	 * Sets the erroneous Person object
	 * @param errors the errors to set
	 */
	 
	public void setErrors(PersonDTO errors) {
		this.errors = errors;
	}
   
}
