package com.blumeglobal.shipmentmanagement.test.service.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blumeglobal.shipmentmanagement.exceptions.DataNotFoundException;
import com.blumeglobal.shipmentmanagement.exceptions.InValidDataException;
import com.blumeglobal.shipmentmanagement.response.ShipmentLegDetailsResponse;
import com.blumeglobal.shipmentmanagement.service.impl.ShipmentEventServiceImpl;
import com.blumeglobal.shipmentmanagement.service.impl.ShipmentLegDetailsServiceImpl;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class ShipmentLegDetailsServiceImplTest {
	
	@Autowired
    ShipmentLegDetailsServiceImpl legDetailsServiceImpl;

	@Before
	    public void init() {
		 MockitoAnnotations.initMocks(this);
	 }
	
	@Test
	public void testSearchShipmentLegsValid() throws Exception{
		List<ShipmentLegDetailsResponse> legDetailsResponses = legDetailsServiceImpl.searchShipmentLegs("unitId=TEST220431", "OR", "DRAY");
		assertTrue("Not Empty list", legDetailsResponses.size()>0);
	}
		
	@Test(expected = DataNotFoundException.class)
	public void testSearchShipmentLegsNoData() throws Exception{
	List<ShipmentLegDetailsResponse> legDetailsResponses = legDetailsServiceImpl.searchShipmentLegs("unitId=TEST357011", "AND", "DRAY");
	}
	
	@Test
	public void testFindShipmentLegByModeAndIDsValid() throws Exception{
		List<ShipmentLegDetailsResponse> legDetailsResponses = legDetailsServiceImpl.findShipmentLegByModeAndIDs("TestSan001", "DRAY");
		assertTrue("Not Empty list", legDetailsResponses.size()>0);
	}	
	
	@Test(expected = DataNotFoundException.class)
	public void testFindShipmentLegByModeAndIDsNoData() throws Exception{
		List<ShipmentLegDetailsResponse> legDetailsResponses = legDetailsServiceImpl.findShipmentLegByModeAndIDs("TestSa464", "DRAY");
	}	
	
	@Test(expected = InValidDataException.class)
	public void testFindShipmentLegByModeAndIDsInvalid() throws Exception{
		List<ShipmentLegDetailsResponse> legDetailsResponses = legDetailsServiceImpl.findShipmentLegByModeAndIDs(null, "DRAY");
	}	
		
		
}


