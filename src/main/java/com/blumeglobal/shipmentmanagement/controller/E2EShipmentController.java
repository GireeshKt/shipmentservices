package com.blumeglobal.shipmentmanagement.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.blumeglobal.shipmentmanagement.request.E2EShipmentAPIRequest;
import com.blumeglobal.shipmentmanagement.response.CommonResponse;
import com.blumeglobal.shipmentmanagement.response.ExcelUploadResponse;
import com.blumeglobal.shipmentmanagement.response.E2EShipmentAPIResponse;
import com.blumeglobal.shipmentmanagement.response.StatusResponse;
import com.blumeglobal.shipmentmanagement.service.IE2EShipmentService;

import io.swagger.annotations.*;

//TODO: Auto-generated Javadoc
/**
 * The Controller .
 */
@RestController
@RequestMapping("/v1")
@Api(value = "E2E Shipment Service")
public class E2EShipmentController {

	private static final Logger logger = LogManager.getLogger(E2EShipmentController.class);

	@Autowired
	private IE2EShipmentService asnShipmentService;

	/**
	 * This Controller method is used to create a Shipment
	 * 
	 * @param E2EShipmentAPIRequest
	 * 
	 * @throws Exception
	 *             Handle the exceptions
	 */

	@RequestMapping(value = "/bv/e2eshipments", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create E2EShipment through API", produces = "application/json", consumes = "application/json",tags= {"E2E Shipment Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
			@ApiResponse(code = 500, message = "The GET call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input")})
	public ResponseEntity<?> createE2EShipment(@RequestBody E2EShipmentAPIRequest asnAPIRequest,
			@ApiParam(name = "userName", value = "Enter User Name", required = true) @RequestParam("userName") String userName,
			@ApiParam(name = "organizationCode", value = "Enter Organization Code", required = true) @RequestParam("organizationCode") String organizationCode)
			throws Exception {

		CommonResponse asnRes = asnShipmentService.createE2EShipment(asnAPIRequest, userName, organizationCode);
		return new ResponseEntity<>(asnRes, HttpStatus.OK);
	}

	/**
	 * This Controller method is used to update purchase order from API
	 * 
	 * @param E2EShipmentAPIRequest
	 * 
	 * @throws Exception
	 *             Handle the exceptions
	 */
	@RequestMapping(value = "/bv/e2eshipments/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update Shipment through API", produces = "application/json", consumes = "application/json",tags= {"E2E Shipment Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The PUT call is Successful"),
			@ApiResponse(code = 500, message = "The PUT call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> updateShipment(@RequestBody E2EShipmentAPIRequest asnAPIRequest,
			@ApiParam(name = "id", value = "Enter SHIPMENT ID", required = true) @PathVariable("id") Long id,
			@ApiParam(name = "userName", value = "Enter User Name", required = false) @RequestParam("userName") String userName,
			@ApiParam(name = "organizationCode", value = "Enter Organization Code", required = false) @RequestParam("organizationCode") String organizationCode)
			throws Exception {

		CommonResponse asnRes = asnShipmentService.updateShipment(asnAPIRequest,id,
				userName, organizationCode);
		return new ResponseEntity<>(asnRes, HttpStatus.OK);
	}

	/**
	 * This Controller method is used to retrieve list of purchase Order
	 * 
	 * @param E2EShipmentAPIResponse
	 * 
	 * @throws Exception
	 *             Handle the exceptions
	 */

	@RequestMapping(value = "/bv/e2eshipments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get All Shipments", produces = "application/json", consumes = "application/json",tags= {"E2E Shipment Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
			@ApiResponse(code = 500, message = "The GET call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getAllShipments(
			@ApiParam(name = "organizationCode", value = "Enter Organization Code", required = true) @RequestParam("organizationCode") String organizationCode)
			throws Exception {

		List<E2EShipmentAPIResponse> asn = asnShipmentService.getAllShipments(organizationCode);
		return new ResponseEntity<>(asn, HttpStatus.OK);
	}

	/**
	 * This Controller method is used to get shipment by shipment id
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

	@RequestMapping(value = "/bv/e2eshipments/shipmentid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Shipment By Id and Organization Code", produces = "application/json", consumes = "application/json",tags= {"E2E Shipment Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The GET call is Successful"),
			@ApiResponse(code = 500, message = "The GET call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getShipmentById(
			@ApiParam(name = "id", value = "Enter SHIPMENT ID", required = true) @PathVariable("id") Long id,
			@ApiParam(name = "organizationCode", value = "Enter Organization Code", required = true) @RequestParam("organizationCode") String organizationCode)
			throws Exception {

		E2EShipmentAPIResponse shipment = asnShipmentService.getShipmentById(id, organizationCode);
		return new ResponseEntity<>(shipment, HttpStatus.OK);

	}

	/**
	 * This Controller method is used to get shipment details by shipment number and organization code
	 * 
	 * @param shipmentNumber
	 * 
	 * @param organizationCode
	 * 
	 * @throws Exception
	 *             Handle the exceptions
	 */
	@RequestMapping(value = "/bv/e2eshipments/shipmentnumber/{shipmentnumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Shipment By shipment number and organization code", produces = "application/json", consumes = "application/json",tags= {"E2E Shipment Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getShipmentDetailByShipmentNumber(
			@ApiParam(name = "shipmentnumber", value = "Enter SHIPMENT NUMBER", required = true) @PathVariable("shipmentnumber") String shipmentnumber,
			@ApiParam(name = "organizationCode", value = "Enter Organization Code", required = true) @RequestParam("organizationCode") String organizationCode)
			throws Exception {

		E2EShipmentAPIResponse shipment = asnShipmentService.getShipmentByShipmentNumberOrgCode(shipmentnumber,
				organizationCode);
		return new ResponseEntity<>(shipment, HttpStatus.OK);

	}
	
	/**
	 * This Controller method is used to get shipment details by asn number and organization code
	 * 
	 * @param asnNumber
	 * 
	 * @param organizationCode
	 * 
	 * @throws Exception
	 *             Handle the exceptions
	 */
	@RequestMapping(value = "/bv/e2eshipments/{asnnumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Shipment By asn number and organization code", produces = "application/json", consumes = "application/json",tags= {"E2E Shipment Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> getShipmentDetailByAsnNumber(
			@ApiParam(name = "asnnumber", value = "Enter ASN NUMBER", required = true) @PathVariable("asnnumber") String asnnumber,
			@ApiParam(name = "organizationCode", value = "Enter Organization Code", required = true) @RequestParam("organizationCode") String organizationCode)
			throws Exception {

		E2EShipmentAPIResponse shipment = asnShipmentService.getShipmentByAsnNumberOrgCode(asnnumber,
				organizationCode);
		return new ResponseEntity<>(shipment, HttpStatus.OK);

	}

	/**
	 * This Controller method is used to retrieve list of Shipment by Order Number and SKU 
	 * @throws Exception Handle the exceptions
	 */
	@RequestMapping(value = "/bv/e2eshipments/{ordernumber}/{skuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Shipments by PO Number, SKU and organization code", produces = "application/json", consumes = "application/json",tags= {"E2E Shipment Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POS call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> findShipmentByOrderNumberSku(
			@ApiParam(name = "ordernumber", value = "Enter Purchase Order Number", required = true) @PathVariable("ordernumber") String ordernumber,
			@ApiParam(name = "skuid", value = "Enter SKU ID", required = true) @PathVariable("skuid") String skuid,
			@ApiParam(name = "organizationCode", value = "Enter Organization Code", required = true) @RequestParam("organizationCode") String organizationCode) throws Exception {
		List<E2EShipmentAPIResponse> asn = asnShipmentService.findShipmentByOrderNumberSku(ordernumber, skuid, organizationCode);
		return new ResponseEntity<>(asn, HttpStatus.OK);
	}
	
	/**
	 * This Controller method is used to delete shipment from API
	 * 
	 * @param list of shipment Ids
	 * 
	 * @throws Exception
	 *             Handle the exceptions
	 */
	@RequestMapping(value = "/bv/e2eshipments/delete/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete Shipment By Id through API", produces = "application/json", consumes = "application/json",tags= {"E2E Shipment Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The PUT call is Successful"),
			@ApiResponse(code = 500, message = "The PUT call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found"),
			@ApiResponse(code = 400, message = "Invalid input") })
	public ResponseEntity<?> deleteShipmentById(@PathVariable("id") String id,
			@ApiParam(name = "organizationCode", value = "Enter Organization Code", required = false) @RequestParam("organizationCode") String organizationCode)
			throws Exception {

		StatusResponse status = asnShipmentService.deleteShipmentById(id, organizationCode);
		return new ResponseEntity<StatusResponse>(status, HttpStatus.OK);
	}
	/**
	 * This Controller method is used to upload shipment from API
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
	@RequestMapping(value = "/bv/e2eshipments/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Make a POST request to upload the file", produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,tags= {"E2E Shipment Service"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found") })
	public ResponseEntity<?> uploadShipments(
			@ApiParam(name = "file", value = "Select the file to Upload", required = true) @RequestPart("file") MultipartFile file,
			@ApiParam(name = "organizationCode", value = "Enter Organization Code", required = true) @RequestParam("organizationCode") String organizationCode,
			@ApiParam(name = "userName", value = "Enter User Name", required = true) @RequestParam("userName") String userName)
			throws Exception {
		logger.info("Entering into FileUploadController.uploadFile");
		ExcelUploadResponse upload = asnShipmentService.uploadShipments(userName, organizationCode, file);
		logger.debug("Inside FileUploadController.uploadFile - Uploading File in E2E_Shipments btable");
		return new ResponseEntity<>(upload, HttpStatus.OK);

	}
}
