package com.blumeglobal.shipmentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
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
import com.blumeglobal.shipmentmanagement.response.ShipmentWOResponse;
import com.blumeglobal.shipmentmanagement.service.IPurchaseOrderService;


import io.swagger.annotations.*;

@RestController
@RequestMapping("/v1")
@Api(value = "Purchase Order Service")
public class PurchaseOrderController {

	@Autowired
	private IPurchaseOrderService purchaseOrderService;


	/**
	 * This Controller method is used to create purchase order from API
	 * 
	 * @param poApiRequest This is used to pass purchase order form data
	 * 
	 * @return poApiRequest To return the poApiRequest after save
	 * @throws Exception Handle the exceptions
	 */
	@RequestMapping(value = "/bv/purchaseorders", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create Purchase Order through API", produces = "application/json", consumes = "application/json",tags = {"Purchase Order Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> createPurchaseOrder(@RequestBody POAPIRequest poApiRequest,
			@ApiParam(name = "organizationCode", value = "Enter Organization Code", required = true) @RequestParam("organizationCode") String organizationCode,
			@ApiParam(name = "userName", value = "Enter User Name", required = true) @RequestParam("userName") String userName) throws Exception {

		POAPICreateUpdateResponse responsePO = purchaseOrderService.createPurchaseOrder(poApiRequest,organizationCode,userName);
		return new ResponseEntity<>(responsePO, HttpStatus.OK);
	}

	/**
	 * This Controller method is used to update purchase order from API
	 * @throws Exception Handle the exceptions
	 */
	@RequestMapping(value = "/bv/purchaseorders/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update Purchase Order through API", produces = "application/json", consumes = "application/json",tags = {"Purchase Order Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The PUT call is Successful"),
			@ApiResponse(code = 500, message = "The PUT call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> updatePurchaseOrder(@RequestBody POAPIRequest poApiRequest,
			@ApiParam(name = "id", value = "Enter PO ID", required = true) @PathVariable("id") Long id,
			@ApiParam(name = "organizationCode", value = "Enter Organization Code", required = true) @RequestParam("organizationCode") String organizationCode,
			@ApiParam(name = "userName", value = "Enter User Name", required = true) @RequestParam("userName") String userName) throws Exception {

		POAPICreateUpdateResponse poRes = purchaseOrderService.updatePurchaseOrder(poApiRequest,id,organizationCode,userName);
		return new ResponseEntity<>(poRes,HttpStatus.OK);
	}

	/**
	 * This Controller method is used to retrieve list of purchase Order 
	 * @throws Exception Handle the exceptions
	 */

	@RequestMapping(value = "/bv/purchaseorders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get list of POs", produces = "application/json", consumes = "application/json",tags = {"Purchase Order Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getPOs(
			@ApiParam(name = "organizationCode", value = "Enter Organization Code", required = true) @RequestParam("organizationCode") String organizationCode) throws Exception {

		POAPIReadOrderLineListResponse po = purchaseOrderService.getAllPOs(organizationCode);
		return new ResponseEntity<>(po, HttpStatus.OK);
	}

	/**
	 * This Controller method is used to retrieve list of purchase Order based on ID
	 * @throws Exception Handle the exceptions
	 */

	@RequestMapping(value = "/bv/purchaseorders/poid/{poid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Purchase Order By Id", produces = "application/json", consumes = "application/json",tags = {"Purchase Order Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
			@ApiResponse(code = 500, message = "The GET call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getPurchaseOrderByPOId(
			@ApiParam(name = "poid", value = "Enter Purchase Order ID", required = true) @PathVariable("poid") Long poid,
			@ApiParam(name = "organizationCode", value = "Enter Organization Code", required = true) @RequestParam("organizationCode") String organizationCode)
					throws Exception {

		POAPIReadOrderListResponse shipment = purchaseOrderService.getPurchaseOrderById(poid,organizationCode);
		return new ResponseEntity<>(shipment, HttpStatus.OK);
	}

	/**
	 * This Controller method is used to retrieve list of purchase Order based on Order Number
	 * @throws Exception Handle the exceptions
	 */

	@RequestMapping(value = "/bv/purchaseorders/ponumber/{ponumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Purchase Order By Purchase Order Number", produces = "application/json", consumes = "application/json",tags = {"Purchase Order Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getPurchaseOrderByPONumber(
			@ApiParam(name = "ponumber", value = "Enter PO number", required = true) @PathVariable("ponumber") String ponumber,
			@ApiParam(name = "organizationCode", value = "Enter Organization Code", required = true) @RequestParam("organizationCode") String organizationCode)
					throws Exception {

		POAPIReadLineShipmentResponse po = purchaseOrderService.getPurchaseOrderByPoNum(ponumber, organizationCode);
		return new ResponseEntity<>(po, HttpStatus.OK);
	}	

	/**
	 * This Controller method is used to retrieve list of PO Transactions based on Order ID
	 * @throws Exception Handle the exceptions
	 */

	@RequestMapping(value = "/bv/purchaseorders/potransactions/poitemid/{poItemId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get PO Transaction Status By Purchase Order Line Item ID", produces = "application/json", consumes = "application/json",tags = {"Purchase Order Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getPOTransactionStatus(
			@ApiParam(name = "poItemId", value = "Enter Purchase Order Line Item ID", required = true) @PathVariable("poItemId") Long poItemId,
			@ApiParam(name = "organizationCode", value = "Enter Organization Code", required = true) @RequestParam("organizationCode") String organizationCode)
					throws Exception {

		POAPIReadTransactionResponse poResponse = purchaseOrderService.getPOTransactionStatus(poItemId, organizationCode);
		return new ResponseEntity<>(poResponse, HttpStatus.OK);
	}	


	/**
	 * This Controller method is used to Delete purchase Order Based on ID
	 * @throws Exception Handle the exceptions
	 */

	@RequestMapping(value = "/bv/purchaseorders/delete/{poId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete Purchase Order By Id through API", produces = "application/json", consumes = "application/json",tags = {"Purchase Order Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The PUT call is Successful"),
			@ApiResponse(code = 500, message = "The PUT call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> deleteFlagPurchaseOrder(
			@ApiParam(name = "poId", value = "Enter Purchase Order ID", required = true)  @PathVariable("poId") String poId,
			@ApiParam(name = "organizationCode", value = "Enter Organization Code", required = true) @RequestParam("organizationCode") String organizationCode
			) throws Exception {

		POAPIDeleteStatusResponse dlt= purchaseOrderService.deleteFlagPurchaseOrder(poId,organizationCode);
		return new ResponseEntity<POAPIDeleteStatusResponse>(dlt,HttpStatus.OK);
	}

	/**
	 * This Controller method is used to upload purchase order through EXCEL 
	 * @param file This is used to pass Excel file for purchase order
	 * @return ExcelPurchaseOrderUploadResponse To return consolidate result after uploaded
	 * @throws Exception Handle the exceptions
	 */
	
	@RequestMapping(value = "/bv/purchaseorders/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Make a POST request to upload the file", produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,tags = {"Purchase Order Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found") })
	public ResponseEntity<?> uploadPurchaseOrder(
			@ApiParam(name = "file", value = "Select the file to Upload", required = true) @RequestPart("file") MultipartFile file,
			@ApiParam(name = "organizationCode", value = "Enter Organization Code", required = true) @RequestParam("organizationCode") String organizationCode,
			@ApiParam(name = "userName", value = "Enter User Name", required = true) @RequestParam("userName") String userName)
					throws Exception {
		
		ExcelPurchaseOrderUploadResponse upload = purchaseOrderService.uploadPurchaseOrder(file,organizationCode, userName);
		return new ResponseEntity<>(upload, HttpStatus.OK);
	}

	/**
	 * This Controller method is used to retrieve PO line items details based on Order Number and sku number
	 * @throws Exception Handle the exceptions
	 */
	
	@RequestMapping(value = "/bv/purchaseorders/potransactions/ponumber/{ponumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Shipment log order details by purchase order number and sku id", produces = "application/json", consumes = "application/json",tags = {"Purchase Order Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getShipmentLogOrderDetailsByPoNum(
			@ApiParam(name = "ponumber", value = "Enter Purchase Order Number", required = true) @PathVariable("ponumber") String ponumber,
			@ApiParam(name = "skuId", value = "Enter sku Number", required = true) @RequestParam("skuId") String skuNumber,
			@ApiParam(name = "organizationCode", value = "Enter Organization Code", required = true) @RequestParam("organizationCode") String organizationCode)
					throws Exception {
		
		POAPIReadShipmentLogOrderResponse po = purchaseOrderService.getShipmentLogOrderDetailsByPoNum(ponumber, skuNumber, organizationCode);
		return new ResponseEntity<>(po, HttpStatus.OK);
	}	

	/**
	 * This Controller method is used to retrieve list of shipment log details on Order Number and Order Number and sku number
	 * @throws Exception Handle the exceptions
	 */
	
	@RequestMapping(value = "/bv/purchaseorders/potransactions/{purchaseOrderNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Shipment log details by purchase order number and sku id", produces = "application/json", consumes = "application/json",tags = {"Purchase Order Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getShipmentLogDetailsByPoNum(
			@ApiParam(name = "purchaseOrderNumber", value = "Enter Purchase Order Number", required = true) @PathVariable("purchaseOrderNumber") String purchaseOrderNumber,
			@ApiParam(name = "skuId", value = "Enter sku Number", required = true) @RequestParam("skuId") String skuNumber,
			@ApiParam(name = "organizationCode", value = "Enter Organization Code", required = true) @RequestParam("organizationCode") String organizationCode)
					throws Exception {
		
		POAPIReadShipmentLogDetailListResponse shipmentLogs = purchaseOrderService.getShipmentLogDetailsByPoNum(purchaseOrderNumber, skuNumber, organizationCode);
		return new ResponseEntity<>(shipmentLogs, HttpStatus.OK);
	}
	
	/**
	 * This Controller method is used to update purchase order from API
	 * @throws Exception Handle the exceptions
	 */
	
	@RequestMapping(value = "/bv/purchaseorder/polineitems", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update Line Items Status through API", produces = "application/json", consumes = "application/json",tags = {"Purchase Order Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The PUT call is Successful"),
			@ApiResponse(code = 500, message = "The PUT call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> updateLineItemsStatus(@RequestBody E2EShipmentPOStatusRequest statusRequest,
			@ApiParam(name = "organizationCode", value = "Enter Organization Code", required = true) @RequestParam("organizationCode") String organizationCode,
			@ApiParam(name = "userName", value = "Enter User Name", required = true) @RequestParam("userName") String userName
			) throws Exception {

		CommonResponse poRes = purchaseOrderService.updatePurchaseOrderStatus(statusRequest,organizationCode,userName);
		return new ResponseEntity<>(poRes,HttpStatus.OK);
	}
	

	/**
	 * This Controller method is used to update purchase order from API
	 * @throws Exception Handle the exceptions
	 */
	
	@RequestMapping(value = "/bv/purchaseorders/shipmentref", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get PO by Shipment Number through API", produces = "application/json", consumes = "application/json",tags = {"Purchase Order Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The PUT call is Successful"),
			@ApiResponse(code = 500, message = "The PUT call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getPOByShipment(
			@ApiParam(name = "shipmentnumber", value = "Enter Shipment Number", required = true) @RequestParam("shipmentnumber") String shipmentnumber,
			@ApiParam(name = "asnNumber", value = "Enter Asn Number", required = true) @RequestParam("asnNumber") String asnNumber,
			@ApiParam(name = "organizationCode", value = "Enter Organization Code", required = true) @RequestParam("organizationCode") String organizationCode
			) throws Exception {

		List<POAPIResponse> poRes = purchaseOrderService.getPOByShipment(shipmentnumber,asnNumber,organizationCode);
		return new ResponseEntity<>(poRes,HttpStatus.OK);
	}

	@RequestMapping(value = "/bv/purchaseorders/blockchain", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get purchaseordershipment", produces = "application/json", consumes = "application/json",tags = {"Purchase Order Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
			@ApiResponse(code = 500, message = "The GET call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getPOByShipmentBlockchain(
			@ApiParam(name = "po", value = "Enter PurchaseId", required = true) @RequestParam("po") String po,
			@ApiParam(name = "poref", value = "Enter Purchase reference", required = true) @RequestParam("poref") String poref,
			@ApiParam(name = "seller", value = "Enter Seller Id", required = true) @RequestParam("seller") String seller,
			@ApiParam(name = "buyer", value = "Enter buyer Id", required = true) @RequestParam("buyer") String buyer
			) throws Exception {
		 
		ResponseEntity<String> response =purchaseOrderService.invokeblockchain(po, poref, seller, buyer);
		return response;
	}
	
	@RequestMapping(value = "/bv/shipment/{shipmentId}/history", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get shipment History ", produces = "application/json", consumes = "application/json",tags = {"Purchase Order Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
			@ApiResponse(code = 500, message = "The GET call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getshipmentFromBlockchain(
			@ApiParam(name = "shipmentId", value = "Enter shipmentId", required = true) @PathVariable("shipmentId") String shipmentId
			) throws Exception {
		 
		ResponseEntity<String> response =purchaseOrderService.invokeblockchainForShipment(shipmentId);
		return response;
	}
	
}