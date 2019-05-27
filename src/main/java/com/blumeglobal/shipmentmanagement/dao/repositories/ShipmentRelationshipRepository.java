package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.model.ShipmentRelationship;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;
/**
 * The ShipmentRelationshipRepository is used to maintain ShipmentRelationship entity operation
 *
 */
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface ShipmentRelationshipRepository extends CrudRepository<ShipmentRelationship, Long>, JpaSpecificationExecutor<ShipmentRelationship>{
	List<ShipmentRelationship> findByShipmentId(Long shipmentId);
}