package com.blumeglobal.shipmentmanagement.dao.specifications;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author aleesha.john
 * CriteriaSpecification is used for construct the filtering condition
 *
 */
@Component
public class CriteriaSpecification implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String criteriaName; 
	private String field;
	private String operator;
	private String start;
	private String end;
	private String value;
	private String[] values;
	private Date dateValue;
	private Date startDate;
	private Date endDate;
	private List<String> valueList;
	
	public CriteriaSpecification() {
		
	}
	public CriteriaSpecification(String criteriaName,String field, String operator, String value) {
		this.criteriaName = criteriaName;
		this.field = field;
		this.operator = operator;
		this.value = value;
	}
	
	public CriteriaSpecification(String field, String operator, Date startDate,Date endDate) {
		this.field = field;
		this.operator = operator;
		this.startDate = startDate;
		this.endDate = endDate;
	}
		
	public CriteriaSpecification(String field, String operator, String value) {
		this.field = field;
		this.operator = operator;
		this.value = value;
	}
	
	public CriteriaSpecification(String field, String operator, List<String> values) {
		this.field = field;
		this.operator = operator;
		this.valueList = values;
	}
		
	public String getStart() {
		return start;
	}

	public List<String> getValueList() {
		return valueList;
	}
	public void setValueList(List<String> valueList) {
		this.valueList = valueList;
	}
	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCriteriaName() {
		return criteriaName;
	}
	public void setCriteriaName(String criteriaName) {
		this.criteriaName = criteriaName;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	

}
