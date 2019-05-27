package com.blumeglobal.shipmentmanagement.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blumeglobal.shipmentmanagement.controller.ShipmentLegController;
import com.blumeglobal.shipmentmanagement.exceptions.DataNotFoundException;
import com.blumeglobal.shipmentmanagement.exceptions.InValidDataException;
import com.blumeglobal.shipmentmanagement.response.ShipmentLegDetailsResponse;
import com.blumeglobal.shipmentmanagement.service.impl.ShipmentLegDetailsServiceImpl;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class ShipmentLegContollerTest {
	
	@Autowired
	ShipmentLegController shipmentLegContoller;

	@Before
	    public void init() {
		 MockitoAnnotations.initMocks(this);
	 }
	
	/**           
	 * To test searchShipmentLegs method valid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test
	public void testSearchShipmentLegsValid() throws Exception{
	//	ResponseEntity<?> legDetailsResponses = shipmentLegContoller.searchShipmentLegs("unitId=TEST220431", "OR", "DRAY");
	//	assertEquals(legDetailsResponses.getStatusCodeValue(),200);
	}
	
	/**           
	 * To test searchShipmentLegs method invalid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test(expected = DataNotFoundException.class)
	public void testSearchShipmentLegsNoData() throws Exception{
	//shipmentLegContoller.searchShipmentLegs("unitIdTEST220431", "OR", "DRAY");
	}
	
	/**           
	 * To test getShipmentLegbyIdAndMode method valid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test
	public void testFindShipmentLegByModeAndIDsValid() throws Exception{
	//	ResponseEntity<?> legDetailsResponses = shipmentLegContoller.getShipmentLegbyIdAndMode("TestSan001", "DRAY");
	//	assertEquals(legDetailsResponses.getStatusCodeValue(),200);
	}	
	
	/**           
	 * To test getShipmentLegbyIdAndMode method invalid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test(expected = DataNotFoundException.class)
	public void testFindShipmentLegByModeAndIDsNoData() throws Exception{
		//ResponseEntity<?> legDetailsResponses = shipmentLegContoller.getShipmentLegbyIdAndMode("TestSa464", "DRAY");
	}	
	
	/**           
	 * To test getShipmentLegbyIdAndMode method invalid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test(expected = InValidDataException.class)
	public void testFindShipmentLegByModeAndIDsInvalid() throws Exception{
		//shipmentLegContoller.getShipmentLegbyIdAndMode(null, "DRAY");
	}	
		
		
}



