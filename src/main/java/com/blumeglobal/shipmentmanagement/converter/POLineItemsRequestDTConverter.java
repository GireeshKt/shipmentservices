package com.blumeglobal.shipmentmanagement.converter;



import com.blumeglobal.shipmentmanagement.model.POLineItems;
import com.blumeglobal.shipmentmanagement.request.POLineItemsAPIRequest;
import com.blumeglobal.shipmentmanagement.utils.DateUtils;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class POLineItemsRequestDTConverter extends CustomMapper<POLineItemsAPIRequest, POLineItems> {
	
	private String dateFormatPattern ="MM-dd-yyyy HH:mm:ss";
	
	public POLineItemsRequestDTConverter(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}
		
	@Override
    public void mapAtoB(POLineItemsAPIRequest poReq, POLineItems po, MappingContext context) {
		java.sql.Timestamp createdOn= DateUtils.convertStringToTimestamp (poReq.getCreatedOn(), dateFormatPattern);
		java.sql.Timestamp updatedOn= DateUtils.convertStringToTimestamp(poReq.getUpdatedOn(), dateFormatPattern);
		po.setCreatedOn(createdOn);
		po.setUpdatedOn(updatedOn);
		
    } 

}
