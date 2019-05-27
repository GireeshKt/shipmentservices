package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.model.Routing;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

/**
 * The RoutingRepository is used for to manage the Routing details
 *
 */
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface RoutingRepository extends CrudRepository<Routing,String>, JpaSpecificationExecutor<Routing> {
	
	
	//Find List of All LaneGroupsId by its origin DEAFULTGROUP flag 'Y', isActive flag 'Y' and its laneId
	@Query(nativeQuery = true, value ="SELECT * FROM ROUTING WHERE ISACTIVE = 'Y' AND LANEGROUPID = :lanegroupId order by routingid")
	List<Routing> getLaneGroupsId( @Param("lanegroupId") String lanegroupId);
	

	@Query(nativeQuery=true, value = "select * "
									+ "from routing "
									+ "where routingid = (select routingid "
														+ "from (select * from( select routingid, lower(rtrim(ltrim(REPLACE(originlocationid,' ','')))) as origin," +
																				"lower(rtrim(ltrim(REPLACE(destinationlocationid,' ','')))) as destination," + "modeoftransport "+ 
																				"from routing)" + 
															"where lower(modeoftransport) = :transMode and lower(rtrim(ltrim(origin))) = :originAdd and lower(rtrim(ltrim(destination))) = :destAdd and rownum = 1))")
	List<Routing> getRoutingRecord(@Param("transMode") String transMode, @Param("originAdd") String originAdd, @Param("destAdd") String destAdd);
	
	@Query(nativeQuery = true, value ="SELECT * FROM ROUTING WHERE ROUTINGID= :routingid")
	Routing getRoutingById( @Param("routingid") String routingid);
}
