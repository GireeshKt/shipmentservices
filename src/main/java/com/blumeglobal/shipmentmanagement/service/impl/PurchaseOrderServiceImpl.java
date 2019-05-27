package com.blumeglobal.shipmentmanagement.service.impl;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.blumeglobal.shipmentmanagement.dao.repositories.AdvanceShipmentNoticeRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.E2EShipmentRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.FreightLineItemsRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.POLineItemsRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.POTransactionsRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.PurchaseOrderRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentRepository;
import com.blumeglobal.shipmentmanagement.enums.Constants;
import com.blumeglobal.shipmentmanagement.enums.POExceptionCode;
import com.blumeglobal.shipmentmanagement.model.AdvanceShipmentNotice;
import com.blumeglobal.shipmentmanagement.model.E2EShipment;
import com.blumeglobal.shipmentmanagement.model.FreightLineItems;
import com.blumeglobal.shipmentmanagement.model.IPOLineShipItems;
import com.blumeglobal.shipmentmanagement.model.IPOListAllItems;
import com.blumeglobal.shipmentmanagement.model.IPurchaseOrderItems;
import com.blumeglobal.shipmentmanagement.model.IShipmentLogDetails;
import com.blumeglobal.shipmentmanagement.model.IShipmentLogOrderDetails;
import com.blumeglobal.shipmentmanagement.model.POLineItems;
import com.blumeglobal.shipmentmanagement.model.POTransactions;
import com.blumeglobal.shipmentmanagement.model.PurchaseOrder;
import com.blumeglobal.shipmentmanagement.model.mapper.AdvanceShipmentNoticeMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.POTransactionsMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.PurchaseOrderMapper;
import com.blumeglobal.shipmentmanagement.request.E2EShipmentAPIRequest;
import com.blumeglobal.shipmentmanagement.request.E2EShipmentPOStatusRequest;
import com.blumeglobal.shipmentmanagement.request.ExcelPoLineItemsRequest;
import com.blumeglobal.shipmentmanagement.request.ExcelPurchaseOrderSuccessRowsRequest;
import com.blumeglobal.shipmentmanagement.request.FreightLineItemsAPIRequest;
import com.blumeglobal.shipmentmanagement.request.POAPIRequest;
import com.blumeglobal.shipmentmanagement.request.POLineItemsAPIRequest;
import com.blumeglobal.shipmentmanagement.request.POTransactionsAPIRequest;
import com.blumeglobal.shipmentmanagement.response.CommonResponse;
import com.blumeglobal.shipmentmanagement.response.E2EShipmentResponse;
import com.blumeglobal.shipmentmanagement.response.ExcelPurchaseOrderErrorRowsResponse;
import com.blumeglobal.shipmentmanagement.response.ExcelPurchaseOrderSuccessRowsResponse;
import com.blumeglobal.shipmentmanagement.response.ExcelPurchaseOrderUploadResponse;
import com.blumeglobal.shipmentmanagement.response.POAPICreateUpdateResponse;
import com.blumeglobal.shipmentmanagement.response.POAPIDeleteStatusResponse;
import com.blumeglobal.shipmentmanagement.response.POAPIReadLineShipmentResponse;
import com.blumeglobal.shipmentmanagement.response.POAPIReadOrderLineListResponse;
import com.blumeglobal.shipmentmanagement.response.POAPIReadOrderListResponse;
import com.blumeglobal.shipmentmanagement.response.POAPIReadShipmentLogDetailListResponse;
import com.blumeglobal.shipmentmanagement.response.POAPIReadShipmentLogOrderResponse;
import com.blumeglobal.shipmentmanagement.response.POAPIReadTransactionResponse;
import com.blumeglobal.shipmentmanagement.response.POAPIResponse;
import com.blumeglobal.shipmentmanagement.response.POLineShipItemsResponse;
import com.blumeglobal.shipmentmanagement.response.POListAPIResponse;
import com.blumeglobal.shipmentmanagement.response.POShipmentAPIResponse;
import com.blumeglobal.shipmentmanagement.response.POTransactionsAPIResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentLogOrderDetailsResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentLogsDetails;
import com.blumeglobal.shipmentmanagement.response.ShipmentLogsDetailsResponse;
import com.blumeglobal.shipmentmanagement.service.IE2EShipmentService;
import com.blumeglobal.shipmentmanagement.service.IPurchaseOrderService;
import com.blumeglobal.shipmentmanagement.service.IShipmentViewService;
import com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants;
import com.blumeglobal.shipmentmanagement.utils.DateUtil;
import com.blumeglobal.shipmentmanagement.utils.DateUtils;
import com.blumeglobal.shipmentmanagement.utils.PurchaseOrderUtil;
import com.blumeglobal.shipmentmanagement.validator.UploadPurchaseOrderValidator;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

