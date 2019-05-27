package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.blumeglobal.shipmentmanagement.exceptionlist.response.ShipmentQuantityExceptionResponse;
import com.blumeglobal.shipmentmanagement.model.IPOLineShipItems;
import com.blumeglobal.shipmentmanagement.model.IPOListAllItems;
import com.blumeglobal.shipmentmanagement.model.IPurchaseOrderItems;
import com.blumeglobal.shipmentmanagement.model.IShipmentLogDetails;
import com.blumeglobal.shipmentmanagement.model.IShipmentLogOrderDetails;
import com.blumeglobal.shipmentmanagement.model.POLineItems;
import com.blumeglobal.shipmentmanagement.model.PurchaseOrder;
import com.blumeglobal.shipmentmanagement.request.POAPIRequest;



public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Long>{

	PurchaseOrder findByPurchaseOrderId(Long poId);
	
	@Query(value="select * from purchase_order po where po.purchase_order_id = :poId and po.organization_code = :organizationCode and po.delete_flag = :flag",nativeQuery = true)
	PurchaseOrder findForpurchaseOrder(@Param("poId") Long poId,@Param("organizationCode") String organizationCode,@Param("flag") String flag);
	
	@Query(value="select poshipitems.purchase_order_number as purchaseOrderNumber ,poshipitems.po_reference_id as poReferenceId,poshipitems.po_request_date as poRequestDate ,\r\n" + 
			"poshipitems.po_promise_date as poPromiseDate,poshipitems.issue_date as poIssueDate,\r\n" + 
			"poshipitems.created_on as createdOn,poshipitems.vendor_name as vendorName,poshipitems.vendor_address as vendorAddress,\r\n" + 
			"poshipitems.buyer_name as buyerName,poshipitems.billing_address as billingAddress from(\r\n" + 
			"select po.purchase_order_id,po.po_reference_id,po.po_promise_date,po.po_date as issue_date,\r\n" + 
			"(select min(pol.due_date) from po_line_items pol where pol.purchase_order_id=po.purchase_order_id)  as po_request_date,\r\n" + 
			"po.created_on,po.vendor_name,po.vendor_address,po.buyer_name,po.billing_address,po.organization_code,po.purchase_order_number  from purchase_order po \r\n" + 
			"where po.delete_flag = :flag order by po.updated_on desc)poshipitems\r\n" + 
			"where poshipitems.purchase_order_number= :purchaseOrderNumber and poshipitems.organization_code= :organizationCode", nativeQuery = true)
	List<IPurchaseOrderItems> findOrderByPoNum(@Param("purchaseOrderNumber") String purchaseOrderNumber,@Param("organizationCode") String organizationCode,@Param("flag") String flag);

	@Query(value="select poshipitems.sku_id as skuId ,poshipitems.shipment_number as shipmentNumber,poshipitems.unit_id as unitId,poshipitems.line_item_status as lineItemStatus, poshipitems.sku_description as skuDescription,\r\n" + 
			"poshipitems.ship_to_state as shipToState,poshipitems.price as price, poshipitems.quantity as quantity,poshipitems.committed_quantity as committedQuantity,\r\n" + 
			"poshipitems.request_date as requestDate, poshipitems.promised_date as promisedDate,poshipitems.unit_of_measurement as unitOfMeasure,\r\n" + 
			"poshipitems.ship_to_city as destination, poshipitems.ship_to_location_code as shipToLocationCode,poshipitems.ship_to_company as shipToCompany,\r\n" + 
			"poshipitems.ship_to_address as shipToAddress, poshipitems.ship_to_zip as shipToZip,poshipitems.ship_to_country as shipToCountry \r\n" + 
			"from( \r\n" + 
			"select ( \r\n" + 
			"    select asnpo.shipment_number  from (\r\n" + 
			"        select asline.shipment_id,asline.po_number,asline.item_number, asnn.shipment_number ,asnn.unit_id from ( \r\n" + 
			"            select asn.shipment_id,asn.po_number,asn.item_number from freight_line_items asn\r\n" + 
			"        where asn.delete_flag = :deleteflag order by asn.updated_date_time desc )asline  \r\n" + 
			"        left outer join advance_shipment_notice asnn  on asline.shipment_id = asnn.shipment_id)\r\n" + 
			"        asnpo\r\n" + 
			"        where asnpo.po_number = polines.purchase_order_number  and asnpo.item_number = polines.sku_id and rownum = 1 )as shipment_number,\r\n" + 
			"        (select asnpo.unit_id  from (\r\n" + 
			"        select asline.shipment_id,asline.po_number,asline.item_number, asnn.shipment_number ,asnn.unit_id from ( \r\n" + 
			"            select asn.shipment_id,asn.po_number,asn.item_number from freight_line_items asn\r\n" + 
			"        where asn.delete_flag = :deleteflag order by asn.updated_date_time desc )asline  \r\n" + 
			"        left outer join advance_shipment_notice asnn  on asline.shipment_id = asnn.shipment_id)\r\n" + 
			"        asnpo\r\n" + 
			"        where asnpo.po_number = polines.purchase_order_number  and asnpo.item_number = polines.sku_id and rownum = 1 ) as unit_id,\r\n" + 
			"        \r\n" + 
			"\r\n" + 
			"polines.* from (\r\n" + 
			"select poitems.*,polineitems.sku_id,polineitems.line_item_status ,polineitems.ship_to_state, polineitems.sku_description,polineitems.quantity,polineitems.committed_quantity,polineitems.due_date as request_date,polineitems.price, \r\n" + 
			"polineitems.promised_date,polineitems.unit_of_measurement,polineitems.ship_to_city,polineitems.ship_to_location_code, polineitems.ship_to_company,polineitems.ship_to_address,polineitems.ship_to_zip,polineitems.ship_to_country from ( \r\n" + 
			"select po.purchase_order_id,po.organization_code,po.purchase_order_number  \r\n" + 
			"from purchase_order po  where po.delete_flag = :flag order by po.updated_on desc)poitems\r\n" + 
			"left outer join po_line_items polineitems on poitems.purchase_order_id = polineitems.purchase_order_id where polineitems.delete_flag = :flag order by polineitems.updated_on desc )polines \r\n" + 
			")poshipitems\r\n" + 
			"where poshipitems.purchase_order_number= :purchaseOrderNumber and poshipitems.organization_code= :organizationCode", nativeQuery = true)
	List<IPOLineShipItems> findPoLineByPoNum(@Param("purchaseOrderNumber") String purchaseOrderNumber,@Param("organizationCode") String organizationCode,@Param("flag") String flag,@Param("deleteflag") String deleteflag);
	
	/*
	 * @Query(
	 * value="select * from (select * from purchase_order po where po.purchase_order_number = :purchaseOrderNumber and po.organization_code = :organizationCode and po.delete_flag = :flag order by po.updated_on desc) where rownum = 1"
	 * ,nativeQuery = true) PurchaseOrder
	 * validateByPoNum(@Param("purchaseOrderNumber") String
	 * purchaseOrderNumber,@Param("organizationCode") String
	 * organizationCode,@Param("flag") String flag);
	 */
	
	
	@Query(value="select * from purchase_order po where po.purchase_order_number = :purchaseOrderNumber and po.organization_code = :organizationCode and po.delete_flag = :flag order by po.updated_on limit 1",nativeQuery = true)
	PurchaseOrder validateByPoNum(@Param("purchaseOrderNumber") String purchaseOrderNumber,@Param("organizationCode") String organizationCode,@Param("flag") String flag);

	
	/*
	 * @Query(
	 * value="select * from (select * from purchase_order po where po.organization_code = :organizationCode and po.delete_flag = :flag order by po.updated_on desc) where rownum = 1"
	 * ,nativeQuery = true) PurchaseOrder
	 * validateByOrganization(@Param("organizationCode") String
	 * organizationCode,@Param("flag") String flag);
	 */
	
	@Query(value="select * from purchase_order po where po.organization_code = :organizationCode and po.delete_flag = :flag order by po.updated_on limit 1",nativeQuery = true)
	PurchaseOrder validateByOrganization(@Param("organizationCode") String organizationCode,@Param("flag") String flag);
 

	/*
	 * @Query(
	 * value="select * from (select * from purchase_order po where po.purchase_order_id = :poId and po.organization_code = :organizationCode and po.delete_flag = :flag order by po.updated_on desc) where rownum = 1"
	 * ,nativeQuery = true) PurchaseOrder validateByIDOrganization(@Param("poId")
	 * Long poId,@Param("organizationCode") String organizationCode,@Param("flag")
	 * String flag);
	 */
	
	@Query(value="select * from purchase_order po where po.purchase_order_id = :poId and po.organization_code = :organizationCode and po.delete_flag = :flag order by po.updated_on limit 1",nativeQuery = true)
	PurchaseOrder validateByIDOrganization(@Param("poId") Long poId,@Param("organizationCode") String organizationCode,@Param("flag") String flag);
 
	
	
	
	/*
	 * @Query(value="select * from (" +
	 * "select * from purchase_order po left outer join PO_LINE_ITEMS poline  on po.PURCHASE_ORDER_ID = poline.PURCHASE_ORDER_ID "
	 * +
	 * "where po.purchase_order_number = :purchaseOrderNumber and poline.SKU_ID = :skuNumber and po.organization_code = :organizationCode and po.delete_flag = :flag "
	 * + "order by po.updated_on desc) where rownum = 1",nativeQuery = true)
	 * PurchaseOrder validateByOrderSkuOrganization(@Param("purchaseOrderNumber")
	 * String purchaseOrderNumber,@Param("skuNumber") String
	 * skuNumber,@Param("organizationCode") String organizationCode,@Param("flag")
	 * String flag);
	 */
	
	@Query(value=""
			+ "select * from purchase_order po left outer join PO_LINE_ITEMS poline  on po.PURCHASE_ORDER_ID = poline.PURCHASE_ORDER_ID "
			+ "where po.purchase_order_number = :purchaseOrderNumber and poline.SKU_ID = :skuNumber and po.organization_code = :organizationCode and po.delete_flag = :flag "
			+ "order by po.updated_on limit 1",nativeQuery = true)
	PurchaseOrder validateByOrderSkuOrganization(@Param("purchaseOrderNumber") String purchaseOrderNumber,@Param("skuNumber") String skuNumber,@Param("organizationCode") String organizationCode,@Param("flag") String flag);
	
	
	
	
	
	@Query(value="select po.purchase_order_number as purchaseOrderNumber,po.logistic_provider as logisticProvider,po.purchase_order_id as purchaseOrderId,"
			+ "po.origin as origin,po.po_receive_date as poReceiveDate,po.po_promise_date as poPromiseDate,po.vendor_name as poVendorName,"
			+ "po.vendor_duns as poVendorDuns,po.vendor_address as poVendorAddress,po.vendor_city as poVendorCity,po.vendor_state as poVendorState,"
			+ "po.vendor_zipcode as poVendorZipcode,po.vendor_country as poVendorCountry,po.buyer_name as poBuyerName,po.buyer_duns as poBuyerDuns,"
			+ "po.billing_address as poBillingAddress,po.po_reference_id as poReferenceId,po.po_expenditure_code_id as poExpenditureCode,"
			+ "po.po_requistioned_by as poRequistionedBy,po.po_tax as poTax,po.po_freight_handling as poFreightHandling,po.po_status as poStatus,"
			+ "po.lead_time as poLeadTime,po.po_date as poDate,po.notes as poNotes,po.destination as poDestination,"
			+ "poline.quantity as quantity,poline.sku_id as skuNumber,poline.line_item_status as lineItemStatus,"
			+ "poline.ship_to_city as shipToCity,poline.ship_to_state as shipToState,poline.promised_date as promisedDate,"
			+ "poline.po_item_id as poItemId,poline.sku_description as skuDescription,poline.committed_quantity as committedQuantity,"
			+ "poline.due_date as dueDate,poline.unit_of_measurement as unitOfMeasurement,poline.price as price,"
			+ "poline.ship_to_location_code as shipToLocationCode,poline.ship_to_company as shipToCompany,poline.ship_to_address as shipToAddress,"
			+ "poline.ship_to_zip as shipToZip,poline.ship_to_country as  shipToCountry "
			+ "from purchase_order po left outer join po_line_items poline on po.purchase_order_id = poline.purchase_order_id  "
			+ "where po.organization_code = :organizationCode and po.delete_flag = :flag order by po.updated_on desc",nativeQuery = true)
	List<IPOListAllItems> findAllForpurchaseOrder(@Param("organizationCode") String organizationCode,@Param("flag") String flag);

	@Query(value="select * from purchase_order po where po.purchase_order_number = :poNumber and po.delete_flag = :flag",nativeQuery = true)
	List<PurchaseOrder> findByAsnNum(@Param("poNumber") String poNumber,@Param("flag") String flag);	

	PurchaseOrder save(POAPIRequest poApiRequest);

	@Modifying
	@Query(value="update purchase_order po set po.delete_flag = :status where po.purchase_order_id = :poId and po.organization_code = :organizationCode",nativeQuery = true)
	int setDeleteStatusForpurchaseOrder(@Param("status") String status, @Param("poId") List<Long> poId,@Param("organizationCode") String organizationCode);
	
	@Modifying
	@Query(value="update purchase_order po set po.po_status = :status where po.purchase_order_number = :podNumber",nativeQuery = true)
	int setUpdatePOStatusForpurchaseOrder(@Param("status") String status,@Param("podNumber") String podNumber);

	
	@Query(value="select count(*) from PURCHASE_ORDER po where po.ORGANIZATION_CODE = :organizationCode and po.DELETE_FLAG = :flag", nativeQuery = true)
	long findAllPurchaseOrderCount(@Param("flag") String flag, @Param("organizationCode") String organizationCode);
	
	
	@Query(value="select count(*) from po_line_items po where po.DELETE_FLAG = :flag and "
			+ "PURCHASE_ORDER_ID in (select PURCHASE_ORDER_ID from PURCHASE_ORDER po where po.ORGANIZATION_CODE = :organizationCode and po.DELETE_FLAG = :flag) "
			+ "and SYSDATE-1 > to_char(DUE_DATE, 'DD-MON-YY')",nativeQuery = true)
	long findAllPOPastDeliveryDate(@Param("flag") String flag, @Param("organizationCode") String organizationCode);
	
	@Query(value="select po.PURCHASE_ORDER_NUMBER as purchaseOrderNumber, poline.SKU_ID as skuId, SUM(poline.QUANTITY) as quantity "
			+ "from PURCHASE_ORDER po INNER JOIN PO_LINE_ITEMS poline ON po.PURCHASE_ORDER_ID = poline.PURCHASE_ORDER_ID "
			+ "where po.ORGANIZATION_CODE = :organizationCode and po.DELETE_FLAG = :flag GROUP BY (po.PURCHASE_ORDER_NUMBER, poline.SKU_ID)", nativeQuery = true)
	List<ShipmentQuantityExceptionResponse> findAllPurchaseOrderQuantity(@Param("flag") String flag, @Param("organizationCode") String organizationCode);
	
	@Query(value="select * from (select po.PURCHASE_ORDER_NUMBER as purchaseOrderNumber, poline.QUANTITY as quantity, poline.PROMISED_DATE as promisedDate, poline.SKU_ID as skuNumber, po.ORIGIN as origin, poline.SHIP_TO_ADDRESS as shipToAddress, poline.SHIP_TO_CITY as shipToCity, poline.SHIP_TO_STATE as shipToState, poline.SHIP_TO_ZIP as shipToZip "
			+ "from PURCHASE_ORDER po INNER JOIN PO_LINE_ITEMS poline ON po.PURCHASE_ORDER_ID = poline.PURCHASE_ORDER_ID "
			+ "where po.PURCHASE_ORDER_NUMBER = :purchaseOrderNumber and po.ORGANIZATION_CODE = :organizationCode and po.DELETE_FLAG = :flag and poline.SKU_ID = :skuNumber order by po.updated_on desc) where rownum =1", nativeQuery = true)
	IShipmentLogOrderDetails findShipmentLogOrderDetails(@Param("purchaseOrderNumber") String purchaseOrderNumber,@Param("skuNumber") String skuNumber, @Param("organizationCode") String organizationCode,@Param("flag") String flag);
	
	@Query(value="select e.SHIPMENT_NUMBER as shipmentNumber, e.UNIT_ID as unitId, "
			+ "e.EXPECTED_DELIVERY_DATE as promisedDate, e.BILL_TO_NAME as billToName, "
			+ "e.TRANSPORTATION_METHOD as transportationMethod,"
			+ "e.STATUS as status,e.SHIPPING_COMPANY as shippingCompany,e.WAY_BILL_NUMBER as wayBillNumber,e.ASN_NUMBER as asnNumber,"
			+ "e.TRACKING_NUMBER as trackingNumber,e.EXPECTED_PICKUP_DATE as expectedPickupDate,e.ORIGIN as origin,e.ORIGIN_ADDRESS1 as originAddress1,"
			+ "e.ORIGIN_ADDRESS2 as originAddress2,e.ORIGIN_CITY as originCity,e.ORIGIN_STATE as originState,e.ORIGIN_ZIP_CODE as originZipCode,"
			+ "e.ORIGIN_COUNTRY as originCountry,e.DESTINATION as destination,e.DESTINATION_ADDRESS1 as destinationAddress1,"
			+ "e.DESTINATION_ADDRESS2 as destinationAddress2,e.DESTINATION_CITY as destinationCity,e.DESTINATION_STATE as destinationState,"
			+ "e.DESTINATION_ZIP_CODE as destinationZipCode,e.DESTINATION_COUNTRY as destinationCountry,e.TOTAL_WEIGHT as totalWeight,"
			+ "e.SHIPMENT_NUMBER_PART as shipmentNumberPart,e.SHIPMENT_NUMBER_TOTAL as shipmentNumberItems,e.NUMBER_OF_UNIQUE_ITEMS as numberOfLineItems,"
			+ "fli.PO_NUMBER as poNumber,fli.ITEM_NUMBER as itemNumber,fli.ITEM_DESCRIPTION as itemDescription,fli.ITEM_QUANTITY_UOM as itemQuantityUOM,"
			+ "fli.CHARGES as price,fli.CONTENT_DESCRIPTION as contentDescription,fli.WEIGHT_QUALIFIER as weightQualifier,"
			+ "fli.ITEM_QUANTITY as itemQuantity "
	        + "from ADVANCE_SHIPMENT_NOTICE e INNER JOIN FREIGHT_LINE_ITEMS fli ON fli.SHIPMENT_ID = e.SHIPMENT_ID "
			+ "INNER JOIN PURCHASE_ORDER po ON po.PURCHASE_ORDER_NUMBER = fli.PO_NUMBER "
			+ "INNER JOIN PO_LINE_ITEMS poline ON poline.PURCHASE_ORDER_ID = po.PURCHASE_ORDER_ID "
			+ "where fli.ITEM_NUMBER = poline.SKU_ID and po.PURCHASE_ORDER_NUMBER = :purchaseOrderNumber and poline.SKU_ID = :skuNumber and po.ORGANIZATION_CODE = :organizationCode and po.DELETE_FLAG = :flag", nativeQuery = true)
	List<IShipmentLogDetails> findShipmentLogDetails(@Param("purchaseOrderNumber") String purchaseOrderNumber,@Param("skuNumber") String skuNumber, @Param("organizationCode") String organizationCode,@Param("flag") String flag);
	
	/*
	 * @Query(
	 * value="select * from (select * from purchase_order po where po.PURCHASE_ORDER_NUMBER = :purchaseOrderNumber and po.ORGANIZATION_CODE = :organizationCode and po.DELETE_FLAG = :flag order by po.UPDATED_ON desc) where rownum =1"
	 * , nativeQuery = true) PurchaseOrder
	 * findByPoNumForDuplication(@Param("purchaseOrderNumber") String
	 * purchaseOrderNumber,@Param("organizationCode") String
	 * organizationCode,@Param("flag") String flag);
	 */
	
	@Query(value="select * from purchase_order po where po.PURCHASE_ORDER_NUMBER = :purchaseOrderNumber and po.ORGANIZATION_CODE = :organizationCode and po.DELETE_FLAG = :flag order by po.UPDATED_ON limit 1",  nativeQuery = true)
	PurchaseOrder findByPoNumForDuplication(@Param("purchaseOrderNumber") String purchaseOrderNumber,@Param("organizationCode") String organizationCode,@Param("flag") String flag);

	
	
	
	@Modifying
	@Query(value="UPDATE po_line_items items SET LINE_ITEM_STATUS = :poStatus WHERE items.SKU_ID = :itemNumber and items.ship_to_city = :destination and items.delete_flag = :flag and PURCHASE_ORDER_ID IN\r\n" + 
			"(select po.PURCHASE_ORDER_ID from purchase_order po join po_line_items items on po.purchase_order_id=items.purchase_order_id and po.purchase_order_number = :poNumber and po.organization_code= :organizationCode and po.delete_flag = :flag)",nativeQuery = true)
	int setUpdatePOLineItemsStatusByShipment(@Param("poStatus") String poStatus,@Param("itemNumber") String itemNumber,@Param("destination") String destination,@Param("poNumber") String poNumber,@Param("organizationCode") String organizationCode,@Param("flag") String flag);
	
	@Query(value="select po.PURCHASE_ORDER_ID from purchase_order po join po_line_items items on po.purchase_order_id=items.purchase_order_id and po.purchase_order_number = :poNumber and po.organization_code= :organizationCode and po.delete_flag = :flag", nativeQuery = true)
	List<Long> findPurchaseOrderIds(@Param("poNumber") String poNumber, @Param("organizationCode") String organizationCode,@Param("flag") String flag);
	
	@Query(value="select items.* from po_line_items items WHERE items.LINE_ITEM_STATUS = :poStatus and items.SKU_ID = :itemNumber and items.ship_to_city = :destination and items.delete_flag = :flag and PURCHASE_ORDER_ID IN\r\n" + 
			"(select po.PURCHASE_ORDER_ID from purchase_order po join po_line_items items on po.purchase_order_id=items.purchase_order_id and po.purchase_order_number = :poNumber and po.organization_code= :organizationCode and po.delete_flag = :flag)",nativeQuery = true)
	List<POLineItems> findPo_line_items(@Param("poStatus") String poStatus,@Param("itemNumber") String itemNumber,@Param("destination") String destination,@Param("poNumber") String poNumber,@Param("organizationCode") String organizationCode,@Param("flag") String flag);
	
	PurchaseOrder findByPurchaseOrderNumberAndDestinationAndOrganizationCodeAndDeleteFlag(String poNumber,
			String destination, String organizationCode, String constants);

	List<PurchaseOrder> findByPurchaseOrderNumberAndOrganizationCodeAndDeleteFlag(String poNumber,
			String organizationCode, String constants);
}
