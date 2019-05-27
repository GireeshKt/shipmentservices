package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.model.Charge;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

/**
 * The ChargeRepository is used to maintain Charge entity operation
 *
 */
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface ChargeRepository
        extends CrudRepository<Charge, Long>, JpaSpecificationExecutor<Charge> {
    List<Charge> findByEquipmentOnShipmentId(Long equipmentOnShipmentId);
}
