package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.model.ShipmentLegDetails;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

/**
 * The ShipmentEventRepository is used for to manage the ShipmentLeg details
 *
 */
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface ShipmentLegDetailsRepository extends CrudRepository<ShipmentLegDetails, Long>, JpaSpecificationExecutor<ShipmentLegDetails> {
 List<ShipmentLegDetails> findBywoBookingIdAndLegMode(Long id,String legMode);
 List<ShipmentLegDetails> findByDestinationCityAndDestinationZipCodeAndUnitId(String city, String postalCode, String unitId);
 ShipmentLegDetails findByWoBookingNumberAndShipmentReferenceNumberAndUnitId(String workordernumber, String shipmentNumber, String unitId);
 List<ShipmentLegDetails> findByWoBookingNumberOrderByWoBookingDateDesc(String workordernumber );
}
