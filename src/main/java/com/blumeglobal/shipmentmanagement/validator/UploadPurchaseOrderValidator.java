package com.blumeglobal.shipmentmanagement.validator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.dao.repositories.PurchaseOrderRepository;
import com.blumeglobal.shipmentmanagement.enums.Constants;
import com.blumeglobal.shipmentmanagement.request.ExcelPoLineItemsRequest;
import com.blumeglobal.shipmentmanagement.request.ExcelPurchaseOrderSuccessRowsRequest;
import com.blumeglobal.shipmentmanagement.request.POLineItemsAPIRequest;
import com.blumeglobal.shipmentmanagement.request.POTransactionsAPIRequest;
import com.blumeglobal.shipmentmanagement.response.ExcelPurchaseOrderErrorRowsResponse;
import com.blumeglobal.shipmentmanagement.response.ExcelPurchaseOrderSuccessRowsResponse;
import com.blumeglobal.shipmentmanagement.utils.DateUtils;
import com.blumeglobal.shipmentmanagement.utils.PurchaseOrderUtil;


/**
 * The UploadEventDataValidator is used to handle the Validations of PO Excel Upload Operation
 *
 */
@Component
public class UploadPurchaseOrderValidator {
	private static final Logger logger = LogManager.getLogger(UploadPurchaseOrderValidator.class);
	Constants success = Constants.valueOf("SUCCESS");
	Constants failure = Constants.valueOf("FAILURE");
	Constants system =  Constants.valueOf("SYSTEM");
	Constants create =  Constants.valueOf("ORDERPLACED");
	Constants active =  Constants.valueOf("ACTIVE");
	Constants inactive =  Constants.valueOf("INACTIVE");
	Constants updated =  Constants.valueOf("UPDATED");
	Constants orderShipped = Constants.valueOf("ORDERSHIPPED");
	Constants orderUploaded = Constants.valueOf("ORDERUPLOADED");

	@Autowired
	PurchaseOrderRepository poRepository;

	@Autowired
	PurchaseOrderUtil poUtil;

	private int draftRowsCount = 0;
	private int pendingRowsCount = 0;

	public UploadPurchaseOrderValidator() {

	}

	public UploadPurchaseOrderValidator(int draftRowsCount, int pendingRowsCount) {
		this.draftRowsCount = draftRowsCount;
		this.pendingRowsCount = pendingRowsCount;  
	}

	public int getDraftRowsCount() {
		return draftRowsCount;
	}

	public void setDraftRowsCount(int draftRowsCount) {
		this.draftRowsCount = draftRowsCount;
	}

	public int getPendingRowsCount() {
		return pendingRowsCount;
	}

	public void setPendingRowsCount(int pendingRowsCount) {
		this.pendingRowsCount = pendingRowsCount;
	}

