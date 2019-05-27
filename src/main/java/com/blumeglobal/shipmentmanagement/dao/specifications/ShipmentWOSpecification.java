package com.blumeglobal.shipmentmanagement.dao.specifications;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.model.ShipmentWO;
import com.blumeglobal.shipmentmanagement.utils.DateUtil;

@Component
public class ShipmentWOSpecification {
	
	public ShipmentWOSpecification() {

	}
	
	public Specification<ShipmentWO> viewShipmentWO(List<CriteriaSpecification> criteriaSpecList,
			String opreration){
		
		return new Specification<ShipmentWO>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ShipmentWO> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<>();
				for (CriteriaSpecification criteriaSpecific : criteriaSpecList) {
					if (root.get(criteriaSpecific.getField()).getJavaType() == String.class) {
						if ("=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
							predicates.add(cb.equal(root.<String>get(criteriaSpecific.getField()),
									criteriaSpecific.getValue()));
						} else if ("DATEBETWEEN".equalsIgnoreCase(criteriaSpecific.getOperator())) {
							predicates.add(cb.between(root.<Date>get(criteriaSpecific.getField()),
									criteriaSpecific.getStartDate(), criteriaSpecific.getEndDate()));
						} else if ("<".equalsIgnoreCase(criteriaSpecific.getOperator())) {
							predicates.add(cb.lessThan(root.<String>get(criteriaSpecific.getField()),
									criteriaSpecific.getValue()));
						} else if (">".equalsIgnoreCase(criteriaSpecific.getOperator())) {
							predicates.add(cb.greaterThan(root.<String>get(criteriaSpecific.getField()),
									criteriaSpecific.getValue()));
						} else if ("<=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
							predicates.add(cb.lessThanOrEqualTo(root.<String>get(criteriaSpecific.getField()),
									criteriaSpecific.getValue()));
						} else if (">=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
							predicates.add(cb.greaterThanOrEqualTo(root.<String>get(criteriaSpecific.getField()),
									criteriaSpecific.getValue()));
						} else if ("IN".equalsIgnoreCase(criteriaSpecific.getOperator())) {
							predicates.add(
									root.<String>get(criteriaSpecific.getField()).in(criteriaSpecific.getValueList()));
						}
					} else if (root.get(criteriaSpecific.getField()).getJavaType() == Date.class) {
						Date inputValue = DateUtil.convertStringToDate(criteriaSpecific.getValue(), "MM-dd-yyyy");
						if ("=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
							predicates.add(cb.equal(root.<Date>get(criteriaSpecific.getField()), inputValue));
						} else if ("<".equalsIgnoreCase(criteriaSpecific.getOperator())) {
							predicates.add(cb.lessThan(root.<Date>get(criteriaSpecific.getField()), inputValue));
						} else if (">".equalsIgnoreCase(criteriaSpecific.getOperator())) {
							predicates.add(cb.greaterThan(root.<Date>get(criteriaSpecific.getField()), inputValue));
						} else if ("<=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
							predicates
									.add(cb.lessThanOrEqualTo(root.<Date>get(criteriaSpecific.getField()), inputValue));
						} else if (">=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
							predicates.add(
									cb.greaterThanOrEqualTo(root.<Date>get(criteriaSpecific.getField()), inputValue));
						}
					} else if (root.get(criteriaSpecific.getField()).getJavaType() == Long.class) {
						Long inputValue = new Long(criteriaSpecific.getValue());
						if ("=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
							predicates.add(cb.equal(root.<Long>get(criteriaSpecific.getField()), inputValue));
						} else if ("<".equalsIgnoreCase(criteriaSpecific.getOperator())) {
							predicates.add(cb.lessThan(root.<Long>get(criteriaSpecific.getField()), inputValue));
						} else if (">".equalsIgnoreCase(criteriaSpecific.getOperator())) {
							predicates.add(cb.greaterThan(root.<Long>get(criteriaSpecific.getField()), inputValue));
						} else if ("<=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
							predicates
									.add(cb.lessThanOrEqualTo(root.<Long>get(criteriaSpecific.getField()), inputValue));
						} else if (">=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
							predicates.add(
									cb.greaterThanOrEqualTo(root.<Long>get(criteriaSpecific.getField()), inputValue));
						}
					}
					
				}
				if ("OR".equals(opreration)) {
					return cb.or(predicates.toArray(new Predicate[] {}));
				} else {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
			}
		};
		
	}

}
