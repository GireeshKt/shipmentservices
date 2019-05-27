package com.blumeglobal.shipmentmanagement.dm;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

/**
 * The ShipmentEventRepository is used for to manage the ShipmentEvent details
 *
 */
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface ShipmentEventRepository
		extends CrudRepository<ShipmentEvent, Long>, JpaSpecificationExecutor<ShipmentEvent> {

	ShipmentEvent findByshipmentEventId(Long shipmentEventId);
	List<ShipmentEvent> findByWorkOrderNumberAndEventCodeAndCityAndPostalCode(String workOrderNumber,String eventCode,String city,String postalCode);
	List<ShipmentEvent> findByHouseBillOrderByEventTimeDesc(String houseBill);

	List<ShipmentEvent> findByMasterBillOrderByEventTimeDesc(String masterBill);

	List<ShipmentEvent> findByOnHandNumberOrderByEventTimeDesc(String onHand);

	List<ShipmentEvent> findByUnitIdOrderByEventTimeDesc(String unitId);

	List<ShipmentEvent> findByShipmentReferenceOrderByEventTimeDesc(Long id);

	List<ShipmentEvent> findOneByShipmentLegRefOrderByEventTimeDesc(Long shipmentleg);

	List<ShipmentEvent> findByProNumberBolIn(List<String> proNumberBol);
	
	List<ShipmentEvent> findByWorkOrderNumberAndEventCodeAndReportSource(String workOrderNumber,String eventCode,String reportSource);
	
	List<ShipmentEvent> findByResolvedEventIdAndResolvedEventSource(Long resolvedEventId,String resolvedEventSource);

	List<ShipmentEvent> findEvenNameByMasterBillOrHouseBillOrOnHandNumberOrUnitIdOrderByEventTimeDesc(String masterBill,
			String houseBill, String onHandNumber, String unitId);
	
	List<ShipmentEvent> findByVesselAndVoyageNumberAndCityAndCountryAndEventCodeAndUnitId(String vessel, String voyage,
			String city, String country, String eventCode, String equipmentNumber);
	
	
	@Query(value = "select * from LE_SHIPMENTEVENTS where trunc(eventtime)>=to_date(:startDate,'DD-MON-YYYY') and trunc(eventtime)<=to_date(:endDate,'DD-MON-YYYY')\n" + 
			" and (unitid=:assetVal or associatedunitid=:assetVal) order by eventtime desc",nativeQuery=true)
	List<ShipmentEvent> searchShipmentAssets(@Param("assetVal")String assetVal, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
    @Query(value ="select * from le_shipmentevents  \n" + 
            "where housebill in :housebill \n" + 
            "or masterbill in :masterbill \n" + 
            "or workordernumber in :workordernumber \n" + 
            "or shipmentnumber in :shipmentnumber \n" + 
            "or shipmentreference in :shipmentreference \n" + 
            "or waybillnumber in :waybillnumber \n" + 
            "or billofladingnumber in :billofladingnumber \n" + 
            "or bookingNumber in :bookingNumber \n" + 
            "order by eventtime desc nulls last", nativeQuery=true)
    List<ShipmentEvent> searchShipmentEventsByRefs(@Param("housebill") Set<String> housebill, @Param("masterbill") Set<String> masterbill,
        @Param("workordernumber") Set<String> workordernumber, @Param("shipmentnumber") Set<String> shipmentnumber, @Param("shipmentreference") Set<String> shipmentreference,
        @Param("waybillnumber") Set<String> waybillnumber, @Param("billofladingnumber") Set<String> billofladingnumber, @Param("bookingNumber") Set<String> bookingNumber
        );
	
	@Query(value ="select * from le_shipmentevents  \n" + 
			"where  workordernumber in :workordernumber \n" + 
			"order by eventtime desc nulls last", nativeQuery=true)
	List<ShipmentEvent> searchShipmentEventsByWorkOrderNumbers(@Param("workordernumber") Set<String> workordernumber);

	List<ShipmentEvent> findByWorkOrderNumberOrderByEventTimeDesc(String workOrderNumber);

   @Query(value ="select * from LE_SHIPMENTEVENTS  \n" + 
            "where workordernumber in :workordernumber \n" + 
            "or billofladingnumber in :billofladingnumber \n" + 
            "or shipmentNumber in :shipmentRefNum \n" + 
            "or unitId in :unitId \n" + 
            "order by eventtime desc nulls last", nativeQuery=true)
    List<ShipmentEvent> searchShipmentEventsByRefs(@Param("workordernumber") Set<String> workordernumber, 
            @Param("billofladingnumber") Set<String> billofladingnumber, @Param("shipmentRefNum") Set<String> shipmentRefNum, 
            @Param("unitId") Set<String> unitId);
   
   
   

   @Query(value ="select *  from le_shipmentevents  \n" + 
           "where shipmenteventid in \n" + 
           "(\n" + 
           "select max(shipmenteventid) from le_shipmentevents   \n" + 
           "           where  (workordernumber = :workordernumber\n" + 
           "           or  billofladingnumber = :billofladingnumber\n" + 
           "           or  bookingNumber = :bookingNumber\n" + 
           "           or  shipmentnumber = :shipmentReferenceNumber  \n" + 
           "           or  unitId = :unitId)  \n" + 
           "           group by eventcode,workordernumber,billofladingnumber, " +
           " bookingNumber, shipmentnumber, unitId \n" + 
           ")order by eventtime desc nulls last", nativeQuery=true)
   List<ShipmentEvent> searchLastShipmentEventsByRefs(@Param("workordernumber") String workordernumber,
           @Param("billofladingnumber") String billofladingnumber, 
           @Param("bookingNumber") String bookingNumber,
           @Param("shipmentReferenceNumber") String shipmentReferenceNumber,
           @Param("unitId") String unitId);
}
