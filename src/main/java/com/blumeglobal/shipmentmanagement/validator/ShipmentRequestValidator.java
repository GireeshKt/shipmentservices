package com.blumeglobal.shipmentmanagement.validator;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.request.common.GenericShipmentRequest;
import com.blumeglobal.shipmentmanagement.request.common.LocationDetail;
import com.blumeglobal.shipmentmanagement.utils.ShipmentUtil;

@Component
public class ShipmentRequestValidator {
	
	public boolean validateCreateShipmentRequest(GenericShipmentRequest genericShipmentRequest,List<String> errorMsgList,StringBuilder errorMsg) {
		boolean validflag = true;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<GenericShipmentRequest>> violations = validator.validate(genericShipmentRequest);
		for (ConstraintViolation<GenericShipmentRequest> violation : violations) {
			String message = violation.getMessage();
			errorMsgList.add(message);
			errorMsg.append(message);
			errorMsg.append(";");
			validflag = false;
		}
        // if (createWorkOrderRequest.getEta() == null)
        // throw new InValidDataException("ETA date is required");

		boolean validlocation = validateLocationDetail(genericShipmentRequest.getShipFrom(), errorMsgList, errorMsg, "origin location");
		if(!validlocation) validflag =validlocation;
		
        validlocation = validateLocationDetail(genericShipmentRequest.getShipTo(), errorMsgList, errorMsg, "destination location");
        if(!validlocation) validflag =validlocation;
		
		return validflag;
	}
	
	private boolean validateLocationDetail(LocationDetail locationDetail, List<String> errorMsgList,
            StringBuilder errorMsg, String locationTag) {
	    //DM country code only allow 2 letters
	    if(!ShipmentUtil.isNullString(locationDetail.getCountry())
	            &&locationDetail.getCountry().length()>2) {
	        errorMsgList.add("Country code more than 2 letters for "+locationTag);
	        errorMsg.append("Country code more than 2 letters for "+locationTag);
	        return false;
	    }
	    
        //unlocode
        if(!ShipmentUtil.isNullString(locationDetail.getUnlocode())) return true;
        
        //name, city, state, zip        
        if(!ShipmentUtil.isNullString(locationDetail.getLocationName())
                &&!ShipmentUtil.isNullString(locationDetail.getCity())
                &&!ShipmentUtil.isNullString(locationDetail.getState())
                &&!ShipmentUtil.isNullString(locationDetail.getZip())) {
            return true;
        }
            
        //address1, address2, city, state, zip, country
        if(!ShipmentUtil.isNullString(locationDetail.getAddress1())
                &&!ShipmentUtil.isNullString(locationDetail.getCity())
                &&!ShipmentUtil.isNullString(locationDetail.getState())
                &&!ShipmentUtil.isNullString(locationDetail.getZip())
                &&!ShipmentUtil.isNullString(locationDetail.getCountry())
                ) {
            return true;
        }
        
        //TODO move to message file 
        errorMsgList.add("Invalid location details for "+locationTag);
        errorMsg.append("Invalid location details for "+locationTag);

        return false;
    }

	
}

	
	
		
	


