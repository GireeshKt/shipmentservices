package com.blumeglobal.shipmentmanagement.converter;


import com.blumeglobal.shipmentmanagement.model.POTransactions;
import com.blumeglobal.shipmentmanagement.response.POTransactionsAPIResponse;
import com.blumeglobal.shipmentmanagement.utils.DateUtils;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class POTransactionsResponseDTConverter extends CustomMapper<POTransactions, POTransactionsAPIResponse> {
	private String dateFormatPattern ="MM-dd-yyyy HH:mm:ss";
	
	public POTransactionsResponseDTConverter(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}
	
	@Override
    public void mapAtoB(POTransactions po, POTransactionsAPIResponse poRes, MappingContext context) {
		String createdOn = DateUtils.convertDateToString(po.getCreatedOn(), dateFormatPattern);
		poRes.setCreatedOn(createdOn);
    }
}
