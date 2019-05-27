package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.model.EquipmentOnShipment;
import com.blumeglobal.shipmentmanagement.model.ShipmentMilestone;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

/**
 * The EquipmentOnShipmentRepository is used to maintain EquipmentOnShipment entity operation
 *
 */
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface EquipmentOnShipmentRepository
        extends CrudRepository<EquipmentOnShipment, Long>, JpaSpecificationExecutor<EquipmentOnShipment> {
    List<EquipmentOnShipment> findByShipmentLegId(Long shipmentId);
}
