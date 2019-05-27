package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.model.Shipment;
import com.blumeglobal.shipmentmanagement.model.ShipmentStop;
import com.blumeglobal.shipmentmanagement.response.ShipmentStopResponse;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;
/**
 * The ShipmentMilestonesRepository is used to maintain ShipmentMilestone entity operation
 *
 */
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface ShipmentStopsRepository extends CrudRepository<ShipmentStop, Long>, JpaSpecificationExecutor<ShipmentStop>{
	List<ShipmentStop> getShipmentStopBysailingScheduleId(Long sailingScheduleId);
}