@Service
@REZ1Logger
@REZ1PerformanceLogger
@Transactional
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {

	@Autowired
	PurchaseOrderRepository poRepository;

	@Autowired
	POLineItemsRepository lineItemsRepository;

	@Autowired
	IE2EShipmentService shipmentService;

	@Autowired
	AdvanceShipmentNoticeRepository shipmentRepository;

	@Autowired
	E2EShipmentRepository e2eshipmentRepository;

	@Autowired
	FreightLineItemsRepository fliRepository;

	@Autowired
	POTransactionsRepository poTransactionsRepository;

	@Autowired
	private PurchaseOrderMapper poMapper;

	@Autowired
	AdvanceShipmentNoticeMapper shipmentMapper;

	@Autowired
	private POTransactionsMapper poTransactionMapper;

	@Autowired
	UploadPurchaseOrderValidator uploadPurchaseOrderValidator;

	@Autowired
	PurchaseOrderUtil purchaseOrderUtil;
	
	@Autowired
	IShipmentViewService shipmentViewService;

	@Autowired
	DateUtil dateUtil;

	@Autowired
	ShipmentRepository shipmentRepo;

	@Value("${client.blockchain.baseUrl}")
	private String blockchainBaseUrl;

	private static final Logger logger = LogManager.getLogger(PurchaseOrderServiceImpl.class);
	Constants success = Constants.valueOf("SUCCESS");
	Constants failure = Constants.valueOf("FAILURE");
	Constants system = Constants.valueOf("SYSTEM");
	Constants create = Constants.valueOf("ORDERPLACED");
	Constants active = Constants.valueOf("ACTIVE");
	Constants inactive = Constants.valueOf("INACTIVE");
	Constants updated = Constants.valueOf("UPDATED");
	Constants orderShipped = Constants.valueOf("ORDERSHIPPED");
	Constants readyToShip = Constants.valueOf("READYTOSHIP");
	Constants orderUploaded = Constants.valueOf("ORDERUPLOADED");
	Constants orderAccepted = Constants.valueOf("ORDERACCEPTED");
	Constants orderInTransit = Constants.valueOf("ORDERINTRANSIT");
	Constants orderDeliverd = Constants.valueOf("ORDERDELIVERED");

	/**
	 * Retrieve list of Purchase Order details based on Organization Code
	 * 
	 * @return PurchaseOrder List of Purchase Order Response
	 * @throws Exception Handle service exceptions
	 */
	@Override
	public POAPIReadOrderLineListResponse getAllPOs(String organizationCode) {
		logger.info("Entering into PurchaseOrderServiceImpl.getAllPOs");
		try {
			POAPIReadOrderLineListResponse statusResponse = new POAPIReadOrderLineListResponse();
			if (organizationCode != null && !organizationCode.isEmpty()) {
				PurchaseOrder poValidation = poRepository.validateByOrganization(organizationCode,
						active.getConstants());
				if (poValidation != null) {
					List<POListAPIResponse> po = new ArrayList<>();
					List<IPOListAllItems> porder = poRepository.findAllForpurchaseOrder(organizationCode,
							active.getConstants());
					if (porder != null && !porder.isEmpty()) {
						for (IPOListAllItems poList : porder) {
							POListAPIResponse poItemss = new POListAPIResponse();
							poItemss.setPurchaseOrderId(poList.getPurchaseOrderId());
							poItemss.setPurchaseOrderNumber(poList.getPurchaseOrderNumber());
							poItemss.setLogisticProvider(poList.getLogisticProvider());
							poItemss.setOrigin(poList.getOrigin());
							poItemss.setPoReceiveDate(poList.getPoReceiveDate());
							poItemss.setPoPromiseDate(poList.getPoPromiseDate());
							poItemss.setPoVendorName(poList.getPoVendorName());
							poItemss.setPoVendorDuns(poList.getPoVendorDuns());
							poItemss.setPoVendorAddress(poList.getPoVendorAddress());
							poItemss.setPoVendorCity(poList.getPoVendorCity());
							poItemss.setPoVendorState(poList.getPoVendorState());
							poItemss.setPoVendorZipcode(poList.getPoVendorZipcode());
							poItemss.setPoVendorCountry(poList.getPoVendorCountry());
							poItemss.setPoBuyerName(poList.getPoBuyerName());
							poItemss.setPoBuyerDuns(poList.getPoBuyerDuns());
							poItemss.setPoBillingAddress(poList.getPoBillingAddress());
							poItemss.setPoReferenceId(poList.getPoReferenceId());
							poItemss.setPoExpenditureCode(poList.getPoExpenditureCode());
							poItemss.setPoRequistionedBy(poList.getPoRequistionedBy());
							poItemss.setPoTax(poList.getPoTax());
							poItemss.setPoFreightHandling(poList.getPoFreightHandling());
							poItemss.setPoStatus(poList.getPoStatus());
							poItemss.setPoLeadTime(poList.getPoLeadTime());
							poItemss.setPoDate(poList.getPoDate());
							poItemss.setPoNotes(poList.getPoNotes());
							poItemss.setPoDestination(poList.getPoDestination());
							poItemss.setPoItemId(poList.getPoItemId());
							poItemss.setLineItemStatus(poList.getLineItemStatus());
							poItemss.setSkuNumber(poList.getSkuNumber());
							poItemss.setShipToCity(poList.getShipToCity());
							poItemss.setShipToState(poList.getShipToState());
							poItemss.setPromisedDate(poList.getPromisedDate());
							poItemss.setQuantity(poList.getQuantity());
							poItemss.setSkuDescription(poList.getSkuDescription());
							poItemss.setCommittedQuantity(poList.getCommittedQuantity());
							poItemss.setDueDate(poList.getDueDate());
							poItemss.setUnitOfMeasurement(poList.getUnitOfMeasurement());
							poItemss.setPrice(poList.getPrice());
							poItemss.setShipToLocationCode(poList.getShipToLocationCode());
							poItemss.setShipToCompany(poList.getShipToCompany());
							poItemss.setShipToAddress(poList.getShipToAddress());
							poItemss.setShipToZip(poList.getShipToZip());
							poItemss.setShipToCountry(poList.getShipToCountry());

							po.add(poItemss);
						}
						statusResponse.setStatus(success.getConstants());
						statusResponse.setSuccessfullData(po);
						statusResponse
								.setMessage(ShipmentConstants.ORG_DATA_RETRIVE_SUCCESS_MESSAGE + organizationCode);
						return statusResponse;
					} else {
						statusResponse.setStatus(failure.getConstants());
						statusResponse
								.setMessage(ShipmentConstants.ORG_DATA_RETRIVE_FAILURE_MESSAGE + organizationCode);
						if (logger.isDebugEnabled()) {
							logger.debug("No Data Available for Organization code : " + organizationCode);
						}

						return statusResponse;
					}
				} else {
					statusResponse.setStatus(failure.getConstants());
					statusResponse.setMessage(ShipmentConstants.INVALID_ORG_CODE_MESSAGE + organizationCode);
					if (logger.isDebugEnabled()) {
						logger.debug("No Purchase Orders Available for Organization code :  " + organizationCode);
					}

					return statusResponse;
				}
			} else {
				statusResponse.setStatus(failure.getConstants());
				statusResponse.setMessage(ShipmentConstants.EMPTY_ORG_CODE_MESSAGE);
				if (logger.isDebugEnabled()) {
					logger.debug("Organization code is Empty");
				}

				return statusResponse;
			}
		} catch (Exception ex) {
			logger.error("Exception inside PurchaseOrderServiceImpl.getAllPOs.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * Retrieve list of PO Transactions details based on Id and Organization Code
	 * 
	 * @return PO Transactions List of PO Transactions Response
	 * @throws Exception, Handle service exceptions
	 */

	@Override
	public POAPIReadTransactionResponse getPOTransactionStatus(Long poItemId, String organizationCode) {
		logger.info("Entering into PurchaseOrderServiceImpl.getPOTransactionStatus");
		try {
			POAPIReadTransactionResponse statusResponse = new POAPIReadTransactionResponse();
			if (poItemId != null && organizationCode != null && !organizationCode.isEmpty()) {
				POTransactions poValidation = poTransactionsRepository.validateByIDOrganization(poItemId,
						organizationCode, active.getConstants());
				if (poValidation != null) {
					List<POTransactionsAPIResponse> po = new ArrayList<>();
					List<POTransactions> porder = poTransactionsRepository.findPoTransactionStatus(poItemId,
							organizationCode, active.getConstants());
					if (porder != null && !porder.isEmpty()) {
						String[] predefinedStatus = { create.getConstants(), orderUploaded.getConstants(),
								orderAccepted.getConstants(), readyToShip.getConstants(), orderShipped.getConstants(),
								orderInTransit.getConstants(), orderDeliverd.getConstants() };
						po = poTransactionMapper.mapAsList(porder, POTransactionsAPIResponse.class);
						statusResponse.setStatus(success.getConstants());
						statusResponse.setPredefinedSatus(predefinedStatus);
						statusResponse.setSuccessfullData(po);
						statusResponse.setMessage(String.format(
								ShipmentConstants.PO_TRANSACTION_RETRIEVE_SUCCESS_MESSAGE, poItemId, organizationCode));
						return statusResponse;
					} else {
						statusResponse.setStatus(failure.getConstants());
						statusResponse.setMessage(String.format(
								ShipmentConstants.PO_TRANSACTION_RETRIEVE_FAILURE_MESSAGE, poItemId, organizationCode));
						if (logger.isDebugEnabled()) {
							logger.debug("No PO Transaction Available for Line Item ID : " + poItemId
									+ " and  Organization code :  " + organizationCode);
						}

						return statusResponse;
					}
				} else {
					statusResponse.setStatus(failure.getConstants());
					statusResponse.setMessage(String.format(ShipmentConstants.INVALID_LINE_ID_ORG_CODE_MESSAGE,
							poItemId, organizationCode));
					if (logger.isDebugEnabled()) {
						logger.debug("No Purchase Orders Available for Line Item ID/Organization code :  " + poItemId
								+ " / " + organizationCode);
					}

					return statusResponse;
				}
			} else {
				statusResponse.setStatus(failure.getConstants());
				statusResponse.setMessage(ShipmentConstants.EMPTY_LINE_ID_ORG_CODE_MESSAGE);
				if (logger.isDebugEnabled()) {
					logger.debug("Line Item ID/Organization code is Empty");
				}

				return statusResponse;
			}
		} catch (Exception ex) {
			logger.error(
					"Exception inside PurchaseOrderServiceImpl.getPOTransactionStatus.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * Retrieve list of Purchase Order data by Purchase Order Id and Organization
	 * Code
	 * 
	 * @param id This is Purchase Order id
	 * @return PurchaseOrder List of Purchase Orders
	 * @throws Exception Handle service exceptions
	 */
	@Override
	public POAPIReadOrderListResponse getPurchaseOrderById(Long poId, String organizationCode) {
		logger.info("Entering into PurchaseOrderServiceImpl.getPurchaseOrderById");
		try {
			POAPIReadOrderListResponse statusResponse = new POAPIReadOrderListResponse();
			if (poId != null && organizationCode != null && !organizationCode.isEmpty()) {
				PurchaseOrder poValidation = poRepository.validateByIDOrganization(poId, organizationCode,
						active.getConstants());
				if (poValidation != null) {
					POAPIResponse poResponse = new POAPIResponse();
					PurchaseOrder pOrder = poRepository.findForpurchaseOrder(poId, organizationCode,
							active.getConstants());
					if (pOrder != null) {
						poResponse = poMapper.map(pOrder, POAPIResponse.class);
						statusResponse.setStatus(success.getConstants());
						statusResponse.setSuccessfullData(poResponse);
						statusResponse.setMessage(String.format(ShipmentConstants.ORDER_LIST_RETRIEVE_SUCCESS_MESSAGE,
								poId, organizationCode));
						return statusResponse;
					} else {
						statusResponse.setStatus(failure.getConstants());
						statusResponse.setMessage(String.format(ShipmentConstants.ORDER_LIST_RETRIEVE_FAILURE_MESSAGE,
								poId, organizationCode));
						if (logger.isDebugEnabled()) {
							logger.debug("No Purchase Orders Available for Order ID/Organization code :  " + poId
									+ " / " + organizationCode);
						}

						return statusResponse;
					}
				} else {
					statusResponse.setStatus(failure.getConstants());
					statusResponse.setMessage(
							String.format(ShipmentConstants.INVALID_ORDER_ID_ORG_CODE_MESSAGE, poId, organizationCode));
					if (logger.isDebugEnabled()) {
						logger.debug("No Purchase Orders Available for Order ID/Organization code :  " + poId + " / "
								+ organizationCode);
					}

					return statusResponse;
				}
			} else {
				statusResponse.setStatus(failure.getConstants());
				statusResponse.setMessage(ShipmentConstants.EMPTY_ORDER_ID_ORG_CODE_MESSAGE);
				if (logger.isDebugEnabled()) {
					logger.debug("Order ID/Organization code is Empty");
				}

				return statusResponse;
			}
		} catch (Exception ex) {
			logger.error(
					"Exception inside PurchaseOrderServiceImpl.getPurchaseOrderById.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * Retrieve list of Purchase Order data by Purchase Order Number and
	 * Organization Code
	 * 
	 * @param purchaseOrderNumber This is Purchase Order Number
	 * @return PurchaseOrder List of Purchase Orders
	 * @throws Exception Handle service exceptions added rownum =1 for testing
	 */
	@Override
	public POAPIReadLineShipmentResponse getPurchaseOrderByPoNum(String purchaseOrderNumber, String organizationCode) {
		logger.info("Entering into PurchaseOrderServiceImpl.getPurchaseOrderByPoNum");
		try {
			POAPIReadLineShipmentResponse statusResponse = new POAPIReadLineShipmentResponse();
			if (purchaseOrderNumber != null && !purchaseOrderNumber.isEmpty() && organizationCode != null
					&& !organizationCode.isEmpty()) {
				PurchaseOrder poValidation = poRepository.validateByPoNum(purchaseOrderNumber, organizationCode,
						active.getConstants());
				if (poValidation != null) {
					POShipmentAPIResponse orderList = new POShipmentAPIResponse();
					List<IPurchaseOrderItems> pOrder = poRepository.findOrderByPoNum(purchaseOrderNumber,
							organizationCode, active.getConstants());
					List<IPOLineShipItems> pLineOrder = poRepository.findPoLineByPoNum(purchaseOrderNumber,
							organizationCode, active.getConstants(), "N");
					if (pOrder != null && !pOrder.isEmpty() && pLineOrder != null && !pLineOrder.isEmpty()) {
						for (IPurchaseOrderItems purchaseOrderList : pOrder) {
							orderList.setPurchaseOrderNumber(purchaseOrderList.getPurchaseOrderNumber());
							orderList.setPoReferenceId(purchaseOrderList.getPoReferenceId());
							orderList.setPoRequestDate(purchaseOrderList.getPoRequestDate());
							orderList.setPoPromiseDate(purchaseOrderList.getPoPromiseDate());
							orderList.setPoIssueDate(purchaseOrderList.getPoIssueDate());
							orderList.setCreatedOn(purchaseOrderList.getCreatedOn());
							orderList.setVendorName(purchaseOrderList.getVendorName());
							orderList.setVendorAddress(purchaseOrderList.getVendorAddress());
							orderList.setBuyerName(purchaseOrderList.getBuyerName());
							orderList.setBillingAddress(purchaseOrderList.getBillingAddress());
						}
						Set<POLineShipItemsResponse> orderItems = new HashSet<>();
						for (IPOLineShipItems pLineOrderList : pLineOrder) {
							POLineShipItemsResponse orderItemResp = new POLineShipItemsResponse();
							orderItemResp.setSkuId(pLineOrderList.getSkuId());
							orderItemResp.setShipmentNumber(pLineOrderList.getShipmentNumber());
							orderItemResp.setUnitId(pLineOrderList.getUnitId());
							orderItemResp.setSkuDescription(pLineOrderList.getSkuDescription());
							orderItemResp.setQuantity(pLineOrderList.getQuantity());
							orderItemResp.setCommittedQuantity(pLineOrderList.getCommittedQuantity());
							orderItemResp.setRequestDate(pLineOrderList.getRequestDate());
							orderItemResp.setPromisedDate(pLineOrderList.getPromisedDate());
							orderItemResp.setUnitOfMeasure(pLineOrderList.getUnitOfMeasure());
							orderItemResp.setDestination(pLineOrderList.getDestination());

							/*
							 * List<AdvanceShipmentNotice> e2eShipments = shipmentRepository
							 * .findByShipmentNumberAndOrganizationCodeAndStatusAndDeleteFlagOrderByUpdatedDateTimeDesc(
							 * pLineOrderList.getShipmentNumber(), organizationCode, active.getConstants(),
							 * "N");
							 */
							/*
							 * String shipmentnumber=pLineOrderList.getShipmentNumber(); String unitid
							 * =pLineOrderList.getUnitId();
							 */
							List<E2EShipment> e2eShipments = e2eshipmentRepository.getShipmentByShipmentNumberAndUintId(
									pLineOrderList.getShipmentNumber(), pLineOrderList.getUnitId(), organizationCode,
									active.getConstants(), "N");

							if (e2eShipments == null || e2eShipments.isEmpty()) {
								orderItemResp.setLineItemStatus(pLineOrderList.getLineItemStatus());
							} else {
								
								E2EShipmentResponse statusRelated = shipmentViewService
										.getE2EShipmentStatusToOrderDetails(pLineOrderList.getShipmentNumber(),
												organizationCode);
								if(statusRelated.getActiveShipment()!=null&&e2eShipments.get(0).getStatus().equals(active.getConstants())) {
									orderItemResp.setActiveShipment(statusRelated.getActiveShipment());
									orderItemResp.setLineItemStatus(e2eShipments.get(0).getStatus());
								}
								
							
							}

							orderItemResp.setShipToState(pLineOrderList.getShipToState());
							orderItemResp.setPrice(pLineOrderList.getPrice());
							orderItemResp.setShipToLocationCode(pLineOrderList.getShipToLocationCode());
							orderItemResp.setShipToCompany(pLineOrderList.getShipToCompany());
							orderItemResp.setShipToAddress(pLineOrderList.getShipToAddress());
							orderItemResp.setShipToZip(pLineOrderList.getShipToZip());
							orderItemResp.setShipToCountry(pLineOrderList.getShipToCountry());

							orderItems.add(orderItemResp);
						}
						orderList.setPoLineItems(orderItems);
						statusResponse.setStatus(success.getConstants());
						statusResponse.setSuccessfullData(orderList);
						statusResponse.setMessage(
								String.format(ShipmentConstants.ORDER_LIST_FOR_ORDER_NUMBER_ORG_CODE_SUCCESS_MESSAGE,
										purchaseOrderNumber, organizationCode));
						return statusResponse;
					} else {
						statusResponse.setStatus(failure.getConstants());
						statusResponse.setMessage(
								String.format(ShipmentConstants.ORDER_LIST_FOR_ORDER_NUMBER_ORG_CODE_FAILURE_MESSAGE,
										purchaseOrderNumber, organizationCode));
						if (logger.isDebugEnabled()) {
							logger.debug("No Purchase Orders Available for Order Number : " + purchaseOrderNumber
									+ " Or Organization code :  " + organizationCode);
						}

						return statusResponse;
					}
				} else {
					statusResponse.setStatus(failure.getConstants());
					statusResponse.setMessage(String.format(ShipmentConstants.INVALID_ORDER_NUMBER_ORG_CODE_MESSAGE,
							purchaseOrderNumber, organizationCode));
					if (logger.isDebugEnabled()) {
						logger.debug("No Purchase Orders Available for Order Number : " + purchaseOrderNumber
								+ " Or Organization code :  " + organizationCode);
					}

					return statusResponse;
				}
			} else {
				statusResponse.setStatus(failure.getConstants());
				statusResponse.setMessage(ShipmentConstants.EMPTY_ORDER_NUMBER_ORG_CODE_MESSAGE);
				if (logger.isDebugEnabled()) {
					logger.debug("Order Number/Organization code is Empty");
				}

				return statusResponse;
			}
		} catch (Exception ex) {
			logger.error(
					"Exception inside PurchaseOrderServiceImpl.getPurchaseOrderByPoNum.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * Create Purchase Order through API
	 * 
	 * @param POAPIResponse poApiRequest This is request which has Purchase Order
	 *                      Details
	 * @return POAPIResponse Purchase Order Response after save
	 * @throws Exception Handle service exceptions
	 */
	@Override
	public POAPICreateUpdateResponse createPurchaseOrder(POAPIRequest poApiRequest, String organizationCode,
			String userName) throws Exception {
		logger.info("Entering into PurchaseOrderServiceImpl.createPurchaseOrder");
		try {
			POAPICreateUpdateResponse commonResponse = new POAPICreateUpdateResponse();
			if (userName != null && !userName.isEmpty() && organizationCode != null && !organizationCode.isEmpty()) {

				PurchaseOrder poValidation = poRepository.validateByPoNum(poApiRequest.getPurchaseOrderNumber(),
						organizationCode, active.getConstants());
				if (poValidation == null) {
					List<String> errorMsgList = new ArrayList<>();
					ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
					Validator validator = factory.getValidator();
					Set<ConstraintViolation<POAPIRequest>> violations = validator.validate(poApiRequest);
					boolean validflag = true;
					String poTransactionStatus = "";
					StringBuilder sb = new StringBuilder("");
					for (ConstraintViolation<POAPIRequest> violation : violations) {
						String message = violation.getMessage();
						errorMsgList.add(message);
						sb.append(message);
						sb.append(";");
						validflag = false;
					}
					Set<POLineItemsAPIRequest> polineItems = poApiRequest.getPoLineItems();
					if (polineItems != null) {
						Iterator<POLineItemsAPIRequest> itSet = polineItems.iterator();
						while (itSet.hasNext()) {
							POLineItemsAPIRequest poLitems = itSet.next();

							Set<ConstraintViolation<POLineItemsAPIRequest>> lineViolations = validator
									.validate(poLitems);
							for (ConstraintViolation<POLineItemsAPIRequest> lineViolation : lineViolations) {
								String message = lineViolation.getMessage();
								errorMsgList.add(message);
								sb.append(message);
								validflag = false;
							}
							// validate po transaction inside line item
							Set<POTransactionsAPIRequest> poTransactions = poLitems.getPoTransactions();
							if (poTransactions != null) {
								Iterator<POTransactionsAPIRequest> itSetTrans = poTransactions.iterator();
								while (itSet.hasNext()) {
									POTransactionsAPIRequest poTrans = itSetTrans.next();
									Set<ConstraintViolation<POTransactionsAPIRequest>> lineViolationsTrans = validator
											.validate(poTrans);
									for (ConstraintViolation<POTransactionsAPIRequest> lineViolation : lineViolationsTrans) {
										String message = lineViolation.getMessage();
										errorMsgList.add(message);
										sb.append(message);
										validflag = false;
									}
								}
							} else {
								commonResponse.setStatus(failure.getConstants());
								commonResponse.setMessage(ShipmentConstants.NO_PO_AVAILABLE_MESSAGE);
								if (logger.isDebugEnabled()) {
									logger.debug("No Purchase Order Transactions Available");
								}

							}
						}
					} else {
						commonResponse.setStatus(failure.getConstants());
						commonResponse.setMessage(ShipmentConstants.NO_PO_LINE_ITEMS_AVAILABLE_MESSAGE);
						if (logger.isDebugEnabled()) {
							logger.debug("No Purchase Order Transactions Available");
						}

					}
					if (validflag) {
						Date curDate = new Date();
						String DateToStr;
						try {
							SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
							DateToStr = format.format(curDate);

						} catch (Exception ex) {
							throw ex;
						}
						poApiRequest.setCreatedOn(DateToStr);
						poApiRequest.setUpdatedOn(DateToStr);
						if (userName != null && organizationCode != null) {
							poApiRequest.setCreatedBy(userName);
							poApiRequest.setUpdatedBy(userName);
							poApiRequest.setOrganizationCode(organizationCode);
						} else {
							poApiRequest.setCreatedBy(system.getConstants());
							poApiRequest.setUpdatedBy(system.getConstants());
						}
						poApiRequest.setPoStatus(create.getConstants());
						PurchaseOrder po = poMapper.map(poApiRequest, PurchaseOrder.class);
						Set<POLineItems> lineItems = po.getPoLineItems();
						Iterator<POLineItems> itSetPOLineItems = lineItems.iterator();
						while (itSetPOLineItems.hasNext()) {
							POLineItems poLineItemss = itSetPOLineItems.next();
							poLineItemss.setPoLineItemsRef(po);
							poLineItemss.setLineItemStatus(create.getConstants());
							poLineItemss.setCreatedBy(po.getCreatedBy());
							poLineItemss.setCreatedOn(po.getCreatedOn());
							poLineItemss.setUpdatedBy(po.getUpdatedBy());
							poLineItemss.setUpdatedOn(po.getUpdatedOn());
							poLineItemss.setLineItemStatus(orderUploaded.getConstants());
							poLineItemss.setDeleteFlag(active.getConstants());
							Set<POTransactions> transactions = new HashSet<>();
							POTransactions POTransactionsAdd = new POTransactions();
							POTransactionsAdd.setPoTransactionRef(poLineItemss);
							POTransactionsAdd.setPoStatus(create.getConstants());
							POTransactionsAdd.setPoTransactionStatus(create.getConstants());
							if (po.getPoReceivedDate() != null) {
								POTransactionsAdd.setPoTransactionDate(DateUtils.convertStringToTimestamp(
										DateUtil.convertDateToString(po.getPoReceivedDate(), "MM-dd-yyyy hh:mm:ss"),
										"MM-dd-yyyy hh:mm:ss"));
							} else {
								commonResponse.setMessage(ShipmentConstants.PO_RECEIVED_DATE_NULL_ERROR_MESSAGE);
								commonResponse.setStatus(failure.getConstants());
								return commonResponse;
							}
							POTransactionsAdd.setCreatedBy(poLineItemss.getCreatedBy());
							POTransactionsAdd.setCreatedOn(poLineItemss.getCreatedOn());
							POTransactionsAdd.setUpdatedBy(poLineItemss.getUpdatedBy());
							POTransactionsAdd.setUpdatedOn(poLineItemss.getUpdatedOn());
							POTransactionsAdd.setOrganizationCode(po.getOrganizationCode());
							POTransactionsAdd.setDeleteFlag(active.getConstants());
							transactions.add(POTransactionsAdd);
							// order upload status for transaction
							POTransactions POTransactionssAdd = new POTransactions();
							POTransactionssAdd.setPoTransactionRef(poLineItemss);
							POTransactionssAdd.setPoStatus(orderUploaded.getConstants());
							POTransactionssAdd.setPoTransactionStatus(create.getConstants());
							POTransactionssAdd.setPoTransactionDate(
									DateUtils.convertStringToTimestamp(DateToStr, "MM-dd-yyyy hh:mm:ss"));
							POTransactionssAdd.setCreatedBy(poLineItemss.getCreatedBy());
							POTransactionssAdd.setCreatedOn(poLineItemss.getCreatedOn());
							POTransactionssAdd.setUpdatedBy(poLineItemss.getUpdatedBy());
							POTransactionssAdd.setUpdatedOn(poLineItemss.getUpdatedOn());
							POTransactionssAdd.setOrganizationCode(po.getOrganizationCode());
							POTransactionssAdd.setDeleteFlag(active.getConstants());
							transactions.add(POTransactionssAdd);
							poLineItemss.setPoTransactions(transactions);
							po.setPoLineItems(lineItems);
						}
						po.setDeleteFlag(active.getConstants());
						PurchaseOrder createPO = poRepository.save(po);
						if (createPO != null) {
							commonResponse.setStatus(success.getConstants());
							commonResponse.setMessage(
									ShipmentConstants.CREATE_PO_SUCCESS_MESSAGE + createPO.getPurchaseOrderId());
							if (logger.isDebugEnabled()) {
								logger.debug("Purchase Order Created Successfully...PO ID :"
										+ createPO.getPurchaseOrderId());
							}

						} else {
							commonResponse.setStatus(failure.getConstants());
							commonResponse.setMessage(String.format(ShipmentConstants.CREATE_PO_FAILURE_MESSAGE,
									createPO.getPurchaseOrderId(), poTransactionStatus));
							if (logger.isDebugEnabled()) {
								logger.debug("Unable to create Purchase Order.." + " and  " + poTransactionStatus);
							}
						}
						return commonResponse;
					} else {
						commonResponse.setStatus(failure.getConstants());
						commonResponse.setMessage(ShipmentConstants.CREATE_PO_VALIDATION_FAILURE_MESSAGE);
						if (logger.isDebugEnabled()) {
							logger.debug("Validation Failed To Create the Order");
						}
						return commonResponse;
					}
				} else {
					commonResponse.setStatus(failure.getConstants());
					commonResponse.setMessage(ShipmentConstants.CREATE_PO_DUPLICATE_MESSAGE);
					if (logger.isDebugEnabled()) {
						logger.debug("Data Already Exist, kindly use UpdatePO API, to update the PO");
					}
					return commonResponse;
				}
			} else {
				commonResponse.setStatus(failure.getConstants());
				commonResponse.setMessage(ShipmentConstants.EMPTY_USERNAME_ORG_CODE_MESSAGE);
				if (logger.isDebugEnabled()) {
					logger.debug("Username / Organization code is Empty");
				}
				return commonResponse;
			}
		} catch (Exception ex) {
			logger.error(
					"Exception inside PurchaseOrderServiceImpl.createPurchaseOrder.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * Update Purchase Order through API
	 * 
	 * @param POAPIResponse poApiRequest This is request which has Purchase Order
	 *                      Details
	 * @return POAPIResponse Purchase Order Response after save
	 * @throws Exception Handle service exceptions
	 */

	@Override
	public POAPICreateUpdateResponse updatePurchaseOrder(POAPIRequest poApiRequest, Long poId, String organizationCode,
			String userName) {
		logger.info("Entering into PurchaseOrderServiceImpl.updatePurchaseOrder");
		try {
			POAPICreateUpdateResponse commonResponse = new POAPICreateUpdateResponse();
			if (poId != null && userName != null && !userName.isEmpty() && organizationCode != null
					&& !organizationCode.isEmpty()) {
				PurchaseOrder pOrderr = poRepository.findForpurchaseOrder(poId, organizationCode,
						active.getConstants());
				if (pOrderr != null) {
					List<String> errorMsgList = new ArrayList<>();
					ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
					Validator validator = factory.getValidator();
					Set<ConstraintViolation<POAPIRequest>> violations = validator.validate(poApiRequest);
					boolean validflag = true;
					StringBuilder sb = new StringBuilder("");
					for (ConstraintViolation<POAPIRequest> violation : violations) {
						String message = violation.getMessage();
						errorMsgList.add(message);
						sb.append(message);
						sb.append(";");
						validflag = false;
					}
					Set<POLineItemsAPIRequest> polineItems = poApiRequest.getPoLineItems();
					Iterator<POLineItemsAPIRequest> itSet = polineItems.iterator();
					while (itSet.hasNext()) {
						POLineItemsAPIRequest poLitems = itSet.next();
						Set<ConstraintViolation<POLineItemsAPIRequest>> lineViolations = validator.validate(poLitems);
						for (ConstraintViolation<POLineItemsAPIRequest> lineViolation : lineViolations) {
							String message = lineViolation.getMessage();
							errorMsgList.add(message);
							sb.append(message);
							validflag = false;
						}
						// validate po transaction inside line item
						Set<POTransactionsAPIRequest> poTransactions = poLitems.getPoTransactions();
						Iterator<POTransactionsAPIRequest> itSets = poTransactions.iterator();
						while (itSets.hasNext()) {
							POTransactionsAPIRequest poTrans = itSets.next();
							Set<ConstraintViolation<POTransactionsAPIRequest>> lineViolationsTrans = validator
									.validate(poTrans);
							for (ConstraintViolation<POTransactionsAPIRequest> lineViolation : lineViolationsTrans) {
								String message = lineViolation.getMessage();
								errorMsgList.add(message);
								sb.append(message);
								validflag = false;
							}
						}
					}
					if (validflag) {
						Date curDate = new Date();
						String DateToStr;
						try {
							SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
							DateToStr = format.format(curDate);
						} catch (Exception ex) {
							throw ex;
						}
						poApiRequest.setUpdatedOn(DateToStr);
						if (userName != null && organizationCode != null) {
							poApiRequest.setUpdatedBy(userName);
							poApiRequest.setOrganizationCode(organizationCode);
						} else {
							poApiRequest.setUpdatedBy(system.getConstants());
						}
						PurchaseOrder po = poMapper.map(poApiRequest, PurchaseOrder.class);
						Set<POLineItems> lineItems = po.getPoLineItems();
						for (POLineItems pItems : lineItems) {
							pItems.setPoLineItemsRef(po);
							pItems.setCreatedBy(pOrderr.getCreatedBy());
							pItems.setCreatedOn(pOrderr.getCreatedOn());
							pItems.setUpdatedBy(po.getUpdatedBy());
							pItems.setUpdatedOn(po.getUpdatedOn());
							if (pItems.getDeleteFlag().equalsIgnoreCase(inactive.getConstants())) {
								pItems.setDeleteFlag(inactive.getConstants());
							} else {
								pItems.setDeleteFlag(inactive.getConstants());
							}
							// setting po transaction inside po line items
							Set<POTransactions> poTransaction = pItems.getPoTransactions();
							for (POTransactions pItemsTrans : poTransaction) {
								pItemsTrans.setPoTransactionRef(pItems);
								pItemsTrans.setPoStatus(pItems.getLineItemStatus());
								pItemsTrans.setPoTransactionStatus(updated.getConstants());
								pItemsTrans.setCreatedBy(pItems.getCreatedBy());
								pItemsTrans.setCreatedOn(pItems.getCreatedOn());
								pItemsTrans.setUpdatedBy(pItems.getUpdatedBy());
								pItemsTrans.setUpdatedOn(pItems.getUpdatedOn());
								pItemsTrans.setOrganizationCode(po.getOrganizationCode());
								if (pItemsTrans.getDeleteFlag().equalsIgnoreCase(inactive.getConstants())) {
									pItemsTrans.setDeleteFlag(inactive.getConstants());
								} else {
									pItemsTrans.setDeleteFlag(active.getConstants());
								}
								pItems.setPoTransactions(poTransaction);
							}
							po.setPoLineItems(lineItems);
						}
						po.setDeleteFlag(active.getConstants());
						po.setCreatedOn(pOrderr.getCreatedOn());
						po.setCreatedBy(pOrderr.getCreatedBy());
						PurchaseOrder updatePO = poRepository.save(po);
						if (updatePO != null) {
							commonResponse.setStatus(success.getConstants());
							commonResponse.setMessage(
									ShipmentConstants.UPDATE_PO_SUCCESS_MESSAGE + updatePO.getPurchaseOrderId());
							if (logger.isDebugEnabled()) {
								logger.debug("Purchase Order Updated Successfully... PO ID : "
										+ updatePO.getPurchaseOrderId());
							}
						} else {
							commonResponse.setStatus(failure.getConstants());
							commonResponse
									.setMessage(ShipmentConstants.UPDATE_PO_FAILURE_MESSAGE + po.getPurchaseOrderId());
							if (logger.isDebugEnabled()) {
								logger.debug("Unable to Update Purchase Order... PO ID : " + po.getPurchaseOrderId());
							}
						}
						return commonResponse;
					} else {
						commonResponse.setStatus(failure.getConstants());
						commonResponse.setMessage(ShipmentConstants.UPDATE_PO_VALIDATION_FAILURE_MESSAGE);
						if (logger.isDebugEnabled()) {
							logger.debug("Validation Failed To Update the Order Based on Order ID");
						}
						return commonResponse;
					}
				} else {
					commonResponse.setStatus(failure.getConstants());
					commonResponse.setMessage(
							String.format(ShipmentConstants.INVALID_PO_ID_ORG_CODE_MESSAGE, poId, organizationCode));
					if (logger.isDebugEnabled()) {
						logger.debug(
								"InValid Purchase Order ID : " + poId + " / Organization Code :" + organizationCode);
					}
					return commonResponse;
				}
			} else {
				commonResponse.setStatus(failure.getConstants());
				commonResponse.setMessage(ShipmentConstants.EMPTY_ORDER_ID_ORG_CODE_MESSAGE);
				if (logger.isDebugEnabled()) {
					logger.debug("Order ID / Organization code is Empty");
				}
				return commonResponse;
			}
		} catch (Exception ex) {
			logger.error(
					"Exception inside PurchaseOrderServiceImpl.updatePurchaseOrder.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * Delete Purchase Order through API
	 * 
	 * @throws Exception Handle service exceptions
	 */

	@Override
	public POAPIDeleteStatusResponse deleteFlagPurchaseOrder(String poId, String organizationCode) {
		logger.info("Entering into PurchaseOrderServiceImpl.deletePurchaseOrder");
		try {
			POAPIDeleteStatusResponse statusResponse = new POAPIDeleteStatusResponse();
			if (poId != null && !poId.isEmpty() && organizationCode != null && !organizationCode.isEmpty()) {
				String[] words = poId.split(",");
				List<Long> myList = new ArrayList<Long>();
				List<Long> inValid = new ArrayList<Long>();
				List<Long> poIds = new ArrayList<Long>();
				for (int i = 0; i < words.length; i++) {
					long num = Long.parseLong(words[i]);
					myList.add(num);
				}
				for (int j = 0; j < myList.size(); j++) {
					Long a = myList.get(j);
					PurchaseOrder findId = poRepository.findByPurchaseOrderId(a);
					if (findId != null) {
						poIds.add(myList.get(j));
					} else {
						inValid.add(a);
					}
				}
				String delete = inactive.getConstants();
				if (!poIds.isEmpty() && poIds.size() < 1000) {
					if (inValid.isEmpty()) {
						int dlt = poRepository.setDeleteStatusForpurchaseOrder(delete, poIds, organizationCode);
						if (dlt > 0) {
							statusResponse.setStatus(success.getConstants());
							statusResponse.setMessage("Successfully Record Deleted for the Id :" + poIds
									+ " Organization_code : " + organizationCode);
							if (logger.isDebugEnabled()) {
								logger.debug("Successfully Record Deleted for the Id :" + poIds
										+ " Organization_code : " + organizationCode);
							}
							return statusResponse;
						} else {
							statusResponse.setStatus(failure.getConstants());
							statusResponse.setMessage("Something went wrong.....Record not Deleted for the Id :" + poIds
									+ " Organization_code : " + organizationCode);
							if (logger.isDebugEnabled()) {
								logger.debug("Something went wrong.....Record not Deleted for the Id :" + poIds
										+ " Organization_code : " + organizationCode);
							}
							return statusResponse;
						}
					} else {
						statusResponse.setStatus(failure.getConstants());
						statusResponse.setValid(poIds);
						statusResponse.setInValid(inValid);
						if (logger.isDebugEnabled()) {
							logger.debug("Invalid ID ");
						}
						return statusResponse;
					}
				} else {
					statusResponse.setStatus(failure.getConstants());
					statusResponse.setMessage(ShipmentConstants.INVALID_INPUT_MESSAGE);
					if (logger.isDebugEnabled()) {
						logger.debug("InValid Input Passed");
					}
					return statusResponse;
				}
			} else {
				statusResponse.setStatus(failure.getConstants());
				statusResponse.setMessage(ShipmentConstants.EMPTY_ORDER_ID_ORG_CODE_MESSAGE);
				if (logger.isDebugEnabled()) {
					logger.debug("Order ID / Organization code is Empty");
				}
				return statusResponse;
			}
		} catch (Exception ex) {
			logger.error(
					"Exception inside PurchaseOrderServiceImpl.deletePurchaseOrder.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * Upload Purchase Order through API
	 * 
	 * @param ExcelPurchaseOrderUploadResponse ExcelPurchaseOrderSuccessRowsRequest
	 *                                         This is request which has Purchase
	 *                                         Order Details
	 * @return ExcelPurchaseOrderSuccessRowsResponse Purchase Order Response after
	 *         save
	 * @throws Exception Handle service exceptions
	 */

	@Override
	public ExcelPurchaseOrderUploadResponse uploadPurchaseOrder(MultipartFile file, String organizationCode,
			String userName) throws Exception {
		logger.info("Entering into PurchaseOrderServiceImpl.PurchaseOrderServiceImpl");
		try {
			String columnErrors = "";
			ExcelPurchaseOrderUploadResponse finalOutput = new ExcelPurchaseOrderUploadResponse();
			List<ExcelPurchaseOrderErrorRowsResponse> lisoferrorrows = new ArrayList<>();
			List<ExcelPurchaseOrderSuccessRowsResponse> lisofvalidrows = new ArrayList<>();
			Set<POLineItemsAPIRequest> listOfLineItems = new HashSet<>();
			if (userName != null && !userName.isEmpty() && organizationCode != null && !organizationCode.isEmpty()) {
				String[] poHeaderElements = new String[] { "Purchase Order Number*", "PO Issue Date", "Response Date",
						"Vendor Name*", "Vendor DUNS#", "Vendor Address", "Vendor City", "Vendor State",
						"Vendor ZipCode", "Vendor Country", "Logistic Provider", "Buyer Name*", "Buyer Duns#",
						"Billing Address", "PO Reference ID", "PO Expenditure Code", "PO Requested By", "PO Tax",
						"PO Freight Handling", "PO Status", "Lead Time", "PO Date", "Notes", "Origin", "Destination",
						"SKU ID", "SKU Description", "Quantity", "Committed Quantity", "Due Date", "Promised Date",
						"Unit of Measurement", "Price", "Ship to location code", "Ship to Company", "Ship to Address",
						"Ship to City", "Ship to State", "Ship to zipCode", "Ship to country" };

				String fileName = file.getOriginalFilename();
				if (fileName.lastIndexOf('.') != -1 && fileName.lastIndexOf('.') != 0) {
					String fileType = fileName.substring(fileName.lastIndexOf('.') + 1);
					if (fileType.equalsIgnoreCase("csv")) {
						if (logger.isDebugEnabled()) {
							logger.debug("PurchaseOrderServiceImpl.PurchaseOrderServiceImpl:  input file is csv");
						}

						File testFile = new File("test");
						FileUtils.writeByteArrayToFile(testFile, file.getBytes());
						List<String> lines = FileUtils.readLines(testFile);
						String fieldHeaders = lines.get(0);
						String[] columslist = fieldHeaders.split(",");
						boolean check = Arrays.equals(poHeaderElements, columslist);
						if (poHeaderElements.length < columslist.length) {
							finalOutput.setError("Irrelevant column available in CSV");
							if (logger.isDebugEnabled()) {
								logger.debug(
										"PurchaseOrderServiceImpl.uploadPurchaseOrder: Irrelevant column available in CSV");
							}

							return finalOutput;
						} else if (poHeaderElements.length > columslist.length) {
							finalOutput.setError("Missing Columns in CSV");
							if (logger.isDebugEnabled()) {
								logger.debug("PurchaseOrderServiceImpl.uploadPurchaseOrder: Missing Columns in CSV");
							}

							return finalOutput;
						} else if (!check) {
							for (int i = 0; i < poHeaderElements.length; i++) {
								String actualHeader = poHeaderElements[i];
								String csvHeader = columslist[i];
								if (!actualHeader.equalsIgnoreCase(csvHeader)) {
									columnErrors = columnErrors + csvHeader + ",";
								}
							}
							finalOutput.setError("Invalid Columns : " + columnErrors);
							if (logger.isDebugEnabled()) {
								logger.debug(
										"PurchaseOrderServiceImpl.uploadPurchaseOrder: Invalid header column names in CSV");
							}

							return finalOutput;
						}
						if (logger.isDebugEnabled()) {
							logger.debug("PurchaseOrderServiceImpl.uploadPurchaseOrder:  Columns Validation Success ");
						}

						// Data load into Request object
						if (lines.size() > 1) {
							int rowindex = 0;
							if (logger.isDebugEnabled()) {
								logger.debug(
										"PurchaseOrderServiceImpl.uploadPurchaseOrder: set the value to ExcelPurchaseOrderSuccessRowsRequest from CSV file");
							}

							for (String rowdata : lines) {
								if (rowindex != 0) {
									int colIndex = 0;
									boolean skiprow = false;
									ExcelPurchaseOrderSuccessRowsRequest rowReqOfPo = new ExcelPurchaseOrderSuccessRowsRequest();
									ExcelPoLineItemsRequest lineItemsRowReq = new ExcelPoLineItemsRequest();
									for (String row : rowdata.split(",")) {
										if (colIndex == 0) {
											/*
											 * if (row == null || row.trim().equals("") || row.trim().isEmpty()) {
											 * skiprow = true; }
											 */
											rowReqOfPo.setPurchaseOrderNumber(row);
										} else if (colIndex == 1) {
											rowReqOfPo.setPoIssueDate(row);
										} else if (colIndex == 2) {
											rowReqOfPo.setPoResponseDate(row);
										} else if (colIndex == 3) {
											rowReqOfPo.setVendorName(row);
										} else if (colIndex == 4) {
											rowReqOfPo.setVendorDuns(row);
										} else if (colIndex == 5) {
											rowReqOfPo.setVendorAddress(row);
										} else if (colIndex == 6) {
											rowReqOfPo.setVendorCity(row);
										} else if (colIndex == 7) {
											rowReqOfPo.setVendorState(row);
										} else if (colIndex == 8) {
											rowReqOfPo.setVendorZip(row);
										} else if (colIndex == 9) {
											rowReqOfPo.setVendorCountry(row);
										} else if (colIndex == 10) {
											rowReqOfPo.setLogisticProvider(row);
										} else if (colIndex == 11) {
											rowReqOfPo.setBuyerName(row);
										} else if (colIndex == 12) {
											rowReqOfPo.setBuyerDuns(row);
										} else if (colIndex == 13) {
											rowReqOfPo.setBillingAddress(row);
										} else if (colIndex == 14) {
											rowReqOfPo.setPoReferenceId(row);
										} else if (colIndex == 15) {
											rowReqOfPo.setPoExpenditureCodeId(row);
										} else if (colIndex == 16) {
											rowReqOfPo.setPoRequistionedBy(row);
										} else if (colIndex == 17) {
											rowReqOfPo.setPoTax(row);
										} else if (colIndex == 18) {
											rowReqOfPo.setPoFreightHandling(row);
										} else if (colIndex == 19) {
											rowReqOfPo.setPoStatus(row);
										} else if (colIndex == 20) {
											rowReqOfPo.setLeadTime(row);
										} else if (colIndex == 21) {
											rowReqOfPo.setPODate(row);
										} else if (colIndex == 22) {
											rowReqOfPo.setNotes(row);
										} else if (colIndex == 23) {
											rowReqOfPo.setOrigin(row);
										} else if (colIndex == 24) {
											rowReqOfPo.setDestination(row);
										} else if (colIndex == 25) {
											lineItemsRowReq.setSkuNumber(row);
										} else if (colIndex == 26) {
											lineItemsRowReq.setSkuDescription(row);
										} else if (colIndex == 27) {
											lineItemsRowReq.setQuantity(row);
										} else if (colIndex == 28) {
											lineItemsRowReq.setCommitedQuantity(row);
										} else if (colIndex == 29) {
											lineItemsRowReq.setDueDate(row);
										} else if (colIndex == 30) {
											lineItemsRowReq.setPromisedDate(row);
										} else if (colIndex == 31) {
											lineItemsRowReq.setUnitOfMeasurement(row);
										} else if (colIndex == 32) {
											lineItemsRowReq.setPrice(row);
										} else if (colIndex == 33) {
											lineItemsRowReq.setShipToLocationCode(row);
										} else if (colIndex == 34) {
											lineItemsRowReq.setShipToCompany(row);
										} else if (colIndex == 35) {
											lineItemsRowReq.setShipToAddress(row);
										} else if (colIndex == 36) {
											lineItemsRowReq.setShipToCity(row);
										} else if (colIndex == 37) {
											lineItemsRowReq.setShipToState(row);
										} else if (colIndex == 38) {
											lineItemsRowReq.setShipToZip(row);
										} else if (colIndex == 39) {
											lineItemsRowReq.setShipToCountry(row);
										}
										colIndex++;
									}
									if (logger.isDebugEnabled()) {
										logger.debug(
												"PurchaseOrderServiceImpl.uploadPurchaseOrder: CSV data Validation Starts ");
									}

									if (!skiprow && rowdata.split(",").length > 0) {
										uploadPurchaseOrderValidator.validateRows(rowReqOfPo, lineItemsRowReq, rowindex,
												lisoferrorrows, lisofvalidrows, listOfLineItems, userName,
												organizationCode);
									}
								}
								rowindex++;
							}
							if (!lisofvalidrows.isEmpty()) {
								if (logger.isDebugEnabled()) {
									logger.debug(
											"PurchaseOrderServiceImpl.uploadPurchaseOrder: Validation successful(No Invalid data found) and Request sets to Response ");

									logger.debug(
											"PurchaseOrderServiceImpl.uploadPurchaseOrder: Mapping done from List of Response to PurchaseOrder Model");
								}

								uploadPurchaseOrderValidator.setPoLineItems(lisofvalidrows, listOfLineItems);

								List<PurchaseOrder> purchaseOrders = poMapper.mapAsList(lisofvalidrows,
										PurchaseOrder.class);
								for (int i = 0; i < purchaseOrders.size(); i++) {
									PurchaseOrder duplicateFlag = poRepository.findByPoNumForDuplication(
											purchaseOrders.get(i).getPurchaseOrderNumber(), organizationCode,
											active.getConstants());
									if (duplicateFlag != null) {
										purchaseOrders.get(i).setPurchaseOrderId(duplicateFlag.getPurchaseOrderId());
										purchaseOrders.get(i).setCreatedBy(duplicateFlag.getCreatedBy());
										purchaseOrders.get(i).setCreatedOn(duplicateFlag.getCreatedOn());
									}
									purchaseOrders.get(i).setDeleteFlag(active.getConstants());
									purchaseOrders.get(i).setOrganizationCode(organizationCode);
									Set<POLineItems> lineItems = purchaseOrders.get(i).getPoLineItems();
									if (lineItems != null) {
										Iterator<POLineItems> itSetPoLineItems = lineItems.iterator();
										while (itSetPoLineItems.hasNext()) {
											POLineItems itSetPOLineItemss = itSetPoLineItems.next();
											String[] skuNumber = itSetPOLineItemss.getSkuNumber().split(",");
											itSetPOLineItemss.setSkuNumber(skuNumber[1]);
											POLineItems lineDuplicateFlag = lineItemsRepository
													.findBySkuIdForDuplication(
															purchaseOrders.get(i).getPurchaseOrderNumber(),
															organizationCode, itSetPOLineItemss.getSkuNumber(),
															itSetPOLineItemss.getShipToCity(),
															itSetPOLineItemss.getPromisedDate(), active.getConstants());
											Date curDate = new Date();
											String DateToStr;
											try {
												SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
												DateToStr = format.format(curDate);
											} catch (Exception ex) {
												throw ex;
											}
											if (lineDuplicateFlag != null) {
												itSetPOLineItemss.setPoItemId(lineDuplicateFlag.getPoItemId());
												itSetPOLineItemss.setPoLineItemsRef(purchaseOrders.get(i));
												itSetPOLineItemss.setUpdatedOn(DateUtils
														.convertStringToTimestamp(DateToStr, "MM-dd-yyyy HH:mm:ss"));

												Set<POTransactions> transactions = itSetPOLineItemss
														.getPoTransactions();
												if (transactions != null) {
													Iterator<POTransactions> itSetPOTransactions = transactions
															.iterator();
													while (itSetPOTransactions.hasNext()) {
														POTransactions poTransactionItems = itSetPOTransactions.next();
														poTransactionItems.setPoTransactionID(
																poTransactionItems.getPoTransactionID());
														poTransactionItems.setPoTransactionRef(itSetPOLineItemss);
														poTransactionItems
																.setPoTransactionStatus(updated.getConstants());
														poTransactionItems.setCreatedOn(
																DateUtils.convertStringToTimestamp(DateToStr,
																		"MM-dd-yyyy HH:mm:ss"));
														poTransactionItems.setUpdatedOn(
																DateUtils.convertStringToTimestamp(DateToStr,
																		"MM-dd-yyyy HH:mm:ss"));
													}
												}
											} else {
												itSetPOLineItemss.setPoLineItemsRef(purchaseOrders.get(i));
												itSetPOLineItemss.setDeleteFlag(active.getConstants());
												itSetPOLineItemss.setCreatedOn(DateUtils
														.convertStringToTimestamp(DateToStr, "MM-dd-yyyy HH:mm:ss"));
												itSetPOLineItemss.setUpdatedOn(DateUtils
														.convertStringToTimestamp(DateToStr, "MM-dd-yyyy HH:mm:ss"));

												Set<POTransactions> transactions = itSetPOLineItemss
														.getPoTransactions();
												if (transactions != null) {
													Iterator<POTransactions> itSetPOTransactions = transactions
															.iterator();
													while (itSetPOTransactions.hasNext()) {
														POTransactions poTransactionItems = itSetPOTransactions.next();
														poTransactionItems.setPoTransactionRef(itSetPOLineItemss);
														poTransactionItems.setDeleteFlag(active.getConstants());
														poTransactionItems.setCreatedOn(
																DateUtils.convertStringToTimestamp(DateToStr,
																		"MM-dd-yyyy HH:mm:ss"));
														poTransactionItems.setUpdatedOn(
																DateUtils.convertStringToTimestamp(DateToStr,
																		"MM-dd-yyyy HH:mm:ss"));
													}
												}

											}
										}
									}
								}

								List<PurchaseOrder> upload = (List<PurchaseOrder>) poRepository.saveAll(purchaseOrders);
								if (logger.isDebugEnabled()) {
									logger.debug(
											"PurchaseOrderServiceImpl.uploadPurchaseOrder:Data Uploaded Successfully into PurchaseOrder table ");

								}

								List<POAPIResponse> purchaseOrderss = poMapper.mapAsList(upload, POAPIResponse.class);
								finalOutput.setSuccessfullData(purchaseOrderss);
								if (logger.isDebugEnabled()) {
									logger.debug(
											"ShipmentEventServiceImpl.uploadEvents: Response mapping from ShipmentEvents ");

									logger.debug(
											"PurchaseOrderServiceImpl.uploadPurchaseOrder: Response mapping from PurchaseOrder ");
								}

								if (!purchaseOrderss.isEmpty()) {
									String message = ShipmentConstants.UPLOAD_PO_SUCCESS_ORDER_NUM_MESSAGE
											+ purchaseOrderss.size();
									finalOutput.setMessage(message);
								}
							}
							if (!lisoferrorrows.isEmpty()) {
								if (logger.isDebugEnabled()) {
									logger.debug(
											"PurchaseOrderServiceImpl.uploadPurchaseOrder: Validation Failed - Invalid Input Passed");
								}

								finalOutput.setConflictedData(lisoferrorrows);
							}
						} else {
							if (logger.isDebugEnabled()) {
								logger.debug(
										"PurchaseOrderServiceImpl.uploadPurchaseOrder - Throwing Exeception as No Data in the file)");
							}

							finalOutput.setMessage(ShipmentConstants.NO_DATA_FOUND_TEMPLATE_MESSAGE);
						}
						if (logger.isDebugEnabled()) {
							logger.debug("PurchaseOrderServiceImpl.uploadPurchaseOrder: Response returned");
						}

						return finalOutput;
					} else {

						logger.error(
								"PurchaseOrderServiceImpl.uploadPurchaseOrder:  - Throwing Exeception as Invalid File Type)");
						throw purchaseOrderUtil.throwInValidDataException(
								POExceptionCode.PURCHASEORDERINVALIDCUSTOM.toString(),
								ShipmentConstants.INVALID_FILE_TYPE_UPLOAD_MESSAGE,
								ShipmentConstants.INVALID_FILE_TYPE_UPLOAD_MESSAGE);
					}
				} else {

					logger.error("PurchaseOrderServiceImpl.uploadPurchaseOrder - Throwing Exeception as Invalid File)");

					throw purchaseOrderUtil.throwInValidDataException(
							POExceptionCode.PURCHASEORDERINVALIDCUSTOM.toString(),
							ShipmentConstants.INVALID_FILE_NAME_TYPE_MESSAGE,
							ShipmentConstants.INVALID_FILE_NAME_TYPE_MESSAGE);
				}
			} else {
				finalOutput.setMessage(ShipmentConstants.EMPTY_USERNAME_ORG_CODE_MESSAGE);
				if (logger.isDebugEnabled()) {
					logger.debug("Username / Organization code is Empty");
				}
				return finalOutput;
			}
		} catch (Exception ex) {
			logger.error("Exception inside PurchaseOrderServiceImpl.uploadPurchaseOrder:" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * Retrieve Shipment Log Order details based on Organization Code, Purchase
	 * Order Number, Sku ID
	 * 
	 * @return Shipment Log Order details
	 * @throws Exception Handle service exceptions
	 */
	@Override
	public POAPIReadShipmentLogOrderResponse getShipmentLogOrderDetailsByPoNum(String purchaseOrderNumber,
			String skuNumber, String organizationCode) {
		logger.info("Entering into PurchaseOrderServiceImpl.getShipmentLogOrderDetailsByPoNum");
		try {

			POAPIReadShipmentLogOrderResponse statusResponse = new POAPIReadShipmentLogOrderResponse();
			if (purchaseOrderNumber != null && !purchaseOrderNumber.isEmpty() && skuNumber != null
					&& !skuNumber.isEmpty() && organizationCode != null && !organizationCode.isEmpty()) {
				PurchaseOrder poValidation = poRepository.validateByOrderSkuOrganization(purchaseOrderNumber, skuNumber,
						organizationCode, active.getConstants());
				if (poValidation != null) {
					IShipmentLogOrderDetails shipmentOrderDetails = poRepository.findShipmentLogOrderDetails(
							purchaseOrderNumber, skuNumber, organizationCode, active.getConstants());
					if (shipmentOrderDetails != null) {
						ShipmentLogOrderDetailsResponse shipmentOrderDetailsResponse = new ShipmentLogOrderDetailsResponse();
						shipmentOrderDetailsResponse
								.setPurchaseOrderNumber(shipmentOrderDetails.getPurchaseOrderNumber());
						shipmentOrderDetailsResponse.setQuantity(shipmentOrderDetails.getQuantity());
						shipmentOrderDetailsResponse.setPromisedDate(shipmentOrderDetails.getPromisedDate());
						shipmentOrderDetailsResponse.setSkuNumber(shipmentOrderDetails.getSkuNumber());
						shipmentOrderDetailsResponse.setOrigin(shipmentOrderDetails.getOrigin());
						shipmentOrderDetailsResponse.setShipToAddress(shipmentOrderDetails.getShipToAddress());
						shipmentOrderDetailsResponse.setShipToCity(shipmentOrderDetails.getShipToCity());
						shipmentOrderDetailsResponse.setShipToState(shipmentOrderDetails.getShipToState());
						shipmentOrderDetailsResponse.setShipToZip(shipmentOrderDetails.getShipToZip());
						statusResponse.setStatus(success.getConstants());
						statusResponse.setSuccessfullData(shipmentOrderDetailsResponse);
						statusResponse.setMessage(
								String.format(ShipmentConstants.DATA_RETRIEVE_ORDER_NUM_SKU_ID_ORG_SUCCESS_MESSAGE,
										purchaseOrderNumber, skuNumber, organizationCode));
						return statusResponse;
					} else {
						statusResponse.setStatus(failure.getConstants());
						statusResponse.setMessage(
								String.format(ShipmentConstants.DATA_RETRIEVE_ORDER_NUM_SKU_ID_ORG_FAILURE_MESSAGE,
										purchaseOrderNumber, skuNumber, organizationCode));
						if (logger.isDebugEnabled()) {
							logger.debug("No Data Available for Order Number / SKU ID / Organization code : "
									+ organizationCode);
						}
						return statusResponse;
					}
				} else {
					statusResponse.setStatus(failure.getConstants());
					statusResponse
							.setMessage(String.format(ShipmentConstants.INVALID_ORDER_NUM_SKU_ID_ORG_FAILURE_MESSAGE,
									purchaseOrderNumber, skuNumber, organizationCode));
					if (logger.isDebugEnabled()) {
						logger.debug("Invalid Order Number / SKU ID / Organization code :  " + organizationCode);
					}
					return statusResponse;
				}
			} else {
				statusResponse.setStatus(failure.getConstants());
				statusResponse.setMessage(ShipmentConstants.EMPTY_ORDER_NUM_SKU_ID_ORG_FAILURE_MESSAGE);
				if (logger.isDebugEnabled()) {
					logger.debug(" Order Number / SKU ID / Organization code is Empty");
				}
				return statusResponse;
			}
		} catch (Exception ex) {
			logger.error("Exception inside PurchaseOrderServiceImpl.getShipmentLogOrderDetailsByPoNum.Catch_Block:"
					+ ex.getMessage());
			throw ex;
		}
	}

	/**
	 * Retrieve List of Shipment Log details based on Organization Code, Purchase
	 * Order Number, Sku ID
	 * 
	 * @return List of Shipment Log details
	 * @throws Exception Handle service exceptions
	 */
	@Override
	public POAPIReadShipmentLogDetailListResponse getShipmentLogDetailsByPoNum(String purchaseOrderNumber,
			String skuNumber, String organizationCode) {
		if (logger.isDebugEnabled()) {
			logger.debug(
					"Entering to PurchaseOrderServiceImpl.getShipmentLogDetailsByPoNum - Fetching Shipment log details");
		}

		try {
			POAPIReadShipmentLogDetailListResponse statusResponse = new POAPIReadShipmentLogDetailListResponse();
			if (purchaseOrderNumber != null && !purchaseOrderNumber.isEmpty() && skuNumber != null
					&& !skuNumber.isEmpty() && organizationCode != null && !organizationCode.isEmpty()) {
				PurchaseOrder poValidation = poRepository.validateByOrderSkuOrganization(purchaseOrderNumber, skuNumber,
						organizationCode, active.getConstants());
				if (poValidation != null) {
					List<ShipmentLogsDetailsResponse> shipmentResponseList = new ArrayList<>();
					List<ShipmentLogsDetails> shipmentlogs = new ArrayList<>();
					List<IShipmentLogDetails> shipmentLogResults = poRepository.findShipmentLogDetails(
							purchaseOrderNumber, skuNumber, organizationCode, active.getConstants());
					if (shipmentLogResults != null && !shipmentLogResults.isEmpty()) {
						if (logger.isDebugEnabled()) {
							logger.debug(
									"PurchaseOrderServiceImpl.getShipmentLogDetailsByPoNum - adding data to ShipmentLogDetails ");
						}

						for (IShipmentLogDetails shipment : shipmentLogResults) {
							shipmentlogs.add(new ShipmentLogsDetails(shipment.getShipmentNumber(), shipment.getUnitId(),
									shipment.getItemQuantity(), shipment.getPromisedDate(), shipment.getBillToName(),
									shipment.getTransportationMethod(), shipment.getStatus(),
									shipment.getShippingCompany(), shipment.getWayBillNumber(), shipment.getAsnNumber(),
									shipment.getTrackingNumber(), shipment.getExpectedPickupDate(),
									shipment.getOrigin(), shipment.getOriginAddress1(), shipment.getOriginAddress2(),
									shipment.getOriginCity(), shipment.getOriginState(), shipment.getOriginZipCode(),
									shipment.getOriginCountry(), shipment.getDestination(),
									shipment.getDestinationAddress1(), shipment.getDestinationAddress2(),
									shipment.getDestinationCity(), shipment.getDestinationState(),
									shipment.getDestinationZipCode(), shipment.getDestinationCountry(),
									shipment.getTotalWeight(), shipment.getShipmentNumberPart(),
									shipment.getShipmentNumberItems(), shipment.getNumberOfLineItems(),
									shipment.getPoNumber(), shipment.getItemNumber(), shipment.getItemDescription(),
									shipment.getItemQuantityUOM(), shipment.getPrice(),
									shipment.getContentDescription(), shipment.getWeightQualifier()));
						}
						for (int i = 0; i < shipmentlogs.size(); i++) {
							ShipmentLogsDetailsResponse responseList = new ShipmentLogsDetailsResponse();
							responseList.setShipmentNumber(shipmentlogs.get(i).getShipmentNumber());
							responseList.setUnitId(shipmentlogs.get(i).getUnitId());
							responseList.setItemQuantity(shipmentlogs.get(i).getItemQuantity());
							responseList.setPromisedDate(shipmentlogs.get(i).getPromisedDate());
							responseList.setBillToName(shipmentlogs.get(i).getBillToName());
							responseList.setTransportationMethod(shipmentlogs.get(i).getTransportationMethod());
							responseList.setStatus(shipmentlogs.get(i).getStatus());
							responseList.setShippingCompany(shipmentlogs.get(i).getShippingCompany());
							responseList.setWayBillNumber(shipmentlogs.get(i).getWayBillNumber());
							responseList.setAsnNumber(shipmentlogs.get(i).getAsnNumber());
							responseList.setTrackingNumber(shipmentlogs.get(i).getTrackingNumber());
							responseList.setExpectedPickupDate(shipmentlogs.get(i).getExpectedPickupDate());
							responseList.setOrigin(shipmentlogs.get(i).getOrigin());
							responseList.setOriginAddress1(shipmentlogs.get(i).getOriginAddress1());
							responseList.setOriginAddress2(shipmentlogs.get(i).getOriginAddress2());
							responseList.setOriginCity(shipmentlogs.get(i).getOriginCity());
							responseList.setOriginState(shipmentlogs.get(i).getOriginState());
							responseList.setOriginZipCode(shipmentlogs.get(i).getOriginZipCode());
							responseList.setOriginCountry(shipmentlogs.get(i).getOriginCountry());
							responseList.setDestination(shipmentlogs.get(i).getDestination());
							responseList.setDestinationAddress1(shipmentlogs.get(i).getDestinationAddress1());
							responseList.setDestinationAddress2(shipmentlogs.get(i).getDestinationAddress2());
							responseList.setDestinationCity(shipmentlogs.get(i).getDestinationCity());
							responseList.setDestinationState(shipmentlogs.get(i).getDestinationState());
							responseList.setDestinationZipCode(shipmentlogs.get(i).getDestinationZipCode());
							responseList.setDestinationCountry(shipmentlogs.get(i).getDestinationCountry());
							responseList.setTotalWeight(shipmentlogs.get(i).getTotalWeight());
							responseList.setShipmentNumberPart(shipmentlogs.get(i).getShipmentNumberPart());
							responseList.setShipmentNumberItems(shipmentlogs.get(i).getShipmentNumberItems());
							responseList.setNumberOfLineItems(shipmentlogs.get(i).getNumberOfLineItems());
							responseList.setPoNumber(shipmentlogs.get(i).getPoNumber());
							responseList.setItemNumber(shipmentlogs.get(i).getItemNumber());
							responseList.setItemDescription(shipmentlogs.get(i).getItemDescription());
							responseList.setItemQuantityUOM(shipmentlogs.get(i).getItemQuantityUOM());
							responseList.setPrice(shipmentlogs.get(i).getPrice());
							responseList.setContentDescription(shipmentlogs.get(i).getContentDescription());
							responseList.setWeightQualifier(shipmentlogs.get(i).getWeightQualifier());
							shipmentResponseList.add(responseList);
						}
						statusResponse.setStatus(success.getConstants());
						statusResponse.setSuccessfullData(shipmentResponseList);
						statusResponse.setMessage(
								String.format(ShipmentConstants.DATA_RETRIEVE_ORDER_NUM_SKU_ID_ORG_SUCCESS_MESSAGE,
										purchaseOrderNumber, skuNumber, organizationCode));
						return statusResponse;
					} else {
						statusResponse.setStatus(failure.getConstants());
						statusResponse.setMessage(
								String.format(ShipmentConstants.DATA_RETRIEVE_ORDER_NUM_SKU_ID_ORG_FAILURE_MESSAGE,
										purchaseOrderNumber, skuNumber, organizationCode));
						if (logger.isDebugEnabled()) {
							logger.debug(
									"No Shipment Log Data Available for Order Number / SKU ID / Organization code : "
											+ organizationCode);
						}
						return statusResponse;
					}
				} else {
					statusResponse.setStatus(failure.getConstants());
					statusResponse
							.setMessage(String.format(ShipmentConstants.INVALID_ORDER_NUM_SKU_ID_ORG_FAILURE_MESSAGE,
									purchaseOrderNumber, skuNumber, organizationCode));
					if (logger.isDebugEnabled()) {
						logger.debug("Invalid Order Number / SKU ID / Organization code :  " + organizationCode);
					}
					return statusResponse;
				}
			} else {
				statusResponse.setStatus(failure.getConstants());
				statusResponse.setMessage(ShipmentConstants.EMPTY_ORDER_NUM_SKU_ID_ORG_FAILURE_MESSAGE);
				if (logger.isDebugEnabled()) {
					logger.debug(" Order Number / SKU ID / Organization code is Empty");
				}
				return statusResponse;
			}
		} catch (Exception ex) {
			logger.error("Exception inside PurchaseOrderServiceImpl.getShipmentLogDetailsByPoNum.Catch_Block:"
					+ ex.getMessage());
			throw ex;
		}
	}

	/**
	 * Update Purchase Order Status based on Organization Code, UserName,
	 * StatusRequest
	 * 
	 * @return Update Purchase Order Status
	 * @throws Exception Handle service exceptions
	 */
	@Override
	public CommonResponse updatePurchaseOrderStatus(E2EShipmentPOStatusRequest statusRequest, String organizationCode,
			String userName) {
		try {
			CommonResponse commonResponse = new CommonResponse();
			Date convertedDate = new Date();
			Long lineItemId = statusRequest.getPoLineItemId();
			String status = statusRequest.getStatus();
			if (statusRequest.getDate() != null) {
				String dtTime = statusRequest.getDate();
				if (dtTime.contains("/")) {
					dtTime = dtTime.replaceAll("/", "-");
				}
				if (!dtTime.contains(":")) {
					dtTime = dtTime + " 00:00";
				}
				convertedDate = DateUtil.convertValidStringToDate(dtTime, "MM-dd-yyyy HH:mm");
				if (convertedDate == null) {
					commonResponse.setStatus("Invalid Date....Suggested is MM/dd/yyyy HH:mm...");
					return commonResponse;
				}

			}
			if (!organizationCode.isEmpty() && organizationCode != null && !userName.isEmpty() && userName != null
					&& !status.isEmpty() && status != null && lineItemId != null) {
				if (status.equalsIgnoreCase(orderShipped.getConstants())) {
					if (statusRequest.getShipmentNumber() == null || statusRequest.getShipmentNumber().isEmpty()) {
						commonResponse.setStatus("Shipment Number should not empty if Status is Order Shipped..");
						return commonResponse;
					}
				}

				if (status.equalsIgnoreCase(orderShipped.getConstants())
						|| status.equalsIgnoreCase(readyToShip.getConstants())) {
					POLineItems lineItems = lineItemsRepository.findByPoItemIdAndDeleteFlag(lineItemId,
							active.getConstants());
					if (lineItems == null) {
						commonResponse.setStatus("Invalide Line Item Id ... " + lineItemId);
						return commonResponse;
					} else {

						Set<POTransactions> transactions = new HashSet<>();
						POTransactions objTransactions = new POTransactions();
						objTransactions.setPoTransactionRef(lineItems);
						objTransactions.setPoStatus(lineItems.getLineItemStatus());
						objTransactions.setCreatedBy(userName);
						objTransactions.setCreatedOn(new Timestamp(System.currentTimeMillis()));
						objTransactions.setUpdatedBy(userName);
						objTransactions.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
						if (status.equalsIgnoreCase(orderShipped.getConstants())) {

							lineItems.setLineItemStatus(orderShipped.getConstants());
							objTransactions.setPoStatus(orderShipped.getConstants());
						}
						if (status.equalsIgnoreCase(readyToShip.getConstants())) {
							lineItems.setLineItemStatus(readyToShip.getConstants());
							objTransactions.setPoStatus(readyToShip.getConstants());
						}

						objTransactions.setOrganizationCode(organizationCode);
						objTransactions.setPoTransactionDate(new Timestamp(convertedDate.getTime()));
						logger.info("date:  " + objTransactions.getPoTransactionDate());
						// objTransactions.setPoTransactionDate(new
						// Timestamp(System.currentTimeMillis()));
						objTransactions.setPoTransactionStatus(create.getConstants());
						objTransactions.setDeleteFlag(active.getConstants());
						transactions.add(objTransactions);
						lineItems.setPoTransactions(transactions);
						POLineItems lineItemss = lineItemsRepository.save(lineItems);
						if (statusRequest.getShipmentNumber() != null && !statusRequest.getShipmentNumber().isEmpty()) {
							if (lineItemss.getLineItemStatus().equalsIgnoreCase(orderShipped.getConstants())
									|| lineItemss.getLineItemStatus().equalsIgnoreCase(readyToShip.getConstants())) {
								String poNumber = statusRequest.getPoNumber();
								String unitId = statusRequest.getUnitId();
								String shipmentNumber = statusRequest.getShipmentNumber();
								String origin = statusRequest.getOrigin();
								String destination = statusRequest.getDestination();
								String itemNumber = statusRequest.getItemNumber();
								AdvanceShipmentNotice duplicateFlag = shipmentRepository.findForCreateShipment(
										shipmentNumber, origin, destination, organizationCode, "N");
								if (duplicateFlag != null) {
									E2EShipmentAPIRequest shipmentAPIRequest = shipmentMapper.map(duplicateFlag,
											E2EShipmentAPIRequest.class);
									shipmentAPIRequest.setUnitId(unitId);
									Set<FreightLineItemsAPIRequest> lineItemsss = shipmentAPIRequest
											.getFreightLineItems();
									Iterator<FreightLineItemsAPIRequest> itSetASNLineItems = lineItemsss.iterator();
									while (itSetASNLineItems.hasNext()) {
										FreightLineItemsAPIRequest itSetASNLineItemss = itSetASNLineItems.next();
										itSetASNLineItemss.setPoNumber(poNumber);

									}
									CommonResponse updateShipment = shipmentService.updateShipment(shipmentAPIRequest,
											shipmentAPIRequest.getShipmentId(), userName, organizationCode);
									if (updateShipment != null) {
										commonResponse.setStatus("PO Line Item Status Updated as "
												+ lineItemss.getLineItemStatus() + " " + lineItemId
												+ "And Shipment Updated.." + updateShipment.getStatus());
										return commonResponse;
									} else {
										commonResponse.setStatus("PO Line Item Status Updated as "
												+ lineItemss.getLineItemStatus() + " " + lineItemId
												+ "And Shipment not updated...some thing went wrong...");
										return commonResponse;
									}
								} else {
									E2EShipmentAPIRequest asnAPIRequest = new E2EShipmentAPIRequest();
									asnAPIRequest.setStatus(status);
									asnAPIRequest.setShipmentId(0L);
									asnAPIRequest.setUnitId(unitId);
									asnAPIRequest.setShipmentNumber(shipmentNumber);
									asnAPIRequest.setOrigin(origin);
									asnAPIRequest.setIsExport("N");
									asnAPIRequest.setIsHazmat("N");
									asnAPIRequest.setIsTemperatureController("Y");
									asnAPIRequest.setStatus(status);
									asnAPIRequest.setDestination(destination);
									Set<FreightLineItemsAPIRequest> freightLineItems = new HashSet<>();
									FreightLineItemsAPIRequest freightItems = new FreightLineItemsAPIRequest();
									freightItems.setItemNumber(itemNumber);
									freightItems.setPoNumber(poNumber);
									freightItems.setCreatedBy(userName);
									freightItems.setUpdatedBy(userName);
									freightItems.setCreatedDateTime(new Date());
									freightItems.setUpdatedDateTime(new Date());
									freightLineItems.add(freightItems);
									asnAPIRequest.setFreightLineItems(freightLineItems);
									CommonResponse createShipment = shipmentService.createE2EShipment(asnAPIRequest,
											userName, organizationCode);
									if (createShipment != null) {
										commonResponse.setStatus(
												"PO Line Item Status Updated as ' Order_Shipped '.." + lineItemId
														+ "And new Shipment Created.." + createShipment.getStatus());
										return commonResponse;
									} else {
										commonResponse.setStatus("PO Line Item Status Updated as ' Order_Shipped '.."
												+ lineItemId + "And Shipment not created...some thing went wrong...");
										return commonResponse;
									}
								}

							} else {
								commonResponse.setStatus(
										"Status updated wrong... Please check for Line Item Id.." + lineItemId);
							}
							return commonResponse;
						} else {
							commonResponse.setStatus("PO Line Item Status Updated as ' Ready To Ship '.." + lineItemId);
							return commonResponse;
						}

					}

				} else {

					POLineItems lineItems = lineItemsRepository.findByPoItemIdAndDeleteFlag(lineItemId,
							active.getConstants());
					lineItems.setLineItemStatus(status);
					Set<POTransactions> transactions = new HashSet<>();
					POTransactions objTransactions = new POTransactions();
					objTransactions.setPoTransactionRef(lineItems);
					objTransactions.setPoStatus(lineItems.getLineItemStatus());
					objTransactions.setCreatedBy(userName);
					objTransactions.setCreatedOn(new Timestamp(System.currentTimeMillis()));
					objTransactions.setUpdatedBy(userName);
					objTransactions.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
					objTransactions.setPoStatus(status);
					objTransactions.setOrganizationCode(organizationCode);
					objTransactions.setPoTransactionDate(new Timestamp(convertedDate.getTime()));
					objTransactions.setPoTransactionStatus(create.getConstants());
					Timestamp ts = new Timestamp(convertedDate.getTime());
					logger.info("date: " + objTransactions.getPoTransactionDate());

					objTransactions.setDeleteFlag(active.getConstants());
					transactions.add(objTransactions);
					lineItems.setPoTransactions(transactions);
					POLineItems lineItemss = lineItemsRepository.save(lineItems);
					if (lineItemss.getLineItemStatus().equalsIgnoreCase(status)) {
						commonResponse.setStatus(
								"PO Line Item Status Updated as  " + status + "...For Line Id : " + lineItemId);
						return commonResponse;
					} else {
						commonResponse.setStatus("Invalid PO_Line_Item_ID passed.." + lineItemId);
						return commonResponse;
					}

				}
			} else {
				commonResponse.setStatus("Invalid Input passed..");
				return commonResponse;
			}
		} catch (Exception ex) {
			logger.error("Exception inside PurchaseOrderServiceImpl.updatePurchaseOrderStatus.Catch_Block:"
					+ ex.getMessage());
			throw ex;
		}
	}

	@Override
	public List<POAPIResponse> getPOByShipment(String shipmentNumber, String asnNumber, String organizationCode) {
		try {
			List<PurchaseOrder> poList = new ArrayList<>();
			List<POLineItems> poLineItems = new ArrayList<>();
			List<AdvanceShipmentNotice> shipment = (List<AdvanceShipmentNotice>) shipmentRepository
					.findShipment(shipmentNumber, asnNumber, organizationCode, "N");
			for (int i = 0; i < shipment.size(); i++) {
				Set<FreightLineItems> lineItems = shipment.get(i).getFreightLineItems();
				if (lineItems != null) {
					Iterator<FreightLineItems> itSetPoLineItems = lineItems.iterator();
					while (itSetPoLineItems.hasNext()) {
						FreightLineItems lines = itSetPoLineItems.next();
						String poNumber = lines.getPoNumber();
						List<POLineItems> litems = (List<POLineItems>) lineItemsRepository
								.findBySkuNumberAndDeleteFlag(lines.getItemNumber(), "Active");
						for (int j = 0; j < litems.size(); j++) {
							if (litems.get(j).getPoLineItemsRef().getPurchaseOrderNumber().equalsIgnoreCase(poNumber)
									&& litems.get(j).getPoLineItemsRef().getOrganizationCode()
											.equalsIgnoreCase(organizationCode)) {
								poList.add(litems.get(j).getPoLineItemsRef());
							}
						}
						// poLineItems.addAll(litems);
					}
				}
			}

			/*
			 * for(int i=0;i<poLineItems.size();i++) { PurchaseOrder po = new
			 * PurchaseOrder(); po = poLineItems.get(0).getPoLineItemsRef(); poList.add(po);
			 * }
			 */
			List<POAPIResponse> pOrders = poMapper.mapAsList(poList, POAPIResponse.class);
			return pOrders;
		} catch (Exception ex) {
			logger.error("Exception inside PurchaseOrderServiceImpl.updatePurchaseOrderStatus.Catch_Block:"
					+ ex.getMessage());
			throw ex;
		}

	}

	@Override
	public ResponseEntity<String> invokeblockchain(String po, String poref, String seller, String buyer) {
		String purchaseurl = ShipmentConstants.SHIPMENT_QUERY_PARAM;
		RestTemplate restTemplate = new RestTemplateBuilder().build();
		// need to move this creation to specific method
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "RRRB");
		headers.set("Content-Type", "application/json");
		// Force the request expires
		headers.setExpires(0);
		// Cache-Control: private, no-store, max-age=0
		headers.setCacheControl("private, no-store, max-age=0");
		HttpEntity<String> requestHttpEntity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> response = null;
		// need to validate the inputs
		if (StringUtils.isNotBlank(purchaseurl)) {
			purchaseurl = purchaseurl.replace("%PO", po).replace("%REF", poref).replace("%SELLER", seller)
					.replace("%BUYER", buyer);
			purchaseurl = blockchainBaseUrl + purchaseurl;
		}
		// need to set the time out
		logger.info("Request Url formed is " + purchaseurl);
		try {
			response = restTemplate.exchange(purchaseurl, HttpMethod.GET, requestHttpEntity, String.class);
			logger.info("Response from block chain" + response.getBody());
		} catch (HttpStatusCodeException httpexception) {
			logger.error("HttpStatusCodeException in blockchain invocation" + httpexception.getMessage());
			// need to throw the exception or create error response
		} catch (Exception e) {
			logger.error("Exception in blockchain invocation " + e.getMessage());
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.GATEWAY_TIMEOUT);
		}

		return response;
	}

	@Override
	public ResponseEntity<String> invokeblockchainForShipment(String shipmentId) {
		String shipmenturl = ShipmentConstants.SHIPMENT_HISTORY_PATH;
		RestTemplate restTemplate = new RestTemplateBuilder().build();
		// need to move this creation to specific method
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "RRRB");
		headers.set("Content-Type", "application/json");
		// Force the request expires
		headers.setExpires(0);
		// Cache-Control: private, no-store, max-age=0
		headers.setCacheControl("private, no-store, max-age=0");
		HttpEntity<String> requestHttpEntity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> response = null;
		// need to validate the inputs
		if (StringUtils.isNotBlank(shipmenturl)) {
			shipmenturl = shipmenturl.replace("%ID", shipmentId);
			shipmenturl = blockchainBaseUrl + shipmenturl;
		}
		// need to set the time out
		logger.info("Request Url formed is " + shipmenturl);
		try {
			response = restTemplate.exchange(shipmenturl, HttpMethod.GET, requestHttpEntity, String.class);
			logger.info("Response from block chain" + response.getBody());
		} catch (HttpStatusCodeException httpexception) {
			logger.error("HttpStatusCodeException in blockchain invocation for shipment history for " + shipmentId
					+ "is " + httpexception.getMessage());
			// need to throw the exception or create error response
		} catch (Exception e) {
			logger.error("Exception in blockchain invocation for shipment history for" + shipmentId + "is "
					+ e.getMessage());
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.GATEWAY_TIMEOUT);
		}

		return response;
	}
}