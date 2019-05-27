package com.blumeglobal.shipmentmanagement.converter;



import com.blumeglobal.shipmentmanagement.model.PurchaseOrder;
import com.blumeglobal.shipmentmanagement.response.ExcelPurchaseOrderSuccessRowsResponse;
import com.blumeglobal.shipmentmanagement.utils.DateUtils;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class ExcelPurchaseOrderDTConverter extends CustomMapper<ExcelPurchaseOrderSuccessRowsResponse, PurchaseOrder> {
	
	private String dateFormatPattern ="MM-dd-yyyy HH:mm:ss";
	
	public ExcelPurchaseOrderDTConverter(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}
		
	@Override
    public void mapAtoB(ExcelPurchaseOrderSuccessRowsResponse poReq, PurchaseOrder po, MappingContext context) {
		java.sql.Timestamp createdOn= DateUtils.convertStringToTimestamp (poReq.getCreatedOn(), dateFormatPattern);
		java.sql.Timestamp updatedOn= DateUtils.convertStringToTimestamp(poReq.getUpdatedOn(), dateFormatPattern);
		po.setCreatedOn(createdOn);
		po.setUpdatedOn(updatedOn);
		
    } 

}
