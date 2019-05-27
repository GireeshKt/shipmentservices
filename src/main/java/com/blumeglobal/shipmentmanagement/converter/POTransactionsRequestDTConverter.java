package com.blumeglobal.shipmentmanagement.converter;



import com.blumeglobal.shipmentmanagement.model.POTransactions;
import com.blumeglobal.shipmentmanagement.request.POTransactionsAPIRequest;
import com.blumeglobal.shipmentmanagement.utils.DateUtils;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class POTransactionsRequestDTConverter extends CustomMapper<POTransactionsAPIRequest, POTransactions> {
	
	private String dateFormatPattern ="MM-dd-yyyy HH:mm:ss";
	
	public POTransactionsRequestDTConverter(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}
		
	@Override
    public void mapAtoB(POTransactionsAPIRequest poReq, POTransactions po, MappingContext context) {
		java.sql.Timestamp createdOn= DateUtils.convertStringToTimestamp (poReq.getCreatedOn(), dateFormatPattern);
		java.sql.Timestamp updatedOn= DateUtils.convertStringToTimestamp(poReq.getUpdatedOn(), dateFormatPattern);
		po.setCreatedOn(createdOn);
		po.setUpdatedOn(updatedOn);
		
    } 

}
