package com.blumeglobal.shipmentmanagement.converter;



import com.blumeglobal.shipmentmanagement.model.POLineItems;
import com.blumeglobal.shipmentmanagement.response.POLineItemsResponse;
import com.blumeglobal.shipmentmanagement.utils.DateUtils;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class POLineItemsResponseDTConverter extends CustomMapper<POLineItems, POLineItemsResponse> {
	private String dateFormatPattern ="MM-dd-yyyy HH:mm:ss";
	
	public POLineItemsResponseDTConverter(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}
	
	@Override
    public void mapAtoB(POLineItems po, POLineItemsResponse poRes, MappingContext context) {
		String createdOn = DateUtils.convertDateToString(po.getCreatedOn(), dateFormatPattern);
		poRes.setCreatedOn(createdOn);
    }
}
