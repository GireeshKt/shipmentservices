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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.blumeglobal.shipmentmanagement.dao.repositories.AdvanceShipmentNoticeRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.FreightLineItemsRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.POLineItemsRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.POTransactionsRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.PurchaseOrderRepository;
import com.blumeglobal.shipmentmanagement.enums.Constants;
import com.blumeglobal.shipmentmanagement.enums.ExceptionCode;
import com.blumeglobal.shipmentmanagement.model.AdvanceShipmentNotice;
import com.blumeglobal.shipmentmanagement.model.FreightLineItems;
import com.blumeglobal.shipmentmanagement.model.IShipmentOrderLine;
import com.blumeglobal.shipmentmanagement.model.POLineItems;
import com.blumeglobal.shipmentmanagement.model.POTransactions;
import com.blumeglobal.shipmentmanagement.model.PurchaseOrder;
import com.blumeglobal.shipmentmanagement.model.mapper.AdvanceShipmentNoticeMapper;
import com.blumeglobal.shipmentmanagement.request.E2EShipmentAPIRequest;
import com.blumeglobal.shipmentmanagement.request.FreightLineItemsAPIRequest;
import com.blumeglobal.shipmentmanagement.request.UploadE2EShipmentRequest;
import com.blumeglobal.shipmentmanagement.request.UploadFreightLineItemsRequest;
import com.blumeglobal.shipmentmanagement.response.CommonResponse;
import com.blumeglobal.shipmentmanagement.response.E2EShipmentAPIResponse;
import com.blumeglobal.shipmentmanagement.response.ExcelErrorRowsResponse;
import com.blumeglobal.shipmentmanagement.response.ExcelUploadResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentOrderLineResponse;
import com.blumeglobal.shipmentmanagement.response.StatusResponse;
import com.blumeglobal.shipmentmanagement.service.IE2EShipmentService;
import com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants;
import com.blumeglobal.shipmentmanagement.utils.BlumeVisibilityUtil;
import com.blumeglobal.shipmentmanagement.utils.ShipmentUtil;
import com.blumeglobal.shipmentmanagement.validator.UploadASNDataValidator;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

@Service
@REZ1Logger
@REZ1PerformanceLogger
@Transactional
public class E2EShipmentServiceImpl implements IE2EShipmentService {

	private static final Logger logger = LogManager.getLogger(E2EShipmentServiceImpl.class);

	@Autowired
	AdvanceShipmentNoticeRepository shipmentRepository;

	@Autowired
	AdvanceShipmentNoticeMapper shipmentMapper;

	@Autowired
	PurchaseOrderRepository poRepository;

	@Autowired
	FreightLineItemsRepository fliRepository;

	@Autowired
	POTransactionsRepository poTransRepo;

	@Autowired
	BlumeVisibilityUtil blumeUtil;

	@Autowired
	UploadASNDataValidator uploadASNDataValidator;

	@Autowired
	ShipmentUtil shipmentUtil;

	@Autowired
	POLineItemsRepository liRepository;

	Constants system = Constants.valueOf("SYSTEM");
	Constants active = Constants.valueOf("ACTIVE");
	Constants updated = Constants.valueOf("UPDATED");
	Constants orderShipped = Constants.valueOf("ORDERSHIPPED");
	Constants created = Constants.valueOf("CREATED");
	Constants create =  Constants.valueOf("ORDERPLACED");
	Constants readyToShip = Constants.valueOf("READYTOSHIP");

