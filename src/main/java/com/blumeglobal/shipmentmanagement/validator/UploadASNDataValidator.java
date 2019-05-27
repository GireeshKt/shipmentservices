package com.blumeglobal.shipmentmanagement.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.blumeglobal.shipmentmanagement.request.E2EShipmentAPIRequest;
import com.blumeglobal.shipmentmanagement.request.FreightLineItemsAPIRequest;
import com.blumeglobal.shipmentmanagement.request.UploadASNRequest;
import com.blumeglobal.shipmentmanagement.request.UploadE2EShipmentRequest;
import com.blumeglobal.shipmentmanagement.request.UploadFreightLineItemsRequest;
import com.blumeglobal.shipmentmanagement.response.ExcelErrorRowsResponse;
import com.blumeglobal.shipmentmanagement.utils.DateUtil;
import com.blumeglobal.shipmentmanagement.utils.ASNUtils;



/**
 * The UploadASNDataValidator is used to handle the Validations of Upload ASN
 * Excel Upload Operation
 *
 */
@Component
public class UploadASNDataValidator {

	
	
	
	
	@Autowired
	ASNUtils asnUtil;

	@Autowired
	DateUtil dateUtil;

	public UploadASNDataValidator() {

	}

	public ExcelErrorRowsResponse errorRowCall(String colName, int rowNumber, String msg, String inputValue) {
		ExcelErrorRowsResponse errorValue = new ExcelErrorRowsResponse();
		errorValue.setColumnName(colName);
		errorValue.setRowsNumber(rowNumber);
		errorValue.setErrorMessage(msg);
		errorValue.setSpreadSheetValue(inputValue);
		return errorValue;

	}

