package com.blumeglobal.shipmentmanagement.dao.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.model.ShipmentNotification;

@Repository
public interface ShipmentNotificationRepository  extends CrudRepository<ShipmentNotification, Long>, JpaSpecificationExecutor<ShipmentNotification>{
	
	ShipmentNotification findByOriginatorCode(String originatorCode);
}
