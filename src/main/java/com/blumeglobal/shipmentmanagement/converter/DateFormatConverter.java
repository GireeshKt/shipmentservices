package com.blumeglobal.shipmentmanagement.converter;


import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.blumeglobal.shipmentmanagement.utils.DateUtil;

@Converter
public class DateFormatConverter implements AttributeConverter<String, Date>{

	private String dateFormatPattern ="MM-dd-yyyy HH:mm:ss"; 
	@Override
	public Date convertToDatabaseColumn(String stringVal) {
		java.util.Date dtUtil= DateUtil.convertStringToDate(stringVal, dateFormatPattern);
		return dtUtil;
	}

	@Override
	public String convertToEntityAttribute(Date dateVal) {
		String outStrVal =null;
		String dtStr = DateUtil.convertDateToString(dateVal, dateFormatPattern);
		if(dtStr!=null) {
			outStrVal = dtStr;
		}
		return outStrVal;
	}	

}
