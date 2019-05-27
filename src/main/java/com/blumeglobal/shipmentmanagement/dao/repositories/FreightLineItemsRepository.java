package com.blumeglobal.shipmentmanagement.dao.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.blumeglobal.shipmentmanagement.model.AdvanceShipmentNotice;
import com.blumeglobal.shipmentmanagement.model.FreightLineItems;


public interface FreightLineItemsRepository extends CrudRepository<FreightLineItems,Long>{
	@Query(value="select * from freight_line_items li where li.shipment_id = :shipmentId and li.item_number = :itemNumber and li.DELETE_FLAG = :flag",nativeQuery = true)
	FreightLineItems findForCreateFreight_Line_Items(@Param("shipmentId") Long shipmentId,@Param("itemNumber") String itemNumber,@Param("flag") String flag);

	@Query(value="select * from freight_line_items li where li.freight_line_id = :id and li.shipment_id = :shipmentId and li.item_number = :itemNumber and li.DELETE_FLAG = :flag",nativeQuery = true)
	FreightLineItems findForFreight_Line_Items(@Param("id") Long id,@Param("shipmentId") Long shipmentId,@Param("itemNumber") String itemNumber,@Param("flag") String flag);
	
	FreightLineItems findByItemNumberAndDeleteFlag(String skuId, String string);

	FreightLineItems findByFreightLineItemsRefAndItemNumberAndDeleteFlag(AdvanceShipmentNotice advanceShipmentNotice, String itemNumber,
			String string);
}