	/**
	 * This method is used to validate the excel data is valid or not
	 * 
	 * @param inputRows	This is used to pass input UploadPurchaseOrderRequest
	 * @param inputRows	This is used to pass input UploadPurchaseOrderLineItemRequest
	 * @param rowNumber This is used to pass int
	 * @param lisoferrorrows This is used to pass List of ExcelErrorRowsResponse
	 * @param lisofvalidrows This is used to pass List of UploadPurchaseOrderResponse
	 * @param lisofvalidrows This is used to pass List of UploadPurchaseOrderLineItemResponse
	 */
	public void validateRows(ExcelPurchaseOrderSuccessRowsRequest inputRows, ExcelPoLineItemsRequest lineItemRows, int rowNumber, 
			List<ExcelPurchaseOrderErrorRowsResponse> errorlist, List<ExcelPurchaseOrderSuccessRowsResponse> lisofvalidrows, Set<POLineItemsAPIRequest> listOfValidLineItemsRows,
			String userName, String organizationCode){
		boolean validrow = true;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<ExcelPurchaseOrderSuccessRowsRequest>> violations = validator.validate(inputRows);
		for (ConstraintViolation<ExcelPurchaseOrderSuccessRowsRequest> violation : violations) {
			String message = violation.getMessage();
			Object invalidValue = violation.getInvalidValue();
			String value = invalidValue.toString();
			errorlist.add(errorRowData(message.split(":")[0], rowNumber, message.split(":")[1], value));
			validrow = false;
		}

		ExcelPurchaseOrderSuccessRowsResponse validRow = new ExcelPurchaseOrderSuccessRowsResponse();
		try {
			//Validation of Purchase Order Number
			if( inputRows.getPurchaseOrderNumber() != null && !inputRows.getPurchaseOrderNumber().isEmpty()) {
				validRow.setPurchaseOrderNumber(inputRows.getPurchaseOrderNumber());

			} else {
				errorlist.add(errorRowData("PurchaseOrderNumber", rowNumber, "Purchase Order Number required", inputRows.getPurchaseOrderNumber()));
				validrow = false;
			}

			if (inputRows.getPoIssueDate() != null && !inputRows.getPoIssueDate().isEmpty()) {
				String dtTime = inputRows.getPoIssueDate();
				if (dtTime.contains("/")) {
					dtTime = dtTime.replaceAll("/", "-");
				}
				Date convertedDate = DateUtils.convertValidStringToDate(dtTime, "MM-dd-yyyy");
				if (convertedDate != null) {
					validRow.setPoReceivedDate(convertedDate);
				} else {
					errorlist.add(errorRowData("PoIssueDate", rowNumber, "Invalid PoIssueDate",
							inputRows.getPoIssueDate()));
					validrow = false;
				}
			}

			if (inputRows.getPoResponseDate() != null && !inputRows.getPoResponseDate().isEmpty()) {
				String dtTime = inputRows.getPoResponseDate();
				if (dtTime.contains("/")) {
					dtTime = dtTime.replaceAll("/", "-");
				}
				Date convertedDate = DateUtils.convertValidStringToDate(dtTime, "MM-dd-yyyy");
				if (convertedDate != null) {
					validRow.setPoPromisedDate(convertedDate);
				} else {
					errorlist.add(errorRowData("ResponseDate", rowNumber, "Invalid ResponseDate",
							inputRows.getPoResponseDate()));
					validrow = false;
				}
			}

			//Validation of Vendor Name
			if (inputRows.getVendorName() != null && !inputRows.getVendorName().isEmpty()) {
				validRow.setVendorName(inputRows.getVendorName());
			} else {
				errorlist.add(errorRowData("VendorName", rowNumber, "VendorName required", inputRows.getVendorName()));
				validrow = false;
			}
			validRow.setVendorDuns(inputRows.getVendorDuns());
			validRow.setVendorAddress(inputRows.getVendorAddress());
			validRow.setVendorCity(inputRows.getVendorCity());
			validRow.setVendorState(inputRows.getVendorState());
			validRow.setVendorZip(inputRows.getVendorZip());
			validRow.setVendorCountry(inputRows.getVendorCountry());
			validRow.setLogisticProvider(inputRows.getLogisticProvider());

			//Validation of Buyer_name
			if (inputRows.getBuyerName() != null && !inputRows.getBuyerName().isEmpty()) {
				validRow.setBuyerName(inputRows.getBuyerName());
			} else {
				errorlist.add(errorRowData("BuyerName", rowNumber, "BuyerName required", inputRows.getBuyerName()));
				validrow = false;
			}
			validRow.setBuyerDuns(inputRows.getBuyerDuns());
			validRow.setBillingAddress(inputRows.getBillingAddress());
			validRow.setPoReferenceId(inputRows.getPoReferenceId());
			validRow.setPoExpenditureCodeId(inputRows.getPoExpenditureCodeId());
			validRow.setPoRequistionedBy(inputRows.getPoRequistionedBy());
			if (poUtil.isLongNumber(inputRows.getPoTax())) {
				validRow.setPoTax(Long.parseLong(inputRows.getPoTax()));
			} else {
				errorlist.add(errorRowData("PoTax", rowNumber, "InValid PoTax found.. PoTax should be number",inputRows.getPoTax()));
				validrow = false;
			}
			validRow.setPoFreightHandling(inputRows.getPoFreightHandling());
			validRow.setPoStatus(inputRows.getPoStatus());
			if (poUtil.isLongNumber(inputRows.getLeadTime())) {
				validRow.setLeadTime(Long.parseLong(inputRows.getLeadTime()));
			} else {
				errorlist.add(errorRowData("LeadTime", rowNumber, "InValid LeadTime found.. LeadTime should be number",inputRows.getLeadTime()));
				validrow = false;
			}

			if (inputRows.getPODate() != null && !inputRows.getPODate().isEmpty()) {
				String dtTime = inputRows.getPODate();
				if (dtTime.contains("/")) {
					dtTime = dtTime.replaceAll("/", "-");
				}
				Date convertedDate = DateUtils.convertValidStringToDate(dtTime, "MM-dd-yyyy");
				if (convertedDate != null) {
					validRow.setPoDate(DateUtils.convertStringToTimestamp(dtTime, "MM-dd-yyyy"));
				} else {
					errorlist.add(errorRowData("PODate", rowNumber, "Invalid PODate",
							inputRows.getPODate()));
					validrow = false;
				}
			}

			validRow.setPoNotes(inputRows.getNotes());
			validRow.setOrigin(inputRows.getOrigin());
			validRow.setDestination(inputRows.getDestination());
			if (!inputRows.getPoStatus().isEmpty() && inputRows.getPoStatus()!= null) {
				validRow.setPoStatus(inputRows.getPoStatus());
			} else {
				validRow.setPoStatus(create.getConstants());
			}

			validRow.setDeleteFlag(active.getConstants());
			Date curDate = new Date();
			String dateToStr;
			try {
				SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
				dateToStr = format.format(curDate);
			} catch (Exception ex) {
				throw ex;
			}
			validRow.setCreatedOn(dateToStr);
			validRow.setUpdatedOn(dateToStr);

			if(userName!=null && organizationCode !=null) {
				validRow.setCreatedBy(userName);
				validRow.setUpdatedBy(userName);
				validRow.setOrganizationCode(organizationCode);
			} else {
				validRow.setCreatedBy(system.getConstants());
				validRow.setUpdatedBy(system.getConstants());
			}

			//Validation of POLineItems
			POLineItemsAPIRequest poLinereq = new POLineItemsAPIRequest();
			String skuNumber = inputRows.getPurchaseOrderNumber() + "," + lineItemRows.getSkuNumber();
			poLinereq.setSkuNumber(skuNumber);

			poLinereq.setLineItemStatus(create.getConstants());
			poLinereq.setSkuDescription(lineItemRows.getSkuDescription());
			if (poUtil.isLongNumber(lineItemRows.getQuantity())) {
				poLinereq.setQuantity(Long.parseLong(lineItemRows.getQuantity()));
			} else {
				errorlist.add(errorRowData("Quantity", rowNumber, "InValid Quantity found.. Quantity should be number", lineItemRows.getQuantity()));
				validrow = false;
			}
			if (poUtil.isLongNumber(lineItemRows.getCommitedQuantity())) {
				if((Long.parseLong(lineItemRows.getCommitedQuantity())) <= (Long.parseLong(lineItemRows.getQuantity()))){
					poLinereq.setCommitedQuantity(Long.parseLong(lineItemRows.getCommitedQuantity()));
				} else {
					errorlist.add(errorRowData("CommittedQuantity", rowNumber, "Committed Quantity should be less than or equal to Quantity", lineItemRows.getCommitedQuantity()));
					validrow = false;
				}
			} else {
				errorlist.add(errorRowData("CommitedQuantity", rowNumber, "InValid CommitedQuantity found.. CommitedQuantity should be number", lineItemRows.getCommitedQuantity()));
				validrow = false;
			}

			if (lineItemRows.getDueDate() != null && !lineItemRows.getDueDate().isEmpty()) {
				String dtTime = lineItemRows.getDueDate();
				if (dtTime.contains("/")) {
					dtTime = dtTime.replaceAll("/", "-");
				}
				Date convertedDate = DateUtils.convertValidStringToDate(dtTime, "MM-dd-yyyy");
				if (convertedDate != null) {
					poLinereq.setDueDate(convertedDate);
				} else {
					errorlist.add(errorRowData("DueDate", rowNumber, "Invalid DueDate",
							lineItemRows.getDueDate()));
					validrow = false;
				}
			}

			if (lineItemRows.getPromisedDate() != null && !lineItemRows.getPromisedDate().isEmpty()) {
				String dtTime = lineItemRows.getPromisedDate();
				if (dtTime.contains("/")) {
					dtTime = dtTime.replaceAll("/", "-");
				}
				Date convertedDate = DateUtils.convertValidStringToDate(dtTime, "MM-dd-yyyy");
				if (convertedDate != null) {
					poLinereq.setPromisedDate(convertedDate);
				} else {
					errorlist.add(errorRowData("PromisedDate", rowNumber, "Invalid PromisedDate",
							lineItemRows.getPromisedDate()));
					validrow = false;
				}
			}

			poLinereq.setUnitOfMeasurement(lineItemRows.getUnitOfMeasurement());
			if (poUtil.isDouble(lineItemRows.getPrice())) {
				poLinereq.setPrice(Double.parseDouble(lineItemRows.getPrice()));
			} else {
				errorlist.add(errorRowData("Price", rowNumber, "InValid Price found..Price should be Number",lineItemRows.getPrice()));
				validrow = false;
			}
			poLinereq.setShipToLocationCode(lineItemRows.getShipToLocationCode());
			poLinereq.setShipToCompany(lineItemRows.getShipToCompany());
			poLinereq.setShipToAddress(lineItemRows.getShipToAddress());
			poLinereq.setShipToCity(lineItemRows.getShipToCity());
			poLinereq.setShipToState(lineItemRows.getShipToState());
			poLinereq.setShipToZip(lineItemRows.getShipToZip());
			poLinereq.setLineItemStatus(orderUploaded.getConstants());
			poLinereq.setShipToCountry(lineItemRows.getShipToCountry());

			poLinereq.setCreatedOn(dateToStr);
			poLinereq.setUpdatedOn(dateToStr);

			if(userName!=null) {
				poLinereq.setCreatedBy(userName);
				poLinereq.setUpdatedBy(userName);
			} else {
				poLinereq.setCreatedBy(system.getConstants());
				poLinereq.setUpdatedBy(system.getConstants());
			}
			poLinereq.setDeleteFlag(active.getConstants());

			//Setting of POTransactions for Order placed status
			Set<POTransactionsAPIRequest> poTransaction = new HashSet<>();
			POTransactionsAPIRequest poTransactions = new POTransactionsAPIRequest();
			poTransactions.setPoTransactionStatus(create.getConstants());
			poTransactions.setPoTransactionDate(validRow.getPoReceivedDate());
			poTransactions.setPoStatus(create.getConstants());
			poTransactions.setCreatedOn(dateToStr);
			poTransactions.setUpdatedOn(dateToStr);
			poTransactions.setDeleteFlag(active.getConstants());

			if(userName!=null && organizationCode !=null) {
				poTransactions.setCreatedBy(userName);
				poTransactions.setUpdatedBy(userName);
				poTransactions.setOrganizationCode(organizationCode);
			} else {
				poTransactions.setCreatedBy(system.getConstants());
				poTransactions.setUpdatedBy(system.getConstants());
			}
			poTransaction.add(poTransactions);

			//Setting of POTransactions for Order Uploaded status
			POTransactionsAPIRequest poTransactionsUpload = new POTransactionsAPIRequest();
			poTransactionsUpload.setPoTransactionStatus(create.getConstants());
			poTransactionsUpload.setPoTransactionDate(DateUtils.convertStringToTimestamp(dateToStr, "MM-dd-yyyy HH:mm:ss"));
			poTransactionsUpload.setPoStatus(orderUploaded.getConstants());
			poTransactionsUpload.setCreatedOn(dateToStr);
			poTransactionsUpload.setUpdatedOn(dateToStr);
			poTransactionsUpload.setDeleteFlag(active.getConstants());

			if(userName!=null && organizationCode !=null) {
				poTransactionsUpload.setCreatedBy(userName);
				poTransactionsUpload.setUpdatedBy(userName);
				poTransactionsUpload.setOrganizationCode(organizationCode);
			} else {
				poTransactionsUpload.setCreatedBy(system.getConstants());
				poTransactionsUpload.setUpdatedBy(system.getConstants());
			}
			poTransaction.add(poTransactionsUpload);
			poLinereq.setPoTransactions(poTransaction);

			if ("DRAFT".equals(validRow.getStatus()) && validrow) {
				draftRowsCount = draftRowsCount + 1;
			} else if ("PENDING".equals(validRow.getStatus()) && validrow) {
				pendingRowsCount = pendingRowsCount + 1;
			}

			if (validrow) {
				listOfValidLineItemsRows.add(poLinereq);
				lisofvalidrows.add(validRow);
			}
		} catch (Exception ee) {
			logger.error("Validation Exception"+ee.getMessage());
			errorlist.add(errorRowData("Some column", rowNumber,
					"Something went wrong while validate row data", "Some Cell value"));
		}
	}

