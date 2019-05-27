package com.blumeglobal.shipmentmanagement.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.model.Milestone;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;
/**
 * The MilestonesRepository is used to maintain Milestones entity operation
 *
 */
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface MilestonesRepository extends CrudRepository<Milestone, Long>, JpaSpecificationExecutor<Milestone>{
	@Query(nativeQuery = true, value = "select * from MILESTONE where lower(modeoftransport) = :modeoftransport \r\n" + 
		"and (organizationId is null or organizationid = :oragnizationid) "
		+ " order by orderseq ")
	List<Milestone> getMilestoneByModeAndOrganization(@Param("modeoftransport") String modeoftransport, @Param("oragnizationid") String oragnizationid);
	
	@Query(nativeQuery = true, value ="SELECT * FROM MILESTONE WHERE MILESTONEID= :milestoneId")
	Milestone findByMileStoneId( @Param("milestoneId") Long milestoneId);
	
	@Query(value="select * from MILESTONE m",nativeQuery = true) 
	List<Milestone> findAllMilestones();
}