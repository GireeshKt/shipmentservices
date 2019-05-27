package com.blumeglobal.shipmentmanagement.dao.specifications;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.blumeglobal.shipmentmanagement.dm.ShipmentEvent;
import com.blumeglobal.shipmentmanagement.service.helper.DispatchConstants;
import com.blumeglobal.shipmentmanagement.utils.DateUtil;
/**
 * The ShipmentEventSpecification is used defining query condition using specification 
 *
 */
public class ShipmentEventSpecification implements Specification<ShipmentEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	CriteriaSpecification criteriaSpecific;

	public ShipmentEventSpecification(CriteriaSpecification criteriaSpecification) {
		this.criteriaSpecific = criteriaSpecification;
	}
	
	@Override
	public Predicate toPredicate(Root<ShipmentEvent> root, CriteriaQuery<?> cQuery, CriteriaBuilder cBuilder) {
		
		if(criteriaSpecific!=null) {
			
		if(root.get(criteriaSpecific.getField()).getJavaType() == String.class) {			
			if("=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
				return cBuilder.equal(root.<String>get(criteriaSpecific.getField()),criteriaSpecific.getValue());
			}else if("DATEBETWEEN".equalsIgnoreCase(criteriaSpecific.getOperator())) {
				return cBuilder.between(root.<Date>get(criteriaSpecific.getField()), criteriaSpecific.getStartDate(), criteriaSpecific.getEndDate());
			}else if("<".equalsIgnoreCase(criteriaSpecific.getOperator())) {
				return cBuilder.lessThan(root.<String>get(criteriaSpecific.getField()),criteriaSpecific.getValue());
			}else if(">".equalsIgnoreCase(criteriaSpecific.getOperator())) {
				return cBuilder.greaterThan(root.<String>get(criteriaSpecific.getField()),criteriaSpecific.getValue());
			}else if("<=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
				return cBuilder.lessThanOrEqualTo(root.<String>get(criteriaSpecific.getField()),criteriaSpecific.getValue());
			}else if(">=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
				return cBuilder.greaterThanOrEqualTo(root.<String>get(criteriaSpecific.getField()),criteriaSpecific.getValue());
			}else if("IN".equalsIgnoreCase(criteriaSpecific.getOperator())) {
				return root.<String>get(criteriaSpecific.getField()).in(criteriaSpecific.getValueList());
			}
		}else if(root.get(criteriaSpecific.getField()).getJavaType() == Date.class) {
			Date inputValue = DateUtil.convertStringToDate(criteriaSpecific.getValue(), DispatchConstants.DATE_FORMAT_MM_DD_YYYY_HH_MM);
			if("=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
				return cBuilder.equal(root.<Date>get(criteriaSpecific.getField()),inputValue);
			}else if("<".equalsIgnoreCase(criteriaSpecific.getOperator())) {
				return cBuilder.lessThan(root.<Date>get(criteriaSpecific.getField()),inputValue);
			}else if(">".equalsIgnoreCase(criteriaSpecific.getOperator())) {
				return cBuilder.greaterThan(root.<Date>get(criteriaSpecific.getField()),inputValue);
			}else if("<=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
				return cBuilder.lessThanOrEqualTo(root.<Date>get(criteriaSpecific.getField()),inputValue);
			}else if(">=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
				return cBuilder.greaterThanOrEqualTo(root.<Date>get(criteriaSpecific.getField()),inputValue);
			}
		}
		}
		return null;
	}

}