	@Override
	public CommonResponse createE2EShipment(E2EShipmentAPIRequest asnAPIRequest, String userName,
				String organizationCode) {
	
			try {
				logger.info("Entering into ASNShipmentServiceImpl.createE2EShipment");
				CommonResponse commonRespons = new CommonResponse();
				String status = asnAPIRequest.getStatus();
				asnAPIRequest.setStatus(create.getConstants());
				List<String> errorMsgList = new ArrayList<>();
				ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
				Validator validator = factory.getValidator();
				Set<ConstraintViolation<E2EShipmentAPIRequest>> violations = validator.validate(asnAPIRequest);
				boolean validflag = true;
				StringBuilder sb = new StringBuilder("");
				for (ConstraintViolation<E2EShipmentAPIRequest> violation : violations) {
					String message = violation.getMessage();
					errorMsgList.add(message);
					sb.append(message);
					sb.append("; ");
					validflag = false;
				}
				Set<FreightLineItemsAPIRequest> lineItemss = asnAPIRequest.getFreightLineItems();
				
				Iterator<FreightLineItemsAPIRequest> lineItemsSet = lineItemss.iterator();
				while (lineItemsSet.hasNext()) {
					FreightLineItemsAPIRequest poLitems = lineItemsSet.next();
	
					Set<ConstraintViolation<FreightLineItemsAPIRequest>> lineViolations = validator.validate(poLitems);
					for (ConstraintViolation<FreightLineItemsAPIRequest> lineViolation : lineViolations) {
						String message = lineViolation.getMessage();
						errorMsgList.add(message);
						sb.append(message);
						validflag = false;
					}
				}
	
				Date curDate = new Date();
				String DateToStr;
				try {
	
					SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
					DateToStr = format.format(curDate);
	
				} catch (Exception ex) {
					throw ex;
				}
	
				if (asnAPIRequest.getShipmentNumber() != null&& asnAPIRequest.getOrigin() != null && asnAPIRequest.getDestination() != null) {
					if(logger.isDebugEnabled()) {
						logger.debug("Entering into ASNShipmentServiceImpl.Checking for Duplicates");
					}
					
					AdvanceShipmentNotice duplicateFlag = shipmentRepository.findForCreateShipment(asnAPIRequest.getShipmentNumber(),
							asnAPIRequest.getOrigin(), asnAPIRequest.getDestination(), organizationCode, "N");
					if (duplicateFlag == null) {
						if(logger.isDebugEnabled()) {
							logger.debug("Entering into ASNShipmentServiceImpl.Given Shipment details are proper");
						}
						
						if (userName != null) {
							asnAPIRequest.setCreatedBy(userName);
							asnAPIRequest.setUpdatedBy(userName);
						} else {
	
							asnAPIRequest.setCreatedBy(system.getConstants().toString());
							asnAPIRequest.setUpdatedBy(system.getConstants().toString());
						}
	
						if ((asnAPIRequest.getIsExport().equalsIgnoreCase("N")
								|| asnAPIRequest.getIsExport().equalsIgnoreCase("Y"))
								&& (asnAPIRequest.getIsHazmat().equalsIgnoreCase("N")
										|| asnAPIRequest.getIsHazmat().equalsIgnoreCase("Y"))
								&& (asnAPIRequest.getIsTemperatureController().equalsIgnoreCase("N")
										|| asnAPIRequest.getIsTemperatureController().equalsIgnoreCase("Y"))) {
							asnAPIRequest.getIsExport().toUpperCase();
							asnAPIRequest.getIsHazmat().toUpperCase();
							asnAPIRequest.getIsTemperatureController();
	
						} else {
							commonRespons.setStatus(ShipmentConstants.HAZMAT_EXPORT_TEMPCONT_VALUE_ERROR);
							return commonRespons;
						}
	
						String dc = asnAPIRequest.getDestinationCountry();
						String oc = asnAPIRequest.getOriginCountry();
						if (dc != null && oc != null) {
							if (dc.length() > 3 && oc.length() > 3) {
	
								commonRespons.setStatus(ShipmentConstants.DESTCOUNTRY_ORIGINCOUNTRY_LENGTH_ERROR);
								return commonRespons;
							}
						}
						AdvanceShipmentNotice request = shipmentMapper.map(asnAPIRequest, AdvanceShipmentNotice.class);
						if(logger.isDebugEnabled()) {
							logger.debug("Entering into ASNShipmentServiceImpl.Mapping to E2EShipmentAPIRequest to E2EShipment");
						}
						
						Set<FreightLineItems> lineItems = request.getFreightLineItems();
						if(logger.isDebugEnabled()) {
							logger.debug("Entering into ASNShipmentServiceImpl.Child object setting");
						}
						
						Iterator<FreightLineItems> itSetASNLineItems = lineItems.iterator();
						while (itSetASNLineItems.hasNext()) {
							FreightLineItems itSetASNLineItemss = itSetASNLineItems.next();
							itSetASNLineItemss.setFreightLineItemsRef(request);
							itSetASNLineItemss.setDeleteFlag("N");
							itSetASNLineItemss.setCreatedDateTime(new Date());
							itSetASNLineItemss.setUpdatedDateTime(new Date());
	
						}
						request.setOrganizationCode(organizationCode);
						request.setDeleteFlag("N");
						request.setCreatedDateTime(new Date());
						request.setUpdatedDateTime(new Date());
						AdvanceShipmentNotice createShipment = shipmentRepository.save(request);
						if(logger.isDebugEnabled()) {
							logger.debug("Entering into ASNShipmentServiceImpl.Shipment Created");
						}
						
						if (createShipment != null) {
							if(logger.isDebugEnabled()) {
								logger.debug("Entering into ASNShipmentServiceImpl.Trying to change status in PO Line Items if same PO is availble as Order Uploaded and Transaction creation");
							}
							
							Set<FreightLineItems> flineItems = request.getFreightLineItems();
							Iterator<FreightLineItems> fitSetASNLineItems = flineItems.iterator();
							if(status!=readyToShip.getConstants()) {
							while (fitSetASNLineItems.hasNext()) {
								FreightLineItems fLItems = fitSetASNLineItems.next();
								List<PurchaseOrder> po = poRepository
										.findByPurchaseOrderNumberAndOrganizationCodeAndDeleteFlag(fLItems.getPoNumber(),
												organizationCode, active.getConstants());
								if (po != null && !po.isEmpty()) {
									List<POLineItems> lItems = liRepository
											.findBySkuNumberAndDeleteFlagAndPoLineItemsRefIn(
													fLItems.getItemNumber(),
													active.getConstants(), po);
									if (!lItems.isEmpty() && lItems != null) {
										for (int j = 0; j < lItems.size(); j++) {
											lItems.get(j).setLineItemStatus(readyToShip.getConstants());
											Set<POTransactions> poTransactionSet = new HashSet<>();
											POTransactions poTransactionItems = new POTransactions();
											poTransactionItems.setPoTransactionRef(lItems.get(j));
											poTransactionItems.setPoStatus(lItems.get(j).getLineItemStatus());
											poTransactionItems.setPoTransactionStatus(created.getConstants());
											poTransactionItems.setCreatedBy(userName);
											poTransactionItems.setCreatedOn(new Timestamp(System.currentTimeMillis()));
											poTransactionItems.setUpdatedBy(userName);
											poTransactionItems.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
											poTransactionItems.setOrganizationCode(organizationCode);
											poTransactionItems.setDeleteFlag(active.getConstants());
											poTransactionItems
													.setPoTransactionDate(new Timestamp(System.currentTimeMillis()));
											poTransactionSet.add(poTransactionItems);
											lItems.get(j).setPoTransactions(poTransactionSet);
										}
										List<POLineItems> savePOItems = (List<POLineItems>) liRepository.saveAll(lItems);
										if(logger.isDebugEnabled()) {
											logger.debug("Entering into ASNShipmentServiceImpl.PO Line Items Line item Status created as 'Order Uploaded' and Transaction created");
										}
										
									}
								}
									
							}
						}
							
							if(logger.isDebugEnabled()) {
								logger.debug("Entering into ASNShipmentServiceImpl.Shipment Created successfully...");
							}
							
							commonRespons.setStatus(
									ShipmentConstants.SHIPMENT_CREATION_SUCCESS_MESSAGE + createShipment.getShipmentId());
	
							
						} else {
							commonRespons.setStatus(ShipmentConstants.SHIPMENT_CREATION_ERROR_MESSAGE);
							if(logger.isDebugEnabled()) {
								logger.debug("Entering into ASNShipmentServiceImpl.Unable to create Shipment");
							}
							
						}
						return commonRespons;
	
					} else {
						if(logger.isDebugEnabled()) {
							logger.debug("Entering into ASNShipmentServiceImpl.Duplicate Records found with the given input..");
						}
						
						commonRespons.setStatus(String.format(ShipmentConstants.DUPLICATE_SHIPMENT_NUMBER_ORIGIN_DEST_ORG_ERROR_MESSAGE,
								asnAPIRequest.getShipmentNumber(), asnAPIRequest.getOrigin(),asnAPIRequest.getDestination(),organizationCode));
						return commonRespons;
					}
				} else {
					if(logger.isDebugEnabled()) {
						logger.debug("Entering into ASNShipmentServiceImpl.some of the input feilds are null");
					}
					
					commonRespons.setStatus(String.format(ShipmentConstants.INVALID_SHIPMENT_NUMBER_STATUS_ORIGIN_DEST_ERROR_MESSAGE,
							asnAPIRequest.getShipmentNumber(),asnAPIRequest.getStatus(),asnAPIRequest.getOrigin(),asnAPIRequest.getDestination()));
					return commonRespons;
				}
	
			} catch (Exception ex) {
				logger.error("Exception inside ASNShipmentServiceImpl.createShipment.Catch_Block:" + ex.getMessage());
				throw ex;
			}
	
		}

