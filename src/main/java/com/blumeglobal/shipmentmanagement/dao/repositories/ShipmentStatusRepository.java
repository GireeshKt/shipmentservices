package com.blumeglobal.shipmentmanagement.dao.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.blumeglobal.shipmentmanagement.model.ShipmentStatus;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

/**
* The ShipmentStatus Repository is used for to manage the ShipmentStatus details
*
*/
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface ShipmentStatusRepository extends CrudRepository<ShipmentStatus,Long>, JpaSpecificationExecutor<ShipmentStatus> {
	
	@Query(nativeQuery = true, value = "SELECT STATUSID FROM SHIPMENTSTATUS WHERE DESCRIPTION = :description")
    Long findShipmentStatusIdBydescription(@Param("description")  String description);

}
