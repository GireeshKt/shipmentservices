package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.model.ShipmentSchedule;
import com.blumeglobal.shipmentmanagement.model.ShipmentStop;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;
/**
 * The ShipmentSchedulesRepository is used to maintain ShipmentSchedule entity operation
 *
 */
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface ShipmentSchedulesRepository extends CrudRepository<ShipmentSchedule, Long>, JpaSpecificationExecutor<ShipmentSchedule>{
	List<ShipmentSchedule> getShipmentScheduleByserviceLane(String serviceLane);
	List<ShipmentSchedule> getShipmentScheduleBycarrierName(String carrierName);
	List<ShipmentSchedule> getShipmentScheduleByvessel(String vessel);
	List<ShipmentSchedule> getShipmentScheduleByOriginLocationIdAndDestinationLocationId(String origin, String destination);
	List<ShipmentSchedule> getShipmentScheduleByOriginLocationId(String origin);
	List<ShipmentSchedule> getShipmentScheduleByDestinationLocationId(String destination);
}