	@Override
	public List<E2EShipmentAPIResponse> getAllShipments(String organizationCode) {
		logger.info("Entering into ASNShipmentServiceImpl.getAllASNs");
		try {
			List<E2EShipmentAPIResponse> asnRes = new ArrayList<>();
			if(logger.isDebugEnabled()) {
				logger.debug("Entering into ASNShipmentServiceImpl.calling the shipment repository");
			}
			List<AdvanceShipmentNotice> asn = (List<AdvanceShipmentNotice>) shipmentRepository.findAllShipments(organizationCode, "N");
			if (asn != null&&!asn.isEmpty()) {
				if(logger.isDebugEnabled()) {
					logger.debug("Entering into ASNShipmentServiceImpl.Shipments are available");
				}
				asnRes = shipmentMapper.mapAsList(asn, E2EShipmentAPIResponse.class);
				return asnRes;
			} else {
				if(logger.isDebugEnabled()) {
					logger.debug("Entering into ASNShipmentServiceImpl.No shipment available");
				}
				E2EShipmentAPIResponse res = new E2EShipmentAPIResponse();
				res.setAsnNumber("");
				asnRes.add(res);
				asnRes.get(0).setStatus(ShipmentConstants.SHIPMENTS_DATA_AVAILABILITY_MESSAGE);
				return asnRes;
			}

		} catch (Exception ex) {
			logger.error("Exception inside ASNShipmentServiceImpl.getAllASNs.Catch_Block:" + ex.getMessage());
			throw ex;
		}

	}

	@Override
	public E2EShipmentAPIResponse getShipmentById(Long shipmentId, String organizationCode) {
		logger.info("Entering into ASNShipmentServiceImpl.getAsnByAsnId");
		try {
			E2EShipmentAPIResponse asnRes = new E2EShipmentAPIResponse();
			if(logger.isDebugEnabled()) {
				logger.debug("Entering into ASNShipmentServiceImpl.calling shipment repository");
			}
			AdvanceShipmentNotice asn = shipmentRepository.findForShipment(shipmentId, organizationCode, "N");
			if (asn != null) {
				if(logger.isDebugEnabled()) {
					logger.debug("Entering into ASNShipmentServiceImpl.shipment available and mapped to response");
				}
				asnRes = shipmentMapper.map(asn, E2EShipmentAPIResponse.class);
				if(logger.isDebugEnabled()) {
					logger.debug("Entering into ASNShipmentServiceImpl.mapping done");
				}
				return asnRes;
			} else {
				if(logger.isDebugEnabled()) {
					logger.debug("Entering into ASNShipmentServiceImpl.No shipment found");
				}
				asnRes.setStatus(ShipmentConstants.SHIPMENT_ID_DATA_AVAILABILITY_MESSAGE + shipmentId);
				return asnRes;
			}

		} catch (Exception ex) {
			logger.error("Exception inside ASNShipmentServiceImpl.getAsnByAsnId.Catch_Block:" + ex.getMessage());
			throw ex;
		}

	}

