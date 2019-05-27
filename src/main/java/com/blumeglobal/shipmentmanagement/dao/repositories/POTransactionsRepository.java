package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.blumeglobal.shipmentmanagement.model.POTransactions;
import com.blumeglobal.shipmentmanagement.request.POTransactionsAPIRequest;




public interface POTransactionsRepository extends CrudRepository<POTransactions, Long>{

	POTransactions save(POTransactionsAPIRequest poTransactionsAPIRequest);
	
	@Query(value="select * from (select * from po_transactions po where po.po_item_id = :poItemId and po.organization_code = :organizationCode and po.delete_flag = :flag order by po.updated_on desc) where rownum = 1",nativeQuery = true)
	POTransactions validateByIDOrganization(@Param("poItemId") Long poItemId,@Param("organizationCode") String organizationCode,@Param("flag") String flag);
	
	@Query(value="select * from po_transactions po where po.po_item_id = :poItemId and po.organization_code = :organizationCode and po.delete_flag = :flag order by po.updated_on desc",nativeQuery = true)
	List<POTransactions> findPoTransactionStatus(@Param("poItemId") Long poItemId,@Param("organizationCode") String organizationCode,@Param("flag") String flag);

}
