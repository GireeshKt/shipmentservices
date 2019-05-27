package com.blumeglobal.shipmentmanagement.converter;



import com.blumeglobal.shipmentmanagement.model.PurchaseOrder;
import com.blumeglobal.shipmentmanagement.response.POAPIResponse;
import com.blumeglobal.shipmentmanagement.utils.DateUtils;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class PurchaseOrderResponseDTConverter extends CustomMapper<PurchaseOrder, POAPIResponse> {
	private String dateFormatPattern ="MM-dd-yyyy HH:mm:ss";
	
	public PurchaseOrderResponseDTConverter(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}
	
	@Override
    public void mapAtoB(PurchaseOrder po, POAPIResponse poRes, MappingContext context) {
		String createdOn = DateUtils.convertDateToString(po.getCreatedOn(), dateFormatPattern);
		String updatedOn = DateUtils.convertDateToString(po.getUpdatedOn(), dateFormatPattern);		
		poRes.setCreatedOn(createdOn);
		poRes.setUpdatedOn(updatedOn);
    }
}