	@Override
	public CommonResponse updateShipment(E2EShipmentAPIRequest asnAPIRequest, Long shipmentId, String userName,
			String organizationCode) {
		try {
			CommonResponse commonRespons = new CommonResponse();
			if (asnAPIRequest.getShipmentNumber() != null && asnAPIRequest.getStatus() != null
					&& asnAPIRequest.getOrigin() != null && asnAPIRequest.getDestination() != null) {
				if(logger.isDebugEnabled()) {
					logger.debug("Entering into ASNShipmentServiceImpl.calling shipment repository");
				}
				AdvanceShipmentNotice asNotice = shipmentRepository.findForShipment(shipmentId, organizationCode, "N");

				if (asNotice != null) {
					if(logger.isDebugEnabled()) {
						logger.debug("Entering into ASNShipmentServiceImpl.shipment available");
					}
					
					Date curDate = new Date();
					String DateToStr;
					try {

						SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
						DateToStr = format.format(curDate);

					} catch (Exception ex) {
						throw ex;
					}
					Constants system = Constants.valueOf("SYSTEM");
					asnAPIRequest.setUpdatedDateTime(new Date());
					if (userName != null) {
						asnAPIRequest.setUpdatedBy(userName);
					} else {
						asnAPIRequest.setUpdatedBy(system.getConstants());
					}
					
					AdvanceShipmentNotice asnNumber = shipmentRepository.findForShipment(asnAPIRequest.getShipmentId(),
							organizationCode, "N");
					if (asnNumber == null) {
						commonRespons.setStatus(ShipmentConstants.INVALID_SHIPMENT_ID_ERROR_MESSAGE + asnAPIRequest.getShipmentId());
						return commonRespons;
					} else {
						AdvanceShipmentNotice asn = shipmentMapper.map(asnAPIRequest, AdvanceShipmentNotice.class);
						if(logger.isDebugEnabled()) {
							logger.debug("Entering into ASNShipmentServiceImpl.shipment available setting values with respect to the child (freight line items)");
						}
						
						Set<FreightLineItems> lineItems = asn.getFreightLineItems();
						Iterator<FreightLineItems> itSetASNLineItems = lineItems.iterator();
						while (itSetASNLineItems.hasNext()) {
							FreightLineItems itSetASNLineItemss = itSetASNLineItems.next();
							itSetASNLineItemss.setFreightLineItemsRef(asn);
							Long id = itSetASNLineItemss.getFreightLineId();
							if(logger.isDebugEnabled()) {
								logger.debug("Entering into ASNShipmentServiceImpl.calling freight line item repository");
							}
						
							FreightLineItems flineItems = fliRepository.findForFreight_Line_Items(id,
									asn.getShipmentId(), itSetASNLineItemss.getItemNumber(), "N");

							if (flineItems != null) {

								itSetASNLineItemss.setDeleteFlag("N");
								itSetASNLineItemss.setCreatedDateTime(new Date());
								itSetASNLineItemss.setUpdatedDateTime(new Date());
							} else {
								if(logger.isDebugEnabled()) {
									logger.debug("Entering into ASNShipmentServiceImpl.Invalid freight line item id");
								}
								
								commonRespons.setStatus(String.format(ShipmentConstants.INVALID_FREIGHT_LINE_ID_SHIPMENT_ID_LINE_NUMBER_ERROR_MESSAGE, id, asn.getShipmentId(),itSetASNLineItemss.getItemNumber()));
								return commonRespons;
							}
						}

						/*
						 * Set<ASNCrossDock> asnCrossDock = asn.getAnsCrossDock();
						 * Iterator<ASNCrossDock> itSetasnCrossDock = asnCrossDock.iterator(); while
						 * (itSetasnCrossDock.hasNext()) { ASNCrossDock itSetasnCrossDockk =
						 * itSetasnCrossDock.next(); itSetasnCrossDockk.setAsnCDref(asn); }
						 */
						asn.setDeleteFlag("N");
						asn.setCreatedDateTime(asNotice.getCreatedDateTime());
						asn.setCreatedBy(asNotice.getCreatedBy());
						asn.setOrganizationCode(organizationCode);
						if(logger.isDebugEnabled()) {
							logger.debug("Entering into ASNShipmentServiceImpl.updating shipment");
						}
						
						AdvanceShipmentNotice createAsn = shipmentRepository.save(asn);
						if (createAsn != null) {
							if(logger.isDebugEnabled()) {
								logger.debug("Entering into ASNShipmentServiceImpl.shipment updated");
							}
							
							commonRespons.setStatus(ShipmentConstants.ASN_UPDATE_SUCCESS_MESSAGE + createAsn.getShipmentId());
						} else {
							if(logger.isDebugEnabled()) {
								logger.debug("Entering into ASNShipmentServiceImpl.unable update shipment");
							}
							
							commonRespons.setStatus(ShipmentConstants.ASN_UPDATE_FAILURE_MESSAGE + asn.getShipmentId());
						}
						return commonRespons;
					}

				} else {
					if(logger.isDebugEnabled()) {
						logger.debug("Entering into ASNShipmentServiceImpl.invalid input passed ... please check");
					}
					
					commonRespons.setStatus(String.format(ShipmentConstants.INVALID_SHIPMENT_NUMBER_STATUS_ORIGIN_DEST_ORG_ERROR_MESSAGE,
							asnAPIRequest.getShipmentNumber(),asnAPIRequest.getStatus(),asnAPIRequest.getOrigin(),asnAPIRequest.getDestination(),organizationCode));
					return commonRespons;
				}
			} else {
				if(logger.isDebugEnabled()) {
					logger.debug("Entering into ASNShipmentServiceImpl.invalid input passed");
				}
				
				commonRespons.setStatus(String.format(ShipmentConstants.INVALID_SHIPMENT_NUMBER_ORIGIN_DEST_ERROR_MESSAGE,
						asnAPIRequest.getShipmentNumber(),asnAPIRequest.getOrigin(),asnAPIRequest.getDestination()));
				return commonRespons;
			}
		} catch (Exception ex) {
			logger.error("Exception inside ASNShipmentServiceImpl.updateASN.Catch_Block:" + ex.getMessage());
			throw ex;
		}

	}

