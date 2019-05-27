package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.exceptionlist.response.ShipmentQuantityExceptionResponse;
import com.blumeglobal.shipmentmanagement.model.E2EShipment;
import com.blumeglobal.shipmentmanagement.model.IShipmentFreightDetails;
import com.blumeglobal.shipmentmanagement.model.IShipmentOrderLine;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

/**
 * The E2EShipmentRepository is used for to manage the E2EShipment details
 *
 */
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface E2EShipmentRepository extends CrudRepository<E2EShipment,Long>, JpaSpecificationExecutor<E2EShipment> {

	
	  //commenting as it was not supported by MySQL
	
	/*
	 * @Query(nativeQuery = true, value =
	 * "select * from ( select row_.*, rownum rownum_ from (select * from E2E_SHIPMENT where\n"
	 * +
	 * "CREATED_DATE_TIME between :fromDate and :currDate and (ORGANIZATION_CODE is null or ORGANIZATION_CODE in :organizationCode )\n"
	 * +
	 * "and exists (select * from shipment where shipment.e2e_shipmentId =E2E_SHIPMENT.shipment_Id\n"
	 * +
	 * "and ( lower(shipment.status) != 'completed' or shipment.status is null) )\n"
	 * + "order by  CREATED_DATE_TIME desc) row_ where rownum <= :endRow) \n" +
	 * "where rownum_ > :startRow") List<E2EShipment> getActiveE2EShipments(
	 * 
	 * @Param("startRow") Integer startRow, @Param("endRow") Integer
	 * endRow, @Param("currDate") Date currDate,
	 * 
	 * @Param("fromDate") Date fromDate, @Param("organizationCode") List<String>
	 * organizationCode );
	 */
	
	
	  @Query(nativeQuery = true, value = "SELECT * FROM E2E_SHIPMENT WHERE\n" +
	  "CREATED_DATE_TIME BETWEEN :fromDate AND :currDate AND ORGANIZATION_CODE IS NULL OR ORGANIZATION_CODE IN (:organizationCode) \n"
	  + "AND EXISTS (\n" +
	  "SELECT * FROM SHIPMENT,E2E_SHIPMENT WHERE SHIPMENT.E2E_SHIPMENTID = E2E_SHIPMENT.SHIPMENT_ID\n"
	  +
	  "AND LOWER(SHIPMENT.status) != 'completed' OR SHIPMENT.status IS NULL ORDER BY  CREATED_DATE_TIME DESC LIMIT :startRow,:endRow\n"
	  + ")") List<E2EShipment> getActiveE2EShipments(	  
	  @Param("startRow") Integer startRow, @Param("endRow") Integer
	  endRow, @Param("currDate") Date currDate,	  
	  @Param("fromDate") Date fromDate, @Param("organizationCode") List<String>
	  organizationCode );
	 
	
	/* 
	 * This is not in use anymore. Should be removed
	 * List<E2EShipment> getActiveE2EShipments(
	 * 
	 * @Param("startRow") Integer startRow, @Param("endRow") Integer
	 * endRow, @Param("currDate") Date currDate,
	 * 
	 * @Param("fromDate") Date fromDate, @Param("organizationCode") String
	 * organizationCode );
	 */
	
	
	
	@Query(nativeQuery = true, value = "SELECT * FROM E2E_SHIPMENT WHERE\n" + 
			"CREATED_DATE_TIME BETWEEN :fromDate AND :currDate AND (ORGANIZATION_CODE IS NULL OR ORGANIZATION_CODE IN (:organizationCode))\n" + 
			"AND EXISTS (SELECT * FROM SHIPMENT,E2E_SHIPMENT WHERE SHIPMENT.e2e_shipmentId =E2E_SHIPMENT.shipment_Id\n" + 
			"AND ( LOWER(SHIPMENT.status) != 'completed' OR SHIPMENT.status IS NULL) ) ORDER BY  CREATED_DATE_TIME DESC")
	List<E2EShipment> getActiveE2EShipmentsSummary( @Param("currDate") Date currDate,
			@Param("fromDate") Date fromDate, @Param("organizationCode") List<String> organizationCode );
	
	
	@Query(nativeQuery = true, value = "select * from ( select row_.*, rownum rownum_ from (select * from E2E_SHIPMENT where\n" + 
			"                CREATED_DATE_TIME between :fromDate and :currDate and  ORGANIZATION_CODE in :organizationCode \n" + 
			"                and exists (select * from shipment where shipment.e2e_shipmentId =E2E_SHIPMENT.shipment_Id and e2e_shipment.shipment_number= :shipmentNumber \n" + 
			"                and ( lower(shipment.status) != 'completed' or shipment.status is null) )\n" + 
			"                order by  CREATED_DATE_TIME desc) row_)")
	E2EShipment getE2EShipmentStatusToOrderDetails( @Param("currDate") Date currDate,
			@Param("fromDate") Date fromDate, @Param("shipmentNumber") String shipmentNumber, @Param("organizationCode") List<String> organizationCode );

	/*
	 * @Query(nativeQuery = true, value ="select * from e2e_shipment\n" +
	 * "where (shipment_number = :shipmentNumber \n" +
	 * "or WAY_BILL_NUMBER in :bolNumbers)\n " +
	 * "and (organization_code is null or \n" +
	 * "organization_code = :organizationCode )\n" + "and  rownum=1\n" + "")
	 * E2EShipment searchE2EShipmentByShipmentRefs(
	 * 
	 * @Param("shipmentNumber") String shipmentNumber, @Param("bolNumbers")
	 * List<String> bolNumbers,
	 * 
	 * @Param("organizationCode") String organizationCode);
	 */
	
	
	
	
	@Query(nativeQuery = true, value ="select * from E2E_SHIPMENT\n" + 
			"where (shipment_number = :shipmentNumber \n" + 
			"or WAY_BILL_NUMBER in :bolNumbers)\n " +
			"and (organization_code is null or \n" + 
			"organization_code = :organizationCode ) limit 1\n" + 
			"")
	E2EShipment searchE2EShipmentByShipmentRefs(
			@Param("shipmentNumber") String shipmentNumber, @Param("bolNumbers") List<String> bolNumbers, 
			@Param("organizationCode") String organizationCode);

	@Query(nativeQuery = true, value = "select * from ( select row_.*, rownum rownum_ from (select * from E2E_SHIPMENT where "
			+ "CREATED_DATE_TIME between :fromDate and :currDate and (ORGANIZATION_CODE is null or ORGANIZATION_CODE in :organizationCode) "
			+ "and exists (SELECT * FROM shipmentexceptions se \n" + 
			"            WHERE e2e_shipment.shipment_id = se.e2e_shipmentid )"
			+ " AND EXISTS ( SELECT  *  FROM  shipment sh " 
			+ "                WHERE  sh.e2e_shipmentid = e2e_shipment.shipment_id )"
			+ " order by CREATED_DATE_TIME desc) row_ where rownum <= :endRow) "
			+ "where rownum_ > :startRow")
	List<E2EShipment> getExceptionE2EShipments(
			@Param("startRow") Integer startRow, @Param("endRow") Integer endRow, @Param("currDate") Date currDate,
			@Param("fromDate") Date fromDate, @Param("organizationCode") List<String> organizationCode );

	@Query(nativeQuery = true, value = "SELECT * FROM E2E_SHIPMENT WHERE CREATED_DATE_TIME BETWEEN :fromDate AND :currDate AND (ORGANIZATION_CODE IS NULL OR ORGANIZATION_CODE IN (:organizationCode))\n" + 
			"AND EXISTS ( SELECT  * FROM SHIPMENTEXCEPTIONS se  WHERE E2E_SHIPMENT.shipment_id = se.e2e_shipmentid)\n" + 
			"AND EXISTS ( SELECT  *  FROM  SHIPMENT sh WHERE  sh.e2e_shipmentid = E2E_SHIPMENT.shipment_id )\n" + 
			"ORDER BY CREATED_DATE_TIME DESC")
	List<E2EShipment> getExceptionE2EShipmentsSummary( @Param("currDate") Date currDate,
			@Param("fromDate") Date fromDate, @Param("organizationCode") List<String> organizationCode );

	
	@Query(nativeQuery = true, value = "select * from\n" + 
			"( select * from ( select row_.*, rownum rownum_ from (select * from E2E_SHIPMENT where\n" + 
			"CREATED_DATE_TIME between :fromDate and :currDate \n" + 
			"and shipment_id in (select shipment_id from e2e_shipment\n" + 
			"where shipment_id in (select e2e_shipmentid\n" + 
			"from shipmentmilestones\n" + 
			"left join milestone \n" + 
			"on shipmentmilestones.milestoneid = milestone.milestoneid \n" + 
			"left join e2e_shipment\n" + 
			"on shipmentmilestones.e2e_shipmentid = e2e_shipment.shipment_id \n" + 
			"where display = 'Y' AND withleadtime = 'Y' AND actualdate >= SYSDATE - 7  AND lower(status) = 'completed' AND (ORGANIZATION_CODE is null OR ORGANIZATION_CODE in :organizationCode)))\n" + 
			"order by  CREATED_DATE_TIME desc) row_ where rownum <= :endRow) \n" + 
			"where rownum_ > :startRow)")
	List<E2EShipment> getCompletedE2EShipments(
			@Param("startRow") Integer startRow, @Param("endRow") Integer endRow, @Param("currDate") Date currDate,
			@Param("fromDate") Date fromDate, @Param("organizationCode") List<String> organizationCode );
	
	@Query(nativeQuery = true, value = "SELECT * FROM E2E_SHIPMENT WHERE CREATED_DATE_TIME BETWEEN :fromDate AND :currDate\n" + 
			"AND shipment_id IN (SELECT shipment_id FROM E2E_SHIPMENT\n" + 
			"WHERE shipment_id IN (SELECT e2e_shipmentid FROM SHIPMENTMILESTONES\n" + 
			"LEFT JOIN MILESTONE ON SHIPMENTMILESTONES.milestoneid = MILESTONE.milestoneid\n" + 
			"LEFT JOIN E2E_SHIPMENT ON SHIPMENTMILESTONES.e2e_shipmentid = E2E_SHIPMENT.shipment_id\n" + 
			"WHERE display = 'Y' AND withleadtime = 'Y' AND actualdate >= SYSDATE() - 7  AND LOWER(STATUS) = 'completed' AND (ORGANIZATION_CODE IS NULL OR ORGANIZATION_CODE IN :organizationCode)))\n" + 
			"ORDER BY  CREATED_DATE_TIME DESC")
	List<E2EShipment> getCompletedE2EShipmentsSummary(
			@Param("currDate") Date currDate,
			@Param("fromDate") Date fromDate, @Param("organizationCode") List<String> organizationCode );
	
	

	/*@Query(nativeQuery = true, value = "select count(shipment_id) \n" + 
			"from e2e_shipment \n" + 
			"where lower(status) != 'completed' AND organization_code = :organizationCode\n" + 
			"AND EXISTS ( SELECT  *  FROM  Shipment sh \n" + 
			"WHERE  sh.e2e_shipmentid = e2e_shipment.shipment_id )" 
			)
	Long getActiveE2EShipmentCount(@Param("organizationCode") String organizationCode);
	*/
	@Query(nativeQuery = true, value = "select count(shipment_id) from ( select row_.*, rownum rownum_ from (select * from E2E_SHIPMENT where \n" + 
			"			CREATED_DATE_TIME between :fromDate and :currentDate and ORGANIZATION_CODE = :organizationCode\n" + 
			"			and exists (select * from shipment where shipment.e2e_shipmentId =E2E_SHIPMENT.shipment_Id\n" + 
			"			and ( lower(shipment.status) != 'completed' or shipment.status is null) )\n" + 
			"			order by  CREATED_DATE_TIME desc) )" 
			)
	Long getActiveE2EShipmentCount(@Param("organizationCode") String organizationCode, @Param("currentDate")Date currentDate, @Param("fromDate")Date fromDate);
	
	/*
	 * @Query(nativeQuery=true, value = "select count(distinct(e2e_shipmentid))\n" +
	 * " from( \n" + " select e2e_shipmentid\n" + " from shipmentmilestones\n" +
	 * " left join milestone\n" +
	 * " on shipmentmilestones.milestoneid = milestone.milestoneid\n" +
	 * " left join e2e_shipment \n" +
	 * " on shipmentmilestones.e2e_shipmentid = e2e_shipment.shipment_id\n" +
	 * " where display = 'Y' AND withleadtime = 'Y' AND actualdate >= SYSDATE - 7 AND lower(status) = 'completed' AND organization_code = :organizationCode\n"
	 * + ")") Long getLastWeekE2ECompletedCount(@Param("organizationCode") String
	 * organizationCode);
	 */
	
	
	@Query(nativeQuery=true, value = "SELECT COUNT( DISTINCT e2e_shipmentid) FROM shipmentmilestones \n" + 
			"LEFT JOIN milestone ON shipmentmilestones.milestoneid = milestone.milestoneid \n" + 
			"LEFT JOIN e2e_shipment ON shipmentmilestones.e2e_shipmentid = e2e_shipment.shipment_id WHERE display = 'Y' AND \n" + 
			"withleadtime = 'Y' AND (actualdate >= DATE(NOW()) - INTERVAL 7 DAY) AND LOWER(STATUS) = 'completed'\n" + 
			"AND organization_code = :organizationCode ")
	Long getLastWeekE2ECompletedCount(@Param("organizationCode") String organizationCode);
	
	
	

	@Query(nativeQuery = true, value = "select * from e2e_shipment sh\n"
			+ " where sh.status !='Completed' "
			+ " AND EXISTS ( SELECT  *  FROM  Shipment ship " 
			+ "                WHERE  ship.e2e_shipmentid = sh.shipment_id )"
			+ " order by sh.shipment_id ")
	List<E2EShipment> findNotCompletedShipment();

	@Query(nativeQuery = true, value = "select * from E2E_SHIPMENT e2esh\n" + 
			"where (e2esh.ORGANIZATION_CODE is null or e2esh.ORGANIZATION_CODE in :organizationCode) and exists \n" + 
			"(select * from SHIPMENTREFERENCES sr, shipment sh\n" + 
			"where sr.shipmentid=sh.shipmentid\n" + 
			"and sh.e2e_shipmentId= e2esh.shipment_id \n" +
			"and lower(sr.shipmentrefvalue) LIKE CONCAT(CONCAT('%', lower(:refValue)), '%')\n" + 
			"and sr.shipmentrefType= :refType ) \n" + 
			"order by CREATED_DATE_TIME desc ")
	List<E2EShipment> findE2EShipmentByRefTypeValue(@Param("refType")  String refType, @Param("refValue")  String refValue,
			@Param("organizationCode") List<String> organizationCode);

	@Query(nativeQuery = true, value = "select * from e2e_shipment e2esh \n" + 
            "    where  exists \n" + 
            "        (select * from shipmentreferences sr, shipment sh \n" + 
            "           where sr.shipmentid = sh.shipmentid\n" + 
            "           and sh.e2e_shipmentId = e2esh.shipment_id \n" + 
            "           and sr.shipmentrefvalue LIKE CONCAT(:refValue,'%')\n" + 
            "           and sr.shipmentrefType = :refType) \n" + 
            "    order by CREATED_DATE_TIME desc")
    List<E2EShipment> findE2EShipmentByRefTypeValue(@Param("refType")  String refType, @Param("refValue")  String refValue);
	
	@Query(value="select * from E2E_SHIPMENT sh where sh.shipment_number = :shipmentNumber and sh.ORGANIZATION_CODE = :organizationCode and sh.DELETE_FLAG = :flag and rownum=1",nativeQuery = true)
	E2EShipment getShipmentByShipmentNumberOrgCode(@Param("shipmentNumber") String shipmentNumber,@Param("organizationCode") String organizationCode, @Param("flag") String flag);
	
	@Query(value="select count(*) from E2E_SHIPMENT sh where sh.DELETE_FLAG = :flag and sh.ORGANIZATION_CODE = :organizationCode",nativeQuery = true)
	long findAllShipmentsCount(@Param("flag") String flag, @Param("organizationCode") String organizationCode);
	
	
	@Query(value="select count(*) from E2E_SHIPMENT sh "
			+ "where sh.DELETE_FLAG = :flag and SYSDATE-1 > to_char(sh.EXPECTED_DELIVERY_DATE, 'DD-MON-YY') and sh.ORGANIZATION_CODE = :organizationCode",nativeQuery = true)
	long findAllShipmentPastDueDate(@Param("flag") String flag, @Param("organizationCode") String organizationCode);
	
	
	@Query(value="select freightline.PO_NUMBER as purchaseOrderNumber, freightline.ITEM_NUMBER as skuId, SUM(freightline.ITEM_QUANTITY) as quantity "
			+ "from E2E_SHIPMENT sh INNER JOIN FREIGHT_LINE_ITEMS freightline ON sh.SHIPMENT_ID = freightline.SHIPMENT_ID "
			+ "where sh.DELETE_FLAG = :flag and sh.ORGANIZATION_CODE = :organizationCode GROUP BY (freightline.PO_NUMBER, freightline.ITEM_NUMBER)",nativeQuery = true)
	List<ShipmentQuantityExceptionResponse> findAllShipmentQuantity(@Param("flag") String flag, @Param("organizationCode") String organizationCode);
	
	/*@Query(value="select * from E2E_SHIPMENT sh where sh.shipment_number = :shipmentNumber and sh.ORGANIZATION_CODE = :organizationCode and sh.DELETE_FLAG = :flag and rownum=1",nativeQuery = true)
	E2EShipment getShipmentByShipmentNumberOrgCode(@Param("shipmentNumber") String shipmentNumber,@Param("organizationCode") String organizationCode, @Param("flag") String flag);
*/
	@Query(value="select distinct po_number, sku_id, ship_to_country, ship_to_state, promised_date, \r\n" + 
			"committed_quantity, item_quantity_uom, shipmentCount  from (\r\n" + 
			"select \r\n" + 
			"(select count(distinct e2e.shipment_id) from freight_line_items fli, e2e_shipment e2e \r\n" + 
			"where fli.item_number=shipPoLine.sku_id and fli.po_number=shipPoLine.po_number and fli.shipment_id=e2e.shipment_id \r\n" + 
			"and e2e.organization_code=shipPoLine.organization_code) as shipmentCount,\r\n" + 
			"shipPoLine.*\r\n" + 
			"from  (select fl.shipment_id, pol.purchase_order_id, fl.PO_NUMBER, pol.sku_id, pol.ship_to_country,\r\n" + 
			"pol.ship_to_state, pol.promised_date, pol.COMMITTED_QUANTITY, fl.ITEM_QUANTITY_UOM, po.organization_code\r\n" + 
			"from freight_line_items fl, purchase_order po, po_line_items pol, e2e_shipment e2 \r\n" + 
			"where fl.item_number = pol.sku_id\r\n" + 
			"and fl.PO_NUMBER = po.purchase_order_number\r\n" + 
			"and pol.purchase_order_id=po.purchase_order_id\r\n" + 
			"and e2.shipment_id=fl.shipment_id\r\n" + 
			"and e2.organization_code=po.organization_code\r\n" + 
			"and fl.shipment_id = :shipmentId)  shipPoLine )\r\n" + 
			"",nativeQuery = true)
	List<IShipmentOrderLine> findAllShipmentOrderLines(Long shipmentId);
	
	@Query(value="select distinct e2e.* from e2e_shipment e2e, freight_line_items fli\r\n" + 
			"where e2e.shipment_id=fli.shipment_id \r\n" + 
			"and e2e.delete_flag='N'\r\n" + 
			"and exists (select po.purchase_order_number \r\n" + 
			"from purchase_order po, po_line_items pol\r\n" + 
			"where po.purchase_order_id=pol.purchase_order_id\r\n" + 
			"and po.purchase_order_number=fli.po_number\r\n" + 
			"and pol.sku_id=fli.item_number \r\n" + 
			"and po.organization_code=e2e.organization_code\r\n" + 
			"and po.delete_flag='Active'\r\n" + 
			"and po.organization_code= :organization_code\r\n" +
			"and pol.sku_id = :sku and po.purchase_order_number= :poNumber)",nativeQuery = true)
	List<E2EShipment> findShipmentByOrderNumberSku(@Param("poNumber") String poNumber, @Param("sku") String sku, @Param("organization_code") String orgnizationCode);

	@Query(value="select * from E2E_SHIPMENT sh where sh.SHIPMENT_NUMBER = :shipmentNumber and sh.ORGANIZATION_CODE = :organizationCode and sh.DELETE_FLAG = :flag",nativeQuery = true)
	List<E2EShipment> findShipment(@Param("shipmentNumber") String shipmentNumber, @Param("organizationCode") String organizationCode,@Param("flag") String flag);
	/*List<E2EShipment> findByShipmentNumberAndOrganizationCodeAndDeleteFlag(String shipmentNumber, String organizationCode,
			String string);*/
	
//	@Query(nativeQuery = true, value = "select * from\n" + 
//			"( select * from ( select row_.*, rownum rownum_ from ( " +
//			"select (select sh.freightDescription from shipment sh  where sh.e2e_shipmentid=sfd.shipmentId and rownum=1) \n" + 
//			"as freightDescription, sfd.* from \n" + 
//			"    ( SELECT\n" + 
//			"         e2.shipment_id as shipmentId,  e2.way_bill_number as bookingNumber, e2.SHIPMENT_NUMBER as shipmentReferenceNumber, e2.STATUS as status,\n" + 
//			"         e2.CONSIGNEE, e2.ORIGIN, e2.ORIGIN_ADDRESS1 as originAddress1, e2.ORIGIN_ADDRESS2 as originAddress2,\n" + 
//			"         e2.ORIGIN_CITY as originCity, e2.ORIGIN_COUNTRY as originCountry, e2.ORIGIN_STATE as originState,\n" + 
//			"         e2.ORIGIN_ZIP_CODE as originZipCode, e2.destination, e2.DESTINATION_ADDRESS1 as destinationAddress1,\n" + 
//			"         e2.DESTINATION_ADDRESS2 as destinationAddress2, e2.DESTINATION_CITY as destinationCity, \n" + 
//			"         e2.DESTINATION_STATE as destinationState, e2.DESTINATION_ZIP_CODE as destinationZipCode,\n" + 
//			"         e2.DESTINATION_COUNTRY as destinationCountry, po.PURCHASE_ORDER_NUMBER  as purchaseOrderNumber,\n" + 
//			"         pol.sku_id as skuNumber, pol.due_date as requestedDate,  pol.promised_date as promisedDate,\n" + 
//			"         po.LOGISTIC_PROVIDER as logisticProvider, po.BUYER_NAME as poBuyerName,\n" + 
//			"         asn.asn_number as asnNumber, e2.unit_id as unitId, pol.COMMITTED_QUANTITY as promisedQuantity,\n" + 
//			"         pol.QUANTITY as requestedQuantity, pol.PO_ITEM_ID as poItemId, pol.LINE_ITEM_STATUS as orderStatus\n" + 
//			"     FROM\n" + 
//			"         freight_line_items fl, purchase_order po,  po_line_items pol,  ADVANCE_SHIPMENT_NOTICE asn , e2e_shipment e2 \n" + 
//			"     WHERE  fl.item_number = pol.sku_id\n" + 
//			"         AND fl.po_number = po.purchase_order_number\n" + 
//			"         AND pol.purchase_order_id = po.purchase_order_id\n" + 
//			"         AND asn.shipment_id = fl.shipment_id\n" + 
//			"         AND e2.organization_code = po.organization_code \n" +
//			"         AND e2.shipment_number = asn.shipment_number \n" +
//			"         AND e2.unit_id = asn.unit_id \n" +
//			"         AND e2.CREATED_DATE_TIME between :fromDate and :currDate \n" + 
//			"         AND e2.organization_code in :organizationCode) sfd\n" + 
//			"     order by promisedDate desc nulls last"
//			+ ") row_ where rownum <= :endRow) \n" + 
//			"where rownum_ > :startRow)")
	
	@Query(nativeQuery = true, value = "select * from\n" + 
	"( select * from ( select row_.*, rownum rownum_ from ( " +
	"select (select sh.freightDescription from shipment sh  where sh.e2e_shipmentid=sfd.shipmentId and rownum=1) \n" + 
	"as freightDescription, sfd.* from \n" + 
	"    ( SELECT  \n" + 
	"			          e2.shipment_id as shipmentId,  e2.way_bill_number as bookingNumber, e2.SHIPMENT_NUMBER as shipmentReferenceNumber, e2.STATUS as status,  \n" + 
	"			          e2.CONSIGNEE, e2.ORIGIN, e2.ORIGIN_ADDRESS1 as originAddress1, e2.ORIGIN_ADDRESS2 as originAddress2,  \n" + 
	"			          e2.ORIGIN_CITY as originCity, e2.ORIGIN_COUNTRY as originCountry, e2.ORIGIN_STATE as originState,  \n" + 
	"			          e2.ORIGIN_ZIP_CODE as originZipCode, po.destination, e2.DESTINATION_ADDRESS1 as destinationAddress1,  \n" + 
	"			          e2.DESTINATION_ADDRESS2 as destinationAddress2, e2.DESTINATION_CITY as destinationCity,   \n" + 
	"			          e2.DESTINATION_STATE as destinationState, e2.DESTINATION_ZIP_CODE as destinationZipCode,  \n" + 
	"			          e2.DESTINATION_COUNTRY as destinationCountry, po.PURCHASE_ORDER_NUMBER  as purchaseOrderNumber,  \n" + 
	"			          pol.sku_id as skuNumber, pol.due_date as requestedDate,  pol.promised_date as promisedDate,  \n" + 
	"			          po.LOGISTIC_PROVIDER as logisticProvider, po.BUYER_NAME as poBuyerName,  \n" + 
	"			          asn.asn_number as asnNumber, e2.unit_id as unitId, pol.COMMITTED_QUANTITY as promisedQuantity,  \n" + 
	"			          pol.QUANTITY as requestedQuantity, pol.PO_ITEM_ID as poItemId, pol.LINE_ITEM_STATUS as orderStatus  \n" + 
	"			      FROM  \n" + 
	"			      purchase_order po \n" + 
	"				  left join po_line_items pol on pol.purchase_order_id = po.purchase_order_id \n" + 
	"				  left join freight_line_items fl on fl.po_number = po.purchase_order_number and fl.item_number = pol.sku_id \n" + 
	"				  left join ADVANCE_SHIPMENT_NOTICE asn  on asn.shipment_id = fl.shipment_id   \n" + 
	"				  left join  e2e_shipment e2   on e2.organization_code = po.organization_code   and e2.shipment_number = asn.shipment_number and  e2.unit_id = asn.unit_id  \n" + 
	"			      WHERE \n" + 
	"					  po.CREATED_ON between :fromDate and :currDate \n" + 
	"			          AND po.organization_code in :organizationCode) sfd\n" + 
	"     order by promisedDate desc nulls last"
	+ ") row_ where rownum <= :endRow) \n" + 
	"where rownum_ > :startRow)")
	
	
	List<IShipmentFreightDetails> getShipmentFreightDetails(
			@Param("startRow") Integer startRow, @Param("endRow") Integer endRow, @Param("currDate") Date currDate,
			@Param("fromDate") Date fromDate, @Param("organizationCode") List<String> organizationCode );


	
	//Search Query method for Purchase Order Summary Page 
	@Query(nativeQuery = true, value = "select (select sh.freightDescription from shipment sh  where sh.e2e_shipmentid=sfd.shipmentId and rownum=1) as freightDescription, sfd.* from \n" + 
			"    (select\n" + 
			"        e2.shipment_id as shipmentId,  \n" + 
			"        e2.way_bill_number as bookingNumber, \n" + 
			"        e2.SHIPMENT_NUMBER as shipmentReferenceNumber, e2.STATUS as status, \n" + 
			"        e2.CONSIGNEE, e2.ORIGIN, \n" + 
			"        e2.ORIGIN_ADDRESS1 as originAddress1, \n" + 
			"        e2.ORIGIN_ADDRESS2 as originAddress2, \n" + 
			"        e2.ORIGIN_CITY as originCity, \n" + 
			"        e2.ORIGIN_COUNTRY as originCountry, \n" + 
			"        e2.ORIGIN_STATE as originState, \n" + 
			"        e2.ORIGIN_ZIP_CODE as originZipCode, \n" + 
			"        po.destination,\n" + 
			"        e2.DESTINATION_ADDRESS1 as destinationAddress1,\n" + 
			"        e2.DESTINATION_ADDRESS2 as destinationAddress2, \n" + 
			"        e2.DESTINATION_CITY as destinationCity, \n" + 
			"        e2.DESTINATION_STATE as destinationState, \n" + 
			"        e2.DESTINATION_ZIP_CODE as destinationZipCode,\n" + 
			"        e2.DESTINATION_COUNTRY as destinationCountry, \n" + 
			"        po.PURCHASE_ORDER_NUMBER  as purchaseOrderNumber, \n" + 
			"        pol.sku_id as skuNumber, \n" + 
			"        pol.due_date as requestedDate,  \n" + 
			"        pol.promised_date as promisedDate,\n" + 
			"        po.LOGISTIC_PROVIDER as logisticProvider, \n" + 
			"        po.BUYER_NAME as poBuyerName, \n" + 
			"        asn.asn_number as asnNumber, \n" + 
			"        e2.unit_id as unitId, \n" + 
			"        pol.COMMITTED_QUANTITY as promisedQuantity,\n" + 
			"        pol.QUANTITY as requestedQuantity, \n" + 
			"        pol.LINE_ITEM_STATUS as orderStatus,\n" + 
			"        pol.PO_ITEM_ID as poItemId\n" + 
"			      FROM  \n" + 
"			      purchase_order po \n" + 
"				  left join po_line_items pol on pol.purchase_order_id = po.purchase_order_id \n" + 
"				  left join freight_line_items fl on fl.po_number = po.purchase_order_number and fl.item_number = pol.sku_id \n" + 
"				  left join ADVANCE_SHIPMENT_NOTICE asn  on asn.shipment_id = fl.shipment_id   \n" + 
"				  left join  e2e_shipment e2   on e2.organization_code = po.organization_code   and e2.shipment_number = asn.shipment_number and  e2.unit_id = asn.unit_id  \n" + 
			"	WHERE (CASE ?1\n" + 
			"          WHEN 'PO' THEN po.PURCHASE_ORDER_NUMBER\n" + 
			"          WHEN 'ASN' THEN asn.asn_number\n" + 
			"          WHEN 'SKU' THEN pol.sku_id\n" + 
			"          WHEN 'SHIPMENTREFNUM' THEN e2.SHIPMENT_NUMBER\n" + 
			"          WHEN 'UNITID' THEN e2.unit_id\n" + 
			"          ELSE e2.SHIPMENT_NUMBER\n" + 
			"          END) LIKE CONCAT(CONCAT('%', ?2) ,'%')\n" + 
			"        AND po.organization_code = ?3) sfd \n" + 
			"			order by promisedDate desc nulls last")
	List<IShipmentFreightDetails> searchShipmentFreightDetails(@Param("searchType") String searchType,
			@Param("searchValue") String searchValue,
			@Param("organizationCode") String organizationCode);

	/*E2EShipment findByShipmentNumberAndDestinationAndOrganizationCodeAndStatusAndDeleteFlag(String shipmentNumber,
			String destination, String organizationCode, String constants, String string);*/

	@Query(value="select e2.* from e2e_shipment e2 join advance_shipment_notice asn on \n" + 
			"e2.shipment_number = asn.shipment_number and  e2.unit_id = asn.unit_id\n" + 
			"and e2.organization_code = asn.organization_code and e2.delete_flag = asn.delete_flag\n" + 
			"where \n" + 
			"asn.shipment_number= :shipmentNumber and asn.unit_id= :unitId and asn.organization_code = :organizationCode and e2.status = :status and e2.delete_flag = :flag",nativeQuery = true)
	List<E2EShipment> getShipmentByShipmentNumberAndUintId(@Param("shipmentNumber") String shipmentNumber,@Param("unitId") String unitId,@Param("organizationCode") String organizationCode,@Param("status") String status, @Param("flag") String flag);
	
	
}












