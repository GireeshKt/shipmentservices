package com.blumeglobal.shipmentmanagement.dao.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blumeglobal.shipmentmanagement.model.LocationMaster;
import com.blumeglobal.shipmentmanagement.model.Milestone;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;
/**
 * The LocationMasterRepository is used to maintain locationMaster entity operation
 *
 */
@Repository
@REZ1Logger
@REZ1PerformanceLogger
public interface LocationMasterRepository extends CrudRepository<LocationMaster, String>, JpaSpecificationExecutor<Milestone>{
	@Query(nativeQuery = true, value ="select * from locationmaster where lower(rtrim(ltrim(REPLACE(location,' ','')))) = :locationId and rownum=1 ")
	LocationMaster getLocationType( @Param("locationId") String lacationId);
	
	
	@Query(nativeQuery = true, value ="SELECT LOCATION FROM LOCATIONMASTER WHERE TYPEDESCRIPTION = :typeDescription")
	List<String> getLocationByTypeDescription( @Param("typeDescription") String typeDescription);
	
	@Query(nativeQuery = true, value ="SELECT * FROM LOCATIONMASTER")
	List<LocationMaster> getAllMasterLocations();
	
	@Query(nativeQuery = true, value ="select * from LOCATIONMASTER where lower (location) like CONCAT(CONCAT('%', :locationId), '%') limit 1 ")
	LocationMaster getLocationTypeByLocationName( @Param("locationId") String lacationId);

}