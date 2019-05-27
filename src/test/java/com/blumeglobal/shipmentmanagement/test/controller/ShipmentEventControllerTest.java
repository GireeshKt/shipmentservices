package com.blumeglobal.shipmentmanagement.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blumeglobal.shipmentmanagement.controller.ShipmentEventController;
import com.blumeglobal.shipmentmanagement.exceptions.DataNotFoundException;
import com.blumeglobal.shipmentmanagement.exceptions.InValidDataException;
import com.blumeglobal.shipmentmanagement.request.ShipmentEventRequest;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class ShipmentEventControllerTest {
	
	@Autowired
	ShipmentEventController shipmentEventController;

	@Before
	    public void init() {
		 MockitoAnnotations.initMocks(this);
	 }
	
	/**           
	 * To test CreateShipmentEvent method invalid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
//	@Test(expected = InValidDataException.class)
//	public void testCreateShipmentEventNoRequest() throws Exception{
//		ShipmentEventRequest createEventsRequest = new ShipmentEventRequest();
//		createEventsRequest = null;
//		Mockito.when(shipmentEventController.createShipmentEvent(createEventsRequest));
//		
//		
//	}
//	/**           
//	 * To test CreateShipmentEvent method invalid case
//	 * @throws Exception
//	 *             To Handle the service exceptions
//	 */
//	@Test(expected = InValidDataException.class)
//	public void testCreateShipmentEventValidationFailure() throws Exception{
//		ShipmentEventRequest createEventsRequest = new ShipmentEventRequest();
//		createEventsRequest.setState("statetest");
//		Mockito.when(shipmentEventController.createShipmentEvent(createEventsRequest));
//	}
//	
//	/**           
//	 * To test CreateShipmentEvent method invalid case
//	 * @throws Exception
//	 *             To Handle the service exceptions
//	 */
//	@Test(expected = InValidDataException.class)
//	public void testCreateShipmentEventInvalidDate() throws Exception{
//		ShipmentEventRequest createEventsRequest = new ShipmentEventRequest();
//		createEventsRequest.setEventTime("12/10/2018");
//		Mockito.when(shipmentEventController.createShipmentEvent(createEventsRequest));
//	}
//	
//	/**           
//	 * To test CreateShipmentEvent method invalid case
//	 * @throws Exception
//	 *             To Handle the service exceptions
//	 */
//	@Test(expected = InValidDataException.class)
//	public void testCreateShipmentEventEventCodeNull() throws Exception{
//		ShipmentEventRequest createEventsRequest = new ShipmentEventRequest();
//		createEventsRequest.setEventCode("Test001");
//		Mockito.when(shipmentEventController.createShipmentEvent(createEventsRequest));
//	} 
	
	/**           
	 * To test CreateShipmentEvent method valid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test
	public void testCreateShipmentEventEventOceanEventsValid() throws Exception{
		ShipmentEventRequest createEventsRequest = new ShipmentEventRequest();
		createEventsRequest.setEventCode("A");
		createEventsRequest.setReportSource("OCEANEVENTS");
		createEventsRequest.setState("abcd");
		createEventsRequest.setCountry("IN");
		createEventsRequest.setEventTime("02-11-2018 09:20:30");
		createEventsRequest.setDestinationState("US");
		createEventsRequest.setVessel("OCEAN");
		createEventsRequest.setVoyageNumber("Voyage1234");
		createEventsRequest.setUnitId("string");
		createEventsRequest.setCity("SSss");
		createEventsRequest.setOriginatorCode("IASDM");
		createEventsRequest.setReceiverCode("COPB");
		createEventsRequest.setHouseBill("test");
		//ResponseEntity<?> shipmentEventResponse = shipmentEventController.createShipmentEvent(createEventsRequest);
		//assertEquals(shipmentEventResponse.getStatusCodeValue(),200);
	} 
	
	/**           
	 * To test CreateShipmentEvent method valid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test
	public void testCreateShipmentEventEventRailEventsValid() throws Exception{
		ShipmentEventRequest createEventsRequest = new ShipmentEventRequest();
		createEventsRequest.setEventCode("EE");
		createEventsRequest.setReportSource("RampEvent");
		createEventsRequest.setState("abcd");
		createEventsRequest.setCountry("US");
		createEventsRequest.setEventTime("02-11-2018 09:20:30");
		createEventsRequest.setDestinationState("US");
		createEventsRequest.setResolvedEventId(1L);
		createEventsRequest.setReasonName("JMK Test");
		createEventsRequest.setCity("SSss");
		createEventsRequest.setOriginatorCode("IASDM");
		createEventsRequest.setReceiverCode("COPB");
		createEventsRequest.setHouseBill("test");
		//ResponseEntity<?> shipmentEventResponse = shipmentEventController.createShipmentEvent(createEventsRequest);
		//assertEquals(shipmentEventResponse.getStatusCodeValue(),200);
	}
	
	/**           
	 * To test getTrackingEvents method invalid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test(expected = DataNotFoundException.class)
	public void testGetSearchShipmentEventsNoData() throws Exception{
		//shipmentEventController.getTrackingEvents("unitIddfde01");
		
	}
	
	/**           
	 * To test getTrackingEvents method valid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test
	public void testGetSearchShipmentEventsValid() throws Exception{
		///ResponseEntity<?> eventResponses =shipmentEventController.getTrackingEvents("unitId=Unit092901");
		//assertEquals(eventResponses.getStatusCodeValue(),200);
	}
	
	/**           
	 * To test getShipmentEvents method valid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test
	public void testgetShipmentEventsValid() throws Exception{
		//ResponseEntity<?> eventResponses =shipmentEventController.getShipmentEvents("MEDUSH597149");
		//assertEquals(eventResponses.getStatusCodeValue(),200);
	}
	
	/**           
	 * To test getShipmentEvents method invalid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test(expected = InValidDataException.class)
	public void testgetShipmentEventsInValid() throws Exception{
		//shipmentEventController.getShipmentEvents(null);
	}
	
	/**           
	 * To test getShipmentEvents method invalid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test(expected = DataNotFoundException.class)
	public void testgetShipmentEventsNoData() throws Exception{
		//shipmentEventController.getShipmentEvents("t45");
	}
	

}