package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.model.Shipment;
import com.blumeglobal.shipmentmanagement.model.Stop;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

/**
* The StopRepository is used for to manage the Stop details
*
*/
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface StopRepository extends CrudRepository<Stop,Long>, JpaSpecificationExecutor<Stop> {
	List<Stop> findByShipmentOrderByStopNumber(Shipment shipment);
	Stop findByShipmentAndName(Shipment shipment, String stopName);
}
