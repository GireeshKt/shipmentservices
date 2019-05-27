package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.model.ShipmentWO;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface ShipmentWorkOrderRepository
		extends CrudRepository<ShipmentWO, Long>, JpaSpecificationExecutor<ShipmentWO> {

	@Modifying
	@Transactional
	@Query(value = "update WORKORDER W set W.STATUSID = :status where W.WORKORDERID = :workOrderId and W.WORKORDERNUMBER = :workOrderNumber and W.STATUSID not in(4,6,7)", nativeQuery = true)
	int updateWorkOrderSetWorkOrderStatusDray(@Param("status") Integer status, @Param("workOrderId") Long workOrderId,
			@Param("workOrderNumber") String workOrderNumber);

	@Modifying
	@Transactional
	@Query(value = "update RAILBOOKING R set R.STATUSID = :status where R.RAILBOOKINGID = :railBookingId and R.RAILBOOKINGNUMBER =:railBookingNumber and R.STATUSID not in(4,5,7,8)", nativeQuery = true)
	int updateWorkOrderSetWorkOrderStatusRail(@Param("status") Integer status,
			@Param("railBookingId") Long railBookingId, @Param("railBookingNumber") String railBookingNumber);

	@Query(nativeQuery = true, value = "select * from ( select row_.*, rownum rownum_ from (select * from vw_shipmentworkorders where "
			+ "WORKORDERDATE between :fromDate and :currDate and RN1=1 and ORIGINATORID in :originatorId order by SHIPMENTSTATUS asc, WORKORDERDATE desc) row_ where rownum <= :endRow) "
			+ "where rownum_ > :startRow")
	List<ShipmentWO> getShipmentDetailsByOriginatorId(@Param("originatorId") List<Long> originatorId,
			@Param("startRow") Integer startRow, @Param("endRow") Integer endRow, @Param("currDate") Date currDate, @Param("fromDate") Date fromDate);

	@Query(nativeQuery = true, value = "select * from ( select row_.*, rownum rownum_ from (select * from vw_shipmentworkorders where "
			+ "WORKORDERDATE between :fromDate and :currDate and RN1=1 and RECEIVERID in :receiverId order by SHIPMENTSTATUS asc, WORKORDERDATE desc) row_ where rownum <= :endRow) "
			+ "where rownum_ > :startRow")
	List<ShipmentWO> getShipmentDetailsByReceiverId(@Param("receiverId") List<Long> receiverId,
			@Param("startRow") Integer startRow, @Param("endRow") Integer endRow, @Param("currDate") Date currDate, @Param("fromDate") Date fromDate);
	
	@Query(nativeQuery = true, value = "select * from vw_shipmentworkorders where WORKORDERDATE>:fromDate AND WORKORDERDATE<:toDate AND ORIGINATORID =:officeId order by shipmentstatus asc, workorderdate desc")
	List<ShipmentWO> getShipmentByDateRangeForOriginator(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate, @Param("officeId") Long officeId);

	@Query(nativeQuery = true, value = "select * from vw_shipmentworkorders where WORKORDERDATE>:fromDate AND WORKORDERDATE<:toDate AND RECECIVERID =:officeId order by shipmentstatus asc, workorderdate desc")
	List<ShipmentWO> getShipmentByDateRangeForReceiver(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate, @Param("officeId") Long officeId);

}