	public void validateShipmentRows(UploadE2EShipmentRequest asnRowReq, UploadFreightLineItemsRequest lItemsRowReq,
			int rowindex, List<ExcelErrorRowsResponse> lisoferrorrows, List<E2EShipmentAPIRequest> lisofvalidrows,
			Set<FreightLineItemsAPIRequest> listLiineItems, String userName) {

		E2EShipmentAPIRequest validRow = new E2EShipmentAPIRequest();
		FreightLineItemsAPIRequest lineItems = new FreightLineItemsAPIRequest();

		boolean valid = true;
		if (asnRowReq.getShipmentNumber() != null && !asnRowReq.getShipmentNumber().isEmpty()) {
			validRow.setShipmentNumber(asnRowReq.getShipmentNumber());
		} else {
			lisoferrorrows.add(errorRowCall("Shipment Number", rowindex, "No Shipment Number found..",
					asnRowReq.getShipmentNumber()));
			valid = false;
		}
		validRow.setShippingCompany(asnRowReq.getShippingCompany());
		validRow.setBillToName(asnRowReq.getBillToName());
		validRow.setConsignee(asnRowReq.getConsignee());
		validRow.setWayBillNumber(asnRowReq.getWayBillNumber());
		validRow.setAsnNumber(asnRowReq.getAsnNumber());
		validRow.setUnitId(asnRowReq.getUnitId());
		validRow.setTrackingNumber(asnRowReq.getTrackingNumber());
		validRow.setStatus(asnRowReq.getStatus());

		if (asnRowReq.getExpectedDeliveryDate() != null && !asnRowReq.getExpectedDeliveryDate().isEmpty()) {
			String dtTime = asnRowReq.getExpectedDeliveryDate();
			if (dtTime.contains("/")) {
				dtTime = dtTime.replaceAll("/", "-");
			}
			if(!dtTime.contains(":")) {
				dtTime = dtTime+" 00:00";
			}
			Date convertedDate = DateUtil.convertValidStringToDate(dtTime, "MM-dd-yyyy");
			if (convertedDate != null) {
				validRow.setExpectedDeliveryDate(convertedDate);
			} else {
				lisoferrorrows.add(errorRowCall("DeliveryDate", rowindex, "Invalid DeliveryDate (MM-dd-yyyy)",
						asnRowReq.getExpectedDeliveryDate()));
				valid = false;
			}

		}
		if (asnRowReq.getExpectdPickupDate() != null && !asnRowReq.getExpectdPickupDate().isEmpty()) {
			String dtTime = asnRowReq.getExpectdPickupDate();
			if (dtTime.contains("/")) {
				dtTime = dtTime.replaceAll("/", "-");
			}
			if(!dtTime.contains(":")) {
				dtTime = dtTime+" 00:00";
			}
			Date convertedDate = DateUtil.convertValidStringToDate(dtTime, "MM-dd-yyyy HH:mm");
			if (convertedDate != null) {
				validRow.setExpectdPickupDate(convertedDate);
			} else {
				lisoferrorrows.add(errorRowCall("ExpectedPickupDate", rowindex, "Invalid ExpectedPickupDate(MM-dd-yyyy HH:mm",
						asnRowReq.getExpectdPickupDate()));
				valid = false;
			}

		}
		
		if (asnRowReq.getOrigin() != null && !asnRowReq.getOrigin().isEmpty()) {
			validRow.setOrigin(asnRowReq.getOrigin());
		} else {
			lisoferrorrows.add(errorRowCall("Origin", rowindex, "No Origin found..", asnRowReq.getOrigin()));
			valid = false;
		}
		validRow.setOriginAddress1(asnRowReq.getOriginAddress1());
		validRow.setOriginAddress2(asnRowReq.getOriginAddress2());
		validRow.setOriginCity(asnRowReq.getOriginCity());
		if(asnRowReq.getOriginState().length()<11) {
			validRow.setOriginState(asnRowReq.getOriginState());
		}else {
			lisoferrorrows
			.add(errorRowCall("OriginState", rowindex, "OriginState size should not greater than 10..", asnRowReq.getOriginState()));
	valid = false;
}
		validRow.setOriginZipCode(asnRowReq.getOriginZipCode());
		validRow.setOriginCountry(asnRowReq.getOriginCountry());
		if(asnRowReq.getOriginCountry().length()<3) {
			validRow.setOriginCountry(asnRowReq.getOriginCountry());
		}else {
			lisoferrorrows.add(errorRowCall("OriginCountry", rowindex, "OriginCountry size should not grater than 2..", asnRowReq.getOriginCountry()));
			valid = false;
		}
		if (asnRowReq.getDestination() != null && !asnRowReq.getDestination().isEmpty()) {
			validRow.setDestination(asnRowReq.getDestination());
		} else {
			lisoferrorrows
			.add(errorRowCall("Destination", rowindex, "No Destination found..", asnRowReq.getDestination()));
			valid = false;
		}
		validRow.setDestinationAddress1(asnRowReq.getDestinationAddress1());
		validRow.setDestinationAddress2(asnRowReq.getDestinationAddress2());
		if(asnRowReq.getDestinationState().length()<11) {
			validRow.setDestinationCity(asnRowReq.getDestinationCity());
		}else {
			lisoferrorrows
			.add(errorRowCall("DestinationState", rowindex, "Destination State size should not greater than 10..", asnRowReq.getDestinationState()));
	valid = false;
		}
		
		validRow.setDestinationState(asnRowReq.getDestinationState());
		validRow.setDestinationZipCode(asnRowReq.getDestinationZipCode());
		if(asnRowReq.getDestinationCountry().length()<3) {
			validRow.setDestinationCountry(asnRowReq.getDestinationCountry());
		}else {
			lisoferrorrows.add(errorRowCall("DestinationCountry", rowindex, "DestinationCountry size should not grater than 2..", asnRowReq.getDestinationCountry()));
			valid = false;
		}
		if (asnRowReq.getTotalWeight() != null && !asnRowReq.getTotalWeight().isEmpty()) {
			if (asnUtil.isLongNumber(asnRowReq.getTotalWeight())) {
				validRow.setTotalWeight(Long.parseLong(asnRowReq.getTotalWeight()));
			} else {
				lisoferrorrows
						.add(errorRowCall("TotalWeight", rowindex, "Invalid TotalWeight", asnRowReq.getTotalWeight()));
				valid = false;
			}
		}
		validRow.setTransportationMethod(asnRowReq.getTransportationMethod());
		if (asnRowReq.getShipmentNumberPart() != null && !asnRowReq.getShipmentNumberPart().isEmpty()) {
			if (asnUtil.isLongNumber(asnRowReq.getShipmentNumberPart())) {
				validRow.setShipmentNumberPart(Long.parseLong(asnRowReq.getShipmentNumberPart()));
			} else {
				lisoferrorrows.add(errorRowCall("ShipmentNumberPart", rowindex, "Invalid ShipmentNumberPart",
						asnRowReq.getShipmentNumberPart()));
				valid = false;
			}
		}
		if (asnRowReq.getShipmentNumberTotal() != null && !asnRowReq.getShipmentNumberTotal().isEmpty()) {
			if (asnUtil.isLongNumber(asnRowReq.getShipmentNumberTotal())) {
				validRow.setShipmentNumberTotal(Long.parseLong(asnRowReq.getShipmentNumberTotal()));
			} else {
				lisoferrorrows.add(errorRowCall("ShipmentNumberItems", rowindex, "Invalid ShipmentNumberItems",
						asnRowReq.getShipmentNumberTotal()));
				valid = false;
			}
		}
		if (asnRowReq.getNumberOfUniqItems() != null && !asnRowReq.getNumberOfUniqItems().isEmpty()) {
			if (asnUtil.isLongNumber(asnRowReq.getNumberOfUniqItems())) {
				validRow.setNumberOfUniqItems(Long.parseLong(asnRowReq.getNumberOfUniqItems()));
			} else {
				lisoferrorrows.add(errorRowCall("NumberOfUniqItems", rowindex, "Invalid NumberOfUniqItems",
						asnRowReq.getNumberOfUniqItems()));
				valid = false;
			}
		}
		validRow.setCreatedDateTime(new Date());
		validRow.setCreatedBy(userName);
		validRow.setUpdatedDateTime(new Date());
		validRow.setUpdatedBy(userName);

		String poNumber = asnRowReq.getShipmentNumber() + "," + asnRowReq.getOrigin() + "," + asnRowReq.getDestination()
				+ "," + lItemsRowReq.getPoNumber();
		lineItems.setPoNumber(poNumber);
		if (lItemsRowReq.getItemNumber() != null && !lItemsRowReq.getItemNumber().isEmpty()) {
			lineItems.setItemNumber(lItemsRowReq.getItemNumber());
		} else {
			lisoferrorrows
					.add(errorRowCall("ItemNumber", rowindex, "No ItemNumber found..", lItemsRowReq.getItemNumber()));
			valid = false;
		}
		lineItems.setItemDescription(lItemsRowReq.getItemDescription());
		if (lItemsRowReq.getItemQuantity() != null && !lItemsRowReq.getItemQuantity().isEmpty()) {
			if (asnUtil.isLongNumber(lItemsRowReq.getItemQuantity())) {
				lineItems.setItemQuantity(Long.parseLong(lItemsRowReq.getItemQuantity()));
			} else {
				lisoferrorrows.add(
						errorRowCall("ItemQuantity", rowindex, "Invalid ItemQuantity", lItemsRowReq.getItemQuantity()));
				valid = false;
			}
		}
		lineItems.setItemQuantityUom(lItemsRowReq.getItemQuantityUom());
		if (lItemsRowReq.getCharges() != null && !lItemsRowReq.getCharges().isEmpty()) {
			if (asnUtil.isLongNumber(lItemsRowReq.getCharges())) {
				lineItems.setCharges(Long.parseLong(lItemsRowReq.getCharges()));
			} else {
				lisoferrorrows.add(errorRowCall("Charges", rowindex, "Invalid Charges", lItemsRowReq.getCharges()));
				valid = false;
			}
		}
		lineItems.setContentDescription(lItemsRowReq.getContentDescription());
		lineItems.setWeightQualifier(lItemsRowReq.getWeightQualifier());
		lineItems.setCreatedDateTime(new Date());
		lineItems.setCreatedBy(userName);
		lineItems.setUpdatedDateTime(new Date());
		lineItems.setUpdatedBy(userName);
		if (valid) {
			listLiineItems.add(lineItems);
			lisofvalidrows.add(validRow);
		}
	}

