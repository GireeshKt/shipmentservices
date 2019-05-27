package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.model.ShipmentException;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;
/**
 * The ShipmentRelationshipRepository is used to maintain ShipmentRelationship entity operation
 *
 */
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface ShipmentExceptionRepository extends CrudRepository<ShipmentException, Long>, JpaSpecificationExecutor<ShipmentException>{
	List<ShipmentException> getShipmentExceptionByE2eShipmentIdOrderByOccuranceDate(Long e2eshipnmentId);
	
	@Query(nativeQuery=true, value = "select count(distinct(e2e_shipmentid)) \r\n" + 
			"from shipmentexceptions\r\n" + 
			"left join e2e_shipment \r\n" + 
			"on shipmentexceptions.e2e_shipmentid = e2e_shipment.shipment_id\r\n" + 
			"where organization_code = :organizationCode " +
			"AND  EXISTS ( SELECT  *  FROM  shipment sh\n" + 
			"WHERE  sh.e2e_shipmentid = e2e_shipment.shipment_id )\n" 
			)
	Long getTotalExceptionCount(@Param("organizationCode") String organizationCode);

	
	@Query(nativeQuery=true, value = "select * from shipmentexceptions where shipmentId = :shipmentId \r\n" + 
			"and lower(modeOfTransport) = :modeOfTransport and exceptionType = :exceptionType ")
	List<ShipmentException> findExceptionByShipmentIdModeType(@Param("shipmentId") Long shipmentId, @Param("modeOfTransport") String modeOfTransport,
			@Param("exceptionType") String exceptionType);
	
	List<ShipmentException> findByShipmentMilestoneId(Long shipmentMilestoneId);
	
	@Query(nativeQuery=true, value = "select count(*)  \r\n" + 
			"from SHIPMENTEXCEPTIONS\r\n" + 
			"where E2E_SHIPMENTID = :e2eShipmentId")
	Long getE2eShipmentExceptionCount(@Param("e2eShipmentId") Long e2eShipmentId);
	
	@Query(nativeQuery=true, value = "select se.* from SHIPMENTEXCEPTIONS se \r\n" + 
			"left join SHIPMENTMILESTONES sms on sms.shipmentmilestoneid=se.shipmentmilestoneid\r\n" + 
			"left join MILESTONE ml on ml.milestoneid = sms.milestoneid\r\n" + 
			"where se.e2e_shipmentid= :e2eShipmentId\r\n" + 
			"and (ml.display='Y' or ml.display is null) \r\n" + 
			"order by se.shipmentmilestoneid desc\r\n" )
	List<ShipmentException> getDisplayShipmentExceptionsByE2eShipmentId(@Param("e2eShipmentId") Long e2eShipmentId);

	@Query(nativeQuery=true, value = "select se.* from SHIPMENTEXCEPTIONS se \r\n" + 
			"left join SHIPMENTMILESTONES sms on sms.shipmentmilestoneid=se.shipmentmilestoneid\r\n" + 
			"left join MILESTONE ml on ml.milestoneid = sms.milestoneid\r\n" + 
			"where se.shipmentid= :shipmentId\r\n" + 
			"and (ml.display='Y' or ml.display is null) \r\n" + 
			"order by se.shipmentmilestoneid desc\r\n" )
	List<ShipmentException> getDisplayShipmentExceptionsByShipmentId(@Param("shipmentId") Long shipmentId);

}