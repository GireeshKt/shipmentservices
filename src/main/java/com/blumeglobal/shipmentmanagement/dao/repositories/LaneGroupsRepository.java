package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.model.Lane;
import com.blumeglobal.shipmentmanagement.model.LaneGroups;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

/**
 * The LaneGroupsRepository is used for to manage the LaneGroups details
 *
 */
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface LaneGroupsRepository extends CrudRepository<LaneGroups,String>, JpaSpecificationExecutor<LaneGroups> {
	
	/*
	//Find List of All LaneGroupsId by its origin DEAFULTGROUP flag 'Y', isActive flag 'Y' and its laneId
	@Query(nativeQuery = true, value ="SELECT LANEGROUPID FROM LANEGROUPS WHERE DEAFULTGROUP = 'Y' AND ISACTIVE = 'Y' AND LANEID = :laneId")
	List<String> getLaneGroupsId( @Param("laneId") String laneId);
	*/
	
	//Find List of All LaneGroupsId by its origin DEAFULTGROUP flag 'Y', isActive flag 'Y' and its laneId
	@Query(nativeQuery = true, value ="SELECT * FROM LANEGROUPS WHERE DEAFULTGROUP = 'Y' AND ISACTIVE = 'Y' AND LANEID = :laneId")
	List<LaneGroups> getLaneGroupsId( @Param("laneId") String laneId);
	
	@Query(nativeQuery = true, value = "SELECT P.LANEID FROM LANEGROUPS P WHERE P.LANEID IS NOT NULL")
	List<String> getAllIds();
	
	@Query(nativeQuery = true, value = "SELECT P.LANEGROUPID FROM LANEGROUPS P WHERE P.LANEGROUPID IS NOT NULL")
	List<String> getAllLaneGroupIds();
	
	@Query(nativeQuery = true, value ="SELECT * FROM LANEGROUPS  WHERE LANEGROUPID= :laneGroupId")
	LaneGroups getLaneGroupById( @Param("laneGroupId") String laneGroupId);
}
