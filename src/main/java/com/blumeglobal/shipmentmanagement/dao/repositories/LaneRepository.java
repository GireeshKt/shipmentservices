package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.model.Lane;
import com.blumeglobal.shipmentmanagement.response.LaneLocationResponse;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

/**
 * The LaneRepository is used for to manage the Lane details
 *
 */
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface LaneRepository extends CrudRepository<Lane,String>, JpaSpecificationExecutor<Lane> {
	
	/*
	//Find List of All LaneId by its origin locationId, destination locationId, isActive flag 'Y' and its lane expirydate is greater than current date 
	@Query(nativeQuery = true, value ="SELECT LANEID from LANE "
			+ "WHERE ORIGIN_LOCATIONID = :origin_locationId AND DESTINATION_LOCATIONID = :destination_locationId AND "
			+ "ISACTIVE = 'Y' AND SYSDATE < to_char(LANE_EXPIRYDATE, 'DD-MON-YY');")
	List<String> getLaneId( @Param("origin_locationId") String origin_locationId, @Param("destination_locationId") String destination_locationId);
	
	*/
	
	//Find List of All LaneId by its origin locationId, destination locationId, isActive flag 'Y' and its lane expirydate is greater than current date 
	@Query(nativeQuery = true, value ="SELECT * from LANE "
				+ "WHERE ORIGIN_LOCATIONID = :origin_locationId AND DESTINATION_LOCATIONID = :destination_locationId AND "
				+ "ISACTIVE = 'Y' AND SYSDATE < to_char(LANE_EXPIRYDATE, 'DD-MON-YY')")
	List<Lane> getLaneId( @Param("origin_locationId") String origin_locationId, @Param("destination_locationId") String destination_locationId);
	
	
	@Query(nativeQuery = true, value ="SELECT * FROM LANE WHERE LANEID= :laneId")
	Lane getLaneById( @Param("laneId") String laneId);
	
	@Query(nativeQuery = true, value = "SELECT P.LANEID FROM LANE P WHERE P.LANEID IS NOT NULL")
	List<String> getAllIds();

}