	public void setFreightLineItems(List<E2EShipmentAPIRequest> lisofvalidrows,
			Set<FreightLineItemsAPIRequest> lisofvalidrows2) {

		Map<String, E2EShipmentAPIRequest> duplicateIdentificationMap = new HashMap<String, E2EShipmentAPIRequest>();

		for (int i = 0; i < lisofvalidrows.size(); i++) {
			if (duplicateIdentificationMap.get(lisofvalidrows.get(i).getShipmentNumber()
					+ lisofvalidrows.get(i).getOrigin() + lisofvalidrows.get(i).getDestination()) == null) {
				duplicateIdentificationMap.put(lisofvalidrows.get(i).getShipmentNumber()
						+ lisofvalidrows.get(i).getOrigin() + lisofvalidrows.get(i).getDestination(),
						lisofvalidrows.get(i));
			}
		}
		Map<String, FreightLineItemsAPIRequest> duplicateIdentificationMap2 = new HashMap<String, FreightLineItemsAPIRequest>();
		 List<FreightLineItemsAPIRequest> lineItems = new ArrayList<FreightLineItemsAPIRequest>(); 
		 for (FreightLineItemsAPIRequest x : lisofvalidrows2) { 
			 lineItems.add(x); 
		 }
		 for (int i = 0; i < lineItems.size(); i++) {
			 String a = lineItems.get(i).getItemNumber();
			 System.out.println(lineItems.get(i).getItemNumber());
				if (duplicateIdentificationMap2.get(lineItems.get(i).getItemNumber()) == null) {
					duplicateIdentificationMap2.put(lineItems.get(i).getItemNumber(),lineItems.get(i));
				}
			}
				
				

		List<E2EShipmentAPIRequest> processedShipments = new ArrayList<E2EShipmentAPIRequest>(
				duplicateIdentificationMap.values());
		Set<FreightLineItemsAPIRequest> processedLineItems = new HashSet<>(duplicateIdentificationMap2.values());
		
		lisofvalidrows.clear();
		lisofvalidrows2.clear();
		lisofvalidrows.addAll(processedShipments);
		lisofvalidrows2.addAll(processedLineItems);
		for (int i = 0; i < lisofvalidrows.size(); i++) {
			Iterator itr = lisofvalidrows2.iterator();
			Set<FreightLineItemsAPIRequest> lineitems = new HashSet<FreightLineItemsAPIRequest>();
			while (itr.hasNext()) {

				FreightLineItemsAPIRequest lItems = (FreightLineItemsAPIRequest) itr.next();
				String poNumber = lItems.getPoNumber();
				String[] shipment = poNumber.split(",");
				String anum = shipment[0];
				if (lisofvalidrows.get(i).getShipmentNumber().equalsIgnoreCase(shipment[0])
						&& lisofvalidrows.get(i).getOrigin().equalsIgnoreCase(shipment[1])
						&& lisofvalidrows.get(i).getDestination().equalsIgnoreCase(shipment[2])) {
					lineitems.add(lItems);
					lisofvalidrows.get(i).setFreightLineItems(lineitems);
				}
			}
		}

	}

}