	@Override
	public StatusResponse deleteShipmentById(String shipmentId, String organizationCode) {
		logger.info("Entering into ASNShipmentServiceImpl.deleteASNById");
		try {
			StatusResponse status = new StatusResponse();
			if (shipmentId != null && !shipmentId.isEmpty()) {
				String[] words = shipmentId.split(",");
				
				String msg = "";
				List<Long> myList = new ArrayList<Long>();
				List<Long> inValid = new ArrayList<Long>();
				List<Long> asnIds = new ArrayList<Long>();
				for (int i = 0; i < words.length; i++) {
					long num = Long.parseLong(words[i]);
					myList.add(num);
				}
				for (int j = 0; j < myList.size(); j++) {
					Long a = myList.get(j);
					if(logger.isDebugEnabled()) {
						logger.debug("Entering into ASNShipmentServiceImpl.calling shipment repository for test valid inputs or not");
					}
					
					AdvanceShipmentNotice f = shipmentRepository.findForShipment(a, organizationCode, "N");
					if (f != null) {
						asnIds.add(myList.get(j));
					} else {
						inValid.add(a);

					}

				}

				String delete = "Y";
				if (!asnIds.isEmpty()) {
					if (inValid.isEmpty()) {
						int dlt = shipmentRepository.setDelete_statusFore2e_shipment(delete, asnIds);
						if (dlt > 0) {
							if(logger.isDebugEnabled()) {
								logger.debug("Entering into ASNShipmentServiceImpl.deletion(soft) done");
							}
							
							status.setValid(asnIds);
							return status;
						} else {
							msg = "" + asnIds;
							status.setOther(msg);
							return status;
						}
					} else {
						if(logger.isDebugEnabled()) {
							logger.debug("Entering into ASNShipmentServiceImpl.some invalid input available");
						}
						
						status.setInValid(inValid);
						status.setValid(asnIds);
						return status;
					}

				} else {
					if(logger.isDebugEnabled()) {
						logger.debug("Entering into ASNShipmentServiceImpl.Invalid input passed");
					}
					
					status.setOther(ShipmentConstants.INVALID_INPUT_ERROR_MESSAGE);
					return status;
				}

			} else {
				if(logger.isDebugEnabled()) {
					logger.debug("Entering into ASNShipmentServiceImpl.invalid input passed");
				}
				
				status.setOther(ShipmentConstants.INVALID_INPUT_ERROR_MESSAGE);
				return status;
			}

		} catch (Exception ex) {
			logger.error("Exception inside ASNShipmentServiceImpl.deleteASNById.Catch_Block:" + ex.getMessage());
			throw ex;
		}

	}

