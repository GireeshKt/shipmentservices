package com.blumeglobal.shipmentmanagement.dao.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.model.BookingDetails;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

/**
* The ShipmentRepository is used for to manage the Shipment details
*
*/
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface BookingDetailsRepository extends CrudRepository<BookingDetails,Long>, JpaSpecificationExecutor<BookingDetails> {

}
