package com.blumeglobal.shipmentmanagement.validator;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.enums.ReportSource;
import com.blumeglobal.shipmentmanagement.exceptions.AdapterServiceException;
import com.blumeglobal.shipmentmanagement.external.hub.adapter.IOrganizationAdapter;
import com.blumeglobal.shipmentmanagement.external.hub.adapter.OrganizationAdapterImpl;
import com.blumeglobal.shipmentmanagement.request.ShipmentEventRequest;
import com.blumeglobal.shipmentmanagement.utils.DateUtil;

@Component
public class EventsValidator {
	
	public boolean validateCreateEventsRequest(ShipmentEventRequest createEventsRequest,List<String> errorMsgList,StringBuilder errorMsg) {
		boolean validflag = true;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<ShipmentEventRequest>> violations = validator.validate(createEventsRequest);
		for (ConstraintViolation<ShipmentEventRequest> violation : violations) {
			String message = violation.getMessage();
			errorMsgList.add(message);
			errorMsg.append(message);
			errorMsg.append(";");
			validflag = false;
		}
		java.util.Date dtUtil = DateUtil.convertStringToDate(createEventsRequest.getEventTime(),
				"MM-dd-yyyy HH:mm:ss");
		if (dtUtil == null) {
			errorMsgList.add("shipmentmanagement.eventrequest.eventtime.format");
			errorMsg.append("shipmentmanagement.eventrequest.eventtime.format");
			validflag = false;
		}	
		
		if ((createEventsRequest.getBookingNumber() == null) && (createEventsRequest.getBillOfLadingNumber() == null)&& (createEventsRequest.getUnitId() == null)
				&& (createEventsRequest.getAssociatedUnitId() == null) && (createEventsRequest.getHouseBill() == null)
				&& (createEventsRequest.getMasterBill() == null) && (createEventsRequest.getOnHandNumber() == null)
				&& (createEventsRequest.getShipmentReferenceNumber() == null) && (createEventsRequest.getRailBillingNumber() == null)
				&& (createEventsRequest.getWorkOrderNumber() == null)) {
			validflag = false;
			errorMsgList.add("Any one of the following must be present: BookingNumber, BillOfLadingNumber,UnitId,AssociatedUnitId, HouseBill, MasterBill, OnHandNumber, ShipmentReferenceNumber, RailBillingNumber, WorkOrderNumber");
			errorMsg.append("Any one of the following must be present:  BookingNumber, BillOfLadingNumber,UnitId,AssociatedUnitId, HouseBill, MasterBill, OnHandNumber, ShipmentReferenceNumber, RailBillingNumber, WorkOrderNumber");
		}
		
		if(!ReportSource.isValid(createEventsRequest.getReportSource())) {
		  validflag = false;
		  errorMsgList.add("Please provide valid ReportSource");
		  errorMsg.append("Please provide valid ReportSource");
		}
		
		return validflag;
	}
	
	public boolean validateHub(ShipmentEventRequest createEventsRequest,List<String> errorMsgList,StringBuilder errorMsg) throws AdapterServiceException {
		boolean validflag = true;
		IOrganizationAdapter iOrganizationAdapter = new OrganizationAdapterImpl();
		if (createEventsRequest.getOriginatorCode() == null
			|| !iOrganizationAdapter.validateOriginatorCode(createEventsRequest.getOriginatorCode())) {
			validflag = false;
			errorMsgList.add("shipmentmanagement.eventrequest.originator.invalid");
			errorMsg.append("shipmentmanagement.eventrequest.originator.invalid");
		}
		
		if (createEventsRequest.getReceiverCode() == null
				|| !iOrganizationAdapter.validateReceiverCode(createEventsRequest.getReceiverCode())) {
			validflag = false;
			errorMsgList.add("Receiver Code is invalid");
			errorMsg.append("Receiver Code is invalid");
			}
		return validflag;
	}
	
}

	
	
		
	


