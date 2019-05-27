package com.blumeglobal.shipmentmanagement.converter;

import com.blumeglobal.shipmentmanagement.dm.ShipmentEvent;
import com.blumeglobal.shipmentmanagement.request.ShipmentEventRequest;
import com.blumeglobal.shipmentmanagement.utils.DateUtil;
import com.blumeglobal.shipmentmanagement.utils.DateUtils;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class EventRequestConverter extends CustomMapper<ShipmentEventRequest, ShipmentEvent> {
	
	private String dateFormatPattern ="MM-dd-yyyy HH:mm:ss";
	
	public EventRequestConverter(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}
		
	@Override
    public void mapAtoB(ShipmentEventRequest eventReq, ShipmentEvent event, MappingContext context) {
		java.util.Date dtUtil= DateUtil.convertStringToDate(eventReq.getEventTime(), dateFormatPattern);
		event.setEventTime(dtUtil);
    }
}
