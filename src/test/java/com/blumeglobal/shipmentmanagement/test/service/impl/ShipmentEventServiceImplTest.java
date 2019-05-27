package com.blumeglobal.shipmentmanagement.test.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.junit.Before;
import org.junit.Rule;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.blumeglobal.shipmentmanagement.exceptions.DataNotFoundException;
import com.blumeglobal.shipmentmanagement.exceptions.InValidDataException;
import com.blumeglobal.shipmentmanagement.request.ShipmentEventRequest;
import com.blumeglobal.shipmentmanagement.response.EventResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentEventResponse;
import static org.mockito.Mockito.*;

import java.util.List;

import com.blumeglobal.shipmentmanagement.service.impl.ShipmentEventServiceImpl;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class ShipmentEventServiceImplTest {
	
	@Autowired
    ShipmentEventServiceImpl shipmentEventService;

	@Before
	    public void init() {
		 MockitoAnnotations.initMocks(this);
	 }
	
	/**           
	 * To test createShipmentEvent method invalid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test(expected = InValidDataException.class)
	public void testCreateShipmentEventNoRequest() throws Exception{
		ShipmentEventRequest createEventsRequest = new ShipmentEventRequest();
		createEventsRequest = null;
		Mockito.when(shipmentEventService.createShipmentEvent(createEventsRequest));
	}
	
	/**           
	 * To test createShipmentEvent method invalid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test(expected = InValidDataException.class)
	public void testCreateShipmentEventValidationFailure() throws Exception{
		ShipmentEventRequest createEventsRequest = new ShipmentEventRequest();
		createEventsRequest.setState("statetest");
		Mockito.when(shipmentEventService.createShipmentEvent(createEventsRequest));
	}
	
	/**           
	 * To test createShipmentEvent method invalid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test(expected = InValidDataException.class)
	public void testCreateShipmentEventInvalidDate() throws Exception{
		ShipmentEventRequest createEventsRequest = new ShipmentEventRequest();
		createEventsRequest.setEventTime("12/10/2018");
		Mockito.when(shipmentEventService.createShipmentEvent(createEventsRequest));
	}
	
	/**           
	 * To test createShipmentEvent method invalid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test(expected = InValidDataException.class)
	public void testCreateShipmentEventEventCodeNull() throws Exception{
		ShipmentEventRequest createEventsRequest = new ShipmentEventRequest();
		createEventsRequest.setEventCode("Test0011");
		createEventsRequest.setReportSource("OCEANEVENTS");
		createEventsRequest.setState("abcd");
		createEventsRequest.setCountry("US");
		createEventsRequest.setEventTime("02-11-2018 09:20:30");
		createEventsRequest.setDestinationState("US");
		Mockito.when(shipmentEventService.createShipmentEvent(createEventsRequest));
	} 
	
	/**           
	 * To test createShipmentEvent method invalid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test(expected = InValidDataException.class)
	public void testCreateShipmentEventEventOceanEventsVesselNull() throws Exception{
		ShipmentEventRequest createEventsRequest = new ShipmentEventRequest();
		createEventsRequest.setEventCode("EE");
		createEventsRequest.setReportSource("OCEANEVENTS");
		createEventsRequest.setState("abcd");
		createEventsRequest.setCountry("US");
		createEventsRequest.setEventTime("02-11-2018 09:20:30");
		createEventsRequest.setDestinationState("US");
		createEventsRequest.setVessel(null);
		createEventsRequest.setVoyageNumber("Voyage123");
		createEventsRequest.setUnitId("Unit092901");
		createEventsRequest.setCity("chicago");
		EventResponse eventResponse = shipmentEventService.createShipmentEvent(createEventsRequest);
		Mockito.when(shipmentEventService.createShipmentEvent(createEventsRequest));
	} 
	
	/**           
	 * To test createShipmentEvent method valid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test
	public void testCreateShipmentEventOceanEventsValid() throws Exception{
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
		EventResponse shipmentEventResponse = shipmentEventService.createShipmentEvent(createEventsRequest);
		//assertEquals("abcd",shipmentEventResponse.getState());
	} 
	
	/**           
	 * To test createShipmentEvent method valid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test
	public void testCreateShipmentEventWorkOrderValid() throws Exception{
		ShipmentEventRequest createEventsRequest = new ShipmentEventRequest();
		createEventsRequest.setEventCode("EE");
		createEventsRequest.setReportSource("rampevent");
		createEventsRequest.setState("abcd");
		createEventsRequest.setCountry("US");
		createEventsRequest.setEventTime("02-11-2018 09:20:30");
		createEventsRequest.setDestinationState("US");
		createEventsRequest.setVessel("LIMARI");
		createEventsRequest.setVoyageNumber("048N");
		createEventsRequest.setUnitId("SUDU3678665");
		createEventsRequest.setHouseBill("ANRM64SSZAI1032X");
		createEventsRequest.setCity("chicago");
		createEventsRequest.setOriginatorCode("IASDM");
		createEventsRequest.setReceiverCode("COPB");
		createEventsRequest.setHouseBill("test");
		EventResponse shipmentEventResponse = shipmentEventService.createShipmentEvent(createEventsRequest);
		//assertEquals("abcd",shipmentEventResponse.getState());
	} 
	
	/**           
	 * To test createShipmentEvent method valid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test
	public void testCreateShipmentEventEventRailEventsValid() throws Exception{
		ShipmentEventRequest createEventsRequest = new ShipmentEventRequest();
		createEventsRequest.setEventCode("PB");
		createEventsRequest.setReportSource("RampEvent");
		createEventsRequest.setState("abcd");
		createEventsRequest.setCountry("US");
		createEventsRequest.setEventTime("02-11-2018 09:20:30");
		createEventsRequest.setDestinationState("US");
		createEventsRequest.setResolvedEventId(1L);
		createEventsRequest.setReasonName("JMK Test");
		createEventsRequest.setCity("SSss");
		createEventsRequest.setOriginatorCode("phone");
		createEventsRequest.setReceiverCode("phone");
		createEventsRequest.setOriginatorCode("IASDM");
		createEventsRequest.setReceiverCode("COPB");
		createEventsRequest.setHouseBill("test");
		EventResponse shipmentEventResponse = shipmentEventService.createShipmentEvent(createEventsRequest);
		//assertEquals("abcd",shipmentEventResponse.getState());
	}
	
	/**           
	 * To test createShipmentEvent method valid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test
	public void testCreateShipmentEventEventRailEventsValidEventName() throws Exception{
		ShipmentEventRequest createEventsRequest = new ShipmentEventRequest();
		createEventsRequest.setEventName("Insufficient Paperwork Customs hold");
		createEventsRequest.setReportSource("drayevents");
		createEventsRequest.setState("abcd");
		createEventsRequest.setCountry("US");
		createEventsRequest.setEventTime("02-11-2018 09:20:30");
		createEventsRequest.setDestinationState("US");
		createEventsRequest.setResolvedEventId(1L);
		createEventsRequest.setReasonName("JMK Test");
		EventResponse shipmentEventResponse = shipmentEventService.createShipmentEvent(createEventsRequest);
		//assertEquals("abcd",shipmentEventResponse.getState());
	}
	
	/**           
	 * To test getSearchShipmentEvents method invalid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test(expected = DataNotFoundException.class)
	public void testGetSearchShipmentEventsNoData() throws Exception{
		Mockito.when(shipmentEventService.getSearchShipmentEvents("unitId=Unit091243"));
		
	}
	
	/**           
	 * To test getSearchShipmentEvents method valid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test
	public void testGetSearchShipmentEventsValid() throws Exception{
		List<ShipmentEventResponse> eventResponses =shipmentEventService.getSearchShipmentEvents("unitId=Unit092901");
		assertTrue("Not Empty list", eventResponses.size()>0);
	}
	
	/**           
	 * To test getSearchShipmentEvents method valid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test
	public void testgetShipmentEventsValid() throws Exception{
		List<ShipmentEventResponse> eventResponses =shipmentEventService.getShipmentEvents("MEDUSH597149");
		assertTrue("Not Empty list", eventResponses.size()>0);
	}
	
	/**           
	 * To test getSearchShipmentEvents method invalid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test(expected = InValidDataException.class)
	public void testgetShipmentEventsInValid() throws Exception{
		Mockito.when(shipmentEventService.getShipmentEvents(null));
	}
	
	/**           
	 * To test getSearchShipmentEvents method invalid case
	 * @throws Exception
	 *             To Handle the service exceptions
	 */
	@Test(expected = DataNotFoundException.class)
	public void testgetShipmentEventsNoData() throws Exception{
		Mockito.when(shipmentEventService.getShipmentEvents("t45"));
	}
	

}
