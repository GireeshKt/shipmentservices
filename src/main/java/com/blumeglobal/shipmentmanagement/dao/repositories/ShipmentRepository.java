package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.model.Shipment;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

/**
 * The ShipmentRepository is used for to manage the Shipment details
 *
 */
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface ShipmentRepository extends CrudRepository<Shipment,Long>, JpaSpecificationExecutor<Shipment> {
	Shipment findByShipmentId(Long shipmentId);

	
	/*
	 * @Query(nativeQuery = true, value
	 * ="select * from shipment where referenceId = :referenceId \n" +
	 * " and referenceType = :referenceType and rownum=1 \n" + "") Shipment
	 * getShipmentByReferenceIdType( @Param("referenceId") Long
	 * referenceId, @Param("referenceType") String referenceType);
	 */
	
	
	@Query(nativeQuery = true, value ="select * from SHIPMENT where referenceId = :referenceId \n" + 
			" and referenceType = :referenceType limit 1 \n" + 
			"")
	Shipment getShipmentByReferenceIdType( @Param("referenceId") Long referenceId, @Param("referenceType") String referenceType);
	
	
	@Query(nativeQuery = true, value = "select * from SHIPMENT where E2E_SHIPMENTID = :e2eShipmentId \n" + 
			"order by SCHEDULEDELIVERYDATE,EXPECTEDDELIVERYDATE, EXPORTCUTOFFDATE, \n" + 
			"LASTFREEDATE, WORKORDERCREATEDDATE, CREATEDATE")
	List<Shipment> getShipmentByE2EShipmentId(@Param("e2eShipmentId") Long e2eShipmentId);

	@Query(nativeQuery = true, value = "select * from SHIPMENT sh\n" + 
		"where (sh.ORGANIZATIONCODE is null or sh.ORGANIZATIONCODE in :organizationCode ) and exists \n" + 
		"(select * from SHIPMENTREFERENCES sr\n" + 
		"where sr.shipmentid=sh.shipmentid\n" + 
		"and sr.shipmentrefvalue= :shipmentref ) \n" + 
		"order by SCHEDULEDELIVERYDATE,EXPECTEDDELIVERYDATE, EXPORTCUTOFFDATE, \n" + 
		"LASTFREEDATE, WORKORDERCREATEDDATE, CREATEDATE")
	List<Shipment> findShipmentsByShipmentRef( @Param("shipmentref")  String shipmentref, @Param("organizationCode") List<String> organizationCode);
	

	@Query(nativeQuery = true, value = "select status.description from status, workorder wo\n" + 
			" where status.statusid=wo.statusid and wo.workorderid= :workorderId")
	String getWorkorderStatusById(@Param("workorderId") Long workorderId);
	
	@Query(nativeQuery = true, value = "SELECT * FROM SHIPMENT"
			+ " INNER JOIN SHIPMENTREFERENCES ON SHIPMENT.shipmentid = SHIPMENTREFERENCES.shipmentid"
			+ " WHERE shipmentreferences.shipmentrefvalue = :workOrderNumber AND shipmentreferences.shipmentreftype = 'WORKORDERNUM'")
	List<Shipment> getShipmentFromWorkOrderNumber(@Param("workOrderNumber") String workOrderNumber);
    
	@Query(nativeQuery = true, value = "SELECT * FROM SHIPMENT"
            + " INNER JOIN SHIPMENTREFERENCES ON SHIPMENT.shipmentid = SHIPMENTREFERENCES.shipmentid"
            + "WHERE shipmentreferences.shipmentrefvalue = :billOfLadingNumber AND shipmentreferences.shipmentreftype = 'BILLOFLADING'")
    List<Shipment> getShipmentFromBillOfLadingNumber(@Param("billOfLadingNumber") String billOfLadingNumber);


    @Query(nativeQuery = true, value = "SELECT * FROM SHIPMENT"
            + " INNER JOIN SHIPMENTREFERENCES ON SHIPMENT.shipmentid = SHIPMENTREFERENCES.shipmentid"
            + " WHERE shipmentreferences.shipmentrefvalue = :bookingNumber AND shipmentreferences.shipmentreftype = 'BOOKINGNUM'")
    List<Shipment> getShipmentFromBookingNumber(@Param("bookingNumber") String bookingNumber);
    
    @Query(nativeQuery = true, value = "SELECT * FROM SHIPMENT"
            + " INNER JOIN SHIPMENTREFERENCES ON SHIPMENT.shipmentid = SHIPMENTREFERENCES.shipmentid"
            + " WHERE shipmentreferences.shipmentrefvalue = :shipmentReferenceNumber AND shipmentreferences.shipmentreftype = 'SHIPMENTREFNUM'")
    List<Shipment> getShipmentFromShipmentReferenceNumber(@Param("shipmentReferenceNumber") String shipmentReferenceNumber);
    
    @Query(nativeQuery = true, value = "SELECT * FROM SHIPMENT"
            + " WHERE SHIPMENT.unitId = :unitId ")
    List<Shipment> getShipmentFromUnitId(@Param("unitId") String unitId);

    @Query(nativeQuery = true, value = "select * from SHIPMENT sh\n"
			+ " where not exists ( select * from shipmentmilestones sm where sm.shipmentid = sh.shipmentid ) "
			+ " order by sh.shipmentid ")
	List<Shipment> findAllShipmentsWithoutShipmentMilestones();
		
	@Query(nativeQuery = true, value = "select * from SHIPMENT where shipmentid = "
			+ " (select parentshipmentid from SHIPMENTRELATIONSHIP where shipmentid = :shipmentId limit 1) ")
	Shipment getParentShipmentByShipmentId(@Param("shipmentId") Long shipmentId);

	@Query(nativeQuery = true, value = "select * from SHIPMENT where E2E_SHIPMENTID = :e2eShipmentId and (status is null or status != 'Cancelled') \n" + 
			"order by SCHEDULEDELIVERYDATE,EXPECTEDDELIVERYDATE, EXPORTCUTOFFDATE, \n" + 
			"LASTFREEDATE, WORKORDERCREATEDDATE, CREATEDATE")
	List<Shipment> getNotCancelledShipmentByE2EShipmentId(@Param("e2eShipmentId") Long e2eShipmentId);

    @Query(nativeQuery = true, value = "select * from SHIPMENT sh \n" + 
            "    where  exists \n" + 
            "        (select * from SHIPMENTREFERENCES sr \n" + 
            "           where sr.shipmentid = sh.shipmentid\n" + 
            "           and sr.shipmentrefvalue = :refValue\n" + 
            "           and sr.shipmentrefType = :refType) \n" + 
             " and rownum=1 ")
    Shipment findShipmentByRefTypeValue(@Param("refType")  String refType, @Param("refValue")  String refValue);

    @Query(nativeQuery = true, value = "select * from SHIPMENT sh \n" + 
            "    where  exists \n" + 
            "        (select * from SHIPMENTREFERENCES sr \n" + 
            "           where sr.shipmentid = sh.shipmentid\n" + 
            "           and sr.shipmentrefvalue = :refValue\n" + 
            "           and sr.shipmentrefType = :refType) \n" )
    List<Shipment> findShipmentsByRefTypeValue(@Param("refType")  String refType, @Param("refValue")  String refValue);
	
	
}