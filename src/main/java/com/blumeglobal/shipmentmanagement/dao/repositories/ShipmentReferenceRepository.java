package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.model.Shipment;
import com.blumeglobal.shipmentmanagement.model.ShipmentReferences;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

/**
 * The ShipmentReferenceRepository is used for to manage the ShipmentReferences details
 *
 */
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface ShipmentReferenceRepository extends CrudRepository<ShipmentReferences,Long>, JpaSpecificationExecutor<ShipmentReferences> {
	Iterable<ShipmentReferences> findAllByShipmentReferencesLink(Shipment shipmentReferencesLink);
	
	@Query(nativeQuery = true, value = "SELECT SHIPMENTID FROM shipmentreferences where SHIPMENTREFTYPE = :refType "
    		+ "AND SHIPMENTREFVALUE = :refValue")
    List<BigDecimal> findShipmentIdByRefTypeValue(@Param("refType")  String refType, @Param("refValue")  String refValue);
    
    
}