	@Override
	public ExcelUploadResponse uploadShipments(String userName, String organization_Code, MultipartFile file)
			throws Exception {

		try {
			logger.info("Entering into ASNShipmentServiceImpl.uploadShipments");
			String organizationCode = organization_Code.toUpperCase();
			String columnErrors = "";
			ExcelUploadResponse finalOut = new ExcelUploadResponse();
			List<ExcelErrorRowsResponse> lisoferrorrows = new ArrayList<>();
			List<E2EShipmentAPIRequest> processedShipments = new ArrayList<>();
			List<E2EShipmentAPIRequest> lisofvalidrows = new ArrayList<>();
			Set<FreightLineItemsAPIRequest> listLiineItems = new HashSet<>();
			List<E2EShipmentAPIResponse> shipmentEventss = new ArrayList<>();
			String[] headerElements = ShipmentConstants.HEADER_ELEMENTS;//moved this to constants
			String fileName = file.getOriginalFilename();
			if (fileName.lastIndexOf('.') != -1 && fileName.lastIndexOf('.') != 0) {
				String fileType = fileName.substring(fileName.lastIndexOf('.') + 1);
				if (fileType.equalsIgnoreCase("csv")) {
					if(logger.isDebugEnabled()) {
						logger.debug("Entering into ASNShipmentServiceImpl.given file is valid..");
						logger.debug("ASNShipmentServiceImpl.uploadShipments:  input file is csv");						
					}
					
					File testFile = new File("test");
					FileUtils.writeByteArrayToFile(testFile, file.getBytes());
					List<String> lines = FileUtils.readLines(testFile);
					String fieldHeaders = lines.get(0);
					String[] columslist = fieldHeaders.split(",");
					
					boolean check = Arrays.equals(headerElements, columslist);
					if (headerElements.length < columslist.length) {
						finalOut.setError(ShipmentConstants.IRRELEVANT_COLUMN_IN_CSV_FOR_EVENTS_ERROR_MESSAGE);
						return finalOut;
					} else if (headerElements.length > columslist.length) {
						finalOut.setError(ShipmentConstants.COLUMN_MISSING_IN_CSV_FOR_EVENTS_ERROR_MESSAGE);
						return finalOut;
					} else if (!check) {
						for (int i = 0; i < headerElements.length; i++) {
							String actualHeader = headerElements[i];
							String csvHeader = columslist[i];
							if (!actualHeader.equalsIgnoreCase(csvHeader)) {
								columnErrors = columnErrors + csvHeader + ",";
							}
						}
						finalOut.setError(ShipmentConstants.INVALID_COLUMNS_ERROR_MESSAGE + columnErrors);
						return finalOut;
					}
					if(logger.isDebugEnabled()) {
						logger.debug(" ASNShipmentServiceImpl.uploadShipments :  Columns Validation Success ");
						logger.debug("ASNShipmentServiceImpl.uploadShipments :  Fetched Shipment Leg Data using legId ");
						
					}
					

					if (lines.size() > 1) {
						int rowindex = 0;
						if(logger.isDebugEnabled()) {
							logger.debug("ASNShipmentServiceImpl.uploadShipments : Data setting to Request from CSV/XLSX file");							
						}
						
						for (String rowdata : lines) {
							if (rowindex != 0) {

								int colIndex = 0;
								boolean skiprow = false;
								UploadE2EShipmentRequest asnRowReq = new UploadE2EShipmentRequest();
								UploadFreightLineItemsRequest lItemsRowReq = new UploadFreightLineItemsRequest();
								for (String row : rowdata.split(",")) {
									if (colIndex == 0) {
										if (rowdata.length() > 0) {
											asnRowReq.setShipmentNumber(row);
										} else if (row == null || row.trim().equals("") || row.trim().isEmpty()) {
											skiprow = true;
											break;
										}

									} else if (colIndex == 1) {
										asnRowReq.setShippingCompany(row);
									} else if (colIndex == 2) {
										asnRowReq.setBillToName(row);
										asnRowReq.setConsignee(row);
									} else if (colIndex == 3) {
										asnRowReq.setWayBillNumber(row);
									} else if (colIndex == 4) {
										asnRowReq.setAsnNumber(row);
									} else if (colIndex == 5) {
										asnRowReq.setUnitId(row);
									} else if (colIndex == 6) {
										asnRowReq.setTrackingNumber(row);
									} else if (colIndex == 7) {
										asnRowReq.setStatus(row);
									} else if (colIndex == 8) {
										asnRowReq.setExpectedDeliveryDate(row);
									} else if (colIndex == 9) {
										asnRowReq.setExpectdPickupDate(row);
									} else if (colIndex == 10) {
										asnRowReq.setOrigin(row);
									} else if (colIndex == 11) {
										asnRowReq.setOriginAddress1(row);
									} else if (colIndex == 12) {
										asnRowReq.setOriginAddress2(row);
									} else if (colIndex == 13) {
										asnRowReq.setOriginCity(row);
									} else if (colIndex == 14) {
										asnRowReq.setOriginState(row);
									} else if (colIndex == 15) {
										asnRowReq.setOriginZipCode(row);
									} else if (colIndex == 16) {
										asnRowReq.setOriginCountry(row);
									} else if (colIndex == 17) {
										asnRowReq.setDestination(row);
									} else if (colIndex == 18) {
										asnRowReq.setDestinationAddress1(row);
									} else if (colIndex == 19) {
										asnRowReq.setDestinationAddress2(row);
									} else if (colIndex == 20) {
										asnRowReq.setDestinationCity(row);
									} else if (colIndex == 21) {
										asnRowReq.setDestinationState(row);
									} else if (colIndex == 22) {
										asnRowReq.setDestinationZipCode(row);
									} else if (colIndex == 23) {
										asnRowReq.setDestinationCountry(row);
									} else if (colIndex == 24) {
										asnRowReq.setTotalWeight(row);
									} else if (colIndex == 25) {
										asnRowReq.setTransportationMethod(row);
									} else if (colIndex == 26) {
										asnRowReq.setShipmentNumberPart(row);
									} else if (colIndex == 27) {
										asnRowReq.setShipmentNumberTotal(row);
									} else if (colIndex == 28) {
										asnRowReq.setNumberOfUniqItems(row);
									} else if (colIndex == 39) {

									} else if (colIndex == 30) {
										lItemsRowReq.setPoNumber(row);
									} else if (colIndex == 31) {
										lItemsRowReq.setItemNumber(row);
									} else if (colIndex == 32) {
										lItemsRowReq.setItemDescription(row);
									} else if (colIndex == 33) {
										lItemsRowReq.setItemQuantity(row);
									} else if (colIndex == 34) {
										lItemsRowReq.setItemQuantityUom(row);
									} else if (colIndex == 35) {
										lItemsRowReq.setCharges(row);
									} else if (colIndex == 36) {
										lItemsRowReq.setContentDescription(row);
									} else if (colIndex == 37) {
										lItemsRowReq.setWeightQualifier(row);
									}
									colIndex++;
								}

								if (!skiprow && rowdata.split(",").length > 0) {
									if(logger.isDebugEnabled()) {
										logger.debug("ASNShipmentServiceImpl.uploadShipments : CSV data Validation Starts ");										
									}
									
									uploadASNDataValidator.validateShipmentRows(asnRowReq, lItemsRowReq, rowindex,
											lisoferrorrows, lisofvalidrows, listLiineItems, userName);
								}
							}
							rowindex++;
						}

						if (!lisofvalidrows.isEmpty()) {
							if(logger.isDebugEnabled()) {
								logger.debug("ASNShipmentServiceImpl.uploadShipments: Adding valid shipment event rows in Response ");								
							}
							

							uploadASNDataValidator.setFreightLineItems(lisofvalidrows, listLiineItems);

							List<AdvanceShipmentNotice> asn = shipmentMapper.mapAsList(lisofvalidrows, AdvanceShipmentNotice.class);
							for (int i = 0; i < asn.size(); i++) {
								AdvanceShipmentNotice duplicateFlag = shipmentRepository.findForCreateShipment(
										asn.get(i).getShipmentNumber(), asn.get(i).getOrigin(),
										asn.get(i).getDestination(), organizationCode, "N");
								if (duplicateFlag != null) {
									asn.get(i).setShipmentId(duplicateFlag.getShipmentId());
									asn.get(i).setCreatedBy(duplicateFlag.getCreatedBy());
									asn.get(i).setCreatedDateTime(duplicateFlag.getCreatedDateTime());
									asn.get(i).setStatus(updated.getConstants().toString());
								}
								asn.get(i).setDeleteFlag("N");
								asn.get(i).setOrganizationCode(organizationCode);
								Set<FreightLineItems> lineItems = asn.get(i).getFreightLineItems();
								if (lineItems != null) {
									Iterator<FreightLineItems> itSetASNLineItems = lineItems.iterator();
									while (itSetASNLineItems.hasNext()) {
										FreightLineItems itSetASNLineItemss = itSetASNLineItems.next();
										if (duplicateFlag == null) {
											String[] poNumber = itSetASNLineItemss.getPoNumber().split(",");
											itSetASNLineItemss.setPoNumber(poNumber[3]);
											itSetASNLineItemss.setFreightLineItemsRef(asn.get(i));
											itSetASNLineItemss.setDeleteFlag("N");
										} else {
											FreightLineItems flineItems = fliRepository
													.findByFreightLineItemsRefAndItemNumberAndDeleteFlag(asn.get(i),
															itSetASNLineItemss.getItemNumber(), "N");
											if (flineItems == null) {
												String[] poNumber = itSetASNLineItemss.getPoNumber().split(",");
												itSetASNLineItemss.setPoNumber(poNumber[3]);
												itSetASNLineItemss.setFreightLineItemsRef(asn.get(i));
												itSetASNLineItemss.setDeleteFlag("N");
											} else {
												itSetASNLineItemss.setFreightLineId(flineItems.getFreightLineId());
												String[] poNumber = itSetASNLineItemss.getPoNumber().split(",");
												itSetASNLineItemss.setPoNumber(poNumber[3]);
												itSetASNLineItemss.setFreightLineItemsRef(asn.get(i));
												itSetASNLineItemss.setCreatedBy(flineItems.getCreatedBy());
												itSetASNLineItemss.setCreatedDateTime(flineItems.getCreatedDateTime());
												itSetASNLineItemss.setDeleteFlag("N");
											}
										}

									}
								}

							}
							if(logger.isDebugEnabled()) {
								logger.debug("ASNShipmentServiceImpl.uploadShipments : Mapping done from List of Response to ShipmentEvent Model");								
							}

							List<AdvanceShipmentNotice> upload = (List<AdvanceShipmentNotice>) shipmentRepository.saveAll(asn);
							if (upload != null && !upload.isEmpty()) {
								if(logger.isDebugEnabled()) {
									logger.debug("ASNShipmentServiceImpl.uploadShipments: Data Uploaded Successfully into ShipmentEvent table ");
									
								}

								shipmentEventss = shipmentMapper.mapAsList(upload, E2EShipmentAPIResponse.class);

								for (int i = 0; i < shipmentEventss.size(); i++) {

									Set<FreightLineItems> lineItems = asn.get(i).getFreightLineItems();
									Iterator<FreightLineItems> itSetASNLineItems = lineItems.iterator();
									while (itSetASNLineItems.hasNext()) {
										FreightLineItems fLItems = itSetASNLineItems.next();
										List<PurchaseOrder> po = poRepository
												.findByPurchaseOrderNumberAndOrganizationCodeAndDeleteFlag(
														fLItems.getPoNumber(), organizationCode, active.getConstants());
										if (po != null && !po.isEmpty()) {
											List<POLineItems> lItems = liRepository
													.findBySkuNumberAndDeleteFlagAndPoLineItemsRefIn(
															fLItems.getItemNumber(),
															active.getConstants(), po);
											if (!lItems.isEmpty() && lItems != null) {
												for (int j = 0; j < lItems.size(); j++) {
													lItems.get(j).setLineItemStatus(readyToShip.getConstants());
													Set<POTransactions> poTransactionSet = new HashSet<>();
													POTransactions poTransactionItems = new POTransactions();
													poTransactionItems.setPoTransactionRef(lItems.get(j));
													poTransactionItems.setPoStatus(lItems.get(j).getLineItemStatus());
													poTransactionItems.setPoTransactionStatus(created.getConstants());
													poTransactionItems.setCreatedBy(userName);
													poTransactionItems
															.setCreatedOn(new Timestamp(System.currentTimeMillis()));
													poTransactionItems.setUpdatedBy(userName);
													poTransactionItems
															.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
													poTransactionItems.setOrganizationCode(organizationCode);
													poTransactionItems.setDeleteFlag(active.getConstants());
													poTransactionItems.setPoTransactionDate(
															new Timestamp(System.currentTimeMillis()));
													poTransactionSet.add(poTransactionItems);
													lItems.get(j).setPoTransactions(poTransactionSet);
												}
												List<POLineItems> savePOItems = (List<POLineItems>) liRepository
														.saveAll(lItems);
											}
										}

									}
								}
								finalOut.setUploadedData(shipmentEventss);
								if(logger.isDebugEnabled()) {
									logger.debug("ASNShipmentServiceImpl.uploadShipments: Response mapping from ShipmentEvents ");									
								}
								
								if (!shipmentEventss.isEmpty()) {
									String message = ShipmentConstants.NUMBER_OF_EVENTS_SUCCESS_MESSAGE
											+ shipmentEventss.size();
									finalOut.setMessage(message);
								}
							} else {
								finalOut.setError(ShipmentConstants.INSERT_RECORDS_ERROR_MESSAGE);
							}

						}
						if (!lisoferrorrows.isEmpty()) {
							int failedIndex = rowindex - 1;
							if(logger.isDebugEnabled()) {
								logger.debug("ASNShipmentServiceImpl.uploadShipments: Adding Invalid shipment event rows in Response ");								
							}
							
							finalOut.setError(String.format(ShipmentConstants.INSERT_RECORD_WITH_NUMBER_OF_RECORDS_ERROR_MESSAGE, failedIndex));
							finalOut.setConflictedData(lisoferrorrows);
						}
						if(logger.isDebugEnabled()) {
							logger.debug(" FileUploadServiceImpl.getUploadFile : Response returned");
						}
						
						return finalOut;
					} else {
						if(logger.isDebugEnabled()) {
							logger.debug("ShipmentServiceImpl.uploadShipment - Throwing Exeception as No Data in the file)");
						}
						
						finalOut.setError(ShipmentConstants.NO_DATA_FOUND_ERROR_MESSAGE);
						return finalOut;
					}

				} else {
					throw blumeUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(),
							ShipmentConstants.INVALID_FILE_TYPE_MESSAGE, ShipmentConstants.INVALID_FILE_TYPE_MESSAGE);
				}

			} else {
				throw blumeUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(),
						ShipmentConstants.INVALID_Path_TYPE_MESSAGE, ShipmentConstants.INVALID_Path_TYPE_MESSAGE);
			}

		} catch (Exception ex) {
			logger.error("Exception inside ASNShipmentServiceImpl.uploadShipments:" + ex.getMessage());
			throw ex;
		}

	}

	@Override
	public E2EShipmentAPIResponse getShipmentByShipmentNumberOrgCode(String shipmentNumber, String organizationCode) {
		logger.info("Entering into ASNShipmentServiceImpl.getShipmentByShipmentNumberOrgCode");
		try {
			E2EShipmentAPIResponse asnRes = new E2EShipmentAPIResponse();
			if(logger.isDebugEnabled()) {
				logger.debug("Entering into ASNShipmentServiceImpl.calling shipment repository");
			}
			
			AdvanceShipmentNotice asn = shipmentRepository.getShipmentByShipmentNumberOrgCode(shipmentNumber, organizationCode,
					"N");
			if (asn != null) {
				if(logger.isDebugEnabled()) {
					logger.debug("Entering into ASNShipmentServiceImpl.mapping to response");
				}
				
				asnRes = shipmentMapper.map(asn, E2EShipmentAPIResponse.class);
				if(logger.isDebugEnabled()) {
					logger.debug("Entering into ASNShipmentServiceImpl.mapping done");
				}
				
				List<ShipmentOrderLineResponse> solResponses = new ArrayList<>();

				List<IShipmentOrderLine> shipmentOrderLines = shipmentRepository
						.findAllShipmentOrderLines(asnRes.getShipmentId());
				for (IShipmentOrderLine sol : shipmentOrderLines) {
					solResponses.add(new ShipmentOrderLineResponse(sol.getPo_number(), sol.getSku_id(),
							sol.getShip_to_country(), sol.getShip_to_state(), sol.getPromised_date(),
							sol.getCommitted_quantity(), sol.getItem_quantity_uom(), sol.getShipmentCount()));
				}
				asnRes.setShipmentOrderLines(solResponses);

				return asnRes;
			} else {
				asnRes.setStatus(ShipmentConstants.SHIPMENT_NUMBER_NO_DATA_AVAILABILITY_MESSAGE + shipmentNumber);
				return asnRes;
			}

		} catch (Exception ex) {
			logger.error("Exception inside ASNShipmentServiceImpl.getShipmentByShipmentNumberOrgCode.Catch_Block:"
					+ ex.getMessage());
			throw ex;
		}
	}

	@Override
	public List<E2EShipmentAPIResponse> findShipmentByOrderNumberSku(String poNumber, String sku, String organizationCode) {
		logger.info("Entering into ASNShipmentServiceImpl.findShipmentByOrderNumberSku");
		try {
			List<E2EShipmentAPIResponse> shipRes = new ArrayList<>();
			List<AdvanceShipmentNotice> shipments = (List<AdvanceShipmentNotice>) shipmentRepository.findShipmentByOrderNumberSku(poNumber,sku, organizationCode);
			if (shipments != null) {
				shipRes = shipmentMapper.mapAsList(shipments, E2EShipmentAPIResponse.class);
			}
			return shipRes;
		} catch (Exception ex) {
			logger.error("Exception inside ASNShipmentServiceImpl.findShipmentByOrderNumberSku.Catch_Block:" + ex.getMessage());
			throw ex;
		}
	}

	@Override
	public E2EShipmentAPIResponse getShipmentByAsnNumberOrgCode(String asnNumber, String organizationCode) {
		logger.info("Entering into ASNShipmentServiceImpl.getShipmentByShipmentNumberOrgCode");
		try {
			E2EShipmentAPIResponse asnRes = new E2EShipmentAPIResponse();
			if(logger.isDebugEnabled()) {
				logger.debug("Entering into ASNShipmentServiceImpl.calling shipment repository");
			}
			
			AdvanceShipmentNotice asn = shipmentRepository.getShipmentByAsnNumberOrgCode(asnNumber, organizationCode,
					"N");
			if (asn != null) {
				if(logger.isDebugEnabled()) {
					logger.debug("Entering into ASNShipmentServiceImpl.mapping to response");
				}
				
				asnRes = shipmentMapper.map(asn, E2EShipmentAPIResponse.class);
				if(logger.isDebugEnabled()) {
					logger.debug("Entering into ASNShipmentServiceImpl.mapping done");
				}
				
				List<ShipmentOrderLineResponse> solResponses = new ArrayList<>();

				List<IShipmentOrderLine> shipmentOrderLines = shipmentRepository
						.findAllShipmentOrderLines(asnRes.getShipmentId());
				for (IShipmentOrderLine sol : shipmentOrderLines) {
					solResponses.add(new ShipmentOrderLineResponse(sol.getPo_number(), sol.getSku_id(),
							sol.getShip_to_country(), sol.getShip_to_state(), sol.getPromised_date(),
							sol.getCommitted_quantity(), sol.getItem_quantity_uom(), sol.getShipmentCount()));
				}
				asnRes.setShipmentOrderLines(solResponses);

				return asnRes;
			} else {
				asnRes.setStatus(ShipmentConstants.SHIPMENT_NUMBER_NO_DATA_AVAILABILITY_MESSAGE + asnNumber);
				return asnRes;
			}

		} catch (Exception ex) {
			logger.error("Exception inside ASNShipmentServiceImpl.getShipmentByShipmentNumberOrgCode.Catch_Block:"
					+ ex.getMessage());
			throw ex;
		}
	}
}
