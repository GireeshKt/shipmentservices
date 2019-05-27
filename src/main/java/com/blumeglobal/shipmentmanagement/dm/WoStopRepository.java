package com.blumeglobal.shipmentmanagement.dm;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

/**
 * The WoStopRepository is used for to manage the WoStop details
 *
 */
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface WoStopRepository
		extends CrudRepository<WoStop, Long>, JpaSpecificationExecutor<WoStop> {
	List<WoStop> findByWorkorderIdOrderByStopNumber(Long workorderId);
	
	@Query(value ="select scheduledtimestart from appointment appt, stop where appt.stopid=stop.stopid and "
			+ " stop.workorderid = :workorderId and stop.stopNumber = :stopNumber and rownum=1", nativeQuery=true)
	Date getStopAppointmentDate(@Param("workorderId") Long workorderId, @Param("stopNumber") Long stopNumber);

}
