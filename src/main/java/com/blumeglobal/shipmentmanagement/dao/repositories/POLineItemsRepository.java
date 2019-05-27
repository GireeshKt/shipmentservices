package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.blumeglobal.shipmentmanagement.model.POLineItems;
import com.blumeglobal.shipmentmanagement.model.POTransactions;
import com.blumeglobal.shipmentmanagement.model.PurchaseOrder;
import com.blumeglobal.shipmentmanagement.request.POTransactionsAPIRequest;



public interface POLineItemsRepository extends CrudRepository<POLineItems, Long>{

	POTransactions save(POTransactionsAPIRequest poTransactionsAPIRequest);
	
	@Modifying
	@Query(value="update po_line_items li set li.line_item_status = :status where li.po_item_id = :itemId and li.delete_flag = :flag",nativeQuery = true)
	int updatePO_Line_Items(@Param("itemId") Long itemId,@Param("status") String status,@Param("flag") String flag);

	POLineItems findByPoItemIdAndDeleteFlag(Long lineItemId, String string);

	List<POLineItems> findBySkuNumberAndDeleteFlagAndPoLineItemsRefIn(String itemNumber,
			 String constants, List<PurchaseOrder> po);

	@Query(value="select * from purchase_order po left outer join PO_LINE_ITEMS poline on po.PURCHASE_ORDER_ID = poline.PURCHASE_ORDER_ID"
			+ " where po.PURCHASE_ORDER_NUMBER = :purchaseOrderNumber and po.organization_code= :organizationCode"
			+ " and poline.SKU_ID = :skuNumber and poline.ship_to_city = :shipToCity and poline.PROMISED_DATE = :promisedDate"
			+ " and poline.DELETE_FLAG = :flag order by poline.UPDATED_ON DESC limit 1 ",  nativeQuery = true)
	POLineItems findBySkuIdForDuplication(@Param("purchaseOrderNumber") String purchaseOrderNumber, @Param("organizationCode") String organizationCode,@Param("skuNumber") String skuNumber, @Param("shipToCity") String shipToCity,@Param("promisedDate") Date promisedDate,@Param("flag") String flag);

	List<POLineItems> findBySkuNumberAndShipToCityAndDeleteFlag(String itemNumber, String destination, String string);

	List<POLineItems> findBySkuNumberAndDeleteFlag(String itemNumber, String string);

	
	/*@Modifying
	@Query(value="UPDATE po_line_items items SET LINE_ITEM_STATUS = :poStatus WHERE items.SKU_ID = :itemNumber and items.ship_to_city = :destination and items.delete_flag = :flag and PURCHASE_ORDER_ID IN\r\n" + 
			"(select po.PURCHASE_ORDER_ID from purchase_order po join po_line_items items on po.purchase_order_id=items.purchase_order_id and po.purchase_order_number = :poNumber and po.organization_code= :organizationCode and po.delete_flag = :flag)",nativeQuery = true)
	int setUpdateePOLineItemsStatusByShipment(@Param("poStatus") String poStatus,@Param("itemNumber") String itemNumber,@Param("destination") String destination,@Param("poNumber") String poNumber,@Param("organizationCode") String organizationCode,@Param("flag") String flag);

	@Query(value="select items.* from po_line_items items WHERE items.SKU_ID = :itemNumber and items.ship_to_city = :destination and items.delete_flag = :flag and PURCHASE_ORDER_ID IN\r\n" + 
		"(select po.PURCHASE_ORDER_ID from purchase_order po join po_line_items items on po.purchase_order_id=items.purchase_order_id and po.purchase_order_number = :poNumber and po.organization_code= :organizationCode and po.delete_flag = :flag)",nativeQuery = true)
List<POLineItems> findPOByShipment(@Param("poStatus") String poStatus,@Param("itemNumber") String itemNumber,@Param("destination") String destination,@Param("poNumber") String poNumber,@Param("organizationCode") String organizationCode,@Param("flag") String flag);
*/
	

}


