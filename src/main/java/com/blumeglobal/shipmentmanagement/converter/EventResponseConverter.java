package com.blumeglobal.shipmentmanagement.converter;

import com.blumeglobal.shipmentmanagement.dm.ShipmentEvent;
import com.blumeglobal.shipmentmanagement.response.ShipmentEventResponse;
import com.blumeglobal.shipmentmanagement.utils.DateUtil;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class EventResponseConverter extends CustomMapper<ShipmentEvent, ShipmentEventResponse> {
	private String dateFormatPattern ="MM-dd-yyyy HH:mm:ss";
	
	public EventResponseConverter(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}
	
	@Override
    public void mapAtoB(ShipmentEvent event, ShipmentEventResponse eventRes, MappingContext context) {
		String dtStr = DateUtil.convertDateToString(event.getEventTime(), dateFormatPattern);		
		//eventRes.setEventTime(dtStr);
    }
}
