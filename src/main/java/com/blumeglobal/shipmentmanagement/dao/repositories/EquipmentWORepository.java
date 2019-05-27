package com.blumeglobal.shipmentmanagement.dao.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.model.Shipment;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

@Repository
@REZ1Logger
@REZ1PerformanceLogger

public interface EquipmentWORepository extends CrudRepository<Shipment, Long>, JpaSpecificationExecutor<Shipment>{
	
	@Query(nativeQuery=true, value = "select freightdescription from equipmentonworkorder where workorderid = :referenceid and rownum = 1")
	String selectShipmentFrieghtDescription(@Param("referenceid") Long referenceid);
}
