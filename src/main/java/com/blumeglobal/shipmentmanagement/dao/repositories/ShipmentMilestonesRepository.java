package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.model.ShipmentMilestone;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;
/**
 * The ShipmentMilestonesRepository is used to maintain ShipmentMilestone entity operation
 *
 */
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface ShipmentMilestonesRepository extends CrudRepository<ShipmentMilestone, Long>{
	List<ShipmentMilestone> findByShipmentIdOrderByEstimatedDate(Long shipmentId);
	List<ShipmentMilestone> findByE2eShipmentIdOrderByShipmentMilestoneId(Long e2eShipmentId);
	List<ShipmentMilestone> findByShipmentIdOrderByShipmentMilestoneIdDesc(Long shipmentId);
}