	public void setPoLineItems(List<ExcelPurchaseOrderSuccessRowsResponse> lisofvalidrows, Set<POLineItemsAPIRequest> lisofvalidrows2)
	{
		Map<String, ExcelPurchaseOrderSuccessRowsResponse> duplicateIdentificationMap = new HashMap<String, ExcelPurchaseOrderSuccessRowsResponse>();

		for (int i = 0; i < lisofvalidrows.size(); i++) {
			if (duplicateIdentificationMap.get(lisofvalidrows.get(i).getPurchaseOrderNumber()) == null) {
				duplicateIdentificationMap.put(lisofvalidrows.get(i).getPurchaseOrderNumber(), lisofvalidrows.get(i));
			}
		}

		Map<String, POLineItemsAPIRequest> duplicateIdentificationMapForLineItems = new HashMap<String, POLineItemsAPIRequest>();
		List<POLineItemsAPIRequest> lineItems = new ArrayList<POLineItemsAPIRequest>(); 
		for (POLineItemsAPIRequest x : lisofvalidrows2) { 
			lineItems.add(x); 
		}

		for (int i = 0; i < lineItems.size(); i++) {
			if (duplicateIdentificationMapForLineItems.get(lineItems.get(i).getSkuNumber() +
					lineItems.get(i).getPromisedDate() + lineItems.get(i).getShipToCity()) == null) {
				duplicateIdentificationMapForLineItems.put(lineItems.get(i).getSkuNumber()
						+lineItems.get(i).getPromisedDate() + lineItems.get(i).getShipToCity(),
						lineItems.get(i));
			}
		}

		List<ExcelPurchaseOrderSuccessRowsResponse> processedShipments = new ArrayList<ExcelPurchaseOrderSuccessRowsResponse>(
				duplicateIdentificationMap.values());

		Set<POLineItemsAPIRequest> processedLineItems = new HashSet<POLineItemsAPIRequest>(
				duplicateIdentificationMapForLineItems.values());
		lisofvalidrows.clear();
		lisofvalidrows2.clear();
		lisofvalidrows.addAll(processedShipments);
		lisofvalidrows2.addAll(processedLineItems);

		for (int i = 0; i < lisofvalidrows.size(); i++) {
			Iterator<POLineItemsAPIRequest> itr = lisofvalidrows2.iterator();
			Set<POLineItemsAPIRequest> lineitems = new HashSet<POLineItemsAPIRequest>();
			while (itr.hasNext()) {
				POLineItemsAPIRequest lItems = (POLineItemsAPIRequest) itr.next();
				String skuNumber = lItems.getSkuNumber();
				String[] purchaseOrder = skuNumber.split(",");
				if (lisofvalidrows.get(i).getPurchaseOrderNumber().equalsIgnoreCase(purchaseOrder[0])) {
					lineitems.add(lItems);
					lisofvalidrows.get(i).setPoLineItems(lineitems);
				}
			}
		}
	}

	public ExcelPurchaseOrderErrorRowsResponse errorRowData(String colName, int rowNumber, String msg, String cellValue) {
		ExcelPurchaseOrderErrorRowsResponse errorValue = new ExcelPurchaseOrderErrorRowsResponse();
		errorValue.setColumnName(colName);
		errorValue.setRowsNumber(rowNumber);
		errorValue.setErrorMessage(msg);
		errorValue.setSpreadSheetValue(cellValue);
		return errorValue;
	}

	public Date convertStringToTimeStamp(String dtString) {
		Date dt = DateUtils.convertWithTries(dtString);
		if (dt != null) {
			return dt;
		}
		return null;
	}
}
