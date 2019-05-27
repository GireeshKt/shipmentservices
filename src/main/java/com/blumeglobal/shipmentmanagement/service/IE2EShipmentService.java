package com.blumeglobal.shipmentmanagement.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blumeglobal.shipmentmanagement.request.E2EShipmentAPIRequest;
import com.blumeglobal.shipmentmanagement.response.CommonResponse;
import com.blumeglobal.shipmentmanagement.response.ExcelUploadResponse;
import com.blumeglobal.shipmentmanagement.response.E2EShipmentAPIResponse;
import com.blumeglobal.shipmentmanagement.response.StatusResponse;


public interface IE2EShipmentService {
	
	/**
	 * This Service method is used to create a Shipment
	 * 
	 * @param E2EShipmentAPIRequest
	 * 
	 * @throws Exception
	 *             Handle the exceptions
	 */
	public CommonResponse createE2EShipment(E2EShipmentAPIRequest asnAPIRequest, String userName, String organizationCode);
	
	/**
	 * This Service method is used to retrieve list of purchase Order
	 * 
	 * @param E2EShipmentAPIResponse
	 * 
	 * @throws Exception
	 *             Handle the exceptions
	 */

	public List<E2EShipmentAPIResponse> getAllShipments(String organizationCode);
	
	/**
	 * This Service method is used to get shipment by shipment id
	 * 
	 * @param shipmentId
	 * 
	 * @param organizationCode
	 * 
	 * @response E2EShipmentAPIResponse
	 * 
	 * @throws Exception
	 *             Handle the exceptions
	 */
	public E2EShipmentAPIResponse getShipmentById(Long shipmentId, String organizationCode);
	/**
	 * This Service method is used to update purchase order from API
	 * 
	 * @param E2EShipmentAPIRequest
	 * 
	 * @throws Exception
	 *             Handle the exceptions
	 */
	public CommonResponse updateShipment(E2EShipmentAPIRequest asnAPIRequest, Long shipmentId, String userName, String organizationCode);
	/**
	 * This Service method is used to delete shipment from API
	 * 
	 * @param list of shipment Ids
	 * 
	 * @throws Exception
	 *             Handle the exceptions
	 */
	public StatusResponse deleteShipmentById(String shipmentId, String organizationCode);
	/**
	 * This Service method is used to upload shipment from API
	 * 
	 * @param file
	 * 
	 * @param organizationCode
	 * 
	 * @param userName
	 * 
	 * @throws Exception
	 *             Handle the exceptions
	 */
	public ExcelUploadResponse uploadShipments(String userName, String organizationCode, MultipartFile file) throws Exception;
	
	/**
	 * This Service method is used to get shipment details by shipment number and organization code
	 * 
	 * @param shipmentNumber
	 * 
	 * @param organizationCode
	 * 
	 * @throws Exception
	 *             Handle the exceptions
	 */
	public E2EShipmentAPIResponse getShipmentByShipmentNumberOrgCode(String shipmentNumber, String organizationCode);
	
	/**
	 * This service method is used to get shipment for given purchase order number, SKU and organization code
	 * @prarm poNumber
	 * @param sku
	 * @param organizationCode
	 * 
	 */
	public List<E2EShipmentAPIResponse>  findShipmentByOrderNumberSku(String poNumber, String sku, String organizationCode);
	
	/**
	 * This Service method is used to get shipment details by asn number and organization code
	 * 
	 * @param asnNumber
	 * 
	 * @param organizationCode
	 * 
	 * @throws Exception
	 *             Handle the exceptions
	 */
	public E2EShipmentAPIResponse getShipmentByAsnNumberOrgCode(String asnNumber, String organizationCode);
}
