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
import com.blumeglobal.shipmentmanagement.model.ShipmentLegDetails;
import com.blumeglobal.shipmentmanagement.utils.DateUtil;

@Component
public class ShipmentLegTrackingSpecification {

	public ShipmentLegTrackingSpecification() {

	}

	public Specification<ShipmentLegDetails> searchShipmentLegswithOpr(List<CriteriaSpecification> criteriaSpecList,
			String opreration, String legMode) {
		return new Specification<ShipmentLegDetails>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ShipmentLegDetails> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
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
						// Date inputValue = DateUtil.convertStringToDate(criteriaSpecific.getValue(),
						// DispatchConstants.DATE_FORMAT_MM_DD_YYYY_HH_MM);
						Date inputValue = DateUtil.convertWithTries(criteriaSpecific.getValue());
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
				cq.orderBy(cb.asc(root.get("woBookingDate")));
				if ("DRAY".equals(legMode) || "RAIL".equals(legMode)) {
					Predicate legPredicate = cb.equal(root.<Date>get("legMode"), legMode);
					if ("OR".equals(opreration)) {
						return cb.and(legPredicate, cb.or(predicates.toArray(new Predicate[] {})));
					} else {
						return cb.and(legPredicate, cb.and(predicates.toArray(new Predicate[] {})));
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
	
	public Specification<ShipmentLegDetails> searchShipmentLegswithIds(String ids, String legMode) {
		return new Specification<ShipmentLegDetails>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ShipmentLegDetails> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				if ("DRAY".equals(legMode) || "RAIL".equals(legMode)) {
					Predicate legPredicate = cb.equal(root.<String>get("legMode"), legMode);
					return cb.and(legPredicate,cb.or(cb.equal(root.<String>get("woBookingNumber"),ids),
						cb.equal(root.<String>get("unitId"),ids),
						cb.equal(root.<String>get("masterAirwayBillNumber"),ids),
						cb.equal(root.<String>get("billOfLadingNumber"),ids),
						cb.equal(root.<String>get("bookingNumber"),ids),
						cb.equal(root.<String>get("railBillingNumber"),ids),
						cb.equal(root.<String>get("houseAirwayBillNumber"),ids)));
				}else {
					return cb.or(cb.equal(root.<String>get("woBookingNumber"),ids),
							cb.equal(root.<String>get("unitId"),ids),
							cb.equal(root.<String>get("masterAirwayBillNumber"),ids),
							cb.equal(root.<String>get("billOfLadingNumber"),ids),
							cb.equal(root.<String>get("bookingNumber"),ids),
							cb.equal(root.<String>get("railBillingNumber"),ids),
							cb.equal(root.<String>get("houseAirwayBillNumber"),ids));
				}
				
			}

		};
	}
	
	public Specification<ShipmentLegDetails> searchWorkOrderWithEvents(ShipmentEvent events, String legMode) {
		return new Specification<ShipmentLegDetails>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ShipmentLegDetails> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				List<String> woStatusList = new ArrayList<>();
				woStatusList.add("Accepted");
				woStatusList.add("Active");
				woStatusList.add("AcceptWithErrors");
				List<Predicate> predicates1 = new ArrayList<>();
				if(events.getUnitId()!=null&& !events.getUnitId().isEmpty()) {
					predicates1.add(cb.equal(root.<String>get("unitId"), events.getUnitId()));
				}
				if(events.getAssociatedUnitId()!=null&& !events.getAssociatedUnitId().isEmpty()) {
					predicates1.add(cb.equal(root.<String>get("unitId"), events.getAssociatedUnitId()));
				}
				if(events.getAssociatedUnitId()!=null && !events.getAssociatedUnitId().isEmpty()) {
					predicates1.add(cb.equal(root.<String>get("associatedUnitId"), events.getAssociatedUnitId()));
				}
				if(legMode!=null) {
					predicates1.add(cb.equal(root.<String>get("legMode"), legMode));
				}
				predicates1.add(root.<String>get("woStatus").in(woStatusList));
				Predicate workOrderCond1 =cb.and(predicates1.toArray(new Predicate[] {}));
				
				List<Predicate> predicates = new ArrayList<>();
				if(events.getBillOfLadingNumber()!=null && !events.getBillOfLadingNumber().isEmpty()) {
					predicates.add(cb.equal(root.<String>get("billOfLadingNumber"), events.getBillOfLadingNumber()));
				}
				if(events.getBookingNumber()!=null && !events.getBookingNumber().isEmpty()) {
					predicates.add(cb.equal(root.<String>get("bookingNumber"), events.getBookingNumber()));
				}
				if(events.getShipmentReferenceNumber()!=null && !events.getShipmentReferenceNumber().isEmpty()) {
					predicates.add(cb.equal(root.<String>get("shipmentReferenceNumber"), events.getShipmentReferenceNumber()));
				}
				if(events.getHouseBill()!=null && !events.getHouseBill().isEmpty()) {
					predicates.add(cb.equal(root.<String>get("houseAirwayBillNumber"), events.getHouseBill()));
				}
				if(events.getMasterBill()!=null && !events.getMasterBill().isEmpty()) {
					predicates.add(cb.equal(root.<String>get("masterAirwayBillNumber"), events.getMasterBill()));
				}
				if(events.getOnHandNumber()!=null && !events.getOnHandNumber().isEmpty()) {
					predicates.add(cb.equal(root.<String>get("originatorOnHandNumber"), events.getOnHandNumber()));
				}
				if(events.getShipmentReferenceNumber()!=null && !events.getShipmentReferenceNumber().isEmpty()) {
					predicates.add(cb.equal(root.<String>get("referenceValue"), events.getShipmentReferenceNumber()));
				}
				if(events.getRailBillingNumber()!=null && !events.getRailBillingNumber().isEmpty()) {
					predicates.add(cb.equal(root.<String>get("railBillingNumber"), events.getRailBillingNumber()));
				}
				if(events.getWorkOrderNumber()!=null && !events.getWorkOrderNumber().isEmpty()) {
					predicates.add(cb.equal(root.<String>get("woBookingNumber"), events.getWorkOrderNumber()));
				}
				
				Predicate workOrderCond2 = cb.or(predicates.toArray(new Predicate[] {})); 
				
				return cb.and(workOrderCond1,workOrderCond2);
				
			}

		};
	}

}
