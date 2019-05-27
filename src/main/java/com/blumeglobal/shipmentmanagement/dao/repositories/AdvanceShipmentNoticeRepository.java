package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.exceptionlist.response.ShipmentQuantityExceptionResponse;
import com.blumeglobal.shipmentmanagement.model.AdvanceShipmentNotice;
import com.blumeglobal.shipmentmanagement.model.E2EShipment;
import com.blumeglobal.shipmentmanagement.model.IShipmentOrderLine;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

/**
 * The AdvanceShipmentNoticeRepository is used for to manage the AdvanceShipmentNotice details
 *
 */
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface AdvanceShipmentNoticeRepository extends CrudRepository<AdvanceShipmentNotice,Long>, JpaSpecificationExecutor<AdvanceShipmentNotice> {

	
	@Query(value="select * from ADVANCE_SHIPMENT_NOTICE sh where sh.shipment_number = :shipmentNumber and sh.ORGANIZATION_CODE = :organizationCode and sh.DELETE_FLAG = :flag and rownum=1",nativeQuery = true)
	AdvanceShipmentNotice getShipmentByShipmentNumberOrgCode(@Param("shipmentNumber") String shipmentNumber,@Param("organizationCode") String organizationCode, @Param("flag") String flag);
	
	@Query(value="select * from (select * from ADVANCE_SHIPMENT_NOTICE asn where asn.asn_number = :asnNumber\n" + 
			"and asn.ORGANIZATION_CODE = :organizationCode and asn.DELETE_FLAG = :flag\n" + 
			"and asn.unit_id is not null order by asn.shipment_id desc) where rownum=1",nativeQuery = true)
	AdvanceShipmentNotice getShipmentByAsnNumberOrgCode(@Param("asnNumber") String asnNumber,@Param("organizationCode") String organizationCode, @Param("flag") String flag);
	
	@Query(value="select * from ADVANCE_SHIPMENT_NOTICE sh where sh.shipment_id = :shipmentId and sh.organization_code = :organizationCode and sh.delete_flag = :flag",nativeQuery = true)
	AdvanceShipmentNotice findForShipment(@Param("shipmentId") Long a,@Param("organizationCode") String organizationCode,@Param("flag") String flag);
	
	@Modifying
	@Query(value="update advance_shipment_notice sh set sh.delete_flag = :status where sh.shipment_id IN :shipmentId",nativeQuery = true)
	int setDelete_statusFore2e_shipment(@Param("status") String status, @Param("shipmentId") List<Long> shipmentId);

	@Query(value="select * from advance_shipment_notice sh where sh.organization_code = :organizationCode and sh.delete_flag = :flag",nativeQuery = true)
	List<AdvanceShipmentNotice> findAllShipments(@Param("organizationCode") String organizationCode,@Param("flag") String flag);
	
	@Query(value="select count(*) from ADVANCE_SHIPMENT_NOTICE sh where sh.DELETE_FLAG = :flag and sh.ORGANIZATION_CODE = :organizationCode",nativeQuery = true)
	long findAllShipmentsCount(@Param("flag") String flag, @Param("organizationCode") String organizationCode);
	
	@Query(value="select count(*) from ADVANCE_SHIPMENT_NOTICE sh "
			+ "where sh.DELETE_FLAG = :flag and SYSDATE-1 > to_char(sh.EXPECTED_DELIVERY_DATE, 'DD-MON-YY') and sh.ORGANIZATION_CODE = :organizationCode",nativeQuery = true)
	long findAllShipmentPastDueDate(@Param("flag") String flag, @Param("organizationCode") String organizationCode);
	
	@Query(value="select freightline.PO_NUMBER as purchaseOrderNumber, freightline.ITEM_NUMBER as skuId, SUM(freightline.ITEM_QUANTITY) as quantity "
			+ "from ADVANCE_SHIPMENT_NOTICE sh INNER JOIN FREIGHT_LINE_ITEMS freightline ON sh.SHIPMENT_ID = freightline.SHIPMENT_ID "
			+ "where sh.DELETE_FLAG = :flag and sh.ORGANIZATION_CODE = :organizationCode GROUP BY (freightline.PO_NUMBER, freightline.ITEM_NUMBER)",nativeQuery = true)
	List<ShipmentQuantityExceptionResponse> findAllShipmentQuantity(@Param("flag") String flag, @Param("organizationCode") String organizationCode);
	
	@Query(value="select * from ADVANCE_SHIPMENT_NOTICE sh where sh.shipment_number = :shipmentNumber and sh.origin = :origin and sh.destination = :destination and upper(sh.ORGANIZATION_CODE) = :organizationCode and sh.DELETE_FLAG = :flag",nativeQuery = true)
	AdvanceShipmentNotice findForCreateShipment(@Param("shipmentNumber") String shipmentNumber,@Param("origin") String origin,@Param("destination") String destination,@Param("organizationCode") String organizationCode,
			@Param("flag") String flag);
	AdvanceShipmentNotice findByShipmentIdAndUnitIdAndDeleteFlag(Long shipmentId, String containerId, String string);

	@Query(value="select distinct po_number, sku_id, ship_to_country, ship_to_state, promised_date, \r\n" + 
			"committed_quantity, item_quantity_uom, shipmentCount  from (\r\n" + 
			"select \r\n" + 
			"(select count(distinct e2e.shipment_id) from freight_line_items fli, advance_shipment_notice e2e \r\n" + 
			"where fli.item_number=shipPoLine.sku_id and fli.po_number=shipPoLine.po_number and fli.shipment_id=e2e.shipment_id \r\n" + 
			"and e2e.organization_code=shipPoLine.organization_code) as shipmentCount,\r\n" + 
			"shipPoLine.*\r\n" + 
			"from  (select fl.shipment_id, pol.purchase_order_id, fl.PO_NUMBER, pol.sku_id, pol.ship_to_country,\r\n" + 
			"pol.ship_to_state, pol.promised_date, pol.COMMITTED_QUANTITY, fl.ITEM_QUANTITY_UOM, po.organization_code\r\n" + 
			"from freight_line_items fl, purchase_order po, po_line_items pol, advance_shipment_notice e2 \r\n" + 
			"where fl.item_number = pol.sku_id\r\n" + 
			"and fl.PO_NUMBER = po.purchase_order_number\r\n" + 
			"and pol.purchase_order_id=po.purchase_order_id\r\n" + 
			"and e2.shipment_id=fl.shipment_id\r\n" + 
			"and e2.organization_code=po.organization_code\r\n" + 
			"and fl.shipment_id = :shipmentId)  shipPoLine )\r\n" + 
			"",nativeQuery = true)
	List<IShipmentOrderLine> findAllShipmentOrderLines(Long shipmentId);
	
	@Query(value="select distinct e2e.* from advance_shipment_notice e2e, freight_line_items fli\r\n" + 
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
	List<AdvanceShipmentNotice> findShipmentByOrderNumberSku(@Param("poNumber") String poNumber, @Param("sku") String sku, @Param("organization_code") String orgnizationCode);

	@Query(value="select * from ADVANCE_SHIPMENT_NOTICE sh where sh.SHIPMENT_NUMBER = :shipmentNumber and sh.ASN_NUMBER = :asnNumber and sh.ORGANIZATION_CODE = :organizationCode and sh.DELETE_FLAG = :flag",nativeQuery = true)
	List<AdvanceShipmentNotice> findShipment(@Param("shipmentNumber") String shipmentNumber, @Param("asnNumber") String asnNumber, @Param("organizationCode") String organizationCode,@Param("flag") String flag);
	
	
	
	/*
	 * commenting this as it was not supported by MySQL
	 * 
	 * @Query(
	 * value="select (select asnn.ASN_NUMBER from (select asn.ASN_NUMBER,asn.unit_id,asn.shipment_number from ADVANCE_SHIPMENT_NOTICE asn )asnn where asnn.unit_id = e2e.unit_id \n"
	 * +"and asnn.shipment_number = e2e.shipment_number and ROWNUM = 1 )AS ASN_NUMBER,\n"
	 * +"e2e.* from e2e_shipment e2e where e2e.shipment_number= :shipmentNumber and e2e.ORGANIZATION_CODE = :organizationCode  and e2e.delete_flag= :flag \n"
	 * +"and e2e.shipment_id = :e2eShipmentId",nativeQuery=true)
	 * 
	 * AdvanceShipmentNotice
	 * getShipmentByShipmentNumberUnitIdOrgCode(@Param("shipmentNumber") String
	 * shipmentNumber,
	 * 
	 * @Param("e2eShipmentId") Long e2eShipmentId, @Param("organizationCode") String
	 * organizationCode,
	 * 
	 * @Param("flag") String flag);
	 */
	 
	
	@Query(value = "SELECT asn.ASN_NUMBER FROM ADVANCE_SHIPMENT_NOTICE asn LEFT JOIN E2E_SHIPMENT e2e ON (asn.unit_id = e2e.unit_id )\n"
			+ "WHERE e2e.ORGANIZATION_CODE = :organizationCode AND e2e.delete_flag= :flag AND e2e.shipment_number= :shipmentNumber AND  e2e.shipment_id = :e2eShipmentId ORDER BY e2e.shipment_id DESC LIMIT 1", nativeQuery = true)
	AdvanceShipmentNotice getShipmentByShipmentNumberUnitIdOrgCode(@Param("shipmentNumber") String shipmentNumber,
			@Param("e2eShipmentId") Long e2eShipmentId, @Param("organizationCode") String organizationCode,
			@Param("flag") String flag);
	
	
	List<AdvanceShipmentNotice> findByShipmentNumberAndOrganizationCodeAndStatusAndDeleteFlagOrderByUpdatedDateTimeDesc(String shipmentNumber,
			 String organizationCode, String constants, String string);

	

	
}