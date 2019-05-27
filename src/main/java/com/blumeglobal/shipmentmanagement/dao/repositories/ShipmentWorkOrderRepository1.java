package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.blumeglobal.shipmentmanagement.model.ShipmentDetails;
import com.blumeglobal.shipmentmanagement.model.ShipmentWO;

public interface ShipmentWorkOrderRepository1 extends CrudRepository<ShipmentDetails, Long>, JpaSpecificationExecutor<ShipmentDetails> {
	
	@Query(nativeQuery = true, value = "select * from(select row_.*, rownum rownum_ from( select * from shipmentdetails where ORIGINATORID in :originatorId) row_ where rownum <= :endRow) where rownum_ >= :startRow")
	List<ShipmentDetails> getShipmentDetailsByOriginatorId(@Param("originatorId") List<Long> originatorId,
			@Param("startRow") Integer startRow, @Param("endRow") Integer endRow);
	
	@Query(nativeQuery = true, value = "select * from(select row_.*, rownum rownum_ from(select * from shipmentdetails where SHIPMENTID in(SELECT DISTINCT SHIPMENTID FROM SHIPMENTLEGS where RECEIVERID in :receiverId)) row_ where rownum <= :endRow) where rownum_ >= :startRow") 
	List<ShipmentDetails> getShipmentDetailsByReceiverId(@Param("receiverId") List<Long> receiverId,
			@Param("startRow") Integer startRow, @Param("endRow") Integer endRow);



}
