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

import com.blumeglobal.shipmentmanagement.dm.ShipmentEvent;
import com.blumeglobal.shipmentmanagement.utils.DateUtil;

/**
 * The TrackingEventSpecification is used defining query condition using specification 
 *
 */
@Component
public class TrackingEventSpecification {
	
	public TrackingEventSpecification() {
		
	}
	
	
	public Specification<ShipmentEvent> getTrackingEventsByReferenceIds(String searchvalue){
		
		return new Specification<ShipmentEvent>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ShipmentEvent> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return cb.or(cb.equal(root.get("shipmentReferenceNumber"),searchvalue),
						cb.equal(root.get("houseBill"),searchvalue),
						cb.equal(root.get("masterBill"), searchvalue),
						cb.equal(root.get("onHandNumber"), searchvalue),
						cb.equal(root.get("unitId"), searchvalue),
						cb.equal(root.get("proNumberBol"), searchvalue),
						cb.equal(root.get("workOrderNumber"), searchvalue),
						cb.equal(root.get("associatedUnitId"), searchvalue));
					
			}
			
		};
	}
	
public Specification<ShipmentEvent> getTrackingByAssets(String searchvalue,String startDt, String endDt){
		
		return new Specification<ShipmentEvent>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ShipmentEvent> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate pd = cb.or(cb.equal(root.get("unitId"),searchvalue),
						cb.equal(root.get("associatedUnitId"), searchvalue));
				cq.orderBy(cb.desc(root.get("eventTime")));
				return cb.and(pd,cb.greaterThanOrEqualTo(root.get("eventTime"), startDt),(cb.lessThanOrEqualTo(root.get("eventTime"), endDt)));
			}
			
		};
	}
	
	
	public Specification<ShipmentEvent> andConditionSpecification(List<CriteriaSpecification> criteriaSpecList){
			List<Specification<ShipmentEvent>> eventSpecs = new ArrayList<>();
			for(CriteriaSpecification criteriaSpecific : criteriaSpecList) {
				eventSpecs.add(new ShipmentEventSpecification(criteriaSpecific));
			}
			if(!eventSpecs.isEmpty()) {
				Specification<ShipmentEvent> finalEventSpec = eventSpecs.get(0);
				for (int i = 1; i < eventSpecs.size(); i++) {
					finalEventSpec = Specification.where(finalEventSpec).and(eventSpecs.get(i));
				}
				return finalEventSpec;
			}	
			return null;		
	}
	
	public Specification<ShipmentEvent> searchANDSpecification(List<CriteriaSpecification> criteriaSpecList){
		return new Specification<ShipmentEvent>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ShipmentEvent> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();				
				for(CriteriaSpecification criteriaSpecific : criteriaSpecList) {
					if(root.get(criteriaSpecific.getField()).getJavaType() == String.class) {			
							if("=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.equal(root.<String>get(criteriaSpecific.getField()),criteriaSpecific.getValue()));
							}else if("DATEBETWEEN".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.between(root.<Date>get(criteriaSpecific.getField()), criteriaSpecific.getStartDate(), criteriaSpecific.getEndDate()));
							}else if("<".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.lessThan(root.<String>get(criteriaSpecific.getField()),criteriaSpecific.getValue()));
							}else if(">".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.greaterThan(root.<String>get(criteriaSpecific.getField()),criteriaSpecific.getValue()));
							}else if("<=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.lessThanOrEqualTo(root.<String>get(criteriaSpecific.getField()),criteriaSpecific.getValue()));
							}else if(">=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.greaterThanOrEqualTo(root.<String>get(criteriaSpecific.getField()),criteriaSpecific.getValue()));
							}else if("IN".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(root.<String>get(criteriaSpecific.getField()).in(criteriaSpecific.getValueList()));
							}
						}else if(root.get(criteriaSpecific.getField()).getJavaType() == Date.class) {
							//Date inputValue = DateUtil.convertStringToDate(criteriaSpecific.getValue(), DispatchConstants.DATE_FORMAT_MM_DD_YYYY_HH_MM);
							Date inputValue = DateUtil.convertWithTries(criteriaSpecific.getValue());
							if("=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.equal(root.<Date>get(criteriaSpecific.getField()),inputValue));
							}else if("<".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.lessThan(root.<Date>get(criteriaSpecific.getField()),inputValue));
							}else if(">".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.greaterThan(root.<Date>get(criteriaSpecific.getField()),inputValue));
							}else if("<=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.lessThanOrEqualTo(root.<Date>get(criteriaSpecific.getField()),inputValue));
							}else if(">=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.greaterThanOrEqualTo(root.<Date>get(criteriaSpecific.getField()),inputValue));
							}
						}					
				}
				cq.orderBy(cb.desc(root.get("eventTime")));
				return cb.and(predicates.toArray(new Predicate[] {}));
			}
			
		};
}
	
	public Specification<ShipmentEvent> searchORSpecification(List<CriteriaSpecification> criteriaSpecList){
		return new Specification<ShipmentEvent>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ShipmentEvent> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();				
				for(CriteriaSpecification criteriaSpecific : criteriaSpecList) {
					if(root.get(criteriaSpecific.getField()).getJavaType() == String.class) {			
							if("=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.equal(root.<String>get(criteriaSpecific.getField()),criteriaSpecific.getValue()));
							}else if("DATEBETWEEN".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.between(root.<Date>get(criteriaSpecific.getField()), criteriaSpecific.getStartDate(), criteriaSpecific.getEndDate()));
							}else if("<".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.lessThan(root.<String>get(criteriaSpecific.getField()),criteriaSpecific.getValue()));
							}else if(">".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.greaterThan(root.<String>get(criteriaSpecific.getField()),criteriaSpecific.getValue()));
							}else if("<=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.lessThanOrEqualTo(root.<String>get(criteriaSpecific.getField()),criteriaSpecific.getValue()));
							}else if(">=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.greaterThanOrEqualTo(root.<String>get(criteriaSpecific.getField()),criteriaSpecific.getValue()));
							}else if("IN".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(root.<String>get(criteriaSpecific.getField()).in(criteriaSpecific.getValueList()));
							}
						}else if(root.get(criteriaSpecific.getField()).getJavaType() == Date.class) {
							//Date inputValue = DateUtil.convertStringToDate(criteriaSpecific.getValue(), DispatchConstants.DATE_FORMAT_MM_DD_YYYY_HH_MM);
							Date inputValue = DateUtil.convertWithTries(criteriaSpecific.getValue());
							if("=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.equal(root.<Date>get(criteriaSpecific.getField()),inputValue));
							}else if("<".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.lessThan(root.<Date>get(criteriaSpecific.getField()),inputValue));
							}else if(">".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.greaterThan(root.<Date>get(criteriaSpecific.getField()),inputValue));
							}else if("<=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.lessThanOrEqualTo(root.<Date>get(criteriaSpecific.getField()),inputValue));
							}else if(">=".equalsIgnoreCase(criteriaSpecific.getOperator())) {
								predicates.add(cb.greaterThanOrEqualTo(root.<Date>get(criteriaSpecific.getField()),inputValue));
							}
						}					
				}
				cq.orderBy(cb.desc(root.get("eventTime")));
				return cb.or(predicates.toArray(new Predicate[] {}));
			}
			
		};
}
	
	
}
