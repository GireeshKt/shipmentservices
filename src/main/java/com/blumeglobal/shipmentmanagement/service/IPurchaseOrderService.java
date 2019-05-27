package com.blumeglobal.shipmentmanagement.service;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.blumeglobal.shipmentmanagement.request.E2EShipmentPOStatusRequest;
import com.blumeglobal.shipmentmanagement.request.POAPIRequest;
import com.blumeglobal.shipmentmanagement.response.CommonResponse;
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


public interface IPurchaseOrderService {
	
    public POAPICreateUpdateResponse updatePurchaseOrder( POAPIRequest poApiRequest, Long l,String organizationCode,String userName);
    public POAPIReadOrderLineListResponse getAllPOs(String organizationCode);
	public POAPIReadOrderListResponse getPurchaseOrderById(Long poId,String organizationCode);
	public POAPICreateUpdateResponse createPurchaseOrder(POAPIRequest poApiRequest,String organizationCode,String userName) throws Exception;
	public POAPIDeleteStatusResponse deleteFlagPurchaseOrder(String poId, String organizationCode);
	public ExcelPurchaseOrderUploadResponse uploadPurchaseOrder(MultipartFile file, String organizationCode, String userName) throws Exception;
	public POAPIReadLineShipmentResponse getPurchaseOrderByPoNum(String purchaseOrderNumber, String organizationCode);
	public POAPIReadTransactionResponse getPOTransactionStatus(Long poItemId, String organizationCode);
	public POAPIReadShipmentLogOrderResponse getShipmentLogOrderDetailsByPoNum(String purchaseOrderNumber, String skuNumber, String organizationCode);
	public POAPIReadShipmentLogDetailListResponse getShipmentLogDetailsByPoNum(String purchaseOrderNumber, String skuNumber, String organizationCode);
	public CommonResponse updatePurchaseOrderStatus(E2EShipmentPOStatusRequest statusRequest, String organizationCode,
			String userName);
	public List<POAPIResponse> getPOByShipment(String shipmentNumber, String asnNumber, String organizationCode);
	/**
	 * @param po
	 * @param poref
	 * @param seller
	 * @param buyer
	 * @return
	 */
	public ResponseEntity<String> invokeblockchain(String po,String poref,String seller,String buyer);
	/**
	 * @param shipmentid
	 * @return
	 */
	public ResponseEntity<String> invokeblockchainForShipment(String shipmentid);
}
